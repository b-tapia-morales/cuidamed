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

public class ResponsibleDAO implements CrudDAO<Responsible, String> {

  private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
      .relativePathString("scripts", "class_queries", "responsible");
  private static final Path FIND_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static final Path FIND_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path UPDATE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "update.sql");
  private static ResponsibleDAO instance;

  public static ResponsibleDAO getInstance() {
    if (instance == null) {
      return new ResponsibleDAO();
    }
    return instance;
  }

  @Override
  public Optional<Responsible> get(String key) throws SQLException, IOException {
    final var query = TextFileUtils.readString(FIND_QUERY_PATH);
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.prepareStatement(query);
    statement.setString(1, key);
    final var resultSet = statement.executeQuery();
    if (resultSet.next()) {
      final var rut = resultSet.getString(1);
      final var firstName = resultSet.getString(2);
      final var lastName = resultSet.getString(3);
      final var secondLastName = resultSet.getString(4);
      final var birthDate = resultSet.getDate(5);
      final var gender = resultSet.getShort(6);
      final var mobilePhone = resultSet.getInt(7);
      final var responsible = Responsible.createInstance(rut, firstName,
          lastName, secondLastName, birthDate, gender, mobilePhone);
      return Optional.of(responsible);
    }
    return Optional.empty();
  }

  @Override
  public Collection<Responsible> getAll() throws IOException, SQLException {
    final var query = TextFileUtils.readString(FIND_ALL_QUERY_PATH);
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.createStatement();
    final var resultSet = statement.executeQuery(query);
    final var set = new LinkedHashSet<Responsible>();
    while (resultSet.next()) {
      final var responsibleRut = resultSet.getString(1);
      final var firstName = resultSet.getString(2);
      final var lastName = resultSet.getString(3);
      final var secondLastName = resultSet.getString(4);
      final var birthDate = resultSet.getDate(5);
      final var gender = resultSet.getShort(6);
      final var mobilePhone = resultSet.getInt(7);
      final var responsible = Responsible
          .createInstance(responsibleRut, firstName, lastName, secondLastName, birthDate, gender,
              mobilePhone);
      set.add(responsible);
    }
    return set;
  }

  @Override
  public void save(Responsible responsible) throws IOException, SQLException {

  }

  @Override
  public void update(Responsible responsible) throws IOException, SQLException {
    var connection = ConnectionSingleton.getInstance();
    var query = TextFileUtils.readString(UPDATE_QUERY_PATH);
    var statement = connection.prepareStatement(query);
    statement.setString(1, responsible.firstName());
    statement.setString(2, responsible.lastName());
    statement.setString(3, responsible.secondLastName());
    statement.setDate(4, Date.valueOf(responsible.birthDate()));
    statement.setShort(5, (short) responsible.gender().getIndex());
    statement.setString(6, responsible.rut());
    statement.setInt(7, responsible.mobilePhone());
    statement.setString(8, responsible.rut());
    statement.executeUpdate();
  }
}
