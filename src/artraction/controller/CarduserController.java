/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.controller;
import artraction.controller.*;
import artraction.service.User;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
    private VBox lv;
    @FXML
    private VBox rootpane;
    @FXML
    private Button btnmail;

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
    private void envoyeremail(ActionEvent event) throws IOException {
     Parent root = FXMLLoader.load(getClass().getResource("/artraction/view/FXML.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();

    }
    
     }
