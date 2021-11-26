package com.bairontapia.projects.cuidamed.daotemplate;

import com.bairontapia.projects.cuidamed.connection.ConnectionSingleton;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface GenericCrudDAO<T, ID> extends GenericReadOnlyDAO<T, ID> {

  String saveQuery() throws IOException;

  String updateQuery() throws IOException;

  void saveTuple(PreparedStatement statement, T t) throws SQLException;

  void updateTuple(PreparedStatement statement, T t) throws SQLException;

  default void save(T t) throws IOException, SQLException {
    final var query = saveQuery();
    final var connection = ConnectionSingleton.getInstance();
    final var statement = connection.prepareStatement(query);
    saveTuple(statement, t);
    statement.close();
  }

  default void update(T t) throws IOException, SQLException {
    var query = updateQuery();
    var connection = ConnectionSingleton.getInstance();
    var statement = connection.prepareStatement(query);
    updateTuple(statement, t);
    statement.close();
  }

}
