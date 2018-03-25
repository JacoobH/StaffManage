package view;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import util.DBConn;
import util.ImagePanel;

import javax.swing.JScrollPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class AdminCard extends ImagePanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private String[] title= {"登录名","密码"};
	private DefaultTableModel model;
	private DefaultTableCellRenderer r;
	
	private JScrollPane tableScrollPane;
	private JPanel addPanel;
	
	private DBConn db;
	private ResultSet rs;
	private JLabel label;
	private JTextField aUsername;
	private JPasswordField aPassword;
	private JPasswordField aRePassword;
	private JButton addButton;
	private JPanel operationPanel;
	private JLabel label_3;
	private JTextField oUsername;
	private JLabel label_4;
	private JPasswordField oPassword;
	private JLabel label_5;
	private JPasswordField oRePassword;
	private JButton updateButton;
	private JButton deleteButton;
	/**
	 * Create the panel.
	 */
	public AdminCard() {
		super("adminImage.jpg");
		setSize(1024,768);
		setLayout(null);
		
		addPanelInit();
		operationPanelInit();
		tableInit();
	}
	
	public void addPanelInit(){
		//面板设置
		addPanel = new JPanel();
		addPanel.setBackground(Color.WHITE);
		addPanel.setBorder(new TitledBorder(null, "\u589E\u52A0\u65B0\u7528\u6237", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		addPanel.setBounds(550, 73, 417, 178);
		add(addPanel);
		addPanel.setLayout(null);
		
		label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setBounds(30, 35, 64, 18);
		addPanel.add(label);
		
		aUsername = new JTextField();
		aUsername.setBounds(145, 31, 115, 24);
		addPanel.add(aUsername);
		aUsername.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setBounds(30, 82, 64, 18);
		addPanel.add(label_1);
		
		aPassword = new JPasswordField();
		aPassword.setBounds(145, 77, 115, 24);
		addPanel.add(aPassword);
		
		JLabel label_2 = new JLabel("\u518D\u6B21\u8F93\u5165\u5BC6\u7801\uFF1A");
		label_2.setBounds(33, 125, 108, 18);
		addPanel.add(label_2);
		
		aRePassword = new JPasswordField();
		aRePassword.setBounds(145, 120, 115, 24);
		addPanel.add(aRePassword);
		
		addButton = new JButton("\u5F55\u5165");
		//录入（按钮）监听
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String suser = aUsername.getText().toString().trim();
				String sps = new String(aPassword.getPassword()).trim();
				String srps = new String(aRePassword.getPassword()).trim();
				
				try {
					db=new DBConn();
					rs = db.doRs("SELECT user FROM admin WHERE user = '"+suser+"'");
					if(!suser.equals("") && !rs.next() && !sps.equals("") && sps.equals(srps) && suser.length()<15 && sps.length()<15) {
						db=new DBConn();
						db.dosth("INSERT INTO admin VALUES ('"+suser+"','"+sps+"')");
						reData();
					}
					else if(suser.length() >= 15 || sps.length() >= 15) {
						JOptionPane.showMessageDialog(null, "用户名/密码最多14位!", "消息", JOptionPane.OK_CANCEL_OPTION);
					}
					else
						JOptionPane.showMessageDialog(null, "录入失败!", "消息", JOptionPane.OK_CANCEL_OPTION);
				}catch(SQLException ex) {
					ex.printStackTrace();
				}finally {
					try {
						if(db!=null)
							db.closed();
						if(rs!=null)
							rs.close();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		addButton.setBounds(291, 116, 88, 27);
		addPanel.add(addButton);
	}
	
	public void operationPanelInit() {
		
		//面板设置
		operationPanel = new JPanel();
		operationPanel.setBackground(Color.WHITE);
		operationPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u64CD\u4F5C\u7528\u6237", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		operationPanel.setBounds(550, 295, 417, 319);
		add(operationPanel);
		operationPanel.setLayout(null);
		
		label_3 = new JLabel("\u7528\u6237\u540D\uFF1A");
		label_3.setBounds(30, 35, 72, 18);
		operationPanel.add(label_3);
		
		oUsername = new JTextField();
		oUsername.setEditable(false);
		oUsername.setColumns(10);
		oUsername.setBounds(140, 31, 115, 24);
		operationPanel.add(oUsername);
		
		label_4 = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
		label_4.setBounds(31, 97, 64, 18);
		operationPanel.add(label_4);
		
		oPassword = new JPasswordField();
		oPassword.setBounds(140, 94, 115, 24);
		operationPanel.add(oPassword);
		
		label_5 = new JLabel("\u518D\u6B21\u8F93\u5165\u5BC6\u7801\uFF1A");
		label_5.setBounds(32, 163, 108, 18);
		operationPanel.add(label_5);
		
		oRePassword = new JPasswordField();
		oRePassword.setBounds(140, 158, 115, 24);
		operationPanel.add(oRePassword);
		
		updateButton = new JButton("\u4FEE\u6539");
		//修改（按钮）监听
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String suser = oUsername.getText().toString().trim();
				String sps = new String(oPassword.getPassword()).trim();
				String srps = new String(oRePassword.getPassword()).trim();
				
				if(!suser.equals("") && !sps.equals("") && sps.equals(srps) && sps.length() < 15) {
					db = new DBConn();
					db.dosth("UPDATE admin SET password = '"+sps+"' WHERE user = '"+suser+"'");
					reData();
				}
				else if(sps.length() >= 15) {
					JOptionPane.showMessageDialog(null, "密码最多14位!", "消息", JOptionPane.OK_CANCEL_OPTION);
				}
				else
					JOptionPane.showMessageDialog(null, "修改失败!", "消息", JOptionPane.OK_CANCEL_OPTION);
			}
		});
		updateButton.setBounds(76, 247, 113, 27);
		operationPanel.add(updateButton);
		
		deleteButton = new JButton("\u5220\u9664");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String suser = oUsername.getText().toString().trim();
				
				if(!suser.equals("") && !suser.equals(Login.currentUser)){
					db = new DBConn();
					db.dosth("DELETE FROM admin WHERE user = '"+suser+"'");
					model.setRowCount(0);
					model.setRowCount(ManageMain.ROW_NUM);
					reData();
					oUsername.setText(null);
				}
				else if(suser.equals(Login.currentUser)) {
					JOptionPane.showMessageDialog(null, "不能删除当前账户!", "消息", JOptionPane.OK_CANCEL_OPTION);
				}
				else
					JOptionPane.showMessageDialog(null, "删除失败!", "消息", JOptionPane.OK_CANCEL_OPTION);
			}
		});
		deleteButton.setBounds(234, 246, 113, 27);
		operationPanel.add(deleteButton);
	}
	
	public void tableInit() {
		//初始化表格
		model= new DefaultTableModel(new String[ManageMain.ROW_NUM][2],title);
		table= new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//判断是否有数据
				if(table.getValueAt(table.getSelectedRow(), 0) != null ) {
					//将选中行的id值赋给 pID
					oUsername.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				}
				else
					oUsername.setText("");
			}
		});
		table.setModel(model);
		table.setRowHeight(30);
		//设置JTable内容居中
		r = new   DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		table.setDefaultRenderer(Object.class, r);
		//初始化滚动条面板并将表格放入其中
		tableScrollPane = new JScrollPane(table);
		tableScrollPane.setBounds(106, 73, 319, 541);
		add(tableScrollPane);
		//得到数据库数据
		reData();
	}
	
	public void reData() {
		try {
			int i = 0;
			db=new DBConn();
			rs = db.doRs("SELECT * FROM admin");
			while(rs.next()) {
				table.setValueAt(rs.getString("user"), i, 0);
				table.setValueAt(rs.getString("password"), i, 1);
				if(i++ >=19) {
					model.setRowCount( i+1 );
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(db!=null)
					db.closed();
				if(rs!=null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
