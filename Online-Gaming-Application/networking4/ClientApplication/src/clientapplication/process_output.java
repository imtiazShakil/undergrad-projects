package clientapplication;

import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public  class process_output {
    
    JLabel opponent_id;
    JList playerList;
    JTextField me;
    JTextArea chatArea;
    
    JPanel gamePanel;
    JButton inviteButton;
    
    String message,myIp;
    StartGame newGame=null;
    JLabel my_score;
    JLabel opp_score;
    JLabel game_status;
    JButton guess;
    JTextField my_num;
    JLabel opp_pre_num;
    boolean myTurn = false;
    int starting = 0;
    boolean cnfrm = false;
    JButton confirm;
    public process_output(JLabel opponent_id, JList playerList, JTextField me, JTextArea enemy, JLabel my_score,JLabel opp_score,JLabel game_status,JButton guess,JTextField my_num,JLabel opp_pre_num,JButton confirm , JPanel gamePanel, JButton inviteButton)
    {
        this.opponent_id = opponent_id;
        this.playerList = playerList;
        this.me = me;
        this.chatArea = enemy;
        this.my_score = my_score;
        this.opp_score = opp_score;
        this.game_status = game_status;
        this.guess = guess;
        this.my_num = my_num;
        this.opp_pre_num = opp_pre_num;
        my_num.setColumns(5);
        this.confirm = confirm;
        
        this.gamePanel=gamePanel;
        this.inviteButton=inviteButton;
    //    this.myIp=myIp;
    }
    
    public static int get_integer(String str)
    {
        if(str==null) return 0;
//        str="00"+str;
        str=str.replaceAll("[^\\d]", " ");
        
        if(str==null) return 0;
        if(str=="") return 0;
        
        String arr[]=str.split(" ");
        String tmp="";
        for(int i=0;i<arr.length;i++) tmp+=arr[i];
        if(tmp=="") tmp+="0";
        return Integer.parseInt( tmp );
    }
    
    public void confirmButton() throws IOException
    {
        if(cnfrm==false)
        {
            newGame.set_data(my_score, opp_score, game_status, guess);    
            newGame.game_process(starting);            
            if(starting != 2) starting = 2;
            Integer[] data = newGame.get_data();
            
            
            my_score.setText(data[0].toString());
            opp_score.setText(data[1].toString());
            opp_pre_num.setText(data[2].toString());
            
            
            ClientView.talkWithServer("opp_Score "+opponent_id.getText()+" "+data[0].toString());
            ClientView.talkWithServer("my_Score "+opponent_id.getText()+" "+data[1].toString());
            
            
            int mySc = get_integer(data[0].toString());
            int oppSc = get_integer(data[1].toString());
            if(mySc<=0 || oppSc<=0)
             newGame.check_result(mySc,oppSc);
            
            cnfrm = true;
            my_num.setVisible(true);
        }
        else
        {
            if(starting != 2) starting = 2;
            cnfrm = false;
            my_num.setVisible(false);
            confirm.setVisible(false);
            change_Turn(myTurn);
            
            int val=get_integer(my_num.getText());
            String num=String.valueOf(val);
            
            ClientView.talkWithServer("opp_Number "+opponent_id.getText()+" "+num);
            
        }
        
        
    }
    
    private void change_Turn(boolean my)
    {
        if(my) game_status.setText("YOUR TURN");
        else game_status.setText("OPPONENT'S TURN");
        myTurn = !myTurn;
    }
    
    public void changeButton(boolean even)
    {
        if(even) guess.setText("EVEN");
        else  guess.setText("ODD");
    }
    
    public void setIp(String myIp)
    {
        this.myIp=myIp;
        return ;
    }
    private void ini_gui(String str)
    {
        if(str.startsWith("Start"))
        {
            System.out.println("start");
            starting = 1; myTurn = true;cnfrm = true;change_Turn(myTurn);
            my_num.setVisible(true);confirm.setVisible(true);
        }
        else if(str.startsWith("Wait"))
        {
            System.out.println("wait");
            starting = 0; myTurn = false;cnfrm= false;change_Turn(myTurn);
            my_num.setVisible(false);confirm.setVisible(false);
        }
    }
    public void take_action(String str)
    {
        message=str;
        System.out.println("Ordered to Take Action "+ str);
        
        if(str.startsWith("SHOW")) updatePlayerList();
        else if(str.startsWith("Start")||str.startsWith("Wait")) ini_gui(str);
        else if(str.startsWith("IncomingRequest")) incomingRequest();
        else if(str.startsWith("PlayBetween")) canPlay();
        else if(str.startsWith("NO")) cantPlay();
        else if(str.startsWith("MessageFrom")) showEnemyMessage();
        else if(str.startsWith("my_Score")||str.startsWith("opp_Score")) update_data(str);
        else if(str.startsWith("you_lose")) newGame.you_lose_turn();
        else if(str.startsWith("you_win")) newGame.you_win_turn();      
        else if(str.startsWith("opp_Number")) update_opp_num(str);
        else if(str.startsWith("WIN")||str.startsWith("LOSE")||str.startsWith("TIE"))newGame.game_over(str);
        
    }
    
    private void update_opp_num(String str)
    {
            String arr[]=str.split("\\s+");
            System.out.println("Assigning OppNumber: "+ arr[2]);
            StartGame.oppNumber = arr[2];
            change_Turn(myTurn);
            confirm.setVisible(true);
    }
    private void update_data(String str)
    {
        
        System.out.println("Update_data: "+ str);
        String arr[]=str.split("\\s+");
        
        if(arr[0].startsWith("opp_Score"))
            opp_score.setText(arr[2]);
        else if(arr[0].startsWith("my_Score")) my_score.setText(arr[2]);
        
    }
    
    public String[] parse_clients(String query)
    {
        String arr[]=query.split("\\s+");
       
        for(int i=0;i<arr.length;i++) 
            System.out.println(arr[i]);
        return arr;
    }
    private void incomingRequest()
    {
        String arr[]=parse_clients(message);
        
        Object[] options = {"Ok",
                    "No,I Don't Accept"};
        
        int n = JOptionPane.showOptionDialog( null ,
            arr[1]+"   Send you an invitation to join him ."
            + " Will you Accept?",
            "Incoming Join Request",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[1]);
        
        try{
            if(n==0)
            {
                ClientView.talkWithServer("Confirm "+arr[1] );
            }
        }catch(IOException e) {
            System.out.println("Couldn't Confirm Two Players meeting");
        }
    
    }
    private void updatePlayerList()
    {
        
        DefaultListModel resultList = new DefaultListModel();
        String arr[];
        
        arr=parse_clients(message);
        for(int i=1;i<arr.length;i++) resultList.addElement(arr[i]);
        playerList.setModel(resultList);    
        
        return;
    }
    
    private void cantPlay()
    {
        System.out.println("This Player is Busy");
        opponent_id.setText("This Player is Busy");
        return ;
    }
    private void canPlay()
    {
        
        System.out.println("Yahhho Can Talk");
        String arr[]=parse_clients(message);
        String enemy_ip=arr[2];
        
        opponent_id.setText(enemy_ip);
        
        Object[] options = {"Ok"};
        
        int n = JOptionPane.showOptionDialog( null ,
            "  You Are Now Connected With "+ enemy_ip ,
            "Congratulations",
            JOptionPane.INFORMATION_MESSAGE,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]);
        
        newGame= new StartGame( myIp , opponent_id.getText() , me, chatArea);
        playerList.setEnabled(false);
        inviteButton.setEnabled(false);
        gamePanel.setVisible(true);
        
        return ;
    }
    
    private void showEnemyMessage()
    {
        String arr[]=parse_clients(message);
        String disp="";
        for(int i=2;i<arr.length;i++) disp=disp+" "+arr[i];
        
        chatArea.append("Opponent : "+ disp +"\n------------------------\n");
        
    }
    
    
    public  void restart()
    {
        newGame=null;
        my_score.setText("20");
        opp_score.setText("20");
        opp_pre_num.setText("Number");
        my_num.setText("NUMBER");
        
        
        playerList.setEnabled(true);
        inviteButton.setEnabled(true);
        gamePanel.setVisible(false);
        chatArea.setText("");
        
        try {
            ClientView.talkWithServer("show");

        }catch(IOException e) {
            
        }
    }
    
}
