/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.service;

import artraction.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import artraction.entity.formation;

/**
 *
 * @author asus
 */
public class ServiceFormation implements IServices<formation> {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void Ajouter(formation t) {
        try{
        String requete = "INSERT INTO formation(nom,adresse,prixph,contact,image) VALUES (?,?,?,?,?)";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setString(1, t.getNom());
        pst.setString(2, t.getAdresse());
        pst.setInt(3, t.getPrixph());
        pst.setString(4, t.getContact());
        pst.setString(5, t.getImage());
        pst.executeUpdate();
        System.out.println("formation ajouté !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    }

    @Override
    public void Supprimer(formation t) {
        try{
        String requete = "DELETE FROM formation WHERE id_formation=?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, t.getId_formation());
        pst.executeUpdate();
        System.out.println("formation Supprimé !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    }
    public int deleteFormation(int id_formation) throws SQLException {
        int i = 0;
        try {
            Statement ste = cnx.createStatement();
            String sql = "delete from formation where id_formation=" + id_formation;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFormation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    @Override
    public void Modifier(formation t) {
       try{
        String requete = "UPDATE formation SET nom=?,adresse= ?,prixph=?,contact=?,image=? WHERE id=?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setString(1, t.getNom());
        pst.setString(2, t.getAdresse());
        pst.setInt(3, t.getPrixph());
        pst.setString(4, t.getContact());
        pst.setString(5, t.getImage());
        pst.executeUpdate();
        System.out.println("formation ajouté !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<formation> Afficher() {
         List<formation> list = new ArrayList<>();
        try{
        String requete = "SELECT * FROM formation";
        PreparedStatement pst = cnx.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            list.add(new formation(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6)));
        }
        System.out.println("formation !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
        return list;
    }
    
}
