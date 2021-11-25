package com.bairontapia.projects.cuidamed.medicalrecord.surgicalintervention;

import com.bairontapia.projects.cuidamed.connection.ConnectionSingleton;
import com.bairontapia.projects.cuidamed.daotemplate.ReadOnlyDAO;
import com.bairontapia.projects.cuidamed.medicalrecord.BloodTypeStats;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;

public class SurgicalInterventionDAO implements ReadOnlyDAO<SurgicalIntervention, String> {

  private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
      .relativePathString("scripts", "class_queries", "surgical_intervention");
  private static final Path GET_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path GET_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static final Path UPDATE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "update.sql");

  @Override
  public Optional<SurgicalIntervention> get(String rut) throws IOException, SQLException {
    final var query = TextFileUtils.readString(GET_QUERY_PATH);
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.prepareStatement(query);
    statement.setString(1, rut);
    final var resultSet = statement.executeQuery();
    if (resultSet.next()) {
      final var elderRut = resultSet.getString(1);
      final var firstNames = resultSet.getString(2);
      final var lastName = resultSet.getString(3);
      final var secondLastName = resultSet.getString(4);
      final var interventionDate = resultSet.getDate(5);
      final var hospital = resultSet.getString(6);
      final var severity = resultSet.getShort(7);
      final var description = resultSet.getString(8);
      final var surgicalIntervention = SurgicalIntervention
          .createInstance(elderRut, firstNames, lastName, secondLastName, interventionDate,
              hospital,
              severity, description);
      return Optional.of(surgicalIntervention);
    }
    return Optional.empty();
  }

  @Override
  public Collection<SurgicalIntervention> getAll() throws IOException, SQLException {
    final var query = TextFileUtils.readString(GET_ALL_QUERY_PATH);
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.createStatement();
    final var resultSet = statement.executeQuery(query);
    final var set = new LinkedHashSet<SurgicalIntervention>();
    while (resultSet.next()) {
      final var rut = resultSet.getString(1);
      final var firstNames = resultSet.getString(2);
      final var lastName = resultSet.getString(3);
      final var secondLastName = resultSet.getString(4);
      final var interventionDate = resultSet.getDate(5);
      final var hospital = resultSet.getString(6);
      final var severity = resultSet.getShort(7);
      final var description = resultSet.getString(8);
      final var surgicalIntervention = SurgicalIntervention
          .createInstance(rut, firstNames, lastName, secondLastName, interventionDate, hospital,
              severity, description);
      set.add(surgicalIntervention);
    }
    return set;
  }
}
