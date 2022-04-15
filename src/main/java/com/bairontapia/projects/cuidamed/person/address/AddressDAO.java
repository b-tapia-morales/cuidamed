package com.bairontapia.projects.cuidamed.person.address;

import com.bairontapia.projects.cuidamed.daotemplate.CrudDAO;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

public class AddressDAO implements CrudDAO<Address, String> {

    private static final AddressDAO INSTANCE = new AddressDAO();

    private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();

    private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
            .pathBuilder("scripts", "class_queries", "person", "address");
    private static final String FIND_ALL_QUERY_PATH = RELATIVE_PATH_STRING + "get_all.sql";
    private static final String FIND_QUERY_PATH = RELATIVE_PATH_STRING + "get.sql";
    private static final String SAVE_QUERY_PATH = RELATIVE_PATH_STRING + "save.sql";
    private static final String UPDATE_QUERY_PATH = RELATIVE_PATH_STRING + "update.sql";

    public static AddressDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public String findQuery() throws IOException {
        final var inputStream = CLASS_LOADER.getResourceAsStream(FIND_QUERY_PATH);
        return IOUtils.toString(Objects.requireNonNull(inputStream), Charset.defaultCharset());
    }

    @Override
    public String findAllQuery() throws IOException {
        final var inputStream = CLASS_LOADER.getResourceAsStream(FIND_ALL_QUERY_PATH);
        return IOUtils.toString(Objects.requireNonNull(inputStream), Charset.defaultCharset());
    }

    @Override
    public String saveQuery() throws IOException {
        final var inputStream = CLASS_LOADER.getResourceAsStream(SAVE_QUERY_PATH);
        return IOUtils.toString(Objects.requireNonNull(inputStream), Charset.defaultCharset());
    }

    @Override
    public String updateQuery() throws IOException {
        final var inputStream = CLASS_LOADER.getResourceAsStream(UPDATE_QUERY_PATH);
        return IOUtils.toString(Objects.requireNonNull(inputStream), Charset.defaultCharset());
    }

    @Override
    public void setKeyParameter(PreparedStatement statement, String rut) throws SQLException {
        statement.setString(1, rut);
    }

    @Override
    public void saveTuple(PreparedStatement statement, Address address) throws SQLException {
        statement.setShort(1, address.communeId());
        statement.setString(2, address.street());
        statement.setShort(3, address.number());
        if (address.postalCode() == null) {
            statement.setNull(4, Types.INTEGER);
        } else {
            statement.setInt(4, address.postalCode());
        }
        if (address.fixedPhone() == null) {
            statement.setNull(5, Types.INTEGER);
        } else {
            statement.setInt(5, address.fixedPhone());
        }
        statement.setString(6, address.rut());
    }

    @Override
    public void updateTuple(PreparedStatement statement, Address address) throws SQLException {
        statement.setInt(1, address.postalCode());
        statement.setInt(2, address.fixedPhone());
        statement.setString(3, address.rut());
    }

    @Override
    public Address readTuple(ResultSet resultSet) throws SQLException {
        final var communeId = resultSet.getShort(1);
        final var street = resultSet.getString(2);
        final var number = resultSet.getShort(3);
        final var postalCode = resultSet.getInt(4);
        final var fixedPhone = resultSet.getInt(5);
        final var rut = resultSet.getString(6);
        return Address.createInstance(communeId, street, number, postalCode, fixedPhone, rut);
    }
}
