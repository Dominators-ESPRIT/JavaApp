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

import artraction.entity.codepromo;
import artraction.utils.ConnexionSingleton;
import static com.mysql.cj.Messages.getString;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class codepromoservice implements Icodepromoservice<codepromo>{
    
    private static codepromoservice instance;
    private Statement st;;
    private ResultSet rs;
    
    
    
        Connection con=null;
        PreparedStatement ins,upd,supp,disp;
        
     
        
    public codepromoservice() throws SQLException {
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
    
    public static codepromoservice getInstance() throws SQLException{
        if(instance==null) 
            instance=new codepromoservice();
        return instance;
    }

    @Override
    public void insert(codepromo o) {
        try {
           
            ins=con.prepareStatement("insert into codepromo (label,valeur) values ('"+ o.getLabel() +"' , '"+o.getValeur() +"' )");
            int statusins=ins.executeUpdate();
            if (statusins==1)
                System.out.println("insert temshy");
            else System.out.println("insert matemshysh");
       
        } catch (SQLException ex) {
            Logger.getLogger(codepromoservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
//____________________________________________________________________________________________________________________

    @Override
    public void delete(codepromo o) {
        try {
       
            supp=con.prepareStatement("delete from codepromo where id ='"+o.getId()+"'");
            int statussupp=supp.executeUpdate();
            if (statussupp==1)
                System.out.println("delete temshy");
            else System.out.println("delete matemshysh");

        } catch (SQLException ex) {
            Logger.getLogger(codepromoservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//____________________________________________________________________________________________________________________

    @Override
    public ObservableList<codepromo> displayAll() {
                 ObservableList<codepromo> list=FXCollections.observableArrayList(); 

        try {
          
            disp=con.prepareStatement("select * from codepromo");
            rs=disp.executeQuery();
            while(rs.next()){
                codepromo p=new codepromo();
                 p.setId(rs.getInt("id"));
                p.setLabel(rs.getString(2));
                p.setValeur(rs.getInt("valeur"));
                list.add(p);
            }
            
      
        } catch (SQLException ex) {
            Logger.getLogger(codepromoservice.class.getName()).log(Level.SEVERE, null, ex);
        }
           return list;

    }

    public List<codepromo> displayAllList() {
        String req="select * from codepromo";
        List<codepromo> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                codepromo p=new codepromo();
                p.setLabel(rs.getString(2));
               p.setValeur(rs.getInt("valeur"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(codepromoservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public codepromo displayById(int id) {
           String req="select * from personne where id ="+id;
           codepromo p=new codepromo();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                   // p.setetat(rs.getInt(1));
               // p.setNom(rs.getString("nom"));
               // p.setPrenom(rs.getString(3));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(codepromoservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }
//____________________________________________________________________________________________________________________
    @Override
    public int updatevaleur(codepromo p,String ch) {
        int statusupd = 0;
        
        try {
          
            upd=con.prepareStatement("update codepromo set valeur= '"+ch+"' where id = '"+p.getId()+"'");
             statusupd=upd.executeUpdate();
     

        } catch (SQLException ex) {
            Logger.getLogger(codepromoservice.class.getName()).log(Level.SEVERE, null, ex);
        }

     return statusupd;
    }
    public int updatelabel(codepromo p, String ch) {
        int statusupd = 0;
        
        try {
          
            upd=con.prepareStatement("update codepromo set label= '"+ch+"' where id = '"+p.getId()+"'");
             statusupd=upd.executeUpdate();
     

        } catch (SQLException ex) {
            Logger.getLogger(codepromoservice.class.getName()).log(Level.SEVERE, null, ex);
        }

     return statusupd;
    }
}
   