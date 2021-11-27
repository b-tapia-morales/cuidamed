package com.bairontapia.projects.cuidamed.disease.sickelderly;


import com.bairontapia.projects.cuidamed.daotemplate.GenericCrudDAO;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SickElderlyDAO implements GenericCrudDAO<SickElderly, String> {

  private static final SickElderlyDAO INSTANCE = new SickElderlyDAO();
  private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
      .relativePathString("scripts", "class_queries", "sick_elderly");
  private static final Path GET_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static final Path GET_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path UPDATE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "update.sql");
  private static final Path SAVE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "save.sql");

  public static SickElderlyDAO getInstance() {
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
  public void saveTuple(PreparedStatement statement, SickElderly sickElderly) throws SQLException {
    statement.setString(1, sickElderly.rut());
    statement.setString(2, sickElderly.diseaseName());
    statement.setDate(3, Date.valueOf(sickElderly.diagnosisDate()));
    statement.executeUpdate();
  }

  @Override
  public void updateTuple(PreparedStatement statement, SickElderly sickElderly)
      throws SQLException {
    statement.setString(1, sickElderly.diseaseName());
    statement.setDate(2, Date.valueOf(sickElderly.diagnosisDate()));
    statement.setString(3, sickElderly.rut());
    statement.executeUpdate();
  }

  @Override
  public SickElderly readTuple(ResultSet resultSet) throws SQLException {
    final var rut = resultSet.getString(1);
    final var fullName = resultSet.getString(2);
    final var diseaseName = resultSet.getString(3);
    final var diagnosisDate = resultSet.getDate(4);
    return SickElderly.createInstance(rut, diseaseName, diagnosisDate);
  }
}
