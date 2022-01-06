/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adsmongojavaproject;

import java.awt.Frame;
import javax.swing.JOptionPane;

/**
 *
 * @author ImtiazShakil
 */
public class mainFrame2 extends javax.swing.JFrame {

    /**
     * Creates new form mainFrame2
     */
    
    public mainFrame2(String dbname) {
        databaseName =dbname;
        dbobj=null;
        initializeDB();
        
        
        initComponents();
        try {
            showCollectionsCombo.setModel(new javax.swing.DefaultComboBoxModel
                (
                    dbobj.getCollections()
                )
            );
        } catch (Exception ex) {
            
            JOptionPane.showMessageDialog(null, "Couldn't Load The Collections");
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        dbnameLabel = new javax.swing.JLabel();
        showCollectionsCombo = new javax.swing.JComboBox();
        queryButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        outputTextArea = new javax.swing.JTextArea();
        showButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        customQueryText = new javax.swing.JTextArea();
        newTableName = new javax.swing.JTextField();
        newTableButton = new javax.swing.JButton();
        insertButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        DeleteButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("MongoDB Database Manipulation");
        setName("mainFrame2"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel1.setText("Current Database Name");

        dbnameLabel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        dbnameLabel.setText( databaseName );

        showCollectionsCombo.setModel(new javax.swing.DefaultComboBoxModel
            (
                //dbobj.getCollections()
            )
        );

        queryButton.setText("Query");
        queryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                queryButtonActionPerformed(evt);
            }
        });

        outputTextArea.setEditable(false);
        outputTextArea.setColumns(20);
        outputTextArea.setRows(5);
        jScrollPane1.setViewportView(outputTextArea);

        showButton.setText("Show");
        showButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showButtonActionPerformed(evt);
            }
        });

        customQueryText.setColumns(20);
        customQueryText.setText("{dept:\"CSE\",marks:69}");
        jScrollPane2.setViewportView(customQueryText);

        newTableName.setText("New Table Name");

        newTableButton.setText("Create Table");
        newTableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newTableButtonActionPerformed(evt);
            }
        });

        insertButton.setText("Insert");
        insertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertButtonActionPerformed(evt);
            }
        });

        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        DeleteButton.setText("Delete");
        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jScrollPane2)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(queryButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(insertButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(newTableName, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newTableButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DeleteButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(showCollectionsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(jLabel1)
                .addGap(42, 42, 42)
                .addComponent(dbnameLabel)
                .addContainerGap(168, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(queryButton)
                    .addComponent(showCollectionsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showButton)
                    .addComponent(insertButton)
                    .addComponent(updateButton)
                    .addComponent(DeleteButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newTableName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newTableButton))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(dbnameLabel)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initializeDB()
    {
        if(dbobj==null) {
            try {
                dbobj= new getConnection(databaseName);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Couldn't Select The Database");
//                Logger.getLogger(mainFrame2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private void queryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_queryButtonActionPerformed
//        initializeDB();
        try {
            dbobj.doQuery( customQueryText.getText(), showCollectionsCombo.getSelectedItem().toString(), outputTextArea);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
//            Logger.getLogger(mainFrame2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_queryButtonActionPerformed

    private void showButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showButtonActionPerformed
//        initializeDB();
        try{
            dbobj.doQuery("", showCollectionsCombo.getSelectedItem().toString(), outputTextArea);
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }//GEN-LAST:event_showButtonActionPerformed

    private void newTableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newTableButtonActionPerformed
//        initializeDB();
        try{
            dbobj.addTable(newTableName.getText());
            if(newTableName.getText()!="") {
                showCollectionsCombo.setModel(new javax.swing.DefaultComboBoxModel
                    (
                       dbobj.getCollections()
                    )
                );
            }
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to Add Collection");
        }
    }//GEN-LAST:event_newTableButtonActionPerformed

    private void insertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertButtonActionPerformed
//        initializeDB();
        insertDeleteForm.main(new String[] {showCollectionsCombo.getSelectedItem().toString() ,"insert" } ,dbobj);
    }//GEN-LAST:event_insertButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
//        initializeDB();
        updateFrame.main(new String[]{showCollectionsCombo.getSelectedItem().toString()}, dbobj);
    }//GEN-LAST:event_updateButtonActionPerformed

    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonActionPerformed
//        initializeDB();
        insertDeleteForm.main(new String[] {showCollectionsCombo.getSelectedItem().toString() ,"delete" } ,dbobj);
    }//GEN-LAST:event_DeleteButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Frame[] frm = mainFrame.getFrames();//loginForm.getFrames();
        for(int i=0;i<frm.length;i++) 
        {
            System.out.println("Frame "+frm[i].getName()+ " "+frm[i].getTitle());
            if(frm[i].getName()=="mainFrame")  frm[i].setVisible(true);
            else frm[i].dispose();
        }
        this.dispose();
    }//GEN-LAST:event_formWindowClosing
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(final String args[]) {
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
            java.util.logging.Logger.getLogger(mainFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainFrame2(args[0]).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DeleteButton;
    private javax.swing.JTextArea customQueryText;
    private javax.swing.JLabel dbnameLabel;
    private javax.swing.JButton insertButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton newTableButton;
    private javax.swing.JTextField newTableName;
    private javax.swing.JTextArea outputTextArea;
    private javax.swing.JButton queryButton;
    private javax.swing.JButton showButton;
    private javax.swing.JComboBox showCollectionsCombo;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
    private String databaseName;
    private getConnection dbobj;
}