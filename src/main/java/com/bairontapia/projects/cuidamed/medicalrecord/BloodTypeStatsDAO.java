package com.bairontapia.projects.cuidamed.medicalrecord;

import com.bairontapia.projects.cuidamed.connection.ConnectionSingleton;
import com.bairontapia.projects.cuidamed.daotemplate.GenericReadOnlyDAO;
import com.bairontapia.projects.cuidamed.daotemplate.ReadOnlyDAO;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;

public class BloodTypeStatsDAO implements GenericReadOnlyDAO<BloodTypeStats, Short> {
  private static BloodTypeStatsDAO INSTANCE = new BloodTypeStatsDAO();
  private static final String RELATIVE_PATH_STRING =
      DirectoryPathUtils.relativePathString("scripts", "class_queries", "blood_type_stats");
  private static final Path GET_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path GET_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");

  public static BloodTypeStatsDAO getInstance() {
    return INSTANCE;
  }

  @Override
  public String getQuery() throws IOException {
    return TextFileUtils.readString(GET_QUERY_PATH);
  }

  @Override
  public String getAllQuery() throws IOException {
    return TextFileUtils.readString(GET_ALL_QUERY_PATH);
  }

  @Override
  public BloodTypeStats readTuple(ResultSet resultSet) throws SQLException {
    final var bloodType = resultSet.getShort(1);
    final var frequency = resultSet.getInt(2);
    return BloodTypeStats.createInstance(bloodType, frequency);
  }

  @Override
  public void setKeyParameter(PreparedStatement statement, Short bloodType) throws SQLException {
    statement.setShort(1, bloodType);
  }
}
