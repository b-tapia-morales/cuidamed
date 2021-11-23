package com.bairontapia.projects.cuidamed.dbconnection;
import java.sql.*;
import java.util.*;

public class DBConnection {

  private static Connection connection = null;

  static {
    String url = "jdbc:postgresql://10.4.3.195:5432/medicamentos?currentSchema=residence";
    String user = "medicamentos_dev";
    String pass = "AsJ65j54";
    try {
      Class.forName("org.postgresql.Driver");
      connection = DriverManager.getConnection(url, user, pass);
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  public static Connection getConnection() {
    return connection;
  }
}
