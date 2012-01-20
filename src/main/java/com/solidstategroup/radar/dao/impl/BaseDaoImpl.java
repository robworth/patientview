package com.solidstategroup.radar.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseDaoImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDaoImpl.class);

    protected JdbcTemplate jdbcTemplate;
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public static <T> T getEnumValue(Class<T> enumClass, int id) {
        try {
            // Assume we've been supplied an enum with an ID field, so get the accessor method
            Method getId = enumClass.getMethod("getId");
            // All enums have a static values method to get an array of the possible values
            Method method = enumClass.getMethod("values");

            // Use the static method to get an array of all the possible values
            T[] values = (T[]) method.invoke(null);
            for (T t : values) {
                // Get the ID field value
                Integer thisId = (Integer) getId.invoke(t);
                if (id == thisId) {
                    return t;
                }
            }

            // If we looped all the ID's and didn't find a value then return null
            LOGGER.debug("Could not find matching value for enum {} with ID {}", enumClass, id);
            return null;

        } catch (Exception e) {
            // This is pretty bad so lets throw a runtime exception
            LOGGER.error("Could not get values for enum {}", enumClass);
            throw new RuntimeException(e);
        }
    }

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Boolean getBooleanWithNullCheck(String column, ResultSet resultSet) throws SQLException {
        boolean value = resultSet.getBoolean(column);
        if (resultSet.wasNull()) {
            return null;
        }
        return value;
    }

    public Double getDoubleWithNullCheck(String column, ResultSet resultSet) throws SQLException {
        double value = resultSet.getLong(column);
        if (resultSet.wasNull()) {
            return null;
        }
        return value;
    }

    public Integer getIntegerWithNullCheck(String column, ResultSet resultSet) throws SQLException {
        int value = resultSet.getInt(column);
        if (resultSet.wasNull()) {
            return null;
        }
        return value;
    }
}
