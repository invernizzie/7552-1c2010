package model.edgedetection;

import java.awt.*;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 *
 * Date: 09/05/2010
 */
public class Stroke {

    /**
	 * Agrega el punto al extremo más cercano del trazo.
	 *
	 * @param point
	 */
	public void addPoint(Point point){
        // TODO
	}

	/**
	 * Cierra el trazo. Si es necesario (TODO criterio?) lo duplica en espejo.
	 */
	public void close(){
        // TODO
	}

    /**
     * Devuelve la distancia del punto al extremo mas cercano del trazo.
     *
     * @param point Punto cuya distancia al trazo se calcula
     */
    public int distanceTo(Point point) {
        return 0;
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
        return 0;
	}
}
