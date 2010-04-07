package view.components;

import java.awt.*;
import java.awt.event.*;

import control.MyMenuHandler;

// Subclase de Frame
@SuppressWarnings("serial")
public class MyFrame extends Frame{
	
	private CheckboxMenuItem grayScale, invertir, contraste, sharpen, blur;
	private Image imageOrig;
	private Image image;
	private Menu filtros;
	private MenuItem reset, ajustar_tamanio;
	private int ancho, alto;
	private final static int anchoMenu = 50;
	private final static int anchoIzquierdo = 7;
	
	public MyFrame(String title) {
		super(title);
		ancho=0;
		alto=0;
		imageOrig=null;
		image=null;	
		// Se crea una barra de men�s y se a�ade al frame
		MenuBar mbar = new MenuBar();
		setMenuBar(mbar);
		
		//Se crean los elementos del men�
		Menu archivo = new Menu("Archivo");
		MenuItem item1, item2, item3, item4, item5;
		archivo.add(item1 = new MenuItem("Abrir..."));
		archivo.add(item2 = new MenuItem("Guardar"));
		archivo.add(item3 = new MenuItem("Guardar Como..."));
		archivo.add(item4 = new MenuItem("-"));
		archivo.add(item5 = new MenuItem("Salir"));
		mbar.add(archivo);
		
		Menu herramientas = new Menu("Herramientas");
		herramientas.add(ajustar_tamanio = new MenuItem("Ajustar Tama�o..."));
		ajustar_tamanio.setEnabled(false);
		herramientas.add(reset = new MenuItem("Resetear"));
		reset.setEnabled(false);
		grayScale = new CheckboxMenuItem("Escala de Grises");
		herramientas.add(grayScale);
		grayScale.setEnabled(false);
		
		filtros = new Menu("Filtros");
		filtros.add(invertir = new CheckboxMenuItem("Invertir"));
		filtros.add(contraste = new CheckboxMenuItem("Contraste"));
		filtros.add(blur = new CheckboxMenuItem("Blur"));
		filtros.add(sharpen = new CheckboxMenuItem("Sharpen"));
		herramientas.add(filtros);
		filtros.setEnabled(false);
		
		mbar.add(herramientas);
		
		//Se crea un objeto para gestionar los eventos del men�
		MyMenuHandler handler = new MyMenuHandler(this);
		//Se registra para recibir esos eventos
		item1.addActionListener(handler);
		item2.addActionListener(handler);
		item3.addActionListener(handler);
		item4.addActionListener(handler);
		item5.addActionListener(handler);
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
