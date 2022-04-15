package com.bairontapia.projects.cuidamed.connection;

import com.bairontapia.projects.cuidamed.utils.properties.PropertyUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionSingleton {

    private static final Connection INSTANCE;

    static {
        Connection connection = null;
        try {
            connection = instantiateConnection();
        } catch (IOException exception) {
            System.out.println("ERROR: Could not read properties file");
            System.exit(1);
        } catch (SQLException exception) {
            System.out.println("ERROR: Could not create connection to database");
            System.exit(1);
        }
        INSTANCE = connection;
    }

    public static Connection instantiateConnection() throws IOException, SQLException {
        var properties = PropertyUtils.getProperties("application.properties");
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");
        return DriverManager.getConnection(url, username, password);
    }

    public static Connection getInstance() {
        return INSTANCE;
    }
}

