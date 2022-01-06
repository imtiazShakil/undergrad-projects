/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package autocompletion;

import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import DocumentLogProcessor.*;

/**
 *
 * @author nafisahmed
 */
public class generateTree {
    
    int maxWords = 100;
    int dp[][], exMatch;
    static int adj[][];
    static ArrayList str, val;
    ArrayList res;
    String curStr, ss;
    
    public void getDict()
    {
        str = new ArrayList();
        val = new ArrayList();
        
        try {
            
            Scanner inFile = new Scanner(new FileReader("DocumentLogResource\\"
                    + "Wordlist.txt")); //Dictionary for Shakil, taking the data from Shahriar directly

//            Scanner inFile = new Scanner(new FileReader("/Users/nafisahmed/NetBeansProjects/Version 18/BanglaSearchSuggestion"
//                    + "/wordlist.txt")); //Dictionary for Nafis
            
            /*
            Scanner inFile = new Scanner(new FileReader("//Users/nafisahmed/NetBeansProjects/"
                    + "AutoCompletion/FilteredWords.txt")); //For Filtering the Query Log
            */
            
            while(inFile.hasNext())
            {
                try {
                    String org;
                    org = (String) inFile.nextLine();
                    String word[] = org.split("([ ]|\n)+");
                    if( (word[0].length() >= 30) || (word.length!=3) ) 
                    {
                        continue;
                    }
                
                    String ss = (String) word[0];
                    int val1=(int) Integer.parseInt( word[1] );
                    int val2=(int) Integer.parseInt( word[2] );
                    str.add( ss );
                    val.add( val2 );
                }
                catch(Exception e) {
                    ss = (String) inFile.next();
                    System.out.println("Garbage in Dictionary Word " + ss);
                }

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(generateTree.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        genTree();
    }
    
    void genTree()
    {
        
        int len = str.size();
        System.out.println("len " + len);
        adj = new int[len][35];
        
        int i, j, now;
        for(i=1;i<len;i++)
        {
            for(j=0; ; )
            {
                now = calcDist( (String) str.get(i), (String) str.get(j) );
                if(adj[j][now] == 0)
                {
                    adj[j][now] = i;
                    break;
                }
                else j = adj[j][now];
            }
        }
        
        /*
        for(i=0;i<len;i++)
        {
            System.out.println(str.get(i) + "-> ");
            for(j=0;j<=10;j++)
            {
                if(adj[i][j]==0) continue;
                else System.out.println(j + ": " + str.get(adj[i][j]));
            }
        }
        */
        
        return;
    }
    
    public String getSuggestion(String str)
    {
        String correctedString = "";
        if(str.length()==0) return correctedString;
        
        SuffixAutomataNode shahAutomata = new SuffixAutomataNode();
        
//        String word[] = str.split("([ ] | '\n' | '\\s')+");
        String word[] = str.split("\\s+");
        
//        System.out.println("Number of word " + word.length + " <-->" + word.toString());
//        for(int i=0;i<word.length;i++)
//        {
//            System.out.println("For Nafis Word Found-->"+word[i]);
//        }
        
        String cur="", prev=""; 
        String bigramStr = "";
        myData naf = new myData("", 0);
        int cnt=1;
        if(str.charAt(str.length()-1)==' ' ) cnt=0;
        
        for(int i=0;i<word.length-cnt;i++)
        {
            double best = -10.0, ccVal = 0.0;
            bigramStr = "";
            cur = word[i];
            if(cur==null || cur.length()==0) continue;
            
            ArrayList naf_al = new ArrayList();
            int st = 1;
            //if(cur.length()>2) st = 2;
            
            for(int j=st;j<=4;j++)
            {    
                naf_al = searchTree(cur, j);
                if(naf_al.isEmpty()) continue;
                break;
            }
            
            if(naf_al.isEmpty())
            {
                System.out.println("kichu pay nai "+cur);
                if(correctedString.length()!=0) correctedString=correctedString+" "+cur;
                else correctedString=cur;
                prev = cur;
            }
            else {
                System.out.println("curr String" + cur);
                
                
//                naf = (myData) naf_al.get(0); //Will be commented 
//                bigramStr = naf.str;
                
                
                for(int j=0;(j<10 && j<naf_al.size());j++)
                {
                    naf = (myData) naf_al.get(j);
                    if(prev=="")
                    {
//                        String nxtWord[] = new String[1];
//                        nxtWord[0] = naf.str;
                        System.out.println("sugg word "+naf.str);
                        bigramStr = naf.str;
                        break;
                    }
                    else
                    {
//                        String nxtWord[] = new String[2];
//                        nxtWord[0] = prev; nxtWord[1] = naf.str;
                        System.out.println("sugg word "+naf.str);
                        ccVal = shahAutomata.getFrequency(correctedString+" "+naf.str);
                        System.out.println("ccVal for " + naf.str + " is "+ccVal + " // " + best);
                        if(best<-5.0 && ccVal>0.0)
                        {
                            bigramStr = naf.str;
                            break;
                        }
                        if(ccVal > best)
                        {
                            best = ccVal;
                            bigramStr = naf.str;
                        }
                    }
                }
               
//                    System.out.println("nafis str " + naf.str);
                if(correctedString.length()!=0) correctedString=correctedString+" "+bigramStr;
                else correctedString=bigramStr;
                prev = bigramStr;
            }
        }
        if(cnt==1)
        {
            if(correctedString.length()!=0) correctedString=correctedString+" "+word[word.length-1];
            else if(word[word.length-1]!=null || word[word.length-1].length()!=0) correctedString=word[word.length-1];
        }
        
        return correctedString;
    }
    
    ArrayList searchTree(String ss, int dis)
    {
        curStr = ss;
        res = new ArrayList();
        this.exMatch = 0;
        
        search(0, dis);
        
        if(this.exMatch==1)
        {
            res.add(new myData(ss, 100000));
        }
        
         try {
            Collections.sort(res, new Comparator<myData>() {
               public int compare(myData m1, myData m2){
                   if(m1.val > m2.val) return -1;
                   else return 1;
               } 
            });
         }catch(Exception ex) {
             System.out.println("Garbage in comparator");
         }
        return res;
    }
    
    void search(int cur, int dis)
    {
        int i, now;
        String nxt = (String) str.get(cur);
        int vv = (int) val.get(cur);
        myData md = new myData(nxt, vv);
//        System.out.println("search -> " + nxt + " / " + curStr);
        now = calcDist(curStr, nxt);
        if(now==0) exMatch = 1;
        if(now <= dis) res.add( md );
        
        for(i=(now-dis);i<=(now+dis);i++)
        {
            if(i<0 || i>=20) continue;
            if(adj[cur][i] == 0) continue;
            search(adj[cur][i], dis);
        }
        return;
    }
    
    public int calcDist(String s1, String s2)
    {
        int i, j;
        int len1 = s1.length(), len2 = s2.length();
        
        dp = new int[len1+2][len2+2];
        
        for(i=0;i<=len1;i++)
        {
            for(j=0;j<=len2;j++)
            {
                if(i*j==0) dp[i][j] = i+j;
                else
                {
                    if( s1.charAt(i-1) == s2.charAt(j-1) )
                        dp[i][j] = dp[i-1][j-1];
                    else
                    {
                        dp[i][j] = Math.min(dp[i-1][j]+1, dp[i][j-1]+1);
                        dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1]+1);
                    }
                }
            }
        }
        return dp[len1][len2];
    }
    
}
