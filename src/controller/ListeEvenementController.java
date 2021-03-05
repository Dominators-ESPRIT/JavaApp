/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Evenement;
import entities.Participer;
import entities.User;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.CommentService;
import service.EvenementService;
import java.lang.String;
import javafx.collections.transformation.SortedList;


/**
 * FXML Controller class
 *
 * @author hazar
 */
public class ListeEvenementController implements Initializable {

    @FXML
    private ScrollPane ScrlPane;
    @FXML
    private AnchorPane AnchPane;
    @FXML
    private TableView<Evenement> Table;
  
    @FXML
    private TableColumn<Evenement, String> titre;
    @FXML
    private TableColumn<Evenement, String> lieu;
    @FXML
    private TableColumn<Evenement, Date> datedebut;
    @FXML
    private TableColumn<Evenement, Date> datefin;
    @FXML
    private TableColumn<Evenement, Integer> nbplaces;
    private TableColumn<Evenement, Integer> nbparticipants;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private TableColumn<Evenement, Integer> ide;

     private ObservableList<Evenement> EventData = FXCollections.observableArrayList();
     
     EvenementService evtservice =  new EvenementService();
    @FXML
    private Button ajout;
    private TextField recherche;
      

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        try {
            List<Evenement> listEvenement= new ArrayList<Evenement>();
            listEvenement = evtservice.getAllEvenement();
        EventData.clear();
        EventData.addAll(listEvenement);
        Table.setItems(EventData);
        
        ide.setCellValueFactory
        (
            new PropertyValueFactory<>("id")
        );
        
        titre.setCellValueFactory
        (
            new PropertyValueFactory<>("titre")
        );
        
         datedebut.setCellValueFactory
        (
            new PropertyValueFactory<>("DateDebut")
        );
         
          datefin.setCellValueFactory
        (
            new PropertyValueFactory<>("DateFin")
        );
          
         nbplaces.setCellValueFactory
        (
            new PropertyValueFactory<>("nbreplaces")
        );  
         
         
        
        
        
        lieu.setCellValueFactory
        (
            new PropertyValueFactory<>("lieu")
        );
        } catch (SQLDataException ex) {
            Logger.getLogger(ListeEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

  

  

  

   


    public void setTitre(TableColumn<Evenement, String> titre) {
        this.titre.setText(titre.getText());
    }

    public void setLieu(TableColumn<Evenement, String> lieu) {
        this.lieu.setText(lieu.getText());
    }

    public void setDatedebut(TableColumn<Evenement, Date> datedebut) {
        this.datedebut.setText(datedebut.getText());
    }

    public void setDatefin(TableColumn<Evenement, Date> datefin) {
        this.datefin.setText(datefin.getText());
    }

    public void setNbplaces(TableColumn<Evenement, Integer> nbplaces) {
        this.nbplaces.setText(nbplaces.getText());
    }


    @FXML
    private void modifieEvent(ActionEvent event) throws SQLDataException {
        
                  // ModifiEvenementController.idmof =  Table.getSelectionModel().getSelectedItem().getId();
                   //System.out.println("cxxxxxxxxxxxxxxxxxxxxxxxxx"+ModifiEvenementController.idmof);

  Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/gui/ModifiEvenement.fxml"));
                            Stage myWindow = (Stage)modifier.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(ListeEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                        } 
        
    }

   
    @FXML
    private void supprimerEvent(ActionEvent event) throws SQLDataException, SQLException, Exception {
        
           Evenement EventSelec = (Evenement) Table.getSelectionModel().getSelectedItem();
           
           EvenementService es = new EvenementService();
           List<User> u = new ArrayList<User>();
     List<Participer> p=  es.findUSerByIdParticip(Table.getSelectionModel().getSelectedItem().getId());
           
           for (Participer pr : p){
               CommentService cs = new CommentService();
            User user= cs.findUserById(pr.getIdUuser());
           
           u.add(user);
           
           }
          

          
        if(EventSelec == null){
            
        }else{
            if(evtservice.deleteEvenement(EventSelec.getId())){
                
               
                resetTableData();
            }else{
                
            }
        }
        
    }
      public void resetTableData() throws SQLDataException
    {
        List<Evenement> listEvenements = new ArrayList<>();
        listEvenements = evtservice.getAllEvenement();
        ObservableList<Evenement> data = FXCollections.observableArrayList(listEvenements);
        Table.setItems(data);
    }

    @FXML
    private void Ajout(ActionEvent event) {
        
         Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/gui/AjoutEvenement.fxml"));
                            Stage myWindow = (Stage)modifier.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(ListeEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                        }
        
    }

 
 

}
