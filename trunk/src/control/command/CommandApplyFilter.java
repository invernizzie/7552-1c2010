package control.command;

import java.awt.Image;

import model.filters.Filter;
import control.command.exceptions.CommandExecutionException;

/**
 * @author Esteban I. Invernizzi
 * @created 13/04/2010
 */
public class CommandApplyFilter extends MyFrameCommand {

    Filter filter;

    protected CommandApplyFilter() {}

    public CommandApplyFilter(Filter filter) {
        this.filter = filter;
    }

    protected void doExecute() throws CommandExecutionException {
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
