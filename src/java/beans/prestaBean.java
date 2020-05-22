package beans;

import modelo.prestamo;
import dao.PrestaDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class prestaBean  implements Serializable{

    prestamo p = new prestamo();
    
 
    List<prestamo> prestamos = new ArrayList<prestamo>();

    public List<prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public prestamo getP() {
        return p;
    }

    public void setP(prestamo p) {
        this.p = p;
    }

    public void registrar() {
       
        PrestaDao dao = new PrestaDao();        
        dao.registrarPrestamo(p);
        this.listar();
        
        p = new prestamo();
        
        System.out.println("datos"+p.getCaso());

    }
    
    
     
    @PostConstruct
    public void init() {
       
       this.listar();

    }   
    
    
    
      public void listar() {
       PrestaDao dao = new PrestaDao();   
        prestamos = dao.listar(); //  llena la lista casos con la anotacion posconstruc
          //System.out.println("pasa por listar ");    
    }
    
    
     public void leerId(prestamo p) {
         PrestaDao dao = new PrestaDao();
        prestamo temp = dao.getCasosIDpresta(p);
        if (temp != null) {
            this.p = temp;
        }
        
        System.out.println(" pasa por leer id ");
    }
    
     
        
    public void modificar(){
    PrestaDao dao = new PrestaDao();
    dao.modificarpresta(p);
    this.listar();
        System.out.println("pasa por modificar bean");
    }
     
     
    
    
    

}
