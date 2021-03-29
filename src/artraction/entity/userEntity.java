<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.entity;

/**
 *
 * @author MSI
 */
public class userEntity {
    int id,numero,age;
    String username,email,password,image,adresse,role;
    
    

    public userEntity(int id, int numero, int age, String username, String email, String password, String image, String adresse, String role) {
        this.id = id;
        this.numero = numero;
        this.age = age;
        this.username = username;
        this.email = email;
        this.password = password;
        this.image = image;
        this.adresse = adresse;
        this.role = role;
    }

    public userEntity(int numero, int age, String username, String email, String password, String adresse, String role) {
        this.numero = numero;
        this.age = age;
        this.username = username;
        this.email = email;
        this.password = password;
        this.adresse = adresse;
        this.role = role;
    }

    public userEntity(int id, String username, String email, String password, int numero, int age, String adresse, String role) {
        this.id = id;
      
        this.username = username;
        this.email = email;
        this.password = password;
          this.numero = numero;
        this.age = age;
        this.adresse = adresse;
        this.role = role;
    }
    
    

    public userEntity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAdresse() {
        System.out.println(adresse);
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return id + " " + numero + " " + age + " " + username + " " + email + " " + password + " " + image + " " + adresse + " " + role;
    }

   
    
    
    
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.entity;

import java.io.InputStream;

/**
 *
 * @author MSI
 */
public class userEntity {
    int id,numero,age;
    String username,email,password,image,adresse,role;
    
    
    

    public userEntity(int id, int numero, int age, String username, String email, String password, String image, String adresse, String role) {
        this.id = id;
        this.numero = numero;
        this.age = age;
        this.username = username;
        this.email = email;
        this.password = password;
        this.image = image;
        this.adresse = adresse;
        this.role = role;
    }

    public userEntity(int numero, int age, String username, String email, String password, String adresse, String role) {
        this.numero = numero;
        this.age = age;
        this.username = username;
        this.email = email;
        this.password = password;
        this.adresse = adresse;
        this.role = role;
    }

    public userEntity(int id, String username, String email, String password, int numero, int age, String adresse, String role) {
        this.id = id;
      
        this.username = username;
        this.email = email;
        this.password = password;
          this.numero = numero;
        this.age = age;
        this.adresse = adresse;
        this.role = role;
    }
    
    

    public userEntity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAdresse() {
        System.out.println(adresse);
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return id + " " + numero + " " + age + " " + username + " " + email + " " + password + " " + image + " " + adresse + " " + role;
    }

   
    
    
    
}
>>>>>>> origin/omrani
