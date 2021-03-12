/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.userEntity;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

    private void onArtisteCompteClicked(MouseEvent event) throws IOException {
		userEntity u = null;
		
		
		
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/membre.fxml"));
                        
                        //  Node userField = (Node)inside.getChildren().get(11);
                        rootpane.getChildren().setAll(pane);
                        if(u != null) {
                        AnchorPane inside = (AnchorPane) rootpane.getChildren().get(0);
                        TextField userField = (TextField) inside.getChildren().get(10);
                        userField.setText(u.getUsername());
                        TextField emailField = (TextField) inside.getChildren().get(11); 
                        emailField.setText(u.getEmail());
                        TextField passField = (TextField) inside.getChildren().get(12); 
                        passField.setText(u.getPassword());
                        TextField numeroField = (TextField) inside.getChildren().get(13); 
                        numeroField.setText(u.getNumero()+"");
                        TextField ageField = (TextField) inside.getChildren().get(14); 
                        ageField.setText(u.getAge()+"");
                        System.out.println(u.getAdresse());
                        TextField adresseField = (TextField) inside.getChildren().get(15); 
                        adresseField.setText(u.getAdresse());
                        ComboBox cb = (ComboBox) inside.getChildren().get(16);
                        cb.setValue(u.getRole());
                        Label hiddenT = (Label) inside.getChildren().get(17);
                        hiddenT.setText(u.getEmail());
		}
    }
    @FXML
    private void oncliquelogout(MouseEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/FXMLDocument.fxml"));
       rootpane.getChildren().setAll( pane);
    }

    private void onclique(MouseEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/artiste.fxml"));
       rootpane.getChildren().setAll( pane);
    }

    @FXML
    private void onAdminCompteClicked(MouseEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/artiste.fxml"));
       rootpane.getChildren().setAll( pane);
    }

   
    
}
