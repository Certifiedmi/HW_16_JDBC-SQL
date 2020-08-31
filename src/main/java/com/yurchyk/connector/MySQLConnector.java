package com.yurchyk.connector;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnector {
    private static final String RELATIVE_PATH_TO_PROPERTIES = "src/main/resources/mysql.properties";

    private MySQLConnector() {
    }

    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        FileInputStream in = new FileInputStream(RELATIVE_PATH_TO_PROPERTIES);
        properties.load(in);
        in.close();
        String driver = properties.getProperty("mysql.driver");
        if (driver != null) {
            Class.forName(driver);
        }
        String url = properties.getProperty("mysql.url");
        String username = properties.getProperty("mysql.user");
        String password = properties.getProperty("mysql.password");
        return DriverManager.getConnection(url, username, password);
    }
}