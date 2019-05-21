//package dao;
//
//import model.User;
//import org.hibernate.*;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.criterion.Restrictions;
//import utils.HibernateSessionFactoryUtil;
//
//import java.util.Collection;
//import java.util.List;
//
//
//public class HibernateStorage implements Storage {
//    private final SessionFactory factory;
//
//    public HibernateStorage() {
//        factory = HibernateSessionFactoryUtil
//                .getSessionFactory();
//    }
//
//    public User findUserByName(String name) {
//        SessionFactory sessionFactory = HibernateSessionFactoryUtil
//                .getSessionFactory();
//        try (Session session = sessionFactory.openSession()) {
//            Criteria criteria = session.createCriteria(User.class);
//            criteria.add(Restrictions.eq("name", name));
// //           LOGGER.error("User with name " + name + " was found");
//            return (User) criteria.uniqueResult();
//        } catch (Exception e) {
// //           LOGGER.error("Can't find user with name " + name, e);
//            return null;
//        }
//    }
//
//
//
//
//    public List<User> serchByLogin(final String login) {
//        final Session session = factory.openSession();
//        Transaction tx = session.beginTransaction();
//        try {
//            return session.createQuery("from User as user where lower(user.login) like %:login%").list();
//        } finally {
//            tx.commit();
//            session.close();
//        }
//    }
//
//    @Override
//    public User findByLogin(String name) {
//        final Session session = factory.openSession();
//        Transaction tx = session.beginTransaction();
//        try {
//            final Query query = session.createQuery("FROM User WHERE name = :name");
//            query.setString("name", name);
//            return (User) query.iterate().next();
//        } finally {
//            tx.commit();
//            session.close();
//        }
//    }
//
//
//    @Override
//    public Collection<User> values() {
//        final Session session = factory.openSession();
//        Transaction tx = session.beginTransaction();
//        try {
//            return session.createQuery("from User").list();
//        } finally {
//            tx.commit();
//            session.close();
//        }
//    }
//
//
//
//    @Override
//    public long add(final User user) {
//        final Session session = factory.openSession();
//        Transaction tx = session.beginTransaction();
//        try {
//            session.save(user);
//            return user.getId();
//        } finally {
//            tx.commit();
//            session.close();
//        }
//    }
//
//    @Override
//    public void edit(User user) {
//
//    }
//
//    @Override
//    public void delete(long id) {
//        final Session session = factory.openSession();
//        Transaction tx = session.beginTransaction();
//        try {
//            session.delete(new User(id));
//        } finally {
//            tx.commit();
//            session.close();
//        }
//    }
//
//    @Override
//    public User get(int id) {
//        final Session session = factory.openSession();
//        Transaction tx = session.beginTransaction();
//        try {
//            return (User) session.get(User.class, id);
//        } finally {
//            tx.commit();
//            session.close();
//        }
//    }
//
//
//    @Override
//    public int generateId() {
//        return 0;
//    }
//
//    @Override
//    public void close() {
//        this.factory.close();
//    }
//}