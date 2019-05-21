package dao;

import model.User;

import java.util.Collection;

public interface Storage {

    public Collection<User> values();

    public long add(final User user);

    public void edit(final User user);

    public void delete(final long id);

    public User get(final int id);

    public User findByLogin(final String login) ;

    public int generateId();

    public void close();
}