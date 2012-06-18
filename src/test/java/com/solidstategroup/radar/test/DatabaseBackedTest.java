package com.solidstategroup.radar.test;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class DatabaseBackedTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseBackedTest.class);

    // Filenames
    private static final String[] SQL_SCRIPTS = {"create_tables.sql", "phase2.sql", "testUpdates.sql"};
    private static final String DATASET_XML_FILENAME = "dataset.xml";

    @Autowired
    private DataSource dataSource;

    @Before
    public void initialise() throws Exception {

        // Create the tables from the SQL script
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();

            // See what tables are in the database
            ResultSet resultSet = statement.executeQuery("SHOW TABLES");
            if (!resultSet.next()) {
                // Our tables don't exist so we need to create them
                for (String script : SQL_SCRIPTS) {

                    // Load the statements and execute each one
                    InputStream inputStream = readFile(script);

                    if (inputStream != null) {
                        // Split by ; and execute
                        String createTablesScript = IOUtils.toString(inputStream);
                        createTablesScript = createTablesScript.replace("ENGINE=InnoDB", "");
                        for (String sqlStatement : createTablesScript.split(";")) {
                            if (StringUtils.isNotBlank(sqlStatement)) {
                                statement.execute(sqlStatement);
                            }
                        }

                    } else {
                        LOGGER.error("Could not initialise database - could not load {}", script);
                        throw new RuntimeException("Could not load " + script);
                    }

                }

            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        // Once we've got the tables created populate them with data - clean insert will delete all data first
        DatabaseDataSourceConnection databaseDataSourceConnection = new DatabaseDataSourceConnection(dataSource);

        // Set the database factory as in http://www.dbunit.org/faq.html#DefaultDataTypeFactory
        DatabaseConfig config = databaseDataSourceConnection.getConfig();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
        config.setProperty(DatabaseConfig.PROPERTY_ESCAPE_PATTERN, "\"?\"");

        // Construct dataset
        XmlDataSet dataSet = new XmlDataSet(readFile(DATASET_XML_FILENAME));

        // Insert, cleanly (remove everything first)
        DatabaseOperation.CLEAN_INSERT.execute(databaseDataSourceConnection, dataSet);

        // Have to close the database connection
        databaseDataSourceConnection.close();
    }

    private InputStream readFile(String filename) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
    }

}
