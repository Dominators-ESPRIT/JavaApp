/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.utilis;

import artraction.entity.User;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author boukhris
 */
public class UserSession {
    private static UserSession          instance = null;
    private User                        user     = null;
    private final Map<Integer, Integer> panier   = new HashMap<>();
    
    public UserSession() {}

    private UserSession(User userConnected) {
        this.user = userConnected;
    }
     public static UserSession getInstance() {
        return instance;
    }

    public final static UserSession getInstance(User userConnected) {
        if (UserSession.instance == null) {
            UserSession.instance = new UserSession(userConnected);
        }

        return UserSession.instance;
    }

    public static void setInstance(UserSession instance) {
        UserSession.instance = instance;
    }
     public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static class getInstance {
        public getInstance() {}
    }
}
