package control.command;

import control.Constants;
import control.MasksEnum;
import control.command.exceptions.CommandConstructionException;
import model.filters.impl.Binarize;
import model.filters.impl.Contrast;
import model.filters.impl.Grayscale;
import model.filters.impl.Invert;
import view.components.MyFrame;

import java.util.NoSuchElementException;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 * @created 11/04/2010
 */
public class CommandFactory {

    public static final String MASK_FILTER_PREFIX = "MASK_FILTER_";

    public static final String FILE_OPEN = "FILE_OPEN_COMMAND";
    public static final String FILE_SAVE = "FILE_SAVE_COMMAND";
    public static final String FILE_SAVE_AS = "FILE_SAVE_AS_COMMAND";
    public static final String EXIT = "EXIT_COMMAND";
    public static final String RESET = "RESET_COMMAND";
    public static final String RESIZE = "CHANGE_SIZE_COMMAND";
    public static final String INVERSION_FILTER = "INVERSION_FILTER_COMMAND";
    public static final String CONTRAST_FILTER = "CONTRAST_FILTER_COMMAND";
    public static final String GRAYSCALE_FILTER = "GRAYSCALE_FILTER_COMMAND";
    public static final String BINARIZE_FILTER = "BINARIZE_FILTER_COMMAND";


    public static Command getCommand(String commandName) throws CommandConstructionException {

        if (commandName.startsWith(MASK_FILTER_PREFIX))
            return getCommand(MasksEnum.valueOf(
                commandName.substring(MASK_FILTER_PREFIX.length())));

        if (FILE_OPEN.equals(commandName))
            return new CommandFileOpen();

        if (FILE_SAVE.equals(commandName))
            return new CommandFileSave();

        if (FILE_SAVE_AS.equals(commandName))
            return new CommandFileSaveAs();

        if (EXIT.equals(commandName))
            return new CommandExit();

        if (RESET.equals(commandName))
            return new CommandReset();

        if (RESIZE.equals(commandName))
            return new CommandResize();

        if (INVERSION_FILTER.equals(commandName))
            return new FilterCommand(new Invert());

        if (CONTRAST_FILTER.equals(commandName))
            return new FilterCommand(new Contrast());

        if (GRAYSCALE_FILTER.equals(commandName))
            return new FilterCommand(new Grayscale());

        if (BINARIZE_FILTER.equals(commandName))
            return new FilterCommand(new Binarize());

        return null;
    }

    public static Command getCommand(MasksEnum mask) throws CommandConstructionException {
        try {
            return new FilterCommand(Constants.getMaskFilter(mask));
        } catch (NoSuchElementException e) {
            throw new CommandConstructionException(e, null);
        }
    }

    public static Command getCommand(MasksEnum[] masks) throws CommandConstructionException {
        try {
            return new FilterCommand(Constants.getMaskFilter(masks));
        } catch (NoSuchElementException e) {
            throw new CommandConstructionException(e, null);
        }
    }

    public static String getCommandName(MasksEnum mask) {
        return MASK_FILTER_PREFIX.concat(mask.name());
    }

    public static MyFrameCommand buildCommand(String commandName, MyFrame frame) throws CommandConstructionException {

        Command command  = CommandFactory.getCommand(commandName);
        if (!(command instanceof MyFrameCommand))
            throw new CommandConstructionException(command);

        MyFrameCommand mfc = (MyFrameCommand)command;
        mfc.setFrame(frame);
        return mfc;
    }

    public static MyFrameCommand buildCommand(MasksEnum mask, MyFrame frame) throws CommandConstructionException {

        Command command = CommandFactory.getCommand(mask);
        if (!(command instanceof MyFrameCommand))
            throw new CommandConstructionException(command);

        MyFrameCommand mfc = (MyFrameCommand)command;
        mfc.setFrame(frame);
        return mfc;
    }

     public static MyFrameCommand buildCommand(MasksEnum[] masks, MyFrame frame) throws CommandConstructionException {

        Command command = CommandFactory.getCommand(masks);
        if (!(command instanceof MyFrameCommand))
            throw new CommandConstructionException(command);

        MyFrameCommand mfc = (MyFrameCommand)command;
        mfc.setFrame(frame);
        return mfc;
    }
}
