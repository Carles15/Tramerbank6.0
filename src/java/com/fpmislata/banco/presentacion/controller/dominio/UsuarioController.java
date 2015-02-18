package com.fpmislata.banco.presentacion.controller.dominio;

import com.fpmislata.banco.common.json.JsonConverter;
import com.fpmislata.banco.dominio.Usuario;
import com.fpmislata.banco.persistencia.BussinessException;
import com.fpmislata.banco.persistencia.UsuarioDAO;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UsuarioController {

    @Autowired
    JsonConverter jsonConverter;

    @Autowired
    UsuarioDAO usuarioDAO;

    @RequestMapping(value = {"/Usuario/{id}"}, method = RequestMethod.GET)
    public void get(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("id") int id) {
        Usuario usuario = usuarioDAO.get(id);
        httpServletResponse.setContentType("application/json");
        try {
            httpServletResponse.getWriter().println(jsonConverter.toJson(usuario));
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException ex) {
            try {
                ex.printStackTrace(httpServletResponse.getWriter());
                httpServletResponse.setContentType("text/plain");
                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (IOException ex1) {
                throw new RuntimeException(ex);
            }
        }
    }

    @RequestMapping(value = "/Usuario/{id}", method = RequestMethod.DELETE)
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("id") int id) {
        try {
            usuarioDAO.delete(id);
            httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception ex) {
            try {
                ex.printStackTrace(httpServletResponse.getWriter());
                httpServletResponse.setContentType("text/plain");
                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (IOException ex1) {
                throw new RuntimeException(ex);
            }
        }
    }

    @RequestMapping(value = "/Usuario", method = RequestMethod.POST)
    public void insert(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) throws IOException {
        httpServletResponse.setContentType("application/json");
        try {
            Usuario usuario = (Usuario) jsonConverter.fromJson(jsonEntrada, Usuario.class);
            usuarioDAO.insert(usuario);
            httpServletResponse.getWriter().println(jsonConverter.toJson(usuarioDAO.get(usuario.getId())));
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } catch (BussinessException bussinessException) {
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().println(jsonConverter.toJson(bussinessException.getMessagesList()));
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/Usuario", method = RequestMethod.PUT)
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) throws IOException {
        httpServletResponse.setContentType("application/json");
        try {
            Usuario usuario = (Usuario) jsonConverter.fromJson(jsonEntrada, Usuario.class);
            usuarioDAO.update(usuario);
            httpServletResponse.getWriter().println(jsonConverter.toJson(usuarioDAO.get(usuario.getId())));
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } catch (BussinessException bussinessException) {
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().println(jsonConverter.toJson(bussinessException.getMessagesList()));
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/Usuario", method = RequestMethod.GET)
    public void findAll(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletResponse.setContentType("application/json");
        try {
            List<Usuario> usuarios = usuarioDAO.findAll();
            httpServletResponse.getWriter().println(jsonConverter.toJson(usuarios));
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception ex) {
            try {
                ex.printStackTrace(httpServletResponse.getWriter());
                httpServletResponse.setContentType("text/plain");
                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (IOException ex1) {
                throw new RuntimeException(ex);
            }
        }
    }

    @RequestMapping(value = "/Usuario/correo/{email}", method = RequestMethod.GET)
    public void accountLocation(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable String email) {
        httpServletResponse.setContentType("application/json");
        Usuario usuario = usuarioDAO.findByCorreo(email);
        try {
            httpServletResponse.getWriter().println(jsonConverter.toJson(usuario));
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException ex) {
            try {
                ex.printStackTrace(httpServletResponse.getWriter());
                httpServletResponse.setContentType("text/plain");
                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (IOException ex1) {
                throw new RuntimeException(ex);
            }
        }
    }
}
