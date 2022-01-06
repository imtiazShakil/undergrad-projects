
package test_j_label;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class and_gate2 {
    
    int flag=0;
    JLabel lb;
    int dragX, dragY;
    JPopupMenu popm;
    public and_gate2(JLabel jb)
    {
        lb=jb;
        
        lb.setComponentPopupMenu(createpm());
        
        Icon ic=new javax.swing.ImageIcon(getClass().getResource("/test_j_label/resources/AND.gif"));
        lb.setIcon(ic);
        lb.setSize(50, 60);
        lb.setVisible(true);
        lb.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                dragX = evt.getX();
                dragY = evt.getY();
            }
        });
        
        lb.addMouseMotionListener(new MouseMotionAdapter() {

            public void mouseDragged(MouseEvent evt) {
                lb.setLocation(evt.getX() - dragX + lb.getLocation().x, evt.getY() - dragY + lb.getLocation().y);
            }
        });
        
        
    }
    private void chkpmenu(JPopupMenu popm, MouseEvent evt){
        if(evt.isPopupTrigger()){
            popm.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }
    private JPopupMenu createpm(){
        popm = new JPopupMenu();
        JMenuItem item;
        item = new JMenuItem("item1");
        item.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                lb.setText("aaaa");
                System.out.println("paisi");
                
            }

        });
        
        popm.add(item);
        popm.add(new JMenuItem("item2"));
        popm.add(new JMenuItem("item3"));
        lb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt){
                chkpmenu(popm, evt);
            }
        });
        return popm;
    }
}
