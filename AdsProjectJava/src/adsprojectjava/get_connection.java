
package adsprojectjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ImtiazShakil
 */

public class get_connection {
        static Connection  conn=null;
        static Statement stmt=null;
        static ResultSet res=null;
        
        static String dbName;
        static String username;
        static String password;
        
    public static void make_sql_connection(String dbName, String username,String password ) {
        String dbUrl="jdbc:mysql://localhost/"+dbName;
        get_connection.dbName=dbName;
        get_connection.username=username;
        get_connection.password=password;
               
        try{
            conn= DriverManager.getConnection(dbUrl, username, password);
            stmt=conn.createStatement();
            Logger.getLogger(AdsProjectJava.class.getName()).log(Level.INFO, "Connection Successful");
        }catch(SQLException e) {
            Logger.getLogger(AdsProjectJava.class.getName()).log(Level.SEVERE, null, e);        
        }
    }
    
    
    public static ResultSet doQuery(String query) throws SQLException
    {
        res=stmt.executeQuery(query);
        return res;
    }
    public static void doUpdate(String query) throws SQLException 
    {
        stmt.executeUpdate(query);
    }
    public static void close_connection() {
            try {
                res.close();
                stmt.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(get_connection.class.getName()).log(Level.SEVERE, "Problem at closing connection", ex);
            } catch(NullPointerException ex) {
                Logger.getLogger(get_connection.class.getName()).log(Level.SEVERE, "Problem at closing connection", ex);
            }
            
    }
}
