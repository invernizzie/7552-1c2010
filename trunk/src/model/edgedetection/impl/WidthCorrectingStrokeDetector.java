package model.edgedetection.impl;

import model.edgedetection.Stroke;
import model.edgedetection.StrokeDetector;
import model.filters.masks.impl.Convolver;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 *         Date: 09/05/2010
 */
public class WidthCorrectingStrokeDetector implements StrokeDetector {

    private Image image;
    private StrokeWidthCorrectionConvolver convolver = new StrokeWidthCorrectionConvolver();
    private List<Stroke> strokes = null;
    private Set<Point> points = new HashSet<Point>();
    private int snapDistance = 20;

    public WidthCorrectingStrokeDetector(Image image) {
        this.image = image;
    }

    public void setThreshold(int threshold) {
        convolver.threshold = threshold;
    }

    public void setSnapDistance(int snapDistance) {
        this.snapDistance = snapDistance;
    }

    public List<Stroke> generateStrokes() {
        detectPoints();
        distributePoints();
        return (strokes == null) ? null : Collections.unmodifiableList(strokes);
    }

    private void detectPoints() {
        convolver.filter(image);
    }

    /**
     * Distribuye en trazos el conjunto de puntos
     */
    private void distributePoints() {
        // TODO Dividir en metodos mas cohesivos
        Iterator<Point> pointIterator = points.iterator();
        if (!pointIterator.hasNext())
            return;

        // Inicializo el primer trazo con el primer punto
        strokes = new ArrayList<Stroke>();
        strokes.add(new Stroke());
        strokes.get(0).addPoint(pointIterator.next());

        while (pointIterator.hasNext()) {
            Point point = pointIterator.next();

            /* Se buscan los trazos que esten a distancia del punto
             * menor o igual a snapDistance */
            List<Stroke> candidateStrokes = searchCandidates(point);

            Stroke chosenStroke;
            chosenStroke = chooseOrCreateStroke(point, candidateStrokes);
            chosenStroke.addPoint(point);
        }
    }

    /**
     * Elije entre los candidatos un trazo al que pueda agregarse un
     * punto dado. Si no hay candidatos, crea un nuevo trazo.
     *
     * @param point Punto al cual
     * @param candidateStrokes
     * @return
     */
    private Stroke chooseOrCreateStroke(Point point, List<Stroke> candidateStrokes) {
        Stroke chosenStroke;
        Iterator<Stroke> candidatesIterator = candidateStrokes.iterator();
        // Si no hay ningun trazo cercano, se crea uno nuevo
        if (!candidatesIterator.hasNext()) {
            chosenStroke = new Stroke();
            strokes.add(chosenStroke);
            return chosenStroke;
        }
        /* Si hay alguno, se elije el que menos modifica su
         * "derivada" al agregar el punto. */
        chosenStroke = candidatesIterator.next();
        double minDelta = chosenStroke.deltaDifferenceWith(point);
        while (candidatesIterator.hasNext()) {
            Stroke candidate = candidatesIterator.next();
            if (candidate.deltaDifferenceWith(point) < minDelta) {
                chosenStroke = candidate;
                minDelta = chosenStroke.deltaDifferenceWith(point);
            }
        }
        return chosenStroke;
    }

    /**
     * Busca los trazos que esten a distancia del punto
     * menor o igual a snapDistance.
     *
     * @param point
     * @return Lista de trazos a los que puede pertenecer el punto
     */
    private List<Stroke> searchCandidates(Point point) {
        List<Stroke> candidateStrokes = new ArrayList<Stroke>();
        for (Stroke stroke: strokes)
            if (stroke.distanceTo(point) <= snapDistance)
                candidateStrokes.add(stroke);
        return candidateStrokes;
    }

    private class StrokeWidthCorrectionConvolver extends Convolver {

        int threshold = 220;

        @Override
        public Image filter(Image in) {
            in.getSource().startProduction(this);

            try {
                convolve();
            } catch (Exception e) {
                System.out.println("Fallo Convolver: " + e);
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void convolve() {
            // Se recorre la imagen en cuadros de 5x5
            for(int y = 0; y < height/5; y++)
                for(int x = 0; x < width/5; x++) {

                    // Si hay puntos por debajo y por encima del threshold, es contorno
                    boolean belowThreshold = false, aboveThreshold = false;
                    for (int k = 0; k < 5 && !(belowThreshold && aboveThreshold) && (y*5+k < height); k++)
                        for (int j = 0; j < 5 && !(belowThreshold && aboveThreshold) && (x*5+j < width); j++)
                            if (grayValue(getImgPixel(x*5+j, y*5+k)) < threshold)
                                belowThreshold = true;
                            else
                                aboveThreshold = true;

                    if (belowThreshold && aboveThreshold)
                        points.add(new Point(x*5, y*5));
                }
        }

        private int grayValue(int rgb) {
            int r = (rgb >> 16) & 0xff;
            int g = (rgb >> 8) & 0xff;
            int b = rgb & 0xff;
            return (int) (.56 *g + .33 * r+ .11 * b);
        }

		@Override
		public boolean isParametrizable() {
			return false;
		}

		@Override
		public void setParameterValue(Double value) {}

    }
}
