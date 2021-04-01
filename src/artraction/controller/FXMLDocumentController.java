/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.controller;
import artraction.service.User;
import artraction.entity.userEntity;
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
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import artraction.utils.ConnexionSingleton;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javafx.scene.input.MouseEvent;
import static oracle.net.aso.C00.u;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class FXMLDocumentController implements Initializable {
public static final String ACCOUNT_SID = "ACaa141c929bfd92eb1c6f28ffead9cc91";
    public static final String AUTH_TOKEN = "6313fc7e7688412e74fd1b3eab89357d";

    @FXML
    private AnchorPane pane_login;
    @FXML
    private TextField tfusername;
    @FXML
    private TextField tfpassword;
    //private ComboBox role;
    @FXML
    private Button btn_login;
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
   User su;
  public User userInfos;
    @FXML
    private Button forget;
    @FXML
    private AnchorPane rootpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        role1.getItems().addAll("Choose Role","Admin","Artiste","Client");
       /* role.getItems().addAll("Choose Role","Admin","Artiste","Client");
        role.getSelectionModel().select(0);*/
    }    

    @FXML
    private void LoginpaneShow(ActionEvent event) {
        pane_signup.setVisible(false);
        pane_login.setVisible(true);
        //role.getSelectionModel().select(0);
    }

    @FXML
    private void SignuppaneShow(ActionEvent event) {
        pane_login.setVisible(false);
        pane_signup.setVisible(true);
        role1.getSelectionModel().select(0);
    }

    @FXML
    private void Login(ActionEvent event) throws Exception {
        if(!tfemail1.getText().equals("")&&
                !tfpassword1.getText().equals(""))
        {
            System.out.println(tfemail1.getText() + " " + tfpassword1.getText());
            userEntity _u = User.getInstance().FindUser(tfemail1.getText(), tfpassword1.getText());
            if(_u != null)
            {
                ConnexionSingleton.getInstance().uInfos = _u;
                String fileDestination = "";
                switch (_u.getRole()) {
                    case "Admin":
                        fileDestination = "CPanel.fxml";
                        break;
                    case "Artiste":
                        fileDestination = "compteartiste.fxml";
                        break;
                    case "Client":
                        fileDestination = "comptemembre.fxml";
                        break;
                    default:
                        break;
                }

                btn_login.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("/artraction/view/"+fileDestination));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
            }
        } else {
            Notifications n = Notifications.create()
            .title("Erreur")
            .text("Merci de vérifier les champs")
            .position(Pos.TOP_CENTER)
            .hideAfter(Duration.seconds(1));
            n.darkStyle();
            n.show();     
        }
    }
public static boolean isNumeric(String strNum) {
            if (strNum == null) {
                return false;
            }
            try {
                double d = Double.parseDouble(strNum);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return true;
        }
    @FXML
    private void add_users(ActionEvent event) throws Exception {
        		User sU = User.getInstance();
		
		String email = tfemail.getText();
                		String numero = tfnumero.getText();

		
		if (!role1.getValue().toString().equals("Choose Role") && !tfusername.getText().equals("") && !tfemail.getText().equals("")
				&& !tfpassword.getText().equals("")  && !tfnumero.getText().equals("") 
				&& !tfage.getText().equals("")&& !tfaddresse.getText().equals("")) {
          
           if(!email.contains("@gmail.") && !email.contains("@esprit.")) {
			   Notifications n = Notifications.create()
                              .title("failed")
                              .text("  check email incorrect format ! ")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(1));
               n.darkStyle();
               n.show();
		   }
           
		  else {
               User.getInstance().AddUser(tfusername.getText(), tfemail.getText(), tfpassword.getText(), Integer.parseInt(tfnumero.getText()), Integer.parseInt(tfage.getText()), tfaddresse.getText(), role1.getValue().toString(), imagePath);
                             //String num = Integer.toString(u));
                             // sms("your profil are Conected", "+216"+num);                         
			  sU.User = u;
			   int code = sU.sendConfirmation(email);
			   FXMLLoader loader = new FXMLLoader(getClass().getResource("/artraction/view/acceil.fxml"));              
				Parent parent = loader.load();
				pane_signup.getChildren().setAll(parent);

				 ConfirmController controller = (ConfirmController) loader.getController();
				controller.setSetAll(u, code);

				
	
		   }
          
          
          
           
       } else {
                       Notifications n = Notifications.create()
                              .title("failed")
                              .text("  check your informations ! ")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(1));
               n.darkStyle();
               n.show();
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
    private void ForgotAction(MouseEvent event) throws IOException {
 AnchorPane pane = FXMLLoader.load(getClass().getResource("/artraction/view/Sendcode.fxml"));
       rootpane.getChildren().setAll(pane);       
    }

    public static void sms(String msg, String phoneNumber) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber(phoneNumber),
                new PhoneNumber("+17743571480"),
                msg).create();
        System.out.println(message.getSid());
    }
}
