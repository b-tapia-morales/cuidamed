package com.bairontapia.projects.cuidamed.dbconnection;

import com.bairontapia.projects.cuidamed.utils.properties.PropertyUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {

  public Connection instance;

  public Connection instantiateConnection() {
    Connection connection = null;
    try {
      var properties = PropertyUtils.getProperties("application.properties");
      var url = properties.getProperty("datasource.url");
      var username = properties.getProperty("datasource.username");
      var password = properties.getProperty("datasource.password");
      connection = DriverManager.getConnection(url, username, password);
    } catch (SQLException | IOException exception) {
      System.exit(1);
    }
    return connection;
  }

  public Connection getInstance() throws ClassNotFoundException, SQLException, IOException {
    if (instance == null) {
      instance = instantiateConnection();
    }
    return instance;
  }
}

