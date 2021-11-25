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

public class MedicationDAO implements CrudDAO<Medication, String> {

  private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
      .relativePathString("scripts", "class_queries", "elder");
  private static final Path GET_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path GET_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static final Path SAVE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "save.sql");

  @Override
  public Optional<Medication> get(String nameMedication) throws IOException, SQLException {
    final var query = TextFileUtils.readString(GET_QUERY_PATH);
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.prepareStatement(query);
    statement.setString(1, nameMedication);
    final var resulSet = statement.executeQuery();
    if (resulSet.next()) {
      final var name = resulSet.getString(1);
      final var administrationRoute = resulSet.getShort(2);
      final var pharmaceuticalForm = resulSet.getShort(3);
      final var measureUnit = resulSet.getShort(4);
      return Optional.of(Medication
          .createInstance(name, administrationRoute, pharmaceuticalForm, measureUnit));
    }
    return Optional.empty();
  }

  @Override
  public Collection<Medication> getAll() throws IOException, SQLException {
    final var query = TextFileUtils.readString(GET_ALL_QUERY_PATH);
    final var set = new LinkedHashSet<Medication>();
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.createStatement();
    final var resulSet = statement.executeQuery(query);
    while (resulSet.next()) {
      final var nameMedication = resulSet.getString(1);
      final var administrationRoute = resulSet.getShort(2);
      final var pharmaceuticalForm = resulSet.getShort(3);
      final var measureUnit = resulSet.getShort(4);
      final var medication = Medication
          .createInstance(nameMedication, administrationRoute, pharmaceuticalForm, measureUnit);
      set.add(medication);
    }
    return set;
  }

  @Override
  public void save(Medication medication) throws IOException, SQLException {
    var connection = ConnectionSingleton.getInstance();
    var query = TextFileUtils.readString(SAVE_QUERY_PATH);
    var statement = connection.prepareStatement(query);
    statement.setString(1, medication.nameMedication());
    statement.setShort(2, (short) medication.administrationRoute().getIndex());
    statement.setShort(3, (short) medication.pharmaceuticalForm().getIndex());
    statement.setShort(4, (short) medication.measureUnit().getIndex());
    statement.executeUpdate();
  }

  @Override
  public void update(Medication medication) throws IOException, SQLException {

  }
}
