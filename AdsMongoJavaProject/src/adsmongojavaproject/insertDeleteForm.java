/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adsmongojavaproject;

import com.mongodb.DBObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ImtiazShakil
 */
public class insertDeleteForm extends javax.swing.JFrame {

    /**
     * Creates new form insertDeleteForm
     */
    public insertDeleteForm(String tbname,String typ,getConnection dbObj) {
        tableName=tbname;
        this.dbObj=dbObj;
        this.typ=typ;
        initComponents();
        tableNameLabel.setText("Collection :"+tbname);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        insertTable = new javax.swing.JTable();
        insertRowButton = new javax.swing.JButton();
        confirmOperationButton = new javax.swing.JButton();
        tableNameLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add_Remove_Form");
        setAlwaysOnTop(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        insertTable.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        insertTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Key", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        insertTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(insertTable);

        insertRowButton.setText("Insert Row");
        insertRowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertRowButtonActionPerformed(evt);
            }
        });

        confirmOperationButton.setText("Confirm Operation");
        confirmOperationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmOperationButtonActionPerformed(evt);
            }
        });

        tableNameLabel.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(insertRowButton)
                .addGap(61, 61, 61)
                .addComponent(tableNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addComponent(confirmOperationButton))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(insertRowButton)
                    .addComponent(confirmOperationButton)
                    .addComponent(tableNameLabel)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void insertRowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertRowButtonActionPerformed
        
        DefaultTableModel dtm= (DefaultTableModel) insertTable.getModel();
        dtm.addRow(new Object[] {"",""});
      
    }//GEN-LAST:event_insertRowButtonActionPerformed

    private void confirmOperationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmOperationButtonActionPerformed
        DefaultTableModel dtm= (DefaultTableModel) insertTable.getModel();
        int rowCount=dtm.getRowCount();
        int colCount=dtm.getColumnCount();
        
        str="{";
        for(int i=0;i<rowCount;i++)
        {
            if(i==0) str=str+"'"+dtm.getValueAt(i, 0).toString()+"' : '"+dtm.getValueAt(i, 1).toString()+"'";
            else str=str+",'"+dtm.getValueAt(i, 0).toString()+"' : '"+dtm.getValueAt(i, 1).toString()+"'";

        }  
        str=str+"}";
        
        if(typ=="insert")
        {    
            System.out.println("Insert String :\n"+str);
            try {
                dbObj.doInsert(str, tableName);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
//                Logger.getLogger(insertDeleteForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(typ=="delete")
        {    
            System.out.println("Delete String :\n"+str);
            try {
                dbObj.doDelete(str, tableName);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
//                Logger.getLogger(insertDeleteForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        this.dispose();
    }//GEN-LAST:event_confirmOperationButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(final String args[] , final getConnection dbObj) {
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
            java.util.logging.Logger.getLogger(insertDeleteForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(insertDeleteForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(insertDeleteForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(insertDeleteForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new insertDeleteForm(args[0],args[1],  dbObj).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton confirmOperationButton;
    private javax.swing.JButton insertRowButton;
    private javax.swing.JTable insertTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel tableNameLabel;
    // End of variables declaration//GEN-END:variables
    private String str;
    private getConnection dbObj;
    private String tableName;
    private String typ;
}