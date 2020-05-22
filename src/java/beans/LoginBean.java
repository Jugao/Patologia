package beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import dao.LoginDao;
import java.io.Serializable;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{

    private String usuario;
    private String pwd;
    private String msg;
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
    private String rol;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String Usuario) {
        this.usuario = Usuario;
    }

 

    //validate login
    public String validateUsernamePassword() {

        boolean validAdm = LoginDao.validate(nombre, pwd);
        boolean validEmp = LoginDao.validateEmple(nombre, pwd);       
        usuario = LoginDao.devuelveusuario(nombre, pwd);
//        System.out.println("validAdm  "+validAdm);
//        System.out.println("validAdm  "+validEmp);

        if (validAdm) {
            
//            System.out.println("pasa valid  login");
            HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(true);
            httpSession.setAttribute("usuario", this.usuario);
            
            return "/privado2/consulCaso";
        }else

        if (validEmp) {
//            System.out.println("pasa empleado login");

            HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(true);
            httpSession.setAttribute("usuario", this.usuario);

            return "/privado2/regisCaso";
        }

        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Usuario o contraseña incorrectas",
                        ""));
        return "index";

    }

    public String cerrarSession() {
        this.usuario = null;
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true);
        httpSession.invalidate();
        return "/index";

    }

    public LoginBean() {
        HttpSession miSession = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSession(true);
        miSession.setMaxInactiveInterval(2400);
    }

}
       