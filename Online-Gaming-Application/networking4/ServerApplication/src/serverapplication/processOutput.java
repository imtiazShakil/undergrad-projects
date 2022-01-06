
package serverapplication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;


public class processOutput {
    
    String User_name , myIp;
    DataInputStream din=null;
    DataOutputStream dout=null;
    
    public processOutput( DataInputStream ddi , DataOutputStream ddo  , String myIp) 
    {
        din=ddi;
        dout=ddo;
        this.myIp=myIp;
    }

    String[] parser(String query) {
        String arr[]=query.split("\\s+");
        return arr;
    }
    
    public void take_action(String input)
    {
        try {
            if(input.equalsIgnoreCase("MyUserName")) setUserNameForThisIp(input);
            else if(input.equalsIgnoreCase("Show"))  show_other_clients();
            else if(input.startsWith("Talking")) {
                String arr[]=parser(input);
                String msg="";
                for(int i=2;i<arr.length;i++) msg=msg+" "+arr[i];
                
                System.out.println("Final MSG "+msg);
                
                talking_with_another(arr[1], msg);
            }
            else if(input.startsWith("Invite")) {
                String arr[]=parser(input);
                send_invitation(arr[arr.length-1]);
            }
            else if(input.startsWith("Confirm")) {
                String arr[]=parser(input);
                confirm_invitation(arr[arr.length-1] );    
            }
            else if(input.startsWith("opp_Number")||input.startsWith("opp_Score")||input.startsWith("my_Score"))
            {
                send_data_to_client(input);
            }
            else if(input.startsWith("you_lose")||input.startsWith("you_win"))
            {
                send_data_to_client(input);
            }
            else if(input.startsWith("WIN")||input.startsWith("LOSE")||input.startsWith("TIE")) send_data_to_client(input);
            else if(input.startsWith("unmap") ) remove_invitation();
            else dout.writeUTF("Okay, Got it send some more :) ");
        }catch(IOException e) {
            System.out.println("Error Writing Data to Client");
        }
    }
    
    private void send_data_to_client(String str) throws IOException
    {
        String arr[]=parser(str);
        String other_id = arr[1];
        String msg = arr[2];
        
        DataOutputStream dos=getOutputStream(other_id);
        System.out.println("sendDataToClient "+ arr[0]+" "+arr[1]+" "+arr[2]);
        if(arr[0].startsWith("opp_Number")) dos.writeUTF("opp_Number "+myIp +" "+ msg);
        else if(arr[0].startsWith("opp_Score")) dos.writeUTF("opp_Score "+myIp +" "+ msg);
        else if(arr[0].startsWith("my_Score")) dos.writeUTF("my_Score "+myIp +" "+ msg);
        else dos.writeUTF(arr[0]+" "+myIp);
    }

    private void show_other_clients()
    {
        System.out.println("Server heard the Order Client needs to see other Clients");
        String ans="SHOW ";
        Vector<Socket> v= server2.sck_list;
        
        for(int i=0;i<v.size();i++)
        {
            ans+=v.get(i).getInetAddress().toString()+"\n";
        }
        System.out.println("Server Saying \n"+ans);
        
        try {
            dout.writeUTF(ans);
        } catch (IOException ex) {
        
        }
    }
    
    private DataOutputStream getOutputStream(String otherId)
    {
        String str;
        for(int i=0;i<server2.sck_list.size();i++)
        {
            str=server2.sck_list.get(i).getInetAddress().toString();
            if( otherId.equals(str) ) return server2.DOS.get(i);
        }
        return null;
    }
    
    private void send_invitation(String other_id)
    {
        System.out.println("Client Wants to Talk With "+other_id);
        DataOutputStream dosOther;
        try {
            if( server2.M.get(other_id) ==1 || server2.M.get( myIp )==1 ) 
                dout.writeUTF("NO");
            else {
                   
                dosOther=getOutputStream(other_id);
                dosOther.writeUTF( "IncomingRequest "+myIp );
             
            }
            
        } catch (IOException ex) {
            System.out.println("Sending Invitation Failed");
        }
    }
    private void confirm_invitation(String other_id)
    {
        DataOutputStream dosOther=getOutputStream(other_id);
        try {
            dosOther.writeUTF("PlayBetween "+other_id+" "+myIp ) ;
            dout.writeUTF("PlayBetween "+myIp+" "+other_id);
            dout.writeUTF("Start "+myIp);
            dosOther.writeUTF("Wait "+ other_id);
            
            server2.M.put(other_id, 1);
            server2.M.put( myIp , 1);

            
        }catch(IOException e) {
            System.out.println("Play Matching ERROR");
        }
    }
    private void remove_invitation()
    {
        server2.M.put(myIp, 0);
    }
    private void talking_with_another(String other_id, String msg){
        
        System.out.println("Trying to pass the message "+msg +" to "+other_id);
        DataOutputStream dos=getOutputStream(other_id);
        
        try {
            
            dos.writeUTF("MessageFrom "+myIp +" "+ msg);
        }catch(IOException e) {
            System.out.println("Couldnt Pass the message");
        }
    }
    private void setUserNameForThisIp(String str)
    {
        String arr[]=parser(str);
        String tmp="";
        for(int i=1;i<arr.length;i++) tmp+=arr[i]+" ";
        this.User_name=tmp;
        
        server2.IpWithUser.put(myIp, this.User_name);
    }
}
