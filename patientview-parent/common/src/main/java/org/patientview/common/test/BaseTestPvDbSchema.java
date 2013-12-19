/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.common.test;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    @Value("${config.environment}")
    private String configEnvironment;

    private boolean isLocalTestEnvironment;

    private static boolean isTableCreated = false;

    @PostConstruct
    public void init() {
        isLocalTestEnvironment = configEnvironment != null && configEnvironment.equals("localhost-test");
    }

    @Before
    public void testDbCreate() throws Exception {

        if (!isLocalTestEnvironment) {
            boolean isTestEnvironment = configEnvironment != null && configEnvironment.equals("test");
            if (!isTestEnvironment) {
                throw new IllegalStateException("Cannot run tests using "
                        + configEnvironment
                        + " profile you risk overwriting a real database");
            }

            LOGGER.info("Starting db setup");

            // a list of all the sql file names we need to run in order
            List<String> sqlFileNames = new ArrayList<String>();
            sqlFileNames.add("sql/1-pvdbschema-create.sql");

            // work out the feature number we need to increment the db to
            Resource txtResource = applicationContext.getResource("classpath:current-db-feature-num.txt");

            Assert.assertTrue("null resource", txtResource != null && txtResource.exists());

            int featureNum = Integer.parseInt(IOUtils.toString(txtResource.getInputStream()));

            Assert.assertTrue("Invalid feature version", featureNum > 0);

            // iterate through the features folders and grab the features we need
            for (int i = 1; i <= featureNum; i++) {
                sqlFileNames.add("sql/features/" + i + "/" + i + ".sql");
            }

            if (!sqlFileNames.isEmpty()) {
                Connection connection = null;

                try {
                    connection = dataSource.getConnection();

                    if (!isTableCreated) {
                        // empty db
                        emptyDatabase(connection);

                        // create tables
                        createTables(connection, sqlFileNames);

                        isTableCreated = true;
                    } else {
                        clearData(connection);
                    }

                } finally {
                    if (connection != null) {
                        connection.close();
                    }
                }
            }
        }
    }

    protected void emptyDatabase(Connection connection) throws Exception {
        LOGGER.info("Emptying database");

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SHOW TABLES");

        Statement dropStatement = connection.createStatement();
        dropStatement.execute("SET FOREIGN_KEY_CHECKS = 0;");

        while (resultSet.next()) {
            String tableName =  resultSet.getString(1);
            String sqlStatement = "DROP table " + tableName;
            LOGGER.info(sqlStatement);
            dropStatement.execute(sqlStatement);
        }

        dropStatement.execute("SET FOREIGN_KEY_CHECKS = 1;");
        statement.close();
        dropStatement.close();
    }

    protected void clearData(Connection connection) throws Exception {
        LOGGER.info("Clear data");

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SHOW TABLES");

        Statement dropStatement = connection.createStatement();
        dropStatement.execute("SET FOREIGN_KEY_CHECKS = 0;");

        while (resultSet.next()) {
            String tableName =  resultSet.getString(1);
            dropStatement.execute("TRUNCATE table " + tableName);
        }

        dropStatement.execute("SET FOREIGN_KEY_CHECKS = 1;");
        statement.close();
        dropStatement.close();
    }

    public void clearData() throws Exception {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            clearData(connection);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

    protected void createTables(Connection connection, List<String> sqlFileNames) throws Exception {
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
                            LOGGER.info(sqlStatement);
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
        Assert.assertTrue("null resource", resource != null);
        InputStream inputStream = resource.getInputStream();
        Assert.assertTrue("inputStream resource", inputStream != null);
        return inputStream;
    }

    protected boolean isLocalTestEnvironment() {
        return isLocalTestEnvironment;
    }
}
