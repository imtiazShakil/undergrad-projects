/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seatassignment;

import java.util.Vector;

/**
 *
 * @author imtiaz
 */
public class CenterData {
    public String centerName;
    public int rooms,totSeat;
    public Vector<Integer>roomSpace;
    public Vector<String>roomName;
    
    
    public int centerId;
    public Vector<Integer>groupId;
    public Vector<Integer>roomUsed;
    
    CenterData(String centerName, Vector<Integer>roomSpace ,Vector<String>roomName)
    {
        this.centerName=centerName;
        this.roomSpace=roomSpace;
        this.roomName=roomName;
        
        rooms=roomSpace.size();
        totSeat=0;
        for(int i=0;i<rooms;i++) totSeat+=roomSpace.get(i);
        
    }
    
    CenterData(int centerId)
    {
        this.centerId=centerId;
        groupId= new Vector<Integer>();
        roomSpace= new Vector<Integer>();
    }
    
    CenterData() {}
    
}
