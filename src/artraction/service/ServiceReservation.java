/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.service;

import artraction.service.ServiceFormation;
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
import artraction.entity.Reservation;

/**
 *
 * @author asus
 */
public class ServiceReservation implements IServices<Reservation> {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void Ajouter(Reservation t) {
        try{
        String requete = "INSERT INTO reservation(id_formation,heure,nom,date) VALUES (?,?,?,?)";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, t.getId_formation());
        pst.setInt(2, t.getHeure());
        pst.setString(3, t.getNom());
        pst.setString(4, t.getDate());
        
        pst.executeUpdate();
        System.out.println("Reservation ajouté !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    }

    @Override
    public void Supprimer(Reservation t) {
        try{
        String requete = "DELETE FROM reservation WHERE id_stade=?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, t.getId_reservation());
        pst.executeUpdate();
        System.out.println("Reservation Supprimé !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    }
        public int deleteReservation(int id_reservation) throws SQLException {
        int i = 0;
        try {
            Statement ste = cnx.createStatement();
            String sql = "DELETE FROM reservation WHERE id_reservation=" + id_reservation;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFormation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    @Override
    public void Modifier(Reservation t) {
        try{
        String requete = "UPDATE reservation SET nom=?,heure=? WHERE id_reservation=?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setString(1, t.getNom());
        pst.setInt(2, t.getHeure());
        pst.executeUpdate();
        System.out.println("reservation supprimé !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Reservation> Afficher() {
         List<Reservation> list = new ArrayList<>();
        try{
        String requete = "SELECT * FROM reservation";
        PreparedStatement pst = cnx.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            list.add(new Reservation(rs.getInt(1),rs.getInt(2)));
        }
        System.out.println("Reservation !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
        return list;
    }
    
}
