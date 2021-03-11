/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.service;

import artraction.entity.oeuvre;
import artraction.utils.ConnexionSingleton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author zeyne
 */
public class ajoutoeuvservice implements Iajoutoeuvservice<oeuvre>{
      private static ajoutoeuvservice instance;
    private Statement st;;
    private ResultSet rs;

        Connection con=null;
        PreparedStatement ins,upd,supp,disp;
  
        private Random rand = new Random();
        int randref = rand.nextInt();
    public ajoutoeuvservice() throws SQLException {
        try {
            Class jdbcclass= Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/artraction", "root", "");
            ConnexionSingleton cs=ConnexionSingleton.getInstance();
            try {
                st=cs.getCnx().createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(codepromoservice.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(codepromoservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void insert(oeuvre o) {
          try {
              ins=con.prepareStatement("insert into oeuvre (ref,label,prix) values ( '"+o.getRef()+"','prd1', 500)");
              int statusins=ins.executeUpdate();
              if (statusins==1)
                  System.out.println("insert temshy");
              else System.out.println("insert matemshysh");
          } catch (SQLException ex) {
              Logger.getLogger(ajoutoeuvservice.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public oeuvre displaypanier(int id) {
         String req="select * from oeuvre where id_panier ="+id;
           oeuvre p=new oeuvre();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                    p.setRef(rs.getString(1));
               p.setLabel(rs.getString("label"));
               p.setPrix(rs.getDouble(7));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(panierService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }
    public int updateidpanier(oeuvre o,int x) {
        int statusupd = 0;
        
        try {
          
            upd=con.prepareStatement("update oeuvre set id_panier= '"+x+"' where ref = '"+o.getRef()+"'");
             statusupd=upd.executeUpdate();
     

        } catch (SQLException ex) {
            Logger.getLogger(codepromoservice.class.getName()).log(Level.SEVERE, null, ex);
        }

     return statusupd;
    }

   
    
    
}
