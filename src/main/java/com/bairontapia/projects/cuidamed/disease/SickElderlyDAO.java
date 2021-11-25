package com.bairontapia.projects.cuidamed.disease;

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

public class SickElderlyDAO implements CrudDAO<SickElderly, String> {

  private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
      .relativePathString("scripts", "class_queries", "sick_elderly");
  private static final Path GET_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static final Path GET_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path UPDATE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "update.sql");
  private static final Path SAVE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "save.sql");


  @Override
  public Optional<SickElderly> get(String s) throws IOException, SQLException {
    return Optional.empty();
  }

  @Override
  public Collection<SickElderly> getAll() throws IOException, SQLException {
    final var query = TextFileUtils.readString(GET_ALL_QUERY_PATH);
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.createStatement();
    final var resulSet = statement.executeQuery(query);
    final var set = new LinkedHashSet<SickElderly>();
    while (resulSet.next()) {
      final var rut = resulSet.getString(1);
      final var diseaseName = resulSet.getString(2);
      final var diagnosisDate = resulSet.getDate(3);
      final var sickElderly = SickElderly.createInstance(rut, diseaseName, diagnosisDate);
      set.add(sickElderly);
    }
    return set;
  }

  @Override
  public void save(SickElderly sickElderly) throws IOException, SQLException {
    final var query = TextFileUtils.readString(SAVE_QUERY_PATH);
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.prepareStatement(query);
    statement.setString(1,sickElderly.rut());
    statement.setString(2,sickElderly.diseaseName());
    statement.setDate(3, Date.valueOf(sickElderly.diagnosisDate()));
    statement.executeUpdate();
  }

  @Override
  public void update(SickElderly sickElderly) throws IOException, SQLException {

  }
}
