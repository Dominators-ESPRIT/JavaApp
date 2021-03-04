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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class panierService implements Ipanierservice<panier>{
    
    private static panierService instance;
    private Statement st;
    private ResultSet rs;
    
    public panierService() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(panierService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static panierService getInstance(){
        if(instance==null) 
            instance=new panierService();
        return instance;
    }

    @Override
    public void insert(panier o) {
        String req="insert into panier (etat) values ('0')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(panierService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    @Override
    public void delete(panier o) {
        String req="delete from personne where id="+o.getid_panier();
        panier p=displayById(o.getid_panier());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(panierService.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    @Override
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

    public List<panier> displayAllList() {
        String req="select * from personne";
        List<panier> list=new ArrayList<>();
        
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
    @Override
    public panier displayById(int id) {
           String req="select * from personne where id ="+id;
           panier p=new panier();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                    p.setetat(rs.getInt(1));
               // p.setNom(rs.getString("nom"));
               // p.setPrenom(rs.getString(3));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(panierService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }

    @Override
    public boolean update(panier p) {
        String qry = "UPDATE personne SET etat = '"+p.getetat()+"' WHERE id = "+p.getid_panier();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(panierService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
    
}
   