package org.patientview.radar.test;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.patientview.common.test.BaseTestPvDbSchema;

import javax.inject.Inject;
import javax.sql.DataSource;

public class TestPvDbSchema extends BaseTestPvDbSchema {

    @Inject
    private DataSource dataSource;

    @Before
    public void testDbCreate() throws Exception {
        super.testDbCreate();

        // populate database with xml
        populateData();
    }

    private void populateData() throws Exception {

        // skip db stuff of
        if (!isLocalTestEnvironment()) {
            // Once we've got the tables created populate them with data - clean insert will delete all data first
            DatabaseDataSourceConnection databaseDataSourceConnection = new DatabaseDataSourceConnection(dataSource);

            // Set the database factory as in http://www.dbunit.org/faq.html#DefaultDataTypeFactory
            DatabaseConfig config = databaseDataSourceConnection.getConfig();

            config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
            config.setProperty(DatabaseConfig.PROPERTY_ESCAPE_PATTERN, "`?`");

            // Construct dataset
            XmlDataSet dataSet = new XmlDataSet(readFileFromClasspath("dataset.xml"));

            // Insert, cleanly (remove everything first)
            DatabaseOperation.CLEAN_INSERT.execute(databaseDataSourceConnection, dataSet);

            // Have to close the database connection
            databaseDataSourceConnection.close();
        }
    }
}
