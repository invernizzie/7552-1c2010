package control.command;

import control.command.exceptions.CommandExecutionException;
import model.edgedetection.impl.WidthCorrectingStrokeDetector;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 * @created 09/05/2010
 */
public class CommandDetectEdges extends MyFrameCommand {

    protected void doExecute() throws CommandExecutionException {
        WidthCorrectingStrokeDetector detector = new WidthCorrectingStrokeDetector(getFrame().getImage());
        getFrame().setStrokes(detector.generateStrokes());
        getFrame().repaint();
    }

    protected void testExecute() throws CommandExecutionException {
        super.testExecute();
        if (getFrame().getImage() == null)
            throw new CommandExecutionException(this);
    }
}
