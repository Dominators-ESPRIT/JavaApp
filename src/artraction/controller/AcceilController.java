package artraction.controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AcceilController {

    @FXML
    private Button btnevenement;

    @FXML
    private Button btnformation;

    @FXML
    private Button btncompetition;

    @FXML
    private Button btnproduit;

    @FXML
    private Button btnenchere;
     @FXML
    private AnchorPane rootpane;

    @FXML
    void oncliqueecompetition(MouseEvent event) throws Exception{
       Parent root = FXMLLoader.load(getClass().getResource("/artraction/view/FXMLDocument.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }
    

    @FXML
    void oncliqueenchere(MouseEvent event) throws Exception{
     Parent root = FXMLLoader.load(getClass().getResource("/artraction/view/FXMLDocument.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    void oncliqueevenement(MouseEvent event) throws Exception{
       Parent root = FXMLLoader.load(getClass().getResource("/artraction/view/FXMLDocument.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    void oncliqueformation(MouseEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/artraction/view/FXMLDocument.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    void oncliquelogin(MouseEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/artraction/view/FXMLDocument.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    void oncliqueproduit(MouseEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/artraction/view/FXMLDocument.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }
}
