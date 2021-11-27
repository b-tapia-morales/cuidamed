package com.bairontapia.projects.cuidamed.medicalrecord.allergy;

import com.bairontapia.projects.cuidamed.daotemplate.GenericCrudDAO;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AllergyDAO implements GenericCrudDAO<Allergy, String> {
  private static final AllergyDAO INSTANCE = new AllergyDAO();
  private static final String RELATIVE_PATH_STRING =
      DirectoryPathUtils.relativePathString("scripts", "class_queries", "allergy");
  private static final Path GET_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path GET_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static final Path SAVE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "save.sql");
  private static final Path UPDATE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "update.sql");

  public static AllergyDAO getInstance() {
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
  public void saveTuple(PreparedStatement statement, Allergy allergy) throws SQLException {
    statement.setString(1, allergy.rut());
    statement.setShort(2, (short) allergy.type().getIndex());
    statement.setString(3, allergy.details());
    statement.executeUpdate();
  }

  @Override
  public Allergy readTuple(ResultSet resultSet) throws SQLException {
    final var rut = resultSet.getString(1);
    final var allergyType = resultSet.getShort(2);
    final var name = resultSet.getString(3);
    return Allergy.createInstance(rut, allergyType, name);
  }

  @Override
  public void updateTuple(PreparedStatement statement, Allergy allergy) throws SQLException {
    statement.setShort(1, (short) allergy.type().getIndex());
    statement.setString(2, allergy.details());
    statement.setString(3, allergy.rut());
    statement.executeUpdate();
  }
}
