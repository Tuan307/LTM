/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Bean.ChatInThread;
import Bean.ChatOutThread;
import Lib.StringUtility;
import java.awt.Color;
import orm.Message;
import orm.Users;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.StyleSheet;
import orm.MessageData;

/**
 *
 * @author AdamKyle
 */
public class HomeFrm extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    private Users user;
    private Users userRe;
    private List<Users> listFriends;
    private MessageData mess;
    private ObjectInputStream ois;
    List<Message> listMessages;
    private Socket socketToServer;
    ChatInThread cit ;
    ChatOutThread cot ;
    GroupCharMainFrm charMainFrm;
    AddFriendFrm addFriendFrm;
    public Users getUser() {//Lấy ra user đang đăng nhập
        return user;
    } 
    public void setUser(Users user) {//Thực hiện đặt tên user đang đăng nhập lên form hiển thị
        this.user = user;
        jLabel_userName.setText(user.getFirstName() + " " +user.getLastName()); //đặt tên cho form hiển thị
        super.setTitle("Hi "+user.getFirstName()); //Tên hiển thị ở ngoài form => thực hiện ghi đè cái homeFrm
    }

    public HomeFrm() { //Hàm tạo khung cho ứng dụng
        initComponents();
        this.setSize(800, 500);
        this.setLocation(200, 100);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setTitle("username");
        jLabel_usernameSend.setText(""); // Đặt tên người gửi là không có gì
        charMainFrm = new GroupCharMainFrm();
        addFriendFrm = new AddFriendFrm();
    }
    public void init(){// hàm khởi tạo được gọi đến trước
        try {
            updateListFriends();
            clickList();
            userRe = new Users();
            mess = new MessageData();
            cot = new ChatOutThread();
            cot.setSocket(socketToServer);
            cot.start();
            ois = new ObjectInputStream(socketToServer.getInputStream());
            // group chat
            charMainFrm.setCot(cot);
            charMainFrm.setOis(ois);
            charMainFrm.setUser(user);
            //charMainFrm.init();
            //get list friend
            getListFriend();
        } catch (IOException ex) {
            Logger.getLogger(HomeFrm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateListFriends(){
        if(listFriends == null || listFriends.size() <= 0) return;
        DefaultTableModel dtm = (DefaultTableModel) jTable_listFriends.getModel();//khởi tạo một bảng mới
        dtm.getDataVector().removeAllElements();//xóa tất cả các phần tử trước đó
        for(Users userFriend : listFriends){//hiển thị một list friend với họ và tên
            dtm.addRow(userFriend.toObjectListFriend());
        }
    }
    private void clickList(){
        jTable_listFriends.addMouseListener(new MouseAdapter() {//Xử lý sự kiện click vào list friend
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount() >=1){
                    try {
                        MessageData messListMess = new MessageData();
                        userRe = listFriends.get(jTable_listFriends.getSelectedRow());
                        jLabel_usernameSend.setText(userRe.getFirstName());
                        messListMess.setFuntion(StringUtility.FUNCTION_CLIENT_GET_MESS_BY_USERID);
                        messListMess.setUserIdReceive(userRe.getUserId());
                        cot.setMessage(messListMess);
                        cot.resume();
                        listMessages = (List<Message>) ois.readObject();
                        updateMessages(listMessages);
                        if(listMessages == null){
                            return;
                        }
                    } catch (IOException | ClassNotFoundException ex) {
                        Logger.getLogger(HomeFrm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    public Socket getSocketToServer() {
        return socketToServer;
    }

    public void setSocketToServer(Socket socketToServer) {
        this.socketToServer = socketToServer;
    }


    public ObjectInputStream getOis() {
        return ois;
    }

    public void setOis(ObjectInputStream ois) {
        this.ois = ois;
    }

    public ChatInThread getCit() {
        return cit;
    }

    public void setCit(ChatInThread cit) {
        this.cit = cit;
    }

    public ChatOutThread getCot() {
        return cot;
    }

    public void setCot(ChatOutThread cot) {
        this.cot = cot;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public void getListFriend(){
        try {
            MessageData messListFriend = new MessageData();
            messListFriend.setFuntion(StringUtility.FUNCTION_CLIENT_GET_LIST_FRIENDS);
            cot.setMessage(messListFriend);
            cot.resume();
            listFriends = (List<Users>) ois.readObject();
            updateListFriends();
        } catch (IOException ex) {
            Logger.getLogger(HomeFrm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomeFrm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel_userName = new javax.swing.JLabel();
        jLabel_usernameSend = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_listFriends = new javax.swing.JTable();
        jButton_send = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea_messageSend = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_messages = new javax.swing.JTable();
        jButton_refresh = new javax.swing.JButton();
        jButton_chat_group = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(255, 0, 0));

        jLabel_userName.setBackground(new java.awt.Color(204, 204, 204));
        jLabel_userName.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        jLabel_userName.setForeground(new java.awt.Color(51, 51, 255));
        jLabel_userName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_userName.setText("user name");

        jLabel_usernameSend.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_usernameSend.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        jLabel_usernameSend.setText("user name 2");

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

        jButton_send.setText("Send");
        jButton_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_sendActionPerformed(evt);
            }
        });

        jTextArea_messageSend.setColumns(20);
        jTextArea_messageSend.setRows(5);
        jScrollPane3.setViewportView(jTextArea_messageSend);

        jTable_messages.setForeground(new java.awt.Color(29, 27, 27));
        jTable_messages.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Messages"
            }
        ));
        jTable_messages.setToolTipText("");
        jTable_messages.setGridColor(new java.awt.Color(0, 255, 153));
        jTable_messages.setRowHeight(60);
        jTable_messages.setSelectionBackground(new java.awt.Color(187, 191, 196));
        jTable_messages.setSelectionForeground(new java.awt.Color(53, 55, 240));
        jScrollPane4.setViewportView(jTable_messages);

        jButton_refresh.setText("Reresh");
        jButton_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_refreshActionPerformed(evt);
            }
        });

        jButton_chat_group.setText(" Group Chat");
        jButton_chat_group.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_chat_groupActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 0, 0));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("Exit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Add Friend");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_userName, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton_refresh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_chat_group)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel_usernameSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_send, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_usernameSend, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel_userName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_send, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_chat_group, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButton_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_refreshActionPerformed
        getListFriend();
    }//GEN-LAST:event_jButton_refreshActionPerformed

    private void jButton_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_sendActionPerformed
        if(jLabel_usernameSend.getText() == null || jLabel_usernameSend.getText().compareToIgnoreCase("") == 0){
            return;
        }
        mess = new MessageData();
        mess.setContent(jTextArea_messageSend.getText());
        System.out.println(mess.getContent());
        mess.setFuntion(StringUtility.FUNCTION_CLIENT_SENT_MESS);
        mess.setUserIdReceive(userRe.getUserId());
        cot.setMessage(mess);
        cot.resume();
        jTextArea_messageSend.setText("");
    }//GEN-LAST:event_jButton_sendActionPerformed

    private void jButton_chat_groupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_chat_groupActionPerformed
        // TODO add your handling code here:
        
        //this.setVisible(false);
        if(listFriends == null || listFriends.size() <0){
            getListFriend();
        }
        charMainFrm.addGroupFrm.setListFriends(listFriends);
        charMainFrm.init();
        charMainFrm.setVisible(true);
        
    }//GEN-LAST:event_jButton_chat_groupActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // TODO add your handling code here:
            //socketToServer.close();
            this.setLayout(null);
            this.setVisible(false);
            System.exit(WIDTH);
        } catch (Exception ex) {
            Logger.getLogger(HomeFrm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        addFriendFrm.setCit(cit);
        addFriendFrm.setCot(cot);
        addFriendFrm.setUser(user);
        addFriendFrm.setOis(ois);
        addFriendFrm.init();
        addFriendFrm.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed
    private void updateMessages(List<Message> listMessages){
        DefaultTableModel dtm = (DefaultTableModel) jTable_messages.getModel();
        dtm.getDataVector().removeAllElements();
        if(listMessages == null || listMessages.size() <= 0 || listMessages.get(0) == null){
            dtm.addRow(new Object[]{""});
            return ;
        }
        for(Message mess : listMessages){
            if(mess.getToUserId() == user.getUserId()){
                //nguio nhan = user
                dtm.addRow(new Object[]{StringUtility.RECEIVE+"("+mess.getCreatedDate()+"): "+mess.getContnet()});
            } else{
                dtm.addRow(new Object[]{StringUtility.SEND+"("+mess.getCreatedDate()+"): "+mess.getContnet()});
            }
            
        }
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
            java.util.logging.Logger.getLogger(HomeFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeFrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton_chat_group;
    private javax.swing.JButton jButton_refresh;
    private javax.swing.JButton jButton_send;
    private javax.swing.JLabel jLabel_userName;
    private javax.swing.JLabel jLabel_usernameSend;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable_listFriends;
    private javax.swing.JTable jTable_messages;
    private javax.swing.JTextArea jTextArea_messageSend;
    // End of variables declaration//GEN-END:variables
}
