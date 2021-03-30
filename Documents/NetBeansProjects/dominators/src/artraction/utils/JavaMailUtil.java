/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.utils;
//import javax.mail;
import java.io.File;
import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 *
 * @author zeyne
 */
public class JavaMailUtil {
    
    /**
     *
     * @param recipient
     * @param file
     * @throws Exception
     */
    public static void sendMail(String recipient,String file,String username) throws Exception{
     Properties prop=new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", true);
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        String myaccount="zeyneb.sdiri@esprit.tn";
        String passw="11660660";
        Session sess = Session.getInstance(prop, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myaccount, passw );
            }
        });
        Message msg=prepareMessage(sess,myaccount,recipient,file,username );
        
        Transport.send(msg);
        System.out.println("Message envoyé avec succés");
        
  //  });*/
    }

    private static Message prepareMessage(Session sess, String myaccount, String recipient, String file, String username) {
              Message msg = msg =new MimeMessage(sess);
  try {
            msg.setFrom(new InternetAddress(myaccount));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            msg.setSubject("Facture artraction");
           
            Multipart attach=new MimeMultipart();
            MimeBodyPart text= new MimeBodyPart();
            text.setText("Merci "+username+" d'éffectuer vos achats sur notre plateforme Artraction. \n Vous trouverez ci-joint votra facture.");
            attach.addBodyPart(text);

             
          
           MimeBodyPart pdf= new MimeBodyPart();
           pdf.attachFile(file);
          attach.addBodyPart(pdf);
            msg.setContent(attach);
         Transport.send(msg);
           
        } catch (Exception ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
                    return msg;

    }
    
}
