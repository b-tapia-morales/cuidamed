package com.bairontapia.projects.cuidamed.daotemplate;

import com.bairontapia.projects.cuidamed.connection.ConnectionSingleton;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;

public interface ReadOnlyDAO<T, ID> {

  String findQuery() throws IOException;

  String findAllQuery() throws IOException;

  T readTuple(ResultSet resultSet) throws SQLException;

  void setKeyParameter(PreparedStatement statement, ID id) throws SQLException;

  default Optional<T> find(ID id) throws IOException, SQLException {
    final var query = findQuery();
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.prepareStatement(query);
    setKeyParameter(statement, id);
    final var resultSet = statement.executeQuery();
    final var optional = resultSet.next() ? Optional.of(readTuple(resultSet)) : Optional.<T>empty();
    resultSet.close();
    statement.close();
    return optional;
  }

  default Collection<T> findAll() throws IOException, SQLException {
    final var query = findAllQuery();
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.createStatement();
    final var resultSet = statement.executeQuery(query);
    final var set = new LinkedHashSet<T>();
    while (resultSet.next()) {
      final T t = readTuple(resultSet);
      set.add(t);
    }
    resultSet.close();
    statement.close();
    return set;
  }
}
