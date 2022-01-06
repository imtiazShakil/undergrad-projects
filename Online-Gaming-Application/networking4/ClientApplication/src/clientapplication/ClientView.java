
package clientapplication;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Action;

import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;
import java.net.UnknownHostException;
import javax.net.ssl.SSLEngineResult.Status;

import javax.swing.DefaultListModel;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ClientView extends FrameView {

    public Socket sck;

    static DataInputStream din=null;
    static  DataOutputStream dout=null;
    StartGame sg;
    static process_output todo;
    
    public ClientView(SingleFrameApplication app) {
        super(app);
        sck=null;dout=null;din=null;
        initComponents();
        todo = new process_output(Player2, Players, User, chatArea,my_score,opp_score,game_status,guess,my_num,opp_pre_num,confirm , gamePanel, Play);
        
    }

    public void allClear()
    {
        try {
            din.close();dout.close();
            sck.close();
            
        }catch(IOException e) {
            System.out.println("Closing Error");
        }
    }
    
    public void get_connection(String userName, int port) throws Exception
    {
        System.out.println(userName +"  "+ port);
//        try {
            
        
            sck = new Socket("ASPIRE4736", port); 
            
            din= new DataInputStream(sck.getInputStream());
            dout= new DataOutputStream(sck.getOutputStream());
            todo.setIp(sck.getInetAddress().toString()); 
            
           
            System.out.println("Client Connected With The server");
            notification_from_server nfs= new notification_from_server(din);
            
            talkWithServer("MyUserName "+userName);
            
            
            try{
                Players.setEnabled(true);
                ClientView.talkWithServer("show");
                Play.setEnabled(true);
            }catch(IOException e) {
                
                Object[] options = {"Ok"};
        
                int n = JOptionPane.showOptionDialog( null ,
                "Sorry The Server Is Down.",
                "Failed To Communicate",
                JOptionPane.YES_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
            }
        
//        } catch (UnknownHostException ex) {
//            System.out.println("Client has connection problem "+ex.toString());
//        } catch (IOException ex) {
//            System.out.println("IOException in client "+ex.toString());
//        }catch(UnknownError e) {
//            System.out.println("Don't know what happened");
//            System.exit(1);
//        }
        
    }
    
    public static  void talkWithServer(String query) throws IOException
    {
        System.out.println("Telling Server "+query);
        String fromServer=null, fromClient;
        
        fromClient=query;

        dout.writeUTF(fromClient);
                
        return ;
    }
  
    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = ClientApplication.getApplication().getMainFrame();
            aboutBox = new ClientAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        ClientApplication.getApplication().show(aboutBox);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Players = new javax.swing.JList();
        UpdatePlayers = new javax.swing.JButton();
        Player2 = new javax.swing.JLabel();
        Play = new javax.swing.JButton();
        gamePanel = new javax.swing.JPanel();
        guess = new javax.swing.JButton();
        confirm = new javax.swing.JButton();
        game_status = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        my_num = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        opp_pre_num = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        opp_score = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        my_score = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        User = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        chatArea = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        UserName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        PortNumber = new javax.swing.JTextField();
        Connect = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(clientapplication.ClientApplication.class).getContext().getResourceMap(ClientView.class);
        mainPanel.setBackground(resourceMap.getColor("mainPanel.background")); // NOI18N
        mainPanel.setBorder(new javax.swing.border.MatteBorder(null));
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(780, 410));
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        Players.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Player's Ip Addresses", "Will be Shown", "Here" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        Players.setVisible(true);
        Players.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Players.setName("Players"); // NOI18N
        Players.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                PlayersValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(Players);

        mainPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 240, 228, 123));

        UpdatePlayers.setText(resourceMap.getString("UpdatePlayers.text")); // NOI18N
        UpdatePlayers.setVisible(false);
        UpdatePlayers.setName("UpdatePlayers"); // NOI18N
        UpdatePlayers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdatePlayersActionPerformed(evt);
            }
        });
        mainPanel.add(UpdatePlayers, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 195, -1, -1));

        Player2.setForeground(resourceMap.getColor("Player2.foreground")); // NOI18N
        Player2.setText(resourceMap.getString("Player2.text")); // NOI18N
        Player2.setName("Player2"); // NOI18N
        mainPanel.add(Player2, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 199, -1, -1));

        Play.setText(resourceMap.getString("Play.text")); // NOI18N
        Play.setName("Play"); // NOI18N
        Play.setVisible(true);
        Play.setEnabled(false);
        Play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayActionPerformed(evt);
            }
        });
        mainPanel.add(Play, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 195, -1, -1));

        gamePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        gamePanel.setName("game_panel"); // NOI18N
        gamePanel.setVisible(false);
        gamePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        guess.setText(resourceMap.getString("guess.text")); // NOI18N
        guess.setName("guess"); // NOI18N
        guess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guessActionPerformed(evt);
            }
        });
        gamePanel.add(guess, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, -1, -1));

        confirm.setText(resourceMap.getString("confirm.text")); // NOI18N
        confirm.setName("confirm"); // NOI18N
        confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmActionPerformed(evt);
            }
        });
        gamePanel.add(confirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 196, -1, -1));

        game_status.setFont(resourceMap.getFont("game_status.font")); // NOI18N
        game_status.setForeground(resourceMap.getColor("game_status.foreground")); // NOI18N
        game_status.setText(resourceMap.getString("game_status.text")); // NOI18N
        game_status.setName("game_status"); // NOI18N
        gamePanel.add(game_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 305, -1, -1));

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        gamePanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 308, -1, -1));

        my_num.setText(resourceMap.getString("my_num.text")); // NOI18N
        my_num.setName("my_num"); // NOI18N
        my_num.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                my_numActionPerformed(evt);
            }
        });
        gamePanel.add(my_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 136, 57, -1));

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        gamePanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 139, -1, -1));

        opp_pre_num.setText(resourceMap.getString("opp_pre_num.text")); // NOI18N
        opp_pre_num.setName("opp_pre_num"); // NOI18N
        gamePanel.add(opp_pre_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 110, -1, 20));

        //jLabel6.setVisible(false);
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        gamePanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 113, -1, -1));

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        gamePanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 66, -1, -1));

        opp_score.setText(resourceMap.getString("opp_score.text")); // NOI18N
        opp_score.setName("opp_score"); // NOI18N
        gamePanel.add(opp_score, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 38, -1, -1));

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        gamePanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 38, -1, -1));

        my_score.setText(resourceMap.getString("my_score.text")); // NOI18N
        my_score.setName("my_score"); // NOI18N
        gamePanel.add(my_score, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 13, -1, -1));

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        gamePanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, -1, -1));

        User.setText(resourceMap.getString("User.text")); // NOI18N
        User.setName("User"); // NOI18N
        User.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserActionPerformed(evt);
            }
        });
        gamePanel.add(User, new org.netbeans.lib.awtextra.AbsoluteConstraints(244, 258, 210, -1));

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        chatArea.setColumns(20);
        chatArea.setEditable(false);
        chatArea.setFont(resourceMap.getFont("chatArea.font")); // NOI18N
        chatArea.setRows(7);
        chatArea.setWrapStyleWord(true);
        chatArea.setName("chatArea"); // NOI18N
        jScrollPane2.setViewportView(chatArea);

        gamePanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(244, 27, 210, 213));

        mainPanel.add(gamePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(277, 28, 480, 330));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        UserName.setText(resourceMap.getString("UserName.text")); // NOI18N
        UserName.setName("UserName"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        PortNumber.setText(resourceMap.getString("PortNumber.text")); // NOI18N
        PortNumber.setName("PortNumber"); // NOI18N

        Connect.setText(resourceMap.getString("Connect.text")); // NOI18N
        Connect.setName("Connect"); // NOI18N
        Connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConnectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(UserName, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(PortNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(Connect)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(UserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(PortNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Connect)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        mainPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 28, -1, -1));

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem1);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(clientapplication.ClientApplication.class).getContext().getActionMap(ClientView.class, this);
        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setComponent(mainPanel);
        setMenuBar(menuBar);
    }// </editor-fold>//GEN-END:initComponents

    private void ConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConnectActionPerformed
        String nm=UserName.getText();
        String po=PortNumber.getText();
        
        
        try {
            get_connection( nm,process_output.get_integer(po) );
        }catch(Exception e) {
            Object[] options = {"Ok"};
        
            int n = JOptionPane.showOptionDialog( null ,
            "Sorry The Server Is Down.",
            "Failed To Login",
            JOptionPane.YES_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
        }
        
        try{
            talkWithServer(nm);
        }catch(Exception e) {
            
        }
        
        if(sck!=null) {
            UpdatePlayers.setVisible(true);
            Connect.setVisible(false);
        }      
        
        
    }//GEN-LAST:event_ConnectActionPerformed

    private void UpdatePlayersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdatePlayersActionPerformed
       

        try {
            talkWithServer("show");
        } catch(IOException e) {
            System.out.println("failed to request the server show");
        }
        
    }//GEN-LAST:event_UpdatePlayersActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        
        try {
            if(sck!=null) {
                talkWithServer("exit");
                allClear();
            }
        }catch(IOException e) {
            System.out.println("Failed to Exit");
        }
        System.exit(0);
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void PlayersValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_PlayersValueChanged
        
        try {
            String str=Players.getSelectedValue().toString();
            if(str!=null) Player2.setText(  str );
        }catch(NullPointerException e) {
            System.out.println("Label er vitore kno jhmaela hoise vai handle koro");
        }
    }//GEN-LAST:event_PlayersValueChanged

    
    private void PlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayActionPerformed
        String str;
        if(Player2.getText()==null) return;
        try{
            talkWithServer("Invite " + Player2.getText() );
            
        }catch(IOException e) {
           
        }
    }//GEN-LAST:event_PlayActionPerformed

    private void UserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserActionPerformed
//        sg=todo.newGame;
        if(todo.newGame!=null)
        {
            todo.newGame.start_chat();
            
        }
        
    }//GEN-LAST:event_UserActionPerformed

    private void confirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmActionPerformed
        try {
            todo.confirmButton();
        } catch (IOException ex) {
            System.out.println("Problem with confirm button");
        }
    }//GEN-LAST:event_confirmActionPerformed
    boolean even = true;
    private void guessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guessActionPerformed
        if(even==true) even = false;
        else if(even==false) even = true;
        todo.changeButton(even);
    }//GEN-LAST:event_guessActionPerformed

    private void my_numActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_my_numActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_my_numActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Connect;
    private javax.swing.JButton Play;
    private javax.swing.JLabel Player2;
    private javax.swing.JList Players;
    private javax.swing.JTextField PortNumber;
    private javax.swing.JButton UpdatePlayers;
    private javax.swing.JTextField User;
    private javax.swing.JTextField UserName;
    private javax.swing.JTextArea chatArea;
    private javax.swing.JButton confirm;
    private javax.swing.JPanel gamePanel;
    private javax.swing.JLabel game_status;
    private javax.swing.JButton guess;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTextField my_num;
    private javax.swing.JLabel my_score;
    private javax.swing.JLabel opp_pre_num;
    private javax.swing.JLabel opp_score;
    // End of variables declaration//GEN-END:variables

    private JDialog aboutBox;
}
