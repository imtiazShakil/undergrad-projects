/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adsmongojavaproject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;
import javax.swing.JTextArea;
/**
 *
 * @author ImtiazShakil
 */
public class getConnection {
    static  MongoClient mongo=null;
    DB db=null;
    public static void connectWithMongoDB(String hostName,String port) throws UnknownHostException
    {
        int prt;
        try{
            prt=Integer.parseInt(port);
        }catch(NumberFormatException e) {
            prt=27017;
        }
        mongo = new MongoClient(hostName,prt );   
    }
    
    public static String[] getDatabasenames() 
    {
        try{
            List<String> dbnam=mongo.getDatabaseNames();
        
            String[] tmp = new String[ dbnam.size() ];
            for(int i=0;i<dbnam.size();i++)
            {
                tmp[i]=dbnam.get(i);
            }
            return tmp;
        }catch(NullPointerException e) {
            return new String[0];
        }
    }
    public static void closeConnection() throws Exception {
        mongo.close();
    }
    public getConnection(String databaseName) throws Exception {
        db=mongo.getDB(databaseName);
    }
    public String[] getCollections() throws Exception
    {
        Set<String>collections = db.getCollectionNames();
        String[] tmp = new String[collections.size()];
        int indx=0;
        for(String var : collections){
            tmp[indx++]=var;
//            System.out.println("collections "+var);
	}
        return tmp;
    }
    
    public String[] doQuery(String query,String tableName , JTextArea jta ) throws Exception
    {
        DBCollection table= db.getCollection(tableName);
        DBObject dbObj = (DBObject)JSON.parse(query);
        DBCursor dbc= table.find(dbObj);
        
        jta.setText("");
        while(dbc.hasNext())
        {
            jta.append( dbc.next().toString() );
            jta.append("\n");
//            System.out.println(dbc.next());
        }
        dbc.close();
        return null;
        
    }
    public void addTable(String name) throws Exception{
        
        BasicDBObject bdo = new BasicDBObject();
        bdo.put("CollectionName", "name");
        db.createCollection(name,bdo );
    }
    public void doInsert(String query,String tableName) throws Exception{
        DBCollection table=db.getCollection(tableName);
        DBObject dboj= (DBObject)JSON.parse(query);
        table.insert(dboj);
    }
    public void doDelete(String query,String tableName) throws Exception{
        DBCollection table=db.getCollection(tableName);
        DBObject dboj= (DBObject)JSON.parse(query);
        table.remove(dboj);
    }
    
    public void doUpdate(String query, String updated, String tableName) throws Exception{
        DBCollection table=db.getCollection(tableName);
       
        DBObject fndObj=(DBObject)JSON.parse(query);
        DBObject Obj2=(DBObject)JSON.parse(updated);
        
        DBObject updObj= (DBObject)new BasicDBObject();
        
        updObj.put("$set", Obj2);
        
        System.out.println("Table Name Here\n"+tableName);
        table.update(fndObj, updObj);
        
    }
}
