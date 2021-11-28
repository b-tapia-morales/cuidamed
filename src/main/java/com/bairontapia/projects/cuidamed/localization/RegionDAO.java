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

public class RegionDAO implements GenericReadOnlyDAO<Region, Short>, OneToManyDAO<Region, Short> {


  private static final RegionDAO INSTANCE = new RegionDAO();

  private static final String RELATIVE_PATH_STRING =
      DirectoryPathUtils.relativePathString("scripts", "class_queries", "region");
  private static final Path FIND_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path FIND_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");

  public static RegionDAO getInstance(){
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
  public Region readTuple(ResultSet resultSet) throws SQLException {
    final var id = resultSet.getShort(1);
    final var regionName = resultSet.getString(2);
    final var abbreviation = resultSet.getString(3);
    final var capital = resultSet.getString(4);
    return Region.createInstance(id, regionName, abbreviation, capital);
  }

  @Override
  public void setKeyParameter(PreparedStatement statement, Short id) throws SQLException {
    statement.setShort(1, id);
  }
}
