package com.bairontapia.projects.cuidamed.daotemplate;

import com.bairontapia.projects.cuidamed.connection.ConnectionSingleton;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface GenericCrudDAO<T, ID> extends GenericReadAndWriteDAO<T, ID> {

  String updateQuery() throws IOException;

  void updateTuple(PreparedStatement statement, T t) throws SQLException;

  default void update(T t) throws IOException, SQLException {
    var query = updateQuery();
    var connection = ConnectionSingleton.getInstance();
    var statement = connection.prepareStatement(query);
    updateTuple(statement, t);
    statement.close();
  }

}
