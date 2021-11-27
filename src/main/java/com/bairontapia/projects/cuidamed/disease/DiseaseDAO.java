package com.bairontapia.projects.cuidamed.disease;


import com.bairontapia.projects.cuidamed.daotemplate.GenericCrudDAO;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiseaseDAO implements GenericCrudDAO<Disease, String> {

  private static final DiseaseDAO INSTANCE = new DiseaseDAO();
  private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
      .relativePathString("scripts", "class_queries", "Disease");
  private static final Path FIND_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static final Path FIND_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path UPDATE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "update.sql");
  private static final Path SAVE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "save.sql");

  public static DiseaseDAO getInstance() {
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
  public String updateQuery() throws IOException {
    return TextFileUtils.readString(UPDATE_QUERY_PATH);
  }

  @Override
  public void setKeyParameter(PreparedStatement statement, String diseaseName) throws SQLException {
    statement.setString(1, diseaseName);
  }

  @Override
  public void saveTuple(PreparedStatement statement, Disease disease) throws SQLException {
    statement.setString(1, disease.disease_name());
    statement.setShort(2, (short) disease.diseaseType().getIndex());
    statement.setBoolean(3, disease.is_chronic());
    statement.executeUpdate();
  }

  @Override
  public void updateTuple(PreparedStatement statement, Disease disease) throws SQLException {

  }

  @Override
  public Disease readTuple(ResultSet resultSet) throws SQLException {
    final var diseaseName = resultSet.getString(1);
    final var diseaseType = resultSet.getShort(2);
    final var isChronic = resultSet.getBoolean(3);
    return Disease.createInstance(diseaseName, diseaseType, isChronic);
  }
}
