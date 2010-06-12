package model.fourier;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class TestDFT {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int N = 10;
		
		List<Point> dataInteger = new ArrayList<Point>();
		dataInteger.add(new Point(12, 34)); //este primer numero no se porque, pero no se considera para la comparacion, o por lo menos eso entendi.
		dataInteger.add(new Point(43, 23)); //23
		dataInteger.add(new Point(76, 66)); //66
		dataInteger.add(new Point(12, 1)); //1
		dataInteger.add(new Point(4, 78)); //78
		dataInteger.add(new Point(44, 75)); //75
		dataInteger.add(new Point(84, 44)); //44
		dataInteger.add(new Point(12, 23)); //23
		dataInteger.add(new Point(88, 49)); //49
		dataInteger.add(new Point(18, 29)); //29
		
		
		List<Complex> dataComplex = new ArrayList<Complex>();
		
		DFT dft = new DFT(dataInteger, N);
		for(int i = 0; i < N; i++){
			Complex p = dft.getDFTPoint(i);
			dataComplex.add(p);
		}

		System.out.println("----------------");
		IDFT idft = new IDFT(dataComplex, N);
		for(int i = 1; i < N; i++){
			Complex p = idft.getIDFTPoint(i);
			System.out.println(Math.round(p.getReal()));
		}

		
	}

}
