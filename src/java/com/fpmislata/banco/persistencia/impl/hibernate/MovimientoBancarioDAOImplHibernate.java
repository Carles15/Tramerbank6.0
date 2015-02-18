package com.fpmislata.banco.persistencia.impl.hibernate;

import com.fpmislata.banco.dominio.CuentaBancaria;
import com.fpmislata.banco.dominio.MovimientoBancario;
import com.fpmislata.banco.dominio.TipoMovimiento;
import com.fpmislata.banco.persistencia.BussinessException;
import com.fpmislata.banco.persistencia.CuentaBancariaDAO;
import com.fpmislata.banco.persistencia.MovimientoBancarioDAO;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

public class MovimientoBancarioDAOImplHibernate extends GenericDAOImplHibernate<MovimientoBancario> implements MovimientoBancarioDAO {

    @Autowired
    CuentaBancariaDAO cuentaBancariaDAO;

    @Override
    public MovimientoBancario insert(MovimientoBancario movimientoBancario) throws BussinessException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        CuentaBancaria cuentaBancaria = cuentaBancariaDAO.get(movimientoBancario.getCuentaPertenece());
        
        if (movimientoBancario.getTipoMovimiento() == TipoMovimiento.DEBE) {
            cuentaBancaria.setSaldoCuenta(cuentaBancaria.getSaldoCuenta() - movimientoBancario.getImporte());
            movimientoBancario.setSaldoTotal(cuentaBancaria.getSaldoCuenta());
            cuentaBancariaDAO.update(cuentaBancaria);
        } else if (movimientoBancario.getTipoMovimiento() == TipoMovimiento.HABER) {
            cuentaBancaria.setSaldoCuenta(cuentaBancaria.getSaldoCuenta() + movimientoBancario.getImporte());
            movimientoBancario.setSaldoTotal(cuentaBancaria.getSaldoCuenta());
            cuentaBancariaDAO.update(cuentaBancaria);
        } else {
            throw new RuntimeException("Hay un valor en el Enumerado que no conozco " + movimientoBancario.getTipoMovimiento());
        }
        try {

            session.beginTransaction();

            session.save(movimientoBancario);

            session.getTransaction().commit();
            return movimientoBancario;
        } catch (Exception ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        }
    }
}
