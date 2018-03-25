package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import util.DBConn;
import util.Department;
import util.mid;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class AddMain extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField pName;
	private JTextField pWages;
	private final JComboBox<Department> cBoxDpmt = new JComboBox<>();
	private DBConn db;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddMain frame = new AddMain(table);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	/**
	 * Create the frame.
	 */
	public AddMain(JTable table) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(300, 270);
		new mid(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lName = new JLabel("\u59D3\u540D\uFF1A");
		lName.setBounds(38, 28, 45, 18);
		
		pName = new JTextField();
		pName.setBounds(38, 59, 95, 24);
		pName.setColumns(10);
		
		JLabel lWages = new JLabel("\u57FA\u672C\u5DE5\u8D44\uFF1A");
		lWages.setBounds(161, 28, 75, 18);
		
		pWages = new JTextField();
		pWages.setBounds(161, 59, 86, 24);
		pWages.setColumns(10);
		
		JComboBox<String> cBoxGender = new JComboBox<>();
		cBoxGender.setBounds(38, 126, 44, 24);
		cBoxGender.setModel(new DefaultComboBoxModel<>(new String[] {"\u7537", "\u5973"}));
		cBoxDpmt.setBounds(162, 126, 74, 24);
		cBoxDpmt.setModel(new DefaultComboBoxModel<>(Department.values()));
		
		JButton bAdd = new JButton("\u5F55\u5165");
		bAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = pName.getText().toString().trim();
				String wages = pWages.getText().toString().trim();
				String gender=(String) cBoxGender.getSelectedItem();
				Department department=(Department) cBoxDpmt.getSelectedItem();
				if(department != Department.全部 && isNumeric(wages) && name.length()<15) {
					db = new DBConn();
					db.dosth("INSERT INTO stafflist(name,gender,department,wages) VALUES ('"+name+"','"+gender+"','"+department+"',"+wages+")");
					db = new DBConn();
					db.getRs(table,"SELECT * FROM staffList");
				}
				else if(name.length()>=15) {
					JOptionPane.showMessageDialog(null, "姓名最多14位!", "消息", JOptionPane.OK_CANCEL_OPTION);
				}
				else if(!isNumeric(wages)) {
					JOptionPane.showMessageDialog(null, "工资只能为数字!", "消息", JOptionPane.OK_CANCEL_OPTION);
				}
				else {
					JOptionPane.showMessageDialog(null, "请选择部门!", "消息", JOptionPane.OK_CANCEL_OPTION);
				}
			}
		});
		bAdd.setBounds(107, 183, 63, 27);
		
		JLabel lDpmt = new JLabel("\u90E8\u95E8\uFF1A");
		lDpmt.setBounds(161, 95, 45, 18);
		contentPane.setLayout(null);
		contentPane.add(lName);
		contentPane.add(pName);
		contentPane.add(lWages);
		contentPane.add(pWages);
		contentPane.add(cBoxGender);
		contentPane.add(cBoxDpmt);
		contentPane.add(bAdd);
		contentPane.add(lDpmt);
		
		JLabel lGender = new JLabel("\u6027\u522B\uFF1A");
		lGender.setBounds(36, 96, 47, 18);
		contentPane.add(lGender);
	}
	public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
	}
}
