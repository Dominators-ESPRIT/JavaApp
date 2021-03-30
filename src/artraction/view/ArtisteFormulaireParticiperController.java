/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.view;

import artraction.entity.Oeuvre_competition;
import artraction.service.OeuvreCompetitionService;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLDataException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author boukhris
 */
public class ArtisteFormulaireParticiperController implements Initializable {

    OeuvreCompetitionService ocs = new OeuvreCompetitionService();

    @FXML
    private Button btnannulerao;
    private String urlEvent;
    private static File photoEvent;
    @FXML
    private ImageView image_p;
    @FXML
    private TextField tfdescription;
    private JFXButton fichier;
    final FileChooser fileChooser = new FileChooser();
    private Desktop desktop = Desktop.getDesktop();
    private String file_image;
    private Path pathfrom;
    private Path pathto;
    private File currentFile;

    private int refCompetition = 1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void annulerajout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ArtisteConsulterCompetition.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
//        stage.setTitle("Gérer compétitions");
        stage.setScene(scene);

        Stage oldstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldstage.close();
        stage.show();
    }

    @FXML
    private void selectionImage(ActionEvent event) throws MalformedURLException {

//
//           
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        //Show open file dialog
        this.currentFile = fileChooser.showOpenDialog(null);
        String s = currentFile.getAbsolutePath();
    }

    @FXML
    private void enregisterParticipation(ActionEvent event) throws IOException {

         OeuvreCompetitionService ocs = new OeuvreCompetitionService();
        try {
            ocs.Add_Oeuvre_Competition(currentFile, refCompetition, 3,tfdescription.getText() );
            JOptionPane.showMessageDialog(null, "Oeuvre ajoutée avec succés");
             Parent root = FXMLLoader.load(getClass().getResource("ArtisteConsulterCompetition.fxml"));

        
        } catch (SQLDataException ex) {
            Logger.getLogger(ArtisteFormulaireParticiperController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {

            //  Logger.getLogger(
            //    af.class.getName()).log(
            //  Level.SEVERE, null, ex
            //  );
        }
    }

    public void initDate(int refCompetition) {
        this.refCompetition = refCompetition;
    }

    @FXML
    private void retour(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("ArtisteConsulterCompetition.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
//        stage.setTitle("Gérer compétitions");
        stage.setScene(scene);

        Stage oldstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldstage.close();
        stage.show();
    }
}
