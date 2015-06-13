package xyz.onesway.tool;

import java.sql.*;

/**
 * @author Ben Li
 * @version Date：2015年5月31日 下午4:29:07
 */
public class DB_manager {
    private static String url=null;
    private static String username=null;
    private static String pwd=null;
    
    static{
        url="jdbc:mysql://127.0.0.1:3306/smart";
        username="root";
        pwd="123456";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static  Connection  getConnection(){
        try {
            return DriverManager.getConnection(url,username,pwd);
        } catch (SQLException e) {
          e.printStackTrace();
        }
        return null;
    }
public static void closeJDBC(ResultSet rst ,Statement st, Connection con){
        
        if(rst!=null){
            
            try {
                rst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        }
        
        if(st!=null)
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
                st=null;
            }
        
        
        if(con!=null)
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                con=null;
            }   
        
    }
    
}
