package control.command;

import control.MyMenuHandler;
import control.command.exceptions.CommandConstructionException;
import control.command.exceptions.CommandExecutionException;
import view.components.MyFrame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Esteban I. Invernizzi
 * @created 13/04/2010
 */
public class CommandFileOpen extends MyFrameCommand {


    public void execute() throws CommandExecutionException {
        testExecute();

        FileDialog fd = new FileDialog(frame,"Abrir",FileDialog.LOAD);
			fd.setFile("*.jpg");
			fd.setLocation(new Point(350,120));
			fd.setVisible(true);
			if (fd.getFile()!=null && fd.getDirectory()!=null){
				BufferedImage img = null;
				try {
				    img = ImageIO.read(new File(fd.getDirectory()+fd.getFile()));
				    menuHandler.setRuta(fd.getDirectory() + fd.getFile());
				    frame.setImage(img);
				    frame.setImageOrig(img);
				    frame.getGrayscale().setEnabled(true);
				    frame.getResetButton().setEnabled(true);
				    frame.getAjustarTamanio().setEnabled(true);
				    menuHandler.setMenuFiltros(true);
				    frame.repaint();
				} catch (IOException e) {
					System.out.println("Error al abrir el archivo");
				}
            }
    }

}
