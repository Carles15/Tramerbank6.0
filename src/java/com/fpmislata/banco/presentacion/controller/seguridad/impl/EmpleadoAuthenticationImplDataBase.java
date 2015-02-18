package com.fpmislata.banco.presentacion.controller.seguridad.impl;

import com.fpmislata.banco.dominio.Usuario;
import com.fpmislata.banco.dominio.seguridad.Credential;
import com.fpmislata.banco.persistencia.UsuarioDAO;
import com.fpmislata.banco.presentacion.controller.seguridad.EmpleadoAuthentication;
import org.springframework.beans.factory.annotation.Autowired;


public class EmpleadoAuthenticationImplDataBase implements EmpleadoAuthentication{
    @Autowired
    UsuarioDAO usuarioDAO;

    @Override
    public Usuario authenticate(Credential credential) {
        Usuario usuario = usuarioDAO.findByCorreo(credential.getEmail());
        if (usuario != null) {
            if (usuario.getContrasenya().equals(credential.getContrasenya())) {
                return usuario;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
