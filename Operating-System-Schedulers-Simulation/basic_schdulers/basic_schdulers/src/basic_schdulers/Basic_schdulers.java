package basic_schdulers;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class Basic_schdulers {
    
    public static int now_time=0;
    public static void main(String[] args) {
        
        
        first_come_first_serve fcfs=new first_come_first_serve(5);
        fcfs.execute();
        
//        shortestjobfirst_preemptive sjf= new shortestjobfirst_preemptive(3);
//        sjf.execute();
//
//        round_robin rb = new round_robin(10, 5);
//        rb.execute();
        
//          round_robin_priority rbp = new round_robin_priority(10, 5);
//          rbp.execute();
        
//        Bankersalgorithm bk= new Bankersalgorithm(3, 5);
//        bk.generate();
        
//          multilevel_feedback mfd= new multilevel_feedback(4);
//          mfd.execute();
            
//        DiningPhilosophers dps= new DiningPhilosophers();
//        dps.execute();
        
        
      }
}
