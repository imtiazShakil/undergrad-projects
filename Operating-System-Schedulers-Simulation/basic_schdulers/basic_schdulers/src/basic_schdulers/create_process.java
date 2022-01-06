
package basic_schdulers;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;


public class create_process implements Runnable{

    Random generator = new Random();
    private int total_cpu_no;
    Thread t;
    Queue<basic_process> myQueue ;
    PriorityQueue<basic_process> pri_Q;
    int type=0;
    
    public create_process( int max_cpu   ,Queue<basic_process>Q )  {
        total_cpu_no= max_cpu ;
        t=new Thread(this," Process Creator " );
        myQueue=Q;
        System.out.println("Process Created 1");
        type=1;
    }
    public create_process(int max_cpu ,PriorityQueue<basic_process>Q )
    {
        total_cpu_no= max_cpu ;
        t=new Thread(this," Process Creator " );
        pri_Q=Q;
        System.out.println("Process Created 2");
        
        type=2;
    }
    
    public void run()
    {
        int last_time=0,arrival_time;
        for(int i=1;i<=total_cpu_no;i++)
        {
            int for_priority=generator.nextInt(10);
            arrival_time=Basic_schdulers.now_time+generator.nextInt(20);
            if(arrival_time<last_time) arrival_time+= (last_time-arrival_time) + generator.nextInt(20);
            
            basic_process  ob= new basic_process( Integer.toString(i) ,arrival_time  ,for_priority );
            last_time=arrival_time;
            
            if(type==1) myQueue.add(ob);
            else if(type==2) pri_Q.add(ob);

            try {
                int want_to_sleep=generator.nextInt(100);
                t.sleep(want_to_sleep);
            }
            catch(InterruptedException e)
            {
                System.out.println(e.toString());
            }
        }
        
        
    }
    
}
