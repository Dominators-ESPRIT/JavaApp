/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.controller;

import artraction.entity.Competition;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author boukhris
 */
public class ConsulterListCompetitionController implements Initializable {

    @FXML
    private TableView<Competition> tvcompetition;
    @FXML
    private TableColumn<Competition, Integer> colrefcompetition;
    @FXML
    private TableColumn<Competition, String> colnom;
    @FXML
    private TableColumn<Competition, String> coltheme;
    @FXML
    private TableColumn<Competition, DatePicker> coldatedebut;
    @FXML
    private TableColumn<Competition, DatePicker> coldatefin;
    @FXML
    private TableColumn<Competition, String> coltype;
    @FXML
    private Button btnparticiper;
    @FXML
    private Button btnretour;
    @FXML
    private AnchorPane listcompetitionpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
            showCompetition();
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterCompetitionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }      
    public Connection getConnection(){
        Connection conn;
        try{
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
            return conn;
        }catch(Exception ex){
           
            System.out.println("Error:" + ex.getMessage());
             
        return null;    
        }
        
    }
    public ObservableList<Competition> getCompetitionList() throws SQLException{
    ObservableList<Competition> competitionList =FXCollections.observableArrayList();
    Connection conn = getConnection();
    String query="SELECT * FROM competition";
        Statement st;
        ResultSet rs;
        try{
            st= conn.createStatement();
            rs = st.executeQuery(query);
            Competition competition;
            while(rs.next()){
                competition = new Competition(rs.getInt("ref_competition"), rs.getString("nom"), rs.getString("theme"), rs.getDate("date_debut"), rs.getDate("date_fin"), rs.getString("type"));
                competitionList.add(competition);
            }
            
        }
        catch(Exception ex){
            ex.printStackTrace();
    }
        return competitionList;
    }
    
    public void showCompetition() throws SQLException{
        ObservableList<Competition> list_compt = getCompetitionList();
        colrefcompetition.setCellValueFactory(new PropertyValueFactory<Competition,Integer>("ref_competition"));
        colnom.setCellValueFactory(new PropertyValueFactory<Competition,String>("nom"));
        coltheme.setCellValueFactory(new PropertyValueFactory<Competition,String>("theme"));
        coldatedebut.setCellValueFactory(new PropertyValueFactory<Competition,DatePicker>("date_debut"));
        coldatefin.setCellValueFactory(new PropertyValueFactory<Competition,DatePicker>("date_fin"));
        coltype.setCellValueFactory(new PropertyValueFactory<Competition,String>("type"));
        
        tvcompetition.setItems(list_compt);
    }
    
     private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st =conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
            
        }
    }

    @FXML
    private void onclickparticiper(MouseEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/artraction/view/ParticiperCompetition.fxml"));
        
        Stage mainStage=new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
    }

