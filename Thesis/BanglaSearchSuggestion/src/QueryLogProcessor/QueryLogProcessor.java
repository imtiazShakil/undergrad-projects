/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package QueryLogProcessor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author ImtiazShakil
 */

/**
 *
 * @author ImtiazShakil
 */
public class QueryLogProcessor {
    private int TrieNodeCounter;
    private int MaxSuggestion;
    private final int initialCapacity=1000000;
    private ArrayList<compressedTrie>Trie = new ArrayList<compressedTrie>(initialCapacity);
    
    private final double FULLMATCH=1000000.0;
//    private final double BIGPREFIXMATCH=100000.0;
    private final double PARTIALBIGPREFIXMATCH=1000.0;
    
    private final double BigMatch=0.97;
//    private final double PartialMatch=0.59;
    private final double minimumMatch=0.9;
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    public QueryLogProcessor(int MaxSuggestion, int phaseSize) {
        this.MaxSuggestion=MaxSuggestion;
        compressedTrie.MaxSuggestion=MaxSuggestion;
        compressedTrie.PhaseSize=phaseSize;
        
        TrieNodeCounter=0;
        Trie.add(null);
        Trie.set(0, new compressedTrie() );
        Trie.get(0).par=0;
        
        
    }
    
    public void update(String query, double frequency)  // Single Query String along with its frequency is updated
    {
        int CloneNode,NewNode,OldNode;
        int cur=0,indx=0,match;
        int edgeId;
        
        int lastNode=-1,lastNode2=-1;
        
        while( indx<query.length() && lastNode==-1 )
        {
            if( Trie.get(cur).next.containsKey(  query.charAt(indx) )  )
            {
                Pair pp=(Pair)Trie.get(cur).next.get(query.charAt(indx));
                edgeId=pp.second;
                String str=Trie.get(cur).EdgeList.get(edgeId);
                
                        
                match=0;
                while(indx<query.length() && match<str.length() &&  query.charAt(indx)==str.charAt(match) ) {++indx;++match;}
                
                //Edge String Ended while Query hasn't yet been ended
                if(match>=str.length() && indx<query.length()) cur=pp.first;
                
                // query string ended while traversing this edge
                if( indx>=query.length() &&  match<str.length() ) 
                {
                    Trie.add(null);
                    NewNode=++TrieNodeCounter;
                    OldNode=pp.first;
                    
                    // New Node Created with Node Number = ++TrieNodeCounter
                    Trie.set(NewNode, new compressedTrie() );
                    
                    
                    //updating NewNode
                    Trie.get(NewNode).updateMap( str.charAt(match) , str.substring(match), OldNode, -1);
                    Trie.get(NewNode).updateFrequency(frequency);
                    Trie.get(NewNode).updateParent(cur, edgeId);
                    
                    //updating OldNode's parent
                    Trie.get(OldNode).updateParent(NewNode,0);
                    
                    
                    //updating current node's Edge
                    Trie.get(cur).updateMap(str.charAt(0), str.substring(0,match), NewNode , edgeId);
                    
                    lastNode=OldNode;
                    
                }
                
                // both string ended
                if(indx>=query.length() && match>=str.length())
                {
                    OldNode=pp.first;
                    Trie.get(OldNode).updateFrequency(frequency);
                    lastNode=OldNode;
                }
                
                // if none string ended here
                if(indx<query.length() && match<str.length())
                {
                    Trie.add(null);
                    CloneNode=++TrieNodeCounter; // CloneNode is the middle node which has oldNode and NewNode as its child , its parent is curNode
                    Trie.set(CloneNode,new compressedTrie());
                    
                    Trie.add(null);
                    NewNode=++TrieNodeCounter;
                    Trie.set(NewNode,new compressedTrie());
                    
                    OldNode=pp.first;
                    
                    //updating Cur Node
                    Trie.get(cur).updateMap(str.charAt(0) , str.substring(0, match), CloneNode, edgeId);
                    
                    
                    //updating Clone Node
                    Trie.get(CloneNode).updateMap( str.charAt(match) , str.substring(match), OldNode, -1);///OldNode given 0th Edge
                    Trie.get(CloneNode).updateMap( query.charAt(indx) , query.substring(indx), NewNode, -1);///NewNode given 1th Edge
                    Trie.get(CloneNode).updateParent(cur, edgeId);
                    
                    
                    
                    //updating OldNode's Parent
                    Trie.get(OldNode).updateParent(CloneNode, 0);
                    
                    //updating New Node's Parent
                    Trie.get(NewNode).updateParent(CloneNode, 1);
                    Trie.get(NewNode).updateFrequency(frequency);
                    
                    lastNode=OldNode;
                    lastNode2=NewNode;
                    
                }
                
           
            }
            else {
                // New Node Created with Node Number = ++TrieNodeCounter
                Trie.add(null);
                NewNode=++TrieNodeCounter;
                Trie.set(NewNode, new compressedTrie() );
                
                //updating current node's Edge
                Trie.get(cur).updateMap(query.charAt(indx), query.substring(indx), NewNode , -1);
                
                //updating newNode's parent
                HashMap hm= Trie.get(cur).next;
                Object pp1=hm.get(query.charAt(indx));
                Pair pp=(Pair) pp1;
                
                Trie.get(NewNode).updateParent( cur , pp.second);
                Trie.get(NewNode).updateFrequency(frequency);
                
                lastNode=NewNode;
                break;
            }
        }
        
        if(lastNode!=-1) updateBestOptions(lastNode);
        if(lastNode2!=-1) updateBestOptions(lastNode2);
        
        return; 
    }
    
//    private void queryWeightCalculator(int nowNode)  // Calculates the Weight of a Leaf Node 
//    {
//            if(Trie.get(nowNode).isLeafNode==false) return;
//            double cumfrequency1=Trie.get(nowNode).frequencySum-Trie.get(nowNode).frequency.getLast();
//            double cumfrequency2=Trie.get(nowNode).frequencySum-Trie.get(nowNode).frequency.getFirst();
//            double ratio=cumfrequency2;
//            if(cumfrequency1>0.0) ratio=cumfrequency2/cumfrequency1;
//            
////            PairDI pp= new PairDI(Trie.get(nowNode).frequencySum , nowNode);
//            
//            //first --> Frequency
//            //Second --> Node
//            
//            PairDI pp= new PairDI(Trie.get(nowNode).globalFrequency*ratio + Math.log10( Trie.get(nowNode).globalFrequency + 1.0 )/Math.log10( 2.0 ) , nowNode);
//            Trie.get(nowNode).updateBestNode( pp );
//            return;
//    }
    
    private void updateBestOptions(int nowNode) /// Updates BestOptions of all Nodes from nowNode to root Node
    {                                           /// Updates from bottom to straight up
        //first --> Frequency
        //Second --> Node
        if(Trie.get(nowNode).isLeafNode)
        {
            Trie.get(nowNode).queryWeightCalculator(nowNode);
        }
        ArrayList<PairDI>BestOptions;//=new ArrayList<Pair>();
        BestOptions=Trie.get(nowNode).BestNodes;
        
        while(nowNode!=0)
        {
            nowNode=Trie.get(nowNode).par;
            if(BestOptions!=null) for(int i=0;i<BestOptions.size();i++) Trie.get(nowNode).updateBestNode(BestOptions.get(i));
            
            BestOptions=Trie.get(nowNode).BestNodes;
        }
        
    }

    
    public void printTree(int nowNode)
    {
        Queue<Integer>Q = new LinkedList<Integer>();
        Q.add(nowNode);
        while(!Q.isEmpty())
        {
            nowNode=Q.peek().intValue();
            Q.remove();
            
            HashMap hm= Trie.get(nowNode).next;
            Iterator it=hm.entrySet().iterator();
            
            
            System.out.println("Current Node " + nowNode +" FrequencySum "+Trie.get(nowNode).frequencySum +" Global Frequency "+Trie.get(nowNode).globalFrequency );
            System.out.println("ParNode "+Trie.get(nowNode).par +" ParEdge "+Trie.get(nowNode).parEdge);
            System.out.println("isObsolete "+Trie.get(nowNode).isObsolete+" isLeafNode "+Trie.get(nowNode).isLeafNode );
            if(Trie.get(nowNode).isLeafNode)
            {
                Object[] frequency = Trie.get(nowNode).frequency.toArray();
                for(int i=0;i<frequency.length;i++)
                {
                    System.out.println("Phase "+i+" Frequency "+ ((Double)frequency[i]).doubleValue() ) ;
                }
            }
            System.out.println("Best Options");
            for(int i=0;i<Trie.get(nowNode).BestNodes.size();i++)
            {
                System.out.println("Frequency "+Trie.get(nowNode).BestNodes.get(i).first +" Leaf Node "+Trie.get(nowNode).BestNodes.get(i).second );
            }
            
            while(it.hasNext()) {
            
                Map.Entry entry = (Map.Entry) it.next();
                Object key = entry.getKey();
                Pair pp =(Pair)entry.getValue();
                
                System.out.println("Character->"+key.toString() +"<-Node "+pp.first+" Edge->"+Trie.get(nowNode).EdgeList.get(pp.second) + "<-Edgeindex " +pp.second);
                Q.add(pp.first);
            }
            
        }
        
    }
    
    
    private void getBestPairs( ArrayList<PairDI>BestNodes, PairDI  node) /// Special method to update BestNodes object with the given node
    {                                                                    /// this BestNodes will not store more than MaxSuggestion+Extra
        if( node.first>0.0 ) {                                           /// this method is only called by getSuggestion method
            int curIndx;
            int Extra=2;

            for(curIndx=0;curIndx<BestNodes.size();curIndx++)
            {
                if(BestNodes.get(curIndx).second == node.second) 
                {
                    BestNodes.get(curIndx).first=Math.max(BestNodes.get(curIndx).first, node.first);  // We are always Trying to get store the Best Frequency
                    break;
                }
            }
            if(curIndx>=BestNodes.size()) BestNodes.add(node);

            for(int i=BestNodes.size()-1;i-1>=0;i--)
            {
                if( BestNodes.get(i).first>BestNodes.get(i-1).first ) Collections.swap(BestNodes, i, i-1);
            }
            if(BestNodes.size()>MaxSuggestion + Extra ) BestNodes.remove(BestNodes.size()-1);
        }
    }
    
    /**
     *
     * @param query
     * @param foundFrequency
     * @return
     */
    public ArrayList<String> getSuggestion(String query , ArrayList<Double>foundFrequency)
    {
        if(query.length()>300) return new ArrayList<String>();
        
        ArrayList<PairDI>BestPairs= new ArrayList<PairDI>();
        ///First --> Frequency
        ///Second --> Leaf Node
        
        int curNode=0,indx=0,prvNode=-1;
        int originalMatch=0;
//        double multiplier=0.0;
        double matchRatio;
        double prevMatchRatio;
        
        while(true)
        {
            matchRatio=( originalMatch*1.0 / query.length()*1.0 ) ;
//            System.out.println("Now Node "+curNode+" Current Multiplier "+multiplier);
            prevMatchRatio=matchRatio;
            if(matchRatio>minimumMatch) for(int i=0;i<Trie.get(curNode).BestNodes.size();i++)
            {    
//                PairDI pp= new PairDI(Trie.get(curNode).BestNodes.get(i).first*multiplier, Trie.get(curNode).BestNodes.get(i).second);
                PairDI pp= new PairDI(Trie.get(curNode).BestNodes.get(i).first*matchRatio, Trie.get(curNode).BestNodes.get(i).second);
                getBestPairs(BestPairs,pp);
            }
            
            if(indx>=query.length()) break;
            if(Trie.get(curNode).next.containsKey(query.charAt(indx)))
            {
                int match=0;
                Pair pp=(Pair)Trie.get(curNode).next.get(query.charAt(indx));
                String str=Trie.get(curNode).EdgeList.get(pp.second);
                
                while(match<str.length() && indx<query.length() && str.charAt(match)==query.charAt(indx) ) {match++;indx++;originalMatch++;}
           
                
                prvNode=curNode;
                curNode=pp.first;
                
                if(match<str.length()) indx=query.length();
                if(indx>=query.length() && Trie.get(curNode).isLeafNode ) // perfect match or query string ended here and it is leaf node
                {
//                    System.out.println("Infinity "+indx+" query "+query+" node "+curNode);
//                    System.out.println("Edge->"+match+" org node "+ppar);
                    matchRatio=( originalMatch*1.0 / query.length()*1.0 ) ;
                    if(matchRatio<minimumMatch) break;
                    
                    if(matchRatio>BigMatch) getBestPairs(BestPairs, new PairDI( FULLMATCH*Trie.get(curNode).globalFrequency , curNode ) );
//                    else getBestPairs(BestPairs, new PairDI( Trie.get(curNode). , curNode ) );
                }
                
                if(indx>=query.length()  ) // perfect match or query string ended here and this may not be the leaf node
                {
//                    System.out.println("CurNode "+curNode);
                    
                    matchRatio=( originalMatch*1.0 / query.length()*1.0 ) ;
                    if(matchRatio<minimumMatch) break;
                    
                    PairDI pp2;
                    for(int i=0;i<Trie.get(curNode).BestNodes.size();i++)
                    {
                        if(matchRatio>BigMatch) pp2= new PairDI( PARTIALBIGPREFIXMATCH*Trie.get(curNode).BestNodes.get(i).first, Trie.get(curNode).BestNodes.get(i).second);
                        else pp2= new PairDI( Trie.get(curNode).BestNodes.get(i).first, Trie.get(curNode).BestNodes.get(i).second);
                        
//                        System.out.println("Type 2 Match Ratio "+matchRatio);
//                        System.out.println("one Pair freq "+pp2.first+" node "+pp2.second);
                        getBestPairs(BestPairs,pp2);
                    }
                    
                    if(prvNode>0 && prevMatchRatio>minimumMatch) for(int i=0;i<Trie.get(prvNode).BestNodes.size();i++)
                    {
                        if(prevMatchRatio>BigMatch) pp2= new PairDI( PARTIALBIGPREFIXMATCH * Trie.get(prvNode).BestNodes.get(i).first, Trie.get(prvNode).BestNodes.get(i).second);
                        else  pp2= new PairDI( Trie.get(prvNode).BestNodes.get(i).first, Trie.get(prvNode).BestNodes.get(i).second );                        
//                        System.out.println("Second Pair freq "+pp2.first+" node "+pp2.second);
//                        System.out.println("Type 3 Match Ratio "+matchRatio);
                        getBestPairs(BestPairs,pp2);
                    }
                    
                }
                
            }
            else if(curNode!=0)  /// query didn't end but this is the end node we can go
            {
//                System.out.println("No Match");
                matchRatio=( originalMatch*1.0 / query.length()*1.0 ) ;
//                System.out.println("No Match matchRatio "+matchRatio+" string-->"+query.substring(indx) );
                if(matchRatio<minimumMatch) break;
                
                
//                if(Trie.get(curNode).isLeafNode) getBestPairs(BestPairs, new PairDI(BIGPREFIXMATCH, curNode) );/// this is a leaf node
                
                PairDI pp2;
                for(int i=0;i<Trie.get(curNode).BestNodes.size();i++)
                {
                    if(matchRatio>BigMatch) pp2= new PairDI(PARTIALBIGPREFIXMATCH*Trie.get(curNode).BestNodes.get(i).first, Trie.get(curNode).BestNodes.get(i).second);
                    else pp2= new PairDI(Trie.get(curNode).BestNodes.get(i).first, Trie.get(curNode).BestNodes.get(i).second);
                     
                    getBestPairs(BestPairs,pp2);
                }

                break;
            }
            else break;
//            multiplier=multiplier+increment;
        }
        
        
        
//        System.out.println("Suggestion "+MaxSuggestion+"BestPair size"+BestPairs.size());
        ArrayList<String>BestSuggestion= new ArrayList<String>();
        foundFrequency.clear();
        
        int par,parEdge;
        for(int i=0;i<BestPairs.size() && i<MaxSuggestion;i++)
        {
            foundFrequency.add(  BestPairs.get(i).first /( Math.log10(TrieNodeCounter*1.0) / Math.log10(2.0)   )   );
            // Found Weight is divided by log2(Total Nodes in Trie)
            // Because it balances Document's Corpus Data With Query Log's Corpus Data 

            curNode=BestPairs.get(i).second;
            String str="";
            while(curNode!=0)
            {
                par=Trie.get(curNode).par;
                parEdge=Trie.get(curNode).parEdge;
                
                str=str+ new StringBuffer( Trie.get(par).EdgeList.get(parEdge) ).reverse().toString();
                curNode=par;
            }
            String reverse = new StringBuffer(str).reverse().toString();
            BestSuggestion.add(reverse);
//            System.out.println(" Freq: "+BestPairs.get(i).first +"String->"+reverse);
        }
        
        return BestSuggestion;
    }
    
    public void updateWholeTree() /// Complexity O(2*N + C) where N is the total number of nodes present in the Trie
    {
        PriorityQueue<Pair>PQ = new PriorityQueue<Pair>(MaxSuggestion,
                new Comparator<Pair>() {

            //Pair 
            //-->  first --> Node
            //-->  second --> Depth
            @Override
            public int compare(Pair a, Pair b) {
                if(a.second!=b.second)
                {
                    if(a.second>b.second) return -1; ///Depth in Descending Order
                    return 1;
                }
                else {
                    if(a.first>b.first) return 1; /// Node in Ascending Order
                    return -1;
                }
            }
        }
        );
        
//        PQ.add(new Pair(12, -6));
//        PQ.add(new Pair(5, 6));
//        PQ.add(new Pair(7, 1));
//        PQ.add(new Pair(10, 4));
//        PQ.add(new Pair(15, 6));
//        PQ.add(new Pair(3, 1));
//        PQ.add(new Pair(3, 4));
//        
//        PQ.add(new Pair(12, -6));
//        PQ.add(new Pair(5, 6));
//        PQ.add(new Pair(7, 1));
//        PQ.add(new Pair(10, 4));
//        PQ.add(new Pair(15, 6));
//        PQ.add(new Pair(3, 1));
//        PQ.add(new Pair(3, 4));
//        
//        
//        
//        while(!PQ.isEmpty())
//        {
//            Pair pp = PQ.peek();
//            PQ.remove();
//            System.out.println(pp.first+" "+pp.second);
//        }
        int nowNode,parNode;
        Pair pp,prv;
        prv=new Pair(0, 0);
        
        Queue<Pair>Q = new LinkedList<Pair>();
        Q.add(new Pair(0,0));/// because 0 is the root node
        while(!Q.isEmpty())
        {
            pp=Q.peek();
            nowNode=pp.first;
            if(Trie.get(nowNode).isLeafNode) PQ.add( new Pair(nowNode, pp.second  ) ); //only adding the leaf nodes in priority queue
            Q.remove();
            
            HashMap hm= Trie.get(nowNode).next;
            Iterator it=hm.entrySet().iterator();
            
            while(it.hasNext()) {
            
                Map.Entry entry = (Map.Entry) it.next();
                Object key = entry.getKey();
                Pair ppn =(Pair)entry.getValue();
                
                Q.add(new Pair( ppn.first, pp.second+1) );
            }
            
        }
        
       
        
        
        while(!PQ.isEmpty())
        {
            pp=PQ.peek();
            nowNode=pp.first;
            PQ.remove();
            if(nowNode==0) continue;
            if(prv.first==pp.first && prv.second==pp.second) continue;
            prv=pp;
            System.out.println("Now Node "+nowNode+ " depth "+pp.second);
            
            if(Trie.get(nowNode).isLeafNode ) {
                Trie.get(nowNode).frequencySum-=Trie.get(nowNode).frequency.peek().doubleValue();
                Trie.get(nowNode).frequency.remove();
                
                if(Trie.get(nowNode).frequency.size()==0) {
                    Trie.get(nowNode).frequency.add(1.0);
                    Trie.get(nowNode).frequency.add(1.0);
                    Trie.get(nowNode).frequencySum=2.0;
//                    Trie.get(nowNode).isObsolete=true;
                }
                
                Trie.get(nowNode).queryWeightCalculator(nowNode);
//                PairDI pp2= new PairDI(Trie.get(nowNode).frequencySum , nowNode);
//                Trie.get(nowNode).updateBestNode( pp2 );
            }
            parNode=Trie.get(nowNode).par;
            for(int i=0;i<Trie.get(nowNode).BestNodes.size();i++) Trie.get(parNode).updateBestNode( Trie.get(nowNode).BestNodes.get(i) );
            PQ.add( new Pair(parNode,  pp.second-1 ) );
        }
    }
    
    public void backupData(String Path) throws IOException
    {
        File file = new File(Path);
        if(!file.exists()) file.createNewFile();
        
        BufferedWriter writer = new BufferedWriter(new FileWriter( file.getAbsoluteFile() ) );
        
        writer.write( TrieNodeCounter+" "+compressedTrie.PhaseSize+"\n" );
        
        
        Queue<Integer>Q = new LinkedList<Integer>();
        int nowNode;
        Q.add(0);// because 0 is the root node
        while(!Q.isEmpty())
        {
            nowNode=Q.peek().intValue();
            Q.remove();
            
            HashMap hm= Trie.get(nowNode).next;
            Iterator it=hm.entrySet().iterator();
            
            
            System.out.println("Current Node " + nowNode +" Frequency "+Trie.get(nowNode).frequencySum );
            System.out.println("ParNode "+Trie.get(nowNode).par +" ParEdge "+Trie.get(nowNode).parEdge);
            System.out.println("isObsolete "+Trie.get(nowNode).isObsolete+" isLeafNode "+Trie.get(nowNode).isLeafNode );
            if(Trie.get(nowNode).isLeafNode)
            {
                Object[] frequency = Trie.get(nowNode).frequency.toArray();
                for(int i=0;i<frequency.length;i++)
                {
                    System.out.println("Phase "+i+" Frequency "+ ((Double)frequency[i]).doubleValue() ) ;
                }
            }
            System.out.println("Best Options");
            for(int i=0;i<Trie.get(nowNode).BestNodes.size();i++)
            {
                System.out.println("Frequency "+Trie.get(nowNode).BestNodes.get(i).first +" Leaf Node "+Trie.get(nowNode).BestNodes.get(i).second );
            }
            
            while(it.hasNext()) {
            
                Map.Entry entry = (Map.Entry) it.next();
                Object key = entry.getKey();
                Pair pp =(Pair)entry.getValue();
                
                System.out.println("Character->"+key.toString() +"<-Node "+pp.first+" Edge->"+Trie.get(nowNode).EdgeList.get(pp.second) + "<-Edgeindex " +pp.second);
                Q.add(pp.first);
            }
            
        }
        writer.close();
        
    }
    public void setQueryLog(String location) throws FileNotFoundException, IOException
    {
        int ADDITIONALFREQ=10000;
        
        System.out.println("Query Log Processing Started");
        long startTime= System.currentTimeMillis();
        HashMap Map = new HashMap();
        BufferedReader reader = new BufferedReader(new FileReader(location));
        String line;
        ArrayList<Double> freq= new ArrayList<>();
        int counter=0,indx;
        
        while ((line = reader.readLine()) != null) {
            if(line.length()<5) continue;
            String sentence[] = line.split("\\s+");
            if( sentence.length<2 ) continue;
            
            line="";
            for(int i=0;i<sentence.length;i++) 
            {
                if(i!=0) line=line+" "+sentence[i];
                else line=sentence[i];
            }
            
            if(Map.containsKey(line)) 
            {
                indx=(int)Map.get(line);
                
                freq.set(indx, freq.get(indx) + 1.0 );
            }
            else {
                freq.add(1.0);
                Map.put(line,counter );
                counter++;
            }
        }
        Iterator it=Map.entrySet().iterator();
        while(it.hasNext()) {
            
                Map.Entry entry = (Map.Entry) it.next();
                Object key = entry.getKey();
                indx =(int)entry.getValue();
                
//                System.out.println((String)key + " "+freq.get(indx));
                update((String)key,  freq.get(indx) + ADDITIONALFREQ );
        }
        long endTime=System.currentTimeMillis();
        Map.clear();
        reader.close();
        System.out.println("Query Log Processing Finished in "+ (endTime-startTime)+" milliseconds");
        System.out.println("Total Trie Nodes "+TrieNodeCounter);
        
    }
    
    public void setQueryLogWithFrequency(String location) throws FileNotFoundException, IOException /// Special Function For Pipilika 
    {                                                                                                   /// Where Each query is unique
                                                                                                        /// query alongside its frequqncy is given
        System.out.println("Pipilika's Query Log Processing Started");                                  /// query String and frequency is seperated with '/'
        long startTime= System.currentTimeMillis();                                                     /// Example-->"I am Superman/3"
        BufferedReader reader = new BufferedReader(new FileReader(location));
        String line,originalString;
        int freq;
        int ADDITIONALFREQ=50000;
        
        while ((line = reader.readLine()) != null) {
            if(line.length()<5) continue;
            for(int i=line.length()-1;i>=0;i--)
            {
                if(line.charAt(i)=='/')
                {
                    freq=Integer.parseInt( line.substring(i+1) );
//                    System.out.println("Found string "+line.substring(0, i)+"<-Freq "+freq );
                    
                    update( line.substring(0, i) , (ADDITIONALFREQ+freq)*1.0 );
                    break;
                }
            }
            
        }
        
        long endTime=System.currentTimeMillis();
        reader.close();
        System.out.println("Pipilika's Query Log Processing Finished in "+ (endTime-startTime)+" milliseconds");
        System.out.println("Total Trie Nodes "+TrieNodeCounter);
        
    }
    
    
}
