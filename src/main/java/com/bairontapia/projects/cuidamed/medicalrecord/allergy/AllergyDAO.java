package com.bairontapia.projects.cuidamed.medicalrecord.allergy;

import com.bairontapia.projects.cuidamed.daotemplate.OneToManyDAO;
import com.bairontapia.projects.cuidamed.daotemplate.ReadAndWriteDAO;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.apache.commons.io.IOUtils;

public class AllergyDAO implements ReadAndWriteDAO<Allergy, String>, OneToManyDAO<Allergy, String> {

  private static final AllergyDAO INSTANCE = new AllergyDAO();

  private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();

  private static final String RELATIVE_PATH_STRING =
      DirectoryPathUtils.pathBuilder("scripts", "class_queries", "medical_record", "allergy");
  private static final String FIND_ALL_QUERY_PATH = RELATIVE_PATH_STRING + "get_all.sql";
  private static final String FIND_QUERY_PATH = RELATIVE_PATH_STRING + "get.sql";
  private static final String SAVE_QUERY_PATH = RELATIVE_PATH_STRING + "save.sql";

  public static AllergyDAO getInstance() {
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
