package com.bairontapia.projects.cuidamed.medicalrecord;

import com.bairontapia.projects.cuidamed.connection.ConnectionSingleton;
import com.bairontapia.projects.cuidamed.daotemplate.ReadOnlyDAO;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;

public class BloodTypeStatsDAO implements ReadOnlyDAO<BloodTypeStats, Short> {

  private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
      .relativePathString("scripts", "class_queries", "blood_type_stats");
  private static final Path GET_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path GET_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");


  @Override
  public Optional<BloodTypeStats> get(Short bloodTypeCode) throws IOException, SQLException {
    final var query = TextFileUtils.readString(GET_QUERY_PATH);
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.prepareStatement(query);
    statement.setShort(1, bloodTypeCode);
    final var resultSet = statement.executeQuery();
    if (resultSet.next()) {
      final var bloodType = resultSet.getShort(1);
      final var frequency = resultSet.getInt(2);
      return Optional.of(BloodTypeStats.createInstance(bloodType, frequency));
    }
    return Optional.empty();
  }

  @Override
  public Collection<BloodTypeStats> getAll() throws IOException, SQLException {
    final var query = TextFileUtils.readString(GET_ALL_QUERY_PATH);
    final var set = new LinkedHashSet<BloodTypeStats>();
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.createStatement();
    final var resultSet = statement.executeQuery(query);
    while (resultSet.next()) {
      final var bloodType = resultSet.getShort(1);
      final var frequency = resultSet.getInt(2);
      final var bloodTypeStats = BloodTypeStats.createInstance(bloodType, frequency);
      set.add(bloodTypeStats);
    }
    return set;
  }
}
