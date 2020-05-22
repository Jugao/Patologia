package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Daologin {

    public static Connection getConnection() {
        try {
            
              Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");   //S7LB4E6         
             Connection con = DriverManager.getConnection("jdbc:sqlserver://192.168.203.123;databaseName=spatology;user=sa;password=admin;"); 
            
                     
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pathology?user=root&password=");

                
            
//            System.out.println("Connected to database OK");
            return con;
        } catch (ClassNotFoundException | SQLException ex) {
//            System.out.println("Database.getConnection() Error h-->" + ex.getMessage());
            return null;
        }
    }

    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }
}
