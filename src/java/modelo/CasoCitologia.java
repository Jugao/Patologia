
package modelo;

import java.io.Serializable;


public class CasoCitologia implements Serializable{


   private int id;
   private String documento;
   private String nombrePaciente; 
   private String caso ;   
   private int cantBloque;
   private String desbloque;
   private int cantLamina;
   private String deslamina;
   private String fecha;
   private String prestamo;
   private String responsable;
   private String ubibloque;
   private String ubilamina;

    public String getUbibloque() {
        return ubibloque;
    }

    public void setUbibloque(String ubibloque) {
        this.ubibloque = ubibloque;
    }

    public String getUbilamina() {
        return ubilamina;
    }

    public void setUbilamina(String ubilamina) {
        this.ubilamina = ubilamina;
    }
   private String Observacion;

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }
   
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getCaso() {
        return caso;
    }

    public void setCaso(String caso) {
        this.caso = caso;
    }

    public int getCantBloque() {
        return cantBloque;
    }

    public void setCantBloque(int cantBloque) {
        this.cantBloque = cantBloque;
    }

    public int getCantLamina() {
        return cantLamina;
    }

    public void setCantLamina(int cantLamina) {
        this.cantLamina = cantLamina;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(String prestamo) {
        this.prestamo = prestamo;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getDesbloque() {
        return desbloque;
    }

    public void setDesbloque(String desbloque) {
        this.desbloque = desbloque;
    }

    public String getDeslamina() {
        return deslamina;
    }

    public void setDeslamina(String deslamina) {
        this.deslamina = deslamina;
    }

    
       
   

    public CasoCitologia(int id, String documento, String nombrePaciente, String caso, int cantBloque, String desbloque, int cantLamina, String deslamina, String fecha, String responsable, String ubibloque, String ubilamina, String Observacion) {
        this.id = id;
        this.documento = documento;
        this.nombrePaciente = nombrePaciente;
        this.caso = caso;
        this.cantBloque = cantBloque;
        this.desbloque = desbloque;
        this.cantLamina = cantLamina;
        this.deslamina = deslamina;
        this.fecha = fecha;
        this.responsable = responsable;
        this.ubibloque = ubibloque;
        this.ubilamina = ubilamina;
        this.Observacion = Observacion;
    }

    
       public CasoCitologia(int id, String documento, String nombrePaciente, String caso, int cantBloque, int cantLamina, String fecha, String prestamo, String responsable, String ubibloque, String ubilamina, String Observacion) {
        this.id = id;
        this.documento = documento;
        this.nombrePaciente = nombrePaciente;
        this.caso = caso;
        this.cantBloque = cantBloque;
        this.cantLamina = cantLamina;
        this.fecha = fecha;
        this.prestamo = prestamo;
        this.responsable = responsable;
        this.ubibloque = ubibloque;
        this.ubilamina = ubilamina;
        this.Observacion = Observacion;
    }


    
    
    
    
    public CasoCitologia(int id, String documento, String nombrePaciente, String caso, int cantBloque, String desbloque, int cantLamina, String deslamina, String fecha, String prestamo, String responsable, String ubibloque, String ubilamina, String Observacion) {
        this.id = id;
        this.documento = documento;
        this.nombrePaciente = nombrePaciente;
        this.caso = caso;
        this.cantBloque = cantBloque;
        this.desbloque = desbloque;
        this.cantLamina = cantLamina;
        this.deslamina = deslamina;
        this.fecha = fecha;
        this.prestamo = prestamo;
        this.responsable = responsable;
        this.ubibloque = ubibloque;
        this.ubilamina = ubilamina;
        this.Observacion = Observacion;
    }
 
    
    

    public CasoCitologia() {
    }



    
}
