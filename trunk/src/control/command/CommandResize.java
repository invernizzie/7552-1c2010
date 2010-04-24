package control.command;

import control.MyDialogHandler;
import control.command.exceptions.CommandExecutionException;

import java.awt.*;

/**
 * @author Esteban I. Invernizzi
 * @created 13/04/2010
 */
public class CommandResize extends MyFrameCommand {

    public void execute() throws CommandExecutionException {
        Dialog d = new Dialog(frame, "Ajustar Tamaño", true);
        d.setSize(new Dimension(250,100));
        d.setLocation(new Point(500,300));
        d.setLayout(new FlowLayout());
        TextField ancho, alto;
        ancho = new TextField(3);
        alto = new TextField(3);
        d.add(new Label("Ancho: ",Label.RIGHT));
        d.add(ancho);
        d.add(new Label("Alto: ",Label.RIGHT));
        d.add(alto);
        Button accept,cancel;
        d.add(accept = new Button("Aceptar"));
        d.add(cancel = new Button("Cancelar"));
        MyDialogHandler dh = new MyDialogHandler(d, frame);
        accept.addActionListener(dh);
        cancel.addActionListener(dh);
        d.setVisible(true);
    }
}
