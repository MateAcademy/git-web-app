package dao;

import model.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {
    Connection connection = DbConnector.connect();
    private static final Logger logger = Logger.getLogger(UserDao.class);

//    public int addUser(User user) {
//        try {
//            Statement statement = connection.createStatement();
//            String name = user.getName();
//            String password = user.getPassword();
//            String email = user.getEmail();
//            Integer role = user.getRole();
//            String sql = "INSERT INTO madb.users(name, password, email, role ) VALUES ('" + name + "','" + password + "','"+ email+ "','" + role +"');";
//            logger.debug(sql);
//            int userAddedOrNo = statement.executeUpdate(sql);
//            return userAddedOrNo;
//        } catch (SQLException e) {
//            logger.error("Can't add user by name", e);
//            return 0;
//        }
//    }

    public int addUser(User user) {
        try {
            String sql = "INSERT INTO madb.users (name , password, email, role) VALUES (?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getRole());
            int result = preparedStatement.executeUpdate();
            logger.debug(sql);
            return result;
        } catch (SQLException e) {
            logger.error("Can't add user", e);
            return 0;
        }
    }

    public Optional<User> getUserByName(String name, String pass) {
        try {
            final String sql = "SELECT * FROM madb.users WHERE name = ? and password = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, pass);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.debug(sql);
            if (resultSet.next()) {
                Long userId = resultSet.getLong(1);
                String nameUser = resultSet.getString(2);
                String password = resultSet.getString(3);
                String email = resultSet.getString(4);
                Integer role = resultSet.getInt(5);
                User user = new User(userId, nameUser, password, email, role);
                                                                        System.out.println(email);
                return Optional.of(user);

            }
        } catch (SQLException e) {
            logger.error("Can't get user by name ", e);
        }
        return Optional.empty();
    }

//    public Optional<User> getUserById(Long id) {
//        try {
//            String sql = "SELECT * FROM madb2.user WHERE id =?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setLong(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                Long userId = resultSet.getLong(1);
//                String nameUser = resultSet.getString(2);
//                String password = resultSet.getString(3);
//                String role = resultSet.getString(4);
//                User user = new User(userId, nameUser, password, role);
//                return Optional.of(user);
//            }
//        } catch (SQLException e) {
//            logger.error("Can't get user by Id ", e);
//        }
//        return Optional.empty();
//    }

//    public void addUser(User user) {
//        try {
//            Statement statement = connection.createStatement();
//            String sql = "INSERT INTO madb.users(name, password) VALUES ('" + user.getName() + "','" + user.getPassword() + "');";
//            System.out.println(sql);
//            statement.execute(sql);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public boolean getUser(User newUser) {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM madb.users WHERE name='" + newUser.getName() + "' and password = '" + newUser.getPassword() + "';";
            boolean userInDatabase = false;
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                if (resultSet.getString("name").equals(newUser.getName()) & resultSet.getString("password").equals(newUser.getPassword())) {
                    userInDatabase = true;
                }

            }
            System.out.println(sql);
            System.out.println("Есть ли такой User в базе данных: " + userInDatabase);
            return userInDatabase;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM madb.users";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                User user = new User(name, password);
                list.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void delUser(User user) {
        try {
            String query = "DELETE FROM madb.users WHERE name='" + user.getName()+"' and password = '"+user.getPassword()+"';";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editUser(String password, String name) {
        try {
            String query = "UPDATE users SET password = '" + password + "' WHERE name='" + name + "';";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


