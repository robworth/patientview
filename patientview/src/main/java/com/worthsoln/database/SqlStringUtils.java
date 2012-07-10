package com.worthsoln.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

public class SqlStringUtils {

    private SqlStringUtils() {
    }

    public static String parameterPlaceHolders(int number, Collection defaults) {
        StringBuffer parameterList = new StringBuffer("");
        for (int i = 0; i < number; i++) {
            if (defaults.contains(new Integer(i))) {
                parameterList.append("DEFAULT,");
            } else {
                parameterList.append("?,");
            }
        }
        if (number > 0) {
            parameterList.delete(parameterList.length() - 1, parameterList.length());
        }
        return parameterList.toString();
    }

    public static String insertSql(String tableName, int numberParameters) {
        return "INSERT INTO " + tableName + " VALUES (" + parameterPlaceHolders(numberParameters, new ArrayList())
                + ")";
    }

    public static String insertSql(String tableName, int numberParameters, Collection defaults) {
        return "INSERT INTO " + tableName + " VALUES (" + parameterPlaceHolders(numberParameters, defaults) + ")";
    }

    public static String updateSql(String tableName, String indexColumnName, String[] columnNames) {
        StringBuffer sql = new StringBuffer("UPDATE " + tableName + " SET");
        for (int i = 0; i < columnNames.length; i++) {
            sql.append(" " + columnNames[i] + " = ?,");
        }
        sql.delete(sql.length() - 1, sql.length());
        sql.append(" WHERE " + indexColumnName + " = ?");
        return sql.toString();
    }

    public static String deleteSql(String tableName, String indexColumnName) {
        return "DELETE FROM " + tableName + " WHERE " + indexColumnName + " = ?";
    }

    public static String retrieveSql(String tableName, String indexColumnName) {
        return "SELECT * FROM " + tableName + " WHERE " + indexColumnName + " = ?";
    }

    public static String retrieveListSql(String tableName, String whereClause, String whereClauseEqualities,
                                         String orderByClause) {
        return retrieveListSql(tableName, whereClause, whereClauseEqualities, null, orderByClause);
    }

    public static String retrieveListSql(String tableName, String whereClause, String whereClauseEqualities,
                                         String logicalConnectors, String orderByClause) {
        String sql = "SELECT * FROM " + tableName;
        if (whereClause != null) {
            sql += " WHERE ";
            StringTokenizer fieldNameTokenizer = new StringTokenizer(whereClause, ",");
            StringTokenizer equalsTokenizer = null;
            if (whereClauseEqualities != null) {
                equalsTokenizer = new StringTokenizer(whereClauseEqualities, ",");
            }
            String equals = "=";
            StringTokenizer logicalTokenizer = null;
            if (logicalConnectors != null) {
                logicalTokenizer = new StringTokenizer(logicalConnectors, ",");
            }
            String logicalConnector = "AND";
            while (fieldNameTokenizer.hasMoreTokens()) {
                if (whereClauseEqualities != null) {
                    equals = equalsTokenizer.nextToken();
                }
                if (logicalConnectors != null) {
                    if (logicalTokenizer.hasMoreTokens()) {
                        logicalConnector = logicalTokenizer.nextToken();
                    } else {
                        logicalConnector = "AND";
                    }
                }
                sql += fieldNameTokenizer.nextToken() + " " + equals + " ? " + logicalConnector + " ";
            }
            sql = sql.substring(0, sql.length() - 5);
        }
        if (orderByClause != null) {
            sql += " ORDER BY " + orderByClause;
        }
        return sql;
    }
}
