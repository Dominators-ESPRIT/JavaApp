/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.view;

import artraction.service.ServiceReservation;
import artraction.service.ServiceFormation;
import artraction.utils.DataSource;
import static artraction.utils.DataSource.ConnectDb;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import artraction.entity.Reservation;
import artraction.entity.formation;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TablePosition;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import artraction.entity.Maptet;
import org.controlsfx.control.Notifications;
import java.awt.Window;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ReserverController implements Initializable {

    @FXML
    private Button btnr;
    @FXML
    private ComboBox<String> com1;
    @FXML
    private ComboBox<String> com2;
    ObservableList<formation> optionS ;
    ObservableList<Reservation> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Reservation, Integer> col_id;
    @FXML
    private TableColumn<Reservation, Integer> col_h;
    private ObservableList<Reservation> listM;
    private ObservableList<formation> listR = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    ObservableList<Reservation> list1 = FXCollections.observableArrayList();
    ObservableList<formation> listS = FXCollections.observableArrayList();
    @FXML
    private TableView<Reservation> table_reservation;
    @FXML
    private Button btns;
    @FXML
    private TextField ftid;
    @FXML
    private TableColumn<?, ?> col_r;
    @FXML
    private TableColumn<formation, Integer> tid;
    @FXML
    private TableColumn<formation, String> tnom;
    @FXML
    private TableColumn<formation, String> tadresse;
    @FXML
    private TableColumn<formation, Integer> tprixph;
    @FXML
    private TableColumn<formation, String> tcontact;
    @FXML
    private TableColumn<formation, String> timage;
    @FXML
    private TableView<formation> table_stade;
    @FXML
    private TextField mapadress;
    @FXML
    private TextField ftR;
    @FXML
    private TableColumn<Reservation,String> col_nom;
    ArrayList name = new ArrayList();
       private Stage primaryStage;
    @FXML
    private DatePicker dater;
    
    ObservableList<Reservation> list66 = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Reservation, String> col_date;
    @FXML
    private Label rand;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            // TODO
            
            
            com1.setPromptText("Les Formations");
            com2.setPromptText("Chosir l'heure");
            ResultSet rs1 = null;
            
            try {
                afficherreservation();
            } catch (SQLException ex) {
                Logger.getLogger(ReserverController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Connection cnx = DataSource.getInstance().getCnx();
                rs1 = cnx.createStatement().executeQuery("SELECT nom FROM formation");
            } catch (SQLException ex) {
                Logger.getLogger(ReserverController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                while (rs1.next()) {  // loop
                    
                    // Now add the comboBox addAll statement
                    com1.getItems().addAll(rs1.getString("nom"));
                    
                }} catch (SQLException ex) {
                    Logger.getLogger(ReserverController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            com2.getItems().addAll("01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
            
            travail();
            Connection conn = ConnectDb();
        
        
            PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("select * from reservation");
        } catch (SQLException ex) {
            Logger.getLogger(ReserverController.class.getName()).log(Level.SEVERE, null, ex);
        }
            ResultSet rs22 = null;
        try {
            rs22 = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ReserverController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        try {
            while (rs22.next()){              
                list66.add(new Reservation(rs22.getInt(1),rs22.getInt(2),rs22.getString(3),rs22.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReserverController.class.getName()).log(Level.SEVERE, null, ex);
        }
                try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM formation");
            while(rs.next()){
            listS.add(new formation(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6)));
            
            table_stade.setItems(listS);
            table_stade.refresh();
            
        }
            } catch (SQLException ex) {
            Logger.getLogger(AjoutFormationController.class.getName()).log(Level.SEVERE, null, ex);
        }
                afficherstade();
            
            
           Connection cnx = DataSource.getInstance().getCnx();
        try {
            ResultSet rs8 = cnx.createStatement().executeQuery("SELECT nom FROM formation");
            while(rs8.next()){
                name.add(rs8.getString("nom"));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReserverController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TextFields.bindAutoCompletion(ftR,name);
            
        
            
        
            
    }    
    public void travail(){
       
        try {
            afficherreservation();
        } catch (SQLException ex) {
            Logger.getLogger(ReserverController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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

    @FXML
    private void Reserver(ActionEvent event) throws SQLException {
        ResultSet rs6 = null;
      
        Connection cnx = DataSource.getInstance().getCnx();
        int j =Integer.parseInt(com2.getValue());
        
        String n = com1.getValue();
        String date = dater.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        rs6 = cnx.createStatement().executeQuery("SELECT id_formation FROM formation where nom='"+n+"'");  
     
        rs6.next();
   int id1 = rs6.getInt("id_formation");
     Connection cnx1 = DataSource.getInstance().getCnx();
        
        ResultSet rs16 = cnx1.createStatement().executeQuery("select count(*) AS count_res from reservation where heure ='"+j+"' and date ='"+date+"'and nom ='"+n+"'");  
     
        rs16.next();
   int id12 = rs16.getInt("count_res");
   
        
       if(id12<8){

        
        ServiceReservation sr = new ServiceReservation();
        
        sr.Ajouter(new Reservation(id1,j,n,date));
        
        UpdateTable();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("réeservation ajoutée");
                
                alert.showAndWait();
        
// SMS ss = new SMS();
 //ss.SendSMS("bechir12", "123456789bB", "felicitation", "21626472874", "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
        
    }else{Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Déja Reservé");
                
                alert.showAndWait();}
    
    }

    @FXML
    private void SelectStade(ActionEvent event) throws SQLException {

    }

    @FXML
    private void SelectHeure(ActionEvent event) {
    }

    @FXML
    private void SupprimerReservation(ActionEvent event) throws SQLException {
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
        public void UpdateTable() throws SQLException{
        col_r.setCellValueFactory(new PropertyValueFactory<>("id_reservation"));    
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_formation"));
        col_h.setCellValueFactory(new PropertyValueFactory<>("heure"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

         listM = DataSource.getDataReservation();
        
        table_reservation.setItems(listM);
        }

    public Reservation gettempReservation(TableColumn.CellEditEvent edittedCell) {
        Reservation test = table_reservation.getSelectionModel().getSelectedItem();
        return test;
    }

    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
            int index = table_reservation.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    
    com1.setValue(col_nom.getCellData(index).toString());
    com2.setValue(col_h.getCellData(index).toString());
    String n =col_date.getCellData(index);
    LocalDate localDate = LocalDate.parse(n);

    dater.setValue(localDate);
    ftid.setText(col_r.getCellData(index).toString());
 
    
    }

    @FXML
    private void Edit() {
        try {
            UpdateTable();
            Connection cnx = DataSource.getInstance().getCnx();
            cnx = DataSource.ConnectDb();
            String value1 =com1.getValue();
        int value2 =Integer.parseInt(com2.getValue());
            int value3 = Integer.parseInt(ftid.getText());
            String value4 = dater.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String sql ="update reservation set nom= '"+value1+"',heure= '"+value2+"',date= '"+value4+"' where id_reservation='"+value3+"' ";
            PreparedStatement pst = cnx.prepareStatement(sql);
            pst= cnx.prepareStatement(sql);
            pst.execute();
            UpdateTable();
            JOptionPane.showMessageDialog(null, "Updated");
            UpdateTable();
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    private void afficherstade(){
       
            tid.setCellValueFactory(new PropertyValueFactory<>("id_formation"));
            tnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            tadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            tprixph.setCellValueFactory(new PropertyValueFactory<>("prixph"));
            tcontact.setCellValueFactory(new PropertyValueFactory<>("contact"));
            timage.setCellValueFactory(new PropertyValueFactory<>("image"));
            table_stade.setItems(listS);
            
            
            
    }
  

    @FXML
    private void getadressclicked(MouseEvent event) {
         int index = table_stade.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
           mapadress.setText(tadresse.getCellData(index).toString());
    }

    @FXML
    private void SeeLocation(ActionEvent event) {
           
int index = table_stade.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
           mapadress.setText(tadresse.getCellData(index).toString());
           String S = mapadress.getText();
           String[] splitString = S.split(",");
                
                Double d = Double.parseDouble(splitString[0]);
                Double d1 = Double.parseDouble(splitString[1]);
                Maptet test = new Maptet(d, d1);
    }

   

    @FXML
    private void BPDF(ActionEvent event) {
        System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(this.primaryStage); 
            
    Node root = this.table_reservation;
           job.printPage(root);
           job.endJob();
    }
  

   
}

    @FXML
    private void SearchFormation(ActionEvent event) throws SQLException {
         Connection conn = ConnectDb();
      
        String value9 = ftR.getText();
      
            PreparedStatement ps = conn.prepareStatement("select * from formation where nom Like'"+value9+"'");
            
            ResultSet rs5 = ps.executeQuery();
            
            while (rs5.next()){   
                
                listR.add(new formation( rs5.getInt(1),rs5.getString(2), rs5.getString(3), rs5.getInt(4), rs5.getString(5), rs5.getString(6)));               
            }
        tid.setCellValueFactory(new PropertyValueFactory<formation,Integer>("id_formation"));
        tnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        tprixph.setCellValueFactory(new PropertyValueFactory<>("prixph"));
        tcontact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        tcontact.setCellValueFactory(new PropertyValueFactory<>("image"));

         table_stade.setItems(listR);
    }
}
    

