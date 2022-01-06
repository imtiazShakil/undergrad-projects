
package basic_schdulers;

import java.util.Random;

public class Bankersalgorithm {
    
    private int max_resources,max_process;
    private int allocation[][]; 
    private int need[][];
    private int availabe[];
    private int col[];
    Random rand = new Random();
    
    public Bankersalgorithm(int max_resource , int max_process) {
        this.max_resources=max_resource;
        this.max_process=max_process;
        
        allocation=new int[max_process+2][max_resources+2];
        need=new int[max_process+2][max_resources+2];
        availabe=new int [max_resources+2];
        col=new int [max_process+2];for(int i=0;i<=max_process;i++) col[i]=1;
    }
    
    public void generate() {
        
        for(int i=0;i<max_process;i++) for(int j=0;j<max_resources;j++) need[i][j]=rand.nextInt(20);
        for(int i=0;i<max_process;i++) for(int j=0;j<max_resources;j++) {
            
            int tot=need[i][j];
            allocation[i][j]=Math.min( rand.nextInt(20), tot);
        }
        for(int i=0;i<max_resources;i++) availabe[i]= rand.nextInt(50);
        
        System.out.println("Allocation");
        for(int i=0;i<max_process;i++)  {
            System.out.print("Process "+i+"  ");
             for(int j=0;j<max_resources;j++) System.out.print(" "+allocation[i][j]);
                        System.out.println("");
                 }
        
        
        System.out.println("\n\n");
        System.out.println("Need");
        for(int i=0;i<max_process;i++)  {
            System.out.print("Process "+i+"  ");
             for(int j=0;j<max_resources;j++) System.out.print(" "+need[i][j]);
                        System.out.println("");
                 }
        
        
        System.out.println("\n\n");
        System.out.println("Available Now");
        for(int i=0;i<max_process;i++) System.out.print(" "+ availabe[i]);
        System.out.println("\n\n");
        
        check_safe_state();
    }
    
    public void check_safe_state()
    {
        int cnt=0;
        for(int i=0;i<max_process;i++)  {
            if(col[i]==1) {
                cnt++;
                int flag=1;
                for(int j=0;j<max_resources;j++)  if(availabe[j] < need[i][j]) flag=0;
                if(flag==1) 
                {
                    for(int j=0;j<max_resources;j++) {availabe[j]-=need[i][j] ; allocation[i][j]+=need[i][j];  }
//                    P4: new allocated 4, 3, 3 and new Available is now: 3, 1, 2
                    System.out.print("P"+i+": new allocated"); // 4, 3, 3 and new Available is now: 3, 1, 2");
                    for(int j=0;j<max_resources;j++) System.out.print(" "+allocation[i][j] +",");
                    System.out.print(" and new Available is now:");
                    for(int j=0;j<max_resources;j++) System.out.print(" "+availabe[j]+",");System.out.println("");
                
                    System.out.print("– But then P"+i +" releases its resource instances and Available is now: ");
                    
                    for(int j=0;j<max_resources;j++) {availabe[j]+=allocation[i][j];System.out.print(" "+availabe[j] );}
                    System.out.println("\n");
                    col[i]=0;
                    check_safe_state();
                    return; 
                    
                }
            }
        }
        if(cnt==0) System.out.println("So there is no deadlock…. Such a sequence is safe!");
        else System.out.println("Safe State couldn't be found");
    }
    
    
}
