package com.bairontapia.projects.cuidamed.medicalrecord;

import com.bairontapia.projects.cuidamed.daotemplate.CrudDAO;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.apache.commons.io.IOUtils;

public class MedicalRecordDAO implements CrudDAO<MedicalRecord, String> {

  private static final MedicalRecordDAO INSTANCE = new MedicalRecordDAO();

  private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();

  private static final String RELATIVE_PATH_STRING =
      DirectoryPathUtils.pathBuilder("scripts", "class_queries", "medical_record");
  private static final String FIND_ALL_QUERY_PATH = RELATIVE_PATH_STRING + "get_all.sql";
  private static final String FIND_QUERY_PATH = RELATIVE_PATH_STRING + "get.sql";
  private static final String SAVE_QUERY_PATH = RELATIVE_PATH_STRING + "save.sql";
  private static final String UPDATE_QUERY_PATH = RELATIVE_PATH_STRING + "update.sql";

  public static MedicalRecordDAO getInstance() {
    return INSTANCE;
  }

  @Override
  public String updateQuery() throws IOException {
    final var inputStream = CLASS_LOADER.getResourceAsStream(UPDATE_QUERY_PATH);
    return IOUtils.toString(Objects.requireNonNull(inputStream), Charset.defaultCharset());
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
    final var inputStream = CLASS_LOADER.getResourceAsStream(SAVE_QUERY_PATH);
    return IOUtils.toString(Objects.requireNonNull(inputStream), Charset.defaultCharset());
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
    final var inputStream = CLASS_LOADER.getResourceAsStream(FIND_QUERY_PATH);
    return IOUtils.toString(Objects.requireNonNull(inputStream), Charset.defaultCharset());
  }

  @Override
  public String findAllQuery() throws IOException {
    final var inputStream = CLASS_LOADER.getResourceAsStream(FIND_ALL_QUERY_PATH);
    return IOUtils.toString(Objects.requireNonNull(inputStream), Charset.defaultCharset());
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
