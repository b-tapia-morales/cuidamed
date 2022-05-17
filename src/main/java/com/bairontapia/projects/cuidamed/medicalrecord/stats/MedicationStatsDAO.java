package com.bairontapia.projects.cuidamed.medicalrecord.stats;

import com.bairontapia.projects.cuidamed.daotemplate.ReadOnlyDAO;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.apache.commons.io.IOUtils;

public class MedicationStatsDAO implements ReadOnlyDAO<MedicationStats, String> {

  private static final MedicationStatsDAO INSTANCE = new MedicationStatsDAO();

  private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();

  private static final String RELATIVE_PATH_STRING =
      DirectoryPathUtils.pathBuilder("scripts", "class_queries", "stats");
  private static final String FIND_ALL_QUERY_PATH = RELATIVE_PATH_STRING + "medication.sql";

  public static MedicationStatsDAO getInstance() {
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
  public MedicationStats readTuple(ResultSet resultSet) throws SQLException {
    final var nameMedication = resultSet.getString(1);
    final var frequency = resultSet.getInt(2);
    return MedicationStats.createInstance(nameMedication, frequency);
  }

  @Override
  public void setKeyParameter(PreparedStatement statement, String s) throws SQLException {
  }
}
