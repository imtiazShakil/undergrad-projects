package adsprojectjava;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class getResult {
    
    
    public static void getColoumn( ResultSet res , ArrayList<String>col )
    {
        try {
            ResultSetMetaData rsmd=res.getMetaData();
            col.clear();
            for(int i=1;i<=rsmd.getColumnCount();i++)
            {
                col.add(rsmd.getColumnLabel(i));
            }
        } catch (SQLException ex) {
            Logger.getLogger(getResult.class.getName()).log(Level.SEVERE, "Column read korte jhamela hoise\n", ex);
        }
    }
    public static int getColoumnCount(ResultSet res)
    {
        try {
            return res.getMetaData().getColumnCount();
            } catch (SQLException ex) {
            Logger.getLogger(getResult.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public static void fillTable(ResultSet res , JTable jtb )//ArrayList< ArrayList<String> >row )
    {
        DefaultTableModel dtm= new DefaultTableModel();
        ArrayList<String>oneRow = new ArrayList<String>();
        getColoumn(res, oneRow);
        for(int i=0;i<oneRow.size();i++) dtm.addColumn(oneRow.get(i));
        
        try {
//                row.clear();
                int cnt=getColoumnCount(res);
                
                while(res.next())
                {
                    oneRow.clear();
                    for(int i=1;i<=cnt;i++) 
                    {
                        oneRow.add(res.getString(i));
                    }
//                    for(int i=0;i<oneRow.size();i++) System.out.println("here "+oneRow.get(i));
//                    row.add(oneRow);
                    dtm.addRow(oneRow.toArray());
                    
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Invalid Query");
                Logger.getLogger(getResult.class.getName()).log(Level.SEVERE, null, ex);
            }
        jtb.removeAll();
        jtb.setModel(dtm);
        
    }
    
    public static String[] get_special_ans(ResultSet res) throws SQLException
    {
        ArrayList<String> arl= new ArrayList<String>();
        while(res.next())
        {
            arl.add(res.getString(1));
        }
        Object obj[]= arl.toArray();
        String str[] = new String[obj.length];
        for(int i=0;i<obj.length;i++)
            str[i]=obj[i].toString();
        return str;
    }
    
    public static String[] get_tableName() {
        try {
            //        show tables from test ;
                    ArrayList<String>arl= new ArrayList<String>();
                    ResultSet res= get_connection.stmt.executeQuery("show tables from "+get_connection.dbName+" ;");
                    
                    return get_special_ans(res);
        } catch (SQLException ex) {
            Logger.getLogger(getResult.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
