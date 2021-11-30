package com.bairontapia.projects.cuidamed.disease.prescription;

import com.bairontapia.projects.cuidamed.daotemplate.CrudDAO;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrescriptionDAO implements CrudDAO<Prescription, String> {

  private static final PrescriptionDAO INSTANCE = new PrescriptionDAO();
  private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();

  private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
      .relativePathString("scripts", "class_queries", "prescription");
  private static final Path FIND_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static final Path FIND_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path SAVE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "save.sql");
  private static final Path UPDATE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "update.sql");


  public static PrescriptionDAO getInstance() {
    return INSTANCE;
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
  public String saveQuery() throws IOException {
    return TextFileUtils.readString(SAVE_QUERY_PATH);
  }

  @Override
  public String updateQuery() throws IOException {
    return TextFileUtils.readString(UPDATE_QUERY_PATH);
  }

  @Override
  public void setKeyParameter(PreparedStatement statement, String rut) throws SQLException {
    statement.setString(1, rut);
  }

  @Override
  public void saveTuple(PreparedStatement statement, Prescription prescription)
      throws SQLException {
    statement.setString(1, prescription.rut());
    statement.setString(2, prescription.diseaseName());
    statement.setDate(3, Date.valueOf(prescription.prescriptionDate()));
    statement.setString(4, prescription.description());
    statement.executeUpdate();
  }

  @Override
  public void updateTuple(PreparedStatement statement, Prescription prescription)
      throws SQLException {
    statement.setString(1, prescription.diseaseName());
    statement.setDate(2, Date.valueOf(prescription.diseaseName()));
    statement.setString(3, prescription.description());
    statement.setString(4, prescription.rut());
    statement.executeUpdate();
  }

  @Override
  public Prescription readTuple(ResultSet resultSet) throws SQLException {
    final var rut = resultSet.getString(1);
    final var fullName = resultSet.getString(2);
    final var diseaseName = resultSet.getString(3);
    final var prescriptionDate = resultSet.getDate(4);
    final var description = resultSet.getString(5);
    return Prescription.createInstance(rut, diseaseName, prescriptionDate, description);
  }
}
