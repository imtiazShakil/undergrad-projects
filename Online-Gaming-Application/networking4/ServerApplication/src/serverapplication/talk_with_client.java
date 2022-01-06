
package serverapplication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


public class talk_with_client  extends Thread{

    String  myIp;
    DataInputStream din=null;
    DataOutputStream dout=null;
    processOutput todo;
    Socket clientSocket=null;
    String input,output,server_input;
    public talk_with_client(Socket cs , DataInputStream ddi , DataOutputStream ddo ) 
    {
        clientSocket=cs;
        din=ddi;
        dout=ddo;
        myIp=cs.getInetAddress().toString();
        todo= new processOutput(ddi, ddo, myIp);
        this.start();
    }

    String[] parser(String query) {
        String arr[]=query.split("\\s+");
        return arr;
    }
    
    public void run()
    {
        try {
            while( true ) {
                input=din.readUTF();
                System.out.println("Recived from Client "+clientSocket.getInetAddress().toString() + ": " + input);
                
                if(input.equalsIgnoreCase("exit")) break;
                else todo.take_action(input);
                
                
                
                try {
                    this.sleep(1000);
                }catch(InterruptedException e) {
                    
                }
            }
            System.out.println("server got here ");
            dispose();
            
        } catch (IOException ex) {
            Logger.getLogger(talk_with_client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void dispose()
    {
        
        try {
            
            server2.sck_list.remove(clientSocket);
//            server2.DIS.remove(din);
            server2.DOS.remove(dout);
            
            dout.close();din.close();
            server2.M.remove(clientSocket.getInetAddress().toString());
            server2.IpWithUser.remove(clientSocket.getInetAddress().toString());
            clientSocket.close();
        }catch(IOException e) {
            System.out.println("ERROR OCCURED ON CLOSING THE CONNECTION");
        }
        this.stop();
    }

}
