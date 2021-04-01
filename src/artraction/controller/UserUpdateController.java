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
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class UserUpdateController implements Initializable {
    
    @FXML
    private TextField tfage;
    @FXML
    private TextField tfusername;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfpassword;
    @FXML
    private TextField tfaddresse;
    @FXML
    private TextField tfnumero;
    @FXML
    private ComboBox tfrole;

    /**
     * Initializes the controller class.
     */
   
    @FXML
    private AnchorPane tfageap;
    @FXML
    private Button btnajouter;
    @FXML
    private Button bntannuler;
    
     ObservableList<userEntity> listM;
    ObservableList<userEntity> dataList;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String oldmail = "";
    @FXML
    private Label accessor;
    @FXML
    private Button btncompte;
    @FXML
    private Button logout;
    @Override
	public void initialize(URL url, ResourceBundle rb) {
		tfrole.getItems().addAll(
				"Client",
				"Admin",
                                "Artiste"
		);
		
		
		tfrole.setValue("Client");
	}	

	private void ModifierClicked(MouseEvent event) throws IOException, Exception {
            System.out.println("Updating user that has email ");
            System.out.println(accessor.getText());
		userEntity sU = new userEntity();
		
		if (!tfusername.getText().equals("") && !tfemail.getText().equals("")
				&& !tfpassword.getText().equals("") && !tfnumero.getText().equals("") 
				&& !tfage.getText().equals("")&& !tfaddresse.getText().equals("")) {
          
           
            User.getInstance().modifier(new userEntity(Integer.parseInt(tfnumero.getText()),Integer.parseInt(tfage.getText()),tfusername.getText(), tfemail.getText(),tfpassword.getText(), tfaddresse.getText(),tfrole.getValue().toString()),accessor.getText());
                            Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  User Modifier")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(1));
               n.darkStyle();
               n.show();
          
			   AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/User.fxml"));
        tfageap.getChildren().setAll(pane);
          
          
		}
	}
        
//	 userEntity u = new userEntity();
//	public void setUser(userEntity u) {
//		this.u = u;
//	}
//	
//	public void updateField() {
//		tfusername.setText(u.getUsername());
//		tfemail.setText(u.getEmail());
//		tfpassword.setText(u.getPassword());
//		tfnumero.setText(u.getNumero() + "");
//		tfage.setText(u.getAge() + "");
//		tfaddresse.setText(u.getAdresse());
//		tfrole.setValue(u.getRole());	
//	}


  
    

    @FXML
    private void btnEcreate_clic(ActionEvent event) throws Exception{
                    System.out.println("Updating user that has email ");
            System.out.println(accessor.getText());
		
		if (!tfusername.getText().equals("") && !tfemail.getText().equals("")
				&& !tfpassword.getText().equals("") && !tfnumero.getText().equals("") 
				&& !tfage.getText().equals("")&& !tfaddresse.getText().equals("")) {
          
           
            User.getInstance().modifier(new userEntity(Integer.parseInt(tfnumero.getText()),Integer.parseInt(tfage.getText()),tfusername.getText(), tfemail.getText(),tfpassword.getText(), tfaddresse.getText(),tfrole.getValue().toString()),accessor.getText());
                            Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  User Modifier")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(1));
               n.darkStyle();
               n.show();
          
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/artraction/view/compte.fxml"));              
				Parent parent = loader.load();
				tfageap.getChildren().setAll(parent);
		}
    }
    
    

    @FXML
    private void oncliqueannuler(ActionEvent event) throws Exception{
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/artraction/view/compte.fxml"));              
				Parent parent = loader.load();
				tfageap.getChildren().setAll(parent);
    }

    void updateField() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setuserEntity(userEntity u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void onAdminCompteClicked(MouseEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/User.fxml"));
       tfageap.getChildren().setAll( pane);
    }

    @FXML
    private void oncliquelogout(MouseEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/artraction/view/FXMLDocument.fxml"));
       tfageap.getChildren().setAll( pane);
    }

   

    
	
}
