package com.worthsoln.database;

public class DatabaseUpdateQuery {

    private String sql;
    private Object[] parameters;

    public DatabaseUpdateQuery(String sql, Object[] parameters) {
        this.parameters = parameters;
        this.sql = sql;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public String getSql() {
        return sql;
    }
}
