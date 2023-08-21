/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end;

/**
 *
 * @author prasi
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author prasi
 */
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConnect {

    private String url;
    private String user;
    private String password;
    private Connection con;
    static ResultSet rs;

    public DbConnect() {
        url = "jdbc:mysql://localhost:3306/ims";
        user = "root";
        password = "";
    }
    public void connect() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("connect established");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Connection getConnection() {
        return con;
    }

    //Return the value obtain from database table
    public ResultSet selectDataAndJoin(String query_statement) {
        try {
            Statement st = con.createStatement();
            String query = query_statement;
            System.out.println(query);
            rs = st.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    //Method to insert data in database
    public boolean insertData(String query_statement) {
        try (Statement st = con.createStatement()) {
            String query = query_statement;
            st.executeUpdate(query);
            System.out.println("Data inserted successfully");
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    //Method to update data in database
    public boolean updateData(String query_statement) {
        try {
            Statement st = con.createStatement();
            String query = query_statement;
            st.executeUpdate(query);
            System.out.println("Data updated successfully");
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    //Method to delete data in database
    public void deleteData(String query_statement) {
        try {
            Statement st = con.createStatement();
            String query = query_statement;
            st.executeUpdate(query);
            System.out.println("Data deleted successfully");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int getUserIdFromUsername(String username) throws SQLException {
        int userId = -1;
        PreparedStatement stmt = con.prepareStatement("SELECT user_id FROM users WHERE Username = ?");
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            userId = rs.getInt("user_id");
        }
        rs.close();
        stmt.close();
        return userId;
    }

    //Method to close the database
    public void close() throws SQLException {
        if (con != null) {
            con.close();
        }
    }

//    public com.mysql.jdbc.PreparedStatement prepareStatement(String query) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return con.prepareStatement(sql);
    }

    public ResultSet executeQuery(String select_option_name_FROM_options_table) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
