package com.solidstategroup.radar.test;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestPvDbSchema {
    @Inject
    private DataSource dataSource;

    @Inject
    private ApplicationContext applicationContext;

    @Value("${jdbc.databasename}")
    private String databaseName;

    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    private static final Logger LOGGER = LoggerFactory.getLogger(TestPvDbSchema.class);

    @Before
    public void testDbCreate() throws Exception {

        LOGGER.info("Starting db setup");

        // a list of all the sql file names we need to run in order
        List<String> sqlFileNames = new ArrayList<String>();
        sqlFileNames.add("sql/1-pvdbschema-create.sql");

        // work out the feature number we need to increment the db to

        Resource txtResource
                = applicationContext.getResource("classpath:current-db-feature-num.txt");

        assertTrue("null resource", txtResource != null);

        int featureNum = Integer.parseInt(IOUtils.toString(txtResource.getInputStream()));

        assertTrue("Invalid feature version", featureNum > 0);

        // iterate through the features folders and grab the features we need
        for (int i = 1; i <= featureNum; i++) {
            sqlFileNames.add("sql/features/" + i + "/" + i + ".sql");
        }

        // Create the tables from the SQL script
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();

            // See what tables are in the database
            ResultSet resultSet = statement.executeQuery("SHOW TABLES");
            if (!resultSet.next()) {
                LOGGER.info("Starting create tables");
                // Our tables don't exist so we need to create them
                for (String script : sqlFileNames) {

                    // Load the statements and execute each one
                    InputStream inputStream = readFileFromClasspath(script);

                    if (inputStream != null) {
                        // Split by ; and execute
                        String createTablesScript = IOUtils.toString(inputStream);

                        // need to clean up the sql if its running in H2 as some of the commands will fail
                        if (driverClassName.equals("org.h2.Driver")) {
                            createTablesScript = convertToH2(createTablesScript);
                        }

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
            } else {
                LOGGER.info("Starting truncate tables");

                // we want to truncate all the table data
                statement.execute("SET FOREIGN_KEY_CHECKS=0");

                ResultSet truncateResultSet = statement.executeQuery("SELECT CONCAT('TRUNCATE TABLE ', " +
                        "TABLE_NAME, ';')\n" +
                        "FROM INFORMATION_SCHEMA.TABLES\n" +
                        "WHERE TABLE_SCHEMA = '" + databaseName + "';");

                Statement truncateStatement = connection.createStatement();

                while (truncateResultSet.next()) {
                    String sql = truncateResultSet.getString(1);
                    truncateStatement.execute(sql);
                }

                statement.execute("SET FOREIGN_KEY_CHECKS=1");
            }

            // Once we've got the tables created populate them with data - clean insert will delete all data first
            DatabaseDataSourceConnection databaseDataSourceConnection = new DatabaseDataSourceConnection(dataSource);

            // Set the database factory as in http://www.dbunit.org/faq.html#DefaultDataTypeFactory
            DatabaseConfig config = databaseDataSourceConnection.getConfig();
            // h2
//            config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
//            config.setProperty(DatabaseConfig.PROPERTY_ESCAPE_PATTERN, "\"?\"");
            // mysql
            config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
            config.setProperty(DatabaseConfig.PROPERTY_ESCAPE_PATTERN, "`?`");

            // Construct dataset
            XmlDataSet dataSet = new XmlDataSet(readFileFromClasspath("dataset.xml"));

            // Insert, cleanly (remove everything first)
            DatabaseOperation.CLEAN_INSERT.execute(databaseDataSourceConnection, dataSet);

            // Have to close the database connection
            databaseDataSourceConnection.close();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private String convertToH2(String sql) {
        sql = sql.replaceAll("MODIFY", "ALTER"); // changing a column
        sql = sql.replaceAll("CHANGE", "ALTER"); // changing a column
        sql = sql.replaceAll("FIRST", ""); // adding a primary key
        return sql;
    }

    private InputStream readFileFromClasspath(String filename) throws IOException {
        Resource resource
                = applicationContext.getResource("classpath:" + filename);
        assertTrue("null resource", resource != null);
        InputStream inputStream = resource.getInputStream();
        assertTrue("inputStream resource", inputStream != null);
        return inputStream;
    }
}
