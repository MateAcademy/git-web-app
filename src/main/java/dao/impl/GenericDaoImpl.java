package dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

import dao.GenericDao;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    private static final Logger logger = Logger.getLogger(GenericDaoImpl.class);

    @Override
    public void add(T object) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
            logger.debug("We save T object");
        } catch (Exception e) {
            logger.warn("WARN, cannot add", e);
        }
    }

    @Override
    public T getById(Class<T> entityClass, final long id) {
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();
        T object = session.get(entityClass, id);
        session.close();
        return object;
    }

    @Override
    public void delete(T object) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
            logger.debug("We delete T object");
        } catch (Exception e) {
            logger.warn("WARN, cannot  delete T object", e);
        }
    }

    @Override
    public void update(T object) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(object);
            transaction.commit();
            logger.debug("We update T object");
        } catch (Exception e) {
            logger.error("Can't update  ", e);
        }
    }

    @Override
    public List<T> getAll(Class<T> entityClass) {
//        return HibernateSessionFactoryUtil
//                .getSessionFactory()
//                .openSession()
//                .createCriteria(entityClass).list();
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();
        List<T> objects = (List<T>) ((Session) session)
                .createQuery("From " + entityClass.getName()).list();
        session.close();
        return objects;
    }
}




