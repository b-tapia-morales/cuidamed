package com.bairontapia.projects.cuidamed.disease;

import com.bairontapia.projects.cuidamed.connection.ConnectionSingleton;
import com.bairontapia.projects.cuidamed.daotemplate.CrudDAO;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;

public class DiseaseDAO implements CrudDAO<Disease, String> {

  private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
      .relativePathString("scripts", "class_queries", "Disease");
  private static final Path GET_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static final Path GET_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path UPDATE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "update.sql");
  private static final Path SAVE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "save.sql");

  @Override
  public Optional<Disease> get(String s) throws IOException, SQLException {
    final var query = TextFileUtils.readString(GET_QUERY_PATH);
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.prepareStatement(query);
    return Optional.empty();
  }

  @Override
  public Collection<Disease> getAll() throws IOException, SQLException {
    final var query = TextFileUtils.readString(GET_ALL_QUERY_PATH);
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.createStatement();
    final var resultSet = statement.executeQuery(query);
    final var set = new LinkedHashSet<Disease>();
    while (resultSet.next()) {
      final var diseaseName = resultSet.getString(1);
      final var diseaseType = resultSet.getShort(2);
      final var isChronic = resultSet.getBoolean(3);
      final var disease = Disease.createInstance(diseaseName, diseaseType, isChronic);
      set.add(disease);
    }
    return set;
  }

  @Override
  public void save(Disease disease) throws IOException, SQLException {
    final var query = TextFileUtils.readString(SAVE_QUERY_PATH);
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.prepareStatement(query);
    statement.setString(1,disease.disease_name());
    statement.setShort(2, (short) disease.diseaseType().getIndex());
    statement.setBoolean(3, disease.is_chronic());
    statement.executeUpdate();

  }

  @Override
  public void update(Disease disease) throws IOException, SQLException {

  }
}
