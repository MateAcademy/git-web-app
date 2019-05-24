package dao;

import model.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {

    User getUserByName(String name);

    Optional<User> getUserByNameOptional(String name);

    void updateUser(final String name, final String password);

    void updatePassword(User user, String newPassword);

}
