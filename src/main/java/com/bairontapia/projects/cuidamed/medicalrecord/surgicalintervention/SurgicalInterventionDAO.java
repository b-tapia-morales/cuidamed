package com.bairontapia.projects.cuidamed.medicalrecord.surgicalintervention;

import com.bairontapia.projects.cuidamed.daotemplate.OneToManyDAO;
import com.bairontapia.projects.cuidamed.daotemplate.ReadAndWriteDAO;
import com.bairontapia.projects.cuidamed.utils.paths.DirectoryPathUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class SurgicalInterventionDAO
        implements ReadAndWriteDAO<SurgicalIntervention, String>,
        OneToManyDAO<SurgicalIntervention, String> {

    private static final SurgicalInterventionDAO INSTANCE = new SurgicalInterventionDAO();

    private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();

    private static final String RELATIVE_PATH_STRING = DirectoryPathUtils
            .pathBuilder("scripts", "class_queries", "medical_record", "surgical_intervention");
    private static final String FIND_ALL_QUERY_PATH = RELATIVE_PATH_STRING + "get_all.sql";
    private static final String FIND_QUERY_PATH = RELATIVE_PATH_STRING + "get.sql";
    private static final String SAVE_QUERY_PATH = RELATIVE_PATH_STRING + "save.sql";

    public static SurgicalInterventionDAO getInstance() {
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
    public void setKeyParameter(PreparedStatement statement, String rut) throws SQLException {
        statement.setString(1, rut);
    }

    @Override
    public void saveTuple(PreparedStatement statement, SurgicalIntervention surgicalIntervention)
            throws SQLException {
        statement.setString(1, surgicalIntervention.rut());
        statement.setDate(2, Date.valueOf(surgicalIntervention.interventionDate()));
        statement.setString(3, surgicalIntervention.hospital());
        statement.setShort(4, (short) surgicalIntervention.severity().getIndex());
        statement.setString(5, surgicalIntervention.description());
        statement.executeUpdate();
    }

    @Override
    public SurgicalIntervention readTuple(ResultSet resultSet) throws SQLException {
        final var elderRut = resultSet.getString(1);
        final var interventionDate = resultSet.getDate(2);
        final var hospital = resultSet.getString(3);
        final var severity = resultSet.getShort(4);
        final var description = resultSet.getString(5);
        return SurgicalIntervention.createInstance(elderRut, interventionDate, hospital, severity,
                description);
    }

}
