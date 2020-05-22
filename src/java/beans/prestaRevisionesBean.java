package beans;

import modelo.prestamo;
import dao.PrestaRevisionesDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class prestaRevisionesBean  implements Serializable{

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
       
        PrestaRevisionesDao dao = new PrestaRevisionesDao();        
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
       PrestaRevisionesDao dao = new PrestaRevisionesDao();   
        prestamos = dao.listar(); //  llena la lista casos con la anotacion posconstruc
          System.out.println("pasa por listar ");    
    }
    
    
     public void leerId(prestamo p) {
         PrestaRevisionesDao dao = new PrestaRevisionesDao();
        prestamo temp = dao.getCasosIDpresta(p);
        if (temp != null) {
            this.p = temp;
        }
        
        System.out.println(" pasa por leer id ");
    }
    
     
        
    public void modificar(){
    PrestaRevisionesDao dao = new PrestaRevisionesDao();
    dao.modificarpresta(p);
    this.listar();
        System.out.println("pasa por modificar bean");
    }
     
     
    
    
    

}
