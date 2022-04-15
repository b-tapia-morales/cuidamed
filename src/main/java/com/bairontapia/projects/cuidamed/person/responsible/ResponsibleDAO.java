package com.bairontapia.projects.cuidamed.person.responsible;

import com.bairontapia.projects.cuidamed.daotemplate.CrudDAO;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class ResponsibleDAO implements CrudDAO<Responsible, String> {

    private static final ResponsibleDAO INSTANCE = new ResponsibleDAO();

    private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();

    private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
            .pathBuilder("scripts", "class_queries", "person", "responsible");
    private static final String FIND_QUERY_PATH = RELATIVE_PATH_STRING + "get.sql";
    private static final String FIND_ALL_QUERY_PATH = RELATIVE_PATH_STRING + "get_all.sql";
    private static final String SAVE_QUERY_PATH = RELATIVE_PATH_STRING + "save.sql";
    private static final String UPDATE_QUERY_PATH = RELATIVE_PATH_STRING + "update.sql";

    public static ResponsibleDAO getInstance() {
        return INSTANCE;
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
    public void setKeyParameter(PreparedStatement statement, String rut) throws SQLException {
        statement.setString(1, rut);
    }

    @Override
    public void saveTuple(PreparedStatement statement, Responsible responsible) throws SQLException {
        statement.setString(1, responsible.rut());
        statement.setString(2, responsible.firstName());
        statement.setString(3, responsible.lastName());
        statement.setString(4, responsible.secondLastName());
        statement.setDate(5, Date.valueOf(responsible.birthDate()));
        statement.setShort(6, (short) responsible.gender().getIndex());
        statement.setString(7, responsible.rut());
        statement.setInt(8, responsible.mobilePhone());
        statement.executeUpdate();
    }

    @Override
    public void updateTuple(PreparedStatement statement, Responsible responsible)
            throws SQLException {
        statement.setString(1, responsible.firstName());
        statement.setString(2, responsible.lastName());
        statement.setString(3, responsible.secondLastName());
        statement.setDate(4, Date.valueOf(responsible.birthDate()));
        statement.setShort(5, (short) responsible.gender().getIndex());
        statement.setString(6, responsible.rut());
        statement.setInt(7, responsible.mobilePhone());
        statement.setString(8, responsible.rut());
        statement.executeUpdate();
    }

    @Override
    public Responsible readTuple(ResultSet resultSet) throws SQLException {
        final var rut = resultSet.getString(1);
        final var firstName = resultSet.getString(2);
        final var lastName = resultSet.getString(3);
        final var secondLastName = resultSet.getString(4);
        final var birthDate = resultSet.getDate(5);
        final var gender = resultSet.getShort(6);
        final var mobilePhone = resultSet.getInt(7);
        return Responsible.createInstance(rut, firstName, lastName, secondLastName,
                birthDate, gender, mobilePhone);
    }

}
