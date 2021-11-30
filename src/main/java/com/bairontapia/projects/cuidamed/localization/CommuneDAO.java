package com.bairontapia.projects.cuidamed.localization;

import com.bairontapia.projects.cuidamed.connection.ConnectionSingleton;
import com.bairontapia.projects.cuidamed.daotemplate.ReadOnlyDAO;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;

public class CommuneDAO implements ReadOnlyDAO<Commune, Short> {

  private static final CommuneDAO INSTANCE = new CommuneDAO();

  private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();

  private static final String RELATIVE_PATH_STRING =
      DirectoryPathUtils.relativePathString("scripts", "class_queries", "localization", "commune");
  private static final Path FIND_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static final Path FIND_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path FIND_ALL_BY_PROVINCE_PATH = Path
      .of(RELATIVE_PATH_STRING, "get_all_by_province.sql");

  public static CommuneDAO getInstance() {
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

  public Collection<Commune> findAll(Short id) throws IOException, SQLException {
    final var query = TextFileUtils.readString(FIND_ALL_BY_PROVINCE_PATH);
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
