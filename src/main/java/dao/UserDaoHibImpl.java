package dao;

import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import utils.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.Optional;

public class UserDaoHibImpl {

    private static final Logger LOGGER = Logger.getLogger(UserDaoHibImpl.class);

    public static int addUser(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.save(user);
            tx1.commit();
            LOGGER.debug("User added!");
            return 1;
        } catch (Exception e) {
            LOGGER.error("Can't addUser user  ", e);
            return 0;
        }
    }

    public static void delete(long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(getUserById(id));
        tx1.commit();
        session.close();
        LOGGER.debug("User deleted!");
    }

    public static void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
        LOGGER.debug("User updated!");
    }

    public static User getUserByLogin(String name) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil
                .getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("name", name));
            LOGGER.error("User with name " + name + " was found");
            return (User) criteria.uniqueResult();
        } catch (Exception e) {
            LOGGER.error("Can't find user with name " + name, e);
            return null;
        }
    }

    public static Optional<User> getUserByLoginOptional(String name) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("name", name));
            LOGGER.error("User with name " + name + " was found");
            return Optional.ofNullable((User) criteria.uniqueResult());
        } catch (Exception e) {
            LOGGER.error("Can't find user with name " + name, e);
            return Optional.empty();
        }
    }

    public void updateUser(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public static void upUs(final String name, final String password) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query queryFindByLogin = session.createQuery("from User where name = :name");
        queryFindByLogin.setString("name", name);
        queryFindByLogin.setParameter("password", password);
        tx1.commit();
        session.close();
    }


    public static void updatePassword(User user, String newPassword) {
        try {
            Session session = HibernateSessionFactoryUtil
                    .getSessionFactory()
                    .openSession();
            session.beginTransaction();
            user.setPassword(newPassword);
            session.update(user);
            session.getTransaction().commit();
            session.close();
            LOGGER.debug("User`s password updated");
        } catch (Exception e) {
            LOGGER.error("Couldn't update user`s password", e);
        }
    }

    public static User getUserById(long id) {
        return HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession().
                get(User.class, id);
    }


    public static List<User> getAllUsers() {
        return (List<User>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User").list();
    }
}


