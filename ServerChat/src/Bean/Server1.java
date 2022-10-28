/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DTO.usersDTO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import orm.Users;
import DAO.UsersDAO;
import View.ServerFrm;
import java.io.ObjectOutputStream;
import java.net.SocketException;

/**
 *
 * @author AdamKyle
 */
public class Server1 {
    public static List<usersDTO> listUsersLogin;
    public static void main(String[] args) throws ClassNotFoundException {
        listUsersLogin= new ArrayList<usersDTO>();
        UsersDAO usersDAO = new UsersDAO();
        ServerFrm serverFrm = new ServerFrm();
        serverFrm.setVisible(true);
        try {
            int port = 10000;
            ServerSocket serverSocket = new ServerSocket(port);
            ObjectInputStream ois;
            ObjectOutputStream oos;
            while(true){
                Socket clientSocket = serverSocket.accept();
                ois = new ObjectInputStream(clientSocket.getInputStream());
                oos = new ObjectOutputStream(clientSocket.getOutputStream());
                usersDTO userLogin =(usersDTO) ois.readObject();
                Users user = usersDAO.checkLogin(userLogin.getUserName(), userLogin.getPassWord());
                userLogin.setClientSocket(clientSocket);
                userLogin.setFirstName(user.getFirstName());
                userLogin.setLastName(user.getLastName());
                listUsersLogin.add(userLogin);
                System.out.println(userLogin.getClientSocket().getInetAddress()+":"+userLogin.getClientSocket().getPort()+" "+userLogin.getUserName() + " connected");
                oos.writeObject(user);
                ThreadTest a = new ThreadTest();
                a.setUser(user);
                a.setClientSocket(clientSocket);
                a.run();
                //update vao list
                serverFrm.setListUsersLogin(listUsersLogin);
                serverFrm.updateListFriends();
            }
        } catch (SocketException ex){
            Logger.getLogger(Server1.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
            Logger.getLogger(Server1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
            System.out.println("avc");
        }
    }
    private static void white() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
