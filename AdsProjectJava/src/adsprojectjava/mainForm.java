/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adsprojectjava;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



/**
 *
 * @author ImtiazShakil
 */
public class mainForm extends javax.swing.JFrame {

    /**
     * Creates new form mainForm
     */
    public mainForm() {
        initComponents();
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
        dataTable = new javax.swing.JTable();
        queryButton = new javax.swing.JButton();
        queryText = new javax.swing.JTextField();
        insertButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        dynamicPanel = new javax.swing.JPanel();
        tableNameCombBox = new javax.swing.JComboBox();
        updateButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        viewTableButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        aboutMenu = new javax.swing.JMenu();
        exitMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Sample Database Application");
        setName("MainForm"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        dataTable.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dataTable.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        dataTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        dataTable.setDoubleBuffered(true);
        dataTable.setDragEnabled(true);
        dataTable.setGridColor(new java.awt.Color(0, 0, 0));
        dataTable.setIntercellSpacing(new java.awt.Dimension(2, 2));
        jScrollPane1.setViewportView(dataTable);

        queryButton.setText("Query");
        queryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                queryButtonActionPerformed(evt);
            }
        });

        queryText.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        queryText.setText("select * from logintable;");
        queryText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                queryTextActionPerformed(evt);
            }
        });

        insertButton.setText("Insert");
        insertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertButtonActionPerformed(evt);
            }
        });

        jScrollPane2.setAutoscrolls(true);

        dynamicPanel.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(dynamicPanel);

        tableNameCombBox.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        tableNameCombBox.setModel(
            new javax.swing.DefaultComboBoxModel(
                getResult.get_tableName()
            )
        );

        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        viewTableButton.setText("View Table");
        viewTableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewTableButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Welcome to Simple Database Application");
        jLabel1.setFocusable(false);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        fileMenu.setText("File");

        aboutMenu.setText("About");
        aboutMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aboutMenuMouseClicked(evt);
            }
        });
        fileMenu.add(aboutMenu);

        exitMenu.setText("Exit");
        exitMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMenuMouseClicked(evt);
            }
        });
        fileMenu.add(exitMenu);

        jMenuBar1.add(fileMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                    .addComponent(queryText)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tableNameCombBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(viewTableButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(insertButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(queryButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(tableNameCombBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(queryText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(insertButton)
                    .addComponent(updateButton)
                    .addComponent(deleteButton)
                    .addComponent(viewTableButton)
                    .addComponent(queryButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void queryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_queryButtonActionPerformed
        try {
                    ResultSet res=get_connection.doQuery(queryText.getText());
                    getResult.fillTable(res, dataTable);
        } catch (SQLException ex) {
            
            try {
                get_connection.doUpdate(queryText.getText());
            } catch(SQLException ex2)
            {
                JOptionPane.showMessageDialog(null, "Invalid Query");
                Logger.getLogger(mainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_queryButtonActionPerformed

    private void queryTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_queryTextActionPerformed
        
        queryButtonActionPerformed(evt);
    }//GEN-LAST:event_queryTextActionPerformed

    private void insertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertButtonActionPerformed
        try {
                    ResultSet res=get_connection.doQuery("SELECT COLUMN_NAME   FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_name = '"+tableNameCombBox.getSelectedItem().toString()+"';");
                    
                    dynamicPanel.removeAll();
                    
                    String col[]=getResult.get_special_ans(res);
                    final ArrayList<JTextField> txtList= new ArrayList<JTextField>();
                    
                    for(int i=0;i<col.length;i++)
                    {
                        JLabel jb=new javax.swing.JLabel(col[i] );
                        dynamicPanel.add(jb);
                        
                        JTextField jtf= new JTextField(col[i]);
                        jtf.setMinimumSize(new Dimension(50, 30));
                        dynamicPanel.add(jtf);
                        txtList.add(jtf);
                    }
                    JButton jb = new JButton("Confirm Insertion");
                    jb.addActionListener(new ActionListener() {

                        @Override
                            public void actionPerformed(ActionEvent e) {
                                String str="Insert into "+tableNameCombBox.getSelectedItem().toString()+" values( ";
                                for(int i=0;i<txtList.size();i++ )
                                {
                                    if(i==0) str=str+"'"+txtList.get(i).getText() + "'";
                                    else str=str+", '"+txtList.get(i).getText() + "'";
                                }
                                str=str+" );";
                                JOptionPane.showMessageDialog(null,str );
                                try {
                                    get_connection.doUpdate(str);
                                    JOptionPane.showMessageDialog(null,"Insertion was Successful" );
                                } catch (SQLException ex) {
                                    Logger.getLogger(mainForm.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                            }
                        }
                    );
                    dynamicPanel.add(jb);
                    dynamicPanel.revalidate();
                    dynamicPanel.repaint();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Invalid Insert Operation");
            Logger.getLogger(mainForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_insertButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        
        try {
                ResultSet res=get_connection.doQuery("SELECT COLUMN_NAME   FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_name = '"+tableNameCombBox.getSelectedItem().toString()+"';");
                    
                dynamicPanel.removeAll();
                final String col[]=getResult.get_special_ans(res);
                final ArrayList<JTextField> txtList= new ArrayList<JTextField>();
                    
                for(int i=0;i<col.length;i++)
                {
                    JLabel jb=new javax.swing.JLabel(col[i] );
                    dynamicPanel.add(jb);

                    JTextField jtf= new JTextField(col[i]);
                    jtf.setMinimumSize(new Dimension(50, 30));
                    dynamicPanel.add(jtf);
                    txtList.add(jtf);
                }
                JButton jb = new JButton("Confirm Update");
                jb.addActionListener(new ActionListener() {

                    @Override
                        public void actionPerformed(ActionEvent e) {
                            String str="update "+tableNameCombBox.getSelectedItem().toString()+" SET ";
                            for(int i=0;i<txtList.size();i++ )
                            {
                                if(i==0) str=str+ col[i]+"= '"+txtList.get(i).getText() + "'";
                                else str=str+","+ col[i]+"= '"+txtList.get(i).getText() + "'";
                            }
                            str=str+" where "+col[0]+"='"+txtList.get(0).getText() +"';";
                            JOptionPane.showMessageDialog(null,str );
                            try {
                                get_connection.doUpdate(str);
                                JOptionPane.showMessageDialog(null,"Update was Successful" );
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null, "Invalid Update Command");
                                Logger.getLogger(mainForm.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    }
                );
            dynamicPanel.add(jb);
            dynamicPanel.revalidate();
            dynamicPanel.repaint();
        } catch (SQLException ex) {
            
            Logger.getLogger(mainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_updateButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        try {
                ResultSet res=get_connection.doQuery("SELECT COLUMN_NAME   FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_name = '"+tableNameCombBox.getSelectedItem().toString()+"';");
                    
                dynamicPanel.removeAll();
                final String col[]=getResult.get_special_ans(res);
                final ArrayList<JTextField> txtList= new ArrayList<JTextField>();
                    
                for(int i=0;i<col.length;i++)
                {
                    JLabel jb=new javax.swing.JLabel(col[i] );
                    dynamicPanel.add(jb);

                    JTextField jtf= new JTextField(col[i]);
                    jtf.setMinimumSize(new Dimension(50, 30));
                    dynamicPanel.add(jtf);
                    txtList.add(jtf);
                    break;
                }
                JButton jb = new JButton("Confirm Delete");
                jb.addActionListener(new ActionListener() {

                    @Override
                        public void actionPerformed(ActionEvent e) {
                            String str="delete from "+tableNameCombBox.getSelectedItem().toString()+" where ";
                            for(int i=0;i<txtList.size();i++ )
                            {
                                str=str+col[i]+" = " + "'"+ txtList.get(i).getText() +"' ;" ;
                            }
                            JOptionPane.showMessageDialog(null,str );
                            try {
                                get_connection.doUpdate(str);
                                JOptionPane.showMessageDialog(null,"Delete was Successful" );
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null,"Delete was Not Successful" );
                                Logger.getLogger(mainForm.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    }
                );
            dynamicPanel.add(jb);
            dynamicPanel.revalidate();
            dynamicPanel.repaint();
        } catch (SQLException ex) {
            Logger.getLogger(mainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void viewTableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewTableButtonActionPerformed
        try {
            ResultSet res=get_connection.doQuery("select * from "+tableNameCombBox.getSelectedItem().toString()+" ;");
            getResult.fillTable(res, dataTable);
        } catch (SQLException ex) {
            Logger.getLogger(mainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_viewTableButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        Frame[] frm = loginForm.getFrames();
        for(int i=0;i<frm.length;i++) 
        {
            System.out.println("Frame "+frm[i].getName()+ " "+frm[i].getTitle());
            if(frm[i].getTitle()=="LoginForm") frm[i].setVisible(true);
            else frm[i].dispose();
        }
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void exitMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMenuMouseClicked
        formWindowClosing(null);
    }//GEN-LAST:event_exitMenuMouseClicked

    private void aboutMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutMenuMouseClicked
        AboutBox.main(null);
        
    }//GEN-LAST:event_aboutMenuMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(mainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                System.out.println("MainForm Visible");
                new mainForm().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu aboutMenu;
    private javax.swing.JTable dataTable;
    private javax.swing.JButton deleteButton;
    private javax.swing.JPanel dynamicPanel;
    private javax.swing.JMenu exitMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JButton insertButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton queryButton;
    private javax.swing.JTextField queryText;
    private javax.swing.JComboBox tableNameCombBox;
    private javax.swing.JButton updateButton;
    private javax.swing.JButton viewTableButton;
    // End of variables declaration//GEN-END:variables
}

