package dao;

import java.util.List;

public interface GenericDao<T> {

    void add(T object);

    T getById(Class<T> entityClazz, long id);

    void delete(T object);

    List<T> getAll(Class<T> entityClass);

    void update(T object);
}
