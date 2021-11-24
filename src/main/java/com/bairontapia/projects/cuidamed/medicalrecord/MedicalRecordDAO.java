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

public class MedicalRecordDAO implements ReadOnlyDAO<MedicalRecord, String> {

  private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
      .relativePathString("scripts", "class_queries", "medical_record");
  private static final Path GET_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path GET_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");

  @Override
  public Optional<MedicalRecord> get(String key) throws IOException, SQLException {
    final var query = TextFileUtils.readString(GET_QUERY_PATH);
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.prepareStatement(query);
    statement.setString(1, key);
    final var resultSet = statement.executeQuery();
    if (resultSet.isBeforeFirst()) {
      final var rut = resultSet.getString(1);
      final var bloodTypeCode = resultSet.getShort(2);
      final var healthCareSystemCode = resultSet.getShort(3);
      return Optional.of(MedicalRecord.createInstance(rut, bloodTypeCode, healthCareSystemCode));
    }
    return Optional.empty();
  }

  @Override
  public Collection<MedicalRecord> getAll() throws IOException, SQLException {
    final var query = TextFileUtils.readString(GET_ALL_QUERY_PATH);
    final var set = new LinkedHashSet<MedicalRecord>();
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.createStatement();
    final var resultSet = statement.executeQuery(query);
    while (resultSet.next()) {
      final var rut = resultSet.getString(1);
      final var firstName = resultSet.getShort(2);
      final var lastName = resultSet.getShort(3);
      final var medicalRecord = MedicalRecord.createInstance(rut, firstName, lastName);
      set.add(medicalRecord);
    }
    return set;
  }
}
