package com.bairontapia.projects.cuidamed.person.carer;

import com.bairontapia.projects.cuidamed.connection.ConnectionSingleton;
import com.bairontapia.projects.cuidamed.daotemplate.CrudDAO;
import com.bairontapia.projects.cuidamed.daotemplate.GenericCrudDAO;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;

public class CarerDAO implements GenericCrudDAO<Carer, String> {

  private static CarerDAO instance = new CarerDAO();
  private static final String RELATIVE_PATH_STRING =
      DirectoryPathUtils.relativePathString("scripts", "class_queries", "carer");
  private static final Path FIND_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path FIND_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static final Path SAVE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "save.sql");
  private static final Path UPDATE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "update.sql");

  public static CarerDAO getInstance() {
    return instance;
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
  public void saveTuple(PreparedStatement statement, Carer carer) throws SQLException {
    statement.setString(1, carer.rut());
    statement.setString(2, carer.firstName());
    statement.setString(3, carer.lastName());
    statement.setString(4, carer.secondLastName());
    statement.setDate(5, Date.valueOf(carer.birthDate()));
    statement.setShort(6, (short) carer.gender().getIndex());
    statement.setString(7, carer.rut());
    statement.setInt(8, carer.mobilePhone());
    statement.setDate(9, Date.valueOf(carer.hireDate()));
    statement.executeUpdate();
  }

  @Override
  public void updateTuple(PreparedStatement statement, Carer carer) throws SQLException {
    statement.setString(1, carer.firstName());
    statement.setString(2, carer.lastName());
    statement.setString(3, carer.secondLastName());
    statement.setDate(4, Date.valueOf(carer.birthDate()));
    statement.setShort(5, (short) carer.gender().getIndex());
    statement.setString(6, carer.rut());
    statement.setInt(7, carer.mobilePhone());
    statement.setDate(8, Date.valueOf(carer.hireDate()));
    statement.setString(9, carer.rut());
    statement.executeUpdate();
  }

  @Override
  public Carer readTuple(ResultSet resultSet) throws SQLException {
    final var carerRut = resultSet.getString(1);
    final var firstName = resultSet.getString(2);
    final var lastName = resultSet.getString(3);
    final var secondLastName = resultSet.getString(4);
    final var birthDate = resultSet.getDate(5);
    final var genderCode = resultSet.getShort(6);
    final var mobilePhone = resultSet.getInt(7);
    final var hireDate = resultSet.getDate(8);
    return Carer.createInstance(
        carerRut,
        firstName,
        lastName,
        secondLastName,
        birthDate,
        genderCode,
        mobilePhone,
        hireDate);
  }
}
