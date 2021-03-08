/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.controller;

import artraction.entity.panier;
import artraction.service.panierService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author zeyne
 */

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class CommandeController implements Initializable {
    @FXML
    private Button confirmer_btn;
    @FXML
    private Button annuler_btn;

    @FXML
    private TextField adresse_livraison;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        confirmer_btn.setOnAction(event -> {

            try {
                panier p1 = new panier();
                panierService p = panierService.getInstance();
                int x=p.update(p1);
                if (x>0)
                    System.out.println("update succeded");
            } catch (SQLException ex) {
                Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        annuler_btn.setOnAction(event -> {

           try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/artraction/view/panier.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
      
    }    
    
}
