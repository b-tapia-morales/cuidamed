package com.bairontapia.projects.cuidamed.localization;

import com.bairontapia.projects.cuidamed.connection.ConnectionSingleton;
import com.bairontapia.projects.cuidamed.daotemplate.ReadOnlyDAO;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;

public class CommuneDAO implements ReadOnlyDAO<Commune, Short> {

  private static final CommuneDAO INSTANCE = new CommuneDAO();

  private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();

  private static final String RELATIVE_PATH_STRING =
      DirectoryPathUtils.pathBuilder("scripts", "class_queries", "localization", "commune");
  private static final String FIND_QUERY_PATH = RELATIVE_PATH_STRING + "get.sql";
  private static final String FIND_ALL_QUERY_PATH = RELATIVE_PATH_STRING + "get_all.sql";
  private static final String FIND_ALL_BY_PROVINCE_PATH =
      RELATIVE_PATH_STRING + "get_all_by_province.sql";

  public static CommuneDAO getInstance() {
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

  public Collection<Commune> findAll(Short id) throws IOException, SQLException {
    final var inputStream = CLASS_LOADER.getResourceAsStream(FIND_ALL_BY_PROVINCE_PATH);
    final var query =
        IOUtils.toString(Objects.requireNonNull(inputStream), Charset.defaultCharset());
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.prepareStatement(query);
    setKeyParameter(statement, id);
    final var resultSet = statement.executeQuery();
    final var set = new LinkedHashSet<Commune>();
    while (resultSet.next()) {
      final var commune = readTuple(resultSet);
      set.add(commune);
    }
    resultSet.close();
    statement.close();
    return set;
  }

  @Override
  public Commune readTuple(ResultSet resultSet) throws SQLException {
    final var id = resultSet.getShort(1);
    final var communeName = resultSet.getString(2);
    final var provinceId = resultSet.getShort(3);
    return Commune.createInstance(id, communeName, provinceId);
  }

  @Override
  public void setKeyParameter(PreparedStatement statement, Short provinceId) throws SQLException {
    statement.setShort(1, provinceId);
  }
}
