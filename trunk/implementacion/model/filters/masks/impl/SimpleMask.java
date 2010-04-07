package model.filters.masks.impl;

import model.filters.masks.Mask;

import java.awt.*;

/**
 * @author Esteban I. Invernizzi
 * @created 07/04/2010
 */
public class SimpleMask implements Mask {

    private double[][] coefs;

    public static SimpleMask create(double[][] coefs) throws InvalidMaskException {

        testCreate(coefs);
        SimpleMask result = new SimpleMask();
        result.coefs = coefs;
        return result;
    }

    public Image apply(Image image) {
        // TODO Copiar implementacion de Cristian
        return null;
    }

    private static void testCreate(double[][] coefs) throws InvalidMaskException {
        int width = coefs.length;
        if ((width < 3) || (width % 2 == 0))
            throw new InvalidMaskException();
        int height = coefs[0].length;
        if ((height < 3) || (height % 2 == 0))
            throw new InvalidMaskException();
        for(int i = 0; i < coefs.length; i++)
            if (coefs[i].length != height)
                throw new InvalidMaskException();
    }
}
