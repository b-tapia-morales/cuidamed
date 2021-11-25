package com.bairontapia.projects.cuidamed.disease;

import com.bairontapia.projects.cuidamed.connection.ConnectionSingleton;
import com.bairontapia.projects.cuidamed.daotemplate.CrudDAO;
import com.bairontapia.projects.cuidamed.utils.files.TextFileUtils;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;

public class PrescriptionDAO implements CrudDAO<Prescription, String> {

  private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
      .relativePathString("scripts", "class_queries", "Prescription");
  private static final Path GET_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get.sql");
  private static final Path GET_ALL_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "get_all.sql");
  private static final Path SAVE_QUERY_PATH = Path.of(RELATIVE_PATH_STRING, "save.sql");


  @Override
  public Optional<Prescription> get(String s) throws IOException, SQLException {
    return Optional.empty();
  }

  @Override
  public Collection<Prescription> getAll() throws IOException, SQLException {
    final var query = TextFileUtils.readString(GET_ALL_QUERY_PATH);
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.createStatement();
    final var resulSet = statement.executeQuery(query);
    final var set = new LinkedHashSet<Prescription>();
    while (resulSet.next()) {
      final var rut = resulSet.getString(1);
      final var diseaseName = resulSet.getString(2);
      final var prescriptionDate = resulSet.getDate(3);
      final var description = resulSet.getString(4);
      final var prescription = Prescription
          .createInstance(rut, diseaseName, prescriptionDate, description);
      set.add(prescription);
    }
    return set;
  }

  @Override
  public void save(Prescription prescription) throws IOException, SQLException {
    final var query = TextFileUtils.readString(SAVE_QUERY_PATH);
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.prepareStatement(query);
    statement.setString(1, prescription.rut());
    statement.setString(2,prescription.diseaseName());
    statement.setDate(3, Date.valueOf(prescription.prescriptionDate()));
    statement.setString(4, prescription.description());
    statement.executeUpdate();
  }

  @Override
  public void update(Prescription prescription) throws IOException, SQLException {

  }
}
