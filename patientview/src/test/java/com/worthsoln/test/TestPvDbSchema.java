package com.worthsoln.test;

import com.worthsoln.service.impl.SpringApplicationContextBean;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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

import static org.junit.Assert.*;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml", "classpath:test-context.xml"})
@Transactional
public class TestPvDbSchema {

    @Inject
    private DataSource dataSource;

    @Inject
    private SpringApplicationContextBean springApplicationContextBean;

    private static final Logger LOGGER = LoggerFactory.getLogger(TestPvDbSchema.class);

    @Test
    @Rollback(false)
    public void testDbCreate() throws IOException, SQLException {

        // a list of all the sql file names we need to run in order
        List<String> sqlFileNames = new ArrayList<String>();
        sqlFileNames.add("sql/1-pvdbschema-create.sql");

        // work out the feature number we need to increment the db to

        Resource txtResource
                = springApplicationContextBean.getApplicationContext()
                .getResource("classpath:current-db-feature-num.txt");

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
                // Our tables don't exist so we need to create them
                for (String script : sqlFileNames) {

                    // Load the statements and execute each one
                    InputStream inputStream = readFileFromClasspath(script);

                    if (inputStream != null) {
                        // Split by ; and execute
                        String createTablesScript = IOUtils.toString(inputStream);
                        createTablesScript = createTablesScript.replaceAll("ENGINE=InnoDB", "");
                        createTablesScript = createTablesScript.replaceAll("ENGINE=MyISAM", "");

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
    }

    private InputStream readFileFromClasspath(String filename) throws IOException {
        Resource resource
                = springApplicationContextBean.getApplicationContext()
                .getResource("classpath:" + filename);
        assertTrue("null resource", resource != null);
        InputStream inputStream = resource.getInputStream();
        assertTrue("inputStream resource", inputStream != null);
        return inputStream;
    }
}
