package com.bairontapia.projects.cuidamed.medicalrecord;

import com.bairontapia.projects.cuidamed.daotemplate.CrudDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public class MedicalRecordDAO implements CrudDAO<MedicalRecord, String> {

  @Override
  public Optional<MedicalRecord> get(String s) throws IOException, SQLException {
    return Optional.empty();
  }

  @Override
  public Collection<MedicalRecord> getAll() throws IOException, SQLException {
    return null;
  }

  @Override
  public void save(MedicalRecord medicalRecord) throws IOException, SQLException {

  }

  @Override
  public void update(MedicalRecord medicalRecord) throws IOException, SQLException {

  }
}
