package ai_mankala_ss;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;


public class main_Frame extends javax.swing.JFrame {

private ImageIcon img = new ImageIcon("background.jpg");
private JLabel bg=new JLabel(img);
private JLabel status=new JLabel("Status");

final JLabel boxx[] = new javax.swing.JLabel[14];
private int difficulty;

static Vector<Integer> arr = new Vector<>();

Boolean playerTurn = true;
Boolean computerTurn=false;
Boolean change=false;

CPU_turn cpobject= new CPU_turn();

    public static int getNum(JLabel jb)
    {
        return Integer.parseInt(jb.getText());
    }
    
    public static void setNum(JLabel jb, int val)
    {
        jb.setText(""+val);
    }
    public void beautify(int iid , int ttrn ,int nextttrn)
    {
        final int id=iid;
        final int turn=ttrn;
        final int nexturn=nextttrn;
        
        final Timer ti = new Timer(0, null);
        ti.addActionListener(new ActionListener() {

        int j = id ;
        int val=getNum(boxx[id]) + 1;
        public void actionPerformed(ActionEvent e ) {
            
            j=(14+ j)%14;
            
//            System.out.println("J "+j);
            if( (turn==0 && j==7 ) || (turn==1 && j==0) )  {
                
            }
            else if(val==0)
            {
                for(int i=0;i<14;i++) {setNum(boxx[i], arr.get(i));boxx[i].setForeground(Color.white);}
                ti.stop();
                if(turn==0 && nexturn==0) doActionComp();
                if( turn==1 && nexturn==0) 
                {
                    status.setText("Computer's Turn");
                    change=true;
                    doAction(0);
                }
                if(nexturn==0) status.setText("Computer's Turn");
                else status.setText("Your Turn");
                
                if(arr.get(0) + arr.get(7) ==48) 
                {
                    if(arr.get(0)>24 ) status.setText("Computer WON !!!");
                    else if(arr.get(7)>24) status.setText("You WON !!!");
                    else status.setText("DRAW Game !!!");
                }
            }
            else {
                if(turn==0) status.setText("Computer's Turn");
                else status.setText("Your Turn");
                
                setNum(boxx[j], arr.get(j));
                if(turn==0) boxx[j].setForeground(Color.RED);
                else boxx[j].setForeground(Color.GREEN);
                --val;
            }
            --j;

            }
        });
        
        if(turn==1) ti.setDelay(500);
        else ti.setDelay(1000);
        ti.start();
        
//        for(int i=0;i<14;i++) {setNum(boxx[i], arr.get(i));boxx[i].setForeground(Color.white);}
            
    }
    
    public void doActionComp()
    {
        if(arr.get(0) + arr.get(7) ==48) 
        {
            if(arr.get(0)>24 ) status.setText("Computer WON !!!");
            else if(arr.get(7)>24) status.setText("You WON !!!");
            else status.setText("DRAW Game !!!");
        }
        
        CPU_turn.pair pp ;
        int cpu_move;
        if(difficulty==0) cpu_move= cpobject.observationMove(arr); // Easy Version
        else if( difficulty==2 ) cpu_move=cpobject.cpuBestMove(14); // Hard Version
        else cpu_move=cpobject.cpuBestMove(7);                     // Medium Version
        
        System.out.println("CPU CHOICE "+cpu_move);
        pp=cpobject.simulate(arr, cpu_move, 0); //computer
        beautify(cpu_move, 0 , pp.sc);
        return;
    }
    public void doAction(int id)
    {
        if(arr.get(0) + arr.get(7) ==48) 
        {
            if(arr.get(0)>24 ) status.setText("Computer WON !!!");
            else if(arr.get(7)>24) status.setText("You WON !!!");
            else status.setText("DRAW Game !!!");
        }
        if(playerTurn)
        {
            if(!change)
            {
                playerTurn = false;
                CPU_turn.pair pp ;

                pp=cpobject.simulate(arr, id, 1);// human turn
                beautify(id,1,pp.sc);

                if(pp.sc==1) 
                {
                    playerTurn=true;
                    return;
                }
            }

            if(change) 
            {
                status.setText("Computer's Turn");
                doActionComp();
            }
            
            playerTurn=true;

        }
    }
    
    
    public main_Frame(int newDifficulty) {
       
        difficulty=newDifficulty;
        initComponents();
        
        System.out.println("Difficulty "+ difficulty);
        
        super.setContentPane(bg);
        setLocationRelativeTo(null);
        status.setFont( new Font("Tahoma", Font.BOLD , 15) );
        status.setBounds(285, 250, 300, 40);
        status.setForeground(Color.YELLOW);
        
        int gap=80;
        int x=45,y=55;
        int labWidth=55,labHeight=55;
        int midY=35;
        

        for(int i=0;i<14;i++) 
        {
            if(i==0 || i==7) arr.add(0);// prvArr.add(0);}
            else  arr.add(4); //prvArr.add(4);}
        }
        
        for(int  i=0;i<14;i++)
        {
            final JLabel jb=new JLabel("4");
            boxx[i] = jb;
            boxx[i].setForeground(Color.white);
            
            boxx[i].setFont(new Font("Cambria", Font.BOLD, 40));
            if(i==0)
            {
                boxx[i].setText("0");
                boxx[i].setBounds(x, y+midY, labWidth, labHeight);
            }
            else if(i>=1 && i<7) 
            {
                x+=gap;
                boxx[i].setBounds(x, y, labWidth, labHeight);
            }
            else if(i==7)
            {
                boxx[i].setText("0");
                x+=gap;
                boxx[i].setBounds(x, y+midY, labWidth, labHeight);
                y+=gap;
            }
            else {
                x-=gap;
                boxx[i].setBounds(x, y, labWidth, labHeight);
            }
            boxx[i].setName(""+i);
            
            boxx[i].addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        
                        int id=Integer.parseInt(jb.getName());
                        if(id<8) return;
                        if(getNum(boxx[id])==0) return ;
                        
                        System.out.println("JLabel id: " + id);
                        change=false;
                        doAction(id);
                       
                    }
                }
                    
            );
           
            add(boxx[i]);
        }
        add(status);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension( Toolkit.getDefaultToolkit().getScreenSize().width/2 ,Toolkit.getDefaultToolkit().getScreenSize().height/2) );
        getContentPane().setLayout(null);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main_Frame(0).setVisible(true);
            }
            
            
            
            
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

