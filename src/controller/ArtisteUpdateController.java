/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ArtisteUpdateController implements Initializable {

    @FXML
    private TextField tfusername;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfpassword;
    @FXML
    private TextField tfnumero;
    @FXML
    private PasswordField tfage;
    @FXML
    private Button btnajouter;
    @FXML
    private Button bntannuler;
    @FXML
    private TextField tfaddresse;
    @FXML
    private ComboBox<?> tfrole;
    @FXML
    private Button btnimage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnEcreate_clic(ActionEvent event) {
    }

    @FXML
    private void oncliqueannuler(ActionEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Artiste.fxml"));
     pane.getChildren().setAll( pane);
    }
    
}
