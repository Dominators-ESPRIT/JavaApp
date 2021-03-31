/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.utilis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author boukhris
 */
public class ConnexionSingleton {
     private static ConnexionSingleton data ;
    private Connection cnx ;
    public String user= "root" ;
    public String password = "";
    public String url ="jdbc:mysql://localhost:3306/artraction4";
    
    private ConnexionSingleton()
    {
        try 
        {
            cnx = DriverManager.getConnection(url, user, password);
            System.out.println("connexion etablie ");
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ConnexionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ConnexionSingleton getInstance ()
    {
        if (data==null)
           data = new ConnexionSingleton();
        return data ;
    }

    public static ConnexionSingleton getData() {
        return data;
    }

    public static void setData(ConnexionSingleton data) {
        ConnexionSingleton.data = data;
    }

    public Connection getCnx() {
        return cnx;
    }

    public void setCnx(Connection cnx) {
        this.cnx = cnx;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
