package com.bairontapia.projects.cuidamed.localization;

import com.bairontapia.projects.cuidamed.connection.ConnectionSingleton;
import com.bairontapia.projects.cuidamed.daotemplate.ReadOnlyDAO;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import org.apache.commons.io.IOUtils;

public class ProvinceDAO implements ReadOnlyDAO<Province, Short> {

  private static final ProvinceDAO INSTANCE = new ProvinceDAO();

  private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();

  private static final String RELATIVE_PATH_STRING =
      DirectoryPathUtils.pathBuilder("scripts", "class_queries", "localization", "province");
  private static final String FIND_QUERY_PATH = RELATIVE_PATH_STRING + "get.sql";
  private static final String FIND_ALL_QUERY_PATH = RELATIVE_PATH_STRING + "get_all.sql";
  private static final String FIND_ALL_BY_REGION_PATH =
      RELATIVE_PATH_STRING + "get_all_by_region.sql";

  public static ProvinceDAO getInstance() {
    return INSTANCE;
  }

  public Collection<Province> findAll(Short id) throws IOException, SQLException {
    final var inputStream = CLASS_LOADER.getResourceAsStream(FIND_ALL_BY_REGION_PATH);
    final var query =
        IOUtils.toString(Objects.requireNonNull(inputStream), Charset.defaultCharset());
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.prepareStatement(query);
    setKeyParameter(statement, id);
    final var resultSet = statement.executeQuery();
    final var set = new LinkedHashSet<Province>();
    while (resultSet.next()) {
      final var province = readTuple(resultSet);
      set.add(province);
    }
    resultSet.close();
    statement.close();
    return set;
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
  public Province readTuple(ResultSet resultSet) throws SQLException {
    final var id = resultSet.getShort(1);
    final var provinceName = resultSet.getString(2);
    final var regionID = resultSet.getShort(3);
    return Province.createInstance(id, provinceName, regionID);
  }

  @Override
  public void setKeyParameter(PreparedStatement statement, Short regionID) throws SQLException {
    statement.setShort(1, regionID);
  }
}
