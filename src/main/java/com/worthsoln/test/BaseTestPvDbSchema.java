package com.worthsoln.test;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;

public class BaseTestPvDbSchema {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTestPvDbSchema.class);

    @Inject
    private DataSource dataSource;

    @Inject
    private ApplicationContext applicationContext;

    @Value("${jdbc.databasename}")
    private String databaseName;

    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    private void emptyDatabase(Connection connection) throws Exception {
        LOGGER.info("Emptying database");

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SHOW TABLES");

        Statement dropStatement = connection.createStatement();

        while(resultSet.next()) {
            String tableName =  resultSet.getString(1);
            dropStatement.execute("DROP table " + tableName);
        }

        statement.close();
        dropStatement.close();
    }

    private void createTables(Connection connection, List<String> sqlFileNames) throws Exception {
        LOGGER.info("Starting create tables");

        Statement statement = connection.createStatement();

        // Our tables don't exist so we need to create them
        for (String script : sqlFileNames) {
            // Load the statements and execute each one
            InputStream inputStream = readFileFromClasspath(script);

            if (inputStream != null) {
                // Split by ; and execute
                String createTablesScript = IOUtils.toString(inputStream);

                for (String sqlStatement : createTablesScript.split(";")) {
                    if (StringUtils.isNotBlank(sqlStatement)) {
                        try {
                            statement.execute(sqlStatement);
                        } catch (SQLException e) {
                            String error = e.getMessage() + " error executing: " + script + ", sql:"
                                    + sqlStatement;
                            throw new Exception(error);
                        }
                    }
                }
            } else {
                LOGGER.error("Could not initialise database - could not load {}", script);
                throw new RuntimeException("Could not load " + script);
            }
        }

        statement.close();
    }

    protected InputStream readFileFromClasspath(String filename) throws IOException {
        Resource resource
                = applicationContext.getResource("classpath:" + filename);
        assertTrue("null resource", resource != null);
        InputStream inputStream = resource.getInputStream();
        assertTrue("inputStream resource", inputStream != null);
        return inputStream;
    }
}
