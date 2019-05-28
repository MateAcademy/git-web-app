package dao.impl;

import dao.UserDao;
import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import utils.HibernateSessionFactoryUtil;

import java.util.Optional;

public class UserDaoImplHibernate extends GenericDaoImpl<User> implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImplHibernate.class);

    @Override
    public User getUserByName(String name) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil
                .getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("name", name));
            logger.debug("getUserByName " + name + " was found");
            return (User) criteria.uniqueResult();
        } catch (Exception e) {
            logger.error("Can't getUserByName " + name, e);
            return null;
        }
    }

    @Override
    public Optional<User> getUserByNameOptional(String name) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("name", name));
            logger.debug("getUserByNameOptional " + name + " was found");
            return Optional.ofNullable((User) criteria.uniqueResult());
        } catch (Exception e) {
            logger.error("Can't getUserByNameOptional " + name, e);
            return Optional.empty();
        }
    }

    @Override
    public void updateUser(final String name, final String password) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query queryFindByLogin = session.createQuery("from User where name = :name");
            queryFindByLogin.setString("name", name);
            queryFindByLogin.setParameter("password", password);
            transaction.commit();
            logger.debug("updateUser " + name);
        } catch (Exception e) {
            logger.error("Can't updateUser", e);
        }
    }

    @Override
    public void updatePassword(User user, String newPassword) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            user.setPassword(newPassword);
            session.update(user);
            transaction.commit();
            logger.debug("User`s password updated");
        } catch (Exception e) {
            logger.error("Couldn't update user`s password", e);
        }
    }
}
