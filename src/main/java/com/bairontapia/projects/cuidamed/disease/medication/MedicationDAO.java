package com.bairontapia.projects.cuidamed.disease.medication;

import com.bairontapia.projects.cuidamed.daotemplate.CrudDAO;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.apache.commons.io.IOUtils;

public class MedicationDAO implements CrudDAO<Medication, String> {

  private static final MedicationDAO INSTANCE = new MedicationDAO();
  private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();

  private static final String RELATIVE_PATH_STRING =
      DirectoryPathUtils.pathBuilder("scripts", "class_queries", "medication");
  private static final String FIND_ALL_QUERY_PATH = RELATIVE_PATH_STRING + "get_all.sql";
  private static final String FIND_QUERY_PATH = RELATIVE_PATH_STRING + "get.sql";
  private static final String SAVE_QUERY_PATH = RELATIVE_PATH_STRING + "save.sql";
  private static final String UPDATE_QUERY_PATH = RELATIVE_PATH_STRING + "update.sql";

  public static MedicationDAO getInstance() {
    return INSTANCE;
  }

  @Override
  public String findQuery() throws IOException {
    final var inputStream = CLASS_LOADER.getResourceAsStream(FIND_QUERY_PATH);
    return IOUtils.toString(Objects.requireNonNull(inputStream), Charset.defaultCharset());
  }

  @Override
  public String findAllQuery() throws IOException {
    final var inputStream = CLASS_LOADER.getResourceAsStream(FIND_ALL_QUERY_PATH);
    return IOUtils.toString(Objects.requireNonNull(inputStream), Charset.defaultCharset());
  }

  @Override
  public String saveQuery() throws IOException {
    final var inputStream = CLASS_LOADER.getResourceAsStream(SAVE_QUERY_PATH);
    return IOUtils.toString(Objects.requireNonNull(inputStream), Charset.defaultCharset());
  }

  @Override
  public String updateQuery() throws IOException {
    final var inputStream = CLASS_LOADER.getResourceAsStream(UPDATE_QUERY_PATH);
    return IOUtils.toString(Objects.requireNonNull(inputStream), Charset.defaultCharset());
  }

  @Override
  public void setKeyParameter(PreparedStatement statement, String nameMedication)
      throws SQLException {
    statement.setString(1, nameMedication);
  }

  @Override
  public void saveTuple(PreparedStatement statement, Medication medication) throws SQLException {
    statement.setString(1, medication.name());
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
    statement.setString(4, medication.name());
  }

  @Override
  public Medication readTuple(ResultSet resultSet) throws SQLException {
    final var nameMedication = resultSet.getString(1);
    final var administrationRoute = resultSet.getShort(2);
    final var pharmaceuticalForm = resultSet.getShort(3);
    final var measureUnit = resultSet.getShort(4);
    return Medication.createInstance(
        nameMedication, administrationRoute, pharmaceuticalForm, measureUnit);
  }
}
