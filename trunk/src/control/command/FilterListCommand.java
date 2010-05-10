package control.command;

import java.awt.Image;

import model.filters.Filter;
import model.filters.FilterMap;
import control.command.exceptions.CommandExecutionException;


public class FilterListCommand extends MyFrameCommand {

    Filter[] filters;
    FilterMap filterMap;

    protected FilterListCommand() {
    	filterMap = new FilterMap();
    }

    public FilterListCommand(Filter[] filters) {
        this.filters = filters;
    	filterMap = new FilterMap();
    }

    protected void doExecute() throws CommandExecutionException {
        frame.setImage(frame.getImageOrig());
        frame.repaint();
        for (int i = 0; i < filters.length; i++) {
        	Filter filter = filters[i];
        	Image img = filter.filter(frame.getImage());
        	frame.setImage(img);
        	frame.repaint();
		}
    }

	public Filter[] getFilters() {
		return filters;
	}

	public void setFilters(Filter[] filters) {
		this.filters = filters;
	}

	public FilterMap getFilterMap() {
		return filterMap;
	}

	public void setFilterMap(FilterMap filterMap) {
		this.filterMap = filterMap;
	}




}
