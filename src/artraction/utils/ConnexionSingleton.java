/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnexionSingleton {
    
 
    private static ConnexionSingleton instance;
    static final String jdbcDriver = "com.mysql.jdbc.Driver";
      Connection con=null;
      PreparedStatement pst;
               
    public Connection getCnx() {
        return con;
    }   
    
    
    private ConnexionSingleton() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
             this.con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/artraction", "root", "");
            System.out.println(con);
             pst=con.prepareStatement("insert into panier (etat) values('1')");
            int status=pst.executeUpdate();
            if (status==1)
                System.out.println("mala jaaaaaaaaaaaaaaaaaaaaaaaaaaaaaw");
            else System.out.println("nshalah lyouuuum");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnexionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
                   

    }
    
   public static ConnexionSingleton getInstance() throws SQLException{
       
       if(instance==null)
           instance= new ConnexionSingleton();
    
       return instance;
   }
    
    
    
    
}
