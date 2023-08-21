/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author prasi
 */
public class Location {
    String location_id;
    String location_Name;
    String location_mobile;
    String location_Address;
    public void setLocation(String location_id, String location_Name, String location_mobile, String location_Address){
        this.location_id = location_id;
        this.location_Name=location_Name;
        this.location_mobile=location_mobile;
        this.location_Address = location_Address;
    }
    
    public boolean addLocation(){
        DbConnect obj = new DbConnect();
        obj.connect();
        String query = "INSERT INTO location (locationId, name, Address, Mobile) VALUES ('" + location_id + "', '" + location_Name + "',  '" + location_Address + "','" + location_mobile + "')";
        return obj.insertData(query);
    }
    public boolean updateLocation(){
        DbConnect obj = new DbConnect();
        obj.connect();
        String query = "UPDATE `location` SET `name` = '" + location_Name + "', `Address` = '" + location_Address + "', `Mobile` = '" + location_mobile + "' WHERE locationId = '" + location_id + "'";
        return obj.insertData(query);
    }
    public boolean deleteLocation(){
        DbConnect obj = new DbConnect();
        obj.connect();
        String query = "DELETE FROM `location` WHERE locationId = '" + location_id + "'";
        return obj.insertData(query);
    }
    public boolean checkLocation(String location_id) {
    DbConnect obj = new DbConnect();
    obj.connect();
    String query = "SELECT locationId FROM location WHERE locationId='" + location_id + "'";
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
    
    public void movement(String itemName, String location_from, String location_To){
        DbConnect obj = new DbConnect();
        obj.connect();
        String query = "SELECT location.name FROM products INNER JOIN location ON products.location_id = location.locationId WHERE products.name = '" + itemName + "'";
        ResultSet rs = obj.selectDataAndJoin(query);
        
        try {
            if(rs.next()){
                location_from = rs.getString("name");                
                if(location_from.equals(location_To)){
                // display an information message
                JOptionPane.showMessageDialog(null, "Please Choose different Locations", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                    query = "SELECT locationId FROM location WHERE name = '"+location_To+"'";
                    rs = obj.selectDataAndJoin(query);
                    while(rs.next()){
                        String id = rs.getString("locationId");
                        System.out.println(id+" "+ itemName);
                        String query1 = "UPDATE `products` SET `location_id`='"+id+"'WHERE `name` = '"+itemName+"'";
                        obj.updateData(query1);
                        JOptionPane.showMessageDialog(null, "Product has been moved from "+location_from+" to "+location_To+" successfully");
                    }                
                    
               

            }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Location.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }
    
    
}
