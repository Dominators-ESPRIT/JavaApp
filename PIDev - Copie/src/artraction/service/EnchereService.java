/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.service;


import artraction.entity.Enchere;
import artraction.utils.ConnectionClass;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author wiemhjiri
 */
public class EnchereService implements IService<Enchere>{
    
    private static EnchereService instance;
    private Statement st;
    private ResultSet rs;
    
    private EnchereService() {
        ConnectionClass cs=ConnectionClass.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(EnchereService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static EnchereService getInstance(){
        if(instance==null) 
            instance=new EnchereService();
        return instance;
    }

    @Override
    public void insert(Enchere o) {
        String req="insert into enchere (prixinit_enchere,duree_enchere,coupant) values ('"+o.getPrixinit_enchere()+"','"+o.getDuree_enchere()+"','"+o.getCoupant()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EnchereService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    @Override
    public void delete(Enchere o) {
        String req="delete from enchere where id="+o.getId_enchere();
        Enchere e=displayById(o.getId_enchere());
        
          if(e!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(EnchereService.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    @Override
    public ObservableList<Enchere> displayAll() {
        String req="select * from enchere";
        ObservableList<Enchere> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Enchere e =new Enchere();
              
                e.setPrixinit_enchere(rs.getString ("Prix"));
                e.setDate_enchere(rs.getDate(3));
                list.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EnchereService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Enchere> displayAllList() {
        String req="select * from enchere";
        List<Enchere> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Enchere e =new Enchere();
               
                e.setPrix_enchere(rs.getString("Prix"));
                e.setDate_enchere(rs.getDate(3));
                 e.setDuree_enchere(rs.getString(3));
                
                list.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EnchereService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public Enchere displayById(int id) {
           String req="select * from enchere where id_enchere ="+id;
           Enchere e=new Enchere();
           
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
          
                e.setPrix_enchere(rs.getString("Prix"));
                e.setDate_enchere(rs.getDate("Date"));
                e.setDuree_enchere(rs.getString(3));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(EnchereService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return e;
    }

    @Override
    public boolean update(Enchere e) {
        String qry = "UPDATE personne SET  prix enchere = '"+e.getPrixinit_enchere()+"Date enchere = '"+e.getDate_enchere()+"Duree enchere = '"+e.getDuree_enchere()+ "coupant= '"+e.getCoupant()+ "' WHERE id = "+e.getId_enchere();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EnchereService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
    
}
