package artraction.controller;

import artraction.controller.*;
import static artraction.controller.FXMLDocumentController.ACCOUNT_SID;
import static artraction.controller.FXMLDocumentController.AUTH_TOKEN;
import artraction.utils.ConnexionSingleton;
import artraction.entity.userEntity;
import artraction.service.User;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import static oracle.net.aso.C00.t;
import org.controlsfx.control.Notifications;

/**
 *
 * @author amir
 */
public class CompteController implements Initializable {
   
public static final String ACCOUNT_SID = "ACaa141c929bfd92eb1c6f28ffead9cc91";
    public static final String AUTH_TOKEN = "6313fc7e7688412e74fd1b3eab89357d";
   @FXML
    private AnchorPane rootpane;

    @FXML
    private TextField tfusername;

    @FXML
    private TextField tfpassword;

    @FXML
    private TextField tfemail;

    @FXML
    private TextField tftype;

    @FXML
    private TextField tfnumero;

    @FXML
    private TextField tfadresse;

    @FXML
    private TextField tfage;

    @FXML
    private TableView table_users;

    @FXML
    private TableColumn<userEntity, String> colusername;

    @FXML
    private TableColumn<userEntity, String> colemail;

    @FXML
    private TableColumn<userEntity, String> colpassword;

    @FXML
    private TableColumn<userEntity, Integer> colnumero;

    @FXML
    private TableColumn<userEntity, Integer> colage;

    @FXML
    private TableColumn<userEntity, String> coladresse;

    @FXML
    private TableColumn<userEntity, String> coltype;
    
   
    @FXML
    private TextField filterField;
    @FXML
    private Button afficheruser;
    private Label accessor;
    ObservableList<userEntity> listM;
    ObservableList<userEntity> dataList;
    
   
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    @FXML
    private AnchorPane go;




    

   
    
     
    
    

   //////// methode select users ///////
  @FXML
  void getSelected (MouseEvent event){
    index = table_users.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
   
    tfusername.setText(colusername.getCellData(index).toString());
    tfemail.setText(colemail.getCellData(index).toString());
    tfpassword.setText(colpassword.getCellData(index).toString());
    
    tfadresse.setText(coladresse.getCellData(index).toString());
    tftype.setText(coltype.getCellData(index).toString());
    tfnumero.setText(colnumero.getCellData(index) + "");
    tfage.setText(colage.getCellData(index) + "");
    
    
    }

 
        
  
    
    @FXML
    public void Delete() throws Exception{
    conn = ConnexionSingleton.getInstance().getCnx();
    String sql = "delete from user where username = ?";
        try {
            String num = Integer.toString(50067452);
           
                                sms("your profil are deleted", "+216"+num);
            pst = conn.prepareStatement(sql);
            pst.setString(1, tfusername.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();
         
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    
    }

    
    public void UpdateTable() throws Exception{
        
         colusername.setCellValueFactory(new PropertyValueFactory<userEntity, String>("username"));
        colemail.setCellValueFactory(new PropertyValueFactory<userEntity, String>("email"));
        colpassword.setCellValueFactory(new PropertyValueFactory<userEntity, String>("password"));
        colnumero.setCellValueFactory(new PropertyValueFactory<userEntity, Integer>("numero"));
          colage.setCellValueFactory(new PropertyValueFactory<userEntity, Integer>("age"));
        coladresse.setCellValueFactory(new PropertyValueFactory<userEntity, String>("adresse"));
        coltype.setCellValueFactory(new PropertyValueFactory<userEntity, String>("role"));

        listM = ConnexionSingleton.getInstance().getDatausers();
        table_users.setItems(listM);
    }
    
    

    
 
   @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
    
       try {
           UpdateTable();
           
           // Code Source in description
       } catch (Exception ex) {
           Logger.getLogger(CompteController.class.getName()).log(Level.SEVERE, null, ex);
       }
    } 

    private Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


  
    private void oncliquereturn(MouseEvent event) throws Exception{
        
    }

    @FXML
    private void afficheruser(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/artraction/view/afficheruser.fxml"));
       go.getChildren().setAll( pane);
    }

    @FXML
    private void search_user(KeyEvent event) throws Exception {
       colusername.setCellValueFactory(new PropertyValueFactory<userEntity, String>("username"));
       colemail.setCellValueFactory(new PropertyValueFactory<userEntity, String>("email"));
        colpassword.setCellValueFactory(new PropertyValueFactory<userEntity, String>("password"));
        colnumero.setCellValueFactory(new PropertyValueFactory<userEntity, Integer>("numero"));
          colage.setCellValueFactory(new PropertyValueFactory<userEntity, Integer>("age"));
        coladresse.setCellValueFactory(new PropertyValueFactory<userEntity, String>("adresse"));
        coltype.setCellValueFactory(new PropertyValueFactory<userEntity, String>("role"));

        listM = ConnexionSingleton.getInstance().getDatausers();
       
        table_users.setItems(dataList);
        FilteredList<userEntity> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredData.setPredicate((userEntity person) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
               } else if (person.getPassword().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (person.getRole().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
               } else if (String.valueOf(person.getEmail()).indexOf(lowerCaseFilter) != -1) {
                    return true;// Filter matches email
                } else if (person.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else {
                    return String.valueOf(person.getNumero()).toLowerCase().indexOf(lowerCaseFilter) != -1; // Filter matches password
                }                // Does not match.
            });
        });
        SortedList<userEntity> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_users.comparatorProperty());
        table_users.setItems(sortedData);
    }

   

    @FXML
    private void searchTextChanged(InputMethodEvent event) {
    }

    @FXML
   private void searchKeyRelaesed(KeyEvent event) {
    }

    @FXML
    private void Edit(ActionEvent event) throws Exception {
        userEntity u = null;
		u = (userEntity) table_users.getSelectionModel().getSelectedItem();
		
		if(u != null) {
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("/artraction/view/UserUpdate.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(pane);
                mainStage.setScene(scene);
                mainStage.show();
			//AnchorPane pane = FXMLLoader.load(getClass().getResource("/artraction/view/UserUpdate.fxml"));
                        
                        //  Node userField = (Node)inside.getChildren().get(11);
                        rootpane.getChildren().setAll(pane);
                        AnchorPane inside = (AnchorPane) rootpane.getChildren().get(0);
                        TextField userField = (TextField) inside.getChildren().get(10);
                        userField.setText(u.getUsername());
                        TextField emailField = (TextField) inside.getChildren().get(11); 
                        emailField.setText(u.getEmail());
                        TextField passField = (TextField) inside.getChildren().get(12); 
                        passField.setText(u.getPassword());
                        TextField numeroField = (TextField) inside.getChildren().get(13); 
                        numeroField.setText(u.getNumero()+"");
                        TextField ageField = (TextField) inside.getChildren().get(14); 
                        ageField.setText(u.getAge()+"");
                        System.out.println(u.getAdresse());
                        TextField adresseField = (TextField) inside.getChildren().get(15); 
                        adresseField.setText(u.getAdresse());
                        ComboBox cb = (ComboBox) inside.getChildren().get(16);
                        cb.setValue(u.getRole());
                        Label hiddenT = (Label) inside.getChildren().get(17);
                        hiddenT.setText(u.getEmail());
		}
     
        
    }
    public static void sms(String msg, String phoneNumber) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber(phoneNumber),
                new PhoneNumber("+17743571480"),
                msg).create();
        System.out.println(message.getSid());
    }
}

   

   



