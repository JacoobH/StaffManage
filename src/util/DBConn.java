package util;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JTable;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBConn {
    private String className; //������
    private String url; //�������ݿ��URL��ַ
    private String username; //���ݿ���û���
    private String password; //���ݿ������
    private Connection conn; //���ݿ����Ӷ���
    private Statement stmt; //���ݿ�Ԥ���봦�����
    private ResultSet rs;
    public DBConn(){
        className="com.mysql.jdbc.Driver";
        url="jdbc:mysql://localhost:3306/staff?useSSL=false";
        username="root";
        password="";
        try{
            Class.forName(className);
            conn=DriverManager.getConnection(url,username,password);
            stmt = conn.createStatement();    
        }catch(ClassNotFoundException e){
            System.out.println("�������ݿ���������ʧ�ܣ�");
            e.printStackTrace();
        }catch (SQLException e) {
            System.out.println("���ݿ�����ʧ�ܣ�");
            e.printStackTrace();
        }
    }
    public synchronized void dosth(String sql) {
    	if(sql!=null && !sql.equals("")) {
    		try {
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
    			closed();
    		}
    	}
    }
    public synchronized void getRs(JTable t,String sql) {
    	if(sql!=null && !sql.equals("")) {
    		int i=0;
        	try {
        		rs = stmt.executeQuery(sql);
    			while (rs.next()) {
    				int id=rs.getInt("id");
    				String name=rs.getString("name");
    				String gender=rs.getString("gender");
    				String department=rs.getString("department");
    				double wages=rs.getDouble("wages");
    				
    				t.setValueAt(id, i, 0);
    				t.setValueAt(name, i, 1);
    				t.setValueAt(gender, i, 2);
    				t.setValueAt(department, i, 3);
    				t.setValueAt(wages, i, 4);
    				i++;
    			}
    		} catch (SQLException e) {
    			e.printStackTrace();
    		} finally {
    			closed();
    		}
    	}
    }
    public void closed(){
    	 try{
             if(rs!=null)
                 rs.close();
         }catch(Exception e){
             System.out.println("�ر�rs����ʧ�ܣ�");
         }
        try{
            if(stmt!=null)
                stmt.close();
        }catch(Exception e){
            System.out.println("�ر�stmt����ʧ�ܣ�");
        }
        try{
            if(conn!=null)
                conn.close();
        }catch(Exception e){
            System.out.println("�ر�conn����ʧ�ܣ�");
        }
    }

	

    
}