/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.CommentEvenement;
import entities.Evenement;
import entities.Participer;
import entities.User;
import java.sql.Date;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import service.CommentService;
import service.EvenementService;
import service.ParticiperService;

/**
 *
 * @author hazar
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLDataException, SQLException {

        DataSource cnx = DataSource.getInstance();

        Date d1 = new Date(01, 01, 2020);
        Date d2 = new Date(01, 01, 2020);

        User U = new User(1, "folan");

        Evenement evt = new Evenement(1, 4, 4, "tuniss", "tunis", d1, d2);
        //  Evenement evt2 = new Evenement(90, "tournoi", "sfax",d1 ,d2);   
        //Evenement evt3 = new Evenement(3,100, 60, "cours", "nabeul",d1 ,d2);   
        Evenement e5 = new Evenement(30, 155, "tuniss", "tuniss", d2, d2);
        CommentEvenement c = new CommentEvenement(evt, U, "helllo");
        CommentEvenement c1 = new CommentEvenement(evt, U, 64, "hazzzzzz r");
        Evenement e = new Evenement(1, 1, "haha", "hahah", "image", d2, d2);
        List<CommentEvenement> Le = new ArrayList<CommentEvenement>();

        List<Evenement> L = new ArrayList<Evenement>();
        List<CommentEvenement> Lc = new ArrayList<CommentEvenement>();

        EvenementService sv = new EvenementService();
        CommentService sc = new CommentService();
        ParticiperService ps = new ParticiperService();
        
     // sc.addComment(c);
       // Participer p = new Participer(sc.findUserById(1).getId(),sc.findEvenementById(88).getId());

        System.out.println("utils.Test.main()" + ps.getParticipant(119, 1));

        //sv.addEvenement(evt3); 
        //    // sv.ModifierEvenenment(e5);
        // sv.deleteEvenement(1);
        /*
        ObservableList<CommentEvenement> L = sc.getAllCommentByEvent(e5);
                                      ObservableList l = FXCollections.observableArrayList(L);

          for (CommentEvenement e : L)
      {
          System.out.println(e);
      }
         */
     /*   L = sv.getAllEvenement();

        for (Evenement ev : L) {
            System.out.println(e);
        }
*/
        // Evenement e2 =sc.findEvenementById(1);
        //    System.out.println(e2);
        // sc.modifieComment(c1);
        /*
 Lc = sc.getAllCommentByEvent(evt);

  for (CommentEvenement e : Lc)
      {
          System.out.println(e);
      }
 
    
         */
        //
       sc.deleteComment(65);
        // sc.finCommentById(2);
        //  ps.addParticiper(p);
    }
}
