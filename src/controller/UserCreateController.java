/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.User;
import entity.userEntity;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class UserCreateController implements Initializable {

    @FXML
    private TextField tfage;
    @FXML
    private TextField tfusername;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfpassword;
    @FXML
    private Button bntannuler;
    @FXML
    private TextField tfaddresse;
    @FXML
    private Button btnajouter;
    @FXML
    private TextField tfnumero;
    @FXML
    private ChoiceBox tfrole;
    @FXML
    private AnchorPane tfageap;
    private Button fc;
     FileChooser Fc = new FileChooser();
    private ImageView changeimg;
  
    
    private String imagePath = "";

    /**
     * Initializes the controller class.
     */
    @Override
	public void initialize(URL url, ResourceBundle rb) {
		tfrole.getItems().addAll(
				"Client",
				"Admin",
                                "Artiste"
		);
		
		
		tfrole.setValue("Client");
	}	

     @FXML
    void btnE_cancel_clic(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/User.fxml"));
       tfageap.getChildren().setAll(pane);
    }

    @FXML
    void btnEcreate_clic(ActionEvent event) throws IOException, Exception {
		
		
		if (!tfusername.getText().equals("") && !tfemail.getText().equals("")
				&& !tfpassword.getText().equals("") && !tfnumero.getText().equals("") 
				&& !tfage.getText().equals("")&& !tfaddresse.getText().equals("")) {
          
           
            
			User.getInstance().ajouter(new userEntity(Integer.parseInt(tfnumero.getText()),Integer.parseInt(tfage.getText()),tfusername.getText(), tfemail.getText(),tfpassword.getText(), tfaddresse.getText(),tfrole.getValue().toString()));
                            Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  User ajouté")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(1));
               n.darkStyle();
               n.show();
          
			   AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/User.fxml"));
        tfageap.getChildren().setAll(pane);
          
          
           
       }
    }

    private void loadImage(ActionEvent event) throws IOException {
        Stage stage = (Stage)fc.getScene().getWindow();
        	File file = Fc.showOpenDialog(stage);
	if (file!=null){
		 Path source = Paths.get(file.getAbsoluteFile().toURI());
		 Path destination = Paths.get("C://wamp64/www/pidev/web/images/"+file.getAbsoluteFile().getName());
	 //   System.out.println(""+file.getAbsoluteFile().getName());
		Files.copy(source, destination,StandardCopyOption.REPLACE_EXISTING);
                imagePath = file.getAbsoluteFile().toURI().toString();
		Image image = new Image(imagePath);
		changeimg.setImage(image); 
	}
    }

	
}
