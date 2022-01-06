package bankingsystem;


import java.sql.*;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

public class mytable  {
    Connection connection = null;
    private JTable table;
    Statement stmt;
    Vector<String>vstr = new Vector<String>();
    mytable(JTable  jtb)
    {
        connection=Oracle_Connection.get_connection();
        stmt=Oracle_Connection.get_statement();
        table=jtb;
    }
    
    Vector<String> get_coulumn_from_table(String table_name)
    {
        Vector<String>v= new Vector<String>();
        table_name = table_name.toUpperCase();
        try {
            ResultSet rset;
            rset = stmt.executeQuery("Select column_name from all_tab_columns where table_name='"+table_name+"'");
            while(rset.next())
            {
                v.add(rset.getString(1));
            }
        }catch(SQLException e) {
            System.out.println(e.toString());
        }
        return  v;
    }
    
    void load_data(String table_name)
    {
        DefaultTableModel tableModel=  new DefaultTableModel(); 
        tableModel.setNumRows(0);
        table.setModel(tableModel);
        
        try {
            ResultSet rset;

            Vector<String>v= get_coulumn_from_table(table_name);
            for(int i=0;i<(int)v.size();i++) tableModel.addColumn(v.get(i));
                
            rset = stmt.executeQuery("SELECT * from "+table_name);
            Vector<Vector> rows=new Vector<Vector>();

            while(rset.next()){
             Vector<String> one_row=new Vector<String>();

             for(int i=1;i<=table.getColumnCount();i++)  one_row.add(rset.getString(i));
             rows.add(one_row);
            }


            Iterator itr=rows.iterator();
            int count=0;
            while(itr.hasNext()){

                tableModel.insertRow(count, (Vector)itr.next());
                count++;
            }
        }catch(SQLException e)
        {
            System.out.println("FOund SQL execution ERRor");
            System.out.println(e.getSQLState());
        }
    }
    
    void create_blank_row()
    {
        DefaultTableModel tableModel= (DefaultTableModel)table.getModel();
        tableModel.insertRow( (int)tableModel.getRowCount() , (Object[])null);
        System.out.println("Blank ROW Created");
    }
   
    public Object GetData(int row_index, int col_index){
        return table.getModel().getValueAt(row_index, col_index);
    }
    
    void save_data( int start_row_indx , String table_name)
    {
        
        Object obj1;vstr.clear();
        for(int i=start_row_indx;i<(int)table.getRowCount();i++)
        {
            int row=i;
            String str= new String();
            for(int j=0;j<(int)table.getColumnCount();j++)
            {
                try
                {
                    obj1=GetData(row, j);
                }catch(NullPointerException e) {
                    obj1="";
                }
                
                if(j==0) str=str+"'"+obj1.toString()+"'";
                else str=str+", '"+obj1.toString()+"'";
            }
            System.out.println("insert into "+table_name+" values( "+str+" )");
            ResultSet rset;
            try {
                rset = stmt.executeQuery("insert into "+table_name+" values( "+str+" )");
            }catch(SQLException e)
            {
                System.out.println(e.toString());
                DefaultTableModel tableModel= (DefaultTableModel)table.getModel();
                tableModel.removeRow(row);
            }
        }

    }
    void delete_data( int row_indx,  String table_name)
    {
        if(row_indx==-1) 
        {
            System.out.println("No Row Selected");
            return; 
        }
        Object obj1;
        String str= new String();int cnt=0;
        for(int i=0;i<(int)table.getColumnCount();i++)
        {
            obj1=GetData( row_indx, i);
            
            try {
            if(obj1.equals(null)) continue;
            }catch(NullPointerException e) {
                continue;
            }

            if(cnt==0) str=str+table.getColumnName(i)+" = "+ "'"+obj1.toString()+"'";
            else str=str+" and " + table.getColumnName(i)+"="+ "'"+obj1.toString()+"'";
            cnt++;   
        }
        
        System.out.println("Delete from "+table_name +" where  " + str);
        ResultSet rset;
        try {
            rset = stmt.executeQuery("Delete from "+table_name +" where  " + str);
            
            DefaultTableModel tableModel= (DefaultTableModel)table.getModel();
            tableModel.removeRow(row_indx);
        }catch(SQLException e)
        {
            System.out.println(e.toString());
        }
        
             
    }
    
    void who_to_update( int row, int col,String table_name)
    {
        int cnt=0;
        String str="update "+table_name;
        String set=" SET ";
        String where=" where ";
        Object obj ;
        for(int i=0;i<(int)table.getColumnCount();i++)
        {
            obj=GetData(row, i);
            try{
                if(obj.toString() !=null) ;
            }catch(NullPointerException e) {
                obj="";
            }
            if(i==col) set=set+" "+table.getColumnName(i)+" = "+"'"+obj.toString()+"'";
            else
            {
                if(cnt!=0) where= where +" and "+table.getColumnName(i)+" = "+"'"+obj.toString()+"'";
                else where = where +table.getColumnName(i)+" = "+"'"+obj.toString()+"'";
                cnt++;
            }
        }
        if(cnt>=1) vstr.add(str+set+where);
    }
    void final_update()
    {
        ResultSet rset;
        for(int i=0;i<(int)vstr.size();i++)
        {
            try {
                System.out.println("Query:->"+vstr.get(i));
                rset = stmt.executeQuery( vstr.get(i) );
            }catch(SQLException e)
            {
                System.out.println(e.toString());
            }
        }
        vstr.clear();
    }
    
    boolean isNULL(Object o)
    {
        try {
            if(o.equals(null)) return true;
            return false;
        }catch(Exception e)
        {
            return  true;
        }
    }
    Vector<String> get_column_from_query(String str)
    {
        str=str.toUpperCase();
        int start=str.indexOf("SELECT");
        String tmp="";
        int last=str.indexOf("FROM");
        int last2=str.indexOf("WHERE");
        
        System.out.println("select indx"+start+" from index "+last+" where index "+last2);
        String table_name="";
        if(last!=-1 && last2!=-1)
        {
            table_name=str.substring(last+4, last2); 
            table_name=table_name.trim(); 
        }
        for(int i=start+6;i<last;i++) tmp+=str.charAt(i);
        System.out.println("Column Name "+tmp+" Table "+table_name);
        
        String col[]= tmp.split(",");
        for(int i=0;i<col.length;i++) {col[i]=col[i].trim();}
        
        Vector<String>v= new Vector<String>();
        if(col[0].equals("*")) v=get_coulumn_from_table(table_name);
        else for(int i=0;i<col.length;i++) v.add(col[i]);
        
        
        return  v;
    }
    void execute_query(String query)
    {
        DefaultTableModel tableModel=  new DefaultTableModel(); 
        tableModel.setNumRows(0);
        table.setModel(tableModel);
        if(connection==null) return ;
        
        try {
            
            Vector<String>v=get_column_from_query(query);
            for(int i=0;i<(int)v.size();i++) tableModel.addColumn(v.get(i));
            
            
            ResultSet rset;
            System.out.println("Qury:-> "+ query);
            rset = stmt.executeQuery(query);
            System.out.println(query);
            Vector<Vector> rows=new Vector<Vector>();

            while(rset.next()){
             Vector<String> one_row=new Vector<String>();
             for(int i=1;i<=table.getColumnCount();i++)  one_row.add(rset.getString(i));
             rows.add(one_row);
             
            }

            Iterator itr=rows.iterator();
            int count=0;
            while(itr.hasNext()){

                tableModel.insertRow(count, (Vector)itr.next());
                count++;
            }
        }catch(SQLException e)
        {
            System.out.println("FOund SQL execution ERRor");
            System.out.println(e.getSQLState());
        }
        
    }
    
}