
package test_j_label;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

abstract public class Gate {
    
    int inflag, outflag, input1, output, inpCon1, outCon, inpCon2, input2;
    int noDrag=0;
    JLabel lb;
    int dragX, dragY;
    JPopupMenu popm;
    
    public Gate(JLabel jb)
    {
        lb=jb;
        lb.setComponentPopupMenu(createpm());
        allClear();  
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
                if(noDrag==0)
                    lb.setLocation(evt.getX() - dragX + lb.getLocation().x, evt.getY() - dragY + lb.getLocation().y);
            }
        });
    }
    
    void allClear(){
        outflag=0; inflag=0; input1=0; output=0; inpCon1=0; outCon=0; input2=0; inpCon2=0;
    }
    
    public void chkpmenu(JPopupMenu popm, MouseEvent evt){
        if(evt.isPopupTrigger()){
            popm.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }
    private JPopupMenu createpm(){
        popm = new JPopupMenu();
        
        lb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt){
                chkpmenu(popm, evt);
            }
        });
        return popm;
    }
    abstract String about_me();
    abstract void set_value_inp1(int i1);
    abstract void set_value_inp2(int i2);
    abstract void evaluate();
}
