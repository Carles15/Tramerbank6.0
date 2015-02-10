package com.fpmislata.banco.presentacion.controller.seguridad;

import com.fpmislata.banco.common.json.JsonConverter;
import com.fpmislata.banco.dominio.Usuario;
import com.fpmislata.banco.dominio.seguridad.Credential;
import com.fpmislata.banco.persistencia.UsuarioDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SesionEmpleadoController {
    @Autowired
    UsuarioDAO usuarioDAO;
    
    @Autowired
    JsonConverter jsonConverter;
    
    @Autowired
    EmpleadoAuthentication empleadoAuthentication;
    
    @RequestMapping(value={"/session/empleado"}, method = RequestMethod.GET)
    public void get(HttpServletRequest httpServletRequest ,HttpServletResponse httpServletResponse){
        HttpSession httpSession = httpServletRequest.getSession(true);
        Integer id = (Integer) httpSession.getAttribute("id");
        
        if(id == null){
            httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }else{
            Usuario usuario = usuarioDAO.get(id);
            try {
                httpServletResponse.getWriter().print(jsonConverter.toJson(usuario));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    
    @RequestMapping(value={"/session/empleado"}, method = RequestMethod.DELETE)
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        HttpSession httpSession = httpServletRequest.getSession(true);
        httpSession.setAttribute("id", null);
    }
    
    @RequestMapping(value={"/session/empleado"}, method = RequestMethod.POST)
    public void login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada){
        System.out.println(jsonEntrada);
        Credential credential = (Credential) jsonConverter.fromJson(jsonEntrada, Credential.class);
        Usuario empleado  = empleadoAuthentication.authenticate(credential);
        if(empleado != null){
            HttpSession httpSession = httpServletRequest.getSession();
            httpSession.setAttribute("id", empleado.getId());
            try {
                httpServletResponse.getWriter().println(jsonConverter.toJson(empleadoAuthentication.authenticate(credential)));
                httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }else{
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        
        HttpSession httpSession = httpServletRequest.getSession(true);
        
    }
}
