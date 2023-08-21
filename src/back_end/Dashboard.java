/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/**
 *
 * @author prasi
 */
public class Dashboard {
    public int getCustomer(){
        DbConnect obj = new DbConnect();
        obj.connect();
        
        int count = 0;
    try {
        String query = "SELECT COUNT(*) FROM users WHERE role ='Customer'";
        ResultSet rs = obj.selectDataAndJoin(query);
        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (SQLException e) {
        System.out.println("error");
    }
    System.out.println(count);
            
    
    return count;
        
}
    public int getEmployee(){
        DbConnect obj = new DbConnect();
        obj.connect();
        
        int count = 0;
    try {
        String query = "SELECT COUNT(*) FROM users WHERE role ='Employee'";
        ResultSet rs = obj.selectDataAndJoin(query);
        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (SQLException e) {
        System.out.println("error");
    }
    System.out.println(count);
            
    
    return count;
        
}
    public int getCategory(){
        DbConnect obj = new DbConnect();
        obj.connect();
        
        int count = 0;
    try {
        String query = "SELECT COUNT(*) FROM categories";
        ResultSet rs = obj.selectDataAndJoin(query);
        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (SQLException e) {
        System.out.println("error");
    }
    System.out.println(count);
            
    
    return count;
        
}
    public int getProduct(){
        DbConnect obj = new DbConnect();
        obj.connect();
        
        int count = 0;
    try {
        String query = "SELECT COUNT(*) FROM products";
        ResultSet rs = obj.selectDataAndJoin(query);
        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (SQLException e) {
        System.out.println("error");
    }
    System.out.println(count);
            
    
    return count;
        
}
    public int getSupplier(){
        DbConnect obj = new DbConnect();
        obj.connect();
        
        int count = 0;
    try {
        String query = "SELECT COUNT(*) FROM supplier";
        ResultSet rs = obj.selectDataAndJoin(query);
        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (SQLException e) {
        System.out.println("error");
    }
    System.out.println(count);
            
    
    return count;
        
}

}

