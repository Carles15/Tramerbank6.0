package com.fpmislata.banco.presentacion.controller.dominio;

import com.fpmislata.banco.common.json.JsonConverter;
import com.fpmislata.banco.dominio.SucursalBancaria;
import com.fpmislata.banco.persistencia.SucursalBancariaDAO;
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
public class SucursalBancariaController {
    @Autowired
    JsonConverter jsonConverter;

    @Autowired
    SucursalBancariaDAO sucursalBancariaDAO;

    @RequestMapping(value = {"/SucursalBancaria/{id}"}, method = RequestMethod.GET)
    public void get(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("id") int id) {
        SucursalBancaria sucursalBancaria = sucursalBancariaDAO.get(id);
        httpServletResponse.setContentType("application/json");
        try {
            httpServletResponse.getWriter().println(jsonConverter.toJson(sucursalBancaria));
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

    @RequestMapping(value = "/SucursalBancaria/{id}", method = RequestMethod.DELETE)
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("id") int id) {
        try {
            sucursalBancariaDAO.delete(id);
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

    @RequestMapping(value = "/SucursalBancaria", method = RequestMethod.POST)
    public void insert(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) {
        httpServletResponse.setContentType("application/json");
        try {
            SucursalBancaria sucursalBancaria = (SucursalBancaria) jsonConverter.fromJson(jsonEntrada, SucursalBancaria.class);
            sucursalBancariaDAO.insert(sucursalBancaria);
            httpServletResponse.getWriter().println(jsonConverter.toJson(sucursalBancariaDAO.get(sucursalBancaria.getId())));
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

    @RequestMapping(value = "/SucursalBancaria", method = RequestMethod.PUT)
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) {
        httpServletResponse.setContentType("application/json");
        try {
            SucursalBancaria sucursalBancaria = (SucursalBancaria) jsonConverter.fromJson(jsonEntrada, SucursalBancaria.class);
            sucursalBancariaDAO.update(sucursalBancaria);
            httpServletResponse.getWriter().println(jsonConverter.toJson(sucursalBancariaDAO.get(sucursalBancaria.getId())));
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

    @RequestMapping(value = "/SucursalBancaria", method = RequestMethod.GET)
    public void findAll(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletResponse.setContentType("application/json");
        try {
            List<SucursalBancaria> sucursalesBancarias = sucursalBancariaDAO.findAll();
            httpServletResponse.getWriter().println(jsonConverter.toJson(sucursalesBancarias));
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
    
    
    @RequestMapping(value="/SucursalBancaria/{id}/CuentasBancarias", method = RequestMethod.GET)
    public void getCuentasBancarias(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("id") int id){
        httpServletResponse.setContentType("application/json");
        SucursalBancaria sucursalBancaria = sucursalBancariaDAO.get(id);
        List<Object> datos = new ArrayList<>();
        datos.add(sucursalBancaria);
        datos.add(sucursalBancariaDAO.getCuentasBancarias(id));
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
