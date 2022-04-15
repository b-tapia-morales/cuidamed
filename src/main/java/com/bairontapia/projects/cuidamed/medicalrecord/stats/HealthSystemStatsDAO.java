package com.bairontapia.projects.cuidamed.medicalrecord.stats;

import com.bairontapia.projects.cuidamed.daotemplate.ReadOnlyDAO;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class HealthSystemStatsDAO implements
        ReadOnlyDAO<HealthSystemStats, Short> {

    private static final HealthSystemStatsDAO INSTANCE = new HealthSystemStatsDAO();
    private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();

    private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
            .pathBuilder("scripts", "class_queries", "stats");
    private static final String FIND_ALL_QUERY_PATH = RELATIVE_PATH_STRING + "health_system.sql";

    public static HealthSystemStatsDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public String findQuery() throws IOException {
        return null;
    }

    @Override
    public String findAllQuery() throws IOException {
        final var inputStream = CLASS_LOADER.getResourceAsStream(FIND_ALL_QUERY_PATH);
        return IOUtils.toString(Objects.requireNonNull(inputStream), Charset.defaultCharset());
    }

    @Override
    public HealthSystemStats readTuple(ResultSet resultSet) throws SQLException {
        final var healthSystem = resultSet.getShort(1);
        final var frequency = resultSet.getInt(2);
        return HealthSystemStats.createInstance(healthSystem, frequency);
    }

    @Override
    public void setKeyParameter(PreparedStatement statement, Short aShort) throws SQLException {

    }
}
