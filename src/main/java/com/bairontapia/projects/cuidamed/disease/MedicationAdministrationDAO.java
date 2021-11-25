package com.bairontapia.projects.cuidamed.disease;

import com.bairontapia.projects.cuidamed.connection.ConnectionSingleton;
import com.bairontapia.projects.cuidamed.daotemplate.CrudDAO;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;

public class MedicationAdministrationDAO implements CrudDAO<MedicationAdministration, String> {

  private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
      .relativePathString("scripts", "class_queries", "medical_record");
  private static final Path GET_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path GET_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static final Path SAVE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "save.sql");
  private static final Path UPDATE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "update.sql");

  @Override
  public Optional<MedicationAdministration> get(String s) throws IOException, SQLException {
    return Optional.empty();
  }

  @Override
  public Collection<MedicationAdministration> getAll() throws IOException, SQLException {
    final var query = TextFileUtils.readString(GET_ALL_QUERY_PATH);
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.createStatement();
    final var resultSet = statement.executeQuery(query);
    final var set = new LinkedHashSet<MedicationAdministration>();
    while (resultSet.next()) {
      final var rut = resultSet.getString(1);
      final var medicationName = resultSet.getString(2);
      final var estimatedDateTime = resultSet.getDate(3);
      final var realDateTime = resultSet.getDate(4);
      final var dosageStatus = resultSet.getShort(5);
      final var carerRut = resultSet.getString(6);
      final var medicationAdministration = MedicationAdministration
          .createInstance(rut, medicationName, estimatedDateTime, realDateTime, dosageStatus,
              carerRut);
      set.add(medicationAdministration);
    }
    return set;
  }

  @Override
  public void save(MedicationAdministration medicationAdministration)
      throws IOException, SQLException {

  }

  @Override
  public void update(MedicationAdministration medicationAdministration)
      throws IOException, SQLException {

  }
}
