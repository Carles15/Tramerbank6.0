/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fpmislata.banco.dominio;
import com.fpmislata.banco.dominio.MovimientoBancario;
import java.util.Date;

/**
 *
 * @author alumno
 */
public class Transaccion {
    public int codigoCuentaClienteOrigen;
    public int codigoCuentaClienteDestino;
    public int idCarrito;
    public double importe;
    public String concepto;

    public Transaccion(){
    }
    
    public Transaccion(int codigoCuentaClienteOrigen, int codigoCuentaClienteDestino, int idCarrito, double importe, String concepto) {
        this.codigoCuentaClienteOrigen = codigoCuentaClienteOrigen;
        this.codigoCuentaClienteDestino = codigoCuentaClienteDestino;
        this.idCarrito = idCarrito;
        this.importe = importe;
        this.concepto = concepto;
    }

    public int getCodigoCuentaClienteOrigen() {
        return codigoCuentaClienteOrigen;
    }

    public void setCodigoCuentaClienteOrigen(int codigoCuentaClienteOrigen) {
        this.codigoCuentaClienteOrigen = codigoCuentaClienteOrigen;
    }

    public int getCodigoCuentaClienteDestino() {
        return codigoCuentaClienteDestino;
    }

    public void setCodigoCuentaClienteDestino(int codigoCuentaClienteDestino) {
        this.codigoCuentaClienteDestino = codigoCuentaClienteDestino;
    }

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
    
        
    
    
    public MovimientoBancario getMovimientoBancarioDebe(int idCuentaOrigen){
        MovimientoBancario movimientoBancario = new MovimientoBancario(idCuentaOrigen,TipoMovimiento.DEBE,importe,new Date(),concepto);
        return movimientoBancario;
    }
    
    public MovimientoBancario getMovimientoBancarioHaber(int idCuentaDestino){
        MovimientoBancario movimientoBancario = new MovimientoBancario(idCuentaDestino,TipoMovimiento.HABER,importe,new Date(),concepto);
        return movimientoBancario;
    } 
    
}
