package com.fpmislata.banco.presentacion.controller.dominio;

import com.fpmislata.banco.common.json.JsonConverter;
import com.fpmislata.banco.dominio.CuentaBancaria;
import com.fpmislata.banco.dominio.MovimientoBancario;
import com.fpmislata.banco.dominio.TipoMovimiento;
import com.fpmislata.banco.persistencia.CuentaBancariaDAO;
import com.fpmislata.banco.persistencia.MovimientoBancarioDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MovimientoBancarioController {

    @Autowired
    JsonConverter jsonConverter;

    @Autowired
    MovimientoBancarioDAO movimientoBancarioDAO;

    @Autowired
    CuentaBancariaDAO cuentaBancariaDAO;

    @RequestMapping(value = {"/MovimientoBancario/{id}"}, method = RequestMethod.GET)
    public void get(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("id") int id) {
        MovimientoBancario movimientoBancario = movimientoBancarioDAO.get(id);
        httpServletResponse.setContentType("application/json");
        try {
            httpServletResponse.getWriter().println(jsonConverter.toJson(movimientoBancario));
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

    @RequestMapping(value = "/MovimientoBancario/{id}", method = RequestMethod.DELETE)
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("id") int id) {
        try {
            movimientoBancarioDAO.delete(id);
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

    @RequestMapping(value = "/MovimientoBancario", method = RequestMethod.POST)
    public void insert(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) {
        httpServletResponse.setContentType("application/json");
        try {
            //Control de la cuenta
            MovimientoBancario movimientoBancario = (MovimientoBancario) jsonConverter.fromJson(jsonEntrada, MovimientoBancario.class);
            CuentaBancaria cuentaBancaria = cuentaBancariaDAO.get(movimientoBancario.getCuentaPertenece());

            if (movimientoBancario.getTipoMovimiento() == TipoMovimiento.DEBE) {
                cuentaBancaria.setSaldoCuenta(cuentaBancaria.getSaldoCuenta() - movimientoBancario.getImporte());
                movimientoBancario.setSaldoTotal(cuentaBancaria.getSaldoCuenta());
            } else {
                cuentaBancaria.setSaldoCuenta(cuentaBancaria.getSaldoCuenta() + movimientoBancario.getImporte());
                movimientoBancario.setSaldoTotal(cuentaBancaria.getSaldoCuenta());
            }
            cuentaBancariaDAO.update(cuentaBancaria);
            movimientoBancarioDAO.insert(movimientoBancario);
            httpServletResponse.getWriter().println(jsonConverter.toJson(movimientoBancarioDAO.get(movimientoBancario.getId())));
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

    @RequestMapping(value = "/MovimientoBancario", method = RequestMethod.PUT)
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) {
        httpServletResponse.setContentType("application/json");
        try {
            MovimientoBancario movimientoBancario = (MovimientoBancario) jsonConverter.fromJson(jsonEntrada, MovimientoBancario.class);
            movimientoBancarioDAO.update(movimientoBancario);
            httpServletResponse.getWriter().println(jsonConverter.toJson(movimientoBancarioDAO.get(movimientoBancario.getId())));
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

    @RequestMapping(value = "/MovimientoBancario", method = RequestMethod.GET)
    public void findAll(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletResponse.setContentType("application/json");
        try {
            List<MovimientoBancario> movimientosBancarios = movimientoBancarioDAO.findAll();
            httpServletResponse.getWriter().println(jsonConverter.toJson(movimientosBancarios));
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
}
