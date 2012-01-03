package com.solidstategroup.radar.test.dao;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Statement;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context.xml"})
public abstract class BaseDaoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDaoTest.class);
    private static final String CREATE_TABLES_SQL = "create_tables.sql";

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

            // Load the statements and execute each one
            InputStream inputStream =
                    Thread.currentThread().getContextClassLoader().getResourceAsStream(CREATE_TABLES_SQL);

            if (inputStream != null) {

                // Split by ; and execute
                String createTablesScript = IOUtils.toString(inputStream);
                for (String sqlStatement : createTablesScript.split(";")) {
                    statement.execute(sqlStatement);
                }

            } else {
                LOGGER.error("Could not initialise database - could not load {}", CREATE_TABLES_SQL);
                throw new RuntimeException("Could not load " + CREATE_TABLES_SQL);
            }

        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }


}
