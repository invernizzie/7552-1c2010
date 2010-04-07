package control;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import view.components.MyFrame;
import control.MyDialogHandler;
import model.filters.*;

public class MyMenuHandler implements ActionListener, ItemListener{

	private MyFrame myframe;
	private String ruta;
	
	public MyMenuHandler(MyFrame f){
		myframe=f;
	}		
	
	//Gestion de eventos de accion (botones comunes)
	public void actionPerformed(ActionEvent ae){
        // TODO Implementar patron command
		String arg = (String)ae.getActionCommand();
		if(arg.equals("Abrir...")){
			FileDialog fd = new FileDialog(myframe,"Abrir",FileDialog.LOAD);
			fd.setFile("*.jpg");
			fd.setLocation(new Point(350,120));
			fd.setVisible(true);
			if (fd.getFile()!=null && fd.getDirectory()!=null){
				BufferedImage img = null;
				try {
				    img = ImageIO.read(new File(fd.getDirectory()+fd.getFile()));
				    ruta = fd.getDirectory()+fd.getFile();
				    myframe.setImage(img);
				    myframe.setImageOrig(img);
				    myframe.getGrayscale().setEnabled(true);
				    myframe.getResetButton().setEnabled(true);
				    myframe.getAjustarTamanio().setEnabled(true);
				    setMenuFiltros(true);
				    myframe.repaint();
				} catch (IOException e) {
					System.out.println("Error al abrir el archivo");
				}
			}
		}else if(arg.equals("Guardar")){
			if(ruta != null){
				try{
					BufferedImage bi = new BufferedImage(myframe.getImage().getWidth(null), myframe.getImage().getHeight(null), BufferedImage.TYPE_INT_RGB);
					Graphics2D g2d = bi.createGraphics();
					g2d.drawImage(myframe.getImage(),0,0,null);
					g2d.dispose();
					ImageIO.write(bi, "jpg", new File(ruta));
				}catch(IOException e){
					System.out.println("Error al escribir el archivo");
				}
			}
		}else if(arg.equals("Guardar Como...")){
			FileDialog fd = new FileDialog(myframe,"Guardar Como",FileDialog.SAVE);
			fd.setFile("*.jpg");
			fd.setLocation(new Point(350,120));
			fd.setVisible(true);
			try{
				if (fd.getFile()!=null && fd.getDirectory()!=null){
					BufferedImage bi = new BufferedImage(myframe.getImage().getWidth(null), myframe.getImage().getHeight(null), BufferedImage.TYPE_INT_RGB);
					Graphics2D g2d = bi.createGraphics();
					g2d.drawImage(myframe.getImage(),0,0,null);
					g2d.dispose();
					ImageIO.write(bi, "jpg", new File(fd.getDirectory()+fd.getFile()));
				}
			}catch(IOException e){
				System.out.println("Error al escribir el archivo");
			}
		}else if(arg.equals("Resetear")){
			myframe.setImage(myframe.getImageOrig());
			setMenuFiltros(true);
			myframe.getGrayscale().setState(false);
			myframe.repaint();
		}else if(arg.equals("Ajustar Tama�o...")){
			Dialog d = new Dialog(myframe,"Ajustar Tama�o",true);
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
			MyDialogHandler dh = new MyDialogHandler(d,myframe);
			accept.addActionListener(dh);
			cancel.addActionListener(dh);
			d.setVisible(true);			
		}else if(arg.equals("Salir")){
			myframe.dispose();
			System.exit(0);
		}
	}
	
	private void setMenuFiltros(boolean habilitar){
		Menu filtros = myframe.getMenuFiltos();
		filtros.setEnabled(habilitar);
		for(int i=0;i<filtros.getItemCount();i++)
			((CheckboxMenuItem)filtros.getItem(i)).setState(!habilitar);
	}
	
	//Gesti�n de eventos de botones "checkbox"
	public void itemStateChanged(ItemEvent ie){
		CheckboxMenuItem aux = (CheckboxMenuItem)ie.getItemSelectable();
		String arg = (String)aux.getActionCommand();
		Filter filter=null;
		boolean reset_filtros=false;
		if(arg.equals("Escala de Grises")){
			if(aux.getState())
				filter = new Grayscale();
			reset_filtros=true;
		}else if(arg.equals("Invertir")){
			filter = new Invert();
		}else if(arg.equals("Contraste")){
			if(aux.getState())	
				filter = new Contrast();
			reset_filtros=true;
		}else if(arg.equals("Blur")){
			if(aux.getState())	
				filter = new Blur();
			reset_filtros=true;
		}else if(arg.equals("Sharpen")){
			if(aux.getState())	
				filter = new Sharpen();
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
		myframe.repaint();
	}
}
