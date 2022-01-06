
package test_j_label;

import java.awt.Point;
import javax.swing.JLabel;

import javax.swing.Icon;
import javax.swing.JComponent;

public class and_gate extends JComponent
{
    JLabel jLabel4;
    public and_gate()
    {
        System.out.println("hudai");
        jLabel4 = new javax.swing.JLabel();
        
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
    }
    public void correct()
    {
        jLabel4.setText("HUDAI");
        jLabel4.setVisible(true);
        
        jLabel4.setSize(new java.awt.Dimension(40, 50) ) ;
        Point pt=new Point(20, 30);
        jLabel4.setLocation(pt);
    }
    
    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {                                     
        
        jLabel4.setLocation(evt.getPoint());
        
    }
    
    JLabel get_jLabel()
    {
        jLabel4.setEnabled(true);
        jLabel4.setText("HUDAI");
        jLabel4.setVisible(true);
        
        jLabel4.setSize(new java.awt.Dimension(50, 60) ) ;
        Point pt=new Point(20, 30);
        jLabel4.setLocation(pt);
        
        
        Icon Ic=new javax.swing.ImageIcon(getClass().getResource("/test_j_label/resources/AND.gif"));
        jLabel4.setIcon(Ic);
        
        return jLabel4;
    }
    
    
    
}
