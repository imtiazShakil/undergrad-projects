package basic_schdulers;

import java.util.Random;

public class basic_process {

    public String id;
    public int max_burst_time=50;
    public int burst_time,arrival_time ,priority,Mem,agp,hdd;
    Random rand = new Random();
    
    public basic_process(String name , int at , int priority  ) {
        id = name;
        burst_time = rand.nextInt(max_burst_time) + 1;
        this.priority = priority;
        arrival_time=at;
        Mem=rand.nextInt(1000)+ 1;
        agp=rand.nextInt(500)+ 1;
        hdd=rand.nextInt(113117)+1;
        System.out.println( "Process "+ id +" is created [Id: " + id +", BT:" + burst_time +", AT: " + arrival_time +", P:" + this.priority +", Resource[Mem:"+ Mem +", AGP:"+agp +", Harddisk:"+ hdd + "]]" );
    }
    
    
    public void  get_cpu(int get_time)
    {
        int mem2=Math.min(rand.nextInt(1000),Mem ); 
        int agp2=Math.min( rand.nextInt(500) , agp );
        int hdd2=Math.min(rand.nextInt(113117) , hdd);
        
        System.out.println("Process "+id+" get CPU for "+get_time+" ms [id:"+id+", NT:"+burst_time+", P:"+this.priority+", AllocRes[Mem:"+mem2+", AGP:"+agp2+", Harddisk:"+hdd2+"], NRes[Mem:"+(Mem-mem2)+", AGP:"+(agp-agp2)+", Harddisk:"+(hdd-hdd2)+"]]");
        burst_time-=get_time;
    }
    
    public void give_up()
    {
        int mem2=Math.min(rand.nextInt(1000),Mem ); 
        int agp2=Math.min( rand.nextInt(500) , agp );
        int hdd2=Math.min(rand.nextInt(113117) , hdd);
        
        System.out.println("Process "+id+" give up CPU   [id:"+id+", NT:"+burst_time+", P:"+this.priority+", AllocRes[Mem:"+mem2+", AGP:"+agp2+", Harddisk:"+hdd2+"], NRes[Mem:"+(Mem-mem2)+", AGP:"+(agp-agp2)+", Harddisk:"+(hdd-hdd2)+"]]");
        
    }
    public void finished(int now_time)
    {
        System.out.println("Process "+id +" is finished at time "+now_time+" msec.");
    }
    
}
