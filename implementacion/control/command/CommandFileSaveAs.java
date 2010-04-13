package control.command;

import control.command.exceptions.CommandExecutionException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Esteban I. Invernizzi
 * @created 13/04/2010
 */
public class CommandFileSaveAs extends MyFrameCommand {

    public void execute() throws CommandExecutionException {
        FileDialog fd = new FileDialog(frame,"Guardar Como",FileDialog.SAVE);
			fd.setFile("*.jpg");
			fd.setLocation(new Point(350,120));
			fd.setVisible(true);
			try{
				if (fd.getFile()!=null && fd.getDirectory()!=null){
					BufferedImage bi = new BufferedImage(
                            frame.getImage().getWidth(null),
                            frame.getImage().getHeight(null),
                            BufferedImage.TYPE_INT_RGB);
					Graphics2D g2d = bi.createGraphics();
					g2d.drawImage(frame.getImage(),0,0,null);
					g2d.dispose();
					ImageIO.write(bi, "jpg", new File(fd.getDirectory()+fd.getFile()));
				}
			}catch(IOException e){
				System.out.println("Error al escribir el archivo");
			}
    }
}
