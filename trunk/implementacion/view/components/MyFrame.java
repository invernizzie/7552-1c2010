package view.components;

import java.awt.*;
import java.awt.event.*;

import control.MasksEnum;
import control.MyMenuHandler;
import control.command.Command;
import control.command.CommandFactory;
import control.command.MyFrameCommand;
import control.command.exceptions.CommandConstructionException;

import javax.swing.*;

// Subclase de Frame
@SuppressWarnings("serial")
public class MyFrame extends Frame{
	
	private CommandCheckboxMenuItem grayScale, invertir, contraste, sharpen, blur;
	private Image imageOrig;
	private Image image;
	private Menu filtros;
	private CommandMenuItem reset, ajustar_tamanio;
	private int ancho, alto;
	private final static int anchoMenu = 50;
	private final static int anchoIzquierdo = 7;
	
	public MyFrame(String title) {
		super(title);
		ancho=0;
		alto=0;
		imageOrig=null;
		image=null;	
        //Se crea un objeto para gestionar los eventos del menu
		MyMenuHandler handler = new MyMenuHandler(this);
        // Se crea una barra de menus y se añade al frame
		MenuBar mbar = new MenuBar();
        Menu herramientas = null;
        CommandMenuItem open = null, save = null, saveAs = null, exit = null;
        
        try {

            // TODO Setear atributos de los commands
            // TODO Crear un metodo en CommandFactory que setee el Frame y el MenuHandler

            MyFrameCommand mfc;

            //Se crean los elementos del men�
            Menu archivo = new Menu("Archivo");
            open = new CommandMenuItem();
            open.setLabel("Abrir...");
            mfc = (MyFrameCommand)CommandFactory.getCommand(CommandFactory.FILE_OPEN);
            mfc.setFrame(this);
            mfc.setMenuHandler(handler);
            open.setCommand(mfc);
            save = new CommandMenuItem();
            save.setLabel("Guardar");
            mfc = (MyFrameCommand)CommandFactory.getCommand(CommandFactory.FILE_SAVE);
            mfc.setFrame(this);
            mfc.setMenuHandler(handler);
            save.setCommand(mfc);
            saveAs = new CommandMenuItem();
            saveAs.setLabel("Guardar Como...");
            mfc = (MyFrameCommand)CommandFactory.getCommand(CommandFactory.FILE_SAVE_AS);
            mfc.setFrame(this);
            mfc.setMenuHandler(handler);
            saveAs.setCommand(mfc);
            exit = new CommandMenuItem();
            exit.setLabel("Salir");
            mfc = (MyFrameCommand)CommandFactory.getCommand(CommandFactory.EXIT);
            mfc.setFrame(this);
            mfc.setMenuHandler(handler);
            exit.setCommand(mfc);
            archivo.add(open);
            archivo.add(save);
            archivo.add(saveAs);
            archivo.add(new MenuItem("-"));
            archivo.add(exit);
            mbar.add(archivo);

            ajustar_tamanio = new CommandMenuItem();
            ajustar_tamanio.setLabel("Ajustar Tamaño...");
            mfc = (MyFrameCommand)CommandFactory.getCommand(CommandFactory.RESIZE);
            mfc.setFrame(this);
            mfc.setMenuHandler(handler);
            ajustar_tamanio.setCommand(mfc);
            ajustar_tamanio.setEnabled(false);
            reset = new CommandMenuItem();
            reset.setLabel("Resetear");
            mfc = (MyFrameCommand)CommandFactory.getCommand(CommandFactory.RESET);
            mfc.setFrame(this);
            mfc.setMenuHandler(handler);
            reset.setCommand(mfc);
            reset.setEnabled(false);
            grayScale = new CommandCheckboxMenuItem();
            grayScale.setLabel("Escala de Grises");
            mfc = (MyFrameCommand)CommandFactory.getCommand(CommandFactory.GRAYSCALE_FILTER);
            mfc.setFrame(this);
            mfc.setMenuHandler(handler);
            grayScale.setCommand(mfc);
            grayScale.setEnabled(false);
            herramientas = new Menu("Herramientas");
            herramientas.add(ajustar_tamanio);
            herramientas.add(reset);
            herramientas.add(grayScale);

            invertir = new CommandCheckboxMenuItem();
            invertir.setLabel("Invertir");
            mfc = (MyFrameCommand)CommandFactory.getCommand(CommandFactory.INVERSION_FILTER);
            mfc.setFrame(this);
            mfc.setMenuHandler(handler);
            invertir.setCommand(mfc);
            contraste = new CommandCheckboxMenuItem();
            contraste.setLabel("Contraste");
            mfc = (MyFrameCommand)CommandFactory.getCommand(CommandFactory.CONTRAST_FILTER);
            mfc.setFrame(this);
            mfc.setMenuHandler(handler);
            contraste.setCommand(mfc);
            blur = new CommandCheckboxMenuItem();
            blur.setLabel("Blur");
            mfc = (MyFrameCommand)CommandFactory.getCommand(MasksEnum.BLUR);
            mfc.setFrame(this);
            mfc.setMenuHandler(handler);
            blur.setCommand(mfc);
            sharpen = new CommandCheckboxMenuItem();
            sharpen.setLabel("Sharpen");
            mfc = (MyFrameCommand)CommandFactory.getCommand(MasksEnum.SHARPEN);
            mfc.setFrame(this);
            mfc.setMenuHandler(handler);
            sharpen.setCommand(mfc);
            filtros = new Menu("Filtros");
            filtros.add(invertir);
            filtros.add(contraste);
            filtros.add(blur);
            filtros.add(sharpen);
            herramientas.add(filtros);
            filtros.setEnabled(false);
        } catch (CommandConstructionException e) {
            String command = (e.getCommand() == null) ? "" : e.getCommand().toString();
            String cause = e.getCause() == null ? "" : ("Causa" + e.getCause().toString());
            JOptionPane.showMessageDialog(
                    this, command + "\n" + cause,
                    "Error al construir Command",
                    JOptionPane.ERROR_MESSAGE);
            this.dispose();
            System.exit(0);
        }
		
		mbar.add(herramientas);
        setMenuBar(mbar);

		//Se registra MyMenuHandler para recibir los eventos
		open.addActionListener(handler);
		save.addActionListener(handler);
		saveAs.addActionListener(handler);
		exit.addActionListener(handler);
		reset.addActionListener(handler);
		ajustar_tamanio.addActionListener(handler);
		grayScale.addItemListener(handler);
		invertir.addItemListener(handler);
		contraste.addItemListener(handler);
		blur.addItemListener(handler);
		sharpen.addItemListener(handler);
		
		// Se crea un objeto para gestionar los eventos de la ventana
		MyWindowAdapter adapter = new MyWindowAdapter();
		// Registrar para recibir eventos
		addWindowListener(adapter);				
	}
	
	public int getAncho(){
		return ancho;
	}
	
	public int getAlto(){
		return alto;
	}
	
	public void setAncho(int ancho){
		this.ancho=ancho;
	}
	
	public void setAlto(int alto){
		this.alto=alto;
	}
	
	public Menu getMenuFiltos(){
		return filtros;
	}
	
	public MenuItem getResetButton(){
		return reset;
	}
	
	public MenuItem getAjustarTamanio(){
		return ajustar_tamanio;
	}
	
	public CheckboxMenuItem getGrayscale(){
		return grayScale;
	}
	
	public void setImageOrig(Image image){
		this.imageOrig=image;
	}
	
	public Image getImageOrig(){
		return imageOrig;
	}
	
	public void setImage(Image image){
		this.image=image;
	}
	
	public Image getImage(){
		return image;
	}
	
	public void paint(Graphics g){
		
		if(image!=null){
			/*BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = bi.createGraphics();
			if(ancho==0 || alto==0)
				g2d.drawImage(image, 0, 0, null);
			else
				g2d.drawImage(image, 0, 0, ancho, alto, null);
			g2d.dispose();*/
			//Lo comentado es para hacer doble buffering, pero como no funciona bi�n
			//o lo estoy aplicando incorrectamente, lo dejo sin este feature
			//Para aplicar doble buffering tambi�n hay que cambiar en el if de abajo "image por bi"
			if(ancho==0 || alto==0)
				g.drawImage(image, anchoIzquierdo, anchoMenu, null);
			else
				g.drawImage(image, anchoIzquierdo, anchoMenu, ancho, alto, null);						
		}		
		
	}

    /*public void update(Graphics g){
         paint(g);
     }*/
		
	class MyWindowAdapter extends WindowAdapter {			
		public void windowClosing(WindowEvent we){
			MyFrame.this.dispose();
			System.exit(0);
		}			
	}
		
}
