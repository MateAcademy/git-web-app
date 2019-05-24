package dao;

import model.GoodHib;

import java.util.Optional;

public interface GoodDao extends GenericDao<GoodHib> {

    Optional<GoodHib> getGoodByIdOptional(long id);
}
