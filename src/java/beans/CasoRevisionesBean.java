package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;

import dao.CasoRevisionesDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import modelo.Caso;

@ManagedBean
@ViewScoped
public class CasoRevisionesBean implements Serializable {

    List<Caso> casos = new ArrayList<Caso>();

    List<Caso> casosprestamo = new ArrayList<Caso>();

    public List<Caso> getCasosprestamo() {
        return casosprestamo;
    }

    public void setCasosprestamo(List<Caso> casosprestamo) {
        this.casosprestamo = casosprestamo;
    }

    List<Caso> casos2 = new ArrayList<Caso>();

    public List<Caso> getCasos2() {
        return casos2;
    }

    public void setCasos2(List<Caso> casos2) {
        this.casos2 = casos2;
    }

    Caso c = new Caso();

    public List<Caso> getCasos() {
        return casos;
    }

    public void setCasos(List<Caso> casos) {
        this.casos = casos;
    }

    public Caso getC() {
        return c;
    }

    public void setC(Caso c) {
        this.c = c;
    }

    public CasoRevisionesBean() {

    }

    public void registrar() {
        CasoRevisionesDao dao = new CasoRevisionesDao();
        dao.registrarCaso(c); // metodo que  guarda en la base de datos  

        this.listar();
        c = new Caso();

    }

    public void leerId(Caso c) {
        CasoRevisionesDao dao = new CasoRevisionesDao();
        Caso temp = dao.getCasosID(c);
        if (temp != null) {
            this.c = temp;
        }

        System.out.println(" pasa por leer id ");
    }

    @PostConstruct
    public void init() {
        this.listar();
        // this.listarprestamo(); // anotacion para inicializar no lista mas los casos

    }

    public void listar() {
        CasoRevisionesDao dao = new CasoRevisionesDao();
        casos = dao.listar(); //  llena la lista casos con la anotacion posconstruc
        casos2 = dao.listarOrden();

    }

    public void modificar() {
        CasoRevisionesDao dao = new CasoRevisionesDao();
        dao.modificar(c);
        this.listar();

    }

    public void eliminar(Caso c) {
        CasoRevisionesDao dao = new CasoRevisionesDao();
        dao.eliminar(c);
        this.listar();
        System.out.println("pasa bean");

    }

    public void consultarCaso() {

        CasoRevisionesDao dao = new CasoRevisionesDao();
        dao.consultarcaso(c);
        this.listar();

    }

}
