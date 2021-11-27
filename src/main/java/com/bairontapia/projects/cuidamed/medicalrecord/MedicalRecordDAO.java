package com.bairontapia.projects.cuidamed.medicalrecord;

import com.bairontapia.projects.cuidamed.connection.ConnectionSingleton;
import com.bairontapia.projects.cuidamed.daotemplate.GenericCrudDAO;
import com.bairontapia.projects.cuidamed.daotemplate.GenericReadAndWriteDAO;
import com.bairontapia.projects.cuidamed.daotemplate.GenericReadOnlyDAO;
import com.bairontapia.projects.cuidamed.daotemplate.ReadOnlyDAO;
import com.bairontapia.projects.cuidamed.medicalrecord.surgicalintervention.SurgicalInterventionDAO;
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

public class MedicalRecordDAO implements GenericCrudDAO<MedicalRecord, String> {

  private static MedicalRecordDAO INSTANCE = new MedicalRecordDAO();
  private static final String RELATIVE_PATH_STRING =
      DirectoryPathUtils.relativePathString("scripts", "class_queries", "medical_record");
  private static final Path FIND_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path FIND_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static final Path SAVE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "save.sql");
  private static final Path UPDATE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "update.sql");

  public static MedicalRecordDAO getInstance() {
    return INSTANCE;
  }

  @Override
  public String updateQuery() throws IOException {
    return TextFileUtils.readString(UPDATE_QUERY_PATH);
  }

  @Override
  public void updateTuple(PreparedStatement statement, MedicalRecord medicalRecord)
      throws SQLException {
    statement.setShort(1, (short) medicalRecord.bloodType().getIndex());
    statement.setShort(2, (short) medicalRecord.healthCare().getIndex());
    statement.setString(3, medicalRecord.rut());
    statement.executeUpdate();
  }

  @Override
  public String saveQuery() throws IOException {
    return TextFileUtils.readString(SAVE_QUERY_PATH);
  }

  @Override
  public void saveTuple(PreparedStatement statement, MedicalRecord medicalRecord)
      throws SQLException {
    statement.setString(1, medicalRecord.rut());
    statement.setShort(2, (short) medicalRecord.bloodType().getIndex());
    statement.setShort(3, (short) medicalRecord.healthCare().getIndex());
    statement.executeUpdate();
  }

  @Override
  public String findQuery() throws IOException {
    return TextFileUtils.readString(FIND_QUERY_PATH);
  }

  @Override
  public String findAllQuery() throws IOException {
    return TextFileUtils.readString(FIND_ALL_QUERY_PATH);
  }

  @Override
  public MedicalRecord readTuple(ResultSet resultSet) throws SQLException {
    final var rut = resultSet.getString(1);
    final var bloodTypeCode = resultSet.getShort(2);
    final var healthCareCode = resultSet.getShort(3);
    return MedicalRecord.createInstance(rut, bloodTypeCode, healthCareCode);
  }

  @Override
  public void setKeyParameter(PreparedStatement statement, String rut) throws SQLException {
    statement.setString(1, rut);
  }
}
