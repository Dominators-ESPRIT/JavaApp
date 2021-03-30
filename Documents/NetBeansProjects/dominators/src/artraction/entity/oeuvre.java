/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.entity;

/**
 *
 * @author zeyne
 */
public class oeuvre {
    String ref;
    String label;
    Double prix;

    public oeuvre(String ref, String label, Double prix) {
        this.ref = ref;
        this.label = label;
        this.prix = prix;
    }

    public oeuvre(String ref) {
        this.ref=ref;
    }
    public oeuvre(){
        
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

  
    
    
}
