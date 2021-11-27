package com.bairontapia.projects.cuidamed.medicalrecord;

import com.bairontapia.projects.cuidamed.daotemplate.GenericReadOnlyDAO;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BloodTypeStatsDAO implements GenericReadOnlyDAO<BloodTypeStats, Short> {

  private static final String RELATIVE_PATH_STRING =
      DirectoryPathUtils.relativePathString("scripts", "class_queries", "blood_type_stats");
  private static final Path FIND_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path FIND_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static BloodTypeStatsDAO INSTANCE = new BloodTypeStatsDAO();

  public static BloodTypeStatsDAO getInstance() {
    return INSTANCE;
  }

  @Override
  public String findQuery() throws IOException {
    return TextFileUtils.readString(FIND_QUERY_PATH);
  }

  @Override
  public String findAllQuery() throws IOException {
    return TextFileUtils.readString(FIND_ALL_QUERY_PATH);
  }

  @Override
  public BloodTypeStats readTuple(ResultSet resultSet) throws SQLException {
    final var bloodType = resultSet.getShort(1);
    final var frequency = resultSet.getInt(2);
    return BloodTypeStats.createInstance(bloodType, frequency);
  }

  @Override
  public void setKeyParameter(PreparedStatement statement, Short aShort) throws SQLException {
  }
}
