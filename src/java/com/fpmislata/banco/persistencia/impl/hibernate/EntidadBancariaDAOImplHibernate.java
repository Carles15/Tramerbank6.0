package com.fpmislata.banco.persistencia.impl.hibernate;

import com.fpmislata.banco.dominio.EntidadBancaria;
import com.fpmislata.banco.dominio.SucursalBancaria;
import com.fpmislata.banco.persistencia.EntidadBancariaDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class EntidadBancariaDAOImplHibernate extends GenericDAOImplHibernate<EntidadBancaria> implements EntidadBancariaDAO{
    
    public List<SucursalBancaria> getSucursalesBancarias(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            Query query = session.createQuery("SELECT p FROM SucursalBancaria p WHERE entidadPertenece = " + id);
            List<SucursalBancaria> sucursalesBancarias = query.list();
            return sucursalesBancarias;
        } catch (Exception ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException();
        }
    }
}
