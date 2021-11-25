package com.bairontapia.projects.cuidamed.person;

import com.bairontapia.projects.cuidamed.connection.ConnectionSingleton;
import com.bairontapia.projects.cuidamed.daotemplate.CrudDAO;
import com.bairontapia.projects.cuidamed.medicalrecord.surgicalintervention.SurgicalInterventionDAO;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;

public class CarerDAO implements CrudDAO<Carer, String> {

  private static CarerDAO instance;
  private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
      .relativePathString("scripts", "class_queries", "carer");
  private static final Path GET_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path GET_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static final Path UPDATE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "update.sql");

  private CarerDAO() {
  }

  public static CarerDAO getInstance() {
    if (instance == null) {
      instance = new CarerDAO();
    }
    return instance;
  }

  @Override
  public Optional<Carer> get(String rut) throws IOException, SQLException {
    final var query = TextFileUtils.readString(GET_QUERY_PATH);
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.prepareStatement(query);
    statement.setString(1, rut);
    final var resultSet = statement.executeQuery();
    if (resultSet.next()) {
      final var carerRut = resultSet.getString(1);
      final var firstName = resultSet.getString(2);
      final var lastName = resultSet.getString(3);
      final var secondLastName = resultSet.getString(4);
      final var birthDate = resultSet.getDate(5);
      final var genderCode = resultSet.getShort(6);
      final var mobilePhone = resultSet.getInt(7);
      final var hireDate = resultSet.getDate(8);
      return Optional.of(Carer.createInstance(carerRut, firstName, lastName, secondLastName,
          birthDate, genderCode, mobilePhone, hireDate));
    }
    return Optional.empty();
  }

  @Override
  public Collection<Carer> getAll() throws IOException, SQLException {
    final var query = TextFileUtils.readString(GET_ALL_QUERY_PATH);
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.createStatement();
    final var resultSet = statement.executeQuery(query);
    final var set = new LinkedHashSet<Carer>();
    while (resultSet.next()) {
      final var carerRut = resultSet.getString(1);
      final var firstName = resultSet.getString(2);
      final var lastName = resultSet.getString(3);
      final var secondLastName = resultSet.getString(4);
      final var birthDate = resultSet.getDate(5);
      final var genderCode = resultSet.getShort(6);
      final var mobilePhone = resultSet.getInt(7);
      final var hireDate = resultSet.getDate(8);
      final var carer = Carer.createInstance(carerRut, firstName, lastName, secondLastName,
          birthDate, genderCode, mobilePhone, hireDate);
      set.add(carer);
    }
    return set;
  }

  @Override
  public void save(Carer carer) throws IOException, SQLException {

  }

  @Override
  public void update(Carer carer) throws IOException, SQLException {

  }
}
