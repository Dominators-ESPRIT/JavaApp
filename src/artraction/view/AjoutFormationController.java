/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.view;
import artraction.view.ReserverController;
import artraction.service.ServiceFormation;
import artraction.utils.DataSource;
import static artraction.utils.DataSource.ConnectDb;
import java.awt.HeadlessException;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import artraction.entity.formation;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjoutFormationController implements Initializable {

    @FXML
    private TextField ftNom;
    @FXML
    private TextField fid;
    @FXML
    private TextField ftAdresse;
    @FXML
    private TextField ftPrixph;
    @FXML
    private TextField ftContact;
    @FXML
    private TextField ftImage;
    @FXML
    private Button btn;
    @FXML
    private TableColumn<formation, Integer> col_id;
    @FXML
    private TableColumn<formation, String> col_nom;
    @FXML
    private TableColumn<formation, String> col_adresse;
    @FXML
    private TableColumn<formation, Integer> col_prixph;
    @FXML
    private TableColumn<formation, String> col_contact;
    @FXML
    private TableColumn<formation, String> col_image;
    @FXML
    private Button btnd;
    @FXML
    private Button btnu;
    @FXML
    private TableView<formation> table_stade;
    private Connection cnx = null ;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<formation> listM;
    private ObservableList<formation> listS = FXCollections.observableArrayList();
    private ObservableList<formation> dataList;
     ArrayList name = new ArrayList();
      private Stage primaryStage1;
    

    ObservableList<formation> list = FXCollections.observableArrayList();
    ResultSet rs1 = null;
    @FXML
    private TextField ftR;
    private AutoCompletionBinding<String> AutoCompletionBinding;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
         try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM formation");
            while(rs.next()){
            list.add(new formation(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6)));
            
            table_stade.setItems(list);
            table_stade.refresh();
            
        }
            } catch (SQLException ex) {
            Logger.getLogger(AjoutFormationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            AfficherStade();
        } catch (SQLException ex) {
            Logger.getLogger(AjoutFormationController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public void AfficherStade() throws SQLException{
          col_id.setCellValueFactory(new PropertyValueFactory<>("id_stade"));
          col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
          col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
          col_prixph.setCellValueFactory(new PropertyValueFactory<>("prixph"));
          col_contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
          col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        UpdateTable();
        table_stade.setItems(list);
        
    }
    
    
    

    @FXML
    private void AjouterStade(ActionEvent event) throws SQLException {
        
       if (controleDeSaisi()) {
          
        ServiceFormation sp = new ServiceFormation();
        int i =Integer.parseInt(ftPrixph.getText());
        
        sp.Ajouter(new formation(ftNom.getText(),ftAdresse.getText(),i,ftContact.getText(),ftImage.getText()));
        
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_formation"));
         col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
         col_prixph.setCellValueFactory(new PropertyValueFactory<>("prixph"));
         col_contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
         col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Formation Ajoutée");
                
                alert.showAndWait();
        UpdateTable();
        table_stade.refresh();}
       
    }

    @FXML
    private void SupprimerStade(ActionEvent event) throws SQLException {
        TableColumn.CellEditEvent edittedcell = null;
        formation x = gettempStade(edittedcell);
        UpdateTable();

        if (x != null) {

            int i = x.getId_formation();
            ServiceFormation cat = new ServiceFormation();

            int s = cat.deleteFormation(i);
            UpdateTable();
            
            
            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Formation deleted");
                
                alert.showAndWait();
                table_stade.refresh();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }


    }
 public formation gettempStade(TableColumn.CellEditEvent edittedCell) {
        formation test = table_stade.getSelectionModel().getSelectedItem();
        return test;
    }
        

    


    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
            int index = table_stade.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    fid.setText(col_id.getCellData(index).toString());
    ftNom.setText(col_nom.getCellData(index));
    ftAdresse.setText(col_adresse.getCellData(index));
    ftPrixph.setText(col_prixph.getCellData(index).toString());
    ftContact.setText(col_contact.getCellData(index));
    ftImage.setText(col_image.getCellData(index));
    
    
    }
    @FXML
    public void Edit (){   
        try {
            UpdateTable();
            cnx = DataSource.ConnectDb();
            String value1 = ftNom.getText();
            int value7 = Integer.parseInt(fid.getText());
            
            String value2 = ftAdresse.getText();
            String value3 = ftPrixph.getText();
            String value4 = ftContact.getText();
            String value5 = ftImage.getText();
            String sql = "update formation set nom= '"+value1+"',adresse= '"+value2+"',prixph= '"+
                    value3+"',contact= '"+value4+"',image= '"+value5+"' where id_formation='"+value7+"' ";
            pst= cnx.prepareStatement(sql);
            pst.execute();
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("UPDATED");
                
                alert.showAndWait();
            UpdateTable();
            
            
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }

   
     public void UpdateTable() throws SQLException{
            
        col_id.setCellValueFactory(new PropertyValueFactory<formation,Integer>("id_formation"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        col_prixph.setCellValueFactory(new PropertyValueFactory<>("prixph"));
        col_contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
         listM = DataSource.getDataformation();
        
        table_stade.setItems(listM);
        }
          private boolean controleDeSaisi() {  

        if (ftNom.getText().isEmpty() || ftAdresse.getText().isEmpty() || ftPrixph.getText().isEmpty()
                || ftContact.getText().isEmpty()  || ftImage.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

            if (!Pattern.matches("[A-z]*", ftNom.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez le Nom ! ");
                ftNom.requestFocus();
                ftNom.selectEnd();
                return false;
            }

         if (!Pattern.matches("[0-9]*", ftPrixph.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le prix ! ");
                ftPrixph.requestFocus();
                ftPrixph.selectEnd();
                return false;
            }if (!Pattern.matches("[0-9]*", ftContact.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le Contact ! ");
                ftContact.requestFocus();
                ftContact.selectEnd();
                return false;
            }if (!Pattern.matches("[A-z]*", ftImage.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le Proprietaire ! ");
                ftImage.requestFocus();
                ftImage.selectEnd();
                return false;
            }
           
        }
        return true;
    }
       public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }

 
    @FXML
    private void savetoword(ActionEvent event) throws FileNotFoundException, IOException {
         XWPFDocument document = new XWPFDocument();
       FileOutputStream out = new FileOutputStream(new File("demo.docx"));
       XWPFParagraph paragraph = document.createParagraph();
       XWPFRun run = paragraph.createRun();
        String value1 = ftNom.getText();
           
            
            String value2 = ftAdresse.getText();
            String value3 = ftPrixph.getText();
            String value4 = ftContact.getText();
            String value5 = ftImage.getText();
            String s1 = "";
            s1= s1.concat("     NOM formation:"     ).concat(value1).concat("     ADRESS:     ").concat(value2).concat("      PRICE:    ").
                    concat(value3).concat("      TUTEUR:         ").concat(value4).concat("      OWNER:        ").concat(value5);
       run.setText(s1);
       document.write(out);
       out.close();
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("DOCUMENT ENREGISTRE");
                
                alert.showAndWait();
    }

    @FXML
    private void BPDF(ActionEvent event) {
               PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(this.primaryStage1); 
            
    Node root = this.table_stade;
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
                
                listS.add(new formation( rs5.getInt(1),rs5.getString(2), rs5.getString(3), rs5.getInt(4), rs5.getString(5), rs5.getString(6)));               
            }
        col_id.setCellValueFactory(new PropertyValueFactory<formation,Integer>("id_formation"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        col_prixph.setCellValueFactory(new PropertyValueFactory<>("prixph"));
        col_contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        col_image.setCellValueFactory(new PropertyValueFactory<>("image"));

         table_stade.setItems(listS);
    }
    
   
   

}      
            
    
