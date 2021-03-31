/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package artraction.utils;
import artraction.entity.userEntity;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wiemhjiri
 */
public class ConnexionSingleton {
    
  
    private Connection con;
    private static ConnexionSingleton instance;
    public userEntity uInfos;
    public Connection getCnx() {
        return con;
    }
    
    

    
   public static ConnexionSingleton getInstance() throws SQLException{
      
       if(instance==null)
           instance = new ConnexionSingleton();
       System.out.println(instance);
       return instance;
   }

  

    
 
    
    
    public ConnexionSingleton() throws SQLException {
      try {
            Class.forName("com.mysql.jdbc.Driver");
             this.con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/artraction", "root", "");
            System.out.println(con);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnexionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
               

    }
    

    
}
