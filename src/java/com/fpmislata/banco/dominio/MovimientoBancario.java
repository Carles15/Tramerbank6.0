package com.fpmislata.banco.dominio;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;


public class MovimientoBancario implements Serializable{
    @Digits(integer=11,fraction=0)
    @Min(1)
    private int id;
    @Digits(integer=11,fraction=0)
    @Min(1)
    private int cuentaPertenece;
    @NotNull
    private TipoMovimiento tipoMovimiento;
    @Digits(integer=11,fraction=2)
    @Min(0)
    private double importe;
    @NotNull
    @Past
    private Date fecha;
    @Digits(integer=11,fraction=10)
    @Min(0)
    private double saldoTotal;
    @Size(min=1, max=1000)
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
