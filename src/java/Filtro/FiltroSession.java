
package Filtro;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class FiltroSession implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse res= (HttpServletResponse)response;
        HttpSession session = req.getSession(true);
        String requesUrl = req.getRequestURL().toString();
        //verificamos si en la session esta el atributo usuario y si se viene desde el index
        
        if(session.getAttribute("usuario")==null&&requesUrl.contains("index.xhtml")){
        //si entra aqui es  falso y por lo tanto se lo redirecciona al index
        res.sendRedirect(req.getContextPath()+"/faces/index.xhtml");
        }else{
         chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        
    }
    
    
  
    
}
