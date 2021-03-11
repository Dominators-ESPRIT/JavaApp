/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class CompteartisteController implements Initializable {
@FXML
    private Button btncompte;
    @FXML
    private Button logout;
     @FXML
    private AnchorPane rootpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onArtisteCompteClicked(MouseEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("profilartiste.fxml"));
       rootpane.getChildren().setAll( pane);
    }

    @FXML
    private void oncliquelogout(MouseEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
       rootpane.getChildren().setAll( pane);
    }

    @FXML
    private void onclique(MouseEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("profilartiste.fxml"));
       rootpane.getChildren().setAll( pane);
    }
    
}
