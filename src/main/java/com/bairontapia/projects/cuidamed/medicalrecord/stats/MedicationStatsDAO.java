package com.bairontapia.projects.cuidamed.medicalrecord.stats;

import com.bairontapia.projects.cuidamed.daotemplate.GenericReadOnlyDAO;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicationStatsDAO implements GenericReadOnlyDAO<MedicationStats, String> {

  private static final MedicationStatsDAO INSTANCE = new MedicationStatsDAO();

  private static final String RELATIVE_PATH_STRING =
      DirectoryPathUtils.relativePathString("scripts", "class_queries", "stats");
  private static final Path FIND_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "medication.sql");

  public static MedicationStatsDAO getInstance(){
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
  public MedicationStats readTuple(ResultSet resultSet) throws SQLException {
    final var nameMedication = resultSet.getString(1);
    final var frequency = resultSet.getInt(2);
    return MedicationStats.createInstance(nameMedication, frequency);
  }

  @Override
  public void setKeyParameter(PreparedStatement statement, String s) throws SQLException {

  }
}
