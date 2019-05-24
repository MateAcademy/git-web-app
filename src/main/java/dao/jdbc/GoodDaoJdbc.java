//package dao.jdbc;
//
//import dao.jdbc.DbConnector;
//import dao.jdbc.UserDaoJdbc;
//import model.Good;
//import org.apache.log4j.Logger;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public class GoodDaoJdbc {
//
//    Connection connection = DbConnector.connect();
//    private static final Logger logger = Logger.getLogger(UserDaoJdbc.class);
//    public void delGood(Integer id) {
//        try {
//            String query = "DELETE FROM madb.goods where id = " + id + ";";
//            Statement statement = connection.createStatement();
//            statement.executeUpdate(query);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public int addGood(Good good) {
//        try {
//            String sql = "INSERT INTO madb.goods (name, description, price) VALUES (?, ?, ?);";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, good.getName());
//            preparedStatement.setString(2, good.getDescription());
//            preparedStatement.setDouble(3, good.getPrice());
//
//            int result = preparedStatement.executeUpdate();
//            logger.debug(sql);
//
//            return result;
//        } catch (SQLException e) {
//            logger.error("Can't add good by name", e);
//            return 0;
//        }
//    }
//
//    public Optional<Good> getGoodById(Long id) {
//        try {
//            final String sql = "SELECT * FROM madb.goods WHERE id = ?;";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setLong(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            logger.debug(sql);
//            if (resultSet.next()) {
//                Long goodId = resultSet.getLong(1);
//                String name = resultSet.getString(2);
//                String description = resultSet.getString(3);
//                Double price = resultSet.getDouble(4);
//                Good good = new Good(goodId, name, description, price);
//                return Optional.of(good);
//            }
//        } catch (SQLException e) {
//            logger.error("Can't get good by name ", e);
//        }
//        return Optional.empty();
//    }
//
//    public List<Good> getAllGoods() {
//        List<Good> allGoods = new ArrayList<>();
//        try {
//            final String sql = "SELECT * FROM madb.goods;";
//            Statement statement = connection.createStatement();
//            ResultSet allGoodsResultSet = statement.executeQuery(sql);
//            logger.debug(sql);
//            while (allGoodsResultSet.next()) {
//                Long goodId = allGoodsResultSet.getLong(1);
//                String name = allGoodsResultSet.getString(2);
//                String description = allGoodsResultSet.getString(3);
//                Double price = allGoodsResultSet.getDouble(4);
//                Good good = new Good(goodId, name, description, price);
//                allGoods.add(good);
//            }
//        } catch (SQLException e) {
//            logger.error("Can't get all goods ", e);
//        }
//        return allGoods;
//    }
//
//    public void updateGoods(Good good) {
//        try {
//            logger.debug("We update good, we send update request");
//            PreparedStatement statement = DbConnector.connect()
//                    .prepareStatement("UPDATE madb.goods SET id=?, name=?, description=?, price=?  WHERE id=?");
//            statement.setLong(1, good.getId());
//            statement.setString(2, good.getName());
//            statement.setString(3, good.getDescription());
//            statement.setDouble(4, good.getPrice());
//            statement.setLong(5, good.getId());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            logger.error("Wrong request updateGoods", e);
//        }
//    }
//}
