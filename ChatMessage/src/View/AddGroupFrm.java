/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Bean.ChatOutThread;
import Lib.StringUtility;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import orm.MessageData;
import orm.Users;

/**
 *
 * @author AdamKyle
 */
public class AddGroupFrm extends javax.swing.JFrame {

    /**
     * Creates new form AddGroupFrm
     */
    private List<Users> listFriends;
    private List<Users> listMembers;
    private Users user;
    private ChatOutThread cot ;
    private MessageData mess;

    public ChatOutThread getCot() {
        return cot;
    }

    public void setCot(ChatOutThread cot) {
        this.cot = cot;
    }
    public AddGroupFrm() {
        initComponents();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setTitle("Add Group");
    }
    void init(){
        updateListFriends();
        mess = new MessageData();
        listMembers = new ArrayList<>();
        clickList();
        //userMember = new Users();
    }

    public JTable getjTable_listFriends() {
        return jTable_listFriends;
    }

    public void setjTable_listFriends(JTable jTable_listFriends) {
        this.jTable_listFriends = jTable_listFriends;
    }
    private void updateListFriends(){
        if(listFriends == null || listFriends.size() <= 0) return;
        DefaultTableModel dtm = (DefaultTableModel) jTable_listFriends.getModel();
        dtm.getDataVector().removeAllElements();
        for(Users userFriend : listFriends){
            dtm.addRow(userFriend.toObjectListFriend());
        }
    }
    private void clickList(){
        jTable_listFriends.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                
                if(e.getClickCount() >=2){
                    int k=jTable_listFriends.getSelectedRow();
                    Users userMembe = listFriends.get(k);
                    listMembers.add(userMembe);
                    //updateListMembers();
                    
                    DefaultTableModel dtm1 = (DefaultTableModel) jTable_listMembers.getModel();
                    
                    dtm1.addRow(userMembe.toObjectListFriend());
                    //xoa row trong listfriends
                    DefaultTableModel dtm = (DefaultTableModel) jTable_listFriends.getModel();
                    
                    dtm.removeRow(k);
                    listFriends.remove(userMembe);
                    //jTable_listFriends.hide();
                }
            }
            
        });
        jTable_listMembers.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                
                if(e.getClickCount() >=2){
                    int k=jTable_listMembers.getSelectedRow();
                    user = listMembers.get(k);
                    listFriends.add(user);
                    //updateListMembers();
                    
                    DefaultTableModel dtm1 = (DefaultTableModel) jTable_listFriends.getModel();
                    
                    dtm1.addRow(user.toObjectListFriend());
                    //xoa row trong listfriends
                    DefaultTableModel dtm = (DefaultTableModel) jTable_listMembers.getModel();
                    
                    dtm.removeRow(k);
                    listMembers.remove(user);
                    //jTable_listFriends.hide();
                }
            }
            
        });
        
    }
    public List<Users> getListFriends() {
        return listFriends;
    }

    public List<Users> getListMembers() {
        return listMembers;
    }

    public void setListMembers(List<Users> listMembers) {
        this.listMembers = listMembers;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setListFriends(List<Users> listFriends) {
        this.listFriends = listFriends;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_listFriends = new javax.swing.JTable();
        jLabel_userName = new javax.swing.JLabel();
        jLabel_nameGroup = new javax.swing.JLabel();
        jTextField_name_group = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_listMembers = new javax.swing.JTable();
        jButton_creat = new javax.swing.JButton();
        jButton_cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable_listFriends.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "List Friends"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_listFriends.setRowHeight(60);
        jScrollPane1.setViewportView(jTable_listFriends);

        jLabel_userName.setBackground(new java.awt.Color(204, 204, 204));
        jLabel_userName.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        jLabel_userName.setForeground(new java.awt.Color(51, 51, 255));
        jLabel_userName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_userName.setText("Suggested");

        jLabel_nameGroup.setBackground(new java.awt.Color(204, 204, 204));
        jLabel_nameGroup.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        jLabel_nameGroup.setForeground(new java.awt.Color(51, 51, 255));
        jLabel_nameGroup.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_nameGroup.setText("Name group");

        jTable_listMembers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Members"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_listMembers.setRowHeight(60);
        jScrollPane2.setViewportView(jTable_listMembers);

        jButton_creat.setText("Creat");
        jButton_creat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_creatActionPerformed(evt);
            }
        });

        jButton_cancel.setText("Cancel");
        jButton_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel_userName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_nameGroup, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                            .addComponent(jTextField_name_group)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_cancel)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_creat)
                        .addGap(34, 34, 34))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_userName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_nameGroup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField_name_group, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_creat)
                            .addComponent(jButton_cancel))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButton_cancelActionPerformed

    private void jButton_creatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_creatActionPerformed
        // TODO add your handling code here:
        String name = jTextField_name_group.getText();
        if(isNameGroup(name)){
            jLabel_nameGroup.setText("Name Group");
            jLabel_nameGroup.setForeground(Color.BLUE);
            
        } else{
            jLabel_nameGroup.setText("Name is not null");
            jLabel_nameGroup.setForeground(Color.red);
        }
        mess.setFuntion(StringUtility.FUNCTION_CLIENT_CREATE_GROUP);
        StringBuilder content = new StringBuilder();
        content.append(jTextField_name_group.getText());
        if(listMembers == null || listMembers.size() <= 0){
            jLabel_nameGroup.setForeground(Color.red);
            jLabel_nameGroup.setText(StringUtility.ERROR_NULL_MEMBER);
            return;
        }
        for(int i=0 ;i<listMembers.size() ; i++){
            content.append("#"+listMembers.get(i).getUserId());
        }
        mess.setContent(content.toString());
        cot.setMessage(mess);
        cot.resume();
        JOptionPane.showMessageDialog(null, StringUtility.NOTE_CREATE_GROUP_SUCCESS+ jTextField_name_group.getText(), "Create Group ", HEIGHT);
        this.setVisible(false);
        jTextField_name_group.setText("");
    }//GEN-LAST:event_jButton_creatActionPerformed
    boolean isNameGroup(String name){
        if(name == null || name.length() <= 0){
            return false;
        }
        for(int i=0;i<name.length();i++){
            if(name.charAt(i) != ' ') {
                return true;
            }
        }
        return false;
    }
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
            java.util.logging.Logger.getLogger(AddGroupFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddGroupFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddGroupFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddGroupFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddGroupFrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_cancel;
    private javax.swing.JButton jButton_creat;
    private javax.swing.JLabel jLabel_nameGroup;
    private javax.swing.JLabel jLabel_userName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_listFriends;
    private javax.swing.JTable jTable_listMembers;
    private javax.swing.JTextField jTextField_name_group;
    // End of variables declaration//GEN-END:variables
}
