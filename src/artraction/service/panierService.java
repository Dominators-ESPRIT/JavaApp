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

import artraction.entity.panier;
import artraction.utils.ConnexionSingleton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;


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
        public String returncodepromo(TextField ch){
         return ch.getText();
         
     }    @Override
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

   /* @Override
    public ObservableList<panier> displayAll() {
        String req="select * from personne";
        ObservableList<panier> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                panier p=new panier();
               p.setetat(rs.getInt(1));
               // p.setNom(rs.getString("nom"));
               // p.setPrenom(rs.getString(3));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(panierService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
*/
 
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

    
    
}
   