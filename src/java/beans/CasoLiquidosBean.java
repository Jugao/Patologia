package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;

import dao.CasoDaoLiquidos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import modelo.Caso;

@ManagedBean
@ViewScoped
public class CasoLiquidosBean implements Serializable {

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

    public CasoLiquidosBean() {

    }

    public void registrar() {
        CasoDaoLiquidos dao = new CasoDaoLiquidos();
        dao.registrarCaso(c); // metodo que  guarda en la base de datos  

        this.listar();
        c = new Caso();

    }

    public void leerId(Caso c) {
        CasoDaoLiquidos dao = new CasoDaoLiquidos();
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
        CasoDaoLiquidos dao = new CasoDaoLiquidos();
        casos = dao.listar(); //  llena la lista casos con la anotacion posconstruc
        casos2 = dao.listarOrden();

    }

    public void modificar() {
        CasoDaoLiquidos dao = new CasoDaoLiquidos();
        dao.modificar(c);
        this.listar();

    }

    public void eliminar(Caso c) {
        CasoDaoLiquidos dao = new CasoDaoLiquidos();
        dao.eliminar(c);
        this.listar();
        System.out.println("pasa bean");

    }

    public void consultarCaso() {

        CasoDaoLiquidos dao = new CasoDaoLiquidos();
        dao.consultarcaso(c);
        this.listar();

    }

}
