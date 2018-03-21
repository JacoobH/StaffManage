package util;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
public class mid {
	public mid(JFrame frame){
		int windowWidth=frame.getWidth();
		int windowHeight=frame.getHeight();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		int screenWidth=screenSize.width;
		int screeHeitch=screenSize.height;
		frame.setLocation(screenWidth/2-windowWidth/2,screeHeitch/2-windowHeight/2);
	}
}
