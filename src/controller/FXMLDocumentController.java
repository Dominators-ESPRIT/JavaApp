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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import utils.ConnexionSingleton;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane pane_login;
    @FXML
    private TextField tfusername;
    @FXML
    private TextField tfpassword;
    @FXML
    private ComboBox role;
    @FXML
    private Button btn_login;
    @FXML
    private Button btnForgot;
    @FXML
    private AnchorPane pane_signup;
    @FXML
    private TextField tfemail;
    private ComboBox type_up;
    @FXML
    private TextField tfaddresse;
    @FXML
    private TextField tfnumero;
    @FXML
    private TextField tfage;
    @FXML
    private PasswordField tfpassword1;
    @FXML
    private TextField tfemail1;
    @FXML
    private Button fc;
    FileChooser Fc = new FileChooser();
    @FXML
    private ImageView changeimg;
    
    private String imagePath = "";
    @FXML
    private ComboBox role1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        role1.getItems().addAll("Admin","Artiste","Client");
        role.getItems().addAll("Admin","Artiste","Client");
    }    

    @FXML
    private void LoginpaneShow(ActionEvent event) {
        pane_signup.setVisible(false);
        pane_login.setVisible(true);
    }

    @FXML
    private void SignuppaneShow(ActionEvent event) {
        pane_login.setVisible(false);
        pane_signup.setVisible(true);
    }

    @FXML
    private void Login(ActionEvent event) throws Exception {
        System.out.println(tfemail1.getText() + " " + tfpassword1.getText() + " " + role.getValue().toString());
        userEntity _u = User.getInstance().FindUser(tfemail1.getText(), tfpassword1.getText(), role.getValue().toString());
        if(_u != null)
        {
            ConnexionSingleton.getInstance().uInfos = _u;
            String fileDestination = "";
            if(_u.getRole() == "Admin")
                fileDestination = "CPanel.fxml";
            else if(_u.getRole() == "Artiste")
                fileDestination = "compteartiste.fxml";
            else if(_u.getRole() == "Client")
                fileDestination = "comptemembre.fxml";
                    
                btn_login.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/view/compteartiste.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
//                btn_login.getScene().getWindow().hide();       
//        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("/view/"+fileDestination));
//        Stage mainStage = new Stage();
//        Scene scene = new Scene(root);
//        mainStage.setScene(scene);
//        mainStage.show();
          
        }
    }

    @FXML
    private void add_users(ActionEvent event) throws Exception {
        System.out.println("TEsting add user ");
        System.out.println(tfusername.getText() + " " + tfemail.getText() + " " + tfpassword.getText() + " " + Integer.parseInt(tfnumero.getText()) + " " + Integer.parseInt(tfage.getText()) + " " + tfaddresse.getText() + " " + role1.getValue().toString() + " " + imagePath);
        User.getInstance().AddUser(tfusername.getText(), tfemail.getText(), tfpassword.getText(), Integer.parseInt(tfnumero.getText()), Integer.parseInt(tfage.getText()), tfaddresse.getText(), role1.getValue().toString(), imagePath);
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
    
}
