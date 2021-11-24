package com.bairontapia.projects.cuidamed.person;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public interface PersonDAO<T, ID> {

  Optional<T> get(ID id) throws IOException, SQLException;

  Collection<T> getAll() throws IOException, SQLException;

  void save(T t) throws IOException, SQLException;

  void delete(ID id) throws IOException, SQLException;

}
