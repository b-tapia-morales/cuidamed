package com.bairontapia.projects.cuidamed.person;

import com.bairontapia.projects.cuidamed.connection.ConnectionSingleton;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.FilePathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;

public class ElderDAO implements PersonDAO<Elder, String> {

  private static final Path ELDER_QUERY_PATH = FilePathUtils
      .relativePath("select_all_elders.sql", "scripts", "class_queries", "elder");


  @Override
  public Optional<Elder> get(String rut) {
    return Optional.empty();
  }

  @Override
  public Collection<Elder> getAll() throws IOException, SQLException {
    var set = new LinkedHashSet<Elder>();
    var connection = ConnectionSingleton.getInstance();
    var statement = connection.createStatement();
    var query = TextFileUtils.readString(ELDER_QUERY_PATH);
    var resultSet = statement.executeQuery(query);
    while (resultSet.next()) {
      var rut = resultSet.getString(1);
      var fullName = resultSet.getString(2);
      var birthDate = resultSet.getDate(3);
      var genderCode = resultSet.getShort(4);
      var isActive = resultSet.getBoolean(5);
      var admissionDate = resultSet.getDate(6);
      var responsibleRut = resultSet.getString(7);
      var elder = Elder.createInstance(rut, fullName, birthDate, genderCode, isActive,
          admissionDate, responsibleRut);
      set.add(elder);
    }
    return set;
  }

  @Override
  public void save(Elder elder) {

  }

  @Override
  public void delete(String rut) {

  }
}
