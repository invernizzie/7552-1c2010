package control;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import control.command.CommandFactory;
import control.command.exceptions.CommandExecutionException;
import model.filters.impl.Contrast;
import model.filters.impl.Grayscale;
import model.filters.impl.Invert;
import control.Constants;
import control.MasksEnum;
import view.components.CommandComponent;
import view.components.MyFrame;
import control.MyDialogHandler;
import model.filters.*;

public class MyMenuHandler implements ActionListener, ItemListener{

	private MyFrame myframe;
	
	public MyMenuHandler(MyFrame f){
		myframe=f;
	}		
	
	//Gestion de eventos de accion (botones comunes)
	public void actionPerformed(ActionEvent ae){

        Object source = ae.getSource();
        if (!(source instanceof CommandComponent))
            return;

        CommandComponent component = (CommandComponent) source;
        try {
            component.getCommand().execute();
        } catch (CommandExecutionException e) {
            String command = (e.getCommand() == null) ? "" : e.getCommand().toString();
            String cause = e.getCause() == null ? "" : ("Causa: " + e.getCause().toString());
            JOptionPane.showMessageDialog(
                    myframe, command + "\n" + cause,
                    "Error al ejecutar Command",
                    JOptionPane.ERROR_MESSAGE);
        }
	}
	
	//Gestion de eventos de botones "checkbox"
	public void itemStateChanged(ItemEvent ie){

        CheckboxMenuItem checkbox = (CheckboxMenuItem)ie.getItemSelectable();
        checkbox.setState(false);

        Object source = ie.getSource();
        if (!(source instanceof CommandComponent))
            return;

        CommandComponent component = (CommandComponent) source;
        try {
            component.getCommand().execute();
        } catch (CommandExecutionException e) {
            String command = (e.getCommand() == null) ? "" : e.getCommand().toString();
            String cause = e.getCause() == null ? "" : ("Causa: " + e.getCause().toString());
            JOptionPane.showMessageDialog(
                    myframe, command + "\n" + cause,
                    "Error al ejecutar Command",
                    JOptionPane.ERROR_MESSAGE);
        }

        /*
		CheckboxMenuItem aux = (CheckboxMenuItem)ie.getItemSelectable();
		String command = (String)aux.getActionCommand();
		Filter filter=null;
		boolean reset_filtros=false;
		if(command.equals(CommandFactory.GRAYSCALE_FILTER)){
			if(aux.getState())
				filter = new Grayscale();
			reset_filtros=true;
		}else if(command.equals(CommandFactory.INVERSION_FILTER)){
			filter = new Invert();
		}else if(command.equals(CommandFactory.CONTRAST_FILTER)){
			if(aux.getState())	
				filter = new Contrast();
			reset_filtros=true;
		}else if(command.equals(CommandFactory.getCommandName(MasksEnum.BLUR))){
			if(aux.getState())	
				filter = Constants.getMaskFilter(MasksEnum.BLUR);
			reset_filtros=true;
		}else if(command.equals(CommandFactory.getCommandName(MasksEnum.SHARPEN))){
			if(aux.getState())
                filter = Constants.getMaskFilter(MasksEnum.SHARPEN);
			reset_filtros=true;
		}
		Image img=null;
		if(aux.getState()){
			img = filter.filter(myframe.getImage());
		}else{
			if(reset_filtros){
				img = myframe.getImageOrig();
				setMenuFiltros(true);
				myframe.getGrayscale().setState(false);
			}else
				img = filter.filter(myframe.getImage());
		}
		myframe.setImage(img);
		myframe.repaint();*/
	}
}
