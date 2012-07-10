package com.worthsoln.database;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
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
        DataSource ds = null;
        try {
            Context ctx = new InitialContext();
            if (ctx == null) {
                throw new Exception("Boom - No Context");
            }
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/" + databaseName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
}
