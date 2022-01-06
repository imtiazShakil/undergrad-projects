
package bankingsystem;
import java.sql.*;


public  class Oracle_Connection {
    
    private static Connection connection = null;
    private static Statement stmt;
    
    Oracle_Connection(String username ,String password)
    {
        ResultSet rset;
        try {
        // Load the JDBC driver
        String driverName = "oracle.jdbc.driver.OracleDriver";
        Class.forName(driverName);
        
        // Create a connection to the database
        String serverName = "127.0.0.1";String portNumber = "1521";
        //oracle:thin:@//192.168.2.1:1521/XE 
        String sid = "XE";
        String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + sid;
//        username = "shakil";
//        password = "shakil123";
        connection = DriverManager.getConnection(url, username, password);
        if(connection == null) System.out.println("failed connection");
        else System.out.println("YEAAAAH  passed connection");
        
        stmt = connection.createStatement();
        
//        rset.close();
//        stmt.close();
//        connection.close();
        
        }
        catch (ClassNotFoundException e) {
            System.out.println("Could not find the database driver");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("SQL Exception");
        }
        
    }
    public static boolean isOracleConnected()
    {
        if(connection==null) return  true;
        return  false;
    }
    static Connection get_connection()
    {
        return  connection;
    }
    static Statement get_statement()
    {
        return  stmt;
    }
    static void execute_query(String query)
    {
        try {
            ResultSet rset;
            rset = stmt.executeQuery(query);
            System.out.println("Query:-> "+query);
          
        }catch(SQLException e)
        {
            System.out.println("FOund SQL execution ERRor");
            System.out.println(e.getSQLState());
        }
    }
    
}
