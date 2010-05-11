package model.filters;

import java.awt.Image;

public interface Filter {
	public Image filter(Image image);
	public boolean isParametrizable();
	public void setParameterValue(Double value);
	
}
