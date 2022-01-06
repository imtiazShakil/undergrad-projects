
package basic_schdulers;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class multilevel_feedback {

    Random  generator = new Random();
    Queue<Integer> Q= new LinkedList<Integer>();
    
    public multilevel_feedback(int max_que_size)
    {
        for(int i=0;i<max_que_size;i++) {
            int typ=generator.nextInt(4);
            Q.add(typ);                  
        }
        
    }

    
   public void execute()
   {
       System.out.println("MultiLevel Feedback Queue Using FCFS Method");
       for(int i=0;i<Q.size() ;i++) {
           int typ=Q.remove();
            if(typ==0)  {first_come_first_serve fff= new first_come_first_serve(10);fff.execute();}
            if(typ==1)  {round_robin rr = new round_robin(10, 5); rr.execute();}
            if(typ==2) {round_robin_priority rrp = new round_robin_priority(10, 5); rrp.execute();}
            if(typ==3) {shortestjobfirst_preemptive sjf = new shortestjobfirst_preemptive(10); sjf.execute(); }
       
            try {
               Thread.sleep(500);
            }catch(InterruptedException e) {
            }
       
       }
   }
}
