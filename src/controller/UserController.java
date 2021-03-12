/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.User;
import entity.userEntity;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Houcem
 */
public class UserController implements Initializable {
userEntity u = null;
    @FXML
    private AnchorPane rootpane;
    @FXML
    private Button btncompte;
    @FXML
    private Button logout;

	/**
	 * Initializes the controller class.
	 */
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
                
    try {
        loadData();
    } catch (Exception ex) {
        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
    }
//                modifier.setOnAction( event -> {
//                u = lv.getSelectionModel().getSelectedItem();
//                    
//                    try {
//                        Parent page1 = FXMLLoader.load(getClass().getResource(""));
//                  
//
//                    Scene scene = new Scene(page1);
//                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                    stage.setScene(scene);
//                    stage.show();
//                      } catch (IOException ex) {
//                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//                );
	}	
	
	public void loadData() throws Exception {
		lv.getItems().setAll(User.getInstance().afficher());
	}
	
    private AnchorPane ap;

    @FXML
    private Button ajouter;

	@FXML
    private ListView<userEntity> lv;
	
	@FXML
    private Button modifier;

    @FXML
    private Button suprimer;
	
	@FXML
    private TextField search;

    @FXML
    void ajouterClicked(MouseEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/UserCreate.fxml"));
        rootpane.getChildren().setAll(pane);
    }


    @FXML
    void suprimerClicked(MouseEvent event) throws Exception {
		userEntity u = null;
		u = lv.getSelectionModel().getSelectedItem();
		
		if(u != null) {
			User.getInstance().supprimer(u);
			Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  User suprrimer")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(1));
               n.darkStyle();
               n.show();
			   loadData();
		}
    }
	
	@FXML
    void searchTextChanged(InputMethodEvent event) {
    }
	
	@FXML
    void searchKeyRelaesed(KeyEvent event) throws Exception {
		
		lv.getItems().setAll(User.getInstance().search(search.getText()));
    }

    @FXML
    private void cliquemodifier(ActionEvent event) throws IOException {
		userEntity u = null;
		u = lv.getSelectionModel().getSelectedItem();
		
		if(u != null) {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/UserUpdate.fxml"));
                        
                        //  Node userField = (Node)inside.getChildren().get(11);
                        rootpane.getChildren().setAll(pane);
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
    private void onAdminCompteClicked(MouseEvent event) {
    }

    @FXML
    private void oncliquelogout(MouseEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/FXMLDocument.fxml"));
       rootpane.getChildren().setAll( pane);
    }

    
	
}
