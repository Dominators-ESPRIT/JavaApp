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
public class Participation_competition {
    private int ref_competition;
    private int ref_oc;

    public Participation_competition() {
    }

    public Participation_competition(int ref_competition, int ref_oc) {
        this.ref_competition = ref_competition;
        this.ref_oc = ref_oc;
    }

    public int getRef_competition() {
        return ref_competition;
    }

    public void setRef_competition(int ref_competition) {
        this.ref_competition = ref_competition;
    }

    public int getRef_oc() {
        return ref_oc;
    }

    public void setRef_oc(int ref_oc) {
        this.ref_oc = ref_oc;
    }

    @Override
    public String toString() {
        return "Participation_competition{" + "ref_competition=" + ref_competition + ", ref_oc=" + ref_oc + '}';
    }
    
    
}
