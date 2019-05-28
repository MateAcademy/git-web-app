package dao;

import model.Good;

import java.util.Optional;

public interface GoodDao extends GenericDao<Good> {

    Optional<Good> getGoodByIdOptional(long id);
}
