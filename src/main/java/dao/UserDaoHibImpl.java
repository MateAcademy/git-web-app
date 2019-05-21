package dao;

import model.User;
import net.sf.ehcache.transaction.xa.commands.Command;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import utils.HashUtil;
import utils.HibernateSessionFactoryUtil;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static javax.swing.UIManager.get;

public class UserDaoHibImpl {


    private static final Logger LOGGER = Logger.getLogger(UserDaoHibImpl.class);

//    public static void deleteUser(User user) {
//        Session session = HibernateSessionFactoryUtil
//                .getSessionFactory()
//                .openSession();
//        session.beginTransaction();
//        session.delete(user);
//        session.getTransaction().commit();
//        session.close();
//    }
    public static void add(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
        LOGGER.debug("User added!");
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

    //TODO : 1
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
 //       final Query query = session.createQuery("update users set password=:password  where name=:name");

        Query queryFindByLogin = session.createQuery("from User where name = :name");
        queryFindByLogin.setString("name", name);
        queryFindByLogin.setParameter("password", password);

//        query.setString("password", password);
 //       query.setString("name", name);
//        int result = query.executeUpdate();
        tx1.commit();
        session.close();


////            Query query = session
////                    .createQuery("SELECT user FROM User user WHERE user.username = :username", User.class);
////            query.setParameter("username", name);
////  //          user = (User) query.getSingleResult();
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
//        User user = null;
//        User user = null;


//            public User findByAuth ( final String login, final String password){
//            return transaction(new Command<User>() {
//                @Override
//                public User process(Session session) {
//                    final Query query = session.createQuery("from User as user where user.login=:login and user.password=:password");
//                    query.setString("login", login);
//                    query.setString("password", password);
//                    List<User> users = query.list();
//
//                    return users.isEmpty() ? null : users.iterator().next();
//                }
//            });
//        }

//    public User getUser(String login) {
//        Session session = HibernateSessionFactoryUtil
//                .getSessionFactory()
//                .openSession();
//        Query queryFindByLogin = session.createQuery("from User where login = :login");
//        queryFindByLogin.setParameter("login", login);
//        List<User> findUserByLogin = queryFindByLogin.list();
//        LOGGER.debug("Get user with login " + login);
//        return findUserByLogin.get(0);
//    }
//}
//    public static Optional<User> getUserByLogin(String name) {
//        Session session;
//        try {
//            session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
//        } catch (Exception e) {
//            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        }
//        try {
//            Criteria criteria = session.createCriteria(User.class);
//            User user = (User) criteria.add(Restrictions.eq("name", name)).uniqueResult();
//            return Optional.of(user);
//        } catch (Exception e) {
//           // logger.debug("Error in getting user " + login, e);
//            return Optional.empty();
//        }
//    }

//        User user = null;
////        try {
////            Session session = HibernateSessionFactoryUtil
////                    .getSessionFactory()
////                    .openSession();
////            Query query = session
////                    .createQuery("SELECT user FROM User user WHERE user.username = :username", User.class);
////            query.setParameter("username", name);
////  //          user = (User) query.getSingleResult();
////        } catch (NullPointerException e) {
////            LOGGER.error("Couldn't get user by name", e);
////        }
////        LOGGER.debug("Returned user`s name");
////        return user   public User getUserByName(String name) {
// ;
//    }


//    public static User getUserByLogin(String login) {
//        User returnUser = new User();
//        List<User> users = getAllUsers();
//        for (User user : users) {
//            if (user.getName().equals(login)) {
//                returnUser = user;
//            }
//        }
//        return returnUser;
//    }


    public static User getUserById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);

    }

//
//    public static  boolean isUserExists(User user) {
//        User newUser = getUserByLogin(user.getName());
//        return newUser.getName() != null;
//    }
//
//
//    public static  String getUserRole(User user) {
//        return getUserByLogin(user.getName()).getRole().toString();
//    }


    public static List<User> getAllUsers() {
        return (List<User>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User").list();
    }
}
//    public List<User> getAllUsers() {
//        List<User> users = (List<User>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User").list();
//        return users;
//    }
//
//    public void addUser(User user) {
//        try {
//            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//            session.beginTransaction();
//            user.setPassword(HashUtil.getSHA512SecurePassword(user.getPassword(), user.getSalt()));
//            session.save(user);
//            session.getTransaction().commit();
//            session.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static Optional<User> getUserByLogin(String name) {
//        Session session;
//        try {
//            session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
//        } catch (Exception e) {
//            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        }
//        try {
//            Criteria criteria = session.createCriteria(User.class);
//            User user = (User) criteria.add(Restrictions.eq("name", name)).uniqueResult();
//            return Optional.of(user);
//        } catch (Exception e) {
//           // logger.debug("Error in getting user " + login, e);
//            return Optional.empty();
//        }
//    }
//
//
//}
//    private final SessionFactory factory;

//    public UserDaoHibImpl() {
//        factory = new Configuration().configure().buildSessionFactory();
//    }
//
//    public UserDaoHibImpl() {
//        factory = new Configuration().configure().buildSessionFactory();
//    }
//
//
//    public interface Command<T> {
//        T process(Session session);
//    }
//
//    private <T> T transaction(final Command<T> command) {
//        final Session session = factory.openSession();
//        final Transaction tx = session.beginTransaction();
//        try {
//            return command.process(session);
//        } finally {
//            tx.commit();
//            session.close();
//        }
//    }
//
//    @Override
//    public Collection<User> values() {
//        return transaction((Session session) -> session.createQuery("from User").list());
//    }
//
//    @Override
//    public Long add(final User user) {
//        return transaction(
//                (Session session) -> {
//                    session.save(user);
//                    return user.getId();
//                }
//        );
//    }
//
//    public void edit(final User user) {
//        transaction(
//                (Session session) -> {
//                    session.update(user);
//                    return null;
//                }
//        );
//    }
//
//
//    @Override
//    public void delete(final Long id) {
//        transaction(new Command() {
//            @Override
//            public Object process(Session session) {
//                session.delete(get(id));
//                return null;
//            }
//        });
//    }
//
//
//    @Override
//    public User get(final Long id) {
//        return transaction(new Command<User>() {
//            @Override
//            public User process(Session session) {
//                return (User) session.get(User.class, id);
//            }
//        });
//    }
//
//
//    @Override
//    public User findByLogin(String login) {
//        return transaction(new Command<User>() {
//            @Override
//            public User process(Session session) {
//                final Query query = session.createQuery("from User as user where user.login=:login");
//                query.setString("login", login);
//                return (User) query.iterate().next();
//            }
//        });
//    }
//
//    public List<User> findByRoleName(final String roleName) {
//        return transaction(new Command<List<User>>() {
//            @Override
//            public List<User> process(Session session) {
//                final Query query = session.createQuery("from User as user inner join user.role as role on role.name=:name");
//                query.setString("name", roleName);
//                return query.list();
//            }
//        });
//
//        public List<User> serchByLogin ( final String login){
//            return transaction(new Command<List<User>>() {
//                @Override
//                public List<User> process(Session session) {
//                    final Query query = session.createQuery("from User as user where lower(user.login) like %:login%");
//                    query.setString("login", login);
//                    return query.list();
//                }
//            });
//        }
//
//        public User findByAuth ( final String login, final String password){
//            return transaction(new Command<User>() {
//                @Override
//                public User process(Session session) {
//                    final Query query = session.createQuery("from User as user where user.login=:login and user.password=:password");
//                    query.setString("login", login);
//                    query.setString("password", password);
//                    List<User> users = query.list();
//
//                    return users.isEmpty() ? null : users.iterator().next();
//                }
//            });
//        }
//                  "UPDATE users SET password = '" + password + "' WHERE name='" + name + "';";
//
//        public int generateId () {
//            return 0;
//        }
//
//        public void close () {
//            this.factory.close();
//        }
//    }
//}

//    public List<User> getAllUsers() {
//        List<User> users = (List<User>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User").list();
//        return users;
//    }
//
//    public void addUser(User user) {
//        try {
//            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//            session.beginTransaction();
//            user.setPassword(HashUtil.getSHA512SecurePassword(user.getPassword(), user.getSalt()));
//            session.save(user);
//            session.getTransaction().commit();
//            session.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public Optional<User> getUserByLogin(String name) {
//        Session session;
//        try {
//            session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
//        } catch (Exception e) {
//            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        }
//        try {
//            Criteria criteria = session.createCriteria(User.class);
//            User user = (User) criteria.add(Restrictions.eq("name", name)).uniqueResult();
//            return Optional.of(user);
//        } catch (Exception e) {
//           // logger.debug("Error in getting user " + login, e);
//            return Optional.empty();
//        }
//    }
//
//    public void delete(User user) {
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        session.delete(user);
//        session.getTransaction().commit();
//        session.close();
//    }
//
//    public User findUserById(Long id) {
//        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
//    }
//
//    public void deleteById(Long id) {
//        User user =HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
//        delete(user);
//    }


//    public Optional<User> getUserByLogin(String name) {
//        Optional<User> user = Optional.of(new User("ava", "1111"));
//        return  user;
//    }


//    public List<User> serchByRoleName(final String login) {
//        return transaction(new Command<List<User>>(){
//            @Override
//            public List<User> process(Session session) {
//                final Query query = session.createQuery("from User as user where lower(user.login) like %:login%");
//                query.setString("name", login);
//                return query.list();
//            }
//        });
//    }
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
//    public Long add(final User user) {
//        final Session session = factory.openSession();
//        Transaction tx = session.beginTransaction();
//        try {
//             session.save(user);
//             return user.getId();
//        } finally {
//            tx.commit();
//            session.close();
//        }
//    }

//    public void delete(Long id) {
//        final Session session = factory.openSession();
//        Transaction tx = session.beginTransaction();
//        try {
//             session.delete(new User(null, null, null, null, null));
//        } finally {
//            tx.commit();
//            session.close();
//        }
//    }

//    public User get(int id) {
//        final Session session = factory.openSession();
//        Transaction tx = session.beginTransaction();
//        try {
//
//            return (User) session.get(User.class, id);
//        } finally {
//            tx.commit();
//            session.close();
//        }
//    }


//    @Override
//    public User findByLogin(String login) {
//        final Session session = factory.openSession();
//        Transaction tx = session.beginTransaction();
//        try {
//            final Query query = session.createQuery("from User as user where user.login=:login");
//            query.setString("login", login);
//            return (User) query.iterate().next();
//        } finally {
//            tx.commit();
//            session.close();
//        }
//    }


//    public List<User> findByRoleName(final String roleName) {
//        return transaction(new Command<List<User>>(){
//            @Override
//            public List<User> process(Session session) {
//                final Query query = session.createQuery("from User as user inner join user.role as role on role.name=:name");
//                query.setString("name", roleName);                                    user.role_id = role user id
//                return query.list();
//            }
//        });
//    }

