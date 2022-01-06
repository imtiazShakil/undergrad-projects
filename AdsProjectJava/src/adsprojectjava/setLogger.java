/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adsprojectjava;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author ImtiazShakil
 */
public class setLogger {
    
    private final static Logger logger = Logger.getLogger(AdsProjectJava.class.getName());
    private static FileHandler fh = null;

    public static void init(){
        try {
            fh=new FileHandler("ADS_Project_Java.log", false);
        } catch ( IOException e) {
            e.printStackTrace();
        } catch( SecurityException e) {
            e.printStackTrace();
        }
        
        Logger l = Logger.getLogger("");
        fh.setFormatter(new SimpleFormatter());
        l.addHandler(fh);
        l.setLevel(Level.SEVERE); /// only Severe Logs will be shown
    }
}
