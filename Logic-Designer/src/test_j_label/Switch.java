
package test_j_label;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

public class Switch extends Gate{

    int flag=0;
    Icon off=new javax.swing.ImageIcon(getClass().getResource("/test_j_label/resources/Switch.gif"));
    Icon on=new javax.swing.ImageIcon(getClass().getResource("/test_j_label/resources/Switch_on.gif"));
        
    public Switch(JLabel jb) 
    {
        super(jb);
        flag=0;
        lb.setIcon(off);allClear();
        
        JMenuItem item;
        
        item = new JMenuItem("Output");
        item.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                
                System.out.println("Switch output");
                outCon=1;outflag=1;noDrag=1;
            }
            
        });
        popm.add(item);
        
        lb.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                    if(flag==0) {lb.setIcon(on);flag=1;output=1;
                        System.out.println("output switch" +output );
                    }
                    else {lb.setIcon(off);flag=0;output=0;}
           }
        });
        
    }
    String about_me()
    {
//        return "SWITCH";
        if(flag==1) return "SWITCH_ON";
        else return  "SWITCH_OFF";
    }
    
    void set_value_inp1(int i1)
    {
//        input1=i1;
    }
    void set_value_inp2(int i2)
    {
//        input2=i2;
    }
    
    void evaluate()
    {
//        if(input1>=1 && input2>=1) output=1;
    }
    
}
    
  
