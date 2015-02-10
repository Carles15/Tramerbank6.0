package com.fpmislata.banco.persistencia.impl.hibernate;

import com.fpmislata.banco.dominio.CuentaBancaria;
import com.fpmislata.banco.dominio.MovimientoBancario;
import com.fpmislata.banco.persistencia.CuentaBancariaDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;


public class CuentaBancariaDAOImplHibernate extends GenericDAOImplHibernate<CuentaBancaria> implements CuentaBancariaDAO{
   @Override
    public List<MovimientoBancario> getMovimientosBancarios(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        try{
            Query query = session.createQuery("SELECT c FROM MovimientoBancario c WHERE cuentaPertenece = " + id);
            List<MovimientoBancario> movimientosBancarios = query.list();
            return movimientosBancarios;
        }catch(Exception ex){
            if(session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            throw new RuntimeException();
        }
    }
    
   @Override
    public CuentaBancaria getCuentaPorNumero(int numCuenta){
        System.out.println(numCuenta);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try{
            
            Query query = session.createQuery("SELECT c FROM CuentaBancaria c WHERE  numCuenta = "+numCuenta);
            CuentaBancaria cuentaBancaria = (CuentaBancaria) query.uniqueResult();
            return cuentaBancaria;
            
        }catch(Exception ex){
            if(session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        }       
    
    }
    
}
