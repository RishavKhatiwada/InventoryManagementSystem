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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author risha
 */
public class Category {

    String Category_id;
    String Category_Name;

    public void setCategory(String Category_id, String Category_Name) {
        this.Category_id = Category_id;
        this.Category_Name = Category_Name;

    }

    public boolean addCategory() throws SQLException {
        DbConnect obj = new DbConnect();
        obj.connect();
        String query = "SELECT * FROM categories WHERE name = '" + Category_Name + "'";
        ResultSet rs = obj.selectDataAndJoin(query);

        if (rs.next()) {
            // category already exists, show popup message
            JOptionPane.showMessageDialog(null, "Category already exists in the database.");
            return false;
        } else {
            // category does not exist, insert into database
            query = "INSERT INTO categories (category_id, name) VALUES ('" + Category_id + "', '" + Category_Name + "')";
            return obj.insertData(query);
        }
    }

    public boolean updateCategory() {
        try {
            DbConnect obj = new DbConnect();
            obj.connect();
            String query = "SELECT * FROM categories WHERE name = '" + Category_Name + "'";
            ResultSet rs = obj.selectDataAndJoin(query);
            
            if (rs.next()) {
                // category name already exists, show popup message
                JOptionPane.showMessageDialog(null, "Category name already exists in the database.");
                return false;
            } else {
                // category name does not exist, perform update
                query = "UPDATE categories SET name = '" + Category_Name + "' WHERE category_id = '" + Category_id + "'";
                return obj.insertData(query);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deleteCategory() {
        DbConnect obj = new DbConnect();
        obj.connect();
        String query = "DELETE FROM `categories` WHERE category_id = '" + Category_id + "'";
        return obj.insertData(query);
    }

    public boolean checkCategory(String location_id) {
        DbConnect obj = new DbConnect();
        obj.connect();
        String query = "SELECT Category_id FROM categories WHERE Category_id='" + Category_id + "'";
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
