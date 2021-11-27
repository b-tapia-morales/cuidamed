package com.bairontapia.projects.cuidamed.medicalrecord;

import com.bairontapia.projects.cuidamed.connection.ConnectionSingleton;
import com.bairontapia.projects.cuidamed.daotemplate.GenericReadOnlyDAO;
import com.bairontapia.projects.cuidamed.daotemplate.ReadOnlyDAO;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;

public class MedicalRecordDAO implements GenericReadOnlyDAO<MedicalRecord, String> {

  private static MedicalRecordDAO INSTANCE = new MedicalRecordDAO();
  private static final String RELATIVE_PATH_STRING =
      DirectoryPathUtils.relativePathString("scripts", "class_queries", "medical_record");
  private static final Path GET_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path GET_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");

  public static MedicalRecordDAO getInstance() {
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
  public MedicalRecord readTuple(ResultSet resultSet) throws SQLException {
    final var elderRut = resultSet.getString(1);
    final var bloodTypeCode = resultSet.getShort(2);
    final var healthCareCode = resultSet.getShort(3);
    return MedicalRecord.createInstance(elderRut, bloodTypeCode, healthCareCode);
  }

  @Override
  public void setKeyParameter(PreparedStatement statement, String rut) throws SQLException {
    statement.setString(1, rut);
  }
}
