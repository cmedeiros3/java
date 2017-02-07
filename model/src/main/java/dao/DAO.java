package dao;

import hibernate.HibernateUtil;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Component;

@Component
public class DAO<T> {

    private Session session;

    public DAO() {
    }

    public T save(T entity) {
        try {
        	
            getSession().beginTransaction();
            getSession().save(entity);
            getSession().getTransaction().commit();
            getSession().close();
            return entity;
        } catch (Exception e) {
            getSession().getTransaction().rollback();
            getSession().close();
            return null;
        }
    }

    public T update(T entity) {
        try {
            getSession().beginTransaction();
            getSession().merge(entity);
            getSession().getTransaction().commit();
            getSession().close();
            return entity;
        } catch (Exception e) {
            getSession().getTransaction().rollback();
            getSession().close();
            return null;
        }
    }

    public boolean delete(T entity) {
        boolean result;
        try {
            getSession().beginTransaction();
            getSession().delete(entity);
            getSession().getTransaction().commit();
            result = true;
        } catch (Exception e) {
            getSession().getTransaction().rollback();
            result = false;
        } finally {
            getSession().close();
        }
        return result;
    }

  
    
    public T searchModel(DetachedCriteria query) {
        T model = (T) query.getExecutableCriteria(getSession()).uniqueResult();
        return model;
    }

    public List<T> searchModels(DetachedCriteria query) {
        List<T> models = query.getExecutableCriteria(getSession()).list();
        return models;
    }

    protected Session getSession() {
        if (session == null || !session.isOpen()) {
            session = HibernateUtil.getSessionFactory().openSession();

        }
        return session;
    }

}