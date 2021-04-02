 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.service;

import artraction.entity.Competition;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author boukhris
 */
public interface CompetitionServiceInterface {
     public void Add_Competition (Competition c) throws SQLDataException;
    public boolean Delate_Competition(int ref_competition) throws SQLException;
    public boolean Edit_Competition(Competition c) throws SQLException;
    public List <Competition> getAllCompetition() throws SQLDataException;
    public List<Competition> afficheCompetition(String nom) throws SQLDataException;
    
}
