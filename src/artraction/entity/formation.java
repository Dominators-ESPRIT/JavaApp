/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.entity;

import java.sql.Blob;

/**
 *
 * @author asus
 */
public class formation {
    private int id_formation ;
    private String nom;
    private String adresse ;
    private int prixph;
    private String contact ;
    private String image;

    public formation(String nom, String adresse, int prixph, String contact, String image) {
        this.nom = nom;
        this.adresse = adresse;
        this.prixph = prixph;
        this.contact = contact;
        this.image = image;
    }

    public formation(int id_formation, String nom, String adresse, int prixph, String contact, String image) {
        this.id_formation = id_formation;
        this.nom = nom;
        this.adresse = adresse;
        this.prixph = prixph;
        this.contact = contact;
        this.image = image;
    }



    public formation() {
        
    }

    public int getId_formation() {
        return id_formation;
    }

    public void setId_formation(int id_formation) {
        this.id_formation = id_formation;
    }

 

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getPrixph() {
        return prixph;
    }

    public void setPrixph(int prixph) {
        this.prixph = prixph;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "formation{" + "id_formation=" + id_formation + ", nom=" + nom + ", adresse=" + adresse + ", prixph=" + prixph + ", contact=" + contact + ", image=" + image + '}';
    }

    


   
    
    
    
}
