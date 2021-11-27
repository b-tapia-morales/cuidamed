package com.bairontapia.projects.cuidamed.medicalrecord.surgicalintervention;


import com.bairontapia.projects.cuidamed.daotemplate.GenericReadOnlyDAO;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SurgicalInterventionDAO implements GenericReadOnlyDAO<SurgicalIntervention, String> {

  private static final SurgicalInterventionDAO INSTANCE = new SurgicalInterventionDAO();
  private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
      .relativePathString("scripts", "class_queries", "surgical_intervention");
  private static final Path GET_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path GET_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static final Path UPDATE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "update.sql");
  private static SurgicalInterventionDAO instance;

  public static SurgicalInterventionDAO getInstance() {
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
  public SurgicalIntervention readTuple(ResultSet resultSet) throws SQLException {
    final var rut = resultSet.getString(1);
    final var firstNames = resultSet.getString(2);
    final var lastName = resultSet.getString(3);
    final var secondLastName = resultSet.getString(4);
    final var interventionDate = resultSet.getDate(5);
    final var hospital = resultSet.getString(6);
    final var severity = resultSet.getShort(7);
    final var description = resultSet.getString(8);
    return SurgicalIntervention
        .createInstance(rut, firstNames, lastName, secondLastName, interventionDate, hospital,
            severity, description);

  }

  @Override
  public void setKeyParameter(PreparedStatement statement, String rut) throws SQLException {
    statement.setString(1, rut);
  }
}
