package com.bairontapia.projects.cuidamed.medicalrecord.routinecheckup;

import com.bairontapia.projects.cuidamed.connection.ConnectionSingleton;
import com.bairontapia.projects.cuidamed.daotemplate.CrudDAO;
import com.bairontapia.projects.cuidamed.medicalrecord.MedicalRecord;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;

public class RoutineCheckupDAO implements CrudDAO<RoutineCheckup, String> {

  private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
      .relativePathString("scripts", "class_queries", "routine_checkup");
  private static final Path GET_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path GET_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");

  @Override
  public Optional<RoutineCheckup> get(String rut) throws IOException, SQLException {
    final var query = TextFileUtils.readString(GET_QUERY_PATH);
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.prepareStatement(query);
    statement.setString(1, rut);
    final var resultSet = statement.executeQuery();
    if (resultSet.next()) {
      final var elderRut = resultSet.getString(1);
      final var checkupDate = resultSet.getDate(2);
      final var height = resultSet.getDouble(3);
      final var weight = resultSet.getDouble(4);
      final var bmi = resultSet.getDouble(5);
      final var heartRate = resultSet.getShort(6);
      final var diastolicPressure = resultSet.getDouble(7);
      final var systolicPressure = resultSet.getDouble(8);
      final var bodyTemperature = resultSet.getDouble(9);
      return Optional.of(RoutineCheckup
          .createInstance(elderRut, checkupDate, height, weight, bmi, heartRate, diastolicPressure,
              systolicPressure, bodyTemperature));
    }
    return Optional.empty();
  }

  @Override
  public Collection<RoutineCheckup> getAll() throws IOException, SQLException {
    final var query = TextFileUtils.readString(GET_ALL_QUERY_PATH);
    final var set = new LinkedHashSet<RoutineCheckup>();
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.createStatement();
    final var resultSet = statement.executeQuery(query);
    while (resultSet.next()) {
      final var rut = resultSet.getString(1);
      final var checkupDate = resultSet.getDate(2);
      final var height = resultSet.getDouble(3);
      final var weight = resultSet.getDouble(4);
      final var bmi = resultSet.getDouble(5);
      final var heartRate = resultSet.getShort(6);
      final var diastolicPressure = resultSet.getDouble(7);
      final var systolicPressure = resultSet.getDouble(8);
      final var bodyTemperature = resultSet.getDouble(9);
      var routineCheckup = RoutineCheckup
          .createInstance(rut, checkupDate, height, weight, bmi, heartRate, diastolicPressure,
              systolicPressure, bodyTemperature);
      set.add(routineCheckup);
    }
    return set;
  }

  @Override
  public void save(RoutineCheckup routineCheckup) throws IOException, SQLException {

  }

  @Override
  public void update(RoutineCheckup routineCheckup) throws IOException, SQLException {

  }
}
