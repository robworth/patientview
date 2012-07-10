package com.worthsoln.database;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.sql.DataSource;

public interface ConnectionManager {

    Connection getConnection() throws NamingException, SQLException;

    DataSource getDataSource() throws NamingException, SQLException;
}
