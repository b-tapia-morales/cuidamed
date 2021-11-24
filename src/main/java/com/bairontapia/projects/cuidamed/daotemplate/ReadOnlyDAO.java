package com.bairontapia.projects.cuidamed.daotemplate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public interface ReadOnlyDAO<T, ID> {

  Optional<T> get(ID id) throws IOException, SQLException;

  Collection<T> getAll() throws IOException, SQLException;

}
