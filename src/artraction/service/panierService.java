/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.service;

/**
 *
 * @author zeyne
 */

import artraction.entity.oeuvre;
import artraction.entity.panier;
import artraction.utils.ConnexionSingleton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class panierService implements Ipanierservice<panier>{
    
    private static panierService instance;
    private Statement st;
    private ResultSet rs;
    
    
    
        Connection con=null;
        PreparedStatement ins,upd,supp;
        
     
        
    public panierService() throws SQLException {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(panierService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static panierService getInstance() throws SQLException{
        if(instance==null) 
            instance=new panierService();
        return instance;
    }
     @Override
    public void insert(panier o) {
        try {
             Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/artraction", "root", "");
            System.out.println(con);
            ins=con.prepareStatement("insert into panier (etat) values('"+o.getetat()+"')");
            int statusins=ins.executeUpdate();
            if (statusins==1)
                System.out.println("insert panier temshy");
            else System.out.println("insert panier matemshysh");
        } catch (SQLException ex) {
            Logger.getLogger(panierService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(panierService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
//____________________________________________________________________________________________________________________

    @Override
    public void delete(panier o) {
        try {
              Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/artraction", "root", "");
            System.out.println(con);
            supp=con.prepareStatement("delete from panier where id_panier='"+o.getid_panier()+"'");
            int statussupp=supp.executeUpdate();
            if (statussupp==1)
                System.out.println("delete temshy");
            else System.out.println("delete matemshysh");
        } catch (SQLException ex) {
            Logger.getLogger(panierService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(panierService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
//____________________________________________________________________________________________________________________

   

 
    @Override
    public int displayId() {
      
        int x=-1;
           String req="select id_panier from panier";
           panier p=new panier();
        try {
            rs=st.executeQuery(req);
           while( rs.next())
           x=rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(panierService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }
    @Override
         public ArrayList<String> displaycodepromo(){
      
           String req="select label from codepromo";
           ArrayList<String> codes = new ArrayList<String>();
        try {
            rs=st.executeQuery(req);
           while( rs.next())
           codes.add(rs.getString(1));
        } catch (SQLException ex) {
            Logger.getLogger(panierService.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    return codes;
         }
    @Override
    public int displaycode(String ch){
        int x = 0;
        try {
            String req="select * from codepromo where label='"+ch+"'";
            rs=st.executeQuery(req);
            rs.next();
            x=rs.getInt("valeur");
            
        } catch (SQLException ex) {
            Logger.getLogger(panierService.class.getName()).log(Level.SEVERE, null, ex);
        }return x;
    }
    
    @Override
    public ObservableList<oeuvre> displayoeuv(int id){
          ObservableList<oeuvre> list=FXCollections.observableArrayList(); 
         String req="select * from oeuvre, panier where panier.id_panier=oeuvre.id_panier and oeuvre.id_panier ="+id;
          
        try {
            rs=st.executeQuery(req);
           while(rs.next()){
         oeuvre p=new oeuvre();
              p.setRef(rs.getString(1));
               p.setLabel(rs.getString("label"));
               p.setPrix(rs.getDouble(7));
               list.add(p);
            }  
        } catch (SQLException ex) {
            Logger.getLogger(panierService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return list;
    }
//____________________________________________________________________________________________________________________
    @Override
    public int update(int x) {
        int statusupd = 0;
        
        try {
             Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/artraction", "root", "");
            System.out.println(con);
            upd=con.prepareStatement("update panier set etat = 0 where id_panier ="+x);
             statusupd=upd.executeUpdate();
     
        } catch (SQLException ex) {
            Logger.getLogger(panierService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(panierService.class.getName()).log(Level.SEVERE, null, ex);
        }
             return statusupd;
    }
 public void updatecode (int id,int idp){
       try {
           upd=con.prepareStatement("update panier set id_codepromo= '"+id+"' where id_panier= '"+idp+"' ");
          int x= upd.executeUpdate();
          if (x>0) System.out.println("update commande jawha behy");
          else System.out.println("update commande ma temshyyyyyyyyys");
       } catch (SQLException ex) {
           Logger.getLogger(commandeservice.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

 
    @Override
     public int displayvaleur(int id) {
      int x=0;
        try {
            String req="select valeur from codepromo , panier where panier.id_codepromo=codepromo.id and id_panier='"+id+"' and valeur IS NOT NULL";
            rs=st.executeQuery(req);
            
            while(rs.next()){
           
            x=rs.getInt("valeur");
            }
        } catch (SQLException ex) {
            Logger.getLogger(panierService.class.getName()).log(Level.SEVERE, null, ex);
        }            return x;

 }
    @Override
    public void deleteoeuv(String ref){
        try {
            upd=con.prepareStatement("update oeuvre set id_panier=NULL where ref='"+ref+"'");
            int x=upd.executeUpdate();
            if (x>0) System.out.println("delete done");
          else System.out.println("delete not done");
        } catch (SQLException ex) {
            Logger.getLogger(panierService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
   