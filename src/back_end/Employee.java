/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author risha
 */
public class Employee {
    String employee_id;
    String employee_Name;
    String employee_username;
    String employee_email;
    String employee_password;
    String role = "Employee";
    public void setEmployee(String employee_id, String employee_Name, String employee_username, String employee_email, String employee_password){
        this.employee_id = employee_id;
        this.employee_Name=employee_Name;
        this.employee_username=employee_username;
        this.employee_email = employee_email;
        this.employee_password = employee_password;
    }
    
    public boolean addEmployee(){
        DbConnect obj = new DbConnect();
        obj.connect();
        String query = "INSERT INTO users (fullName, password, role, email, Username) VALUES ('" + employee_Name + "',  '" + employee_password + "','"+role+"','" + employee_email + "','" + employee_username + "')";
        
        return obj.insertData(query);
    }
    public boolean updateEmployee(){
        DbConnect obj = new DbConnect();
        obj.connect();
        String query = "UPDATE `users` SET `fullName` = '" + employee_Name + "', `password` = '" + employee_password + "', `email` = '" + employee_email + "', `Username` = '"+employee_username+"' WHERE user_id = '" + employee_id + "'";
        return obj.insertData(query);
    }
    public boolean deleteEmployee(){
        DbConnect obj = new DbConnect();
        obj.connect();
        String query = "DELETE FROM `users` WHERE user_id = '" + employee_id + "'";
        return obj.insertData(query);
    }
    public boolean checkEmployee(String employee_id) {
    DbConnect obj = new DbConnect();
    obj.connect();
    String query = "SELECT user_id FROM users WHERE user_id='" + employee_id + "'";
    ResultSet rs = obj.selectDataAndJoin(query);
    try {
        if (rs.next()) {
            return true; // Employee id already exists
        }
    } catch (SQLException ex) {
        System.out.println("error");
    }
    return false; // Employee id does not exist
    }

    
    
}
