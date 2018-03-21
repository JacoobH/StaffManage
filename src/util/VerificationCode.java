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
		// ���ñ���ɫ  
        g.setColor(Color.WHITE);  
        g.fillRect(0, 0, width, height);  
  
        // ��������  
        g.setFont(new Font("����", Font.BOLD, 20));  
  
        // �������  
        Random r = new Random(new Date().getTime());  
        for (int i = 0; i < 4; i++) {  
            int a = r.nextInt(10);  
            Login.code+=a;
            int y = 10 + r.nextInt(20);// 10~30��Χ�ڵ�һ����������Ϊy����  
  
            Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));  
            g.setColor(c);  
  
            g.drawString("" + a, 5 + i * width / 4, y);  
        }  
  
        // ������  
        for (int i = 0; i < lines; i++) {  
            Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));  
            g.setColor(c);  
            g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height));  
        }  
	}
}
