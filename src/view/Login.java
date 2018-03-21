package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import util.ImagePanel;
import util.VerificationCode;
import util.mid;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class Login extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImagePanel contentPane;
	public static String code="";
	private JLabel title;
	private JLabel labelName;
	private JTextField name;
	private JLabel labelPw;
	private JLabel labelVCode;
	private VerificationCode vcodePanel;
	private JTextField vCode;
	private JPasswordField pw;
	private JButton byes;
	private JButton bcancel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 328);
		new mid(this);
		contentPane = new ImagePanel("LoginImage.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		title = new JLabel("\u5458\u5DE5\u7BA1\u7406");
		title.setFont(new Font("幼圆", Font.PLAIN, 25));
		title.setBounds(162, 13, 100, 59);
		contentPane.add(title);
		
		labelName = new JLabel("\u7528\u6237\u540D\uFF1A");
		labelName.setBounds(88, 85, 67, 26);
		contentPane.add(labelName);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(162, 85, 176, 24);
		contentPane.add(name);
		
		labelPw = new JLabel("\u5BC6\u7801\uFF1A");
		labelPw.setBounds(88, 124, 62, 25);
		contentPane.add(labelPw);
		
		labelVCode = new JLabel("\u8BF7\u8F93\u5165\u9A8C\u8BC1\u7801\uFF1A");
		labelVCode.setBounds(88, 174, 114, 18);
		contentPane.add(labelVCode);
		
		vcodePanel = new VerificationCode();
		vcodePanel.setBounds(258, 159, 80, 40);
		contentPane.add(vcodePanel);
		
		vCode = new JTextField();
		vCode.setColumns(10);
		vCode.setBounds(195, 170, 57, 26);
		contentPane.add(vCode);
		
		pw = new JPasswordField();
		pw.setBounds(162, 122, 176, 24);
		contentPane.add(pw);
		
		byes = new JButton("\u786E\u8BA4");
		byes.setBounds(88, 223, 113, 27);
		byes.addActionListener(this);
		contentPane.add(byes);
		
		bcancel = new JButton("\u53D6\u6D88");
		bcancel.setBounds(225, 223, 113, 27);
		bcancel.addActionListener(this);
		contentPane.add(bcancel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String sname=name.getText().toString().trim();
		String sps=new String(pw.getPassword()).trim();
		String scode=vCode.getText().toString().trim();
		if(e.getSource()==byes) {
			if(sname.equals("hy")&&sps.equals("123")&&scode.equals(code)) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ManageMain frame = new ManageMain();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				this.dispose();
			}
			else if(scode.equals(code))
				JOptionPane.showMessageDialog(this, "用户名或密码错误");
			else {
				JOptionPane.showMessageDialog(this, "验证码错误");	
			}
		}
	
		if(e.getSource()==bcancel) {
			System.exit(0);
		}
	}
}
