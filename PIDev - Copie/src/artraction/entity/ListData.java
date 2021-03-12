/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.entity;

import artraction.service.EnchereService;
import artraction.entity.Enchere;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;



/**
 *
 * @author wiemhjiri
 */
public class ListData {
    
     /**
     * The data as an observable list of Persons.
     */
    
    private ObservableList<Enchere> encheres=FXCollections.observableArrayList();

    public ListData() {
        
        EnchereService eService=EnchereService.getInstance();
        encheres= eService.displayAll();
        System.out.println(encheres);
    }
    
    public ObservableList<Enchere> getEncheres(){
        return encheres;
    }
   
}
