/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction;

import java.sql.Date;

/**
 *
 * @author MSI
 */
public class users {

    static Object gettype() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    int id,numero;
    String username,email,password,adresse,type ;
    Date date;

    public users(int id, int numero, String username, String email, String password, String adresse, String type, Date date) {
        this.id = id;
        this.numero = numero;
        this.username = username;
        this.email = email;
        this.password = password;
        this.adresse = adresse;
        this.type = type;
        this.date = date;
    }

    @Override
    public String toString() {
        return "users{" + "id=" + id + ", numero=" + numero + ", username=" + username + ", email=" + email + ", password=" + password + ", adresse=" + adresse + ", type=" + type + ", date=" + date + '}';
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    Object getLogin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Object getpassword() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
