package com.bairontapia.projects.cuidamed.disease.medicationprescription;

import com.bairontapia.projects.cuidamed.connection.ConnectionSingleton;
import com.bairontapia.projects.cuidamed.daotemplate.CrudDAO;
import com.bairontapia.projects.cuidamed.daotemplate.OneToManyDAO;
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

public class MedicationPrescriptionDAO implements CrudDAO<MedicationPrescription, String>,
    OneToManyDAO<MedicationPrescription, String> {

  private static final MedicationPrescriptionDAO INSTANCE = new MedicationPrescriptionDAO();
  private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();

  private static final String RELATIVE_PATH_STRING =
      DirectoryPathUtils.pathBuilder("scripts", "class_queries", "medication_prescription");
  private static final String FIND_BY_RUT_AND_DISEASE_NAME = RELATIVE_PATH_STRING +
      "get_by_rut_and_disease_name.sql";
  private static final String FIND_ALL_QUERY_PATH = RELATIVE_PATH_STRING + "get_all.sql";
  private static final String FIND_QUERY_PATH = RELATIVE_PATH_STRING + "get.sql";
  private static final String UPDATE_QUERY_PATH = RELATIVE_PATH_STRING + "update.sql";
  private static final String SAVE_QUERY_PATH = RELATIVE_PATH_STRING + "save.sql";

  public static MedicationPrescriptionDAO getInstance() {
    return INSTANCE;
  }

  public Collection<MedicationPrescription> findByRutAndDiseaseName(String rut, String diseaseName)
      throws IOException, SQLException {
    final var inputStream = CLASS_LOADER.getResourceAsStream(FIND_BY_RUT_AND_DISEASE_NAME);
    final var query =
        IOUtils.toString(Objects.requireNonNull(inputStream), Charset.defaultCharset());
    final var connection = ConnectionSingleton.getInstance();
    try (var statement = connection.prepareStatement(query)) {
      statement.setString(1, rut);
      statement.setString(2, diseaseName);
      final var resultSet = statement.executeQuery();
      final var set = new LinkedHashSet<MedicationPrescription>();
      while (resultSet.next()) {
        final var medicationPrescription = readTuple(resultSet);
        set.add(medicationPrescription);
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
  public void saveTuple(PreparedStatement statement, MedicationPrescription medicationPrescription)
      throws SQLException {
    statement.setString(1, medicationPrescription.rut());
    statement.setString(2, medicationPrescription.diseaseName());
    statement.setDate(3, Date.valueOf(medicationPrescription.prescriptionDate()));
    statement.setString(4, medicationPrescription.medicationName());
    statement.setDate(5, Date.valueOf(medicationPrescription.startDate()));
    statement.setDate(6, Date.valueOf(medicationPrescription.endDate()));
    statement.setShort(7, medicationPrescription.quantity());
    statement.executeUpdate();
  }

  @Override
  public void updateTuple(
      PreparedStatement statement, MedicationPrescription medicationPrescription)
      throws SQLException {
    statement.setString(1, medicationPrescription.diseaseName());
    statement.setDate(2, Date.valueOf(medicationPrescription.prescriptionDate()));
    statement.setString(3, medicationPrescription.medicationName());
    statement.setDate(4, Date.valueOf(medicationPrescription.startDate()));
    statement.setDate(5, Date.valueOf(medicationPrescription.endDate()));
    statement.setShort(6, medicationPrescription.quantity());
    statement.setString(7, medicationPrescription.rut());
    statement.executeUpdate();
  }

  @Override
  public MedicationPrescription readTuple(ResultSet resultSet) throws SQLException {
    final var rut = resultSet.getString(1);
    final var diseaseName = resultSet.getString(2);
    final var prescriptionDate = resultSet.getDate(3);
    final var medicationName = resultSet.getString(4);
    final var startDate = resultSet.getDate(5);
    final var endDate = resultSet.getDate(6);
    final var quantity = resultSet.getShort(7);
    return MedicationPrescription.createInstance(
        rut, diseaseName, prescriptionDate, medicationName, startDate, endDate, quantity);
  }
}
