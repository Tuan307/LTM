/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.baseBean;
import Lib.StringUtility;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import orm.GroupChat;
import orm.Message;
import orm.Users;
import java.lang.String;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AdamKyle
 */
public class UsersDAO {
    public Users checkLogin(String user, String pass){
        try {
            StringBuilder query = new StringBuilder();
            query.append("select * from messenger.users ul ");
            query.append("where ul.username = '"+user+"' ");
            query.append("and ul.password = '"+pass+"' ");
            query.append("limit 1");
            PreparedStatement ps = null;
            ps = baseBean.con.prepareStatement(query.toString());
            ResultSet rs = ps.executeQuery();
            Users result = null ;
            if(rs.next()){
                result = new Users();
                result.setUserId(rs.getInt("UsersId"));
                result.setUserName(rs.getString("UserName"));
                result.setPassWord(rs.getString("PassWord"));
                result.setState(rs.getInt("State"));
                result.setFirstName(rs.getString("FirstName"));
                result.setLastName(rs.getString("LastName"));
                result.setEmail(rs.getString("Email"));
                result.setAddress(rs.getString("Address"));
            }
            ps.close();
            if(result != null){
                return result;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    public List<Users> selectFriends(int userId){
        try {
            StringBuilder query = new StringBuilder();
            query.append("select ul.* from users ul,friendList fl ");
            query.append("where (fl.userId = '"+userId+"' and ul.usersId = fl.userFriendId )");
            query.append("or (fl.userFriendId = '"+userId+"' and ul.usersId = fl.userId )");
            PreparedStatement ps = null;
            ps = baseBean.con.prepareStatement(query.toString());
            List<Users> listFriends = new ArrayList<Users>();
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Users userFriend = new Users();
                userFriend.setUserId(rs.getInt("UsersId"));
                userFriend.setUserName(rs.getString("UserName"));
                userFriend.setPassWord(rs.getString("PassWord"));
                userFriend.setState(rs.getInt("State"));
                userFriend.setFirstName(rs.getString("FirstName"));
                userFriend.setLastName(rs.getString("LastName"));
                userFriend.setEmail(rs.getString("Email"));
                userFriend.setAddress(rs.getString("Address"));
                listFriends.add(userFriend);
            }
            ps.close();
            System.out.println("userId: " + userId + ".select friends size = "+listFriends.size());
            return listFriends;
        } catch (SQLException ex) {
            
        }
        return new ArrayList<>();
    }
    public List<Message> selectMessagesFromToUserId(int fromUserId, int toUserId){
        try {
            StringBuilder query = new StringBuilder();
            query.append("select m.* from messages m ");
            query.append("where (m.fromUserId = "+fromUserId+" and m.toUserId = "+toUserId+") ");
            query.append("or (m.fromUserId = "+toUserId+" and m.toUserId = "+fromUserId+") ");
            query.append("and m.groupId = -1 ");
            query.append("order by m.createdDate DESC ");
            PreparedStatement ps = null;
            ps = baseBean.con.prepareStatement(query.toString());
            List<Message> listMessages = new ArrayList<Message>();
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Message mess = new Message();
                mess.setMessagesId(rs.getInt("messagesId"));
                mess.setFromUserId(rs.getInt("fromUserId"));
                mess.setToUserId(rs.getInt("toUserId"));
                mess.setContnet(rs.getString("content"));
                mess.setCreatedDate(rs.getDate("createdDate"));
                mess.setGroupId(rs.getInt("groupId"));
                listMessages.add(mess);
            }
            ps.close();
            System.out.println("userId: " + fromUserId + ".select mess size "+fromUserId+" to "+ toUserId+" = "+listMessages.size());
            return listMessages;
        } catch (SQLException ex) {
            
        }
        return new ArrayList<>();
    }
    public boolean updateMess(int toUser, int fromuser, String mess){
        try {
            StringBuilder query = new StringBuilder();
            //INSERT INTO `messenger`.`messages` (`fromUserId`, `toUserId`, `content`, `createdDate`, `groupId`) VALUES ('1', '4', 'asdfs', 'date', '-1');
            query.append("INSERT INTO messages ");
            query.append("(`fromUserId`, `toUserId`, `content`, `createdDate`, `groupId`)");
            query.append("VALUES ('"+toUser+"', '"+fromuser+"', '"+mess+"', '"+StringUtility.currentDateTime()+"', '-1')");
            Statement statement = baseBean.con.createStatement();
            statement.executeUpdate(query.toString());
            statement.close();
            return true;
        } catch (SQLException ex) {
            
        }
        return false;
    }
    public boolean addUser(){
        return false;
    }
    public List<GroupChat> selectGroups(int userId){
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT g.* FROM messenger.groupdetail gd,messenger.groupchat g "
                    + "WHERE g.GroupId = gd.GroupId ");
            query.append("AND ToUserId = "+userId+" ");
            PreparedStatement ps = null;
            ps = baseBean.con.prepareStatement(query.toString());
            List<GroupChat> listGroups = new ArrayList<GroupChat>();
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                GroupChat group = new GroupChat();
                group.setGroupId(rs.getInt("GroupId"));
                group.setGroupName(rs.getString("GroupName"));
                group.setUserId(rs.getInt("UserId"));// nguoi tao
                group.setCreatedDate(rs.getTimestamp("CreatedDate"));
                listGroups.add(group);
            }
            ps.close();
            System.out.println("userId: " + userId + ".select groups size = "+listGroups.size());
            return listGroups;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return new ArrayList<>();
    }
    
    public List<Message> selectMessagesFromGroupId(int groupId){
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT * FROM messenger.messages m ");
            query.append("where m.groupId = "+groupId+" ");
            query.append("order by m.createdDate DESC ");
            PreparedStatement ps = null;
            ps = baseBean.con.prepareStatement(query.toString());
            List<Message> listMessages = new ArrayList<Message>();
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Message mess = new Message();
                mess.setMessagesId(rs.getInt("messagesId"));
                mess.setFromUserId(rs.getInt("fromUserId"));
                mess.setToUserId(rs.getInt("toUserId"));
                mess.setContnet(rs.getString("content"));
                mess.setCreatedDate(rs.getDate("createdDate"));
                mess.setGroupId(rs.getInt("groupId"));
                listMessages.add(mess);
            }
            ps.close();
            System.out.println("groupId: " + groupId + ".select mess size = "+listMessages.size());
            return listMessages;
        } catch (SQLException ex) {
            
        }
        return new ArrayList<>();
    }
    public boolean updateMessGroup(int toUser, int groupId, String mess){
        try {
            StringBuilder query = new StringBuilder();
            //INSERT INTO `messenger`.`messages` (`fromUserId`, `toUserId`, `content`, `createdDate`, `groupId`) VALUES ('1', '4', 'asdfs', 'date', '-1');
            query.append("INSERT INTO messages ");
            query.append("(`fromUserId`, `toUserId`, `content`, `createdDate`, `groupId`)");
            query.append("VALUES ('"+toUser+"', '"+groupId+"', '"+mess+"', '"+StringUtility.currentDateTime()+"', '"+groupId+"')");
            Statement statement = baseBean.con.createStatement();
            statement.executeUpdate(query.toString());
            statement.close();
            return true;
        } catch (SQLException ex) {
            
        }
        return false;
    }
    
    public boolean insertGroup(int userCreate, String nameGroup, ArrayList<String> usermembers){
        try {
            if(usermembers == null || usermembers.size() <= 0){
                return false;
            }
            int idg = getMaxGroupId();
            if(idg == 0){
                idg = 1;
            }
            idg +=1;
            String query = "INSERT INTO groupchat (`GroupId`,`UserId`, `GroupName`, `CreatedDate`) "
                    + "VALUES ("+idg+","+userCreate+", '"+nameGroup+"', '"+StringUtility.currentDateTime()+"'); ";
            Statement statement = baseBean.con.createStatement();
            statement.executeUpdate(query.toString());
            query = "INSERT INTO groupdetail (`GroupId`, `ToUserId`) VALUES ("+idg+", "+userCreate+"); ";
            statement.executeUpdate(query.toString());
            for(int i=0;i<usermembers.size();i++){
                query = "INSERT INTO groupdetail (`GroupId`, `ToUserId`) VALUES ("+idg+", "+usermembers.get(i)+"); ";
                statement.executeUpdate(query.toString());
            }
            statement.close();
            //System.out.println("create group success");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public int getMaxGroupId(){
        try {
            String query = "select case when idg is null then  0 else idg end as idg " +
                "from(SELECT max(groupid) as idg FROM messenger.groupchat ) as tbl";
            PreparedStatement ps = null;
            ps = baseBean.con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int idg = rs.getInt(1);
                ps.close();
                return idg;
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public List<Users> selectLikeUser(int userId,String name){
        try {
            String query = "SELECT * FROM messenger.users " +
                "where concat(FirstName,' ',LastName,' ',FirstName) like '%"+name+"%' "
                + "and UsersId not in (select ul.UsersId from users ul,friendList fl "
                + "where (fl.userId = "+userId+" and ul.usersId = fl.userFriendId ) "
                + "or (fl.userFriendId = "+userId+" and ul.usersId = fl.userId )) "
                + "and UsersId != "+userId+"";
            PreparedStatement ps = null;
            ps = baseBean.con.prepareStatement(query.toString());
            ResultSet rs = ps.executeQuery();
            List<Users> listFriends = new ArrayList<Users>();
            while(rs.next()){
                Users userFriend = new Users();
                userFriend.setUserId(rs.getInt("UsersId"));
                //userFriend.setUserName(rs.getString("UserName"));
                //userFriend.setPassWord(rs.getString("PassWord"));
                userFriend.setState(rs.getInt("State"));
                userFriend.setFirstName(rs.getString("FirstName"));
                userFriend.setLastName(rs.getString("LastName"));
                userFriend.setEmail(rs.getString("Email"));
                userFriend.setAddress(rs.getString("Address"));
                listFriends.add(userFriend);
            }
            ps.close();
            return listFriends;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    public boolean insertFriend(int userId,int UserFrId){
         try {
            String query = "insert into friendlist (`UserId`, `UserFriendId`) values ("+userId+","+UserFrId+")";
            Statement statement = baseBean.con.createStatement();
            statement.executeUpdate(query.toString());
            statement.close();
            return true;
        } catch (SQLException ex) {
             System.out.println("them ban da ton tai");
        }
        return false;
    }
}
