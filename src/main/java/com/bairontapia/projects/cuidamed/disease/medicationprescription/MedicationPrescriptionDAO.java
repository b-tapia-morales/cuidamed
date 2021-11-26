package com.bairontapia.projects.cuidamed.disease.medicationprescription;

import com.bairontapia.projects.cuidamed.daotemplate.GenericCrudDAO;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MedicationPrescriptionDAO implements GenericCrudDAO<MedicationPrescription, String> {

  private static final MedicationPrescriptionDAO INSTANCE = new MedicationPrescriptionDAO();
  private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
      .relativePathString("scripts", "class_queries", "medication_prescription");
  private static final Path GET_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path GET_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static final Path UPDATE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "update.sql");
  private static final Path SAVE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "save.sql");

  public static MedicationPrescriptionDAO getInstance() {
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
  public String saveQuery() throws IOException {
    return TextFileUtils.readString(SAVE_QUERY_PATH);
  }

  @Override
  public String updateQuery() throws IOException {
    return TextFileUtils.readString(UPDATE_QUERY_PATH);
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
    statement.setInt(7, medicationPrescription.frequency());
    statement.setInt(8, medicationPrescription.quantity());
    statement.executeUpdate();
  }

  @Override
  public void updateTuple(PreparedStatement statement,
      MedicationPrescription medicationPrescription) throws SQLException {
    statement.setString(1, medicationPrescription.diseaseName());
    statement.setDate(2, Date.valueOf(medicationPrescription.prescriptionDate()));
    statement.setString(3, medicationPrescription.medicationName());
    statement.setDate(4, Date.valueOf(medicationPrescription.startDate()));
    statement.setDate(5, Date.valueOf(medicationPrescription.endDate()));
    statement.setInt(6, medicationPrescription.frequency());
    statement.setInt(7, medicationPrescription.quantity());
    statement.setString(8, medicationPrescription.rut());
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
    final var frequency = resultSet.getInt(7);
    final var quantity = resultSet.getInt(8);
    return MedicationPrescription
        .createInstance(rut, diseaseName, prescriptionDate, medicationName, startDate, endDate,
            frequency, quantity);
  }
}
