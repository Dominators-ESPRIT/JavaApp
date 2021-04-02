/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.service;

import artraction.entity.Competition;
import artraction.entity.OeuvreCompetitionnModel;
import artraction.entity.Oeuvre_competition;
import artraction.utilis.ConnexionSingleton;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author boukhris
 */
public class OeuvreCompetitionService implements OeuvrecompetitionInterface {

    Connection cn = ConnexionSingleton.getInstance().getCnx();

    @Override
    public void Add_Oeuvre_Competition(Oeuvre_competition oc) throws SQLDataException {
        String query = "INSERT INTO oeuvre_competition( `description`,`image`) VALUES (?,?)";

        PreparedStatement st;

        try {
            st = cn.prepareStatement(query);
            st.setString(1, oc.getDescription());
            st.setString(2, oc.getImage());
            ;

            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(OeuvreCompetitionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Oeuvre_competition> getAllOeuvreCompetition() throws SQLDataException {
        List<Oeuvre_competition> listoc = new ArrayList<Oeuvre_competition>();
        int count = 0;

        String requete = "select * from oeuvre_competition";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Oeuvre_competition oc = new Oeuvre_competition();
                oc.setDescription(rs.getString(3));
                oc.setImage(rs.getString(4));

                listoc.add(oc);

                count++;
            }
            if (count == 0) {
                return null;
            } else {
                ObservableList lc_final = FXCollections.observableArrayList(listoc);

                return lc_final;

            }
        } catch (SQLException ex) {
            Logger.getLogger(CompetitionService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public List<Oeuvre_competition> afficheOeuvreCompetition(String description) throws SQLDataException {
        List<Oeuvre_competition> listoc = new ArrayList<Oeuvre_competition>();
        try {
            String req = "SELECT * FROM competition where `nom`='" + description + "'";
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Oeuvre_competition oc = new Oeuvre_competition();
                oc.setDescription(rs.getString(3));
                oc.setImage(rs.getString(4));
                listoc.add(oc);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CompetitionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listoc;
    }

    @Override
    public List<OeuvreCompetitionnModel> getOeuvreCompetionById(int refCompetion) throws SQLDataException {
        List<OeuvreCompetitionnModel> list = new ArrayList<>();
        try {
            String req = "select o.nom, oc.description, u.username, oc.image, oc.ref_oc from oeuvre o , competition  c, oeuvre_competition oc, portfolio p, user u where o.ref = oc.Ref_Ov and c.ref_competition  = oc.ref_competition and o.fk_id_portfolio = p.id and p.Id_user = u.id and c.ref_competition = " + refCompetion;
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                String s = rs.getString(1);
                OeuvreCompetitionnModel ocm = new OeuvreCompetitionnModel(rs.getInt(5), rs.getString(1), rs.getString(2), rs.getString(3), rs.getBinaryStream(4));
                list.add(ocm);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CompetitionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    @Override
    public void Add_Oeuvre_Competition(File file, int refCompetition, int refOeuvre, String description) throws SQLDataException {

        String updateSQL = "insert into oeuvre_competition(Ref_Ov,description,image,ref_competition) values (? , ? , ?, ?)";
        String s = file.getAbsolutePath();
        try (Connection conn = ConnexionSingleton.getInstance().getCnx();
                PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

            // read the file
            File filee = new File(file.getAbsolutePath());
            FileInputStream input = new FileInputStream(file.getAbsoluteFile());

            // set parameters
            pstmt.setInt(1, refOeuvre);
            pstmt.setString(2, description);
            pstmt.setBinaryStream(3, input);
            pstmt.setInt(4, refCompetition);

            // store the resume file in database
            System.out.println("Reading file " + file.getAbsolutePath());
            System.out.println("Store file in the database.");
            pstmt.executeUpdate();

        } catch (SQLException | FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int getVoteByRefOC(int refOC) throws SQLDataException {
        int nbVote = 0;

        try {
            String req = "SELECT count(*) FROM votec where `ref_oc`='" + refOC + "'";
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(req);
            rs.next();
            nbVote = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(CompetitionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nbVote;
    }

    @Override
    public void Add_Vote_Oeuvre_Competition(int id_user_vote, int ref_oc, int ref_competition) throws SQLException {
        Connection conn = ConnexionSingleton.getInstance().getCnx();
        id_user_vote = 4;
        String selectSQL = "select count(*) from votec v , oeuvre_competition oc where v.ref_oc = oc.ref_oc and oc.ref_competition =" + ref_competition + " and v.id_user_vote =" + id_user_vote;
        Statement st;
        st = cn.createStatement();
        ResultSet rs = st.executeQuery(selectSQL);
        rs.next();
        int nbVote = rs.getInt(1);

        if (nbVote == 0) {
            String updateSQL = "insert into votec(ref_oc , id_user_vote) values (?, 4)";
            PreparedStatement pstmt = conn.prepareStatement(updateSQL);

            pstmt.setInt(1, ref_oc);

            pstmt.executeUpdate();
        } else {
            throw new SQLException();
        }

    }

    @Override
    public List<OeuvreCompetitionnModel> getOuvreCompetitionByArtist(int refCompetion, int artistId) throws SQLException {
        List<OeuvreCompetitionnModel> list = new ArrayList<>();
        try {
            String req = "select o.nom, oc.description, u.username, oc.image, oc.ref_oc from oeuvre o , competition  c, oeuvre_competition oc, portfolio p, user u where o.ref = oc.Ref_Ov and c.ref_competition  = oc.ref_competition and o.fk_id_portfolio = p.id and p.Id_user = u.id and c.ref_competition = " + refCompetion + " and u.id =" + artistId;
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                String s = rs.getString(1);
                OeuvreCompetitionnModel ocm = new OeuvreCompetitionnModel(rs.getInt(5), rs.getString(1), rs.getString(2), rs.getString(3), rs.getBinaryStream(4));
                list.add(ocm);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CompetitionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public void delateoeuvrecompetitionbyId(int ref_oc, int id_user) throws SQLException {
        int res = 0;
        PreparedStatement ps = cn.prepareStatement("DELETE oeuvre_competition FROM oeuvre_competition\n"
                + "INNER JOIN oeuvre\n"
                + "ON oeuvre.ref = oeuvre_competition.Ref_Ov \n"
                + "INNER JOIN portfolio\n"
                + "ON portfolio.id = oeuvre.fk_id_portfolio \n"
                + "WHERE portfolio.id_user = "+id_user+" and ref_oc = "+ref_oc+";");
        //ps.setInt(1, Ref_Ov);
        res = ps.executeUpdate();
        if (res != 0) {
            System.out.println("Competition supprimée avec succès ! ");
        } else {
            System.out.println("Suppression impossible");
        }

    }
    
         public static void sendEmail(String emailAddress, String sub) {
        String to = emailAddress;//change accordingly  
        //String subject = emailSubject;
        //String msg = emailMessage;

        String host = "smtp.gmail.com";//or IP address  
        final String username = "alaeddine.boukhris@esprit.tn";
        final String password = "203JMT1765";
        //Get the session object  
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", host);
        props.put("mail.smtp.starttls.enable", "true");
        /* mail.smtp.ssl.trust is needed in script to avoid error "Could not convert socket to TLS"  */
        props.setProperty("mail.smtp.ssl.trust", host);
        props.put("mail.smtp.auth", "true");
        // props.put("mail.smtp.user", username);
        //  props.put("mail.smtp.password", password);

        props.put("mail.smtp.port", "587");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("TGT Forget Password");
            message.setText(sub);

            // Send message  
            Transport.send(message);
            System.out.println("message sent successfully....");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
}
