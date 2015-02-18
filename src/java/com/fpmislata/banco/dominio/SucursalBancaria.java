package com.fpmislata.banco.dominio;

import java.io.Serializable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class SucursalBancaria implements Serializable{
    @Digits(integer=11,fraction=0)
    @Min(1)
    private int id;
    @Digits(integer=11,fraction=0)
    @Min(1)
    private int entidadPertenece;
    @Size(min=1, max=50)
    @NotNull
    private String codigoSucursal;
    @Size(min=1, max=50)
    @NotNull
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
