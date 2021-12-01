package com.bairontapia.projects.cuidamed.medicalrecord.stats;

import com.bairontapia.projects.cuidamed.daotemplate.ReadOnlyDAO;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import com.bairontapia.projects.cuidamed.utils.paths.FilePathUtils;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.apache.commons.io.IOUtils;

public class BloodTypeStatsDAO implements ReadOnlyDAO<BloodTypeStats, Short> {

  private static final BloodTypeStatsDAO INSTANCE = new BloodTypeStatsDAO();

  private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();

  private static final String RELATIVE_PATH_STRING =
      DirectoryPathUtils.pathBuilder("scripts", "class_queries", "stats");
  private static final String FIND_ALL_QUERY_PATH = RELATIVE_PATH_STRING + "blood_type.sql";

  public static BloodTypeStatsDAO getInstance() {
    return INSTANCE;
  }

  @Override
  public String findQuery() throws IOException {
    return null;
  }

  @Override
  public String findAllQuery() throws IOException {
    final var inputStream = CLASS_LOADER.getResourceAsStream(FIND_ALL_QUERY_PATH);
    return IOUtils.toString(Objects.requireNonNull(inputStream), Charset.defaultCharset());
  }

  @Override
  public void setKeyParameter(PreparedStatement statement, Short id) throws SQLException {
  }

  @Override
  public BloodTypeStats readTuple(ResultSet resultSet) throws SQLException {
    final var bloodType = resultSet.getShort(1);
    final var frequency = resultSet.getInt(2);
    return BloodTypeStats.createInstance(bloodType, frequency);
  }

}
