package com.fpmislata.banco.persistencia.impl.hibernate;

import com.fpmislata.banco.persistencia.GenericDAO;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class GenericDAOImplHibernate<T> implements GenericDAO<T> {

    @Override
    public T get(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            T t = (T) session.get(getEntityClass(), id);
            session.getTransaction().commit();
            return t;
        } catch (Exception ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException();
        }
    }

    @Override
    public T insert(T t) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
            return t;
        } catch (Exception ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            T t = (T) session.get(getEntityClass(), id);
            session.delete(t);
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException();
        }
    }

    @Override
    public T update(T t) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.update(t);
            session.getTransaction().commit();
            return t;
        } catch (Exception ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException();
        }
    }

    @Override
    public List<T> findAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            Query query = session.createQuery("SELECT p FROM " + getEntityClass().getName() + " p");
            List<T> t = query.list();
            return t;
        } catch (Exception ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException();
        }
    }

    private Class<T> getEntityClass() {
        //En el [0], son los parámetros en el implements de la clase/interfaz
        // GenericDAO <T, ID extends Serializable>
        //En la línea superior, la T sería [0] e ID sería [1]
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
