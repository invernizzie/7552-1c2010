package control;

import java.awt.Dialog;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.components.MyFrame;
import control.command.CommandFactory;
import control.command.MyFrameCommand;
import control.command.exceptions.CommandConstructionException;
import control.command.exceptions.CommandException;
import control.command.exceptions.CommandExecutionException;

public class FilterSelectorHandler implements ActionListener {
	
	private List listaDisponibles;
	private List listaSeleccionados;
	private Dialog d;
	private MyFrame frame;
	
	public FilterSelectorHandler(List listaDisponibles, List listaSeleccionados, Dialog d, MyFrame frame){
		this.listaDisponibles = listaDisponibles;
		this.listaSeleccionados = listaSeleccionados;
		this.d = d;
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent ae) {
		String arg = (String)ae.getActionCommand();
		if(arg.equals("agregarFiltro")){
			String[] selectedItems = listaDisponibles.getSelectedItems();
			for (int i = 0; i < selectedItems.length; i++) {
				String item = selectedItems[i];
				listaDisponibles.remove(item);
				listaSeleccionados.add(item);
			}
		}
		else if(arg.equals("quitarFiltro")){
			String[] selectedItems = listaSeleccionados.getSelectedItems();
			for (int i = 0; i < selectedItems.length; i++) {
				String item = selectedItems[i];
				listaSeleccionados.remove(item);
				listaDisponibles.add(item);
			}		
		}
		else if(arg.equals("aplicarFiltros")){
			try {
				String[] filterNames = listaSeleccionados.getItems();
				MyFrameCommand buildCommand = CommandFactory.buildCommand(filterNames, frame);
				
				buildCommand.execute();
				
			} catch (CommandConstructionException e) {
	            closeAll(e);
			} catch (CommandExecutionException e) {
	            closeAll(e);
			}
			
			d.dispose();
		}
		else if(arg.equals("cancelar")){
			d.dispose();
		}
	}

	private void closeAll(CommandException e) {
		String command = (e.getCommand() == null) ? "" : e.getCommand().toString();
		String cause = e.getCause() == null ? "" : ("Causa: " + e.getCause().toString());
		JOptionPane.showMessageDialog(
		        d, command + "\n" + cause,
		        "Error al construir Command",
		        JOptionPane.ERROR_MESSAGE);
		d.dispose();
		frame.dispose();
		System.exit(0);
	}

}
