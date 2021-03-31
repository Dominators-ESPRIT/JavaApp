/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.service;

import artraction.entity.commande;
import artraction.utils.ConnexionSingleton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zeyne
 */
public class commandeservice implements Icommandeservice<commande> {
   private static commandeservice instance;
    private Statement st;;
    private ResultSet rs;
    Connection con=null;
    PreparedStatement ins,upd,supp,disp;
    
       public commandeservice() throws SQLException {
      
       try {
           Class jdbcclass= Class.forName("com.mysql.jdbc.Driver");
           con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/artraction", "root", "");
           ConnexionSingleton cs=ConnexionSingleton.getInstance();
           
           st=cs.getCnx().createStatement();
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(commandeservice.class.getName()).log(Level.SEVERE, null, ex);
       }
         
    }
          public static commandeservice getInstance() throws SQLException{
        if(instance==null) 
            instance=new commandeservice();
        return instance;
    }
    @Override
    public void insert(commande o) {
       try {
           ins=con.prepareStatement("insert into liste_commande (id_panier,id) values ('"+ o.getId_panier() +"' , '"+o.getId_user() +"' )");
            ins.executeUpdate();       
       } catch (SQLException ex) {
           Logger.getLogger(commandeservice.class.getName()).log(Level.SEVERE, null, ex);
       }

    }
    
   
    
    public int displayRef(){
           int x=-1;
           String req="select ref_commande from liste_commande ";
        try { 
            
                rs=st.executeQuery(req);
           while(rs.next())
           x=rs.getInt("ref_commande");
        } catch (SQLException ex) {
            Logger.getLogger(panierService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }
         public void updateadr(String ch,int id){
    try {
        upd=con.prepareStatement("update liste_commande set adresse_liv ='"+ch+"' where ref_commande="+id);
        int statusupd = upd.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
        }
    
}
