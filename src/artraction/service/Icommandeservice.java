/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.service;

import java.sql.ResultSet;
import java.sql.Statement;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author zeyne
 */
public interface Icommandeservice <T> {

         public void insert(T o);
         public void updatecode(int id, T o);

}
