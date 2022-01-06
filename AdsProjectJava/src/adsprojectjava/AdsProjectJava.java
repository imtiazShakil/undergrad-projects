
package adsprojectjava;

/**
 *
 * @author ImtiazShakil
 */
public class AdsProjectJava {


    public static void main(String[] args) {
        setLogger.init();               
        get_connection.make_sql_connection("test","admin","admin"); ///database name , user , password
        
        loginForm.main( null );
//        mainForm.main(null);
        
    }
}
