package clientapplication;

import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class StartGame {
    int initialScore=20;
    int bet;
    String currentHost,OpponentHost;
    JTextField user;//,opponent;
    JTextArea chatArea;
    
    public StartGame(String a, String b, JTextField jb1 , JTextArea chatArea) {
        currentHost=a ;
        OpponentHost=b;
        user=jb1;
//        opponent=jb2;
        this.chatArea=chatArea;
    }
    String myScore,oppScore, status,toggle,myNumber,pre;
    public void set_data(JLabel my_score,JLabel opp_score,JLabel game_status,JButton guess)
    {
        myScore = my_score.getText();
        oppScore = opp_score.getText();
        status = game_status.getText();
        toggle = guess.getText();
        System.out.println("printing myScore: "+ myScore);   
    }
    
    public void check_result(int mySc,int oppSc) throws IOException
    {
        System.out.println("checking_result");
        {
            String msg = "";
            String send = "";
            System.out.println("mySc "+mySc+" OPpSC: "+oppSc);
            if(mySc<oppSc)
            {
                System.out.println("your opponent win");
                msg += "YOU LOSE";
                send += "WIN";
            }
            else if(mySc>oppSc)
            {
                System.out.println("you win");
                msg += "YOU WIN";
                send += "LOSE";
            }
            else
            {
                System.out.println("tie");
                msg += "GAME IS TIE";
                send += "TIE";
            }
            System.out.println("msg: "+msg+" send: "+send);
            ClientView.talkWithServer(send+" "+OpponentHost+" send ");
            game_over(msg);
        }
        
    }
    
    public void game_over(String msg)
    {
        Object[] options = {"OK","RESTART"};
        
        int n = JOptionPane.showOptionDialog( null ,msg,
            "GAME OVER",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[1]);
        
        try{
            ClientView.talkWithServer("unmap");
        }catch(IOException e) {
            
        }
        
        ClientView.todo.restart();
        
    }
    
    Integer opp_Number,opp_Score,my_Score,opp_Pre_Number;
    public static String oppNumber = "20";
    public void game_process(int starting) throws IOException
    {
        System.out.println("game process status "+ status + " Starting : "+ starting);
         if(starting==1)
         {
                opp_Number = new Integer(oppNumber);
                my_Score = new Integer(myScore);
                opp_Score = new Integer(oppScore);
         }
        else if(status.startsWith("YOUR TURN"))
        {
            System.out.println("kahini ki");
            opp_Number = new Integer(oppNumber);
            my_Score = new Integer(myScore);
            opp_Score = new Integer(oppScore);
            int flag = (opp_Number%2);
            System.out.println("kahini ki");
            if((flag==1&&toggle.equalsIgnoreCase("odd"))||(flag==0&&toggle.equalsIgnoreCase("even")))
            {
                my_Score += opp_Number;
                opp_Score -= opp_Number;
                ClientView.talkWithServer("you_lose "+OpponentHost+" send");
                you_win_turn();
                
            }
            else
            {
                my_Score -= opp_Number;
                opp_Score += opp_Number;
                ClientView.talkWithServer("you_win "+OpponentHost+" send ");
                you_lose_turn();
                
            }
            System.out.println("kahini ki");
        }
    }

     public Integer[] get_data() {
        Integer[] rpl = new Integer[3];
        rpl[0] = my_Score;
        rpl[1] = opp_Score;
        rpl[2] =opp_Number;
        return rpl;
    }
     
    public void start_chat() {
            
            String me=user.getText();
            if(me==null) return;
            try {
                ClientView.talkWithServer("Talking "+OpponentHost+" "+me);
                chatArea.append("Me : "+ me +"\n---------------------\n");
                user.setText("");
            }catch(IOException e) {
            }

    }

    public  void you_win_turn() {
        Object[] options = {"OK"};
        
        int n = JOptionPane.showOptionDialog( null ,"Yahoooo! "+"YOU WIN THIS TURN",
            "Game Info",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]);
        
    }

    public void you_lose_turn() {
        Object[] options = {"OK"};
        
        int n = JOptionPane.showOptionDialog( null ,"oooops! "+"YOU LOSE THIS TURN",
            "Game Info",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]);
    }

}
