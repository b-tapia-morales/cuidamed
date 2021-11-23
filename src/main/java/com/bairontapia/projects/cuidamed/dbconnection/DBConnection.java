package com.bairontapia.projects.cuidamed.dbconnection;

import com.bairontapia.projects.cuidamed.utils.properties.PropertyUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class DBConnection {

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
      instance = new DBConnection();
    }
    return instance;
  }
}

