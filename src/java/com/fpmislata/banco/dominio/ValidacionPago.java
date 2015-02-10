
package com.fpmislata.banco.dominio;


public class ValidacionPago {
    public int pin;
    public int cuenta;
    public double importe;

    public ValidacionPago(){
    }
    public ValidacionPago(int pin, int cuenta, double importe) {
        this.pin = pin;
        this.cuenta = cuenta;
        this.importe = importe;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getCuenta() {
        return cuenta;
    }

    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
    
    
    
}
