package com.bairontapia.projects.cuidamed.medicalrecord.routinecheckup;

import com.bairontapia.projects.cuidamed.daotemplate.OneToManyDAO;
import com.bairontapia.projects.cuidamed.daotemplate.ReadAndWriteDAO;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.apache.commons.io.IOUtils;

public class RoutineCheckupDAO
    implements ReadAndWriteDAO<RoutineCheckup, String>, OneToManyDAO<RoutineCheckup, String> {

  private static final RoutineCheckupDAO INSTANCE = new RoutineCheckupDAO();

  private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();

  private static final String RELATIVE_PATH_STRING =
      DirectoryPathUtils.pathBuilder(
          "scripts", "class_queries", "medical_record", "routine_checkup");
  private static final String FIND_ALL_QUERY_PATH = RELATIVE_PATH_STRING + "get_all.sql";
  private static final String FIND_QUERY_PATH = RELATIVE_PATH_STRING + "get.sql";
  private static final String SAVE_QUERY_PATH = RELATIVE_PATH_STRING + "save.sql";

  public static RoutineCheckupDAO getInstance() {
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
  public void setKeyParameter(PreparedStatement statement, String rut) throws SQLException {
    statement.setString(1, rut);
  }

  @Override
  public RoutineCheckup readTuple(ResultSet resultSet) throws SQLException {
    final var rut = resultSet.getString(1);
    final var checkupDate = resultSet.getDate(2);
    final var height = resultSet.getDouble(3);
    final var weight = resultSet.getDouble(4);
    final var bmi = resultSet.getDouble(5);
    final var heartRate = resultSet.getInt(6);
    final var diastolicPressure = resultSet.getInt(7);
    final var systolicPressure = resultSet.getInt(8);
    final var bodyTemperature = resultSet.getDouble(9);
    return RoutineCheckup.createInstance(
        rut,
        checkupDate,
        height,
        weight,
        bmi,
        heartRate,
        diastolicPressure,
        systolicPressure,
        bodyTemperature);
  }

  @Override
  public void saveTuple(PreparedStatement statement, RoutineCheckup routineCheckup)
      throws SQLException {
    statement.setString(1, routineCheckup.rut());
    statement.setDate(2, Date.valueOf(routineCheckup.checkupDate()));
    statement.setDouble(3, routineCheckup.height());
    statement.setDouble(4, routineCheckup.weight());
    statement.setDouble(5, routineCheckup.bmi());
    statement.setInt(6, routineCheckup.heartRate());
    statement.setInt(7, routineCheckup.diastolicPressure());
    statement.setInt(8, routineCheckup.systolicPressure());
    statement.setDouble(9, routineCheckup.bodyTemperature());
    statement.executeUpdate();
  }
}
