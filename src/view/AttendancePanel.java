package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;

public class AttendancePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	public AttendancePanel() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u56FA\u5B9A\u73ED\u6B21", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(20, 25, 965, 313);
		add(panel);
		panel.setLayout(null);
		
		JLabel label_1 = new JLabel("\u73ED\u6B21");
		label_1.setFont(new Font("Ó×Ô²", Font.PLAIN, 18));
		label_1.setBounds(74, 63, 49, 24);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u4E0A\u73ED\u8003\u52E4\u65F6\u95F4");
		label_2.setFont(new Font("Ó×Ô²", Font.PLAIN, 18));
		label_2.setBounds(144, 64, 119, 24);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("\u4E0B\u73ED\u8003\u52E4\u65F6\u95F4");
		label_3.setFont(new Font("Ó×Ô²", Font.PLAIN, 18));
		label_3.setBounds(286, 64, 119, 24);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("\u6709\u6548\u6253\u5361\u65F6\u95F4");
		label_4.setFont(new Font("Ó×Ô²", Font.PLAIN, 18));
		label_4.setBounds(446, 63, 119, 24);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("\u5348\u4F11\u65F6\u95F4");
		label_5.setFont(new Font("Ó×Ô²", Font.PLAIN, 18));
		label_5.setBounds(593, 63, 82, 24);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("\u5907\u6CE8");
		label_6.setFont(new Font("Ó×Ô²", Font.PLAIN, 18));
		label_6.setBounds(768, 63, 53, 24);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("\u64CD\u4F5C");
		label_7.setFont(new Font("Ó×Ô²", Font.PLAIN, 18));
		label_7.setBounds(891, 66, 53, 24);
		panel.add(label_7);
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		comboBox.setBounds(75, 101, 45, 24);
		panel.add(comboBox);
		
		JLabel label_8 = new JLabel("\uFF1A");
		label_8.setBounds(195, 101, 15, 24);
		panel.add(label_8);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		comboBox_2.setBounds(207, 101, 49, 24);
		panel.add(comboBox_2);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		comboBox_1.setBounds(144, 101, 49, 24);
		panel.add(comboBox_1);
		
		JLabel label_9 = new JLabel("\uFF1A");
		label_9.setBounds(338, 101, 15, 24);
		panel.add(label_9);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		comboBox_3.setBounds(286, 101, 49, 24);
		panel.add(comboBox_3);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		comboBox_4.setBounds(349, 101, 49, 24);
		panel.add(comboBox_4);
		
		JLabel label_10 = new JLabel("\u4E0A\u5348\u6700\u65E9\u63D0\u524D");
		label_10.setFont(new Font("ËÎÌå", Font.PLAIN, 13));
		label_10.setBounds(412, 87, 80, 18);
		panel.add(label_10);
		
		textField = new JTextField();
		textField.setFont(new Font("ËÎÌå", Font.PLAIN, 13));
		textField.setBounds(493, 87, 28, 18);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel label_11 = new JLabel("\u5206\u949F\u7B7E\u5230");
		label_11.setFont(new Font("ËÎÌå", Font.PLAIN, 13));
		label_11.setBounds(523, 87, 59, 18);
		panel.add(label_11);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("ËÎÌå", Font.PLAIN, 13));
		textField_1.setColumns(10);
		textField_1.setBounds(493, 107, 28, 18);
		panel.add(textField_1);
		
		JLabel label_12 = new JLabel("\u5206\u949F\u7B7E\u9000");
		label_12.setFont(new Font("ËÎÌå", Font.PLAIN, 13));
		label_12.setBounds(523, 107, 59, 18);
		panel.add(label_12);
		
		JLabel label_13 = new JLabel("\u4E0B\u5348\u6700\u665A\u63A8\u8FDF");
		label_13.setFont(new Font("ËÎÌå", Font.PLAIN, 13));
		label_13.setBounds(412, 107, 80, 18);
		panel.add(label_13);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setBounds(593, 101, 49, 24);
		panel.add(comboBox_5);
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setBounds(644, 101, 49, 24);
		panel.add(comboBox_6);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("ËÎÌå", Font.PLAIN, 13));
		textField_2.setColumns(10);
		textField_2.setBounds(751, 100, 105, 25);
		panel.add(textField_2);
		
		JLabel label = new JLabel("\u73ED\u6B21");
		label.setFont(new Font("Ó×Ô²", Font.PLAIN, 18));
		label.setBounds(74, 170, 49, 24);
		panel.add(label);
		
		JLabel label_14 = new JLabel("\u4E0A\u73ED\u8003\u52E4\u65F6\u95F4");
		label_14.setFont(new Font("Ó×Ô²", Font.PLAIN, 18));
		label_14.setBounds(144, 171, 119, 24);
		panel.add(label_14);
		
		JLabel label_15 = new JLabel("\u4E0B\u73ED\u8003\u52E4\u65F6\u95F4");
		label_15.setFont(new Font("Ó×Ô²", Font.PLAIN, 18));
		label_15.setBounds(286, 171, 119, 24);
		panel.add(label_15);
		
		JLabel label_16 = new JLabel("\u6709\u6548\u6253\u5361\u65F6\u95F4");
		label_16.setFont(new Font("Ó×Ô²", Font.PLAIN, 18));
		label_16.setBounds(446, 170, 119, 24);
		panel.add(label_16);
		
		JLabel label_17 = new JLabel("\u5348\u4F11\u65F6\u95F4");
		label_17.setFont(new Font("Ó×Ô²", Font.PLAIN, 18));
		label_17.setBounds(593, 170, 82, 24);
		panel.add(label_17);
		
		JLabel label_18 = new JLabel("\u5907\u6CE8");
		label_18.setFont(new Font("Ó×Ô²", Font.PLAIN, 18));
		label_18.setBounds(768, 170, 53, 24);
		panel.add(label_18);
		
		JLabel label_19 = new JLabel("\u64CD\u4F5C");
		label_19.setFont(new Font("Ó×Ô²", Font.PLAIN, 18));
		label_19.setBounds(891, 173, 53, 24);
		panel.add(label_19);
		
		JComboBox<String> comboBox_7 = new JComboBox<String>();
		comboBox_7.setModel(new DefaultComboBoxModel(new String[] {"2", "3", "4", "5", "6", "7", "8", "9"}));
		comboBox_7.setBounds(75, 208, 45, 24);
		panel.add(comboBox_7);
		
		JLabel label_20 = new JLabel("\uFF1A");
		label_20.setBounds(195, 208, 15, 24);
		panel.add(label_20);
		
		JComboBox comboBox_8 = new JComboBox();
		comboBox_8.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		comboBox_8.setBounds(207, 208, 49, 24);
		panel.add(comboBox_8);
		
		JComboBox comboBox_9 = new JComboBox();
		comboBox_9.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		comboBox_9.setBounds(144, 208, 49, 24);
		panel.add(comboBox_9);
		
		JLabel label_21 = new JLabel("\uFF1A");
		label_21.setBounds(338, 208, 15, 24);
		panel.add(label_21);
		
		JComboBox comboBox_10 = new JComboBox();
		comboBox_10.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		comboBox_10.setBounds(286, 208, 49, 24);
		panel.add(comboBox_10);
		
		JComboBox comboBox_11 = new JComboBox();
		comboBox_11.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		comboBox_11.setBounds(349, 208, 49, 24);
		panel.add(comboBox_11);
		
		JLabel label_22 = new JLabel("\u4E0A\u5348\u6700\u65E9\u63D0\u524D");
		label_22.setFont(new Font("ËÎÌå", Font.PLAIN, 13));
		label_22.setBounds(412, 194, 80, 18);
		panel.add(label_22);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("ËÎÌå", Font.PLAIN, 13));
		textField_3.setColumns(10);
		textField_3.setBounds(493, 194, 28, 18);
		panel.add(textField_3);
		
		JLabel label_23 = new JLabel("\u5206\u949F\u7B7E\u5230");
		label_23.setFont(new Font("ËÎÌå", Font.PLAIN, 13));
		label_23.setBounds(523, 194, 59, 18);
		panel.add(label_23);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("ËÎÌå", Font.PLAIN, 13));
		textField_4.setColumns(10);
		textField_4.setBounds(493, 214, 28, 18);
		panel.add(textField_4);
		
		JLabel label_24 = new JLabel("\u5206\u949F\u7B7E\u9000");
		label_24.setFont(new Font("ËÎÌå", Font.PLAIN, 13));
		label_24.setBounds(523, 214, 59, 18);
		panel.add(label_24);
		
		JLabel label_25 = new JLabel("\u4E0B\u5348\u6700\u665A\u63A8\u8FDF");
		label_25.setFont(new Font("ËÎÌå", Font.PLAIN, 13));
		label_25.setBounds(412, 214, 80, 18);
		panel.add(label_25);
		
		JComboBox comboBox_12 = new JComboBox();
		comboBox_12.setBounds(593, 208, 49, 24);
		panel.add(comboBox_12);
		
		JComboBox comboBox_13 = new JComboBox();
		comboBox_13.setBounds(644, 208, 49, 24);
		panel.add(comboBox_13);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("ËÎÌå", Font.PLAIN, 13));
		textField_5.setColumns(10);
		textField_5.setBounds(751, 207, 105, 25);
		panel.add(textField_5);
	}
}
