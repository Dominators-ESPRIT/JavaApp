package artraction.controller;
import artraction.service.User;
import artraction.entity.userEntity;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class ConfirmController implements Initializable {

	@FXML
    private AnchorPane apppp;
	 
    @FXML
    private Label emailLabel;

    @FXML
    private TextField code;

    @FXML
    private Button validate;

	private int time = 1;
	
	private userEntity u = new userEntity();
	
	private int codeInt;
	
	public void setSetAll(userEntity inputuserEntity, int inputCode) {
		this.u = inputuserEntity;
		this.codeInt = inputCode;
		emailLabel.setText("Check Your Mail ("+u.getEmail()+")");
	}
	
    @FXML
    void validateClicked(MouseEvent event) throws IOException, Exception {
		time--;
		if(code.getText().equals(""+codeInt)) {
			User sU = User.getInstance();
			sU.ajouter(u);
                            Notifications n = Notifications.create()
                              .title("SUCCESS")
                              .text("  Sign up: Success ")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(1));
               n.darkStyle();
               n.show();
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/FXMKDocument.fxml"));
        apppp.getChildren().setAll(pane);
          

			   
		} else {
                    Notifications n = Notifications.create()
                              .title("Failed")
                              .text("  Sign up: Failed ")
                              .position(Pos.TOP_CENTER)
                              .hideAfter(Duration.seconds(1));
               n.darkStyle();
               n.show();
                }
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//TPODO
	}

}
