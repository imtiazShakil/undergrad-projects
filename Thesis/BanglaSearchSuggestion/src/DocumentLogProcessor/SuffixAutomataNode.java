package DocumentLogProcessor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

public class SuffixAutomataNode implements Comparator<ArrayList<Integer>>
{
    public SuffixAutomataNode()
    {
        
    }
    
    SuffixAutomataNode(int dep,int freq,int _id)
    {
        suffixautomatanode++;
        depth=dep;
        frequency=freq;
        id=_id;
    }    
        
    void suffixAutomataExtend(int val)
    {
        lastnode=this;
        if(!child.containsKey(val))
        {
            newnode=new SuffixAutomataNode(depth+1, frequencyvalue,val);            
            makeParentLink(val);
            newnode.link.updateFrequeny(frequencyvalue); 
        }
        else 
        {
            if(child.get(val).depth!=depth+1)  //not immidiate child
            {
                 SuffixAutomataNode now=child.get(val);
                 SuffixAutomataNode clone=new SuffixAutomataNode(depth+1,now.frequency+1,val); //frequency including itself
                 clone.child.putAll(now.child); //copies child information (pass by value)
                 clone.link=now.link;
                 SuffixAutomataNode remainingparent;
                 for (remainingparent=this;remainingparent!=null && remainingparent.child.get(val)==now;remainingparent=remainingparent.link)
                        remainingparent.child.put(val,clone);
                 now.link=clone;  
                 newnode=clone;
                 newnode.link.updateFrequeny(frequencyvalue);
            } 
            
            else 
            {
                newnode=child.get(val);
                newnode.updateFrequeny(frequencyvalue);
            }
        }
        updateMaxNodes(val);
    }        

    void makeParentLink(int val)
    {
        //making link
         if(child.containsKey(val))
         {
             SuffixAutomataNode now=child.get(val);
             if(now.depth==depth+1)
                 newnode.link=now;
             //making clone node
             else
             {
                 SuffixAutomataNode clone=new SuffixAutomataNode(depth+1,now.frequency,val);
                 clone.child.putAll(now.child); //copies child information (pass by value)
                 clone.link=now.link;
                 SuffixAutomataNode remainingparent;
                 for (remainingparent=this;remainingparent!=null && remainingparent.child.get(val)==now;remainingparent=remainingparent.link)
                        remainingparent.child.put(val,clone);
                 now.link=newnode.link=clone;                             
             }             
             return;
         }
         
         child.put(val,newnode);
         //if(this==root) System.out.println(val);
         if(link!=null)
            link.makeParentLink(val);
         else             
            newnode.link=root;
    }

    void updateFrequeny(int freq)
    {
        frequency+=freq;
        if(this!=root)
            link.updateFrequeny(freq);
    } 
    
    void updateMaxNodes(int val)
    {
        int i;
        for(i=0;i<maxnodes.size();i++)
            if((maxnodes.get(i).compareTo(val))==0)
                break;
         
        if(i>=maxnodes.size()) maxnodes.add(val);
        
        while(i>=1 && child.get(maxnodes.get(i)).frequency>child.get(maxnodes.get(i-1)).frequency)
        {
            Collections.swap(maxnodes,i,i-1);
            i--;
        }  
        
        if(maxnodes.size()>maxsuggestions)
           maxnodes.remove(maxsuggestions);
        if(this!=root)
            link.updateMaxNodes(val);       
    }
    
    ArrayList<Integer> queryToArrayListConversion(String querystring[])
    {
         ArrayList<Integer>ret=new ArrayList<Integer>();
         for(int i=0;i<querystring.length;i++)
            if(querystring[i].length()!=0 && wordtonumber.containsKey(querystring[i].toString())) //to cancel out unmapped word
                ret.add(wordtonumber.get(querystring[i].toString()));   
            else if(querystring[i].length()!=0)
                ret.add(-1);
        return ret; 
    }
    
    String arrayListToQueryConversion(ArrayList<Integer>query)
    {
         StringBuffer sentence = new StringBuffer();
         for (Integer w : query) 
             if(numbertoword.containsKey(w))
                sentence.append(numbertoword.get(w)+" ");
         sentence.deleteCharAt(sentence.length()-1);
         sentence.append("\n");
         return sentence.toString();
    }
    
    String getText( ArrayList<String> querystring)
    {
         StringBuffer sentence = new StringBuffer();
         for (String w : querystring) sentence.append(w+" ");
         sentence.append("\n");
         return sentence.toString();
    }
    
    void traverse(ArrayList<Integer> query,int in)
    {
        if(query.size()==0) return;
        if(in>=query.size()) { retrieveSuggestion(query); return;}
        if(child.containsKey(query.get(in).intValue()))
            child.get(query.get(in).intValue()).traverse(query, in+1);
        
    }
    
    //dynamic traversal
    void traverse(ArrayList<Integer> query,ArrayList<Integer> now,int depth,int extrawords)
    {
         if(depth>=query.size()-1) 
        {
            if(depth!=extrawords && depth!=0) retrieveSuggestion(now); //at least 1 word from query
            if(depth>query.size()+2) return;
        }
        
          for(int i=0;i<query.size();i++)
          if(child.containsKey(query.get(i)))
          {
            now.add(query.get(i));
            child.get(query.get(i)).traverse(query,now,depth+1,extrawords);   
            now.remove(now.size()-1);
          }  
        
         if(depth/3+1>extrawords && depth!=0) //change here to get more dynamic suggestion
        for(int i=0;i<maxnodes.size();i++) {
            now.add(maxnodes.get(i));
            child.get(maxnodes.get(i)).traverse(query,now,depth+1,extrawords+1);
            now.remove(now.size()-1);
        }    
        
    }    
    
    void retrieveSuggestion(ArrayList<Integer> query)
    {
         if(visited.containsKey(this)) return;
         visited.put(this,true);
         if(maxnodes.size()==1) //ensuring the line in case of only 1 suggestion (mostly like trie)
        {
             query.add(maxnodes.get(0).intValue());
             child.get(maxnodes.get(0).intValue()).retrieveSuggestion(query); 
             query.remove(query.size()-1);
             return;
         }
//         if(child.size()==1) {
//             //System.out.println(child.get(0));
//             return;
//         }
         if(maxnodes.size()==0)
         {
             ArrayList<Integer>sentence=new ArrayList<Integer>();
             sentence.addAll(query);
             querysuggestion.add(sentence);
             return;
         }
                         
         ArrayList<Integer>sentence=new ArrayList<Integer>();
         sentence.addAll(query);
         for(int i=0;i<maxnodes.size();i++)
         {
             sentence.add(maxnodes.get(i));
             ArrayList<Integer>temp =new ArrayList<Integer>();
             temp.addAll(sentence);
             querysuggestion.add(temp);
             SuffixAutomataNode now=child.get(maxnodes.get(i));
            // if(!visited.containsKey(now)) 
             for(int j=0;j<now.maxnodes.size() ;j++) //going 1 depth inside
             {
                  sentence.add(now.maxnodes.get(j));
                  ArrayList<Integer>temp2 =new ArrayList<Integer>();
                  temp2.addAll(sentence);
                  querysuggestion.add(temp2);
                  sentence.remove(sentence.size()-1);
             }
             sentence.remove(sentence.size()-1);
         }
             
    }
    
    double getFrequency(ArrayList<Integer> query,int in)
    {
        if(in>=query.size()) return 1.0*frequency/( Math.log10(suffixautomatanode*1.0) / Math.log10(2.0) ) ;  
        // Found Weight is divided by log2(Total Nodes in Automata)
        // Because it balances Document's Corpus Data With Query Log's Corpus Data
        
        if(!child.containsKey(query.get(in))) return 0.0;
        return child.get(query.get(in)).getFrequency(query, in+1);        
    }
    
    public double getFrequency(String formattedString)
    {
        if(this!=root) return root.getFrequency(formattedString);
        String querystring[]=formattedString.split("\\s+");
        return getFrequency(queryToArrayListConversion(querystring),0);        
    }
    
    boolean matches(ArrayList<Integer>o1,ArrayList<Integer>o2)
    {
         for(int i=0;i<o1.size()&&i<o2.size();i++)
             if(o1.get(i).compareTo(o2.get(i))!=0) //not matched
                 return false;
         return true;
    }   
    
    boolean partialMatches(ArrayList<Integer>o1)
    {
        double val=0;
        int in=0;
        while(in<o1.size() && in<query.size())
        {
            if(o1.get(in).compareTo(query.get(in))==0) //found match
                val+=weight.get(query.get(in));
            else break;
            in++;
        }        
        
        if(val/queryvalue>partialmatchthreshold) return true;
        return false;
    }
    
    int serialMatch(ArrayList<Integer>o1)
    {
        int inq=0;
        int ino1=0;
        while(ino1<o1.size()&&inq<query.size())
        {
            if(o1.get(ino1).compareTo(query.get(inq))==0) //found match
                inq++;
            ino1++;
        }        
        return inq+1;
    }  
            
    boolean closeMatches(ArrayList<Integer>o1)
    {
         double querysuggestionweight=0;
         ArrayList<Integer>temp=new ArrayList<Integer>();
         temp.addAll(o1);
         Collections.sort(temp);
         for(int i=0;i<temp.size();i++)
         {
             if(i>0 && temp.get(i).compareTo(temp.get(i-1))==0)             
                  continue;
             if(weight.containsKey(temp.get(i).intValue()))
                 querysuggestionweight++;
         }
         if(querysuggestionweight/(double)query.size()>closematchthreshold) return true;
         return false;
    }   
    
    double getWeight(ArrayList<Integer> query)
    {
        double ret=0;
        for(int i=0;i<query.size();i++)
           if(weight.containsKey(query.get(i)))
              ret+=weight.get(query.get(i));
        return ret;
    }
    
    double getWeightedFrequency(ArrayList<Integer> query)
    {
        //System.out.println("Exact match? "+arrayListToQueryConversion(query));
        if(matches(query,SuffixAutomataNode.query)) return exactmatch*getFrequency(query, 0);
        if(partialMatches(query)) return partialmatch*getFrequency(query,0);
        //System.out.println("Serial match? "+arrayListToQueryConversion(query));
        if((serialMatch(query)-1)/(double)SuffixAutomataNode.query.size()>serialmatchthreshold) return serialmatch*serialMatch(query)*getFrequency(query,0);
        //System.out.println("Close match? "+arrayListToQueryConversion(query));
        //if(closeMatches(query)) return closematch*getFrequency(query, 0);
       // System.out.println("No match "+arrayListToQueryConversion(query));
       
        return getWeight(query)*getFrequency(query, 0)*serialMatch(query);
    }
    
    //return if frequency is more, top priority for prefix match, top priority for longest suggestion in case of a draw
    public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2)
    {
         if(getWeightedFrequency(o1)+error<getWeightedFrequency(o2)) return 1;
         if(getWeightedFrequency(o1)>getWeightedFrequency(o2)+error) return -1;
         if(o1.size()<o2.size()) return 1;
         if(o1.size()>o2.size()) return -1;
         return 0;
    }   
    
    boolean valid(ArrayList<Integer>query)
    {
        System.out.println(query);
        System.out.println("checkvalid "+arrayListToQueryConversion(query)+"main query "+arrayListToQueryConversion(SuffixAutomataNode.query));
        if(!closeMatches(query)) return false; //word match
        System.out.println("valid1");
        if(getWeight(query)/queryvalue>weightthreshold) //weight match
                return true;
        System.out.println("invalid");
        return false;
    }  
    
    ArrayList<ArrayList<Integer> > makeSuggestion(String []querystring)
    {
        query=queryToArrayListConversion(querystring);  
        querysuggestion.clear();
        //exact prefix match
        traverse(query,0);
        ArrayList<Integer>temp=new ArrayList<Integer>();
        //weighted match
        traverse(query, temp, 0, 0);
               
        ArrayList<ArrayList<Integer> >ret=new ArrayList<ArrayList<Integer>>();
        for(ArrayList<Integer>now: querysuggestion)
         ret.add(now);
        
        weight.clear();
        for(int i=0;i<query.size();i++)
        {
            if(!wordfrequency.containsKey(query.get(i)))
                continue;
            double val=1.0/(wordfrequency.get(query.get(i)));
            weight.put(query.get(i),val);
        }
        
        queryvalue=0;
        for(int i=0;i<query.size();i++)
        {
            if(weight.containsKey(query.get(i).intValue()))
                queryvalue+=weight.get(query.get(i));
            else queryvalue+=exactmatch;
        }
        System.out.println(queryvalue);
        Collections.sort(ret,this);
        return ret;
    }
    
    public ArrayList<String> getSuggestion(String formattedString,ArrayList<Double> freq)
    {
        if(this!=root)  return root.getSuggestion(formattedString,freq);
        String querystring[]=formattedString.split("\\s+");
        visited.clear();
        ArrayList<ArrayList<Integer> >now=makeSuggestion(querystring);       
        System.out.println(query);
        ArrayList<String> ret=new ArrayList<String>();
        int tot=0;        
        for(int i=0;i<now.size();i++)
        {
           //prefix match
            //System.out.println("getweight "+now.get(i)+getWeight(now.get(i))/queryvalue);
            if(!valid(now.get(i)))
                continue;           
            
            int j;
            for(j=0;j<i;j++)
            {
                  //System.out.println(arrayListToQueryConversion(now.get(i))+" "+arrayListToQueryConversion((now.get(j)))+" " +matches(now.get(i),now.get(j)));    
                  if(matches(now.get(i),now.get(j))  &&  now.get(i).size()<=now.get(j).size()) 
                    break;
            }
            if(i>j) continue; 
            
            //valid suggestion
            if(DocumentLogProcessor.displayarea!=null) {
                DocumentLogProcessor.displayarea.append(arrayListToQueryConversion(now.get(i)));
                DocumentLogProcessor.displayarea.append(getFrequency(now.get(i),0)+"\n");
            }
            ret.add(arrayListToQueryConversion(now.get(i)));
            tot++;
            if(freq!=null)
                 freq.add(getWeightedFrequency(now.get(i)));
            if(tot>=maxsuggestions+2) break; 
        }
        
        return ret;
    }         
        
    //it is required that it should be a text file and there is a stop mark at the end of every line
    void updateCorpus(String path) throws FileNotFoundException, IOException
    {
//        System.out.println(path);
        FileWriter fw = new FileWriter(RootPath+"DocumentList.txt",true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(path+"\n");
        bw.close();
        FileReader file=new FileReader(path); //reading bytes file
        BufferedReader input=new BufferedReader(file); //reading from file as characters 
        String parsestring="[  -:“?;!”‘।’]";
        String str;       
        int wordid=wordtonumber.size();
        while(true)
        {
            str=input.readLine();
            if(str==null) break;
            if(str.length()<=1) continue;
            String word[] = str.split(parsestring);
            for (int i = 0; i < word.length; i++)
            {
                    if(word[i]==null) continue;
                    if(word[i].length()==0)  continue;
                    if(word[i].equals(" ")) continue;                            
                    if(wordtonumber.get(word[i])==null) 
                    {
                         wordid++;
                         wordtonumber.put(word[i],wordid);
                         wordfrequency.put(wordid, 1);
                         numbertoword.put(wordid,word[i]);
                    }
                    
                    else
                    {
                        int id=wordtonumber.get(word[i]);
                        wordfrequency.put(id,wordfrequency.get(id)+1);
                    }
            }
         }
         
         fw = new FileWriter(RootPath+"WordList.txt",false);
         bw = new BufferedWriter(fw);
                 
         Set<Map.Entry<Integer,String>> set = numbertoword.entrySet();
         for (Map.Entry<Integer,String> me : set)
         {
                 bw.write(me.getValue()+" ");
                 bw.write(me.getKey()+" ");
                 bw.write(wordfrequency.get(me.getKey())+"\n");
         }          
         bw.close(); 
         System.out.println("Word complete");
         
         fw = new FileWriter(RootPath+"SentenceList.txt",true);
         bw = new BufferedWriter(fw);
         
         // For Query Log
         //FileWriter fw2 = new FileWriter("QueryLogResource\\QueryLog.txt",true);
         //BufferedWriter bw2 = new BufferedWriter(fw2);
         
         file=new FileReader(path); 
         input=new BufferedReader(file);
         int tot=0;
         while(true)
        {
            str=input.readLine();
            if(str==null) break;
            if(str.length()<=1) continue;
            String sentence[] = str.split("[।?]");
            for(int i=0; i<sentence.length;i++)
            {
                  String word[] = sentence[i].split(parsestring);
                  newnode=root;
                  for(int j=0;j<word.length;j++)
                  {
                           if(!wordtonumber.containsKey(word[j])) //not in the wordlist
                           {
                                    newnode=root;
                                    bw.write("-1\n");
                                    tot++;
                                    if(tot%10000==0) System.out.println(tot);
                                    //bw2.write("\n");
                                    continue;                   
                            }
                          // bw2.write(word[j]+" ");  
                           int a=wordtonumber.get(word[j]);
                           bw.write(a+" ");
                           newnode.suffixAutomataExtend(a);                          
                  }     
                 bw.write("-1\n");
                // bw2.write("\n");
                 tot++;
                 if(tot%10000==0) System.out.println(tot);
             }
            
         }
         
         bw.close(); 
        // bw2.close();
         System.out.println("Sentence Complete");
         System.out.println("Total Suffix Automata node "+suffixautomatanode);
    }      
    
    //building automata
    public void preProcess()   throws FileNotFoundException, IOException {      
        long startTime=System.currentTimeMillis();
        String path= RootPath+"WordList.txt";
        FileReader file=new FileReader(path); //reading bytes file
        BufferedReader input=new BufferedReader(file); //reading from file as characters 
        String parsestring="\\s+";
        String str;
        while(true)
        {
            str=input.readLine();
//            System.out.print(str);
            if(str==null || str.length()==0) break;
                       
            //str =str.replaceAll("[A-z]+\\s+", "");
            String str2[]=str.split(parsestring);            
//                       System.out.println(" "+str2.length);
            if(str2.length!=3) continue;
            if(str2[0].length()==0 || str2[0]==null) continue;
            if(str2[0].equals(" ")) continue;
            wordtonumber.put(str2[0],Integer.parseInt(str2[1]));
            wordfrequency.put(Integer.parseInt(str2[1]),Integer.parseInt(str2[2]));
            numbertoword.put(Integer.parseInt(str2[1]),str2[0]);                 
        }
        System.out.println(wordtonumber.size()+" words");
        root=new SuffixAutomataNode(0, 0, 0);
        path=RootPath+"SentenceList.txt";
        file=new FileReader(path); //reading bytes file
        input=new BufferedReader(file); //reading from file as characters 
        
        while(true)
        {
            str=input.readLine();
            if(str==null || str.length()==0) break;
            String str2[]=str.split("\\s+"); 
            newnode=root;
            for(int i=0;i<str2.length;i++)
            {
               int a=Integer.parseInt(str2[i]);
               if(!numbertoword.containsKey(a)) //not in the wordlist
               {
                   newnode=root;
                   continue;                   
               }
               newnode.suffixAutomataExtend(a);
            }               
         }
         long endTime=System.currentTimeMillis();
         System.out.println("Total Suffix Automata node "+suffixautomatanode +" Build Time " + (endTime-startTime) +" milliSeconds" );
         //root. test();
    }

    //for test purpose
     private void test()
     {
       // System.out.println(now+" has "+child.keySet().toString()+" and depth "+depth);
        for (Integer key : child.keySet()) {
                if(child.get(key).depth<depth) System.out.println("problem");
                child.get(key).test();
        }       
    }    
    
    protected static final String RootPath="DocumentLogResource//";
    int id,depth,frequency;    
    SuffixAutomataNode link;    
    static int frequencyvalue=1;
    static int suffixautomatanode=0;   
    Map<Integer,SuffixAutomataNode> child=new HashMap<Integer,SuffixAutomataNode>();
    static SuffixAutomataNode root,newnode,lastnode;
    static int maxsuggestions=3;
    ArrayList<Integer> maxnodes=new ArrayList<Integer>();  //best maxsuggestions nodes (cosidering frequency)    
    static Set<ArrayList<Integer>> querysuggestion=new HashSet<ArrayList<Integer>> (); //for storing unique querysuggestions
    static Map<String,Integer> wordtonumber=new HashMap<String,Integer>();
    static Map<Integer,Integer> wordfrequency=new HashMap<Integer,Integer>();
    static Map<Integer,String> numbertoword=new HashMap<Integer,String>();    
    static ArrayList<Integer> query; //converting a query string to query
    static double queryvalue;
    static Map<SuffixAutomataNode,Boolean>visited=new HashMap<SuffixAutomataNode,Boolean>();
    static Map<Integer,Double>weight=new HashMap<Integer,Double>();
    static double exactmatch=1000000,partialmatch=1000,serialmatch=100,closematch=10;
    static double weightthreshold=.9,partialmatchthreshold=.6,serialmatchthreshold=.6,closematchthreshold=.6,error=1e-8;
}
