package com.fpmislata.banco.persistencia;

import com.fpmislata.banco.dominio.CuentaBancaria;
import com.fpmislata.banco.dominio.MovimientoBancario;
import java.util.List;


public interface CuentaBancariaDAO extends GenericDAO <CuentaBancaria>{
  public List<MovimientoBancario> getMovimientosBancarios(int id);
  public CuentaBancaria getCuentaPorNumero(int numCuenta);
}
