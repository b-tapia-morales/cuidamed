package com.bairontapia.projects.cuidamed.disease.medicationadministration;

import com.bairontapia.projects.cuidamed.connection.ConnectionSingleton;
import com.bairontapia.projects.cuidamed.daotemplate.CrudDAO;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import org.apache.commons.io.IOUtils;

public class MedicationAdministrationDAO implements CrudDAO<MedicationAdministration, String> {

  private static final MedicationAdministrationDAO INSTANCE = new MedicationAdministrationDAO();
  private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();

  private static final String RELATIVE_PATH_STRING =
      DirectoryPathUtils.pathBuilder("scripts", "class_queries", "medical_administration");
  private static final String FIND_ALL_QUERY_PATH = RELATIVE_PATH_STRING + "get_all.sql";
  private static final String FIND_QUERY_PATH = RELATIVE_PATH_STRING + "get.sql";
  private static final String SAVE_QUERY_PATH = RELATIVE_PATH_STRING + "save.sql";
  private static final String UPDATE_QUERY_PATH = RELATIVE_PATH_STRING + "update.sql";
  private static final String FIND_BY_RUT_AND_MEDICATION_NAME = "get_by_rut_and_medication_name.sql";

  public static MedicationAdministrationDAO getInstance() {
    return INSTANCE;
  }

  public Collection<MedicationAdministration> findByRutAndMedicationName(String rut,
      String medicationName)
      throws IOException, SQLException {
    final var inputStream =
        CLASS_LOADER.getResourceAsStream(FIND_BY_RUT_AND_MEDICATION_NAME);
    final var query =
        IOUtils.toString(Objects.requireNonNull(inputStream), Charset.defaultCharset());
    final var connection = ConnectionSingleton.getInstance();
    try (var statement = connection.prepareStatement(query)) {
      statement.setString(1, rut);
      statement.setString(2, medicationName);
      final var resultSet = statement.executeQuery();
      final var set = new LinkedHashSet<MedicationAdministration>();
      while (resultSet.next()) {
        final var medicationAdministration = readTuple(resultSet);
        set.add(medicationAdministration);
      }
      resultSet.close();
      return set;
    }
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
  public void setKeyParameter(PreparedStatement statement, String rut) throws SQLException {
    statement.setString(1, rut);
  }

  @Override
  public void saveTuple(
      PreparedStatement statement, MedicationAdministration medicationAdministration)
      throws SQLException {
    statement.setString(1, medicationAdministration.rut());
    statement.setString(2, medicationAdministration.medicationName());
    statement.setDate(3, Date.valueOf(medicationAdministration.estimatedDateTime().toLocalDate()));
    statement.setDate(4, Date.valueOf(medicationAdministration.realDatetime().toLocalDate()));
    statement.setShort(5, (short) medicationAdministration.status().getIndex());
    statement.setString(6, medicationAdministration.carerRut());
    statement.executeUpdate();
  }

  @Override
  public void updateTuple(
      PreparedStatement statement, MedicationAdministration medicationAdministration)
      throws SQLException {
    statement.setDate(1, Date.valueOf(medicationAdministration.realDatetime().toLocalDate()));
    statement.setShort(2, (short) medicationAdministration.status().getIndex());
    statement.setString(3, medicationAdministration.rut());
    statement.setString(4, medicationAdministration.medicationName());
    statement.setDate(5, Date.valueOf(medicationAdministration.estimatedDateTime().toLocalDate()));
    statement.executeUpdate();
  }

  @Override
  public MedicationAdministration readTuple(ResultSet resultSet) throws SQLException {
    final var rut = resultSet.getString(1);
    final var medicationName = resultSet.getString(2);
    final var estimatedDateTime = resultSet.getDate(3);
    final var realDateTime = resultSet.getDate(4);
    final var status = resultSet.getShort(5);
    final var carerRut = resultSet.getString(6);
    return MedicationAdministration.createInstance(
        rut, medicationName, estimatedDateTime, realDateTime, status, carerRut);
  }
}
