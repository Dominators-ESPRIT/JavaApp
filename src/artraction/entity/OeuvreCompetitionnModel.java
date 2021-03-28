/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.entity;

import java.io.InputStream;

/**
 *
 * @author boukhris
 */
public class OeuvreCompetitionnModel {
    int ref_oc;
    String label;
    String description;
    String username;

    public int getRef_oc() {
        return ref_oc;
    }

    public void setRef_oc(int ref_oc) {
        this.ref_oc = ref_oc;
    }
    InputStream image;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public OeuvreCompetitionnModel(String label, String description, String username, InputStream image) {
        this.label = label;
        this.description = description;
        this.username = username;
        this.image = image;
    }

    public OeuvreCompetitionnModel(int ref_oc, String label, String description, String username, InputStream image) {
        this.ref_oc = ref_oc;
        this.label = label;
        this.description = description;
        this.username = username;
        this.image = image;
    }
    
    
    
}
