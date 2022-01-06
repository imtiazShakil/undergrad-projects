
package basic_schdulers;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class round_robin {
    int max_process, time_quantum;
    Queue<basic_process> Q = new LinkedList<basic_process>();
    
    public round_robin(int mx_process , int time_quantum) {
        max_process=mx_process;
        this.time_quantum=time_quantum;
        create_process cp=new create_process(mx_process,Q);
        cp.t.start();
    }
    
    public void execute( )
    {
        System.out.println("Staring Schduler Round Robin Method");
        int arr[]=new int[max_process+5];
        for(int i=0;i<=max_process;i++) arr[i]=0;
        
        int baicha_ase=0;
        Random  generator = new Random();
        basic_process prev=null;
        while(max_process>0)
        {
            while( !Q.isEmpty())
            {
                basic_process ob = Q.remove();
                
                baicha_ase=0;
                for(int i=0;i<=max_process;i++) if(arr[i]==1) baicha_ase=1;
                
                if(baicha_ase==0 )
                {
                    while(ob.arrival_time>Basic_schdulers.now_time) Basic_schdulers.now_time++;
                }
                if(baicha_ase==1 && ob.arrival_time>Basic_schdulers.now_time) {  Q.add(ob);continue; }
                
                if(prev!=null && ob!=prev && prev.burst_time>0 ) prev.give_up();
                int at=ob.arrival_time;
                
                int b=ob.burst_time;
                int allocated=Math.min(time_quantum,b);
                
                ob.get_cpu( allocated  );
                if(ob.burst_time==0) {arr[max_process]=0;max_process-- ;ob.finished( Basic_schdulers.now_time +allocated  );}
                else {Q.add(ob);arr[max_process]=1;}
                
                Basic_schdulers.now_time+=allocated;
                
                try{
                    int want_to_sleep=generator.nextInt(155);
                    Thread.sleep(want_to_sleep);
                }catch(InterruptedException e)
                {}
            }
        }
        System.out.println("Round Robin Done");
    }
}

