package com.fpmislata.banco.persistencia.impl.hibernate;

import com.fpmislata.banco.dominio.Usuario;
import com.fpmislata.banco.persistencia.UsuarioDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UsuarioDAOImplHibernate extends GenericDAOImplHibernate<Usuario> implements UsuarioDAO{
    SessionFactory sessionFactory;
    
    @Override
    public Usuario findByCorreo(String email) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Usuario usuario = (Usuario) session.createQuery("SELECT p FROM Usuario p WHERE email = '" + email+"'").uniqueResult();
        System.out.println(usuario.getNombre());
        return usuario;
//        Session session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        Usuario usuario = (Usuario) session.get(Usuario.class, email);
//        session.getTransaction().commit();
//        
//        return usuario;
    }
    
}