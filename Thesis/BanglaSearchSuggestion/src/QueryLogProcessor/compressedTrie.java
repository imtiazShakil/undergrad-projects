package QueryLogProcessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ImtiazShakil
 */

public class compressedTrie {
    HashMap next = new HashMap();    //First Part --> Node
                                    // Second Part --> EdgeIdIndex
    int par,parEdge;
    
    double globalFrequency;
    double frequencySum;            // total frequency of the past 7 phases
    ArrayList<String>EdgeList = new ArrayList<String>();
    
    ArrayList<PairDI>BestNodes = new ArrayList<PairDI>();//First part --> Freq of the Leaf Node
                                                       //Second part --> Id of that Leaf Node
    
    Deque<Double>frequency;                                 //contains frequency of the previous 7 phase
                                                            //phase can be 7days, 7 hours, 7 months
    
    
    protected static int MaxSuggestion;                // Maximum Suggestions stored by each node
    protected static int PhaseSize;                   // Maximum Size of frequency 
    
    public boolean isObsolete;                       // String ending in this node is obsolete not searched anymore
    public boolean isLeafNode;                      // String ending here is a leaf node
    
    public compressedTrie( ) {
        next.clear();
        EdgeList.clear();
        BestNodes.clear();
        par=-1;
        parEdge=-1;
        frequencySum=0.0;
        globalFrequency=0.0;
        isObsolete=false;
        isLeafNode=false;
    }
    
    protected void updateFrequency(double newFrequency)
    {
        isLeafNode=true;
        if(frequency==null) frequency=new LinkedList<Double>();
        if(frequency.size()<PhaseSize+1) // inserting one frequency extra so when full tree is updates we'd still have PhasSize number of frequencies
        {
            frequency.add(newFrequency);
            frequencySum+=newFrequency;
        }
        else {
            frequencySum-=frequency.peek().doubleValue();
            frequency.remove();
            frequency.add(newFrequency);
            frequencySum+=newFrequency;
        }
        globalFrequency+=newFrequency;
//        while(frequency.size()<PhaseSize+1) frequency.add(0.0); // don't have sufficient phase data for this node
//                                                                // thus storing redundant data
        return;
    }
    
    
    protected void updateBestNode( PairDI  node)
    {
        if(node.first>0.0) ;
        else return;
        
        int curIndx;
        for(curIndx=0;curIndx<BestNodes.size();curIndx++)
        {
            if(BestNodes.get(curIndx).second == node.second) 
            {
                BestNodes.get(curIndx).first=node.first;             // We won't take the best frequency of these two nodes
                break;                                               // because curNode's Weight may increase or decrease
            }
        }
        if(curIndx>=BestNodes.size()) BestNodes.add(node);
        
        for(int i=BestNodes.size()-1;i-1>=0;i--)
        {
            if( BestNodes.get(i).first>BestNodes.get(i-1).first ) Collections.swap(BestNodes, i, i-1);
        }
        
        if(BestNodes.size()>MaxSuggestion) BestNodes.remove(BestNodes.size()-1);
        return;
    }
    
    protected void updateMap(char ch,String EdgeLabel , int NewNodeIndex , int EdgeListIndex )
    {
        //next has a key which is the starting character
        //next has a value which is a pair
        //Pair.first --> NewNode
        //Pair.second --> EdgeListIndex
        if(EdgeListIndex==-1)
        {
            next.put(ch, new Pair(NewNodeIndex,  EdgeList.size() ) );
            EdgeList.add(EdgeLabel);
        }
        else {
            next.put(ch, new Pair(NewNodeIndex,  EdgeListIndex ) );
            EdgeList.set(EdgeListIndex, EdgeLabel);  
        }
        return;
    }
    
    protected void updateParent(int parNode , int ParNodeEdge)
    {
        this.par=parNode;
        this.parEdge=ParNodeEdge;
        return;
    }
    
    protected void queryWeightCalculator(int nowNode)  // Calculates the Weight of a Leaf Node 
    {
            if(isLeafNode==false) return;
            double cumfrequency1;//=frequencySum-frequency.getLast(); //old
            double cumfrequency2;//=frequencySum-frequency.getFirst(); // new
            if(frequency.size()==1)   // When a Query is initialized for the first Time
            {
                cumfrequency2=frequencySum;
                cumfrequency1=0.0;
            }
            else {
                cumfrequency1=frequencySum-frequency.getLast(); //old
                cumfrequency2=frequencySum-frequency.getFirst(); // new
            
            }
            double ratio=cumfrequency2;
            if(cumfrequency1>0.0) ratio=cumfrequency2/cumfrequency1; // ratio = new/old
            
//            PairDI pp= new PairDI(Trie.get(nowNode).frequencySum , nowNode);
            
            //first --> Frequency
            //Second --> Node
            
            PairDI pp= new PairDI(globalFrequency*ratio + Math.log10( globalFrequency + 1.0 )/Math.log10( 2.0 ) , nowNode);
            updateBestNode(pp);
//            BestNodes.clear();
//            BestNodes.add(pp);
            
            return;
    }
}
