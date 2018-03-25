package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Date;
import java.util.Random;

import javax.swing.JPanel;

import view.Login;

public class VerificationCode extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		int width = 80;  
        int height = 40;  
        int lines = 10;
        Login.code="";
        
		// 设置背景色  
        g.setColor(Color.WHITE);  
        g.fillRect(0, 0, width, height);  
  
        // 设置字体  
        g.setFont(new Font("宋体", Font.BOLD, 20));  
  
        // 随机数字  
        Random r = new Random(new Date().getTime());  
        for (int i = 0; i < 4; i++) {  
            int a = r.nextInt(10);  
            Login.code+=a;
            int y = 10 + r.nextInt(20);// 10~30范围内的一个整数，作为y坐标  
  
            Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));  
            g.setColor(c);  
  
            g.drawString("" + a, 5 + i * width / 4, y);  
        }  
  
        // 干扰线  
        for (int i = 0; i < lines; i++) {  
            Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));  
            g.setColor(c);  
            g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height));  
        }  
	}
}
