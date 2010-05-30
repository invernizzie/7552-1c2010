package view.components;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import control.MasksEnum;
import control.MyMenuHandler;
import control.command.CommandFactory;
import control.command.exceptions.CommandConstructionException;
import model.edgedetection.Stroke;

import javax.swing.*;

@SuppressWarnings("serial")
public class MyFrame extends Frame{
	
    private String ruta;
	private Image imageOrig;
	private Image image;
	private CommandMenuItem
            reset,
            ajustar_tamanio,
            secuenciaFiltros,
            buscarContorno,
            fourier;
    private List<MenuItem> processingMIs = new ArrayList<MenuItem>();
	private int ancho, alto;
	private final static int alturaMenu = 50;
	private final static int anchoIzquierdo = 7;
    private List<Stroke> strokes = null;
    private Stroke profile = null;
	
	public MyFrame(String title) {
		super(title);
		ancho=0;
		alto=0;
		imageOrig=null;
		image=null;
		MyMenuHandler handler = new MyMenuHandler(this);
		MenuBar mbar = new MenuBar();
        Menu herramientas = null;
        CommandMenuItem open = null, save = null, saveAs = null, exit = null;
        
        try {

            //Se crean los elementos del menu
            Menu archivo = new Menu("Archivo");
            open = new CommandMenuItem();
            open.setLabel("Abrir...");
            open.setCommand(CommandFactory.buildCommand(CommandFactory.FILE_OPEN, this));
            save = new CommandMenuItem();
            save.setLabel("Guardar");
            save.setCommand(CommandFactory.buildCommand(CommandFactory.FILE_SAVE, this));
            saveAs = new CommandMenuItem();
            saveAs.setLabel("Guardar Como...");
            saveAs.setCommand(CommandFactory.buildCommand(CommandFactory.FILE_SAVE_AS, this));
            exit = new CommandMenuItem();
            exit.setLabel("Salir");
            exit.setCommand(CommandFactory.buildCommand(CommandFactory.EXIT, this));
            archivo.add(open);
            archivo.add(save);
            archivo.add(saveAs);
            archivo.add(new MenuItem("-"));
            archivo.add(exit);
            mbar.add(archivo);

            ajustar_tamanio = new CommandMenuItem();
            ajustar_tamanio.setLabel("Ajustar Tamaño...");
            ajustar_tamanio.setCommand(CommandFactory.buildCommand(CommandFactory.RESIZE, this));
            ajustar_tamanio.setEnabled(false);
            reset = new CommandMenuItem();
            reset.setLabel("Resetear");
            reset.setCommand(CommandFactory.buildCommand(CommandFactory.RESET, this));
            reset.setEnabled(false);
            
            herramientas = new Menu("Herramientas");
            herramientas.add(ajustar_tamanio);
            herramientas.add(reset);

            /* TODO Usar esto para agregar Prewitt como unica entrada a la lista de filtros
            prewitt.setCommand(CommandFactory.buildCommand(
                new MasksEnum[] {MasksEnum.PREWITT_1, MasksEnum.PREWITT_2}, this));
             */

            secuenciaFiltros = new CommandMenuItem();
            secuenciaFiltros.setLabel("Secuencia de Filtros");
            secuenciaFiltros.setCommand(CommandFactory.buildCommand(CommandFactory.FILTER_SELECTOR, this));
            secuenciaFiltros.setEnabled(false);
            herramientas.add(secuenciaFiltros);
            
            buscarContorno = new CommandMenuItem();
            buscarContorno.setLabel("Detectar contorno");
            buscarContorno.setCommand(CommandFactory.buildCommand(CommandFactory.DETECT_EDGE, this));
            buscarContorno.setEnabled(false);
            herramientas.add(buscarContorno);
            
            fourier = new CommandMenuItem();
            fourier.setLabel("Aplicar DFT");
            fourier.setCommand(CommandFactory.buildCommand(CommandFactory.DFT, this));
            fourier.setEnabled(false);
            herramientas.add(fourier);            

        } catch (CommandConstructionException e) {
            String command = (e.getCommand() == null) ? "" : e.getCommand().toString();
            String cause = e.getCause() == null ? "" : ("Causa: " + e.getCause().toString());
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
        secuenciaFiltros.addActionListener(handler);
        buscarContorno.addActionListener(handler);
        fourier.addActionListener(handler);

        processingMIs.add(reset);
        processingMIs.add(ajustar_tamanio);
        processingMIs.add(secuenciaFiltros);
        processingMIs.add(buscarContorno);
        processingMIs.add(fourier);
		
		// Se crea un objeto para gestionar los eventos de la ventana
		MyWindowAdapter adapter = new MyWindowAdapter();
		// Registrar para recibir eventos
		addWindowListener(adapter);	
		
	}

    public void enableProcessing() {
        for (MenuItem mi: processingMIs)
            mi.setEnabled(true);
    }

    public void disableProcessing() {
        for (MenuItem mi: processingMIs)
            mi.setEnabled(false);
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

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public List<Stroke> getStrokes() {
        return strokes;
    }

    public void setStrokes(List<Stroke> strokes) {
        this.strokes = strokes;
    }

    public Stroke getProfile() {
        return profile;
    }

    public void setProfile(Stroke profile) {
        this.profile = profile;
    }

    public void paint(Graphics graphics){
		
		if(image!=null){
			/*BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = bi.createGraphics();
			if(ancho==0 || alto==0)
				g2d.drawImage(image, 0, 0, null);
			else
				g2d.drawImage(image, 0, 0, ancho, alto, null);
			g2d.dispose();*/
			//Lo comentado es para hacer doble buffering, pero como no funciona bien
			//o lo estoy aplicando incorrectamente, lo dejo sin este feature
			//Para aplicar doble buffering tambien hay que cambiar en el if de abajo "image por bi"

            graphics.translate(anchoIzquierdo, alturaMenu);
			if(ancho==0 || alto==0)
				graphics.drawImage(image, 0, 0, null);
			else
				graphics.drawImage(image, 0, 0, ancho, alto, null);

            graphics.translate(2, 2);
            if (strokes != null)
                for(Stroke stroke: strokes)
                    stroke.paint(graphics, nextColor());
            if (profile != null)
                profile.paint(graphics, Color.RED);
		}
	}

    private static int colorIndex = 0;

    private Color nextColor() {
        colorIndex++;
        if (colorIndex > 5)
            colorIndex = 0;
        switch (colorIndex) {
            case 0: return Color.YELLOW;
            case 1: return Color.BLUE;
            case 2: return Color.CYAN;
            case 3: return Color.GREEN;
            case 4: return Color.MAGENTA;
            case 5: return Color.ORANGE;
        }
        return Color.RED;
    }
		
	class MyWindowAdapter extends WindowAdapter {			
		public void windowClosing(WindowEvent we){
			MyFrame.this.dispose();
			System.exit(0);
		}			
	}
		
}
