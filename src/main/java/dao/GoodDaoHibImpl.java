package dao;

import model.Good;
import model.GoodHib;
import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import utils.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.Optional;


public class GoodDaoHibImpl {

    final static Logger logger = Logger.getLogger(GoodDaoHibImpl.class);

    public static GoodHib getGoodById(long id) {
        return HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession().
                get(GoodHib.class, id);
    }

    public static Optional<GoodHib> getGoodByIdOptional(long id) {
        return Optional.ofNullable(HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession().
                get(GoodHib.class, id));
    }

//    public static Optional<User> getUserByLoginOptional(String name) {
//        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
//        try (Session session = sessionFactory.openSession()) {
//            Criteria criteria = session.createCriteria(User.class);
//            criteria.add(Restrictions.eq("name", name));
//            LOGGER.error("User with name " + name + " was found");
//            return Optional.ofNullable((User) criteria.uniqueResult());
//        } catch (Exception e) {
//            LOGGER.error("Can't find user with name " + name, e);
//            return Optional.empty();
//        }
//    }


    public static void addGood(GoodHib good) {
        logger.debug("adding good");
        try (Session session = HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.save(good);
            tx1.commit();
        } catch (Exception e) {
            logger.error("Can't add good with name " + good.getName(), e);
        }
    }

    public static void updateGood(GoodHib good) {
        logger.debug("updating good");
        try (Session session = HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.update(good);
            tx1.commit();
        } catch (Exception e) {
            logger.error("Can't update good with name " + good.getName(), e);
        }
    }

//    public static void updateGood(GoodHib good, String newPassword) {
//        try {
//            Session session = HibernateSessionFactoryUtil
//                    .getSessionFactory()
//                    .openSession();
//            session.beginTransaction();
//            user.setPassword(newPassword);
//            session.update(user);
//            session.getTransaction().commit();
//            session.close();
//            LOGGER.debug("User`s password updated");
//        } catch (Exception e) {
//            LOGGER.error("Couldn't update user`s password", e);
//        }
//    }

    public static void deleteGood(long id) {
        logger.debug("deleting good");
        try (Session session = HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession()) {
        Transaction tx1 = session.beginTransaction();
        session.delete(getGoodById(id));
        tx1.commit();
        } catch (Exception e) {
            logger.error("Can't delete good with id: " + id, e);
        }
    }


    public static List<GoodHib> getAllGoods() {
        logger.debug("getting all goods");
        List<GoodHib> goods = (List<GoodHib>) HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession().
                createQuery("From GoodHib").list();
        return goods;
    }
}