package com.fpmislata.banco.servicios;

import com.fpmislata.banco.dominio.CuentaBancaria;
import com.fpmislata.banco.dominio.MovimientoBancario;
import com.fpmislata.banco.dominio.Transaccion;
import com.fpmislata.banco.persistencia.CuentaBancariaDAO;
import com.fpmislata.banco.persistencia.MovimientoBancarioDAO;
import org.springframework.beans.factory.annotation.Autowired;


public class ServicioDeTransaccion {
    @Autowired
    CuentaBancariaDAO cuentaBancariaDAO;
    
    @Autowired
    MovimientoBancarioDAO movimientoBancarioDAO;
    
    public ServicioDeTransaccion(){
    }
    
       
    public boolean generarTransaccion(Transaccion transaccion){
        int codigoCliente = transaccion.getCodigoCuentaClienteOrigen();
        CuentaBancaria cuentaBancariaDebe = cuentaBancariaDAO.getCuentaPorNumero(codigoCliente);
        MovimientoBancario movimientoBancarioDebe = transaccion.getMovimientoBancarioDebe(cuentaBancariaDebe.getId());
        movimientoBancarioDAO.insert(movimientoBancarioDebe);
        
        CuentaBancaria cuentaBancariaHaber = cuentaBancariaDAO.getCuentaPorNumero(transaccion.getCodigoCuentaClienteDestino());
        MovimientoBancario movimientoBancarioHaber = transaccion.getMovimientoBancarioHaber(cuentaBancariaHaber.getId());
        movimientoBancarioDAO.insert(movimientoBancarioHaber);
        
        
        return true;
        
    }
    
    
    
    
    
    
}
