package com.bairontapia.projects.cuidamed.person;

import com.bairontapia.projects.cuidamed.connection.ConnectionSingleton;
import com.bairontapia.projects.cuidamed.daotemplate.CrudDAO;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;

public class ElderDAO implements CrudDAO<Elder, String> {

  private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
      .relativePathString("scripts", "class_queries", "elder");
  private static final Path GET_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path GET_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static final Path UPDATE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "update.sql");
  private static ElderDAO instance;

  private ElderDAO() {
  }

  public static ElderDAO getInstance() {
    if (instance == null) {
      instance = new ElderDAO();
    }
    return instance;
  }

  @Override
  public Optional<Elder> get(String rut) throws IOException, SQLException {
    final var query = TextFileUtils.readString(GET_QUERY_PATH);
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.prepareStatement(query);
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
  public Collection<Elder> getAll() throws IOException, SQLException {
    final var query = TextFileUtils.readString(GET_ALL_QUERY_PATH);
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.createStatement();
    final var resultSet = statement.executeQuery(query);
    final var set = new LinkedHashSet<Elder>();
    while (resultSet.next()) {
      final var rut = resultSet.getString(1);
      final var firstName = resultSet.getString(2);
      final var lastName = resultSet.getString(3);
      final var secondLastName = resultSet.getString(4);
      final var birthDate = resultSet.getDate(5);
      final var genderCode = resultSet.getShort(6);
      final var isActive = resultSet.getBoolean(7);
      final var admissionDate = resultSet.getDate(8);
      final var responsibleRut = resultSet.getString(9);
      final var elder = Elder.createInstance(rut, firstName, lastName, secondLastName, birthDate,
          genderCode, isActive, admissionDate, responsibleRut);
      set.add(elder);
    }
    return set;
  }

  @Override
  public void save(Elder elder) throws IOException, SQLException {

  }

  @Override
  public void update(Elder elder) throws IOException, SQLException {
    var connection = ConnectionSingleton.getInstance();
    var query = TextFileUtils.readString(UPDATE_QUERY_PATH);
    var statement = connection.prepareStatement(query);
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

}
