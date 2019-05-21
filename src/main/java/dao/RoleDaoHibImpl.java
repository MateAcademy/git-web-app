package dao;

import model.Role;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HashUtil;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class RoleDaoHibImpl {

    public List<Role> getAllRoles() {
        List<Role> roles = (List<Role>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Role").list();
        return roles;
    }

    public void addRole (Role role) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
           Transaction transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();   // logging
        }
    }
}
