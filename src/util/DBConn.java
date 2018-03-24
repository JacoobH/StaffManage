package util;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JTable;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBConn {
    private String className; //驱动名
    private String url; //连接数据库的URL地址
    private String username; //数据库的用户名
    private String password; //数据库的密码
    private Connection conn; //数据库连接对象
    private Statement stmt; //数据库预编译处理对象
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
            System.out.println("加载数据库驱动程序失败！");
            e.printStackTrace();
        }catch (SQLException e) {
            System.out.println("数据库连接失败！");
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
	
    public synchronized ResultSet doRs(String sql) {
    	if(sql!=null && !sql.equals("")) {
    		try {
				rs = stmt.executeQuery(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	return rs;
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
             System.out.println("关闭rs对象失败！");
         }
        try{
            if(stmt!=null)
                stmt.close();
        }catch(Exception e){
            System.out.println("关闭stmt对象失败！");
        }
        try{
            if(conn!=null)
                conn.close();
        }catch(Exception e){
            System.out.println("关闭conn对象失败！");
        }
    }

	

    
}
