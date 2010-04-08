package model.filters.masks.impl;

import model.filters.masks.Mask;

import java.awt.*;

/**
 * @author Esteban I. Invernizzi
 * @created 08/04/2010
 */
public class NormalizedMask implements Mask {

    Mask mask;

    public NormalizedMask(Mask mask) {
        this.mask = mask;
    }

    public Image apply(Image image) {
        return normalize(mask.apply(image));
    }

    private Image normalize(Image image) {
        // TODO Implementar normalizacion
        return image;
    }
}
