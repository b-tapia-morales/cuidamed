package com.bairontapia.projects.cuidamed.daotemplate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public interface NonCompositeIdDAO<T, ID> {

  Optional<T> get(ID id) throws IOException, SQLException;

  Collection<T> getAll() throws IOException, SQLException;

  void save(T t) throws IOException, SQLException;

  void update(T t) throws IOException, SQLException;

}
