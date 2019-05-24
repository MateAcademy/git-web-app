package dao.impl;

import dao.GoodDao;
import model.GoodHib;
import org.apache.log4j.Logger;
import utils.HibernateSessionFactoryUtil;

import java.util.Optional;

public class GoodDaoImplHibImpl extends GenericDaoImpl<GoodHib> implements GoodDao {

    final static Logger logger = Logger.getLogger(GoodDaoImplHibImpl.class);

    @Override
    public Optional<GoodHib> getGoodByIdOptional(long  id) {
        return Optional.ofNullable(HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession().
                get(GoodHib.class, id));
    }

    public GoodHib getGoodById(long id) {
        return HibernateSessionFactoryUtil.
                getSessionFactory().
                openSession().
                get(GoodHib.class, id);
    }
}

