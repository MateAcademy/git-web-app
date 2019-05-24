package dao;

import model.User;

import java.util.Collection;

public interface Storage {

    Collection<User> values();

    long add(final User user);

    void edit(final User user);

    void delete(final long id);

    User get(final int id);

    User findByLogin(final String login);

    int generateId();

    void close();
}