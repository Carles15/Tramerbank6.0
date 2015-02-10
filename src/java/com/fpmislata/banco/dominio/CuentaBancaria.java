package com.fpmislata.banco.dominio;

import java.io.Serializable;


public class CuentaBancaria implements Serializable{
    private int id;
    private int sucursalPertenece;
    private int numCuenta;
    private int digitosControl;
    private double saldoCuenta;

    public CuentaBancaria() {
    }

    public CuentaBancaria(int id, int sucursalPertenece, int numCuenta, int digitosControl, double saldoCuenta) {
        this.id = id;
        this.sucursalPertenece = sucursalPertenece;
        this.numCuenta = numCuenta;
        this.digitosControl = digitosControl;
        this.saldoCuenta = saldoCuenta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSucursalPertenece() {
        return sucursalPertenece;
    }

    public void setSucursalPertenece(int sucursalPertenece) {
        this.sucursalPertenece = sucursalPertenece;
    }

    public int getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(int numCuenta) {
        this.numCuenta = numCuenta;
    }

    public int getDigitosControl() {
        return digitosControl;
    }

    public void setDigitosControl(int digitosControl) {
        this.digitosControl = digitosControl;
    }

    public double getSaldoCuenta() {
        return saldoCuenta;
    }

    public void setSaldoCuenta(double saldoCuenta) {
        this.saldoCuenta = saldoCuenta;
    }
    
    
}
