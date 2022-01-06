package serverapplication;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class server2 {
    ServerSocket srvsocket;
    public static Vector<Socket> sck_list = new Vector<Socket>();
//    public static Vector<DataInputStream> DIS = new Vector<DataInputStream>();
    public static Vector<DataOutputStream> DOS= new Vector<DataOutputStream>();
    public static Map<String, Integer> M = new HashMap<String, Integer>();
    public static Map<String,String>IpWithUser = new HashMap<String, String>();
    
    server2() 
    {      
        srvsocket=null;
        try {
            srvsocket = new ServerSocket(4567);
            System.out.println("Listennning on Port Number "+4567);
            for_client();
        } catch (IOException ex) {
            
            System.out.println("Port "+4567+" listening Failed");
            System.exit(1);
        }
    }
    
    void for_client()
    {
        
        try {
            while(true) {
                Socket clientSocket=null;
                clientSocket = srvsocket.accept();
                DataInputStream dis=new DataInputStream(clientSocket.getInputStream());
                DataOutputStream dos= new DataOutputStream(clientSocket.getOutputStream());
                
                System.out.println("Server Is now Connected With this particular client "+clientSocket.getInetAddress().toString() );
                sck_list.add(clientSocket);
//                DIS.add(dis);
                DOS.add(dos);
                M.put(clientSocket.getInetAddress().toString(), 0);
                
                talk_with_client twc= new talk_with_client(clientSocket, dis, dos);
                
            }
        } catch (IOException ex) {
            
            System.out.println("Client Accpetance Failed");
            System.exit(1);
        }
    }
    
}
