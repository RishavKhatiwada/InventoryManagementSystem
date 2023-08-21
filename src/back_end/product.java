/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author prasi
 */
public class product {

    public int orderProduct(String pName, String cName, String qNumber, String sNumber, String username, String Pprice) throws SQLException {
        DbConnect obj = new DbConnect();
        obj.connect();
        ResultSet rs;
        rs = obj.selectDataAndJoin("SELECT locationId from location where default_location = 1");
        String location = "";
        while (rs.next()) {
            location = rs.getString("locationId");
        }
        rs = obj.selectDataAndJoin("Select user_id from users where Username = '" + username + "'");
        String username1 = "";
        while (rs.next()) {
            username1 = rs.getString("user_id");
        }
        rs = obj.selectDataAndJoin("SELECT category_id FROM categories WHERE name = '" + cName + "'");
        String c_id = "";
        while (rs.next()) {
            c_id = rs.getString("category_id");
        }

        // First, check if the product exists in the database
        String query = "SELECT * FROM products WHERE name='" + pName + "'";
        rs = obj.selectDataAndJoin(query);
        query = "INSERT INTO products (name, quantity, category_id, supplier_id, location_id, price, user_id) "
                        + "VALUES ('" + pName + "', '" + qNumber + "', '" + c_id + "', '" + sNumber + "', '" + location + "', '" + Pprice + "', '" + username1 + "')";
                // call the insertData method with the query statement
        System.out.println(query);

        try {
            // If the product already exists, update the quantity
            if (rs.next()) {
                int currentQuantity = rs.getInt("quantity");
                int newQuantity = currentQuantity + Integer.parseInt(qNumber);

                String updateQuery = "UPDATE products SET quantity='" + newQuantity + "' WHERE name='" + pName + "'";
                obj.updateData(updateQuery);
                JOptionPane.showMessageDialog(null, "Order Placed Sucessfully", "Message", JOptionPane.INFORMATION_MESSAGE);
                return 2; // 2 means data is updated

            } // If the product doesn't exist, insert a new row into the database
            else {
                boolean inserted = obj.insertData(query);
                // check if the data was inserted successfully
                if (inserted) {
                    System.out.println("Data inserted successfully");
                    return 1;
                } else {
                    System.out.println("Failed to insert data");
                    return 0;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error");
        }
        return 0;

    }

    public int returnProdcut(String id, String name, String quantity) {

        DbConnect obj = new DbConnect();
        obj.connect();

        // First, retrieve the data for the specified product name
        String query = "SELECT * FROM products WHERE name='" + name + "'";
        ResultSet rs = obj.selectDataAndJoin(query);

        try {
            // Loop through the results to find the matching product
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                int currentQuantity = rs.getInt("quantity");
                System.out.println(currentQuantity == Integer.parseInt(quantity));
                // If the current quantity matches the specified quantity, remove the product from the database
                if (currentQuantity == Integer.parseInt(quantity)) {
                    System.out.println("It throws error");
                    String deleteQuery = "DELETE FROM products WHERE product_id='" + id + "'";
                    obj.deleteData(deleteQuery);
                    JOptionPane.showMessageDialog(null, "Product Returned", "Message", JOptionPane.INFORMATION_MESSAGE);
                    return 1;

                } // If the current quantity is greater than the specified quantity, update the quantity in the database
                else if (currentQuantity > Integer.parseInt(quantity)) {
                    int newQuantity = currentQuantity - Integer.parseInt(quantity);
                    String updateQuery = "UPDATE products SET quantity='" + newQuantity + "' WHERE product_id='" + productId + "'";
                    obj.updateData(updateQuery);
                    JOptionPane.showMessageDialog(null, "Product Returned", "Message", JOptionPane.INFORMATION_MESSAGE);
                    return 1;

                } // If the current quantity is less than the specified quantity, display an error message
                else {
                    JOptionPane.showMessageDialog(null, "Number of Product is less than you entered", "Message", JOptionPane.INFORMATION_MESSAGE);
                    return 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
