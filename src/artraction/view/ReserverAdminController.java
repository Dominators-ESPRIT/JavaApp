/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.view;

import artraction.service.ServiceReservation;
import artraction.utils.DataSource;
import static artraction.utils.DataSource.ConnectDb;
import java.awt.Window;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import artraction.entity.Reservation;
import artraction.entity.formation;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class ReserverAdminController implements Initializable {

    @FXML
    private TableView<Reservation> table_reservation;
    @FXML
    private TableColumn<?, ?> col_r;
    private ObservableList<Reservation> listR = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> col_h;
    @FXML
    private TableColumn<?, ?> col_id;
    @FXML
    private TableColumn<?, ?> col_nom;
    @FXML
    private TableColumn<?, ?> col_date;
        private ObservableList<Reservation> listM;
            ArrayList name = new ArrayList();
    @FXML
    private TextField ftR;
    private Stage primaryStage;
    ObservableList<Reservation> list66 = FXCollections.observableArrayList();
    Connection cnx = DataSource.getInstance().getCnx();
    private ObservableList<formation> listS = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection conn = ConnectDb();
        // TODO
        PreparedStatement ps = null;
      
        try {
            ps = conn.prepareStatement("select * from reservation");
        } catch (SQLException ex) {
            Logger.getLogger(ReserverAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            ResultSet rs22 = null;
        
       
        try {
            rs22 = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ReserverAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
         Connection cnx = DataSource.getInstance().getCnx();
        try {
            ResultSet rs8 = cnx.createStatement().executeQuery("SELECT nom FROM reservation");
            while(rs8.next()){
                name.add(rs8.getString("nom"));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReserverController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TextFields.bindAutoCompletion(ftR,name);
       
            
        
        try {              
            while (rs22.next()){
                list66.add(new Reservation(rs22.getInt(1),rs22.getInt(2),rs22.getString(3),rs22.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReserverAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            afficherreservation();
        } catch (SQLException ex) {
            Logger.getLogger(ReserverAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void getSelected(MouseEvent event) {
    }

    @FXML
    private void imprimer(ActionEvent event) {
        System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(this.primaryStage); 
            
    Node root = this.table_reservation;
           job.printPage(root);
           job.endJob();
    }}

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        TableColumn.CellEditEvent edittedcell = null;
        Reservation x = gettempReservation(edittedcell);

        if (x != null) {

            int i = x.getId_reservation();
            ServiceReservation cat = new ServiceReservation();

            int s = cat.deleteReservation(i);
            

            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("reservation deleted");
                UpdateTable();
                alert.showAndWait();
                table_reservation.refresh();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }
    
    }

    @FXML
    private void search(ActionEvent event) throws SQLException {
        Connection conn = ConnectDb();
      
        String value9 = ftR.getText();
      
            PreparedStatement ps = conn.prepareStatement("select * from formation where nom Like'"+value9+"'");
            
            ResultSet rs5 = ps.executeQuery();
            
            while (rs5.next()){   
                
               // listR.add(new Reservation( rs5.getInt(1), rs5.getString(2), rs5.getString(3), rs5.getString(4)));               
            }
            col_r.setCellValueFactory(new PropertyValueFactory<>("id_reservation"));
          col_h.setCellValueFactory(new PropertyValueFactory<>("heure"));
          col_id.setCellValueFactory(new PropertyValueFactory<>("id_formation"));
          col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
          col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
          table_reservation.setItems(listR); 
          UpdateTable();
    }
      public Reservation gettempReservation(TableColumn.CellEditEvent edittedCell) {
        Reservation test = table_reservation.getSelectionModel().getSelectedItem();
        return test;
    }
      public void UpdateTable() throws SQLException{
        col_r.setCellValueFactory(new PropertyValueFactory<>("id_reservation"));    
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_formation"));
        col_h.setCellValueFactory(new PropertyValueFactory<>("heure"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

         listM = DataSource.getDataReservation();
        
        table_reservation.setItems(listM);
        }
       public void afficherreservation() throws SQLException{
         Connection conn = ConnectDb();
        
        
            PreparedStatement ps = conn.prepareStatement("select * from reservation");
            ResultSet rs22 = ps.executeQuery();
            
            while (rs22.next()){   
                list66.add(new Reservation(rs22.getInt(1),rs22.getInt(2),rs22.getString(3),rs22.getString(4)));              
            }
        col_r.setCellValueFactory(new PropertyValueFactory<>("id_reservation"));
          col_h.setCellValueFactory(new PropertyValueFactory<>("heure"));
          col_id.setCellValueFactory(new PropertyValueFactory<>("id_formation"));
          col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
          col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
          table_reservation.setItems(list66); 
          UpdateTable();
          
    }
    
}
