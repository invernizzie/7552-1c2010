package control.command;

import java.awt.image.BufferedImage;

import model.fouriertransform.ImageFFT;
import model.fouriertransform.exceptions.FFTException;
import control.command.exceptions.CommandExecutionException;


public class CommandDFT extends MyFrameCommand {

    protected void doExecute() throws CommandExecutionException{
    	BufferedImage bi = new BufferedImage(
                frame.getImage().getWidth(null),
                frame.getImage().getHeight(null),
                BufferedImage.TYPE_BYTE_GRAY);
    	try{
    		ImageFFT fft = new ImageFFT(bi,ImageFFT.HANNING_WINDOW);
    		fft.transform();
    		frame.setImage(fft.getSpectrum());
	        frame.repaint();
	        
    	}catch (FFTException e) {
    		e.printStackTrace();
    	}
    }

    protected void testExecute() throws CommandExecutionException {
        super.testExecute();
        if (getFrame().getImage() == null)
            throw new CommandExecutionException(this);
    }
}
