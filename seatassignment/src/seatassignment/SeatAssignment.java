/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seatassignment;

import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author imtiaz
 */

public class SeatAssignment {

   
    public Vector<CenterData>cData = new Vector<CenterData>();
    public Vector<String>GroupName = new Vector<String>();
    public Vector<Integer>GroupSeat = new Vector<Integer>();
    
    private void takeInput()
    {
//        System.out.println("How Many Center ?");
        Scanner scan = new Scanner(System.in);
        String str;
        
        int n = scan.nextInt() , r , val;
        
        for(int i=0;i<n;i++)
        {
            String centerName ;//= scan.next();
            Vector<String>roomName = new Vector<>();
            Vector<Integer>roomSpace = new Vector<>();

//            System.out.println("Center Name");
            centerName=scan.next();
            
//            System.out.println("Total Rooms");
            r=scan.nextInt();
            
//            System.out.println("Room Name , Available Seat");
            for(int j=0;j<r;j++)
            {
                str=scan.next();
                val=scan.nextInt();
                roomName.add(str);
                roomSpace.add(val);
            }
            cData.add(new CenterData(centerName, roomSpace, roomName) );
        }
        
//        for(int i=0;i<cData.size();i++)
//        {
//            System.out.println("Center Name "+ cData.get(i).centerName);
//            for(int j=0;j<cData.get(i).rooms;j++)
//            {
//                System.out.println("Room "+(cData.get(i)).roomName.get(j) +"  Space "+ (cData.get(i)).roomSpace.get(j));                
//            }
//        }
        
//        System.out.println("How Many Groups ?");
        n=scan.nextInt();
        for(int i=0;i<n;i++)
        {
            str=scan.next();
            val=scan.nextInt();
            
            GroupName.add(str);
            GroupSeat.add(val);
        }
        
//        System.out.println("Total Groups "+GroupName.size());
    }
    public static void main(String[] args) {
        // TODO code application logic here
        SeatAssignment ob = new SeatAssignment();
        ob.takeInput();
        
        assignmentSoution asob;
        asob= new assignmentSoution(ob.cData, ob.GroupName, ob.GroupSeat );
        
        int loss=asob.execute();
        System.out.println("Final Loss "+loss);
        if(loss<10000)
        {
            Vector<CenterData>output = asob.processOutput();

            for(int i=0;i<output.size();i++)
            {
    //            System.out.println("Center "+output.get(i).centerName +" id "+output.get(i).centerId);
                Vector<String>roomName=output.get(i).roomName;
                Vector<Integer>TotSeat = output.get(i).roomSpace;
                Vector<Integer>UsedSeat = output.get(i).roomUsed;
                Vector<Integer>GroupId = output.get(i).groupId;
                Vector<String>GroupName = ob.GroupName;
                
//                System.out.println(roomName.size() + " "+TotSeat.size() + " " + UsedSeat.size() + " "+ GroupId.size() + " "+GroupName.size() );
                for(int j=0;j<roomName.size();j++)
                {
                    if(GroupId.get(j)!=-1) System.out.println(output.get(i).centerName+" "+roomName.get(j)+" "+TotSeat.get(j) + " "+UsedSeat.get(j) +" "+GroupName.get( GroupId.get(j) ) );
                    else System.out.println(output.get(i).centerName+" "+roomName.get(j)+" "+TotSeat.get(j) + " NA "+ "NA" );
                }
            }
        }else System.out.println("Assignment Failed");
        
        
        
//        for(int i=0;i<asob.SolGroup.size();i++)
//        {
//            System.out.println("Group Number "+ asob.SolGroup.get(i) + " Center " + asob.SolCenter.get(i)  );
//        }
//        
//        System.out.println("Conflicting Center");
//        for(int i=0;i<asob.SolConflictCenter.size();i++)
//        {
//            System.out.println("Center Index "+ asob.SolConflictCenter.get(i).centerId );
//            System.out.println("Room Configuration");
//            
//            for(int j=0;j<asob.SolConflictCenter.get(i).groupId.size();j++)
//            {
//                System.out.println("Room "+j+ " Group "+asob.SolConflictCenter.get(i).groupId.get(j) +" Used Seat " + asob.SolConflictCenter.get(i).roomSpace.get(j) );
//            }
//        }
    }
    
}
