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

public class ProvinceDAO implements ReadOnlyDAO<Province, Short> {


  private static final ProvinceDAO INSTANCE = new ProvinceDAO();

  private static final String RELATIVE_PATH_STRING =
      DirectoryPathUtils.relativePathString("scripts", "class_queries", "localization", "province");
  private static final Path FIND_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static final Path FIND_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path FIND_ALL_BY_REGION_PATH = Path
      .of(RELATIVE_PATH_STRING, "get_all_by_region.sql");

  public static ProvinceDAO getInstance() {
    return INSTANCE;
  }

  public Collection<Province> findAll(Short id) throws IOException, SQLException {
    final var query = TextFileUtils.readString(FIND_ALL_BY_REGION_PATH);
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
    return TextFileUtils.readString(FIND_QUERY_PATH);
  }

  @Override
  public String findAllQuery() throws IOException {
    return TextFileUtils.readString(FIND_ALL_QUERY_PATH);
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
