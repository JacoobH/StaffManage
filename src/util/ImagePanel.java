/*
 * 此类是一个图片JPanel
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * */
package util;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
public class ImagePanel extends JPanel{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private Image image;
	private String fileName="";
	public ImagePanel(String str){
		fileName=str;
		getImage();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image,0,0,this);
	}
	
	void getImage(){
		try {
			image=ImageIO.read(new File("rse/"+fileName));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
