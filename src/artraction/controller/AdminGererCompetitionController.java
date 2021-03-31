/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.controller;

import artraction.entity.Competition;
import artraction.service.CompetitionService;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author boukhris
 */
public class AdminGererCompetitionController implements Initializable {

  
    @FXML
    private TableView<Competition> tvcomp;
    @FXML
    private TableColumn<Competition , String> colnom;
    @FXML
    private TableColumn<Competition, String> coltheme;
    @FXML
    private TableColumn<Competition, Date> coldatedebut;
    @FXML
    private TableColumn<Competition, Date> coldatefin;
    @FXML
    private TableColumn<Competition, String> coltype;
    
    ObservableList<Competition> listcomp;
    ObservableList<Competition> dataList;

    int index =-1;
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst =null;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tftheme;
    @FXML
    private DatePicker tfdatedebut;
    @FXML
    private DatePicker tfdatefin;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tffilter;
    
    private ObservableList<Competition> CompData = FXCollections.observableArrayList();
     
     CompetitionService compservice =  new CompetitionService();
    @FXML
    private Button Addcompetition;
    @FXML
    private Button updatecompetition;
    @FXML
    private Button delatecompetition;
    
     
    int c;
    int file;
    File pDir;
    File pfile;
    @FXML
    private Button imp;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      afficher_competition();
    }

    public void afficher_competition()
    {
         try {
            List<Competition> listCompetition= new ArrayList<Competition>();
            listCompetition = compservice.getAllCompetition();
        CompData.clear();
        CompData.addAll(listCompetition);
       tvcomp.setItems(CompData);
        
        
        colnom.setCellValueFactory
        (
            new PropertyValueFactory<>("nom")
        );
        
        coltheme.setCellValueFactory
        (
            new PropertyValueFactory<>("theme")
        );
        
         coldatedebut.setCellValueFactory
        (
            new PropertyValueFactory<>("date_debut")
        );
         
          coldatefin.setCellValueFactory
        (
            new PropertyValueFactory<>("date_fin")
        );
          
         coltype.setCellValueFactory
        (
            new PropertyValueFactory<>("type")
        );  
         
         
        
        
        
    
        } catch (SQLDataException ex) {
            Logger.getLogger(AdminGererCompetitionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Add_Competition(ActionEvent event) throws SQLDataException {
         LocalDate d = LocalDate.now();
       
   if(tfdatedebut.getValue().compareTo(d)>=0 && tfdatefin.getValue().compareTo(tfdatedebut.getValue())>=0){  
       System.out.println(tfdatedebut.getValue());
        if (tfnom.getText().equals("") || tftheme.getText().equals("") || tftype.getText() == null) {
            JOptionPane.showMessageDialog(null, "Verifier les champ(s) vide(s)");
        } else {

            String n = tfnom.getText();
            String t = tftheme.getText();
            String ty =tftype.getText();

            CompetitionService cs = new CompetitionService();
            if (!cs.findByName(tfnom.getText())) {
            
              Competition c = new Competition(n, t, tfdatedebut.getValue().toString(), tfdatefin.getValue().toString(), ty);

                cs.Add_Competition(c);
                afficher_competition();
            } else {
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Nom existe");
                JOptionPane.showMessageDialog(null, "Nom existe");
            }
        }
        } else {
        JOptionPane.showMessageDialog(null, "Date incorrect");
      
   }
    }
 

       

    
    

    @FXML
    private void Edit_Competition(ActionEvent event) throws SQLException {
          Competition comptSelec = (Competition) tvcomp.getSelectionModel().getSelectedItem();
            CompetitionService es = new CompetitionService();
            String value1 = tfnom.getText();
            String value2 = tftheme.getText();
            String value3 = tfdatedebut.getValue().toString();
            String value4 = tfdatefin.getValue().toString();
            String value5 = tftype.getText();
            comptSelec.setNom(value1);
            comptSelec.setTheme(value2);
            comptSelec.setType(value5);
            System.out.println("updaaaated"+comptSelec);
            es.Edit_Competition(comptSelec);
            
            afficher_competition();
    }

    @FXML
    private void Delate_Competition(ActionEvent event) throws SQLException {
         Competition comptSelec = (Competition) tvcomp.getSelectionModel().getSelectedItem();

        CompetitionService es = new CompetitionService();
        List<Competition> u = new ArrayList<Competition>();

        compservice.Delate_Competition(comptSelec.getRef_competition());
        afficher_competition();
    }
    
     public void resetTableData() throws SQLDataException
    {
        List<Competition> listcompetition = new ArrayList<>();
        listcompetition = compservice.getAllCompetition();
        ObservableList<Competition> data = FXCollections.observableArrayList(listcompetition);
        tvcomp.setItems(data);
    }

 
    
     public void UpdateTable(){
        colnom.setCellValueFactory(new  PropertyValueFactory<Competition,String>("nom"));
        coltheme.setCellValueFactory(new PropertyValueFactory<Competition, String>("theme"));
        coldatedebut.setCellValueFactory(new PropertyValueFactory<Competition, Date>("date_debut"));
        coldatefin.setCellValueFactory(new PropertyValueFactory<Competition, Date>("date_fin"));
        coltype.setCellValueFactory(new PropertyValueFactory<Competition, String>("type"));

      
        tvcomp.setItems(listcomp);
    }
   

    @FXML
    private void getSelected(MouseEvent event) {
        
          index= tvcomp.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        tfnom.setText(colnom.getCellData(index).toString());
        tftheme.setText(coltheme.getCellData(index).toString());
          tftype.setText(coltype.getCellData(index).toString());
        
         java.sql.Date LocalDT = (java.sql.Date) coldatedebut.getCellData(index);
        System.out.println(LocalDT.toString());
        tfdatedebut.setValue(LocalDT.toLocalDate());
        java.sql.Date LocalDTt = (java.sql.Date) coldatefin.getCellData(index);
        System.out.println(LocalDTt.toString());
        tfdatefin.setValue(LocalDTt.toLocalDate());

      
    }

    @FXML
    private void search(MouseEvent event) throws SQLDataException {
          colnom.setCellValueFactory(new PropertyValueFactory<Competition, String>("nom"));
        coltheme.setCellValueFactory(new PropertyValueFactory<Competition, String>("theme"));
        
        coltype.setCellValueFactory(new PropertyValueFactory<Competition, String>("type"));
        
        dataList = (ObservableList<Competition>) compservice.getAllCompetition();
        tvcomp.setItems(dataList);
         FilteredList<Competition> filteredData = new FilteredList<>(dataList, b -> true);
         tffilter.textProperty().addListener((observable, oldValue,newValue ) -> {
             filteredData.setPredicate(person -> {
                   if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (person.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } else if (person.getTheme().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }else if (person.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
    
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<Competition> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(tvcomp.comparatorProperty());  
  tvcomp.setItems(sortedData);      
    }

    
    public void savefile(ObservableList<Competition> CompData,File file){
        try {
            BufferedWriter outwriter = new BufferedWriter(new FileWriter(file));
            for (Competition competition  : CompData){
                outwriter.write(competition.toString());
                outwriter.newLine();
            }
            System.out.println(CompData.toString());
            outwriter.close();
        } catch (IOException e) {
            Alert ioAlert= new Alert(Alert.AlertType.ERROR ,"oops" , ButtonType.OK);
            ioAlert.setContentText("Error");
            ioAlert.showAndWait();
            if(ioAlert.getResult()== ButtonType.OK){
                ioAlert.close();
            }
        }
        
    }

    @FXML
    private void save(ActionEvent event) {
         Stage secondarystage = new Stage();
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("save liste compétitions");
        filechooser.setInitialDirectory(new File(System.getProperty("user.home")));
        if(CompData.isEmpty()){
            secondarystage.initOwner(this.imp.getScene().getWindow());
            Alert emptytablealert = new Alert(Alert.AlertType.ERROR, "Empty table" , ButtonType.OK);
            emptytablealert.setContentText("you have nothing to save");
            emptytablealert.initModality(Modality.APPLICATION_MODAL);
            emptytablealert.initOwner(this.imp.getScene().getWindow());
            emptytablealert.showAndWait();
            if(emptytablealert.getResult() == ButtonType.OK){
                emptytablealert.close();
            }
        }else{
            File file = filechooser.showSaveDialog(secondarystage);
            if(file != null){
                savefile(tvcomp.getItems(), file);
            }
        }
    }
    }
    

