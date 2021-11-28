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

public class CommuneDAO
    implements GenericReadOnlyDAO<Commune, Short>, OneToManyDAO<Commune, Short> {

  private static final CommuneDAO INSTANCE = new CommuneDAO();

  private static final String RELATIVE_PATH_STRING =
      DirectoryPathUtils.relativePathString("scripts", "class_queries", "commune");
  private static final Path FIND_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path FIND_ALL_BY_PROVINCE_QUERY_PATH =
      Path.of(RELATIVE_PATH_STRING, "get_all_by_province.sql");

  public static CommuneDAO getInstance() {
    return INSTANCE;
  }

  @Override
  public String findQuery() throws IOException {
    return TextFileUtils.readString(FIND_ALL_BY_PROVINCE_QUERY_PATH);
  }

  @Override
  public String findAllQuery() throws IOException {
    return TextFileUtils.readString(FIND_ALL_QUERY_PATH);
  }

  @Override
  public Commune readTuple(ResultSet resultSet) throws SQLException {
    final var id = resultSet.getShort(1);
    final var communeName = resultSet.getString(2);
    final var provinceID = resultSet.getShort(3);
    return Commune.createInstance(id, communeName, provinceID);
  }

  @Override
  public void setKeyParameter(PreparedStatement statement, Short provinceId) throws SQLException {
    statement.setShort(1, provinceId);
  }
}
