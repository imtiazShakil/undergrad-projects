
package basic_schdulers;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class shortestjobfirst_preemptive {
    int max_process;
    Comparator<basic_process> comparator = new sjf_comparator();
    
    PriorityQueue<basic_process> pri_Q= new PriorityQueue<basic_process>(1000, comparator);
    
    public shortestjobfirst_preemptive(int mx_process) {
        max_process=mx_process;
        create_process cp=new create_process(mx_process,pri_Q);
        cp.t.start();
    }
    
    public void execute() {
        Random  generator = new Random();
        System.out.println("Starting Schduler Shortest Job First Method");
//        try{
//        Thread.sleep(100);
//        }catch(InterruptedException e) {}
//        
        basic_process prev=null;
        while(max_process>0)
        {
            while( pri_Q.size()!=0 )
            {
//                System.out.println("Now time "+Basic_schdulers.now_time);
                
                basic_process ob = pri_Q.remove();
                if(prev!=null && ob!=prev && prev.burst_time>0 ) prev.give_up();
                int at=ob.arrival_time;
                while(at>Basic_schdulers.now_time) Basic_schdulers.now_time++;
                
                ob.get_cpu(  1 );
                if(ob.burst_time==0)  {max_process--; ob.finished( Basic_schdulers.now_time +1  );}
                else pri_Q.add(ob);                 
                prev=ob;
                Basic_schdulers.now_time+=1;
                try{
                    int want_to_sleep=generator.nextInt(200);
                    Thread.sleep(want_to_sleep);
                }catch(InterruptedException e)
                {}
            }
        }
        System.out.println("SJF Done");
    }
}

class sjf_comparator implements Comparator<basic_process> {
    
    public int compare(basic_process a, basic_process b)
    {
//        if(a.arrival_time>Basic_schdulers.now_time && b.arrival_time>Basic_schdulers.now_time) return  0;
        if(Basic_schdulers.now_time>a.arrival_time && Basic_schdulers.now_time>b.arrival_time)
        {
            if(a.burst_time<b.burst_time) return  -1;///dont swap
            else if(a.burst_time>b.burst_time)return  1;
            else return  0;
        }
        else 
        {
            if(a.arrival_time<b.arrival_time) return  -1;
            else if(b.arrival_time<a.arrival_time) return 1;
            else return 0;
                    
        }
    }
}