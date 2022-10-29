/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.net.Socket;

/**
 *
 * @author AdamKyle
 */
public class UserBean {

    public Socket getSocketToServer(String localhost, int port){
        try {
            Socket clientSocket = new Socket(localhost, port);
            System.out.println("get socket success: "+localhost+":"+port);
            return clientSocket;
        } catch (Exception e) {
        }
        return null;
    }

}
