/*
 * Test_j_labelView.java
 */

package test_j_label;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JLabel;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * The application's main frame.
 */
class make_edge
{
    int to;
    int incon1,incon2,outcon;

    public make_edge() {
        to=0;incon1=0;incon2=0;outcon=0;
    }
}

public class Test_j_labelView extends FrameView {

    Gate[] objects = new Gate[100];
    int indx=0;
    public Test_j_labelView(SingleFrameApplication app) {
        super(app);
        initComponents();
    }
    
    make_edge[][] con = new make_edge[100][100];
    int len[] = new int[100];
    
    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = Test_j_labelApp.getApplication().getMainFrame();
            aboutBox = new Test_j_labelAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        Test_j_labelApp.getApplication().show(aboutBox);
    }

    
    //@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(test_j_label.Test_j_labelApp.class).getContext().getResourceMap(Test_j_labelView.class);
        mainPanel.setBackground(resourceMap.getColor("mainPanel.background")); // NOI18N
        mainPanel.setBorder(new javax.swing.border.MatteBorder(null));
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(300, 200));
        mainPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainPanelMouseClicked(evt);
            }
        });
        mainPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                mainPanelMouseMoved(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 653, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 636, Short.MAX_VALUE)
        );

        menuBar.setBackground(resourceMap.getColor("menuBar.background")); // NOI18N
        menuBar.setFont(resourceMap.getFont("menuBar.font")); // NOI18N
        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(test_j_label.Test_j_labelApp.class).getContext().getActionMap(Test_j_labelView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N
        statusPanel.setPreferredSize(new java.awt.Dimension(400, 40));

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setName("jButton6"); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setName("jButton7"); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText(resourceMap.getString("jButton8.text")); // NOI18N
        jButton8.setName("jButton8"); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addGap(255, 255, 255)
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton5)
                    .addComponent(jButton4)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addGap(214, 214, 214)
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void mainPanelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainPanelMouseMoved
       draw_edges();
    }//GEN-LAST:event_mainPanelMouseMoved
    
    public void do_it(JLabel lb1, JLabel lb2, int inpVal ,int wire_col)
    {
        Point st = lb1.getLocation();
        st.x +=45; st.y+=25;
        Point end = lb2.getLocation();
        if(inpVal==1)
            end.y+=15;
        else
            end.y+=35;
        
        Color cl=Color.BLACK;
        if(wire_col==0)  cl= Color.BLACK;
        else if(wire_col==1) cl = Color.RED;
        
        draw(mainPanel.getGraphics() , st , end ,cl );
        
    }
    private void draw(Graphics g, Point start, Point end , Color cl)
    {
        g.setColor(cl);
        
               
        g.fillRect(start.x, start.y, 5, 5);
        g.drawLine(start.x, start.y, end.x, end.y);
        g.fillRect(end.x, end.y, 5, 5);
    }
    
    public void draw_edges()
    {
        int wire_col,which=0;
        for(int i=0;i<indx;i++)
        {
            for(int j=0;j<len[i];j++)
            {
                if(con[i][j].incon1==1) which=1;
                else if(con[i][j].incon2==1) which=2;
                wire_col=0;
                if( objects[i].output==1 ) wire_col=1;
                do_it( objects[i].lb , objects[ con[i][j].to ].lb, which, wire_col);
            }
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        //AND GATE
        JLabel jb = new javax.swing.JLabel();
        mainPanel.add(jb);
        final_and_gate obj = new final_and_gate(jb);
        objects[indx]=obj;indx++;
        draw_edges();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void mainPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainPanelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_mainPanelMouseClicked

    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //OR GATE
        JLabel jb = new javax.swing.JLabel();
        mainPanel.add(jb);
        orGate obj = new orGate(jb);
        objects[indx]=obj;indx++;
        draw_edges();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //NOT GATE
        JLabel jb= new javax.swing.JLabel();
        mainPanel.add(jb);
        NotGate obj = new NotGate(jb);
        objects[indx]=obj;indx++;
        draw_edges();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //SWITCH GATE
        JLabel jb= new javax.swing.JLabel();
        mainPanel.add(jb);
        Switch obj = new Switch(jb);
        objects[indx]=obj;indx++;
        draw_edges();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //WIRE CONNECTION
        make_edge obj= new make_edge();
        if(indx>=2)
        {
            int fnd=0;
            Gate V = objects[0];
            JLabel u=objects[0].lb,v=objects[1].lb;
            for(int i=0;i<indx;i++)
            {
                if(objects[i].inflag==1) 
                {
                    fnd++;
                    if(objects[i].inpCon1==1) obj.incon1=1;
                    if(objects[i].inpCon2==1) obj.incon2=1;
                    obj.to=i;
                    
                    v=objects[i].lb;
                    V = objects[i];
                    //objects[i].allClear();
                }
                if(objects[i].outflag==1) 
                {
                    fnd++;
                    u=objects[i].lb;
                    objects[i].allClear();
                    con[i][ len[i] ]=obj;
                    len[i]++;
                }
            }
            int gg;
            System.out.println("vv "+V.inpCon1+" "+V.inpCon2);
            if(V.inpCon1==1) gg=1;
            else gg=2;
            if(fnd==2) do_it(u, v, gg,0);
            V.allClear();
        }
        
        draw_edges();
    }//GEN-LAST:event_jButton4ActionPerformed

    
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        //LED
        JLabel jb= new javax.swing.JLabel();
        mainPanel.add(jb);
        Led_indicator obj = new Led_indicator(jb);
        objects[indx]=obj;indx++;
        draw_edges();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        //CHECK WIRE FOR DUBGGING PURPOSE
        draw_edges();
        for(int i=0;i<indx;i++)
        {
            System.out.println("for index "+ i + " type "+objects[i].about_me() );
            for(int j=0;j<len[i];j++)
            {
                make_edge ob=con[i][j];
                System.out.println("incn1 "+ ob.incon1 + " incn2 "+ob.incon2 +  " outcn "+ ob.outcon + " to index "+ob.to + "  "+ objects[ob.to].about_me() );
                
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        //RUN
        bfs();
    }//GEN-LAST:event_jButton8ActionPerformed

    public void bfs()
    {
        Queue<Integer>Q = new LinkedList<Integer>();

        for(int i=0;i<indx;i++)
        {
            if( "SWITCH_ON".equals(objects[i].about_me()) ) 
            {
                Q.add(i);objects[i].output=1;
                System.out.println("inserting in queue "+ objects[i].output );
            }
            if( "SWITCH_OFF".equals(objects[i].about_me()) ) 
            {
                Q.add(i);objects[i].output=0;
                System.out.println("inserting in queue "+ objects[i].output );
            }
            
        }
        System.out.println("BFS Starts");
        while(!Q.isEmpty())
        {
            int source=Q.remove();
            int source_output=objects[source].output;
            System.out.println("type "+ objects[source].about_me() + " output"+ source_output);
//            objects[source].allClear();
            make_edge ob ;
            for(int p=0;p<len[source] ;p++)
            {
                ob=con[source][p];
//                int inp=0;int inp1=0;
                if(ob.incon1==1) objects[ob.to].set_value_inp1(source_output);
                if(ob.incon2==1) objects[ob.to].set_value_inp2(source_output);
//                objects[ob.to].set_value(inp, inp1);
                objects[ob.to].evaluate();
                Q.add(ob.to);
            }
        }
        draw_edges();
        for(int i=0;i<indx;i++) objects[i].allClear();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables
    

    private JDialog aboutBox;

}
