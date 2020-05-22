package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dao {

    private Connection con;

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    
      private final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
      private final String conexionBD = "jdbc:sqlserver://192.168.203.123;databaseName=spatology;user=sa;password=admin;";
      
      // private final String conexionBD = "jdbc:sqlserver://localhost:1443;databaseName=arriving;user=sa;password=zp3ak4pc5J;";
  
      //jdbc:sqlserver://192.168.200.49;databaseName=arriving
    
//    private final String conexionBD = "jdbc:mysql://localhost:3306/pathology?user=root&password=";
//    private final String driver = "com.mysql.jdbc.Driver";
   
      public void conectar() { 

        try {
            Class.forName(driver); 
            con = DriverManager.getConnection(conexionBD); //// tenemos declarada driver  conexion db  DESKTOP-2VJHJF8
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void cerrar() {
        if (con != null) {
            try {
                if (!con.isClosed()) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
