package control.command;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 * @created 11/04/2010
 */
public interface Command {

    public void execute() throws CommandExecutionException;

}
