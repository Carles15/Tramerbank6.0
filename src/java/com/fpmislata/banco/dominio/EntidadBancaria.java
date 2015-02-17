package com.fpmislata.banco.dominio;

import java.io.Serializable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.mapping.Column;





public class EntidadBancaria implements Serializable{

    @Digits(integer=11,fraction=0)
    @Min(1)
    private int id;
    @Digits(integer=11,fraction=0)
    @Min(1)
    private int codigoEntidad;
    @Size(min=1, max=50)
    @NotNull
    private String nombreEntidad;
    @Size(min=1, max=50)
    @NotNull
    private String cif;
    TipoEntidad tipoEntidad;

    public EntidadBancaria() {

    }

    public EntidadBancaria(int id, int codigoEntidad, String nombreEntidad, String cif, TipoEntidad tipoEntidad) {
        this.id = id;
        this.codigoEntidad = codigoEntidad;
        this.nombreEntidad = nombreEntidad;
        this.cif = cif;
        this.tipoEntidad = tipoEntidad;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigoEntidad() {
        return codigoEntidad;
    }

    public void setCodigoEntidad(int codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }
    
    public TipoEntidad getTipoEntidad() {
        return tipoEntidad;
    }

    public void setTipoEntidad(TipoEntidad tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
    }
}
