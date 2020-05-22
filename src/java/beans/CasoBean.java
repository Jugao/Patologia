package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import modelo.Caso;
import dao.CasoDao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@ManagedBean
@ViewScoped
public class CasoBean implements Serializable {

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

    public CasoBean() {

    }

    public void registrar() {
        CasoDao dao = new CasoDao();
        dao.registrarCaso(c); // metodo que  guarda en la base de datos  

        this.listar();
        c = new Caso();

    }

    public void leerId(Caso c) {
        CasoDao dao = new CasoDao();
        Caso temp = dao.getCasosID(c);
        if (temp != null) {
            this.c = temp;
        }

//        System.out.println(" pasa por leer id ");
    }

    @PostConstruct
    public void init() {
        this.listar();
        // this.listarprestamo(); // anotacion para inicializar no lista mas los casos

    }

    public void listar() {
        CasoDao dao = new CasoDao();
        casos = dao.listar(); //  llena la lista casos con la anotacion posconstruc
        casos2 = dao.listarOrden();

    }

    public void modificar() {
        CasoDao dao = new CasoDao();
        dao.modificar(c);
        this.listar();

    }

    public void eliminar(Caso c) {
        CasoDao dao = new CasoDao();
        dao.eliminar(c);
        this.listar();
        System.out.println("pasa bean");

    }

    public void consultarCaso() {

        CasoDao dao = new CasoDao();
        dao.consultarcaso(c);
        this.listar();

    }

}
