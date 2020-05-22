package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import dao.CasoDaoCitologia;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import modelo.CasoCitologia;

@ManagedBean
@ViewScoped
public class CasoCitologiaBean implements Serializable {

    List<CasoCitologia> casos = new ArrayList<CasoCitologia>();

    List<CasoCitologia> casosprestamo = new ArrayList<CasoCitologia>();

    public List<CasoCitologia> getCasosprestamo() {
        return casosprestamo;
    }

    public void setCasosprestamo(List<CasoCitologia> casosprestamo) {
        this.casosprestamo = casosprestamo;
    }

    List<CasoCitologia> casos2 = new ArrayList<CasoCitologia>();

    public List<CasoCitologia> getCasos2() {
        return casos2;
    }

    public void setCasos2(List<CasoCitologia> casos2) {
        this.casos2 = casos2;
    }

    CasoCitologia d = new CasoCitologia();

    public CasoCitologia getD() {
        return d;
    }

    public void setD(CasoCitologia d) {
        this.d = d;
    }

     
    public List<CasoCitologia> getCasos() {
        return casos;
    }

    public void setCasos(List<CasoCitologia> casos) {
        this.casos = casos;
    }



    public CasoCitologiaBean() {

    }

    public void registrar() {
        CasoDaoCitologia daos = new CasoDaoCitologia();
       daos.registrarCaso(d);
        this.listar();
        d = new CasoCitologia();

    }

    
    public void leerId(CasoCitologia d) {
        CasoDaoCitologia dao = new CasoDaoCitologia();
        CasoCitologia temp = dao.getCasosID(d);
        if (temp != null) {
            this.d = temp;
        }

        System.out.println(" pasa por leer id ");
    }

    @PostConstruct
    public void init() {
        this.listar();
        // this.listarprestamo(); // anotacion para inicializar no lista mas los casos

    }

    public void listar() {
        CasoDaoCitologia dao = new CasoDaoCitologia();
        casos = dao.listar(); //  llena la lista casos con la anotacion posconstruc
        casos2 = dao.listarOrden();

    }

    public void modificar() {
        CasoDaoCitologia dao = new CasoDaoCitologia();
        dao.modificar(d);
        this.listar();

    }

    public void eliminar(CasoCitologia c) {
        CasoDaoCitologia dao = new CasoDaoCitologia();
        dao.eliminar(c);
        this.listar();
        System.out.println("pasa bean");

    }

    public void consultarCaso() {

        CasoDaoCitologia dao = new CasoDaoCitologia();
        dao.consultarcaso(d);
        this.listar();

    }

}
