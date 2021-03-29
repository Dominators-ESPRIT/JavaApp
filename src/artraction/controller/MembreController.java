/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.controller;
import artraction.dao.User;
import artraction.entity.userEntity;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import artraction.utils.ConnexionSingleton;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class MembreController implements Initializable {

    FileChooser Fc = new FileChooser();
    private ComboBox tfrole;
    private Label accessor;
    private String imagePath = "";
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
    @FXML
    private Button btnajouter;
    @FXML
    private Button annuler;
    @FXML
    private AnchorPane tfageap;
    @FXML
    private Button suprimer;
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
            // TODO
            textAr.setText(ConnexionSingleton.getInstance().uInfos.toString());
        } catch (Exception ex) {
            Logger.getLogger(MembreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
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
    

    @FXML
    private void btnEcreate_clic(ActionEvent event) throws Exception{
               
           
		
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
          
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/compteartiste.fxml"));
		   tfageap.getChildren().setAll( pane);
          
          
		}
    }

      @FXML
    private void oncliqueannuler(ActionEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/compteartiste.fxml"));
       tfageap.getChildren().setAll( pane);
    }

    @FXML
    private void suprimerClicked(MouseEvent event) {
    }

     @FXML
    private void onAdminCompteClicked(MouseEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/membre.fxml"));
       tfageap.getChildren().setAll( pane);
    }

    @FXML
    private void oncliquelogout(MouseEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/FXMLDocument.fxml"));
       tfageap.getChildren().setAll( pane);
    }

    
}
