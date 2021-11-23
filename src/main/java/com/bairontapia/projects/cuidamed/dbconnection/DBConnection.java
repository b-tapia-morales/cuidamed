package com.bairontapia.projects.cuidamed.dbconnection;
import java.sql.*;
import java.util.*;

public class DBConnection{

  private static DBConnection instance;
  private String url="jdbc:postgresql://10.4.3.195:5432/medicamentos?currentSchema=residence";
  private String login="medicamentos_dev";
  private String pass="AsJ65j54";

  private DBConnection(){

    try {
      Class.forName("org.postgresql.Driver");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static Connection getConnection() throws SQLException {
    if (instance == null) {
      instance = new DBConnection();
    }
    try {
      return DriverManager.getConnection(instance.url, instance.login,instance.pass);
    } catch (SQLException e) {
      throw e;
    }
  }

  public static void close(Connection connection)
  {
    try {
      if (connection != null) {
        connection.close();
        connection=null;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
