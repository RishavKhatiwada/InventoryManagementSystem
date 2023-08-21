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
import javax.swing.JOptionPane;

/**
 *
 * @author risha
 */
public class Supplier {

    String Supplier_id;
    String Supplier_Name;

    public void setSupplier(String Supplier_id, String Supplier_Name) {
        this.Supplier_id = Supplier_id;
        this.Supplier_Name = Supplier_Name;

    }

    public boolean addSupplier() throws SQLException {
        DbConnect obj = new DbConnect();
        obj.connect();
        String query = "SELECT * FROM Supplier WHERE name = '" + Supplier_Name + "'";
        ResultSet rs = obj.selectDataAndJoin(query);

        if (rs.next()) {
            // supplier name already exists, show popup message
            JOptionPane.showMessageDialog(null, "Supplier name already exists in the database.");
            return false;
        } else {
            // supplier name does not exist, perform insert
            query = "INSERT INTO Supplier (Supplier_id, name) VALUES ('" + Supplier_id + "', '" + Supplier_Name + "')";
            return obj.insertData(query);
        }

    }

    public boolean updateSupplier() throws SQLException {
        DbConnect obj = new DbConnect();
        obj.connect();
        String query = "SELECT * FROM Supplier WHERE name = '" + Supplier_Name + "'";
        ResultSet rs = obj.selectDataAndJoin(query);

        if (rs.next()) {
            // supplier name already exists, show popup message
            JOptionPane.showMessageDialog(null, "Supplier name already exists in the database.");
            return false;
        } else {
            // supplier name does not exist, perform update
            query = "UPDATE Supplier SET name = '" + Supplier_Name + "' WHERE Supplier_id = '" + Supplier_id + "'";
            return obj.insertData(query);
        }

    }

    public boolean deleteSupplier() {
        DbConnect obj = new DbConnect();
        obj.connect();
        String query = "DELETE FROM `Supplier` WHERE Supplier_id = '" + Supplier_id + "'";
        return obj.insertData(query);
    }

    public boolean checkSupplier(String location_id) {
        DbConnect obj = new DbConnect();
        obj.connect();
        String query = "SELECT Supplier_id FROM Supplier WHERE Supplier_id='" + Supplier_id + "'";
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
