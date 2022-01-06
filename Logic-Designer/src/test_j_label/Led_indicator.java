
package test_j_label;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;


public class Led_indicator extends Gate{
    
    int flag=0;
    Icon off=new javax.swing.ImageIcon(getClass().getResource("/test_j_label/resources/LED.gif"));
    Icon on=new javax.swing.ImageIcon(getClass().getResource("/test_j_label/resources/LED_on.gif"));
        
    public Led_indicator(JLabel jb) 
    {
        super(jb);allClear();
        
        lb.setIcon(off);allClear();
        JMenuItem item,item2;
        item = new JMenuItem("Input 1");
        item.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                System.out.println("Input 1");
                inflag=1;
                inpCon1=1;
                noDrag=1;
            }
        });
        
        popm.add(item);
        
        item2 = new JMenuItem("Output");
        item2.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                System.out.println("Output");
                outflag=1;
                outCon=1;
                noDrag=1;
            }
        });
        popm.add(item2);
            
        
    }
    String about_me()
    {
        return "LED";
    }
    void set_value_inp1(int i1)
    {
        input1=i1;
    }
    void set_value_inp2(int i2)
    {
        input2=i2;
    }
    
    void evaluate()
    {
        if(input1>=1) output=1;
        else output=0;
        if(output==1)
        {
            lb.setIcon(on);
        }
        else lb.setIcon(off);
    }
    
}
