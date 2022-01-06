/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seatassignment;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author imtiaz
 */
class Pair
{
    public int fs;
    public int sc;
    public Pair(int fs, int sc) {
        this.fs = fs;
        this.sc = sc;
    }
}
public class assignmentSoution {
    private Vector<CenterData> v;
    private Vector<String> groupName;
    private Vector<Integer> groupSeat;
    
    
    private Vector<Integer>SolGroup;
    private Vector<Integer>SolCenter;
    private Vector<CenterData>SolConflictCenter;
    
    
    private Vector<Integer>Room;
    private Vector<Integer>Sol;
    private Vector<Integer>SolSeatLeft;
    
    private int mx=16 , mxWaste=2000 , mxRoom=80 , mxRemStudent=20000;
    private int additionalSeatPerCenter=5;
    private int FinalLoss=0;
    
    private int[][] dp = new int[(1<<mx) + 10][mxWaste];
    private int[][] dp2= new int[mxRoom+10][mxRemStudent];
    
    HashMap<Vector<Integer>, Vector<Integer>> cache ;
    
    public assignmentSoution(Vector<CenterData> v, Vector<String> groupName, Vector<Integer> groupSeat) {
        this.v = v;
        this.groupName = groupName;
        this.groupSeat = groupSeat;
        
    }
    
    int execute()
    {
        Room= new Vector<Integer>();
        Sol= new Vector<Integer>();
        SolSeatLeft = new Vector<Integer>();
        
        
        SolGroup = new Vector<Integer>();
        SolCenter = new Vector<Integer>();
        SolConflictCenter = new Vector<CenterData>();
        
        cache = new HashMap< Vector<Integer> , Vector<Integer> >();
        
        for(int i=0;i< (1<<v.size()) + 10 ;i++)
                for(int k=0;k<mxWaste;k++) dp[i][k]=-1;//dp[i][j][k]=-1;
        
        int mask=0;
        for(int i=0;i<v.size();i++) mask|=(1<<i);
        
        
        FinalLoss=rec(mask,0);
        getFinalSolution(mask, 0);
        
        return FinalLoss;
        
    }
    int rec(int mask , int waste)
    {
        if(waste>=mxWaste) return (1<<20);
        int ret=dp[mask][waste];
        if(ret!=-1) return ret;
        
        ret=(1<<20);
        
        int curGroup=-1,remStudent=0 , total=0;
        for(int i=0;i<v.size();i++) if( (mask&(1<<i))==0 ) total += ( v.get(i).totSeat - additionalSeatPerCenter );
        total-=waste;
        
        for(int i=0;i<groupName.size();i++)
        {
            if( groupSeat.get(i)>total )
            {
                
                curGroup=i;
                remStudent=groupSeat.get(i)-total;
                break;
            }
            else total-=groupSeat.get(i);
            
        }
        if(curGroup==-1) return 0;
//        System.out.println("Current Group "+curGroup +" RemainingStudent "+remStudent);
        

        for(int i=0;i<v.size();i++)
        {
            if( (mask&(1<<i))!=0 )
            {
                int totalCost=0,tmpCurGroup=curGroup,tmpRemStudent=remStudent;
                if(v.get(i).totSeat<=tmpRemStudent) ret=Math.min(ret , rec(mask^(1<<i) , waste) );
                else {
                    
                    int isAdditionalseatGiven=0;
                    Vector<Integer>vv = new Vector<Integer>();
                    for(int p=0;p<v.get(i).rooms;p++) vv.add(1);
                    
//                    System.out.println("Problematic Center" + i);
//                    System.out.println("Current Group "+curGroup +" Remaining " + remStudent);
                    while(true)
                    {
                        int cost=makeIthappen(i, tmpRemStudent,vv );
                        totalCost+=cost;

                        if(isAdditionalseatGiven==0) {
                            for(int p=0;p<SolSeatLeft.size();p++) {
                                if(SolSeatLeft.get(p)>=additionalSeatPerCenter)
                                {
                                    isAdditionalseatGiven=1;
                                    totalCost-=additionalSeatPerCenter;
                                    break;
                                }
                            }

                        }
                        
                        int allRoomsfilled=1;
                        for(int p=0;p<v.get(i).rooms;p++) if(vv.get(p)==1) {allRoomsfilled=0;break;}
                        if(allRoomsfilled==1) break;

                        

                        tmpCurGroup++;
                        if(tmpCurGroup>=groupSeat.size()) break;
                        tmpRemStudent=groupSeat.get(tmpCurGroup);

                        
                        
                    }
//                    System.out.println("Found Total Cost "+totalCost);
                    ret=Math.min(ret, rec(mask^(1<<i) , waste+totalCost) + totalCost );
                }
            }
        }
        dp[mask][waste]=ret;
        return ret;   
    }
    
    void getFinalSolution(int mask , int waste)
    {
//        System.out.println("State "+mask+" waste "+waste +" ret "+rec(mask,waste) );
//        if(mask==0) return ;
        if(waste>=mxWaste) return;
        int ret=rec(mask , waste);
        
        int curGroup=-1,remStudent=0 , total=0;
        for(int i=0;i<v.size();i++) if( (mask&(1<<i))==0 ) total += ( v.get(i).totSeat - additionalSeatPerCenter );
        total-=waste;
        
        for(int i=0;i<groupName.size();i++)
        {
            if( groupSeat.get(i)>total )
            {
                curGroup=i;
                remStudent=groupSeat.get(i)-total;
                break;
            }
            else total-=groupSeat.get(i);
            
        }
        if(curGroup==-1) return ;
//        System.out.println("Current Group "+curGroup +" RemainingStudent "+remStudent);
        
        
        for(int i=0;i<v.size();i++)
        {
            if( (mask&(1<<i))!=0 )
            {
                int totalCost=0 , tmpCurGroup=curGroup , tmpRemStudent=remStudent;
                if(v.get(i).totSeat<=tmpRemStudent) {
                    if( ret==rec(mask^(1<<i) , waste) ) 
                    {
                        SolGroup.add(tmpCurGroup);
                        SolCenter.add(i);
                        
                        getFinalSolution(mask^(1<<i), waste);
                        return;
                    }
                }
                else {
                    
                    CenterData conflictCenter = new CenterData(i);
                    
                    int isAdditionalseatGiven=0;
                    Vector<Integer>vv = new Vector<Integer>();
                    for(int p=0;p<v.get(i).rooms;p++) 
                    {
                        vv.add(1);///this rooms are initialized to be valid
                        conflictCenter.groupId.add(-1); //initialize
                        conflictCenter.roomSpace.add(-1); //initialize
                    }
                    
                    while(true)
                    {
                        int cost=makeIthappen(i, tmpRemStudent,vv );
                        totalCost+=cost;
                        
                        for(int p=0;p<Sol.size();p++)
                        {
                            //Sol.get(p) gives the room index
                            //in that index i am assigining curGroup
                            int rm=Sol.get(p);
                            conflictCenter.groupId.set( rm , tmpCurGroup);
                            conflictCenter.roomSpace.set( rm , v.get(i).roomSpace.get( rm ) - SolSeatLeft.get(p) );
                        }
                        
                        
                        
                        if(isAdditionalseatGiven==0) {
                            for(int p=0;p<SolSeatLeft.size();p++) {
                                if(SolSeatLeft.get(p)>=additionalSeatPerCenter)
                                {
                                    isAdditionalseatGiven=1;
                                    //conflictCenter.roomSpace.set(Sol.get(p),conflictCenter.roomSpace.get( Sol.get(p) )-additionalSeatPerCenter  );
                                    totalCost-=additionalSeatPerCenter;
                                    break;
                                }
                            }
                        }
                        
                        int allRoomsfilled=1;
                        for(int p=0;p<v.get(i).rooms;p++) if(vv.get(p)==1) {allRoomsfilled=0;break;}
                        if(allRoomsfilled==1) break;

                        

                        tmpCurGroup++;
                        if(tmpCurGroup>=groupSeat.size()) break;
                        tmpRemStudent=groupSeat.get(tmpCurGroup);

                        
                        
                    }
//                    System.out.println("Found Total Cost "+totalCost);
                    if( ret==rec(mask^(1<<i) , waste+totalCost) + totalCost )
                    {
                        int unusedRoom=0;
                        for(int p=0;p<vv.size();p++) if(vv.get(p)==1 && v.get(i).roomSpace.get(p)>=additionalSeatPerCenter) {unusedRoom=1;break;}
                        
                        if(isAdditionalseatGiven==0 && unusedRoom==0)
                        {
                            int rm;
                            for(int p=0;p<Sol.size();p++)
                            {
                                //Sol.get(p) gives the room index
                                //in that index i am assigining curGroup
                                rm=Sol.get(p);
                                if(v.get(i).roomSpace.get(rm)>=additionalSeatPerCenter ) 
                                {
                                    conflictCenter.roomSpace.set( rm , v.get(i).roomSpace.get( rm ) - SolSeatLeft.get(p) - additionalSeatPerCenter );
                                    break;
                                }
                            }
                        }
                        SolConflictCenter.add(conflictCenter);
                        getFinalSolution(mask^(1<<i), waste+totalCost);
                        return;
                    }
                }
            }
        }
        
        return ;   
    }
    
    int makeIthappen(int center , int RemStudent , Vector<Integer>valid )
    {
        Room.clear();Sol.clear();SolSeatLeft.clear();
        int seatAvailable=0;
        
        for(int i=0;i<v.get(center).rooms;i++) if(valid.get(i)==1)  {
                Room.add( v.get(center).roomSpace.get(i) );
                seatAvailable+=v.get(center).roomSpace.get(i);
        }
        
        RemStudent=Math.min(RemStudent, seatAvailable);
        int waste;
        
        Room.add(RemStudent);
        if(cache.containsKey(Room))
        {
            Vector<Integer>cacheVal=cache.get(Room);
            waste=cacheVal.get(0);
            for(int i=1;i<=cacheVal.size()/2 ;i++) Sol.add(cacheVal.get(i));
            for(int i=cacheVal.size()/2 + 1; i<cacheVal.size();i++) SolSeatLeft.add(cacheVal.get(i));
        }
        else {
            Room.removeElementAt( Room.size()-1 );
            
            for(int i=0;i<Room.size() + 5;i++) for(int j=0;j<RemStudent+10;j++) dp2[i][j]=-1;

            waste=rec2(0,RemStudent);
            getSolution(0, RemStudent);
            
            Vector<Integer>Key = new Vector<Integer>();
            for(int i=0;i<Room.size();i++) Key.add(Room.get(i));Key.add(RemStudent);
            
            Vector<Integer>cacheVal = new Vector<Integer>();
            cacheVal.add(waste);
            for(int i=0;i<Sol.size();i++) cacheVal.add( Sol.get(i));
            for(int i=0;i<SolSeatLeft.size();i++) cacheVal.add( SolSeatLeft.get(i));
            
            cache.put(Key, cacheVal);
        }
        Vector<Integer>RoomId =  new Vector<Integer>();
        
        int cur=0 , paisi=-1;
        for(int i=0;i<Sol.size();i++)
        {
            for( ;cur<v.get(center).rooms;cur++ )
            {
                if(valid.get(cur)==1) paisi++;
                if(paisi==Sol.get(i)) break;
            }
            RoomId.add(cur);
//            System.out.println("Picked room "+cur+" Seats "+Sol.get(i) +" wasted  "+SolSeatLeft.get(i) );
            valid.set(cur, 0);
            cur++;
        }
        Sol=RoomId;
        return waste;
    }
    
    int rec2(int roomId,int studentLeft)
    {
        if(studentLeft<=0) return 0;
        if(roomId>=Room.size()) return (1<<20);
        
        int ret=dp2[roomId][studentLeft];
        if(ret!=-1) return  ret;
        
        ret=(1<<20);
        
        if(Room.get(roomId)<=studentLeft) ret=Math.min( ret,  rec2( roomId+1 , studentLeft-Room.get(roomId) )  );
        else ret=Math.min( ret , rec2(roomId+1 , 0 ) + Math.abs(Room.get(roomId)-studentLeft ) );
        
        ret=Math.min(ret , rec2(roomId+1 , studentLeft) );
        
        
        dp2[roomId][studentLeft]=ret;
        return ret;
    }
    
    void getSolution(int roomId , int studentLeft)
    {
        if(studentLeft<=0) return ;
        if(roomId>=Room.size()) return ;
        
        int ret=rec2(roomId ,studentLeft );
        
        if(Room.get(roomId)<=studentLeft)
        {
            if( ret== rec2( roomId+1 , studentLeft-Room.get(roomId) ) )
            {
                Sol.add(roomId);
                SolSeatLeft.add(0);
                
                getSolution(roomId +1, studentLeft - Room.get(roomId));
                return;
            }
        }
        else if( ret==rec2(roomId+1 , 0 ) + Math.abs(Room.get(roomId)-studentLeft ) )
        {
                Sol.add(roomId);
                SolSeatLeft.add( Math.abs(Room.get(roomId)-studentLeft ) );
                
                getSolution(roomId +1, 0);
                return;
        }
        if( ret==rec2(roomId+1 , studentLeft) )
        {
            getSolution(roomId + 1, studentLeft);
            return;
        }
        
        return;
    }
    
    

    Vector<CenterData> processOutput()
    {
        
        Vector<CenterData> FinalOutput = new Vector<CenterData>();
        
        int centerId,groupId;
        
        for(int i=0;i<SolCenter.size();i++)
        {
            centerId = SolCenter.get(i);
            groupId  = SolGroup.get(i);
            
            Vector<Integer>grpId = new Vector<Integer>();
            Vector<Integer>used= new Vector<Integer>();
            
            for(int j=0;j<v.get(centerId).rooms;j++)
            {
                grpId.add(groupId);
                if(j==v.get(centerId).rooms-1) used.add( v.get(centerId).roomSpace.get(j) - additionalSeatPerCenter );
                else used.add( v.get(centerId).roomSpace.get(j) );
            }
            
            CenterData cd = new CenterData();
            cd.centerId=centerId;
            cd.centerName=v.get(centerId).centerName;
            cd.groupId=grpId;
            cd.roomName=v.get(centerId).roomName;
            cd.roomSpace=v.get(centerId).roomSpace;
            cd.roomUsed=used;
            cd.rooms=v.get(centerId).rooms;
            
            FinalOutput.add(cd);
        }
        
        for(int i=0;i<SolConflictCenter.size();i++)
        {
            CenterData cd = new CenterData();
            
            centerId=SolConflictCenter.get(i).centerId;
            cd.centerId=centerId;
            
            cd.centerName=v.get(centerId).centerName;
            cd.groupId=SolConflictCenter.get(i).groupId;
            cd.roomName=v.get(centerId).roomName;
            cd.roomSpace=v.get(centerId).roomSpace;
            cd.roomUsed=SolConflictCenter.get(i).roomSpace;
            cd.rooms=v.get(centerId).rooms;
            
            FinalOutput.add(cd);
        }
        
        Collections.sort(FinalOutput, new Comparator() {
            public int compare(Object a, Object b) {
                
              return ( new Integer(((CenterData) a).centerId ) ).compareTo( new Integer(((CenterData) b).centerId ));
            
            }
         });
        
        return FinalOutput;
    }
    
}
