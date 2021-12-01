package com.bairontapia.projects.cuidamed.disease.sickelderly;


import com.bairontapia.projects.cuidamed.daotemplate.CrudDAO;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.apache.commons.io.IOUtils;


public class SickElderlyDAO implements CrudDAO<SickElderly, String> {

  private static final SickElderlyDAO INSTANCE = new SickElderlyDAO();
  private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();

  private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
      .pathBuilder("scripts", "class_queries", "sick_elderly");
  private static final String FIND_QUERY_PATH = RELATIVE_PATH_STRING + "get.sql";
  private static final String FIND_ALL_QUERY_PATH = RELATIVE_PATH_STRING + "get_all.sql";
  private static final String UPDATE_QUERY_PATH = RELATIVE_PATH_STRING + "update.sql";
  private static final String SAVE_QUERY_PATH = RELATIVE_PATH_STRING + "save.sql";

  public static SickElderlyDAO getInstance() {
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
  public String updateQuery() throws IOException {
    final var inputStream = CLASS_LOADER.getResourceAsStream(UPDATE_QUERY_PATH);
    return IOUtils.toString(Objects.requireNonNull(inputStream), Charset.defaultCharset());
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
