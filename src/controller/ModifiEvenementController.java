/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.CommentEvenement;
import entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.CommentService;
import service.EvenementService;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ModifiEvenementController implements Initializable {

    @FXML
    private TextField titre;
    @FXML
    private TextField lieu;
    @FXML
    private TextField nbp;
    @FXML
    private TextField dd;
    @FXML
    private TextField df;

     public static int idmof ; 
        CommentService cs= new CommentService();
    @FXML
    private Button m;
    @FXML
    private Button cancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Evenement e = new Evenement();
        System.out.println("controller.azzzzzzzzzzz"+idmof+"zzzzzzz.initialize()");
       e= cs.findEvenementById(idmof);
        titre.setText(e.getTitre());
        lieu.setText(e.getLieu());
        nbp.setText(String.valueOf(e.getNbreplaces()));
        dd.setText(String.valueOf(e.getDateDebut()));
        df.setText(String.valueOf(e.getDateFin()));
       
        
        
        
        
        
    }    

    public void setTitre(TextField titre) {
        this.titre.setText(titre.getText()); 
    }

    public void setLieu(TextField lieu) {
        this.lieu.setText(lieu.getText()); 
    }

    public void setNbp(TextField nbp) {
        this.nbp.setText(nbp.getText());
    }

    public void setDd(TextField dd) {
        this.dd.setText(dd.getText()); 
    }

    public void setDf(TextField df) {
        this.df.setText(df.getText());

    }

    @FXML
    private void modif(ActionEvent event) throws SQLDataException {
        
        
                String t= titre.getText();
                String l= lieu.getText();
                int nbp= Integer.parseInt(this.nbp.getText());
                Date dd = java.sql.Date.valueOf(this.dd.getText());
                Date df = java.sql.Date.valueOf(this.dd.getText());
                EvenementService Es= new EvenementService();
                Evenement e= new Evenement(idmof,nbp, l, t, (java.sql.Date)dd, (java.sql.Date)df);
                Es.ModifierEvenenment(e);
                
                
                Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/gui/listeEvenement.fxml"));
                            Stage myWindow = (Stage)titre.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(ModifiEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                        } 
        
                
    }

    @FXML
    private void cancel(ActionEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/gui/listeEvenement.fxml"));
            Stage myWindow = (Stage)titre.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("page name");
            //myWindow.setFullScreen(true);
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifiEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
