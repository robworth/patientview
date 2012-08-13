package com.worthsoln.database;

import com.worthsoln.utils.LegacySpringUtils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.sql.DataSource;

public final class DatabaseConnectionManager implements ConnectionManager {

    private String databaseName;

    public DatabaseConnectionManager(String databaseName) {
        super();
        setDatabaseName(databaseName);
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public Connection getConnection() throws NamingException, SQLException {
        Connection conn = null;
        try {
            DataSource ds = getDataSource();
            if (ds != null) {
                conn = ds.getConnection();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public DataSource getDataSource() throws NamingException, SQLException {
        // just grab the spring database source we have setup
        return (DataSource) LegacySpringUtils.getSpringApplicationContextBean().getApplicationContext()
                .getBean("dataSource");
    }
}
