package com.bairontapia.projects.cuidamed.person;

import com.bairontapia.projects.cuidamed.daotemplate.GenericCrudDAO;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDAO implements GenericCrudDAO<Address, String> {

  private static final AddressDAO INSTANCE = new AddressDAO();

  private static final String RELATIVE_PATH_STRING =
      DirectoryPathUtils.relativePathString("scripts", "class_queries", "address.");
  private static final Path FIND_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path FIND_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static final Path SAVE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "save.sql");
  private static final Path UPDATE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "update.sql");

  public static AddressDAO getInstance() {
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
  public String updateQuery() throws IOException {
    return TextFileUtils.readString(UPDATE_QUERY_PATH);
  }

  @Override
  public void setKeyParameter(PreparedStatement statement, String rut) throws SQLException {
    statement.setString(1, rut);
  }

  @Override
  public void saveTuple(PreparedStatement statement, Address address) throws SQLException {
  }

  @Override
  public void updateTuple(PreparedStatement statement, Address address) throws SQLException {
  }

  @Override
  public Address readTuple(ResultSet resultSet) throws SQLException {
    final var regionName = resultSet.getString(1);
    final var provinceName = resultSet.getString(2);
    final var communeName = resultSet.getString(3);
    final var street = resultSet.getString(4);
    final var number = resultSet.getInt(5);
    final var postalCode = resultSet.getInt(6);
    final var fixedPhone = resultSet.getInt(7);
    final var rut = resultSet.getString(8);
    return Address.createInstance(
        regionName, provinceName, communeName, street, number, postalCode, fixedPhone, rut);
  }
}
