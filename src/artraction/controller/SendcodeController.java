/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.controller;


import artraction.entity.userEntity;
import artraction.service.User;
import artraction.utils.ConnexionSingleton;
import java.awt.Component;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class SendcodeController implements Initializable {
int randomCode;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtVer;
    @FXML
    private Button envoyer;
    @FXML
    private Button verifier;
    @FXML
    private AnchorPane rootpane;
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    } 
//       @FXML
//private String RechercheMail (String email ) 
//     {
//        String status = "Success";
//      
//            //query
//            String sql = "SELECT * FROM user Where email = ?  ";
//            try {
//                preparedStatement = con.prepareStatement(sql);
//                preparedStatement.setString(1, email);
//         
//
//                resultSet = preparedStatement.executeQuery();
//                if (!resultSet.next()) 
//                {
//                    status = "Error";
//                } 
//                else 
//                {
//                   status = "Success"; 
//                }
//            } catch (SQLException ex) {
//                System.err.println(ex.getMessage());
//                status = "Exception";
//            }
//        
//        
//        return status;
//    }
    @FXML
    private void envoyer(ActionEvent event) throws AddressException, MessagingException, Exception {
         if(!txtEmail.getText().equals(""))
        {
            System.out.println(txtEmail.getText() );
            userEntity _u = User.getInstance().FindUser1(txtEmail.getText());
             if(_u != null)
            {
                ConnexionSingleton.getInstance().uInfos = _u;
                Random rand = new Random();
randomCode=rand.nextInt(999999);
String host = "smtp.gmail.com";
String mail ="artraction1@gmail.com";
String pass="esperance1919";
String to = txtEmail.getText();
String subject="Reseting Code";
String message ="Your reset code is "+randomCode;
boolean sessionDebug = false;
Properties pros = System.getProperties();
Properties properties = new Properties();
       pros.put("mail.smtp.auth","true");
        pros.put("mail.smtp.starttls.enable","true");
       pros.put("mail.smtp.ssl.trust", "smtp.gmail.com");
       pros.put("mail.smtp.host", "smtp.gmail.com");
        pros.put("mail.smtp.port","587");
java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
Session mailSession = Session.getDefaultInstance(pros, null);
mailSession.setDebug(sessionDebug);
Message msg = new MimeMessage(mailSession);

msg.setFrom(new InternetAddress(mail));
InternetAddress [] address = {new InternetAddress(to)};
msg.setRecipients(Message.RecipientType.TO, address);
msg.setSubject(subject);
msg.setText(message);
Transport transport = mailSession.getTransport("smtp");

transport.connect(host, mail, pass);
System.out.println("artraction.controller.SendcodeController.envoyer()");
transport.sendMessage(msg, msg.getAllRecipients());
transport.close();
JOptionPane.showMessageDialog(null, "code has been send to the email");
                
                }
              
//Random rand = new Random();
//randomCode=rand.nextInt(999999);
//String host = "smtp.gmail.com";
//String mail ="artraction1@gmail.com";
//String pass="esperance1919";
//String to = txtEmail.getText();
//String subject="Reseting Code";
//String message ="Your reset code is "+randomCode;
//boolean sessionDebug = false;
//Properties pros = System.getProperties();
//Properties properties = new Properties();
//       pros.put("mail.smtp.auth","true");
//        pros.put("mail.smtp.starttls.enable","true");
//       pros.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//       pros.put("mail.smtp.host", "smtp.gmail.com");
//        pros.put("mail.smtp.port","587");
//java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
//Session mailSession = Session.getDefaultInstance(pros, null);
//mailSession.setDebug(sessionDebug);
//Message msg = new MimeMessage(mailSession);
//
//msg.setFrom(new InternetAddress(mail));
//InternetAddress [] address = {new InternetAddress(to)};
//msg.setRecipients(Message.RecipientType.TO, address);
//msg.setSubject(subject);
//msg.setText(message);
//Transport transport = mailSession.getTransport("smtp");
//
//transport.connect(host, mail, pass);
//System.out.println("artraction.controller.SendcodeController.envoyer()");
//transport.sendMessage(msg, msg.getAllRecipients());
//transport.close();
//JOptionPane.showMessageDialog(null, "code has been send to the email");

    }
    }
    @FXML
    private void verifier(ActionEvent event) throws IOException {
        if(Integer.valueOf(txtVer.getText())==randomCode){
FXMLLoader loader = new FXMLLoader(getClass().getResource("/artraction/view/Reset.fxml"));              
				Parent parent = loader.load();
				rootpane.getChildren().setAll(parent);


}else{
JOptionPane.showMessageDialog(null, "code do not match");
}
}

    
    }

   
    

