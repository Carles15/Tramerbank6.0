package com.fpmislata.banco.presentacion.controller.dominio;
import com.fpmislata.banco.common.json.JsonConverter;
import com.fpmislata.banco.dominio.CuentaBancaria;
import com.fpmislata.banco.dominio.MovimientoBancario;
import com.fpmislata.banco.dominio.TipoMovimiento;
import com.fpmislata.banco.persistencia.BussinessException;
import com.fpmislata.banco.persistencia.CuentaBancariaDAO;
import com.fpmislata.banco.persistencia.MovimientoBancarioDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
public class CuentaBancariaController {

    @Autowired
    JsonConverter jsonConverter;

    @Autowired
    CuentaBancariaDAO cuentaBancariaDAO;
    
    @Autowired
    MovimientoBancarioDAO movimientoBancarioDAO;

    @RequestMapping(value = {"/CuentaBancaria/{id}"}, method = RequestMethod.GET)
    public void get(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("id") int id) {
        CuentaBancaria cuentaBancaria = cuentaBancariaDAO.get(id);
        httpServletResponse.setContentType("application/json");
        try {
            httpServletResponse.getWriter().println(jsonConverter.toJson(cuentaBancaria));
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

    @RequestMapping(value = "/CuentaBancaria/{id}", method = RequestMethod.DELETE)
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("id") int id) {
        try {
            cuentaBancariaDAO.delete(id);
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

    @RequestMapping(value = "/CuentaBancaria", method = RequestMethod.POST)
    public void insert(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) throws IOException {
        httpServletResponse.setContentType("application/json");
        try {
            CuentaBancaria cuentaBancaria = (CuentaBancaria) jsonConverter.fromJson(jsonEntrada, CuentaBancaria.class);
            cuentaBancariaDAO.insert(cuentaBancaria);
            httpServletResponse.getWriter().println(jsonConverter.toJson(cuentaBancariaDAO.get(cuentaBancaria.getId())));
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } catch (BussinessException bussinessException) {
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().println(jsonConverter.toJson(bussinessException.getMessagesList()));
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/CuentaBancaria", method = RequestMethod.PUT)
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) throws IOException {
        httpServletResponse.setContentType("application/json");
        try {
            CuentaBancaria cuentaBancaria = (CuentaBancaria) jsonConverter.fromJson(jsonEntrada, CuentaBancaria.class);
            cuentaBancariaDAO.update(cuentaBancaria);
            httpServletResponse.getWriter().println(jsonConverter.toJson(cuentaBancariaDAO.get(cuentaBancaria.getId())));
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } catch (BussinessException bussinessException) {
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().println(jsonConverter.toJson(bussinessException.getMessagesList()));
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/CuentaBancaria", method = RequestMethod.GET)
    public void findAll(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletResponse.setContentType("application/json");
        try {
            List<CuentaBancaria> cuentasBancarias = cuentaBancariaDAO.findAll();
            httpServletResponse.getWriter().println(jsonConverter.toJson(cuentasBancarias));
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
    
    @RequestMapping(value = {"/CuentaBancaria/{id}/MovimientoBancario"}, method = RequestMethod.GET)
    public void getMovimientosBancarios(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("id") int id){
        httpServletResponse.setContentType("application/json");
        CuentaBancaria cuentaBancaria = cuentaBancariaDAO.get(id);
        List<Object> datos = new ArrayList<>();
        datos.add(cuentaBancaria);
        datos.add(cuentaBancariaDAO.getMovimientosBancarios(id));
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
    
    @RequestMapping(value ={"CuentaBancaria/create"}, method = RequestMethod.POST)
    public void createAccount(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) throws IOException{
        httpServletResponse.setContentType("application/json");
        CuentaBancaria cuentaBancaria = (CuentaBancaria) jsonConverter.fromJson(jsonEntrada, CuentaBancaria.class);
        MovimientoBancario movimientoBancario = new MovimientoBancario(cuentaBancaria.getId(), TipoMovimiento.HABER, 0, new Date(), "nueva cuenta");
        try {
            movimientoBancario.setSaldoTotal(cuentaBancaria.getSaldoCuenta());
            cuentaBancariaDAO.insert(cuentaBancaria);
            movimientoBancarioDAO.insert(movimientoBancario);

            httpServletResponse.getWriter().print(jsonConverter.toJson(cuentaBancaria));
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } catch (BussinessException bussinessException) {
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().println(jsonConverter.toJson(bussinessException.getMessagesList()));
        }
    }
    
}

