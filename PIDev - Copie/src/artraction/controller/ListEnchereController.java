/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.controller;

import artraction.entity.Enchere;
import artraction.entity.ListData;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hanine
 */
public class ListEnchereController implements Initializable {

    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem exit;
    @FXML
    private TableColumn<?, ?> RefTab;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private Button creer;
    @FXML
    private Text Titre;
    private TableColumn<?, ?> DateTab;
    @FXML
    private Label Nom;

    
       private ListData listdata = new ListData();
    @FXML
    private TableView<Enchere> EncheresTable;
    @FXML
    private TableColumn<?, ?> DateTAb;
    private Label date;
    @FXML
    private Label Date;
    @FXML
    private Label PrixInit;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
   
   
 /* EncheresTable.setItems(listdata.getEncheres());
        Nom.setCellValueFactory(cell -> cell.
                getValue().getNomProperty());
        DateTab.setCellValueFactory(cell -> cell.
                getValue().getDate_enchereProperty());
        
        EncheresTable.setOnMouseClicked(event->{
        Nom.setText(String.valueOf(listdata.getEncheres()
                .get(EncheresTable.getSelectionModel().getSelectedIndex())
                .getNomOeuvre()));
        date.setText(listdata.getEncheres()
                .get(EncheresTable.getSelectionModel().getSelectedIndex())
                .getDate_enchere());
        PrixInit.setText(listdata.getEncheres()
                .get(EncheresTable.getSelectionModel().getSelectedIndex())
                .getPrixinit_enchere());
        
       
        }); 
        
     */  
      creer.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/artraction/view/Creer_Enchere.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            
        }    catch (IOException ex) { 
                 Logger.getLogger(Creer_EnchereController.class.getName()).log(Level.SEVERE, null, ex);
             }
        } 
    );
   
        
        
    }
    
    }    
    

