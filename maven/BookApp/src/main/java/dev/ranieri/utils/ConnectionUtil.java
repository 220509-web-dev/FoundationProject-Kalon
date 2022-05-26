package dev.ranieri.utils;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection getConnection(){

        try {
            String dbInfo =
            Connection connection = DriverManager.getConnection(url:"")
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
