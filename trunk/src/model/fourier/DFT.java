package model.fourier;

import java.util.List;


public class DFT {
	
	private int numberOfPoints;      
	private List<Point> data;  

	public DFT(List<Point> data) {
		this.data = data;
		this.numberOfPoints = data.size();
	}


	public Complex getDFTPoint(int pointNumber){
		final double twoPi = 2 * Math.PI;
		Complex cx = new Complex();
	
		if (pointNumber >= 0 && pointNumber < numberOfPoints){
			double R = 0f;
			double I = 0f;
	
			if (pointNumber == 0){
				Point p;
				for (int n = 0; n < numberOfPoints; n++){
					p = (Point)data.get( n );
					R = R + p.getY();
				}
			}
			else{
				double x;
				double scale;
				Point p;
				for (int n = 0; n < numberOfPoints; n++){
					p = (Point)data.get( n );
					x = p.getY();
					scale = (twoPi * n * pointNumber)/numberOfPoints;
					R = R + x * Math.cos( scale );
					I = I - x * Math.sin( scale );
				}
			}
			cx.setReal( (float)R );
			cx.setImaginary( (float)I );
		}
		return cx;
	}

}
