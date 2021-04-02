/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.entity;

/**
 *
 * @author boukhris
 */
public class Oeuvre_competition {
    private int ref_oc;
    private int Ref_Ov;
    private String description;
    private String image;

    public Oeuvre_competition() {
    }

    public Oeuvre_competition(int ref_oc, int Ref_Ov, String description, String image) {
        this.ref_oc = ref_oc;
        this.Ref_Ov = Ref_Ov;
        this.description = description;
        this.image = image;
    }

    public Oeuvre_competition(int Ref_Ov, String description, String image) {
        this.Ref_Ov = Ref_Ov;
        this.description = description;
        this.image = image;
    }

    public Oeuvre_competition(String description, String image) {
        this.description = description;
        this.image = image;
    }

    public int getRef_oc() {
        return ref_oc;
    }

    public void setRef_oc(int ref_oc) {
        this.ref_oc = ref_oc;
    }

    public int getRef_Ov() {
        return Ref_Ov;
    }

    public void setRef_Ov(int Ref_Ov) {
        this.Ref_Ov = Ref_Ov;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Oeuvre_competition{" + "ref_oc=" + ref_oc + ", Ref_Ov=" + Ref_Ov + ", description=" + description + ", image=" + image + '}';
    }
    
    
}
