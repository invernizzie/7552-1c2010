package control.command;

import control.Constants;
import control.MasksEnum;
import model.filters.impl.MaskFilter;
import view.components.MyFrame;

import java.awt.*;
import java.util.NoSuchElementException;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 * @created 11/04/2010
 */
public class MaskFilterCommand implements Command {

    MyFrame myFrame;
    MaskFilter maskFilter;

    public MaskFilterCommand(MasksEnum mask) throws CommandConstructionException {
        try {
            maskFilter = Constants.getMaskFilter(mask);
        }
        catch (NoSuchElementException e) {
            throw new CommandConstructionException();
        }
    }

    public void execute() throws CommandExecutionException {

        testExecute();

        Image img = maskFilter.filter(myFrame.getImage());
        myFrame.setImage(img);
        myFrame.repaint();
    }

    public void setFrame(MyFrame frame) {
        myFrame = frame;
    }

    private void testExecute() throws CommandExecutionException {
        if (myFrame == null)
            throw new CommandExecutionException();
    }
}
