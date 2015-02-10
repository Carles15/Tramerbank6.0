package com.fpmislata.banco.persistencia;

import com.fpmislata.banco.dominio.EntidadBancaria;
import com.fpmislata.banco.dominio.SucursalBancaria;
import java.util.List;


public interface EntidadBancariaDAO extends GenericDAO <EntidadBancaria> {
    public List<SucursalBancaria> getSucursalesBancarias(int id);
}
