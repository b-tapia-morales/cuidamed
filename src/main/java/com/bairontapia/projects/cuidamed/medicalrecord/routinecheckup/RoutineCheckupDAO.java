package com.bairontapia.projects.cuidamed.medicalrecord.routinecheckup;

import com.bairontapia.projects.cuidamed.daotemplate.OneToManyDAO;
import com.bairontapia.projects.cuidamed.daotemplate.ReadAndWriteDAO;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoutineCheckupDAO implements ReadAndWriteDAO<RoutineCheckup, String>,
    OneToManyDAO<RoutineCheckup, String> {

  private static final RoutineCheckupDAO INSTANCE = new RoutineCheckupDAO();

  private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();

  private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
      .relativePathString("scripts", "class_queries", "medical_record", "routine_checkup");
  private static final Path FIND_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path FIND_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static final Path SAVE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "save.sql");

  public static RoutineCheckupDAO getInstance() {
    return INSTANCE;
  }

  @Override
  public String findQuery() throws IOException {
    return TextFileUtils.readString(FIND_QUERY_PATH);
  }

  @Override
  public String findAllQuery() throws IOException {
    return TextFileUtils.readString(FIND_ALL_QUERY_PATH);
  }

  @Override
  public String saveQuery() throws IOException {
    return TextFileUtils.readString(SAVE_QUERY_PATH);
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
    final var heartRate = resultSet.getShort(6);
    final var diastolicPressure = resultSet.getDouble(7);
    final var systolicPressure = resultSet.getDouble(8);
    final var bodyTemperature = resultSet.getDouble(9);
    return RoutineCheckup.createInstance(rut, checkupDate, height, weight, bmi, heartRate,
        diastolicPressure, systolicPressure, bodyTemperature);
  }

  @Override
  public void saveTuple(PreparedStatement statement, RoutineCheckup routineCheckup)
      throws SQLException {
    statement.setString(1, routineCheckup.rut());
    statement.setDate(2, Date.valueOf(routineCheckup.checkupDate()));
    statement.setDouble(3, routineCheckup.height());
    statement.setDouble(4, routineCheckup.weight());
    statement.setDouble(5, routineCheckup.bmi());
    statement.setShort(6, routineCheckup.heartRate());
    statement.setDouble(7, routineCheckup.diastolicPressure());
    statement.setDouble(8, routineCheckup.systolicPressure());
    statement.setDouble(9, routineCheckup.bodyTemperature());
    statement.executeUpdate();
  }

}
