package util;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class MyRender extends AbstractCellEditor implements TableCellRenderer,TableCellEditor{
	 
    private static final long serialVersionUID = 1L;
    private JButton button =null;
    private DBConn db=null;
    public MyRender(JTable table,DefaultTableModel model){
    	
        button = new JButton("删除本行");
        button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getValueAt(table.getSelectedRow(), 0) != null ) {
					String id = table.getValueAt(table.getSelectedRow(), 0).toString();
					db = new DBConn();
					db.dosth("DELETE FROM stafflist WHERE id = "+id);
					//比删除号大的id自减1
					db = new DBConn();
					db.dosth("UPDATE stafflist SET id=id-1 where id >"+id);
					db = new DBConn();
					db.dosth("ALTER TABLE stafflist AUTO_INCREMENT = 0");
					
					//刷新
					model.setRowCount( 0 );
            		model.setRowCount( 20 );
					db = new DBConn();
					db.getRs(table,"SELECT * FROM staffList");
				}
				else {
					JOptionPane.showMessageDialog(null, "删除失败", "消息", JOptionPane.OK_OPTION);
				}
			}
		});
    }
 
@Override
    public Object getCellEditorValue() {
        // TODO Auto-generated method stub
        return null;
    }
 
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        // TODO Auto-generated method stub
        return button;
    }
 
//@Override
//    public void actionPerformed(ActionEvent e) {
//        // TODO Auto-generated method stub
////		System.out.println(this.getCellEditorValue());
//        JOptionPane.showMessageDialog(null, "删除失败", "消息", JOptionPane.OK_OPTION);
//         
//    }
 
@Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        // TODO Auto-generated method stub
        return button;
    }
     
}
