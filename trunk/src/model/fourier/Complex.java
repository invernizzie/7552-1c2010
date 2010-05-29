package model.fourier;

public class Complex {
  
	private float real;
	private float imaginary;

	public Complex(){
		this.real = 0f;
		this.imaginary = 0f;
	}

	public Complex(float real, float imaginary){
		this.real = real;
		this.imaginary = imaginary;
	}

	public float getReal() {
		return real;
	}

	public void setReal(float real) {
		this.real = real;
	}

	public float getImaginary() {
		return imaginary;
	}

	public void setImaginary(float imaginary) {
		this.imaginary = imaginary;
	}
	
	public float getMagnitude(){
	    return ( (float)Math.sqrt((real*real)+(imaginary*imaginary)) );
	}

	public float getAngle(){
	    float angle = (float)Math.atan( imaginary / real );
	    return ( (angle * 180.0f)/(float)Math.PI );
	}
	
}
