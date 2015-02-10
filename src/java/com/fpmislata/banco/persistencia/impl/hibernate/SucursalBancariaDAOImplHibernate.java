package com.fpmislata.banco.persistencia.impl.hibernate;

import com.fpmislata.banco.dominio.CuentaBancaria;
import com.fpmislata.banco.dominio.SucursalBancaria;
import com.fpmislata.banco.persistencia.SucursalBancariaDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class SucursalBancariaDAOImplHibernate extends GenericDAOImplHibernate<SucursalBancaria> implements SucursalBancariaDAO{

    @Override
    public List<CuentaBancaria> getCuentasBancarias(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        try{
            Query query = session.createQuery("SELECT p FROM CuentaBancaria p WHERE sucursalPertenece = " + id);
            List<CuentaBancaria> cuentasBancarias = query.list();
            return cuentasBancarias;
        }catch(Exception ex){
            if(session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            throw new RuntimeException();
        }
    }
    
}
