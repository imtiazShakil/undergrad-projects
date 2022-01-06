/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package autocompletion;


import java.io.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nafisahmed
 */
public class filterQuery {
    
    void flQuery()
    {
        ArrayList al;
        generateTree gt = new generateTree();
        gt.getDict();
        
        try {
            Scanner inFile = new Scanner(new FileReader("//Users/nafisahmed/NetBeansProjects/"
                    + "AutoCompletion/Query.txt"));
            File outFile = new File("//Users/nafisahmed/NetBeansProjects/AutoCompletion/FilteredQuery.txt");
            PrintWriter printer = new PrintWriter(outFile);
            
            
            while(inFile.hasNext())
            {
                int ok=1;
                String str = inFile.nextLine();
                String word[] = str.split("([ ]|\n)+");
                for(int i=0;i<word.length;i++)
                {
                    String cur = word[i];
                    Integer flag = new Integer(1);
                    al = gt.searchTree(cur, 1);
                    if(al.isEmpty()) continue;
                    else
                    {
                        ok=0;
                        break;
                    }
                }
                if(ok==1)
                {
                    printer.write(str+"\n");
                }
                else
                    System.out.println(str);
            }
        } catch (Exception ex) {
            Logger.getLogger(filterQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
