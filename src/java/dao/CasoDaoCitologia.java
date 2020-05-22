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
import modelo.CasoCitologia;
import java.text.SimpleDateFormat;
import java.util.Date;

@ManagedBean
@RequestScoped
public class CasoDaoCitologia extends Dao {
     Date myDate = new Date();
  
    public void registrarCaso(CasoCitologia c) {

        try {
            this.conectar();
            
             String dates = new SimpleDateFormat("yyyy-MM-dd").format(myDate);
             
            String insert = "Insert into casC ( numCaso, canBloque, describloque, ubibloque, descrilamina, cantLami, ubilamina,  fechaRegis, estado,responsable, docPaciente, nombrePaciente, observacion) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
            Logger.getLogger(CasoDaoCitologia.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Error"));

        } finally {
            this.cerrar();
        }

    }

    
    public List<CasoCitologia> listar() {

        List<CasoCitologia> casos = new ArrayList<CasoCitologia>();
        
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
            String select = "select idcaso,docPaciente,nombrePaciente,numcaso,canBloque,describloque,ubibloque,descrilamina,cantLami,ubilamina,fechaRegis,estado,responsable,observacion  from casC ORDER BY idCaso DESC ";
            PreparedStatement st = this.getCon().prepareStatement(select);
            rs = st.executeQuery();
            while (rs.next()) {

                CasoCitologia c;

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
                
                

                c = new CasoCitologia(id, documento, nombrePaciente, caso, cantBloque, describloque, cantLamina, descrilamina, fecha, estado, responsable, ubibloque, ubilamina, observacion);

                casos.add(c);
            }
            this.cerrar();
        } catch (SQLException ex) {
            Logger.getLogger(CasoDaoCitologia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return casos;
    }
    
    

    

    public CasoCitologia getCasosID(CasoCitologia c) {

        System.out.println("pasa por get caso id");
        CasoCitologia cas = new CasoCitologia();
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
            String select = " SELECT  idcaso, docPaciente,nombrePaciente, numCaso, canBloque, describloque, cantLami, descrilamina, fechaRegis, responsable,  ubibloque, ubilamina, observacion  from casC where idcaso=?";
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
                                
                cas = new CasoCitologia(id, documento, nombrePaciente, caso, cantBloque, describloque, cantLamina, descrilamina, fecha, responsable, ubibloque, ubilamina, observacion);
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(CasoDaoCitologia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cas;
    }

    
  
    

    public void modificarpresta(CasoCitologia c) {

        try {
            this.conectar();

            String update = "update casC set estado=?, RespPrestamo=?   WHERE idcaso=?";

            PreparedStatement st = this.getCon().prepareStatement(update);

            
            st.setString(1, c.getPrestamo().toUpperCase());
            st.setString(2, c.getResponsable().toUpperCase());
            st.setInt(3, c.getId());
            
            System.out.println("hace update");

       
            st.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(CasoDaoCitologia.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.cerrar();
        }
    }
    
    
    
    public void modificar(CasoCitologia c) {

        try {
            this.conectar();

           
            String update = "update casC set nombrePaciente=?, docPaciente=?, numCaso=?, canBloque=?, cantLami=?, fechaRegis=?,ubibloque=?, ubilamina=?, descrilamina=?, describloque=?  where idcaso=?";

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
            Logger.getLogger(CasoDaoCitologia.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.cerrar();
        }
    }
    
    
    
    

    public void eliminar(CasoCitologia c) {
        try {
            this.conectar();
            String delete = "delete from casC where idcaso=?";
            
            PreparedStatement st = this.getCon().prepareStatement(delete);
            st.setInt(1, c.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CasoDaoCitologia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
       public void consultarcaso(CasoCitologia c) {

        try {
            this.conectar();
            ResultSet rs;
            String select = "select * from casC where numcaso=? ";
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
       
   
    public List<CasoCitologia> listarOrden() {

        List<CasoCitologia> casos = new ArrayList<CasoCitologia>();
        
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
            String select = "SELECT idcaso,docPaciente,nombrePaciente,numcaso,canBloque,describloque,ubibloque,descrilamina,cantLami,ubilamina,fechaRegis,estado,responsable,observacion FROM dbo.casC order by numcaso desc";
            PreparedStatement st = this.getCon().prepareStatement(select);
            rs = st.executeQuery();
            while (rs.next()) {

                CasoCitologia c;

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
                

                c = new CasoCitologia(id, documento, nombrePaciente, caso, cantBloque, describloque, cantLamina, descrilamina, fecha, estado, responsable, ubibloque, ubilamina, observacion);

                casos.add(c);
            }
            this.cerrar();
        } catch (SQLException ex) {
            Logger.getLogger(CasoDaoCitologia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return casos;
    }
        

}
