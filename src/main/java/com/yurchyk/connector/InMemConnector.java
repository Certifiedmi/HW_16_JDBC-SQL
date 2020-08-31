package com.yurchyk.connector;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class InMemConnector {

    private static final String RELATIVE_PATH_TO_PROPERTIES = "src/main/resources/h2.properties";

    private InMemConnector() {
    }

    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        FileInputStream in = new FileInputStream(RELATIVE_PATH_TO_PROPERTIES);
        properties.load(in);
        in.close();
        String driver = properties.getProperty("h2.driver");
        if (driver != null) {
            Class.forName(driver);
        }
        String url = properties.getProperty("h2.url");
        String username = properties.getProperty("h2.username");
        String password = properties.getProperty("h2.password");
        return DriverManager.getConnection(url, username, password);
    }
}