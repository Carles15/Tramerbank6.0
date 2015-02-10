package com.fpmislata.banco.dominio;

import java.io.Serializable;
import java.util.Date;


public class MovimientoBancario implements Serializable{
    private int id;
    private int cuentaPertenece;
    private TipoMovimiento tipoMovimiento;
    private double importe;
    private Date fecha;
    private double saldoTotal;
    private String concepto;

    public MovimientoBancario() {
    }

    public MovimientoBancario(int id, int cuentaPertenece, TipoMovimiento tipoMovimiento, double importe, Date fecha, double saldoTotal, String concepto) {
        this.id = id;
        this.cuentaPertenece = cuentaPertenece;
        this.tipoMovimiento= tipoMovimiento;
        this.importe = importe;
        this.fecha = fecha;
        this.saldoTotal = saldoTotal;
        this.concepto = concepto;
    }
      public MovimientoBancario( int cuentaPertenece, TipoMovimiento tipoMovimiento, double importe, Date fecha,String concepto) {
        this.cuentaPertenece = cuentaPertenece;
        this.tipoMovimiento= tipoMovimiento;
        this.importe = importe;
        this.fecha = fecha;
        this.concepto = concepto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCuentaPertenece() {
        return cuentaPertenece;
    }

    public void setCuentaPertenece(int cuentaPertenece) {
        this.cuentaPertenece = cuentaPertenece;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
    
    
}
