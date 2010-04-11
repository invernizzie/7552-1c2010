package control.command;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 * @created 11/04/2010
 */
public class CommandExecutionException extends Throwable {

    private Exception cause;

    public CommandExecutionException() {}

    public CommandExecutionException(Exception cause) {
        this.cause = cause;
    }

    public Exception getCause() {
        return cause;
    }
}
