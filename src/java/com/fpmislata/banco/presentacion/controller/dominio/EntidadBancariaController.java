package com.fpmislata.banco.presentacion.controller.dominio;

import com.fpmislata.banco.common.json.JsonConverter;
import com.fpmislata.banco.dominio.EntidadBancaria;
import com.fpmislata.banco.dominio.SucursalBancaria;
import com.fpmislata.banco.persistencia.EntidadBancariaDAO;
import com.fpmislata.banco.persistencia.impl.hibernate.EntidadBancariaDAOImplHibernate;
import java.io.IOException;
import java.util.ArrayList;
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
public class EntidadBancariaController {
    @Autowired
    JsonConverter jsonConverter;

    @Autowired
    EntidadBancariaDAO entidadBancariaDAO;

    @RequestMapping(value = {"/EntidadBancaria/{id}"}, method = RequestMethod.GET)
    public void get(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("id") int id) {
        EntidadBancaria entidadBancaria = entidadBancariaDAO.get(id);
        httpServletResponse.setContentType("application/json");
        try {
            httpServletResponse.getWriter().println(jsonConverter.toJson(entidadBancaria));
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

    @RequestMapping(value = "/EntidadBancaria/{id}", method = RequestMethod.DELETE)
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("id") int id) {
        try {
            entidadBancariaDAO.delete(id);
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

    @RequestMapping(value = "/EntidadBancaria", method = RequestMethod.POST)
    public void insert(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) {
        httpServletResponse.setContentType("application/json");
        try {
            EntidadBancaria entidadBancaria = (EntidadBancaria) jsonConverter.fromJson(jsonEntrada, EntidadBancaria.class);
            entidadBancariaDAO.insert(entidadBancaria);
            httpServletResponse.getWriter().println(jsonConverter.toJson(entidadBancariaDAO.get(entidadBancaria.getId())));
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

    @RequestMapping(value = "/EntidadBancaria", method = RequestMethod.PUT)
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) {
        httpServletResponse.setContentType("application/json");
        try {
            EntidadBancaria entidadBancaria = (EntidadBancaria) jsonConverter.fromJson(jsonEntrada, EntidadBancaria.class);
            entidadBancariaDAO.update(entidadBancaria);
            httpServletResponse.getWriter().println(jsonConverter.toJson(entidadBancariaDAO.get(entidadBancaria.getId())));
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

    @RequestMapping(value = "/EntidadBancaria", method = RequestMethod.GET)
    public void findAll(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletResponse.setContentType("application/json");
        try {
            List<EntidadBancaria> entidadesBancarias = entidadBancariaDAO.findAll();
            httpServletResponse.getWriter().println(jsonConverter.toJson(entidadesBancarias));
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
    
    @RequestMapping(value="/EntidadBancaria/{id}/SucursalesBancarias", method = RequestMethod.GET)
    public void getSucursales(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("id") int id){
        httpServletResponse.setContentType("application/json");
        EntidadBancaria entidadBancaria = entidadBancariaDAO.get(id);
        List<Object> datos = new ArrayList<>();
        datos.add(entidadBancaria);
        datos.add(entidadBancariaDAO.getSucursalesBancarias(id));
//        List<SucursalBancaria> sucursalBancarias = entidadBancariaDAO.getSucursalesBancarias(id);
        try {
            httpServletResponse.getWriter().print(jsonConverter.toJson(datos));
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
