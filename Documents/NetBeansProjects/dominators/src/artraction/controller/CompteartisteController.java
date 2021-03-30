/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.controller;
import artraction.service.User;
import artraction.entity.userEntity;
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
    private ListView<userEntity> lv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void loadData() throws Exception {
		lv.getItems().setAll(User.getInstance().afficher());
	}

    private void onArtisteCompteClicked(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/artraction/view/profilartiste.fxml"));
       rootpane.getChildren().setAll( pane);
//	userEntity u = null;	
//        u = lv.getSelectionModel().getSelectedItem();
//		if(u != null) {
//                        AnchorPane inside = (AnchorPane) rootpane.getChildren().get(0);
//                        TextField userField = (TextField) inside.getChildren().get(3);
//                        userField.setText(u.getUsername());
//                        TextField emailField = (TextField) inside.getChildren().get(4); 
//                        emailField.setText(u.getEmail());
//                        TextField passField = (TextField) inside.getChildren().get(5); 
//                        passField.setText(u.getPassword());
//                        TextField numeroField = (TextField) inside.getChildren().get(6); 
//                        numeroField.setText(u.getNumero()+"");
//                        TextField ageField = (TextField) inside.getChildren().get(7); 
//                        ageField.setText(u.getAge()+"");
//                        TextField adresseField = (TextField) inside.getChildren().get(8); 
//                        adresseField.setText(u.getAdresse());
//                        
//			
//                        
//                        //  Node userField = (Node)inside.getChildren().get(11);
//                        
//                       
//		}
//		AnchorPane pane = FXMLLoader.load(getClass().getResource("/artraction/view/profilartiste.fxml"));
//		rootpane.getChildren().setAll(pane);
                        
    }
    @FXML
    private void oncliquelogout(MouseEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/artraction/view/FXMLDocument.fxml"));
       rootpane.getChildren().setAll( pane);
    }

    private void onclique(MouseEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/artration/view/profilartiste.fxml"));
       rootpane.getChildren().setAll( pane);
    }

    @FXML
    private void onAdminCompteClicked(MouseEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/artraction/view/profilartiste.fxml"));
       rootpane.getChildren().setAll( pane);
    }

   
    
}
