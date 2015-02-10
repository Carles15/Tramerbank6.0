package com.fpmislata.banco.persistencia;

import com.fpmislata.banco.dominio.Usuario;


public interface UsuarioDAO extends GenericDAO <Usuario> {
    public Usuario findByCorreo(String email);
}
