package control.command;

import control.MyMenuHandler;
import control.command.exceptions.CommandExecutionException;
import view.components.MyFrame;

/**
 * @author Esteban I. Invernizzi
 * @created 13/04/2010
 */
public abstract class MyFrameCommand implements Command {

    MyFrame frame;

    protected void testExecute() throws CommandExecutionException {
        if (frame == null)
            throw new CommandExecutionException(this);
    }
    
    public MyFrame getFrame() {
        return frame;
    }

    public void setFrame(MyFrame frame) {
        this.frame = frame;
    }
}
