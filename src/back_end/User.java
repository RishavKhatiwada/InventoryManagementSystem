/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author prasi
 */
public class User {
    String username;
    String password;
    String conf_password;
    String firstname;
    String lastname;
    String role;
    String email;
    //Regex to check email and password
	String email_regex;
	Pattern email_pattern;
	
	String password_regex;
	Pattern password_pattern;

    public User() {
        this.password_regex = "^(?=.*[!@#$%^&*(),.?\":{}|<>])(?!.*[\\s]).{4,}$";
        this.password_pattern = Pattern.compile(password_regex);
        
        this.email_regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        this.email_pattern = Pattern.compile(email_regex);
        
    }
    
    public void setUsernameAndPassword(String Username, String Password){
        this.username = Username;
        this.password = Password;
    }
    
    public void Setsignup(String email,String password,String conf_password,String firstname,String lastname,String username, String role){
        this.username = username;
        this.password = password;
        this.conf_password = conf_password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.email = email;
    }
    
    public boolean loginCheck(){
        DbConnect obj = new DbConnect();
        obj.connect();
         
        String query = "SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'";
        ResultSet rs = obj.selectDataAndJoin(query);
try {
    if (rs.next()) {
        // The query returned at least one row, so the username and password are valid
        popmsg("Login successfully");
        return true;
    } else {
        // The query returned no rows, so the username and password are not valid
        popmsg("Invalid username or password.");
        return false;
    }
} catch (SQLException e) {
    System.out.println(e.getMessage() + "Error executing query: ");
    return false;
}
    
}
    public boolean signup() {
         DbConnect obj = new DbConnect();
        obj.connect();
        Matcher email_matcher = email_pattern.matcher(email);
        Matcher password_matcher = password_pattern.matcher(password);
        if(!email_matcher.matches()){
            popmsg("Email doesnot match the regex");
            return false;
        }
        else if(!password_matcher.matches()){
            popmsg("Password must contain 1 special letter and atleast 4 character");
            return false;
        }
        else if(password == null ? conf_password != null : !password.equals(conf_password)){
            popmsg("Password are not same");
            return false;
        }
        else if(firstname.equals("") || lastname.equals("") || firstname.equals("Firstname")|| lastname.equals("Lastname")){
                popmsg("First name and Last name required!!!");
                return false;
            }
        else {
            String fullname = firstname + " " + lastname; // Catch the specific exception for duplicate entry error
             // Catch other SQLExceptions here
             String query = "INSERT INTO users (email, password, fullname, username, role) VALUES ('" + email + "', '" + password + "', '" + fullname + "', '" + username + "', '" + role + "')";
             boolean dulicate  = obj.insertData(query);
             if(!dulicate){
                 popmsg("Dulicate email or username!!");
                 return false;
             }
             else{
                 popmsg("Account Created!!");
                 return true;
             }
             
        }
    }
    
    public void popmsg(String msg) {
              JOptionPane.showMessageDialog(null, msg);
   }
}
