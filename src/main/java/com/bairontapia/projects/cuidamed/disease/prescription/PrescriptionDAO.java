package com.bairontapia.projects.cuidamed.disease.prescription;

import com.bairontapia.projects.cuidamed.connection.ConnectionSingleton;
import com.bairontapia.projects.cuidamed.daotemplate.CrudDAO;
import com.bairontapia.projects.cuidamed.daotemplate.GenericCrudDAO;
import com.bairontapia.projects.cuidamed.disease.prescription.Prescription;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;

public class PrescriptionDAO implements GenericCrudDAO<Prescription, String> {

  private static final PrescriptionDAO INSTANCE = new PrescriptionDAO();
  private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
      .relativePathString("scripts", "class_queries", "prescription");
  private static final Path GET_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static final Path GET_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path SAVE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "save.sql");
  private static final Path UPDATE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "update.sql");


  public static PrescriptionDAO getInstance() {
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
    final var diseaseName = resultSet.getString(2);
    final var prescriptionDate = resultSet.getDate(3);
    final var description = resultSet.getString(4);
    return Prescription.createInstance(rut, diseaseName, prescriptionDate, description);
  }
}
