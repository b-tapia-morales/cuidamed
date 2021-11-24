package com.bairontapia.projects.cuidamed.person;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public interface PersonDAO<T> {

  Optional<T> get(String rut) throws IOException, SQLException;

  Collection<T> getAll() throws IOException, SQLException;

  void save(T t) throws IOException, SQLException;

  void update(T t) throws IOException, SQLException;

}
