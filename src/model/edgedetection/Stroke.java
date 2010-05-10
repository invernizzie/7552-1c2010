package model.edgedetection;

import java.awt.*;
import java.util.*;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 *
 * Date: 09/05/2010
 */
public class Stroke {

    Deque<Point> points = new ArrayDeque<Point>();

    /**
	 * Agrega el punto al extremo más cercano del trazo.
	 *
	 * @param point
	 */
	public void addPoint(Point point){
        // TODO
        if (Math.abs(distanceToFirst(point)) < Math.abs(distanceToLast(point)))
            points.addFirst(point);
        else
            points.addLast(point);
	}

	/**
	 * Cierra el trazo. Si es necesario lo duplica en espejo.
	 */
	public void close(double snapDistance){
        // TODO
	}

    /**
     * Devuelve la distancia del punto al extremo mas cercano del trazo.
     *
     * @param point Punto cuya distancia al trazo se calcula
     */
    public double distanceTo(Point point) {
        return Math.min(distanceToFirst(point), distanceToLast(point));
    }

    private double distanceToFirst(Point point) {
        if (points.isEmpty())
            return Double.MAX_VALUE;
        return Math.abs(points.getFirst().distance(point));
    }

    private double distanceToLast(Point point) {
        if (points.isEmpty())
            return Double.MAX_VALUE;
        return Math.abs(points.getLast().distance(point));
    }

	/**
	 * Devuelve una aproximacion a la derivada direccional al final del trazo, de
	 * agregarse el punto pasado por parametro. El calculo se realiza sobre el
	 * extremo del trazo mas cercano al punto.
	 *
	 * @param point
	 */
	public double deltaDifferenceWith(Point point){
        // TODO
        return distanceTo(point);
	}
}
