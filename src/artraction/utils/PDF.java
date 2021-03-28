/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.utils;

import artraction.controller.PanierController;
import artraction.entity.oeuvre;
import artraction.service.ajoutoeuvservice;
import artraction.service.commandeservice;
import artraction.service.panierService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.ObservableList;
/**
 *
 * @author zeyne
 */
public class PDF {
    public String commandePDF() throws FileNotFoundException, SQLException, DocumentException 
    {
        Document document = new Document(){};
        panierService pan=panierService.getInstance();
        commandeservice com=commandeservice.getInstance();
        int idpan=pan.displayId();
        int numcom=com.displayRef();
        PanierController panier=new PanierController();
        ajoutoeuvservice ps = ajoutoeuvservice.getInstance();
        ObservableList<oeuvre> res=ps.displaycommande(idpan);
        Double somme=0.0;
        Double total=0.0;
        somme=panier.sommetotale(res);
        int codevaleur=pan.displayvaleur(idpan);
        if(codevaleur==0)
            total=somme;
        else total=somme*(100-codevaleur)/100;   
        String path="Facture"+numcom+".pdf";
        PdfWriter.getInstance(document, new FileOutputStream (new File (path)));
        document.open();
        Paragraph commande= new Paragraph ("Commande n°"+numcom);
        commande.setAlignment (Element.ALIGN_CENTER);
        commande.breakUp();
        document.add (commande);
        document.add(new Paragraph("\n\n\n\n"));
        
        for (oeuvre o : res) {
            Paragraph ref = new Paragraph ("Reference du produit: "+o.getRef()+" \t Produit: "+o.getLabel()+" \t Prix: "+o.getPrix());
            ref.setAlignment (Element.ALIGN_LEFT);
            document.add (ref);
            document.add(new Paragraph("\n\n"));
        }  
        Paragraph sommee =new Paragraph("Sous-total: "+somme);
        sommee.setAlignment(500);
        document.add(sommee);
        
        Paragraph totals =new Paragraph("Total: "+total);
        totals.setAlignment(500);
        document.add(totals);
        document.close ();
        return path;
}
}   
