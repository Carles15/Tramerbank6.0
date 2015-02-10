package com.fpmislata.banco.dominio;

import java.io.Serializable;
import org.hibernate.mapping.Column;





public class EntidadBancaria implements Serializable{

    private int id;
    private int codigoEntidad;
    private String nombreEntidad;
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
