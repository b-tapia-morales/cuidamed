package com.bairontapia.projects.cuidamed.medicalrecord.allergy;

import com.bairontapia.projects.cuidamed.daotemplate.GenericReadAndWriteDAO;
import com.bairontapia.projects.cuidamed.daotemplate.OneToManyDAO;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AllergyDAO implements GenericReadAndWriteDAO<Allergy, String>,
    OneToManyDAO<Allergy, String> {

  private static final AllergyDAO INSTANCE = new AllergyDAO();

  private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
      .relativePathString("scripts", "class_queries", "medical_record", "allergy");
  private static final Path FIND_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path FIND_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static final Path SAVE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "save.sql");

  public static AllergyDAO getInstance() {
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
  public void saveTuple(PreparedStatement statement, Allergy allergy) throws SQLException {
    statement.setString(1, allergy.rut());
    statement.setShort(2, (short) allergy.type().getIndex());
    statement.setString(3, allergy.details());
    statement.executeUpdate();
  }

  @Override
  public Allergy readTuple(ResultSet resultSet) throws SQLException {
    final var rut = resultSet.getString(1);
    final var type = resultSet.getShort(2);
    final var details = resultSet.getString(3);
    return Allergy.createInstance(rut, type, details);
  }
}
