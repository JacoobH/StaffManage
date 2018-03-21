package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import util.MyRender;
import util.DBConn;
import util.ImagePanel;
import util.MusicPlayer;
import util.mid;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import util.Department;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class ManageMain extends JFrame{

	private static final long serialVersionUID = 1L;
	//�������
	private JPanel contentPane;
	
	private JTable table;
	private CardLayout card = new CardLayout();
	private JPanel cardPanel;
	private JTextField selectField;
	//���ݿ����
	private DBConn db;
	//��������������
	private MusicPlayer mp = MusicPlayer.makePlayer();
	//��ǰ�������
	private int index = 0;
	
	private JTextField pID;
	private JTextField pName;
	private JTextField pWages;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the frame.
	 */
	public ManageMain() {
		
		//���ô�Frame����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024,768);
		//����Frame����
		new mid(this);
		
		//�����������
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//����menuInit()���ò˵���
		menuInit();
		
		//���ÿ�Ƭ����
		cardPanel = new JPanel();
		contentPane.add(cardPanel);
		cardPanel.setLayout(card);
		
		//mainPanel��Ϊ��һ�ſ�Ƭ�������棩
		ImagePanel mainPanel = new ImagePanel("MainImage.jpg");
		cardPanel.add(mainPanel, "card_main");
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		//����staffPanel()�������õڶ��ſ�Ƭ
		staffPanel();
		
		//attendancePanel��Ϊ�����ſ�Ƭ
		AttendancePanel attendancePanel = new AttendancePanel();
		cardPanel.add(attendancePanel, "card_AttendancePanel");
	}
	public void staffPanel() {
		
		//��ʼ��staffPanel����Ϊ�ڶ��ſ�Ƭ
		ImagePanel staffPanel = new ImagePanel("StaffImage.jpg");
		cardPanel.add(staffPanel, "card_staff");
		staffPanel.setLayout(null);
		//��ʼ��tablePanel�����ŵ�staffPanel��
		JPanel tablePanel = new JPanel();
		tablePanel.setBounds(33, 92, 930, 423);
		staffPanel.add(tablePanel);
		tablePanel.setLayout(new BorderLayout(0, 0));
		//����JTable��ģ��
		String[] title= {"#","����","�Ա�","����","����","����"};
		DefaultTableModel model=new DefaultTableModel(new String[20][6],title);
		table = new JTable(model);
		//table����
		table.addMouseListener(new MouseAdapter() {
			@Override
			//������table
			public void mousePressed(MouseEvent e) {
				//�ж��Ƿ�������
				if(table.getValueAt(table.getSelectedRow(), 0) != null ) {
					//��ѡ���е�idֵ���� pID
					pID.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				}
				else
					pID.setText("");
			}
		});
		//����JTable���ݾ���
		DefaultTableCellRenderer r = new   DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		table.setDefaultRenderer(Object.class,   r);
		//����JTable�и�
		table.setRowHeight(30);
		table.getColumnModel().getColumn(5).setCellEditor(new MyRender(table,model));//���ñ༭��
		table.getColumnModel().getColumn(5).setCellRenderer(new MyRender(table,model));//�õ������ͣ��õ�ָ���������࣬������Ⱦ��
		//�������ݿ�
		db=new DBConn();
		db.getRs(table,"SELECT * FROM staffList");
		JScrollPane scrollPane = new JScrollPane(table);
		//�������ֵ�table�ŵ�tablePanel��
		tablePanel.add(scrollPane);
		
		
		//��ť��������
		JButton bAdd = new JButton("\u65B0\u589E");
		//������������
		bAdd.addActionListener((e)->{
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						AddMain frame = new AddMain(table);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		});
		bAdd.setBounds(33, 13, 63, 27);
		//��bAdd�ŵ�staffPanel��ȥ
		staffPanel.add(bAdd);
		
		
		//��ť��ɾ���������ݣ�
		JButton bAllDelete = new JButton("\u5220\u9664\u6240\u6709\u6570\u636E");
		bAllDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "ȷ��ɾ������������", "��ʾ",JOptionPane.YES_NO_OPTION);
				//n����-1��ʾ�ر��˵����ĶԻ���������Ĭ��ֵ
	            //n����0(JOptionPane.YES_OPTION)��ʾѡ����Yes
	            //n����1(JOptionPane.NO_OPTION)��ʾѡ����No
				if(n==JOptionPane.YES_OPTION){
					db = new DBConn();
					model.setRowCount( 0 );
            		model.setRowCount( 20 );
					db.dosth("DELETE FROM stafflist");
					//
					db = new DBConn();
					db.dosth("ALTER TABLE stafflist AUTO_INCREMENT = 0");
					
					pID.setText("");
//					System.out.println("yes");
				}
				else if(n==JOptionPane.NO_OPTION){
	            }
			}
		});
		bAllDelete.setBounds(144, 13, 140, 27);
		//��bAllDelete�ŵ�staffPanel��ȥ
		staffPanel.add(bAllDelete);
		
		
		//���ҿ�������Ա����Ϣ...��
		selectField = new JTextField();
		selectField.setText("\u8F93\u5165\u5458\u5DE5\u4FE1\u606F...");
		selectField.setColumns(10);
		selectField.setBounds(375, 14, 140, 24);
		//��selectField�ŵ�staffPanel��ȥ
		staffPanel.add(selectField);
		
		//��ť�����ң�
		JButton bSearch = new JButton("\u67E5\u627E");
		bSearch.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String message=selectField.getText().toString().trim();
				model.setRowCount( 0 );
                model.setRowCount( 20 );
                db=new DBConn();
        		db.getRs(table,"SELECT * FROM staffList WHERE name LIKE "+"'%"+message+"%'");
			}
		});
		bSearch.setBounds(529, 13, 79, 27);
		staffPanel.add(bSearch);
		
		
		//����������
		JComboBox<Department> cBDpmtSelecct = new JComboBox<>();
		cBDpmtSelecct.setModel(new DefaultComboBoxModel<>(Department.values()));
		cBDpmtSelecct.setBounds(794, 14, 140, 24);
		//����
		cBDpmtSelecct.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
//				ItemEvent������
//				Object getItem() 
//				�������¼�Ӱ���� 
//				Object getItem() 
//				�������¼�Ӱ���� 
//				ItemSelectable getItemSelectable() 
//				�����¼��Ĳ������� 
//				int getStateChange() 
//				����״̬���ĵ����ͣ���ѡ������ȡ��ѡ������ 
//		        String paramString() 
//		              ���ر�ʶ�����¼��Ĳ����ַ���
				switch ((Department)e.getItem())
                {
                case ȫ��: 
                	if(e.getStateChange()==1) {
                		db=new DBConn();
                		db.getRs(table,"SELECT * FROM staffList");
                		break;
                	}
                    
                case ������:
                	if(e.getStateChange()==1) {
                		model.setRowCount( 0 );
                		model.setRowCount( 20 );
                		db=new DBConn();
                		db.getRs(table,"SELECT * FROM staffList WHERE department = '������'");
                		break;
                	}
                    
                case ������:
                	if(e.getStateChange()==1) {
                		model.setRowCount( 0 );
                        model.setRowCount( 20 );
                        db=new DBConn();
                		db.getRs(table,"SELECT * FROM staffList WHERE department = '������'");
                        break;
                	}
                    
                case ��Ӫ��:
                	if(e.getStateChange()==1) {
                		model.setRowCount( 0 );
                		model.setRowCount( 20 );
                		db=new DBConn();
                		db.getRs(table,"SELECT * FROM staffList WHERE department = '��Ӫ��'");
                		break;
                	}
                    
                case �г���:
                	if(e.getStateChange()==1) {
                		model.setRowCount( 0 );
                		model.setRowCount( 20 );
                		db=new DBConn();
                		db.getRs(table,"SELECT * FROM staffList WHERE department = '�г���'");
                		break;
                	}
                    
                case ������:
                	if(e.getStateChange()==1) {
                		model.setRowCount( 0 );
                		model.setRowCount( 20 );
                		db=new DBConn();
                		db.getRs(table,"SELECT * FROM staffList WHERE department = '������'");
                		break;
                	}
                    
				default:
					break;
                }

			}
		});
		staffPanel.add(cBDpmtSelecct);
		
		
		//������壨�������
		ImagePanel operatingPanel = new ImagePanel("operatingImage.jpg");
		operatingPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u8868\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		operatingPanel.setToolTipText("");
		operatingPanel.setBounds(33, 528, 930, 144);
		staffPanel.add(operatingPanel);
		operatingPanel.setLayout(null);
		
		//��ǩ����ţ�
		JLabel labelNum = new JLabel("\u7F16\u53F7\uFF1A");
		labelNum.setBounds(47, 19, 51, 26);
		operatingPanel.add(labelNum);
		
		//�ı��򣨱�ţ�
		pID = new JTextField();
		pID.setEditable(false);
		pID.setBounds(96, 20, 52, 24);
		operatingPanel.add(pID);
		pID.setColumns(10);
		
		
		//��ǩ��������
		JLabel labelName = new JLabel("\u59D3\u540D\uFF1A");
		labelName.setBounds(192, 19, 51, 26);
		operatingPanel.add(labelName);
		
		//�ı���������
		pName = new JTextField();
		pName.setBounds(292, 20, 101, 24);
		operatingPanel.add(pName);
		pName.setColumns(10);
		
		
		//��ǩ���Ա�
		JLabel labelGender = new JLabel("\u6027\u522B\uFF1A");
		labelGender.setBounds(444, 23, 72, 18);
		operatingPanel.add(labelGender);
		
		//�������Ա�
		JComboBox<String> cBoxGender = new JComboBox<>();
		cBoxGender.setModel(new DefaultComboBoxModel<>(new String[] {"\u7537", "\u5973"}));
		cBoxGender.setBounds(517, 20, 51, 24);
		operatingPanel.add(cBoxGender);
		
		
		//��ǩ�����ţ�
		JLabel labelDepartment = new JLabel("\u90E8\u95E8\uFF1A");
		labelDepartment.setBounds(444, 61, 51, 18);
		operatingPanel.add(labelDepartment);
		
		//�����򣨲��ţ�
		JComboBox<Department> cBoxDpmt = new JComboBox<>();
		cBoxDpmt.setModel(new DefaultComboBoxModel<>(Department.values()));
		cBoxDpmt.setBounds(517, 58, 72, 24);
		operatingPanel.add(cBoxDpmt);
		
		
		//��ǩ���������ʣ�
		JLabel labelWages = new JLabel("\u57FA\u672C\u5DE5\u8D44\uFF1A");
		labelWages.setBounds(192, 61, 86, 18);
		operatingPanel.add(labelWages);
		
		//�ı��򣨻������ʣ�
		pWages = new JTextField();
		pWages.setBounds(292, 58, 101, 24);
		operatingPanel.add(pWages);
		pWages.setColumns(10);
		
		
		//��ť���޸ģ�
		JButton bUpdate = new JButton("\u4FEE\u6539");
		//�޸İ�ť����
		bUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id =pID.getText().toString().trim();
				String name = pName.getText().toString().trim();
				String wages = pWages.getText().toString().trim();
				String gender=(String) cBoxGender.getSelectedItem();
				Department department=(Department) cBoxDpmt.getSelectedItem();
				if(id != null && name !=null && wages !=null && isNumeric(wages) && department != Department.ȫ��) {
					
					db = new DBConn();
					db.dosth("UPDATE stafflist SET name = '"+name+"', gender = '"+gender+"', department = '"+department+"', wages = "+wages+" WHERE id = "+id);
					db = new DBConn();
					db.getRs(table,"SELECT * FROM staffList");
				}
				else {
					JOptionPane.showMessageDialog(null, "�޸�ʧ��", "��Ϣ", JOptionPane.NO_OPTION);
				}
				
			}
		});
		bUpdate.setBackground(Color.GREEN);
		bUpdate.setBounds(661, 86, 113, 27);
		operatingPanel.add(bUpdate);
		
		
		//��ť��ɾ����
		JButton bDelete = new JButton("\u5220\u9664");
		//ɾ����ť����
		bDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id =pID.getText().toString().trim();
				if(!id.equals("")) {
					//ɾ��
					db = new DBConn();
					db.dosth("DELETE FROM stafflist WHERE id = "+id);
					
					//��ɾ���Ŵ��id�Լ�1
					db = new DBConn();
					db.dosth("UPDATE stafflist SET id=id-1 where id >"+id);
					db = new DBConn();
					db.dosth("ALTER TABLE stafflist AUTO_INCREMENT = 0");
					
					//ˢ��
					model.setRowCount( 0 );
            		model.setRowCount( 20 );
					db = new DBConn();
					db.getRs(table,"SELECT * FROM staffList");
					pID.setText("");
				}
				else {
					JOptionPane.showMessageDialog(null, "ɾ��ʧ��", "��Ϣ", JOptionPane.OK_OPTION);
				}
				
			}
		});
		bDelete.setBackground(Color.RED);
		bDelete.setBounds(788, 86, 113, 27);
		operatingPanel.add(bDelete);
		
	}
	public void menuInit() {
		JMenuBar menuBar = new JMenuBar();
		contentPane.add(menuBar, BorderLayout.NORTH);
		
		JMenu menu = new JMenu("\u4EBA\u4E8B");
		menuBar.add(menu);
		
		JMenuItem mItemStaff = new JMenuItem("\u5458\u5DE5\u82B1\u540D\u518C");
		//����
		mItemStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "card_staff");
			}
		});
		menu.add(mItemStaff);
		
		JMenuItem mItemMain = new JMenuItem("\u4E3B\u754C\u9762");
		mItemMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "card_main");
			}
		});
		menu.add(mItemMain);
		
		JMenu menu_1 = new JMenu("\u97F3\u4E50");
		menuBar.add(menu_1);
		
		JMenu menu_4 = new JMenu("\u9009\u62E9\u6B4C\u66F2");
		menu_1.add(menu_4);
		
		JMenuItem mntmEyewater = new JMenuItem("eye-water");
		mntmEyewater.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index = 0;
				mp.play(index);
			}
		});
		menu_4.add(mntmEyewater);
		
		JMenuItem mntmAufFlugelnDes = new JMenuItem(" Auf Flugeln Des Gesanges");
		mntmAufFlugelnDes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index = 1;
				mp.play(index);
			}
		});
		menu_4.add(mntmAufFlugelnDes);
		
		JMenuItem menuItem = new JMenuItem("\u7ED3\u675F\u64AD\u653E");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mp.close();
			}
		});
		menu_1.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("\u4E0A\u4E00\u9996");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(index>=1) {
					mp.play(--index);
				}
			}
		});
		menu_1.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("\u4E0B\u4E00\u9996");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(index<=0) {
					mp.play(++index);
				}
			}
		});
		menu_1.add(menuItem_2);
		
		JMenu menu_2 = new JMenu("\u8BBE\u7F6E");
		menuBar.add(menu_2);
		
		JMenuItem menuItem_3 = new JMenuItem("\u8003\u52E4\u8BBE\u7F6E");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "card_AttendancePanel");
			}
		});
		menu_2.add(menuItem_3);
		
		JMenu menu_3 = new JMenu("\u5E2E\u52A9");
		menuBar.add(menu_3);
		
		JMenuItem menuItem_4 = new JMenuItem("\u5173\u4E8E\u6211\u4EEC..");
		menu_3.add(menuItem_4);

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