package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import modelo.prestamo;

@ManagedBean
@RequestScoped
public class PrestaCitologiaDao extends Dao {

    public void registrarPrestamo(prestamo p) {
        Date myDate = new Date();
        String dates = new SimpleDateFormat("yyyy-MM-dd").format(myDate);
        try {
            this.conectar();
            String insert = "insert into prestamoC ( numcaso,canBloque, cantLami, fecha_prestamo, fecha_devolucion, ubibloque,ubilamina,estado,respPrestamo, observacion, describloque,descrilamina ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

            System.out.println("pasa  caso " + p.getCaso());
            PreparedStatement st = this.getCon().prepareStatement(insert);

            st.setString(1, p.getCaso()); //3
            st.setInt(2, p.getCantBloque());  //4           
            st.setInt(3, p.getCantLamina()); //5                       
            st.setString(4, dates); //si
            st.setString(5, "0001-01-01"); //7
            st.setString(6, p.getUbibloque()); //8
            st.setString(7, p.getUbilamina());//9
            st.setString(8, "PRESTAMO"); //1o
            st.setString(9, p.getResponsable());//11
            st.setString(10, p.getObservacion()); //12    
            st.setString(11, p.getDesbloque()); //12
            st.setString(12, p.getDeslamina()); //12             

            String update2 = "update casC set  estado=? where numCaso=?";

            PreparedStatement st2 = this.getCon().prepareStatement(update2);

            st2.setString(1, "PRESTAMO");

            st2.setString(2, p.getCaso());

            st2.executeUpdate();

            st.executeUpdate();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Prestamo Registrado"));

        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Prestado o Nulo, verifique por favor"));

        } finally {
            this.cerrar();
        }

    }

    public List<prestamo> listar() {

        List<prestamo> prestamos = new ArrayList<prestamo>();

        String numcaso;
        int canBloque;
        int cantLami;
        String fecha_prestamo;
        String fecha_devolucion;
        String ubibloque;
        String ubilamina;
        String estado;
        String respPrestamo;
        String observacion;
        String descrilamina;
        String describloque;

        try {
            this.conectar();
            ResultSet rs;
            String select = "select numCaso, canBloque, cantLami,fecha_prestamo, fecha_devolucion, ubibloque, ubilamina, estado,respPrestamo, describloque, descrilamina, observacion from prestamoC ORDER BY idpres DESC ";
            PreparedStatement st = this.getCon().prepareStatement(select);
            rs = st.executeQuery();
            while (rs.next()) {

                prestamo p;

                numcaso = rs.getString("numcaso");
                canBloque = rs.getInt("canBloque");
                cantLami = rs.getInt("cantLami");
                estado = rs.getString("estado");
                respPrestamo = rs.getString("respPrestamo");
                ubibloque = rs.getString("ubibloque");
                ubilamina = rs.getString("ubilamina");
                fecha_prestamo = rs.getString("fecha_prestamo");
                fecha_devolucion = rs.getString("fecha_devolucion");
                observacion = rs.getString("observacion");
                describloque = rs.getString("describloque");
                descrilamina = rs.getString("descrilamina");

                p = new prestamo(numcaso, canBloque, cantLami, estado, respPrestamo, ubibloque, ubilamina, fecha_prestamo, fecha_devolucion, observacion, describloque, descrilamina);

                prestamos.add(p);

            }
            this.cerrar();
        } catch (SQLException ex) {
            Logger.getLogger(CasoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prestamos;
    }

    public prestamo getCasosIDpresta(prestamo p) { // CASO QUE TRAE EL OBJECTO SLECIONADO

        prestamo pres = new prestamo();
        ResultSet rs;

        String numcaso;
        int canBloque;
        int cantLami;
        String fecha_prestamo;
        String fecha_devolucion;
        String ubibloque;
        String ubilamina;
        String estado;
        String respPrestamo;
        String observacion;
        String descrilamina;
        String describloque;

        try {
            this.conectar();
            String select = "select  numCaso, canBloque, cantLami,fecha_prestamo, fecha_devolucion, ubibloque, ubilamina, estado,respPrestamo, observacion,describloque,descrilamina from prestamoC where numCaso=?";
            PreparedStatement st = this.getCon().prepareStatement(select);
            st.setString(1, p.getCaso());
            rs = st.executeQuery();

            System.out.println(" caso que le pasamos " + p.getCaso());

            while (rs.next()) {

                numcaso = rs.getString("numCaso");
                canBloque = rs.getInt("canBloque");
                cantLami = rs.getInt("cantLami");
                fecha_prestamo = rs.getString("fecha_prestamo");
                fecha_devolucion = rs.getString("fecha_devolucion");
                ubibloque = rs.getString("ubibloque");
                ubilamina = rs.getString("ubilamina");
                estado = rs.getString("estado");
                respPrestamo = rs.getString("respPrestamo");
                observacion = rs.getString("observacion");
                describloque = rs.getString("describloque");
                descrilamina = rs.getString("descrilamina");

                pres = new prestamo(numcaso, canBloque, cantLami, estado, respPrestamo, ubibloque, ubilamina, fecha_prestamo, fecha_devolucion, observacion, describloque, descrilamina);

            }
        } catch (SQLException ex) {
            Logger.getLogger(PrestaCitologiaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pres;
    }

    public void modificarpresta(prestamo p) {

        try {
            this.conectar();

            String estado = p.getEstado();

            System.out.println("estado que recibe" + estado);

            if (estado.equals("DISPONIBLE") || estado.equals("EXTRAVIADO")) {

                String update = "update prestamoC set canBloque=?, cantLami=?, fecha_devolucion=?, estado=?, respPrestamo=?, observacion=?, describloque=?, descrilamina=? where numCaso=?";

                PreparedStatement st = this.getCon().prepareStatement(update);

                st.setInt(1, p.getCantBloque());
                st.setInt(2, p.getCantLamina());
                st.setString(3, p.getFechadev());
                st.setString(4, p.getEstado());
                st.setString(5, p.getResponsable());
                st.setString(6, p.getObservacion());
                st.setString(7, p.getDesbloque());
                st.setString(8, p.getDeslamina());
                st.setString(9, p.getCaso());

                st.executeUpdate();

                String update2 = "update casC set  estado=? where numCaso=?";

                PreparedStatement st2 = this.getCon().prepareStatement(update2);

                st2.setString(1, p.getEstado());

                st2.setString(2, p.getCaso());

                st2.executeUpdate();

                System.out.println(" UPDATE 2 " + update2);

            } else if (p.getEstado().equals("PRESTAMO")) {

                String update = "update prestamoC set canBloque=?, cantLami=?, fecha_devolucion=?, estado=?, respPrestamo=?, observacion=?, describloque=?, descrilamina=? where numCaso=?";

                PreparedStatement st = this.getCon().prepareStatement(update);

                st.setInt(1, p.getCantBloque());
                st.setInt(2, p.getCantLamina());
                st.setString(3, p.getFechadev());
                st.setString(4, p.getEstado());
                st.setString(5, p.getResponsable());
                st.setString(6, p.getObservacion());
                st.setString(7, p.getDesbloque());
                st.setString(8, p.getDeslamina());
                st.setString(9, p.getCaso());

                st.executeUpdate();

                String update2 = "update casC set  estado=? where numCaso=?";

                PreparedStatement st2 = this.getCon().prepareStatement(update2);

                st2.setString(1, p.getEstado());
                st2.setString(2, p.getCaso());

                st2.executeUpdate();

            }

        } catch (SQLException ex) {
            Logger.getLogger(CasoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.cerrar();
        }
    }

}
