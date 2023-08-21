/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end;

/**
 *
 * @author prasi
 */
public class Report {
    
    public void addReportDetails(String query, int userId){
        DbConnect obj = new DbConnect();
        obj.connect();
         String insertQuery = "INSERT INTO report (Description, user_id) VALUES ('" + query + "', '"+userId+"')";
         System.out.println(insertQuery);
        Boolean check = obj.insertData(insertQuery);
        User msg = new User();
        if(!check){
            msg.popmsg("Something went wrong");
        }
    } 
}
