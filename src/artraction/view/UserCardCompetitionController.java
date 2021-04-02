/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.view;

import artraction.entity.Competition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author boukhris
 */
public class UserCardCompetitionController implements Initializable {

    @FXML
    private Label lnom;
    @FXML
    private Label ltheme;
    @FXML
    private Label ldatedebut;
    @FXML
    private Label ldatefin;
    @FXML
    private Label ltype;
    @FXML
    private Button btnconsulter;

    private int refCompetition;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Competition competition) {
        lnom.setText(competition.getNom());
        ltheme.setText(competition.getTheme());
        ldatedebut.setText(competition.getDate_debut().toString());
        ldatefin.setText(competition.getDate_fin().toString());
        ltype.setText(competition.getType());
        refCompetition = competition.getRef_competition();

    }

    @FXML
    private void consulter(ActionEvent event) throws IOException {
//         Parent root = FXMLLoader.load(getClass().getResource("UserConsulterOeuvreCompetition.fxml"));
//        
//        Scene scene = new Scene(root);
//        Stage stage = new Stage();
////        stage.setTitle("Gérer compétitions");
//        stage.setScene(scene);
//       
//        UserConsulterOeuvreCompetitionController uccc = new UserConsulterOeuvreCompetitionController();
//        uccc.initData(1);
//        Stage oldstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        oldstage.close();
//                 stage.show();
        String source1 = event.getSource().toString();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserConsulterOeuvreCompetition.fxml"));
        fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> controllerClass) {
                if (controllerClass == UserConsulterOeuvreCompetitionController.class) {
                    UserConsulterOeuvreCompetitionController controller = new UserConsulterOeuvreCompetitionController();
                    controller.initData(refCompetition);
                    return controller;
                } else {
                    try {
                        return controllerClass.newInstance();
                    } catch (Exception exc) {
                        throw new RuntimeException(exc); // just bail
                    }
                }
            }
        });
        Parent root1 = fxmlLoader.load();

        Scene scene = new Scene(root1);
        Stage stage = new Stage();
//        stage.setTitle("Gérer compétitions");
        stage.setScene(scene);

        UserConsulterOeuvreCompetitionController uccc = new UserConsulterOeuvreCompetitionController();
        uccc.initData(1);
        Stage oldstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldstage.close();
        stage.show();
    }

}
