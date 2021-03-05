/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.CommentEvenement;
import entities.Evenement;
import entities.User;
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
public class CommentService implements CommentServiceInterface{
    
     Connection cn=DataSource.getInstance().getCnx();

    @Override
    public void addComment(CommentEvenement c) throws SQLDataException {
        
   String query ="INSERT INTO `comment`( `idevenement`, `iduser`, `commentaire`) VALUES (?,?,?)";
 
         PreparedStatement st;
        
        try {
            st = cn.prepareStatement(query);
                st.setInt(1,c.getIdEvt().getId());
                st.setInt(2,c.getId().getId());
                st.setString(3,c.getComment());
                st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void modifieComment(CommentEvenement c) throws SQLDataException {
        
         try {
             PreparedStatement pst=cn.prepareStatement("UPDATE `comment` SET `commentaire`=? WHERE `idcomment` = ?");
             pst.setString(1,c.getComment());
             pst.setInt(2,c.getIdComment());
             pst.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
         }

    }

    @Override
    public void deleteComment(int idComment) throws SQLDataException {
         try {
             Statement st=cn.createStatement();
             String req= "DELETE FROM `comment` WHERE `idcomment` ="+idComment;  
             st.executeUpdate(req);
         } catch (SQLException ex) {
             Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
  
    public List<CommentEvenement> getAllComment() throws SQLDataException {
         try {
             List<CommentEvenement> list=new ArrayList<CommentEvenement>();
             int c=0;
             String req="select * from comment";
             Statement st=cn.createStatement();
             ResultSet rs=st.executeQuery(req);
             
             while(rs.next())
             {
                 CommentEvenement cm=new CommentEvenement();
                 cm.setIdComment(rs.getInt(1));
                 cm.setIdEvt(findEvenementById(rs.getInt(2)));
                 cm.setId(findUserById(rs.getInt(3)));
                 cm.setComment(rs.getString(4));
                 
                 list.add(cm);
                 c++;
                 
             }
             if (c == 0)
             {
                 return null;
             }else {
                 return list;
             }
         } catch (SQLException ex) {
             Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
             return null;
        
         }
    }

  

    @Override
    public Evenement findEvenementById(int id) {
        
       try {
             Evenement evt=new Evenement();
             int c=0;
             String req="select * from evenements where id="+id;
             Statement st=cn.createStatement();
             ResultSet rs=st.executeQuery(req);
             while(rs.next())
             {
                 evt.setId(rs.getInt(1));
                 evt.setLieu(rs.getString(2));
                 evt.setTitre(rs.getString(3));
                 evt.setNbreplaces(rs.getInt(4));
                 evt.setNbreparticipants(rs.getInt(5));
                 evt.setDateDebut(rs.getDate(6));
                 evt.setDateFin(rs.getDate(7));
                 evt.setImage(rs.getString(8));

                 c++;
                         }
             if(c==0)
             {
                 return null;
             }else {
                 return evt;
             }
         } catch (SQLException ex) {
             Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
             return null;

         }
       } 
    

    @Override
    public User findUserById(int id) {
          try {
             User u=new User();
             int c=0;
             String req="select * from user where id="+id;
             Statement st=cn.createStatement();
             ResultSet rs=st.executeQuery(req);
             while(rs.next())
             {
                 u.setId(rs.getInt(1));
                 u.setUsername(rs.getString(2));
                 u.setMail(rs.getString(3));
                 c++;
                         }
             if(c==0)
             {
                 return null;
             }else {
                 return u;
             }
         } catch (SQLException ex) {
             Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
             return null;

         }
       }

    @Override
    public ObservableList<CommentEvenement> getAllCommentByEvent(Evenement e) throws SQLDataException {
   try {

             List<CommentEvenement> list=new ArrayList<CommentEvenement>();
             int c=0;
             String req="select * from comment where idevenement="+e.getId();
             Statement st=cn.createStatement();
             ResultSet rs=st.executeQuery(req);
             
             while(rs.next())
             {
                 CommentEvenement cm=new CommentEvenement();
                 cm.setIdComment(rs.getInt(1));
                 cm.setIdEvt(findEvenementById(rs.getInt(2)));
                 cm.setId(findUserById(rs.getInt(3)));
                 cm.setComment(rs.getString(4));
                 
                 list.add(cm);
                 c++;
                 
             }
             if (c == 0)
             {
                 return null;
             }else {
                              ObservableList lc = FXCollections.observableArrayList(list);

                 return lc;
             }
         } catch (SQLException ex) {
             Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
             return null;
        
         }    }

    @Override
    public CommentEvenement finCommentById(int id) {
       try {
             CommentEvenement cmt=new CommentEvenement();
             int c=0;
             String req="select * from comment where idcomment="+id;
             Statement st=cn.createStatement();
             ResultSet rs=st.executeQuery(req);
             while(rs.next())
             {
                 cmt.setIdComment(rs.getInt(1));
                 cmt.setIdEvt(findEvenementById(rs.getInt(2)));
                 cmt.setId(findUserById(rs.getInt(3)));
                 cmt.setComment(rs.getString(4));
               
                 c++;
                         }
             if(c==0)
             {
                 return null;
             }else {
                 return cmt;
             }
         } catch (SQLException ex) {
             Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
             return null;

         }    }

   
    }
    

  

           //  String req="select * from comment where idevenement="+e.getId();

    

