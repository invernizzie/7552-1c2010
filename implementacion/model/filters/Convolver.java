package model.filters;

import java.awt.*;
import java.awt.image.*;
import java.util.Hashtable;

public abstract class Convolver implements ImageConsumer, Filter {
	int width, height;
	int imgpixels[], newimgpixels[];
	
	public abstract void convolve();
	
	public synchronized void imageComplete(int dummy) {
		notifyAll();		
	}

	public void setColorModel(ColorModel dummy) { }

	public void setDimensions(int x, int y) {
		width = x;
		height = y;
		imgpixels = new int[x*y];
	}

	public void setHints(int dummy) { }

	public void setPixels(int x1, int y1, int w, int h,
			ColorModel model, byte[] pixels, int off, int scansize) {
		int pix, x, y, x2, y2, sx, sy;
		
		x2 = x1+w;
		y2 = y1+h;
		sy = off;
		for(y=y1; y<y2; y++) {
			sx = sy;
			for(x=x1; x<x2; x++) {
				pix = model.getRGB(pixels[sx++]);
				if((pix & 0xff000000) == 0)
					pix = 0x00ffffff;
				imgpixels[y*width+x] = pix;
			}
			sy += scansize;
		}		
	}

	public void setPixels(int x1, int y1, int w, int h,
			ColorModel model, int[] pixels, int off, int scansize) {
		int pix, x, y, x2, y2, sx, sy;
		
		x2 = x1+w;
		y2 = y1+h;
		sy = off;
		for(y=y1; y<y2; y++) {
			sx = sy;
			for(x=x1; x<x2; x++) {
				pix = model.getRGB(pixels[sx++]);
				if((pix & 0xff000000) == 0)
					pix = 0x00ffffff;
				imgpixels[y*width+x] = pix;
			}
			sy += scansize;
		}		
	}

	public void setProperties(Hashtable<?, ?> dummy) { }

	public Image filter(Image in) {
		in.getSource().startProduction(this);
		//waitForImage();
		newimgpixels = new int[width*height];
		
		try {
			convolve();
		} catch (Exception e) {
			System.out.println("Fallo Convolver: " + e);
			e.printStackTrace();
		}
		Canvas c = new Canvas();
		return c.createImage(new MemoryImageSource(width, height, newimgpixels, 0, width));
		
	}
	
	synchronized void waitForImage(){
		try { wait(); } catch (Exception e) { };
	}

}
