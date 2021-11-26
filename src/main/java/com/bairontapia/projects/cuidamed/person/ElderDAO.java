package com.bairontapia.projects.cuidamed.person;

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

public class ElderDAO implements GenericCrudDAO<Elder, String> {

  private static final ElderDAO INSTANCE = new ElderDAO();

  private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
      .relativePathString("scripts", "class_queries", "elder");
  private static final Path GET_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path GET_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static final Path SAVE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "save.sql");
  private static final Path UPDATE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "update.sql");

  public static ElderDAO getInstance() {
    return INSTANCE;
  }

  @Override
  public String getQuery() throws IOException {
    return TextFileUtils.readString(GET_QUERY_PATH);
  }

  @Override
  public String getAllQuery() throws IOException {
    return TextFileUtils.readString(GET_ALL_QUERY_PATH);
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
    final var resultSet = statement.executeQuery();

    if (resultSet.next()) {
      final var elderRut = resultSet.getString(1);
      final var firstName = resultSet.getString(2);
      final var lastName = resultSet.getString(3);
      final var secondLastName = resultSet.getString(4);
      final var birthDate = resultSet.getDate(5);
      final var genderCode = resultSet.getShort(6);
      final var isActive = resultSet.getBoolean(7);
      final var admissionDate = resultSet.getDate(8);
      final var responsibleRut = resultSet.getString(9);
      final var elder = Elder
          .createInstance(elderRut, firstName, lastName, secondLastName, birthDate,
              genderCode, isActive, admissionDate, responsibleRut);
      return Optional.of(elder);
    }
    return Optional.empty();
  }

  @Override
  public void saveTuple(final PreparedStatement statement, Elder elder) throws SQLException {
    statement.setString(1, elder.rut());
    statement.setString(2, elder.firstName());
    statement.setString(3, elder.lastName());
    statement.setString(4, elder.secondLastName());
    statement.setDate(5, Date.valueOf(elder.birthDate()));
    statement.setShort(6, (short) elder.gender().getIndex());
    statement.setBoolean(7, elder.isActive());
    statement.setDate(8, Date.valueOf(elder.admissionDate()));
    statement.setString(9, elder.responsibleRut());
    statement.executeUpdate();
  }

  @Override
  public void updateTuple(final PreparedStatement statement, Elder elder) throws SQLException {
    statement.setString(1, elder.firstName());
    statement.setString(2, elder.lastName());
    statement.setString(3, elder.secondLastName());
    statement.setDate(4, Date.valueOf(elder.birthDate()));
    statement.setShort(5, (short) elder.gender().getIndex());
    statement.setString(6, elder.rut());
    statement.setBoolean(7, elder.isActive());
    statement.setDate(8, Date.valueOf(elder.admissionDate()));
    statement.setString(9, elder.rut());
    statement.executeUpdate();
  }

  @Override
  public Elder readTuple(final ResultSet resultSet) throws SQLException {
    final var rut = resultSet.getString(1);
    final var firstName = resultSet.getString(2);
    final var lastName = resultSet.getString(3);
    final var secondLastName = resultSet.getString(4);
    final var birthDate = resultSet.getDate(5);
    final var genderCode = resultSet.getShort(6);
    final var isActive = resultSet.getBoolean(7);
    final var admissionDate = resultSet.getDate(8);
    final var responsibleRut = resultSet.getString(9);
    return Elder.createInstance(rut, firstName, lastName, secondLastName, birthDate, genderCode,
        isActive, admissionDate, responsibleRut);
  }

}
