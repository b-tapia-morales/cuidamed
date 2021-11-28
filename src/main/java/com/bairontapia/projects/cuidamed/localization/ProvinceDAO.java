package com.bairontapia.projects.cuidamed.localization;

import com.bairontapia.projects.cuidamed.daotemplate.GenericReadOnlyDAO;
import com.bairontapia.projects.cuidamed.daotemplate.OneToManyDAO;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProvinceDAO
    implements GenericReadOnlyDAO<Province, Short>, OneToManyDAO<Province, Short> {
  private static final String RELATIVE_PATH_STRING =
      DirectoryPathUtils.relativePathString("scripts", "class_queries", "province");
  private static final Path FIND_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path FIND_ALL_BY_REGION_QUERY_PATH =
      Path.of(RELATIVE_PATH_STRING, "get_all_by_region.sql");
  private static ProvinceDAO INSTANCE = new ProvinceDAO();

  public static ProvinceDAO getInstance() {
    return INSTANCE;
  }

  @Override
  public String findQuery() throws IOException {
    return TextFileUtils.readString(FIND_ALL_BY_REGION_QUERY_PATH);
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
