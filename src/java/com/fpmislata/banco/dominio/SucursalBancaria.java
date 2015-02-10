package com.fpmislata.banco.dominio;

import java.io.Serializable;


public class SucursalBancaria implements Serializable{
    private int id;
    private int entidadPertenece;
    private String codigoSucursal;
    private String nombreSucursal;

    public SucursalBancaria() {
    }

    public SucursalBancaria(int id, int entidadPertenece, String codigoSucursal, String nombreSucursal) {
        this.id = id;
        this.entidadPertenece = entidadPertenece;
        this.codigoSucursal = codigoSucursal;
        this.nombreSucursal = nombreSucursal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEntidadPertenece() {
        return entidadPertenece;
    }

    public void setEntidadPertenece(int entidadPertenece) {
        this.entidadPertenece = entidadPertenece;
    }

    public String getCodigoSucursal() {
        return codigoSucursal;
    }

    public void setCodigoSucursal(String codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }
  
}
