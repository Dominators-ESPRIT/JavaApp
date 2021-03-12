/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.service;

import artraction.entity.Competition;
import artraction.utilis.ConnexionSingleton;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author boukhris
 */
public class CompetitionService implements CompetitionServiceInterface {

    Connection cn = ConnexionSingleton.getInstance().getCnx();

    @Override
    public void Add_Competition(Competition c) throws SQLDataException {
        String query = "INSERT INTO competition( `nom`, `theme`,`date_debut`,`date_fin`,`type`) VALUES (?,?,?,?,?)";

        PreparedStatement st;

        try {
            st = cn.prepareStatement(query);
            st.setString(1, c.getNom());
            st.setString(2, c.getTheme());
            st.setDate(3, (Date) c.getDate_debut());
            st.setDate(4, (Date) c.getDate_fin());
            st.setString(5, c.getType());

            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CompetitionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean Delate_Competition(int ref_competition) throws SQLException {
        int res = 0;
        PreparedStatement ps = cn.prepareStatement("DELETE FROM `competition` WHERE ref_competition=?;");
        ps.setInt(1, ref_competition);
        res = ps.executeUpdate();
        if (res != 0) {
            System.out.println("Competition supprimée avec succès ! ");
        } else {
            System.out.println("Suppression impossible");
        }
        return true;
    }

    @Override
    public boolean Edit_Competition(Competition c) throws SQLException {

        int res=0;
        //String query = "UPDATE `competition` SET `nom`=?,`theme`=?,`date_debut`=?,`date_fin`=?,`type`=? WHERE `ref_competition` = ?";
       PreparedStatement st = cn.prepareStatement("UPDATE `competition` SET `nom`=?,`theme`=?,`date_debut`=?,`date_fin`=?,`type`=? WHERE `ref_competition` = ?");

        
        
            st.setString(1, c.getNom());
            st.setString(2, c.getTheme());
            st.setDate(3, (Date) c.getDate_debut());
            st.setDate(4, (Date) c.getDate_fin());
            st.setString(5, c.getType());
            st.setInt(6,c.getRef_competition());

                   res=st.executeUpdate();
        if(res!=0)
            System.out.println("Competition modifiée avec succès ! ");
        else
            System.out.println("Modificarion impossible");
        return true;
    }
//     public void modifierAgence (Agence a) throws SQLException {
//        int res=0;
//        PreparedStatement ps = connexion.prepareStatement("UPDATE `agence` SET `sponsor_id`=?,`logo`=?,`nom`=?,`matriculeFiscale`=?,`adresse`=?,`telephone`=?,`fax`=?,`site`=?,`email`=? WHERE `id`=?");
//        
//        ps.setInt(1,a.getSponsor().getId());
//        ps.setString(2,a.getLogo());
//        ps.setString(3,a.getNom());
//        ps.setString(4,a.getMatriculeFiscale());
//        ps.setString(5,a.getAdresse());
//        ps.setInt(6,a.getTelephone());
//        ps.setInt(7,a.getFax());
//        ps.setString(8,a.getSite());
//        ps.setString(9,a.getEmail());
//        ps.setInt(10,a.getId());
//        
//        res=ps.executeUpdate();
//        if(res!=0)
//            System.out.println("Agence modifiée avec succès ! ");
//        else
//            System.out.println("Modificarion impossible");
//
//    }

    @Override
    public List<Competition> getAllCompetition() throws SQLDataException {
        List<Competition> listc = new ArrayList<Competition>();
        int count = 0;

        String requete = "select * from competition";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Competition c = new Competition();
                c.setRef_competition(rs.getInt(1));
                c.setNom(rs.getString(2));
                c.setTheme(rs.getString(3));
                c.setDate_debut(rs.getDate(4));
                c.setDate_fin(rs.getDate(5));
                c.setType(rs.getString(6));
                listc.add(c);

                count++;
            }
            if (count == 0) {
                return null;
            } else {
                ObservableList lc_final = FXCollections.observableArrayList(listc);

                return lc_final;

            }
        } catch (SQLException ex) {
            Logger.getLogger(CompetitionService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public List<Competition> afficheCompetition(String nom) throws SQLDataException {
        List<Competition> listc = new ArrayList<Competition>();
        try {
            String req = "SELECT * FROM competition where `nom`='" + nom + "'";
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Competition c = new Competition();
                c.setRef_competition(rs.getInt(1));
                c.setNom(rs.getString(2));
                c.setTheme(rs.getString(3));
                c.setDate_debut(rs.getDate(4));
                c.setDate_fin(rs.getDate(5));
                c.setType(rs.getString(6));
                listc.add(c);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CompetitionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listc;
    }
    
    
    public boolean findByName(String nom) {
        ResultSet resultat;
        String req = "SELECT * FROM competition WHERE nom = ?";
        PreparedStatement ps = null;
        try {
            ps = cn.prepareStatement(req);
            ps.setString(1, nom);
            resultat = ps.executeQuery();
            if (resultat.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    
	
                
                
            
    
    
    
    

}