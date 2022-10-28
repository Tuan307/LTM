/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orm;

import java.io.Serializable;

/**
 *
 * @author AdamKyle
 */
public class FriendList implements Serializable{
    private int userId;
    private int userFriendId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserFriendId() {
        return userFriendId;
    }

    public void setUserFriendId(int userFriendId) {
        this.userFriendId = userFriendId;
    }
}
