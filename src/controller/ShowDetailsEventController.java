/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import static controller.ShowEventClientController.idE;
import entities.BadWords;
import entities.CommentEvenement;
import entities.Evenement;
import entities.Participer;
import entities.Sponsor;
import entities.User;
import entities.Vote;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import service.CommentService;
import service.EvenementService;
import service.ParticiperService;
import service.ServiceVote;


/**
 * FXML Controller class
 *
 * @author dell
 */
public class ShowDetailsEventController implements Initializable {

    @FXML
    private ListView<CommentEvenement> listViewQR;
    ObservableList<CommentEvenement> data;
    int i,i2 ;
    public static int idC ;

    @FXML
    private Text question;
    private JFXButton Update;
    @FXML
    private JFXButton type_vote_oui;
    @FXML
    private JFXButton type_vote_non;
    @FXML
    private JFXButton Back;
    @FXML
    private JFXButton addComment;
    @FXML
    private JFXButton back;
    @FXML
    private ImageView img_ev;
    @FXML
    private TextField commentText;
    @FXML
    private Label nbrLike;
    @FXML
    private Label nbrdeslike;
    @FXML
    private JFXButton participer;
    @FXML
    private JFXButton quiter;
 
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ParticiperService ps = new ParticiperService();
        try {
            System.out.println("controller.ShowDetailsEvheloooooooooooentController.initialize()"+ps.getParticipant(idE, 1));
        } catch (SQLException ex) {
            Logger.getLogger(ShowDetailsEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        try {
            if(ps.getParticipant(idE, 1)== null){
                
                quiter.setVisible(true);
                
            }
            else{
            
            participer.setVisible(false);
            
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShowDetailsEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

                 CommentService cs = new CommentService();
         EvenementService es = new EvenementService();
         question.setText(cs.findEvenementById(idE).getTitre());
                  System.err.println("okiiiiiiiiiiii"+ cs.findEvenementById(idE).getImage());

       img_ev.setImage(new Image("gui/img/" + cs.findEvenementById(idE).getImage()));
        try {
            data = cs.getAllCommentByEvent(cs.findEvenementById(idE));
        } catch (SQLDataException ex) {
            Logger.getLogger(ShowDetailsEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
         //supprimer.setVisible(false);
        // Question quest = new Question();
         //quest.setId_client(LoginController.session.getId());

     /*    if (quest.getId_client()==idU){
       supprimer.setVisible(true);
    }*/
         
     

        listViewQR.setItems(data);
        listViewQR.setCellFactory((ListView<CommentEvenement> param) -> new ListViewCommentEvent());
        
        ServiceVote vs = new ServiceVote();
        try {
            nbrLike.setText(String.valueOf(vs.NumLike(idE)));
            nbrdeslike.setText(String.valueOf(vs.NumdeLike(idE)));
            //supprimer.setVisible(false);
            // Question quest = new Question();
            //quest.setId_client(LoginController.session.getId());
            
            /*    if (quest.getId_client()==idU){
            supprimer.setVisible(true);
        }*/ } catch (SQLException ex) {
            Logger.getLogger(ShowDetailsEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     

    }

        
        
        
        // TODO
      

    private void DeleteQuestion(ActionEvent event) throws SQLDataException {
        
    CommentService q = new CommentService();
    ObservableList<CommentEvenement> e;
        e = listViewQR.getSelectionModel().getSelectedItems();
        
         for (CommentEvenement m : e) {
            idC=m.getIdComment();
              
        }
         System.out.println("controller.ShowDetailsEventController.DeleteQuestion(ddddddddd)"+i);
        q.deleteComment(idC);
         Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/gui/ShowDetailsEvent.fxml"));
            Stage myWindow =  (Stage) question.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    
    }

    private void UpdateReponse(ActionEvent event) {
        
           ObservableList<CommentEvenement> c;
        c = listViewQR.getSelectionModel().getSelectedItems();
        
      
        for (CommentEvenement m : c) {
            idC=m.getIdComment();
            
              
        }
        
         Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/gui/UpdateComment.fxml"));
            Stage myWindow =  (Stage) question.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Modifier Reponse");
            myWindow.show();
            
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }   

    @FXML
    private void Like(ActionEvent event) throws SQLException {
        
        Vote v = new Vote();
       ServiceVote vs = new ServiceVote();
       v.setId_client(1);
       v.setId_evenement(idE);
       v.setType_vote(2);
              vs.addVote(v);

         
        try {
       Parent root = FXMLLoader.load(getClass().getResource("/gui/ShowDetailsEvent.fxml"));
            Stage myWindow =  (Stage) question.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    
       
    }

    @FXML
    private void DesLike(ActionEvent event) throws SQLException {
    
        
        Vote v = new Vote();
       ServiceVote vs = new ServiceVote();
      // vs.getAllVote(idE, 1);
       v.setId_client(1);
       v.setId_evenement(idE);
       v.setType_vote(1);
       vs.addVote(v);
         
        try {
       Parent root = FXMLLoader.load(getClass().getResource("/gui/ShowDetailsEvent.fxml"));
            Stage myWindow =  (Stage) question.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    
    }

    @FXML
    private void BackQuestion(ActionEvent event) {
    }

    @FXML
    private void AddComment(ActionEvent event) throws SQLDataException {
        
        
        
        
        
        
        
           CommentEvenement e = new CommentEvenement();
        CommentService cs = new CommentService();

        e.setId(cs.findUserById(1));
        e.setIdEvt(cs.findEvenementById(idE));

        BadWords.loadConfigs();
      
            {
                
                if (BadWords.filterText(commentText.getText())) {
            
       Notifications notificationBuilder = Notifications.create()
               .title("Alert").text("cette application n'autorise pas ces termes").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
                       
           Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/gui/ShowDetailsEvent.fxml"));
            Stage myWindow =  (Stage) commentText.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("comment");
            myWindow.show();
           
        } catch (IOException ex) {
            Logger.getLogger(CommentItemController.class.getName()).log(Level.SEVERE,null,ex);
           
        }

                  
                } else
                
                {
                    
                          
       Notifications notificationBuilder = Notifications.create()
               .title("Succes").text("Votre Commentaire est publier").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
                   e.setComment(commentText.getText());
                   cs.addComment(e);
                   
                   // Alert alert = new Alert(Alert.AlertType.WARNING);
                   
                   /* alert.setTitle("gros mot");
                    alert.setHeaderText("gros mot trouve");
                    alert.setContentText("cette application n'autorise pas ces termes ");
                    alert.showAndWait();*/
                    
                    
            
           Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/gui/ShowDetailsEvent.fxml"));
            Stage myWindow =  (Stage) commentText.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("comment");
            myWindow.show();
             
        } catch (IOException ex) {
            Logger.getLogger(CommentItemController.class.getName()).log(Level.SEVERE,null,ex);
           
        }
        
    
            }
    
    }}
      /*    Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/gui/ShowDetailsEvent.fxml"));
            Stage myWindow =  (Stage) Update.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Ajouter Comment");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        
    } */
    
    private void ParticiperEvent(ActionEvent event) throws SQLDataException {
         
      //  e2 = listViewQR.getSelectionModel().getSelectedItems().getI;
        
      
         
         CommentService cs = new CommentService();
         EvenementService es = new EvenementService();
         ParticiperService ps = new ParticiperService();
         Participer p = new Participer(1,idE);
     
        
        //e=cs.findEvenementById(idE);
       // u = cs.findUserById(1);      ;
        ps.addParticiper(p);
        
        
        try {
       Parent root = FXMLLoader.load(getClass().getResource("/gui/ShowDetailsEvent.fxml"));
            Stage myWindow =  (Stage) question.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    
        
        
    }

    @FXML
    private void back(ActionEvent event) {
         try {
           Parent root = FXMLLoader.load(getClass().getResource("/gui/ShowEventClient.fxml"));
            Stage myWindow =  (Stage) commentText.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Ajouter Comment");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @FXML
    private void Participer(ActionEvent event) throws SQLDataException {
        
               CommentService cs = new CommentService();
         EvenementService es = new EvenementService();
         ParticiperService ps = new ParticiperService();
         int nbr=0;
          

         Participer p = new Participer(1,idE);
     
        
      Evenement  e1=cs.findEvenementById(idE);
      nbr = e1.getNbreparticipants()+1;
      e1.setNbreparticipants(nbr);
        System.out.println("controller.ShowEventClientController.participation()++++++++++"+nbr);
      es.ModifierEvenenmentPlace(e1);
      ps.addParticiper(p);
        
              
       Notifications notificationBuilder = Notifications.create()
               .title("Success").text("Votre Participation et accepter").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
        try {
           Parent root = FXMLLoader.load(getClass().getResource("/gui/ShowDetailsEvent.fxml"));
            Stage myWindow =  (Stage) commentText.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Ajouter Comment");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
 
    }

    @FXML
    private void quiter(ActionEvent event) {
    }
    
}
