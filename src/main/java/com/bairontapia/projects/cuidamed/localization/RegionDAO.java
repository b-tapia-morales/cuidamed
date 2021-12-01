package com.bairontapia.projects.cuidamed.localization;

import com.bairontapia.projects.cuidamed.daotemplate.ReadOnlyDAO;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.apache.commons.io.IOUtils;

public class RegionDAO implements ReadOnlyDAO<Region, Short> {


  private static final RegionDAO INSTANCE = new RegionDAO();

  private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();

  private static final String RELATIVE_PATH_STRING =
      DirectoryPathUtils.pathBuilder("scripts", "class_queries", "localization", "region");
  private static final String FIND_ALL_QUERY_PATH = RELATIVE_PATH_STRING + "get_all.sql";
  private static final String FIND_QUERY_PATH = RELATIVE_PATH_STRING + "get.sql";

  public static RegionDAO getInstance() {
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
