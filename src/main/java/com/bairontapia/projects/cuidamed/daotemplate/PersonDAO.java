package com.bairontapia.projects.cuidamed.daotemplate;

import java.util.Collection;
import java.util.Optional;

public interface PersonDAO<T, ID> {

  Optional<T> get(ID id);

  Collection<T> getAll();

  void save(T t);

  void delete(ID id);

}
