/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import utils.ConnexionSingleton;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class MembreController implements Initializable {

    @FXML
    private TextField tfusername;
    @FXML
    private TextField tfpassword;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfaddresse;
    @FXML
    private TextField tfnumero;
    @FXML
    private Button fc;
    @FXML
    private ImageView changeimg;
    @FXML
    private TextField tfage;
    @FXML
    private TextArea textAr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            textAr.setText(ConnexionSingleton.getInstance().uInfos.toString());
        } catch (Exception ex) {
            Logger.getLogger(MembreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void loadImage(ActionEvent event) {
    }
    
}
