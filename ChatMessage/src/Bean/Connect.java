/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adam Kyle
 */
public class Connect {

    static Connection con = null;

    public static Connection ketnoi() {
        //dang ky driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        //connect
        if (con == null) {
            try {
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/messenger", "root", "123456");

            } catch (SQLException ex) {
                Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.toString());
            }
            System.out.println("connected to database");
        }
        return con;
    }
}
