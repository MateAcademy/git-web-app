package dao.jdbc;

import model.User;
import org.apache.log4j.Logger;
import utils.HashUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDaoJdbc {

    private static final Logger logger = Logger.getLogger(UserDaoJdbc.class);
    private final Connection connection;

    public UserDaoJdbc() {
        this.connection = DbConnector.connect();
    }

    public int addUser(User user) {
        try {
            String sql = "INSERT INTO madb.users (name , password, email, role, salt) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, HashUtil.getSHA512SecurePassword(user.getPassword(), user.getSalt()));
            statement.setString(3, user.getEmail());
            statement.setLong(4, user.getRole().getId());
            statement.setString(5, user.getSalt());
            int result = statement.executeUpdate();
            logger.debug(sql);
            return result;
        } catch (SQLException e) {
            logger.error("Can't add user", e);
            return 0;
        }
    }

//    public List<User> getAllUsers() {
//        List<User> list = new ArrayList<>();
//        try {
//            logger.debug("");
//            Statement statement = connection.createStatement();
//            String sql = "SELECT * FROM madb.users";
//            ResultSet resultSet = statement.executeQuery(sql);
//            logger.debug("We send sql getALLUsers: " + sql);
//            while (resultSet.next()) {
//                Long id = resultSet.getLong("id");
//                String name = resultSet.getString("name");
//                String password = resultSet.getString("password");
//                User user = new User(id, name, password);
//                list.add(user);
//            }
//        } catch (SQLException e) {
//            logger.error("Error, we can't getAllUsers", e);
//        }
//        return list;
//    }

    public void deleteUser(User user) {
        try {
            String query = "DELETE FROM madb.users WHERE name='" + user.getName() + "' and password = '" + user.getPassword() + "';";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editPasswordUser(String name, String password) {
        try {
            String query = "UPDATE users SET password = '" + password + "' WHERE name='" + name + "';";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public Optional<User> getUserByName(String name) {
//        try {
//            final String sql = "SELECT * FROM madb.users WHERE name = ?;";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, name);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            logger.debug(sql);
//            if (resultSet.next()) {
//                Long userId = resultSet.getLong("id");
//                String nameUser = resultSet.getString("name");
//                String password = resultSet.getString("password");
//                String email = resultSet.getString("email");
//                Role role = resultSet.getInt("role");
//                String salt = resultSet.getString("salt");
//                User user = new User(userId, nameUser, password, email, role, salt);
//                return Optional.of(user);
//            }
//        } catch (SQLException e) {
//            logger.error("Can't get user by name ", e);
//        }
//        return Optional.empty();
//    }

//        public int add(User user){
//        String sql = "INSERT INTO madb.users (name , password, email, role, salt) VALUES (?, ?, ?, ?, ?);";
//        try (PreparedStatement statement = connection.prepareStatement(sql)){
//            statement.setString(1, user.getName());
//            statement.setString(2, HashUtil.getSHA512SecurePassword(user.getPassword(), user.getSalt()));
//            statement.setString(3, user.getEmail());
//            statement.setInt(4, user.getRole());
//            statement.setString(5, user.getSalt());
//            int result = statement.executeUpdate();
//            logger.debug(sql);
//            return result;
//        } catch (SQLException e) {
//            logger.error("Error! Can't add user", e);
//            return 0;
//        }finally {
//            try {
//                connection.close();
//                logger.debug("Connection for add was successfully close");
//            } catch (SQLException e) {
//                logger.error("Error! Connection for add wasn't close", e);
//            }
//        }
//    }

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

//    public void add(User user) {
//        try {
//            Statement statement = connection.createStatement();
//            String sql = "INSERT INTO madb.users(name, password) VALUES ('" + user.getName() + "','" + user.getPassword() + "');";
//            System.out.println(sql);
//            statement.execute(sql);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

//    public boolean getUser(User newUser) {
//        try {
//            Statement statement = connection.createStatement();
//            String sql = "SELECT * FROM madb.users WHERE name='" + newUser.getName() + "' and password = '" + newUser.getPassword() + "';";
//            boolean userInDatabase = false;
//            ResultSet resultSet = statement.executeQuery(sql);
//            while (resultSet.next()) {
//                if (resultSet.getString("name").equals(newUser.getName()) & resultSet.getString("password").equals(newUser.getPassword())) {
//                    userInDatabase = true;
//                }
//            }
//            return userInDatabase;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }


//    //TODO пусть возвращает кол-во измененных строк, если 0 или не 1 то что-то с этим делать
//    public void updateUser(User user) {
//        try {
//            logger.debug("We update user, we send updateUser request");
//            PreparedStatement statement = DbConnector.connect()
//                    .prepareStatement("UPDATE users SET name=?, password=?, email=?, role=?, salt=?  WHERE name=?");
//            statement.setString(1, user.getName());
//            statement.setString(2, HashUtil.getSHA512SecurePassword(user.getPassword(), user.getSalt()));
//            statement.setString(3, user.getEmail());
//            statement.setInt(4, user.getRole());
//            statement.setString(5, user.getSalt());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            logger.error("wrong request data" + e);
//        }
//    }

    //    public int add(User user) {
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
}


