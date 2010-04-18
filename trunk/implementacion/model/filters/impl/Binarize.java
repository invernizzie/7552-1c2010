package model.filters.impl;

import model.filters.Filter;

import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

/**
 * @author Esteban I. Invernizzi
 * @created 16/04/2010
 */
public class Binarize extends RGBImageFilter implements Filter {

    private int umbral = 220;

    public Image filter(Image image) {
        Canvas c = new Canvas();
		return c.createImage(new FilteredImageSource(image.getSource(),this));
    }

    @Override
    public int filterRGB(int x, int y, int rgb) {
        int r = (rgb >> 16) & 0xff;
		int g = (rgb >> 8) & 0xff;
		int b = rgb & 0xff;
		int grayValue = (int) (.56 *g + .33 * r+ .11 * b);
		return (grayValue < umbral) ? 0xff000000 : 0xffffffff;
    }

    public int getUmbral() {
        return umbral;
    }

    public void setUmbral(byte umbral) {
        if ((umbral > 225) || (umbral < 0))
            throw new IllegalArgumentException();
        this.umbral = umbral;
    }
}
