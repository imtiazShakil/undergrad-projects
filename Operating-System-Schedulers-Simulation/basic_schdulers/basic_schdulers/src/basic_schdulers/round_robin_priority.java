
package basic_schdulers;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class round_robin_priority{
    int max_process, time_quantum;
    Comparator<basic_process> comparator = new round_robin_priority_comparator(); 
    PriorityQueue<basic_process> pri_Q= new PriorityQueue<basic_process>(1000, comparator);
    
    public round_robin_priority(int mx_process , int time_quantum) {
        max_process=mx_process;
        this.time_quantum=time_quantum;
        create_process cp=new create_process(mx_process,pri_Q);
        cp.t.start();
    }
    public void execute( )
    {
        System.out.println("Starting Schduler  Round Robin with Priority");
        int arr[]=new int[max_process+5];
        for(int i=0;i<=max_process;i++) arr[i]=0;
        
        int baicha_ase=0;
        Random  generator = new Random();
        basic_process prev=null;
        while(max_process>0)
        {
            while( !pri_Q.isEmpty())
            {
                basic_process ob = pri_Q.remove();
                
                baicha_ase=0;
                for(int i=0;i<=max_process;i++) if(arr[i]==1) baicha_ase=1;
                
                if(baicha_ase==0 )
                {
                    while(ob.arrival_time>Basic_schdulers.now_time) Basic_schdulers.now_time++;
                }
                if(baicha_ase==1 && ob.arrival_time>Basic_schdulers.now_time) {  pri_Q.add(ob);continue; }
                
                if(prev!=null && ob!=prev && prev.burst_time>0 ) prev.give_up();
                int at=ob.arrival_time;
                
                int b=ob.burst_time;
                int allocated=Math.min(time_quantum,b);
                
                ob.get_cpu( allocated  );
                if(ob.burst_time==0) {arr[max_process]=0;max_process-- ;ob.finished( Basic_schdulers.now_time +allocated  );}
                else {pri_Q.add(ob);arr[max_process]=1;}
                
                Basic_schdulers.now_time+=allocated;
                
                try{
                    int want_to_sleep=generator.nextInt(155);
                    Thread.sleep(want_to_sleep);
                }catch(InterruptedException e)
                {}
            }
        }
        System.out.println("Round Robin Priority Done");
    }
}

class round_robin_priority_comparator implements Comparator<basic_process> {
    
    public int compare(basic_process a, basic_process b)
    {
        if(Basic_schdulers.now_time>a.arrival_time && Basic_schdulers.now_time>b.arrival_time)
        {
                if(a.priority<b.priority) return -1;
                else if(a.priority>b.priority) return 1;
                else return 0;
        }
        
        if(a.arrival_time != b.arrival_time)
        {
                if(a.arrival_time<b.arrival_time) return  -1;
                else if(b.arrival_time<a.arrival_time) return 1;
                else return 0;
        }
        else
        {
                if(a.priority<b.priority) return -1;
                else if(a.priority>b.priority) return 1;
                else return 0;
                
         }

    }
}