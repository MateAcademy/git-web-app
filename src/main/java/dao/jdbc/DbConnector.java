package dao.jdbc;

import exeption.NoConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnector {
    private static final String url = "jdbc:mysql://localhost:3306/madb?useSSL=false&serverTimezone=UTC";
    private static final String name = "root";
    private static final String pass = "root";

    public static Connection connect() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        final Connection connection = DriverManager.getConnection(url, name, pass);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (Connection) new NoConnection();
    }

}
