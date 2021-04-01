/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artraction.service;

import artraction.entity.userEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import org.springframework.security.crypto.bcrypt.BCrypt;
import artraction.utils.ConnexionSingleton;
import java.util.Properties;
import javafx.scene.control.TextField;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author MSI
 */
public class User implements IdaoUser<artraction.entity.userEntity> {

    public static int getNumero() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
PreparedStatement pst = null;
Statement st;
public static User instance;
ResultSet rs = null;
public User userInfos;
User su;
    public int User;
    public int get;

    public User(int parseInt, int aInt, int aInt0, String string, String string0, String string1, String string2, String string3, String string4) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User(String text, String text0, String text1, String text2, String text3, TextField tfaddresse, Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
    public artraction.entity.userEntity FindUser(String email, String password, String role) {
        Connection conn = null;
    try {
        conn = ConnexionSingleton.getInstance().getCnx();
    } catch (Exception ex) {
        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    String sql = "Select * from user where email = ? and role = ? ";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            //pst.setString(2, password);
            pst.setString(2, role);
            
            rs = pst.executeQuery();
            
            if(rs.next()){ 
                /*JOptionPane.showMessageDialog(null, "Username And Password is Corect");
                userEntity _u = new userEntity(Integer.parseInt(rs.getString("id")), Integer.parseInt(rs.getString("numero")), Integer.parseInt(rs.getString("age")), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("Image"), rs.getString("addresse"), rs.getString("role"));
                return _u;*/
                if(BCrypt.checkpw(password, rs.getString("password")))
                {
                    JOptionPane.showMessageDialog(null, "Username and password are correct");
                    artraction.entity.userEntity _u = new artraction.entity.userEntity(Integer.parseInt(rs.getString("id")), Integer.parseInt(rs.getString("numero")), Integer.parseInt(rs.getString("age")), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("Image"), rs.getString("addresse"), rs.getString("role"));
                    return _u;
                }
                /* btn_login.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("CPanel.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();*/
               
                
            }else
                JOptionPane.showMessageDialog(null, "Invalid username or password");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public artraction.entity.userEntity FindUser(String email, String password) {
        Connection conn = null;
    try {
        conn = ConnexionSingleton.getInstance().getCnx();
    } catch (Exception ex) {
        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    String sql = "Select * from user where email = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            
            rs = pst.executeQuery();
            
            if(rs.next()){ 
                /*JOptionPane.showMessageDialog(null, "Username And Password is Corect");
                userEntity _u = new userEntity(Integer.parseInt(rs.getString("id")), Integer.parseInt(rs.getString("numero")), Integer.parseInt(rs.getString("age")), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("Image"), rs.getString("addresse"), rs.getString("role"));
                return _u;*/
                if(BCrypt.checkpw(password, rs.getString("password")))
                {
                    JOptionPane.showMessageDialog(null, "Username and password are correct");
                    artraction.entity.userEntity _u = new artraction.entity.userEntity(Integer.parseInt(rs.getString("id")), Integer.parseInt(rs.getString("numero")), Integer.parseInt(rs.getString("age")), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("Image"), rs.getString("addresse"), rs.getString("role"));
                    return _u;
                }
                /* btn_login.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("CPanel.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();*/
               
                
            }else
                JOptionPane.showMessageDialog(null, "Invalid username or password");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public static User getInstance() throws Exception
    {
        if(instance == null)
            instance = new User();
        return instance;
    }
    
    public User() throws Exception
    {
        ConnexionSingleton cs = ConnexionSingleton.getInstance();
        try {
            st = cs.getCnx().createStatement();
        } catch(SQLException ex)
        {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AddUser(String username, String email, String password, int numero, int age, String addresse, String role, String image) {
                String sql = "insert into user (username,email,password,numero,age,addresse,role,image) values (?,?,?,?,?,?,?,?)";
        Connection conn = null;
    try {
        conn = ConnexionSingleton.getInstance().getCnx();
    } catch (Exception ex) {
        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, email);
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(13));
            pst.setString(3, hashedPassword);
            pst.setInt(4, numero);
            pst.setInt(5, age);
            pst.setString(6, addresse);
            pst.setString(7, role);
            pst.setString(8, image);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Saved");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }  
    }
    
    public ObservableList<artraction.entity.userEntity> getDatausers(){
        Connection conn = null;
        ObservableList<artraction.entity.userEntity> list = FXCollections.observableArrayList();
        try {
            conn = ConnexionSingleton.getInstance().getCnx();
            pst = conn.prepareStatement("select * from user");
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()){   
                System.out.println(Integer.parseInt(rs.getString("id")) + " " + rs.getInt("numero") + " " + rs.getInt("username") + " " + rs.getString("age") + " " + rs.getString("email") + " " + rs.getString("password") + " " + rs.getString("image") + " " + rs.getString("addresse") + " " +rs.getString("role"));
                list.add(new artraction.entity.userEntity(Integer.parseInt(rs.getString("id")), rs.getInt("numero"), rs.getInt("username"), rs.getString("age"), rs.getString("email"), rs.getString("password"), rs.getString("image"), rs.getString("addresse"),rs.getString("role")));
            }
        } catch (Exception e) {
        }
        return list;
    }

  
    public void DeleteUser(String email) {
        String sql = "delete from users where username = ?";
        Connection conn = null;
    try {
        conn = ConnexionSingleton.getInstance().getCnx();
    } catch (Exception ex) {
        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void ajouter(artraction.entity.userEntity t) throws Exception {
        System.out.println(t.getUsername() + " " +t.getEmail() + " " +t.getPassword() + " " +t.getNumero() + " " +t.getAge() + " " +t.getAdresse() + " " +t.getRole() + " " +"");
        AddUser(t.getUsername(),t.getEmail(),t.getPassword(),t.getNumero(),t.getAge(),t.getAdresse(),t.getRole(),"");
    }
	
	public void supprimer(artraction.entity.userEntity t) throws Exception {
		try {
            String requete = "DELETE FROM user WHERE id=?";
            PreparedStatement pst = ConnexionSingleton.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,t.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
	}
	
	public void modifier(artraction.entity.userEntity t, String oldmail) throws Exception {
		try {
                    String requete = "Update `user` SET username=?, email=?,password=?,numero=?,age=?,addresse=?,role=? WHERE email=?";
					/*+ "`username`, `email`,"
					+ " `password`, `numero`, `age`,"
					+ " `addresse`, `role` "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";*/
            PreparedStatement pst = ConnexionSingleton.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, t.getUsername());
			pst.setString(2, t.getEmail());
			pst.setString(3, t.getPassword());
			pst.setInt(4, t.getNumero());
			pst.setInt(5, t.getAge());
			pst.setString(6, t.getAdresse());
                        pst.setString(7, t.getRole());
              
            
                    
			System.out.println(requete);
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
	}
	
	public List<artraction.entity.userEntity> afficher() throws Exception {
		ObservableList <artraction.entity.userEntity> ListUsers = FXCollections.observableArrayList();
		
		try {
            String requete = "SELECT id, username,"
					+ " email, password"
					+ " , numero, age, addresse,role "
					+ "FROM user";
            PreparedStatement pst = ConnexionSingleton.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                                artraction.entity.userEntity usr = new artraction.entity.userEntity(rs.getInt("id"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getInt("numero"), rs.getInt("age"), rs.getString("addresse"), rs.getString("role"));

                //userEntity usr = new userEntity(rs.getInt("id"),rs.getInt("numero"),rs.getInt("age"), rs.getString("username"), rs.getString("email"),rs.getString("password"), "",rs.getString("addresse"), rs.getString("role"));
                System.out.println(usr);
                ListUsers.add(usr);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
		return ListUsers;
	}
        
	
	public List<artraction.entity.userEntity> search(String input) throws Exception {
		ObservableList <artraction.entity.userEntity> ListUsers = FXCollections.observableArrayList();
		
		try {
            String requete = "SELECT id, username,"
					+ " email,password,"
                                         + " numero, age, addresse,role "+ "FROM user "
					+ "WHERE `username` like ? or `email` like ? or "
					+ " `password` like ? or `numero` like ? or `age` like ? or "
					+ " `addresse` like ? ";
            PreparedStatement pst = ConnexionSingleton.getInstance().getCnx().prepareStatement(requete);
			pst.setString(1, "%"+input+"%");
			pst.setString(2, "%"+input+"%");
			pst.setString(3, "%"+input+"%");
			pst.setString(4, "%"+input+"%");
			pst.setString(5, "%"+input+"%");
			pst.setString(6, "%"+input+"%");
                       
                      
                    
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ListUsers.add(new artraction.entity.userEntity(rs.getInt("id"),rs.getInt("numero"),
						rs.getInt("age"), rs.getString("username"), 
						rs.getString("email"),
						rs.getString("password"), rs.getString("addresse"), rs.getString("role"),"" 
						
						
				));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
		return ListUsers;
	}
        
    
public int sendConfirmation(String email) {
		int min = 111111;
		int max = 999999;
		int code = (int) ((Math.random() * (max - min)) + min);
		
		String to = email;
		String from = "5889fc49fe-7e1702@inbox.mailtrap.io";
		final String username = "1ee9c0a3f50772";
		final String password = "db69ab814472b5";
		
		String host = "smtp.mailtrap.io";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");  
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "2525");
		
		Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
    }
         });
		
		try {
    //create a MimeMessage object
    Message message = new MimeMessage(session);
 
    //set From email field 
    message.setFrom(new InternetAddress(from));
 
    //set To email field
    message.setRecipients(Message.RecipientType.TO,
               InternetAddress.parse(to));
 
    //set email subject field
    message.setSubject("Artraction Sign up conformation!");
 
    //set the content of the email message
    message.setText("user are created ");

    //send the email message
    Transport.send(message);

    System.out.println("Email Message Sent Successfully");

      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }

		return code;
	}

    public List<artraction.entity.userEntity> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void traiter(int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void ajouter(User u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getEmail() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    public artraction.entity.userEntity FindUser1(String email) {
        Connection conn = null;
    try {
        conn = ConnexionSingleton.getInstance().getCnx();
    } catch (Exception ex) {
        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    String sql = "Select * from user where email = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            
            rs = pst.executeQuery();
            
            if(rs.next()){ 
                /*JOptionPane.showMessageDialog(null, "Username And Password is Corect");
                userEntity _u = new userEntity(Integer.parseInt(rs.getString("id")), Integer.parseInt(rs.getString("numero")), Integer.parseInt(rs.getString("age")), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("Image"), rs.getString("addresse"), rs.getString("role"));
                return _u;*/
               
                    JOptionPane.showMessageDialog(null, "Username and password are correct");
                    artraction.entity.userEntity _u = new artraction.entity.userEntity(Integer.parseInt(rs.getString("id")), Integer.parseInt(rs.getString("numero")), Integer.parseInt(rs.getString("age")), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("Image"), rs.getString("addresse"), rs.getString("role"));
                    return _u;
                
                /* btn_login.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("CPanel.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();*/
               
                
            }else
                JOptionPane.showMessageDialog(null, "Invalid email");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    void setId(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setUsername(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setMail(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean ResetPassword(String pass, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
   
}
