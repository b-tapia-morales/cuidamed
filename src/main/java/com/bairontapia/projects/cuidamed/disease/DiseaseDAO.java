package com.bairontapia.projects.cuidamed.disease;


import com.bairontapia.projects.cuidamed.daotemplate.CrudDAO;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class DiseaseDAO implements CrudDAO<Disease, String> {

    private static final DiseaseDAO INSTANCE = new DiseaseDAO();

    private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();


    private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
            .pathBuilder("scripts", "class_queries", "disease");
    private static final String FIND_QUERY_PATH = RELATIVE_PATH_STRING + "get.sql";
    private static final String FIND_ALL_QUERY_PATH = RELATIVE_PATH_STRING + "get_all.sql";
    private static final String UPDATE_QUERY_PATH = RELATIVE_PATH_STRING + "update.sql";
    private static final String SAVE_QUERY_PATH = RELATIVE_PATH_STRING + "save.sql";

    public static DiseaseDAO getInstance() {
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
    public void setKeyParameter(PreparedStatement statement, String diseaseName) throws SQLException {
        statement.setString(1, diseaseName);
    }

    @Override
    public void saveTuple(PreparedStatement statement, Disease disease) throws SQLException {
        statement.setString(1, disease.disease_name());
        statement.setShort(2, (short) disease.diseaseType().getIndex());
        statement.setBoolean(3, disease.is_chronic());
        statement.executeUpdate();
    }

    @Override
    public void updateTuple(PreparedStatement statement, Disease disease) throws SQLException {

    }

    @Override
    public Disease readTuple(ResultSet resultSet) throws SQLException {
        final var diseaseName = resultSet.getString(1);
        final var diseaseType = resultSet.getShort(2);
        final var isChronic = resultSet.getBoolean(3);
        return Disease.createInstance(diseaseName, diseaseType, isChronic);
    }
}
