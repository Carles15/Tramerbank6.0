package com.fpmislata.banco.persistencia;

import com.fpmislata.banco.dominio.CuentaBancaria;
import com.fpmislata.banco.dominio.SucursalBancaria;
import java.util.List;


public interface SucursalBancariaDAO extends GenericDAO <SucursalBancaria> {
    public List<CuentaBancaria> getCuentasBancarias(int id);
}
