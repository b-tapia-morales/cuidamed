package com.bairontapia.projects.cuidamed.disease;

import com.bairontapia.projects.cuidamed.connection.ConnectionSingleton;
import com.bairontapia.projects.cuidamed.daotemplate.CrudDAO;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;

public class MedicationPrescriptionDAO implements CrudDAO<MedicationPrescription, String> {

  private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
      .relativePathString("scripts", "class_queries", "medication_prescription");
  private static final Path GET_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path GET_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static final Path UPDATE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "update");

  @Override
  public Optional<MedicationPrescription> get(String s) throws IOException, SQLException {
    return Optional.empty();
  }

  @Override
  public Collection<MedicationPrescription> getAll() throws IOException, SQLException {
    final var query = TextFileUtils.readString(GET_ALL_QUERY_PATH);
    final var set = new LinkedHashSet<MedicationPrescription>();
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.createStatement();
    final var resultSet = statement.executeQuery(query);
    while (resultSet.next()) {
      final var rut = resultSet.getString(1);
      final var diseaseName = resultSet.getString(2);
      final var prescriptionDate = resultSet.getDate(3);
      final var medicationName = resultSet.getString(4);
      final var startDate = resultSet.getDate(5);
      final var endDate = resultSet.getDate(6);
      final var frequency = resultSet.getInt(7);
      final var quantity = resultSet.getInt(8);
      final var medicationPrescription = MedicationPrescription
          .createInstance(rut, diseaseName, prescriptionDate, medicationName, startDate,
              endDate, frequency, quantity);
      set.add(medicationPrescription);
    }
    return null;
  }

  @Override
  public void save(MedicationPrescription medicationPrescription) throws IOException, SQLException {

  }

  @Override
  public void update(MedicationPrescription medicationPrescription)
      throws IOException, SQLException {
    var connection = ConnectionSingleton.getInstance();
    var query = TextFileUtils.readString(UPDATE_QUERY_PATH);
    var statement = connection.prepareStatement(query);
    statement.setDate(5, Date.valueOf(medicationPrescription.startDate()));
    statement.setInt(7, medicationPrescription.frequency());
    statement.setInt(8, medicationPrescription.quantity());
    statement.executeUpdate();

  }
}
