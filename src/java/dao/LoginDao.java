package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao extends Dao {

    Dao conexion = new Dao();

    // ,String rol
    public static boolean validate(String user, String password) {
        Connection con = null;
        PreparedStatement ps = null;
//        System.out.println("pasa validate 1 dao");
//        System.out.println("user " + user);
//        System.out.println("passwordr " + password);

        try {

            con = Daologin.getConnection();
            ps = con.prepareStatement("select doc, nom, pass from usuario where doc = ? and pass = ? and Rol = 'A'  ");
            ps.setString(1, user);
            ps.setString(2, password);
            //ps.setString(3, rol);            

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //result found, means valid inputs
                return true;
            }

        } catch (SQLException ex) {
//            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            // DaoLogin.close(con);
            Daologin.close(con);
        }
        return false;

    }

    //String rol
    public static boolean validateEmple(String user, String password) {
        Connection con = null;
        PreparedStatement ps = null;
//        System.out.println("pasa validateemple 2 dao");
        try {
            con = Daologin.getConnection();
            ps = con.prepareStatement("select doc, Nom, pass from usuario where doc =? and pass =? and Rol = 'C' ");
            ps.setString(1, user);
            ps.setString(2, password);
            // ps.setString(3, rol); 
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //result found, means valid inputs
                return true;
            }

        } catch (SQLException ex) {
//            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            Daologin.close(con);
        }
        return false;
    }

    public static String devuelveusuario(String user, String password) {
        Connection con = null;
        PreparedStatement ps = null;

        String Nombre = null;

        try {
            con = Daologin.getConnection();
            ps = con.prepareStatement("select doc, nom, pass from usuario where doc = ? and pass = ? and rol = 'C'  ");
            ps.setString(1, user);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Nombre = rs.getString("nom");
//                System.out.println("Nombre del que loguea " + Nombre);
            }
        } catch (SQLException ex) {
//            System.out.println("Login error -->" + ex.getMessage());
        } finally {
            // DaoLogin.close(con);
            Daologin.close(con);
        }
        return String.valueOf(Nombre);
    };
}
