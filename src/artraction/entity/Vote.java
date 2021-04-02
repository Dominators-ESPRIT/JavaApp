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
public class Vote {
    private int ref_vote;
    private int ref_oc;
    private int id_user_vote;

    public Vote() {
    }

    public Vote(int ref_vote, int ref_oc, int id_user_vote) {
        this.ref_vote = ref_vote;
        this.ref_oc = ref_oc;
        this.id_user_vote = id_user_vote;
    }

    public Vote(int ref_oc, int id_user_vote) {
        this.ref_oc = ref_oc;
        this.id_user_vote = id_user_vote;
    }

    public int getRef_vote() {
        return ref_vote;
    }

    public void setRef_vote(int ref_vote) {
        this.ref_vote = ref_vote;
    }

    public int getRef_oc() {
        return ref_oc;
    }

    public void setRef_oc(int ref_oc) {
        this.ref_oc = ref_oc;
    }

    public int getId_user_vote() {
        return id_user_vote;
    }

    public void setId_user_vote(int id_user_vote) {
        this.id_user_vote = id_user_vote;
    }

    @Override
    public String toString() {
        return "Vote{" + "ref_vote=" + ref_vote + ", ref_oc=" + ref_oc + ", id_user_vote=" + id_user_vote + '}';
    }
    
    
    
}
