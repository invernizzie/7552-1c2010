package control.command;

import control.MasksEnum;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 * @created 11/04/2010
 */
public class CommandFactory {

    public static final String MASK_FILTER_PREFIX = "MASK_FILTER_";

    public Command getCommand(String commandName) throws CommandConstructionException {

        if (commandName.startsWith(MASK_FILTER_PREFIX))
            return getCommand(MasksEnum.valueOf(
                commandName.substring(MASK_FILTER_PREFIX.length())));
        else
            return null;
    }

    public Command getCommand(MasksEnum mask) throws CommandConstructionException {
        return new MaskFilterCommand(mask);
    }

}
