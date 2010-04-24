package model.filters;

import java.util.HashMap;
import java.util.Map;

import model.filters.impl.Binarize;
import model.filters.impl.Contrast;
import model.filters.impl.Grayscale;
import model.filters.impl.Invert;

import control.Constants;
import control.FilterEnum;
import control.MasksEnum;
import control.command.FilterCommand;

public class FilterMap{

	Map<String, FilterCommand> map;

	public FilterMap() {
		map = new HashMap<String, FilterCommand>();
		map.put(MasksEnum.BLUR.getNombre(), new FilterCommand(Constants.getMaskFilter(MasksEnum.BLUR)));
		map.put(MasksEnum.SHARPEN.getNombre(), new FilterCommand(Constants.getMaskFilter(MasksEnum.SHARPEN)));
		map.put(MasksEnum.LOW_PASS.getNombre(), new FilterCommand(Constants.getMaskFilter(MasksEnum.LOW_PASS)));
		map.put(MasksEnum.MID_PASS.getNombre(), new FilterCommand(Constants.getMaskFilter(MasksEnum.MID_PASS)));
		map.put(MasksEnum.PREWITT_1.getNombre(), new FilterCommand(Constants.getMaskFilter(MasksEnum.PREWITT_1)));
		map.put(MasksEnum.PREWITT_2.getNombre(), new FilterCommand(Constants.getMaskFilter(MasksEnum.PREWITT_2)));
		map.put(MasksEnum.GAUSS_LOW_PASS.getNombre(), new FilterCommand(Constants.getMaskFilter(MasksEnum.GAUSS_LOW_PASS)));
		map.put(MasksEnum.SMOOTH.getNombre(), new FilterCommand(Constants.getMaskFilter(MasksEnum.SMOOTH)));
		map.put(FilterEnum.INVERT.getNombre(), new FilterCommand( new Invert() ));
		map.put(FilterEnum.CONTRAST.getNombre(), new FilterCommand( new Contrast() ));
		map.put(FilterEnum.GREY_SCALE.getNombre(), new FilterCommand( new Grayscale() ));
		map.put(FilterEnum.BINARIZE.getNombre(), new FilterCommand( new Binarize() ));
	}
	
	public FilterCommand getFilterCommand(String key){
		return map.get(key);
	}
}
