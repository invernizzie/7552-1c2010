package control.command;

import control.Constants;
import control.command.exceptions.CommandConstructionException;
import control.command.exceptions.CommandExecutionException;
import model.filters.Filter;
import view.components.MyFrame;

import java.awt.*;
import java.util.NoSuchElementException;

/**
 * @author Esteban I. Invernizzi
 * @created 13/04/2010
 */
public class FilterCommand extends MyFrameCommand {

    Filter filter;

    protected FilterCommand() {}

    public FilterCommand(Filter filter) {
        this.filter = filter;
    }

    public void execute() throws CommandExecutionException {

        testExecute();

        Image img = filter.filter(frame.getImage());
        frame.setImage(img);
        frame.repaint();
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}
