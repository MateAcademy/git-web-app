package dao.impl;

import dao.GoodDao;
import model.Good;
import org.apache.log4j.Logger;
import utils.HibernateSessionFactoryUtil;

import java.util.Optional;

public class GoodDaoImplHibernate extends GenericDaoImpl<Good> implements GoodDao {

    final static Logger logger = Logger.getLogger(GoodDaoImplHibernate.class);

    @Override
    public Optional<Good> getGoodByIdOptional(long  id) {
        return Optional.ofNullable(HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession().
                get(Good.class, id));
    }

    public Good getGoodById(long id) {
        return HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession().
                get(Good.class, id);
    }
}

