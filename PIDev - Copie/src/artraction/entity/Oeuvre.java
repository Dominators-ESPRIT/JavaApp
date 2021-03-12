/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.entity;

/**
 *
 * @author Hanine
 */
public class Oeuvre {
    
    
     private int id;
     private int ref ;
     private int id_panier;
     private int label;
     private int description;
     private int date_ajout;
     private int prix;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public int getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(int date_ajout) {
        this.date_ajout = date_ajout;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Oeuvre{" + "id=" + id + ", ref=" + ref + ", id_panier=" + id_panier + ", label=" + label + ", description=" + description + ", date_ajout=" + date_ajout + ", prix=" + prix + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Oeuvre other = (Oeuvre) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
     
     
    
}
