package com.bairontapia.projects.cuidamed.daotemplate;

import com.bairontapia.projects.cuidamed.connection.ConnectionSingleton;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;

public interface OneToManyDAO<T, ID> extends ReadOnlyDAO<T, ID> {

    default Collection<T> findAll(ID id) throws IOException, SQLException {
        final var query = findQuery();
        final var connection = ConnectionSingleton.getInstance();
        final var statement = connection.prepareStatement(query);
        setKeyParameter(statement, id);
        final var resultSet = statement.executeQuery();
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
