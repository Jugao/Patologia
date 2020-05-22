package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.Caso;
import java.text.SimpleDateFormat;
import java.util.Date;

@ManagedBean
@RequestScoped
public class CasoRevisionesDao extends Dao {
     Date myDate = new Date();
  
    public void registrarCaso(Caso c) {

        try {
            this.conectar();
            
             String dates = new SimpleDateFormat("yyyy-MM-dd").format(myDate);
             
            String insert = "Insert into casR ( numCaso, canBloque, describloque, ubibloque, descrilamina, cantLami, ubilamina,  fechaRegis, estado,responsable, docPaciente, nombrePaciente, observacion) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = this.getCon().prepareStatement(insert);
            st.setString(1, c.getCaso().toUpperCase()); //si
            st.setInt(2, c.getCantBloque()); //si
            st.setString(3, c.getDesbloque()); //si
            st.setString(4, c.getUbibloque().toUpperCase());  //si            
            st.setString(5, c.getDeslamina()); //si
            st.setInt(6, c.getCantLamina()); //si
            st.setString(7, c.getUbilamina().toUpperCase()); //si           
            st.setString(8, dates); //si
            st.setString(9, "DISPONIBLE"); //si
            st.setString(10, "LABORATORIO");//si
            st.setString(11, c.getDocumento()); //si
            st.setString(12, c.getNombrePaciente().toUpperCase());//si
            st.setString(13, c.getObservacion());//si
            
            st.executeUpdate();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Caso Registrado"));

        } catch (SQLException ex) {
            Logger.getLogger(CasoRevisionesDao.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Error"));

        } finally {
            this.cerrar();
        }

    }

    
    public List<Caso> listar() {

        List<Caso> casos = new ArrayList<Caso>();
        
        String caso;
        int id;
        int cantBloque;
        int cantLamina;
        String fecha;        
        String responsable;
        String documento;
        String estado;
        String descrilamina;
        String describloque;
        String nombrePaciente;        
        String ubibloque;
        String ubilamina;
        String observacion;

        //caso ORDER BY idCaso DESC
        try {
            this.conectar();
            ResultSet rs;
            String select = "select idcaso,docPaciente,nombrePaciente,numcaso,canBloque,describloque,ubibloque,descrilamina,cantLami,ubilamina,fechaRegis,estado,responsable,observacion  from casR ORDER BY idCaso DESC ";
            PreparedStatement st = this.getCon().prepareStatement(select);
            rs = st.executeQuery();
            while (rs.next()) {

                Caso c;

                id = rs.getInt("idcaso");
                documento = rs.getString("docPaciente");
                nombrePaciente = rs.getString("nombrePaciente");
                caso = rs.getString("numCaso");
                descrilamina = rs.getString("descrilamina");
                cantBloque = rs.getInt("canBloque");
                describloque = rs.getString("describloque");
                cantLamina = rs.getInt("cantLami");
                fecha = rs.getString("fechaRegis");
                estado = rs.getString("estado");
                responsable = rs.getString("responsable");
                ubibloque = rs.getString("ubibloque");
                ubilamina = rs.getString("ubilamina");
                observacion = rs.getString("observacion");
                
                

                c = new Caso(id, documento, nombrePaciente, caso, cantBloque, describloque, cantLamina, descrilamina, fecha, estado, responsable, ubibloque, ubilamina, observacion);

                casos.add(c);
            }
            this.cerrar();
        } catch (SQLException ex) {
            Logger.getLogger(CasoRevisionesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return casos;
    }
    
    
//
//    public List<Caso> listarPrestamo() {
//
//       
//        List<Caso> casoslist = new ArrayList<Caso>();
//
//        String caso;
//        int cantBloque;
//        int cantLamina;
//        String fecha;
//        String prestamo;
//        String responsable;
//        String documento;
//        String nombrePaciente;
//        int id;
//
//        
//        try {
//            this.conectar();
//            ResultSet rs;
//            String select = "SELECT  idcaso, numCaso,canBloque, cantLami, fechaRegis, docPaciente, nombrePaciente,estado, responsable  from cas ORDER BY idCaso DESC ";
//            PreparedStatement st = this.getCon().prepareStatement(select);
//            rs = st.executeQuery();
//            while (rs.next()) {
//
//                Caso c;
//
//                id = rs.getInt("idcaso");
//                documento = rs.getString("docPaciente");
//                nombrePaciente = rs.getString("nombrePaciente");
//                caso = rs.getString("numCaso");
//                cantBloque = rs.getInt("canBloque");
//                cantLamina = rs.getInt("cantLami");
//                fecha = rs.getString("fechaRegis");
//                prestamo = rs.getString("estado");
//                responsable = rs.getString("responsable");
//
//                c = new Caso(id, documento, nombrePaciente, caso, cantBloque, cantLamina, fecha, prestamo, responsable);
//
//                casoslist.add(c);
//                
//            }
//            this.cerrar();
//        } catch (SQLException ex) {
//            Logger.getLogger(CasoDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return casoslist;
//    }
//    
//    
    
    

    public Caso getCasosID(Caso c) {

        System.out.println("pasa por get caso id");
        Caso cas = new Caso();
        ResultSet rs;

        int id;
        
        String documento;
        String nombrePaciente;
        String caso;
        int cantBloque;
        int cantLamina;
        String fecha;
        String responsable;
        String ubibloque;
        String ubilamina;
        String observacion;
        String descrilamina;
        String describloque;
        
        

        try {
            this.conectar();
            String select = " SELECT  idcaso, docPaciente,nombrePaciente, numCaso, canBloque, describloque, cantLami, descrilamina, fechaRegis, responsable,  ubibloque, ubilamina, observacion  from casR where idcaso=?";
            PreparedStatement st = this.getCon().prepareStatement(select);
            st.setInt(1, c.getId());
            rs = st.executeQuery();            

            while (rs.next()) {               
                            
                id = rs.getInt("idcaso");//idcaso
                documento = rs.getString("docPaciente");
                nombrePaciente = rs.getString("nombrePaciente");
                caso = rs.getString("numCaso");                
                cantBloque = rs.getInt("canBloque");
                describloque = rs.getString("describloque");
                cantLamina = rs.getInt("cantLami");
                descrilamina = rs.getString("descrilamina");
                fecha = rs.getString("fechaRegis");               
                responsable = rs.getString("responsable");
                ubibloque = rs.getString("ubibloque");
                ubilamina = rs.getString("ubilamina");
                observacion = rs.getString("observacion");
                                
                cas = new Caso(id, documento, nombrePaciente, caso, cantBloque, describloque, cantLamina, descrilamina, fecha, responsable, ubibloque, ubilamina, observacion);
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(CasoRevisionesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cas;
    }

    
    
    
//    public Caso getCasosIDpresta(Caso c) { // CASO QUE TRAE EL OBJECTO SLECIONADO
//
//        System.out.println("pasa casos id ");
//        Caso cas = new Caso();
//        ResultSet rs;
//
//        int id;
//        String documento;
//        String nombrePaciente;
//        String caso;
//        int cantBloque;
//        int cantLamina;
//        String fecha;
//        String prestamo;
//        String responsable;
//
//        try {
//            this.conectar();
//            String select = " SELECT  idcaso, docPaciente, nombrePaciente, numCaso,canBloque, cantLami, fechaRegis, estado, responsable from cas where idcaso=?";
//            PreparedStatement st = this.getCon().prepareStatement(select);
//            st.setInt(1, c.getId());
//            rs = st.executeQuery();
// 
//            while (rs.next()) {
//
//                id = rs.getInt("idcaso");
//                documento = rs.getString("docPaciente");
//                nombrePaciente = rs.getString("nombrePaciente"); // orden de los contructores
//                caso = rs.getString("numCaso");
//                cantBloque = rs.getInt("canBloque");
//                cantLamina = rs.getInt("cantLami");
//                fecha = rs.getString("fechaRegis");
//                prestamo = rs.getString("estado");
//                responsable = rs.getString("responsable");
//
//                //cas = new Caso(documento, nombrePaciente, caso, cantBloque, cantLamina, fecha, prestado, respPrestamo);  , prestado, respPrestamo
//                cas = new Caso(id, documento, nombrePaciente, caso, cantBloque, cantLamina, fecha, prestamo, responsable);
//
//                            }
//        } catch (SQLException ex) {
//            Logger.getLogger(CasoDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return cas;
//    }
    
    
    
    

    public void modificarpresta(Caso c) {

        try {
            this.conectar();

            /*String update = "update caso set "
                    nombrePaciente=?", numCaso=?, canBloque=?, 
            cantLami=?, fechaRegis=?, Prestado=?, RespPrestamo=? where docPaciente=?";
            
             */
            String update = "update casR set estado=?, RespPrestamo=?   WHERE idcaso=?";

            PreparedStatement st = this.getCon().prepareStatement(update);

            
            st.setString(1, c.getPrestamo().toUpperCase());
            st.setString(2, c.getResponsable().toUpperCase());
            st.setInt(3, c.getId());
            
            System.out.println("hace update");

       
            st.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(CasoRevisionesDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.cerrar();
        }
    }
    
    
    
    public void modificar(Caso c) {

        try {
            this.conectar();

           
            String update = "update casR set nombrePaciente=?, docPaciente=?, numCaso=?, canBloque=?, cantLami=?, fechaRegis=?,ubibloque=?, ubilamina=?, descrilamina=?, describloque=?  where idcaso=?";

            PreparedStatement st = this.getCon().prepareStatement(update);

            st.setString(1, c.getNombrePaciente().toUpperCase());
            st.setString(2, c.getDocumento());
            st.setString(3, c.getCaso());
            st.setInt(4, c.getCantBloque());
            st.setInt(5, c.getCantLamina());
            st.setString(6, c.getFecha());
            st.setString(7, c.getUbibloque());
            st.setString(8, c.getUbilamina()); 
            st.setString(9,c.getDeslamina());
            st.setString(10,c.getDesbloque());
            st.setInt(11, c.getId());
                             
            st.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(CasoRevisionesDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.cerrar();
        }
    }
    
    
    
    

    public void eliminar(Caso c) {
        try {
            this.conectar();
            String delete = "delete from casR where idcaso=?";
            
            PreparedStatement st = this.getCon().prepareStatement(delete);
            st.setInt(1, c.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CasoRevisionesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
       public void consultarcaso(Caso c) {

        try {
            this.conectar();
            ResultSet rs;
            String select = "select * from casR where numcaso=? ";
            PreparedStatement st = this.getCon().prepareStatement(select);
            st.setString(1, c.getCaso());
            rs = st.executeQuery();
            
            if(rs.next()){
         
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Caso ya ingresado en la base de datos"));
            }else {
            
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok", "Caso sin Ingreso en la base de datos"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PrestaDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            this.cerrar();
        }

    }
       
       
       
       
       
     //SELECT idcaso,docPaciente,nombrePaciente,numcaso,canBloque,describloque,ubibloque,descrilamina,cantLami,ubilamina,fechaRegis,estado,responsable,observacion FROM dbo.cas order by numcaso desc  
       
   
    public List<Caso> listarOrden() {

        List<Caso> casos = new ArrayList<Caso>();
        
        String caso;
        int id;
        int cantBloque;
        int cantLamina;
        String fecha;        
        String responsable;
        String documento;
        String estado;
        String descrilamina;
        String describloque;
        String nombrePaciente;        
        String ubibloque;
        String ubilamina;
        String observacion;

        //caso ORDER BY idCaso DESC
        try {
            this.conectar();
            ResultSet rs;
            String select = "SELECT idcaso,docPaciente,nombrePaciente,numcaso,canBloque,describloque,ubibloque,descrilamina,cantLami,ubilamina,fechaRegis,estado,responsable,observacion FROM dbo.casR order by numcaso desc";
            PreparedStatement st = this.getCon().prepareStatement(select);
            rs = st.executeQuery();
            while (rs.next()) {

                Caso c;

                id = rs.getInt("idcaso");
                documento = rs.getString("docPaciente");
                nombrePaciente = rs.getString("nombrePaciente");
                caso = rs.getString("numCaso");
                descrilamina = rs.getString("descrilamina");
                cantBloque = rs.getInt("canBloque");
                describloque = rs.getString("describloque");
                cantLamina = rs.getInt("cantLami");
                fecha = rs.getString("fechaRegis");
                estado = rs.getString("estado");
                responsable = rs.getString("responsable");
                ubibloque = rs.getString("ubibloque");
                ubilamina = rs.getString("ubilamina");
                observacion = rs.getString("observacion");
                
                

                c = new Caso(id, documento, nombrePaciente, caso, cantBloque, describloque, cantLamina, descrilamina, fecha, estado, responsable, ubibloque, ubilamina, observacion);

                casos.add(c);
            }
            this.cerrar();
        } catch (SQLException ex) {
            Logger.getLogger(CasoRevisionesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return casos;
    }
         
       
    
    
    
    
    
    

}
