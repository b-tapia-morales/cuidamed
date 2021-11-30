package com.bairontapia.projects.cuidamed.medicalrecord.stats;

import com.bairontapia.projects.cuidamed.daotemplate.ReadOnlyDAO;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HealthSystemStatsDAO implements
    ReadOnlyDAO<HealthSystemStats, Short> {

  private static final HealthSystemStatsDAO INSTANCE = new HealthSystemStatsDAO();
  private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
      .relativePathString("scripts", "class_queries", "stats");
  private static final Path FIND_ALL_QUERY_PATH = Path
      .of(RELATIVE_PATH_STRING, "healthy_system.sql");

  public static HealthSystemStatsDAO getInstance() {
    return INSTANCE;
  }

  @Override
  public String findQuery() throws IOException {
    return null;
  }

  @Override
  public String findAllQuery() throws IOException {
    return TextFileUtils.readString(FIND_ALL_QUERY_PATH);
  }

  @Override
  public HealthSystemStats readTuple(ResultSet resultSet) throws SQLException {
    final var healthSystem = resultSet.getShort(1);
    final var frequency = resultSet.getInt(2);
    return HealthSystemStats.createInstance(healthSystem, frequency);
  }

  @Override
  public void setKeyParameter(PreparedStatement statement, Short aShort) throws SQLException {

  }
}
