
package basic_schdulers;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class first_come_first_serve {

    int max_process;
    Queue<basic_process> myQueue = new LinkedList<basic_process>();
    public first_come_first_serve(int mx_process) {
        max_process=mx_process;
        create_process cp=new create_process(mx_process,myQueue);
        cp.t.start();
    }
    
    
    public void execute( )
    {
        System.out.println("Staring Schduler First Come First Serve Method");
        Random  generator = new Random();
        while(max_process>0)
        {
            while( !myQueue.isEmpty())
            {
                max_process--;
                
                basic_process ob = myQueue.remove();
                
                int at=ob.arrival_time;
                while(at>Basic_schdulers.now_time) Basic_schdulers.now_time++;
                
                int b=ob.burst_time;
                ob.get_cpu(  ob.burst_time );
                ob.finished( Basic_schdulers.now_time +b  );
                
                Basic_schdulers.now_time+=b;
                
                try{
                    int want_to_sleep=generator.nextInt(155);
                    Thread.sleep(want_to_sleep);
                }catch(InterruptedException e)
                {}
            }
        }
        System.out.println("FCFS Done");
    }
    
}
