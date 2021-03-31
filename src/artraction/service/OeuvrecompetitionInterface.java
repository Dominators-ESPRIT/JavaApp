/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.service;

import artraction.entity.OeuvreCompetitionnModel;
import artraction.entity.Oeuvre_competition;
import java.io.File;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author boukhris
 */
public interface OeuvrecompetitionInterface {

    public void Add_Oeuvre_Competition(Oeuvre_competition oc) throws SQLDataException;

    public List<Oeuvre_competition> getAllOeuvreCompetition() throws SQLDataException;

    public List<Oeuvre_competition> afficheOeuvreCompetition(String nom) throws SQLDataException;

    public List<OeuvreCompetitionnModel> getOeuvreCompetionById(int refCompetion) throws SQLDataException;

    public void Add_Oeuvre_Competition(File file, int refCompetition, int refOeuvre, String description) throws SQLDataException;
    public void Add_Vote_Oeuvre_Competition( int id_user_vote,int  ref_oc,int ref_competition) throws SQLException;
    public int getVoteByRefOC(int refOC)throws SQLDataException;
    
    public List<OeuvreCompetitionnModel> getOuvreCompetitionByArtist(int refCompetion, int artistId) throws SQLException;
    
    public void delateoeuvrecompetitionbyId(int ref_Ov,int id_user) throws SQLException;

}
