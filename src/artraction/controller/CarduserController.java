/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.controller;
import artraction.controller.*;
import artraction.dao.User;
import artraction.entity.userEntity;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class CarduserController implements Initializable {

    @FXML
    private ImageView tfimage;
    @FXML
    private Label tfusername;
    @FXML
    private Label tfemail;
    @FXML
    private Label tfpassword;
    @FXML
    private Label tfnumero;
    @FXML
    private Label tfage;
    @FXML
    private Label tfaddresse;
    @FXML
    private Label tfrole;
    @FXML
    private Button modifier;
    @FXML
    private Button suprimer;
    @FXML
    private VBox lv;
    @FXML
    private VBox rootpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
     public void setData(userEntity user){
            tfusername.setText(user.getUsername());
             tfemail.setText(user.getEmail());
             tfpassword.setText(user.getPassword());
tfnumero.setText(String.valueOf(user.getNumero()));
tfage.setText(String.valueOf(user.getAge()));

               tfaddresse.setText(user.getAdresse());
                tfrole.setText(user.getRole());
               

    }

    @FXML
    private void cliquemodifier(ActionEvent event) throws IOException {
		userEntity u = null;
		//u = lv.getSelectionModel().getSelectedItem();
		
		if(u != null) {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/UserUpdate.fxml"));
                        
                        //  Node userField = (Node)inside.getChildren().get(11);
                        rootpane.getChildren().setAll(pane);
                        AnchorPane inside = (AnchorPane) rootpane.getChildren().get(0);
                        TextField userField = (TextField) inside.getChildren().get(6);
                        userField.setText(u.getUsername());
                        TextField emailField = (TextField) inside.getChildren().get(9); 
                        emailField.setText(u.getEmail());
                        TextField passField = (TextField) inside.getChildren().get(12); 
                        passField.setText(u.getPassword());
                        TextField numeroField = (TextField) inside.getChildren().get(15); 
                        numeroField.setText(u.getNumero()+"");
                        TextField ageField = (TextField) inside.getChildren().get(18); 
                        ageField.setText(u.getAge()+"");
                        System.out.println(u.getAdresse());
                        TextField adresseField = (TextField) inside.getChildren().get(21); 
                        adresseField.setText(u.getAdresse());
                        ComboBox cb = (ComboBox) inside.getChildren().get(24);
                        cb.setValue(u.getRole());
                        Label hiddenT = (Label) inside.getChildren().get(27);
                        hiddenT.setText(u.getEmail());
		}
    }
public void loadData() throws Exception {
		//lv.getItems().setAll(User.getInstance().afficher());
	}
    @FXML
    void suprimerClicked(MouseEvent event) throws Exception {
		userEntity u = null;
		//u = lv.getSelection().getSelectedItem();
		
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
    
     }
