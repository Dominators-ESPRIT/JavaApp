/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wiemhjiri
 */
public class ConnexionSingleton {
    
    private String url="jdbc:mysql://127.0.0.1:3306/art";
    private String login="root";
    private String pwd="";
    private Connection cnx;
    private static ConnexionSingleton instance;
    public userEntity uInfos;
    public Connection getCnx() {
        return cnx;
    }
    
    
    private ConnexionSingleton() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");  
            cnx=DriverManager.getConnection(url, login, pwd);
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public static ConnexionSingleton getInstance() throws Exception{
      
       if(instance==null)
           instance = new ConnexionSingleton();
       System.out.println(instance);
       return instance;
   }

    public Connection ConnectDb() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  
=======
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ConnexionSingleton {
    
  public userEntity uInfos;

 
    private static ConnexionSingleton instance;
   // static final String jdbcDriver = "com.mysql.jdbc.Driver";
      Connection con=null;
      PreparedStatement pst;
               
    public Connection getCnx() {
        return con;
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
    
   public static ConnexionSingleton getInstance() throws SQLException{
       
       if(instance==null)
           instance= new ConnexionSingleton();
    
       return instance;
   }
    
    

    
    
}
