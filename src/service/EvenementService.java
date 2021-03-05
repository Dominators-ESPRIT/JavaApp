
package service;

import entities.Evenement;
import entities.Participer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author hazar
 */
public class EvenementService implements EvenementServiceInterface{
    
    Connection cn=DataSource.getInstance().getCnx();
    
    @Override
    public void addEvenement(Evenement q)throws SQLDataException{
        
         
 String query ="INSERT INTO `evenements`( `lieu`, `titre`, `nbreplaces`,`DateDebut`,`DateFin`,`image`) VALUES (?,?,?,?,?,?)";
 
         PreparedStatement st;
        
        try {
            st = cn.prepareStatement(query);
                st.setString(1,q.getLieu());
                st.setString(2,q.getTitre());
                st.setInt(3,q.getNbreplaces());
                st.setDate(4,q.getDateDebut());
                st.setDate(5,q.getDateFin());
                st.setString(6,q.getImage());
                st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                

    }

    @Override
    public boolean ModifierEvenenment(Evenement e) throws SQLDataException {

               
                String query = "UPDATE `evenements` SET `lieu`=?,`nbreplaces`=?,`DateDebut`=?,`DateFin`=?,`titre`=? WHERE `id` = ?";
		PreparedStatement st;
        try {
                st = cn.prepareStatement(query);
                st.setString(1,e.getLieu());
                st.setInt(2,e.getNbreplaces());
                st.setDate(3,e.getDateDebut());
                st.setDate(4,e.getDateFin());
                st.setString(5,e.getTitre());
                st.setInt(6,e.getId());
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                
               
    }

    @Override
    public ObservableList<Evenement> getAllEvenement() throws SQLDataException {

        
        List<Evenement> list =new ArrayList<Evenement>();
        int count =0;
        
        String requete="select * from evenements";
         try{
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Evenement e = new Evenement();
                e.setId(rs.getInt(1));
                e.setLieu(rs.getString(5));
                e.setTitre(rs.getString(2));
                e.setNbreplaces(rs.getInt(6));
                e.setDateDebut(rs.getDate(3));
                e.setDateFin(rs.getDate(4));
                e.setImage(rs.getString(7));
                  
                
                list.add(e);
                
                count++;
            }
            if(count == 0){
                return null;
           }else{
             ObservableList lc_final = FXCollections.observableArrayList(list);

               return lc_final;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}

    @Override
    public boolean deleteEvenement(int idEvenement) throws SQLDataException {

        
        
        try {
            
            Statement st=cn.createStatement();
            String req= "DELETE FROM `evenements` WHERE `id` ="+idEvenement;
            st.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    @Override
    public List<Evenement> afficheEvenement(String titre) throws SQLDataException
    {

       List<Evenement> list=new ArrayList<Evenement>();
           try {
               String req="SELECT * FROM `evenements` where `titre`='"+titre+"'";
               Statement st;
                 st = cn.createStatement();
                 ResultSet rs=st.executeQuery(req);
               
                while(rs.next())
                       {
                           Evenement e= new Evenement();
                           e.setId(rs.getInt(1));
                           e.setLieu(rs.getString(2));
                           e.setTitre(rs.getString(3));
                           e.setNbreplaces(rs.getInt(4));
                           e.setNbreparticipants(rs.getInt(5));
                           e.setDateDebut(rs.getDate(6));
                           e.setDateFin(rs.getDate(7));
               
                           list.add(e);
            
                       }    
           } catch (SQLException ex) {
               Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
           }
          return list;
    }

    @Override
    public boolean ModifierEvenenmentPlace(Evenement e) throws SQLDataException {
        String query = "UPDATE `evenements` SET `nbreparticipants`=? WHERE `id` = ?";
		PreparedStatement st;
        try {
                st = cn.prepareStatement(query);
            
                st.setInt(1,e.getNbreparticipants());
                st.setInt(2,e.getId());
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
    
    
      public List<Participer> findUSerByIdParticip(int id) throws SQLException {
               List<Participer> list=new ArrayList<Participer>();

     
            
             String req="select * from participer where idEvenement="+id;
             Statement st=cn.createStatement();
             ResultSet rs=st.executeQuery(req);
             while(rs.next())
             {
                 Participer e= new Participer();
                 e.setId(rs.getInt(1));
                 e.setIdEvent(rs.getInt(2));
                 e.setIdUuser(rs.getInt(3));
               
                list.add(e);
                         
          
         } 
    return list ;
    
}
      
      
       public List<Integer> getAllEvent() throws SQLDataException {

        
        List<Integer> list =new ArrayList<Integer>();
        int count =0;
        
        String requete="select id from evenements";
         try{
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Evenement e = new Evenement();
                e.setId(rs.getInt(1));
               
                  int i = e.getId();
                
                list.add(i);
                
                count++;
            }
            if(count == 0){
                return null;
           }else{

               return list;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}

}

