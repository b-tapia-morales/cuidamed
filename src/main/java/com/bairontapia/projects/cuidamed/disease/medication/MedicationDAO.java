package com.bairontapia.projects.cuidamed.disease.medication;

import com.bairontapia.projects.cuidamed.daotemplate.GenericCrudDAO;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicationDAO implements GenericCrudDAO<Medication, String> {

  private static final MedicationDAO INSTANCE = new MedicationDAO();
  private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
      .relativePathString("scripts", "class_queries", "medication");
  private static final Path FIND_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path FIND_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static final Path SAVE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "save.sql");
  private static final Path UPDATE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "update.sql");

  public static MedicationDAO getInstance() {
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
  public String saveQuery() throws IOException {
    return TextFileUtils.readString(SAVE_QUERY_PATH);
  }

  @Override
  public String updateQuery() throws IOException {
    return TextFileUtils.readString(UPDATE_QUERY_PATH);
  }

  @Override
  public void setKeyParameter(PreparedStatement statement, String nameMedication)
      throws SQLException {
    statement.setString(1, nameMedication);
  }

  @Override
  public void saveTuple(PreparedStatement statement, Medication medication) throws SQLException {
    statement.setString(1, medication.nameMedication());
    statement.setShort(2, (short) medication.administrationRoute().getIndex());
    statement.setShort(3, (short) medication.pharmaceuticalForm().getIndex());
    statement.setShort(3, (short) medication.measureUnit().getIndex());
    statement.executeUpdate();
  }

  @Override
  public void updateTuple(PreparedStatement statement, Medication medication) throws SQLException {
    statement.setShort(1, (short) medication.administrationRoute().getIndex());
    statement.setShort(2, (short) medication.pharmaceuticalForm().getIndex());
    statement.setShort(3, (short) medication.measureUnit().getIndex());
    statement.setString(4,medication.nameMedication());
  }

  @Override
  public Medication readTuple(ResultSet resultSet) throws SQLException {
    final var nameMedication = resultSet.getString(1);
    final var administrationRoute = resultSet.getShort(2);
    final var pharmaceuticalForm = resultSet.getShort(3);
    final var measureUnit = resultSet.getShort(4);
    return Medication
        .createInstance(nameMedication, administrationRoute, pharmaceuticalForm, measureUnit);
  }
}
