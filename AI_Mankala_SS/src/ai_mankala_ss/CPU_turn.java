package ai_mankala_ss;

import java.util.Vector;

public class CPU_turn {
    
    private int inf= 1000000;
    private int mx_Depth;
    
    public class pair{
        int fs,sc; // heuristics value , now_turn
        pair(int a, int b)
        {
            fs=a;sc=b;
        }
    }
    
    int heuristicValue(Vector<Integer>v)
    {
        int cmpguti=0;for(int i=0;i<=6;i++) cmpguti+=v.get(i);
        int plaguti=0;for(int i=7;i<=13;i++) plaguti+=v.get(i);
        
        return   (cmpguti-plaguti);
    }
    pair simulate(Vector<Integer> v , int id  , int turn) // Heuristics Value , Next Turn
    {
            
        int heuristics_val=0; 
        int now_turn= 1- turn;
        
        int cmp_mankala=v.get(0);
        int pla_mankala=v.get(7);
                
        int val=v.get(id);
        v.set(id, 0);
        
        for(int j=id-1; val>0 ;val-- , j--)
        {
            j=(14+ j)%14;
            if( (turn==0 && j==7 ) || (turn==1 && j==0) )  {
                val++;continue;
            }
            
            v.set(j,v.get(j) +1 );
            
            if(val==1)
            {
                if(turn==0 && j>=1 && j<=6 && v.get(j)==1 && v.get(14-j)!=0 )
                {
                    v.set(0, v.get(0) + v.get(j) + v.get(14-j) );
                    
                    v.set(j,0);
                    v.set(14-j,0);
                }
                else if(turn==1 && j>=8 && j<=13 && v.get(j)==1 && v.get(14-j)!=0 )
                {
                    v.set(7, v.get(7) + v.get(j) + v.get(14-j) );
                    
                    v.set(j,0);
                    v.set(14-j,0);
                }
            }
            
            if(val==1 && j==7 && turn==1) now_turn=turn; // human player
            else if(val==1 && j==0 && turn==0) now_turn=turn; // cpu player
        }
        
        int now_val=0;for(int i=1;i<=6;i++) now_val+=v.get(i);
        if(now_val==0) for(int i=8;i<=13;i++) { v.set(7, v.get(i) + v.get(7) ); v.set(i, 0);}
        
        now_val=0;for(int i=8;i<=13;i++) now_val+=v.get(i);
        if(now_val==0) for(int i=1;i<=6;i++) { v.set(0, v.get(i) + v.get(0) ); v.set(i, 0);}
        
        heuristics_val= ( (v.get(0)-cmp_mankala) - ( v.get(7) - pla_mankala) );
        
        pair pp = new pair(heuristics_val, now_turn);
        return pp;// Heuristics Value , Next Turn
        
    }
    
    int best_move(Vector<Integer> v , int turn , int depth, int firstMove , int alpha , int beta )
    {
//        if(alpha>=beta) return 0;
        if(depth>mx_Depth) return heuristicValue(v);
        
        Vector<Integer> tmp = new Vector<>();
        for(int i=0;i<14;i++) tmp.add(0);
        
        pair pp;
        
        if(turn==0) {// computer turn
            
            int best_heu=-inf;
            if(firstMove!=-1)
            {
                for(int j=0;j<14;j++) tmp.setElementAt(v.get(j),j);
                pp=simulate( tmp, firstMove, turn);
               
                return best_move(tmp, pp.sc, depth + 1, -1,-inf,inf ); //+ pp.fs;
            }
            else {
                for(int i=1;i<7;i++)
                {
                    if(alpha>=beta) break;
                    if(v.get(i)==0) continue;
                    for(int j=0;j<14;j++) tmp.setElementAt(v.get(j),j);
                    pp=simulate( tmp, i, turn);
                    
                    int ret=best_move( tmp , pp.sc, depth +1 , firstMove , alpha , beta) ;// + pp.fs;
                    alpha=Math.max( alpha ,ret);
                    
                    best_heu = Math.max(best_heu , ret );
                }
            }
            if(best_heu==-inf) return heuristicValue(v);
            return best_heu;
        }
        else { // player turn
            int best_heu=inf;
            for(int i=8;i<14;i++)
            {
                if(alpha>=beta) break;
                if(v.get(i)==0) continue;
                for(int j=0;j<14;j++) tmp.set(j,v.get(j));
                pp=simulate( tmp, i, turn);
                
                int ret=best_move( tmp , pp.sc, depth +1 , firstMove , alpha , beta) ;// + pp.fs;
                beta=Math.min(beta, ret);
                best_heu = Math.min(best_heu ,ret );
            }            
            if(best_heu==inf) return heuristicValue(v);
            return best_heu;
        }
    }
    
    int cpuBestMove( int depth )
    {
        mx_Depth=depth;
        int best=-inf , choice=1 , tmp;
        for(int i=1;i<7;i++)
        {
            if(main_Frame.arr.get(i)==0) continue;
            tmp=best_move( main_Frame.arr, 0, 0,i,-inf,inf);
            System.out.println("for move "+i+" found heuristics "+tmp);
            if(best<tmp) { best=tmp ; choice=i; }
        }
//        System.out.println("CPU Choice "+choice);
        return choice;
    }
    
    int observationMove(Vector<Integer>v)
    {
        int mx = -1;
        int choice = 1;
        int mid = 0;
        for(int i=6;i>0;i--)
        {
            if(v.get(i)!=0)
            {
                choice = i;
                System.out.println("1");
            }
        }
        for(int i=1;i<7;i++)
        {
            if(v.get(i)==i)
            {
                System.out.println("2");
                mx = 1;
                choice = i;
            }
        }
        for(int i=1;i<7;i++)
        {
            int val = v.get(i);
            Vector<Integer> m = new Vector(14);
            for(int k=0;k<14;k++) m.add(v.get(k));
            for(int j=i-1; val>0 ;val-- , j--)
            {
                j=(14+ j)%14;
                if(j==7)  {
                    val++;continue;
                }
                
                v.set(j,v.get(j) +1 );
            
                if(val==1)
                {
                    if(j>=1 && j<=6 && v.get(j)==1 && v.get(14-j)!=0 )
                    {
                        v.set(0, v.get(0) + v.get(j) + v.get(14-j) );
                        if(v.get(0)>mx)
                        {
                            mx = v.get(0);
                            choice = i;
                        }
                        v.set(j,0);
                        v.set(14-j,0);
                    }
                }
            }
            for(int k=0;k<14;k++) v.set(k, m.get(k));
        }
        return choice;
    }
}
