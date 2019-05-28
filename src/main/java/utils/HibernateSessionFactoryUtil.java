package utils;

import model.Good;
import model.Order;
import model.Role;
import model.User;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static final Logger logger = Logger.getLogger(HibernateSessionFactoryUtil.class);
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
//        Class.forName("com.mysql.jdbc.Driver");
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Role.class);
                configuration.addAnnotatedClass(Good.class);
                configuration.addAnnotatedClass(Order.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
                logger.debug("Session opened in HibernateSessionFactoryUtil");
            } catch (Exception e) {
                logger.warn("Warn! Session didn't open in HibernateSessionFactoryUtil", e);
            }
        }
        return sessionFactory;
    }
}