package com.fpmislata.banco.presentacion.controller.dominio;

import com.fpmislata.banco.common.json.JsonConverter;
import com.fpmislata.banco.dominio.Transaccion;
import com.fpmislata.banco.persistencia.BussinessException;
import com.fpmislata.banco.persistencia.CuentaBancariaDAO;
import com.fpmislata.banco.persistencia.MovimientoBancarioDAO;
import com.fpmislata.banco.servicios.ServicioDeTransaccion;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TransaccionController {
    @Autowired
    CuentaBancariaDAO cuentaBancariaDAO;
    
    @Autowired
    MovimientoBancarioDAO movimientoBancarioDAO;
    
    @Autowired
    ServicioDeTransaccion servicioDeTransaccion;

    @Autowired
    JsonConverter jsonConverter;

    @RequestMapping(value = "/Transaccion", method = RequestMethod.POST)
    public void insertTransaccion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) throws BussinessException {
        httpServletResponse.setContentType("application/json");
        try {
            Transaccion transaccion = (Transaccion) jsonConverter.fromJson(jsonEntrada, Transaccion.class);

            servicioDeTransaccion.generarTransaccion(transaccion);

            
            
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

}
