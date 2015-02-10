package com.fpmislata.banco.presentacion.controller.seguridad;

import com.fpmislata.banco.dominio.Usuario;
import com.fpmislata.banco.dominio.seguridad.Credential;

public interface EmpleadoAuthentication {
    public Usuario authenticate(Credential credential);
}
