package controller;

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
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("/view/FXMLDocument.fxml"));
       rootpane.getChildren().setAll( pane);
    }
    

    @FXML
    void oncliqueenchere(MouseEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("/view/FXMLDocument.fxml"));
       rootpane.getChildren().setAll( pane);
    }

    @FXML
    void oncliqueevenement(MouseEvent event) throws Exception{
       AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("/view/FXMLDocument.fxml"));
       rootpane.getChildren().setAll( pane);
    }

    @FXML
    void oncliqueformation(MouseEvent event) throws Exception{
       AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("/view/FXMLDocument.fxml"));
       rootpane.getChildren().setAll( pane);
    }

    @FXML
    void oncliquelogin(MouseEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/FXMLDocument.fxml"));
       rootpane.getChildren().setAll( pane);
    }

    @FXML
    void oncliqueproduit(MouseEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("/view/FXMLDocument.fxml"));
       rootpane.getChildren().setAll( pane);
    }
}
