package modelo;

import java.io.Serializable;

public class prestamo implements Serializable {

   
    
    private String caso;
    private int cantBloque;
    private int cantLamina;
    private String estado;
    private String responsable;
    private String ubibloque;
    private String ubilamina;
    private String fechapresta;   
    private String fechadev;
    private String Observacion;
     private String desbloque;
      private String deslamina;



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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

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

    public String getFechapresta() {
        return fechapresta;
    }

    public void setFechapresta(String fechapresta) {
        this.fechapresta = fechapresta;
    }

    public String getFechadev() {
        return fechadev;
    }

    public void setFechadev(String fechadev) {
        this.fechadev = fechadev;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
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
    
    

    public prestamo() {
    }

    public prestamo( String caso, int cantBloque, int cantLamina, String estado, String responsable, String ubibloque, String ubilamina, String fechapresta, String fechadev, String Observacion, String desbloque, String deslamina) {
        
        this.caso = caso;
        this.cantBloque = cantBloque;
        this.cantLamina = cantLamina;
        this.estado = estado;
        this.responsable = responsable;
        this.ubibloque = ubibloque;
        this.ubilamina = ubilamina;
        this.fechapresta = fechapresta;
        this.fechadev = fechadev;
        this.Observacion = Observacion;
        this.desbloque = desbloque;
        this.deslamina = deslamina;
    }

 
 
}
