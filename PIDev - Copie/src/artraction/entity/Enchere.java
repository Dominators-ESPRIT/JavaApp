/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.entity;

import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author Hanine
 */
public class Enchere {
    
    
        private int id_enchere;
     
        private int ref_O;
        private String nomOeuvre;
        private String prixinit_enchere;
        private Date date_enchere;
        private String duree_enchere;
        private String coupant ;
        protected  static  int prix_vente  ;
        
    
        
           public Enchere() {
    }

    public Enchere(String prixinit_enchere, Date date_enchere, String duree_enchere, String coupant) {
        this.prixinit_enchere = prixinit_enchere;
        this.date_enchere = date_enchere;
        this.duree_enchere = duree_enchere;
        this.coupant = coupant;
    }

    public Enchere(String nomOeuvre, String prixinit_enchere, Date date_enchere, String duree_enchere, String coupant) {
        this.nomOeuvre = nomOeuvre;
        this.prixinit_enchere = prixinit_enchere;
        this.date_enchere = date_enchere;
        this.duree_enchere = duree_enchere;
        this.coupant = coupant;
    }

    public Enchere(String prixinit_enchere, String duree_enchere, String coupant) {
        this.prixinit_enchere = prixinit_enchere;
        this.duree_enchere = duree_enchere;
        this.coupant = coupant;
    }

   

    public int getId_enchere() {
        return id_enchere;
    }

    public void setId_enchere(int id_enchere) {
        this.id_enchere = id_enchere;
    }

  
    public int getRef_O() {
        return ref_O;
    }

    public void setRef_O(int ref_O) {
        this.ref_O = ref_O;
    }

    public String getNomOeuvre() {
        return nomOeuvre;
    }

    public void setNomOeuvre(String nomOeuvre) {
        this.nomOeuvre = nomOeuvre;
    }

    public String getPrixinit_enchere() {
        return prixinit_enchere;
    }

    public void setPrixinit_enchere(String prixinit_enchere) {
        this.prixinit_enchere = prixinit_enchere;
    }

    public Date getDate_enchere() {
        return date_enchere;
    }

    public void setDate_enchere(Date date_enchere) {
        this.date_enchere = date_enchere;
    }

    public String getDuree_enchere() {
        return duree_enchere;
    }

    public void setDuree_enchere(String duree_enchere) {
        this.duree_enchere = duree_enchere;
    }

    public String getCoupant() {
        return coupant;
    }

    public void setCoupant(String coupant) {
        this.coupant = coupant;
    }

    public static int getPrix_vente() {
        return prix_vente;
    }

    public static void setPrix_vente(int prix_vente) {
        Enchere.prix_vente = prix_vente;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.id_enchere;
        hash = 89 * hash + Objects.hashCode(this.nomOeuvre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Enchere other = (Enchere) obj;
        if (this.id_enchere != other.id_enchere) {
            return false;
        }
        if (!Objects.equals(this.nomOeuvre, other.nomOeuvre)) {
            return false;
        }
        return true;
    }

 

   

    

    public void setPrix_enchere(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}