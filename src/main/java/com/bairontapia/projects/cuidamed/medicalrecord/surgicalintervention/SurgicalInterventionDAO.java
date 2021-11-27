package com.bairontapia.projects.cuidamed.medicalrecord.surgicalintervention;

import com.bairontapia.projects.cuidamed.daotemplate.GenericReadAndWriteDAO;
import com.bairontapia.projects.cuidamed.daotemplate.OneToManyDAO;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SurgicalInterventionDAO
    implements GenericReadAndWriteDAO<SurgicalIntervention, String>,
    OneToManyDAO<SurgicalIntervention, String> {

  private static final SurgicalInterventionDAO INSTANCE = new SurgicalInterventionDAO();

  private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
      .relativePathString("scripts", "class_queries", "medical_record", "surgical_intervention");
  private static final Path FIND_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path FIND_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static final Path SAVE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "save.sql");

  public static SurgicalInterventionDAO getInstance() {
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
  public void setKeyParameter(PreparedStatement statement, String rut) throws SQLException {
    statement.setString(1, rut);
  }

  @Override
  public void saveTuple(PreparedStatement statement, SurgicalIntervention surgicalIntervention)
      throws SQLException {
    statement.setString(1, surgicalIntervention.rut());
    statement.setDate(2, Date.valueOf(surgicalIntervention.interventionDate()));
    statement.setString(3, surgicalIntervention.hospital());
    statement.setShort(4, surgicalIntervention.severity());
    statement.setString(5, surgicalIntervention.description());
    statement.executeUpdate();
  }

  @Override
  public SurgicalIntervention readTuple(ResultSet resultSet) throws SQLException {
    final var elderRut = resultSet.getString(1);
    final var interventionDate = resultSet.getDate(2);
    final var hospital = resultSet.getString(3);
    final var severity = resultSet.getShort(4);
    final var description = resultSet.getString(5);
    return SurgicalIntervention.createInstance(elderRut, interventionDate, hospital, severity,
        description);
  }

}
