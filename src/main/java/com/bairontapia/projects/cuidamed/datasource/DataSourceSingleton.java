package com.bairontapia.projects.cuidamed.datasource;

import com.bairontapia.projects.cuidamed.utils.properties.PropertyUtils;
import java.io.IOException;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public final class DataSourceSingleton {

  private static final DataSource INSTANCE;

  static {
    DataSource dataSource = null;
    try {
      dataSource = instantiateDataSource();
    } catch (IOException exception) {
      System.out.println("ERROR: Could not read properties file");
      System.exit(1);
    } catch (SQLException exception) {
      System.out.println("ERROR: Could not create data Source");
      System.exit(1);
    }
    INSTANCE = dataSource;
  }

  public static DataSource instantiateDataSource() throws IOException, SQLException {
    final var properties = PropertyUtils.getProperties("application.properties");
    final var dataSource = new BasicDataSource();
    dataSource.setDriverClassName(properties.getProperty("datasource.driver-class-name"));
    dataSource.setUrl(properties.getProperty("datasource.url"));
    dataSource.setUsername(properties.getProperty("datasource.username"));
    dataSource.setPassword(properties.getProperty("datasource.password"));
    return dataSource;
  }

}
