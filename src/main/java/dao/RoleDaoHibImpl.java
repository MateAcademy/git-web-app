package dao;

import model.Role;
import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HashUtil;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class RoleDaoHibImpl {

    private static Logger logger = Logger.getLogger(RoleDaoHibImpl.class);
    public List<Role> getAllRoles() {
        List<Role> roles = (List<Role>) HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession().
                createQuery("From Role").list();
        return roles;
    }

    public void addRole(Role role) {
        try (Session session = HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
            logger.debug("We add new role in method addRole");
        } catch (Exception e) {
            logger.warn("We can't add new Role in method addRole ", e);
        }
    }
}
