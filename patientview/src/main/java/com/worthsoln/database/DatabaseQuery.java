package com.worthsoln.database;

import org.apache.commons.dbutils.ResultSetHandler;

public class DatabaseQuery {

    private String sql;
    private Object[] parameters;
    private ResultSetHandler rsHandler;

    public DatabaseQuery(String sql) {
        this.sql = sql;
    }

    public DatabaseQuery(String sql, Object[] parameters, ResultSetHandler rsHandler) {
        this.parameters = parameters;
        this.sql = sql;
        this.rsHandler = rsHandler;
    }

    public String getSql() {
        return sql;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public ResultSetHandler getResultSetHandler() {
        return rsHandler;
    }
}
