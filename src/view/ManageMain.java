package view;
/*
 * 作者注：
 * 
 * 此类使用windowbuilder编写
 * 编写过程中没有考虑代码耦合度问题，
 * 而其中staffPanel（cardPanel（卡片布局）的其中一张卡片）拥有较多组件和功能，
 * 没有及时将以staffPanel为首的众多卡片(JPanel)抽象成类
 * 又因为各种业务逻辑纠缠在一起，后续的抽象工作比较困难
 * 使代码可读性不高，
 * 为了提高可读性
 * 我们使用了menuInit()方法进行菜单初始化,mainInit()进行主菜单初始化
 * 调用staffInit()进行员工花名册初始化
 * 
 * */
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
import java.awt.Font;

public class ManageMain extends JFrame{

	private static final long serialVersionUID = 1L;
	//内容面板
	private JPanel contentPane;

	private JTable table;
	private CardLayout card = new CardLayout();
	private JPanel cardPanel;
	private JTextField selectField;
	//数据库对象
	private DBConn db;
	//创建播放器对象
	private MusicPlayer mp = MusicPlayer.makePlayer();
	//当前歌曲编号
	public static int index = -1;
	public static int ROW_NUM = 20;
	
	private JTextField pID;
	private JTextField pName;
	private JTextField pWages;
	
	public static DefaultTableModel model;
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
		
		//设置此Frame属性
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024,768);
		//将此Frame居中
		new mid(this);
		//不能调整界面大小
		setResizable(false);
		
		//内容面板属性
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//调用menuInit()设置菜单栏
		menuInit();
		
		//设置卡片容器
		cardPanel = new JPanel();
		contentPane.add(cardPanel);
		cardPanel.setLayout(card);
		
		//mainPanel作为一张卡片（主界面）
		mainInit();
		
		//调用staffPanel()方法设置卡片
		staffInit();
		
		//管理员账户管理卡片
		AdminCard adminPanel = new AdminCard();
		cardPanel.add(adminPanel, "card_admin");
		
		//attendancePanel作为卡片
		AttendancePanel attendancePanel = new AttendancePanel();
		cardPanel.add(attendancePanel, "card_AttendancePanel");
	}
	
	public void mainInit () {
		ImagePanel mainPanel = new ImagePanel("MainImage.jpg");
		cardPanel.add(mainPanel, "card_main");
		mainPanel.setLayout(null);
		
		JLabel currentUser = new JLabel("当前账户："+Login.currentUser);
		currentUser.setForeground(Color.WHITE);
		currentUser.setFont(new Font("幼圆", Font.BOLD, 21));
		currentUser.setBounds(309, 572, 226, 54);
		mainPanel.add(currentUser);
		
		JButton resignButton = new JButton("\u91CD\u65B0\u767B\u9646");
		resignButton.setFont(new Font("幼圆", Font.PLAIN, 19));
		resignButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				dispose();
			}
		});
		resignButton.setBounds(648, 582, 116, 36);
		mainPanel.add(resignButton);
	}
	
	public void staffInit() {
		
		//初始化staffPanel并作为第二张卡片
		ImagePanel staffPanel = new ImagePanel("StaffImage.jpg");
		cardPanel.add(staffPanel, "card_staff");
		staffPanel.setLayout(null);
		//初始化tablePanel，并放到staffPanel中
		JPanel tablePanel = new JPanel();
		tablePanel.setBounds(33, 92, 930, 423);
		staffPanel.add(tablePanel);
		tablePanel.setLayout(new BorderLayout(0, 0));
		//设置JTable的模型
		String[] title= {"#","姓名","性别","部门","工资","操作"};
		model=new DefaultTableModel(new String[ROW_NUM][6],title);
		table = new JTable(model);
		//table监听
		table.addMouseListener(new MouseAdapter() {
			@Override
			//如果点击table
			public void mousePressed(MouseEvent e) {
				//判断是否有数据
				if(table.getValueAt(table.getSelectedRow(), 0) != null ) {
					//将选中行的id值赋给 pID
					pID.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				}
				else
					pID.setText("");
			}
		});
		//设置JTable内容居中
		DefaultTableCellRenderer r = new   DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		table.setDefaultRenderer(Object.class,   r);
		//设置JTable行高
		table.setRowHeight(30);
		table.getColumnModel().getColumn(5).setCellEditor(new MyRender(table));//设置编辑器
		table.getColumnModel().getColumn(5).setCellRenderer(new MyRender(table));//得到列类型，得到指定参数的类，设置渲染器
		//连接数据库
		db=new DBConn();
		db.getRs(table,"SELECT * FROM staffList");
		JScrollPane scrollPane = new JScrollPane(table);
		//将带滚轮的table放到tablePanel中
		tablePanel.add(scrollPane);
		
		
		//按钮（新增）
		JButton bAdd = new JButton("\u65B0\u589E");
		//（新增）监听
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
		//将bAdd放到staffPanel中去
		staffPanel.add(bAdd);
		
		
		//按钮（删除所有数据）
		JButton bAllDelete = new JButton("\u5220\u9664\u6240\u6709\u6570\u636E");
		bAllDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "确定删除所有数据吗？", "提示",JOptionPane.YES_NO_OPTION);
				//n等于-1表示关闭了弹出的对话框等情况的默认值
	            //n等于0(JOptionPane.YES_OPTION)表示选择了Yes
	            //n等于1(JOptionPane.NO_OPTION)表示选择了No
				if(n==JOptionPane.YES_OPTION){
					db = new DBConn();
					model.setRowCount( 0 );
            		model.setRowCount( ROW_NUM );
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
		//将bAllDelete放到staffPanel中去
		staffPanel.add(bAllDelete);
		
		
		//查找框（请输入员工信息...）
		selectField = new JTextField();
		selectField.setText("\u8F93\u5165\u5458\u5DE5\u4FE1\u606F...");
		selectField.setColumns(10);
		selectField.setBounds(375, 14, 140, 24);
		//将selectField放到staffPanel中去
		staffPanel.add(selectField);
		
		//按钮（查找）
		JButton bSearch = new JButton("\u67E5\u627E");
		bSearch.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String message=selectField.getText().toString().trim();
				model.setRowCount( 0 );
                model.setRowCount( ROW_NUM );
                db=new DBConn();
        		db.getRs(table,"SELECT * FROM staffList WHERE name LIKE "+"'%"+message+"%'");
			}
		});
		bSearch.setBounds(529, 13, 79, 27);
		staffPanel.add(bSearch);
		
		
		//部门下拉框
		JComboBox<Department> cBDpmtSelecct = new JComboBox<>();
		cBDpmtSelecct.setModel(new DefaultComboBoxModel<>(Department.values()));
		cBDpmtSelecct.setBounds(794, 14, 140, 24);
		//监听
		cBDpmtSelecct.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
//				ItemEvent方法：
//				Object getItem() 
//				返回受事件影响的项。 
//				Object getItem() 
//				返回受事件影响的项。 
//				ItemSelectable getItemSelectable() 
//				返回事件的产生程序。 
//				int getStateChange() 
//				返回状态更改的类型（已选定或已取消选定）。 
//		        String paramString() 
//		              返回标识此项事件的参数字符串
				switch ((Department)e.getItem())
                {
                case 全部: 
                	if(e.getStateChange()==1) {
                		db=new DBConn();
                		db.getRs(table,"SELECT * FROM staffList");
                		break;
                	}
                    
                case 技术部:
                	if(e.getStateChange()==1) {
                		model.setRowCount( 0 );
                		model.setRowCount( ROW_NUM );
                		db=new DBConn();
                		db.getRs(table,"SELECT * FROM staffList WHERE department = '技术部'");
                		break;
                	}
                    
                case 财政部:
                	if(e.getStateChange()==1) {
                		model.setRowCount( 0 );
                        model.setRowCount( ROW_NUM );
                        db=new DBConn();
                		db.getRs(table,"SELECT * FROM staffList WHERE department = '财政部'");
                        break;
                	}
                    
                case 运营部:
                	if(e.getStateChange()==1) {
                		model.setRowCount( 0 );
                		model.setRowCount( ROW_NUM );
                		db=new DBConn();
                		db.getRs(table,"SELECT * FROM staffList WHERE department = '运营部'");
                		break;
                	}
                    
                case 市场部:
                	if(e.getStateChange()==1) {
                		model.setRowCount( 0 );
                		model.setRowCount( ROW_NUM );
                		db=new DBConn();
                		db.getRs(table,"SELECT * FROM staffList WHERE department = '市场部'");
                		break;
                	}
                    
                case 物流部:
                	if(e.getStateChange()==1) {
                		model.setRowCount( 0 );
                		model.setRowCount( ROW_NUM );
                		db=new DBConn();
                		db.getRs(table,"SELECT * FROM staffList WHERE department = '物流部'");
                		break;
                	}
                    
				default:
					break;
                }

			}
		});
		staffPanel.add(cBDpmtSelecct);
		
		
		//操作面板（表操作）
		ImagePanel operatingPanel = new ImagePanel("operatingImage.jpg");
		operatingPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u8868\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		operatingPanel.setToolTipText("");
		operatingPanel.setBounds(33, 528, 930, 144);
		staffPanel.add(operatingPanel);
		operatingPanel.setLayout(null);
		
		//标签（编号）
		JLabel labelNum = new JLabel("\u7F16\u53F7\uFF1A");
		labelNum.setBounds(47, 19, 51, 26);
		operatingPanel.add(labelNum);
		
		//文本框（编号）
		pID = new JTextField();
		pID.setEditable(false);
		pID.setBounds(96, 20, 52, 24);
		operatingPanel.add(pID);
		pID.setColumns(10);
		
		
		//标签（姓名）
		JLabel labelName = new JLabel("\u59D3\u540D\uFF1A");
		labelName.setBounds(192, 19, 51, 26);
		operatingPanel.add(labelName);
		
		//文本框（姓名）
		pName = new JTextField();
		pName.setBounds(292, 20, 101, 24);
		operatingPanel.add(pName);
		pName.setColumns(10);
		
		
		//标签（性别）
		JLabel labelGender = new JLabel("\u6027\u522B\uFF1A");
		labelGender.setBounds(444, 23, 72, 18);
		operatingPanel.add(labelGender);
		
		//下拉框（性别）
		JComboBox<String> cBoxGender = new JComboBox<>();
		cBoxGender.setModel(new DefaultComboBoxModel<>(new String[] {"\u7537", "\u5973"}));
		cBoxGender.setBounds(517, 20, 51, 24);
		operatingPanel.add(cBoxGender);
		
		
		//标签（部门）
		JLabel labelDepartment = new JLabel("\u90E8\u95E8\uFF1A");
		labelDepartment.setBounds(444, 61, 51, 18);
		operatingPanel.add(labelDepartment);
		
		//下拉框（部门）
		JComboBox<Department> cBoxDpmt = new JComboBox<>();
		cBoxDpmt.setModel(new DefaultComboBoxModel<>(Department.values()));
		cBoxDpmt.setBounds(517, 58, 72, 24);
		operatingPanel.add(cBoxDpmt);
		
		
		//标签（基本工资）
		JLabel labelWages = new JLabel("\u57FA\u672C\u5DE5\u8D44\uFF1A");
		labelWages.setBounds(192, 61, 86, 18);
		operatingPanel.add(labelWages);
		
		//文本框（基本工资）
		pWages = new JTextField();
		pWages.setBounds(292, 58, 101, 24);
		operatingPanel.add(pWages);
		pWages.setColumns(10);
		
		
		//按钮（修改）
		JButton bUpdate = new JButton("\u4FEE\u6539");
		//修改按钮监听
		bUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id =pID.getText().toString().trim();
				String name = pName.getText().toString().trim();
				String wages = pWages.getText().toString().trim();
				String gender=(String) cBoxGender.getSelectedItem();
				Department department=(Department) cBoxDpmt.getSelectedItem();
				if(!id.equals("") && !name.equals("") && !wages.equals("") && isNumeric(wages) && department != Department.全部 && name.length() < 15) {
					
					db = new DBConn();
					db.dosth("UPDATE stafflist SET name = '"+name+"', gender = '"+gender+"', department = '"+department+"', wages = "+wages+" WHERE id = "+id);
					db = new DBConn();
					db.getRs(table,"SELECT * FROM staffList");
				}
				else if(name.length() >= 15) {
					JOptionPane.showMessageDialog(null, "名字最多14位！", "消息", JOptionPane.NO_OPTION);
				}
				else {
					JOptionPane.showMessageDialog(null, "修改失败！", "消息", JOptionPane.NO_OPTION);
				}
				
			}
		});
		bUpdate.setBackground(Color.GREEN);
		bUpdate.setBounds(661, 86, 113, 27);
		operatingPanel.add(bUpdate);
		
		
		//按钮（删除）
		JButton bDelete = new JButton("\u5220\u9664");
		//删除按钮监听
		bDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id =pID.getText().toString().trim();
				if(!id.equals("")) {
					//删除
					db = new DBConn();
					db.dosth("DELETE FROM stafflist WHERE id = "+id);
					
					//比删除号大的id自减1
					db = new DBConn();
					db.dosth("UPDATE stafflist SET id=id-1 where id >"+id);
					db = new DBConn();
					db.dosth("ALTER TABLE stafflist AUTO_INCREMENT = 0");
					
					//刷新
					model.setRowCount( 0 );
            		model.setRowCount( ROW_NUM );
					db = new DBConn();
					db.getRs(table,"SELECT * FROM staffList");
					pID.setText("");
				}
				else {
					JOptionPane.showMessageDialog(null, "删除失败", "消息", JOptionPane.OK_OPTION);
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
		//监听
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
		
		JMenuItem mItemManager = new JMenuItem("\u7BA1\u7406\u5458\u8D26\u6237");
		mItemManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "card_admin");
			}
		});
		menu.add(mItemManager);
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
				if(index != -1) {
					mp.close();
				}
				
			}
		});
		menu_1.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("\u4E0A\u4E00\u9996");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(index>=1 && index!= -1) {
					mp.play(--index);
				}
			}
		});
		menu_1.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("\u4E0B\u4E00\u9996");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(index<=0 && index!= -1) {
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
