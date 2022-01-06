
package clientapplication;

import java.io.DataInputStream;
import java.io.IOException;

public class notification_from_server extends Thread{
    
    DataInputStream dis;
    public notification_from_server(DataInputStream ddi) {
        dis=ddi;
        this.start();
    }
    

    String str;
    public void run() {
        while(true) {
            str=null;
            try {
                if(dis.available()>0)
                {
                    str=dis.readUTF();
                    System.out.println("Heard From Server "+str);
                    ClientView.todo.take_action(str);
                }
            }catch(IOException e) {
                System.out.println("Thread Can't get the Input from server");
            }

            try {
                Thread.sleep(1000);
            }catch(InterruptedException e) {
                System.out.println("Sleeping ERROR FOR Notification From server thread");
            }
        }
    }
    
}
