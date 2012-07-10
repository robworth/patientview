package com.worthsoln.database;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public abstract class StorableItem
        implements InsertableItem, UpdateableItem, DeletableItem, RetrievableItem, RetrievableList {

    public abstract String getTableName();

    public abstract Object getIdParameter();

    public abstract String[] getColumnNames();

    public abstract ArrayList getColumnParameters();

    public abstract Class getTableMapper();

    public String getIdColumnName() {
        return getTableName() + "_id";
    }

    public DatabaseUpdateQuery getInsertQuery() {
        ArrayList parameters = new ArrayList();
        parameters.add(getIdParameter());
        parameters.addAll(getColumnParameters());
        String sql = SqlStringUtils.insertSql(getTableName(), parameters.size());
        return new DatabaseUpdateQuery(sql, parameters.toArray());
    }

    public DatabaseUpdateQuery getUpdateQuery() {
        ArrayList parameters = new ArrayList();
        parameters.addAll(getColumnParameters());
        parameters.add(getIdParameter());
        String sql = SqlStringUtils.updateSql(getTableName(), getIdColumnName(), getColumnNames());
        return new DatabaseUpdateQuery(sql, parameters.toArray());
    }

    public DatabaseUpdateQuery getDeleteQuery() {
        Object[] parameters = new Object[]{getIdParameter()};
        String sql = SqlStringUtils.deleteSql(getTableName(), getIdColumnName());
        return new DatabaseUpdateQuery(sql, parameters);
    }

    public DatabaseQuery getRetrieveQuery() {
        Object[] parameters = new Object[]{getIdParameter()};
        String sql = SqlStringUtils.retrieveSql(getTableName(), getIdColumnName());
        ResultSetHandler rsHandler = new BeanHandler(getTableMapper());
        return new DatabaseQuery(sql, parameters, rsHandler);
    }

    public DatabaseQuery getRetrieveListQuery() {
        ArrayList parameters = new ArrayList();
        parameters.addAll(getRetrieveListWhereClauseParameters());
        String sql = SqlStringUtils.retrieveListSql(getTableName(), getRetrieveListWhereClauseSql(),
                getRetrieveListWhereClauseEqualities(),
                getRetrieveListLogicalConnectors(),
                getRetrieveListOrderByClauseSql());
        ResultSetHandler rsHandler = new BeanListHandler(getTableMapper());
        return new DatabaseQuery(sql, parameters.toArray(), rsHandler);
    }

    public String getRetrieveListWhereClauseSql() {
        return null;
    }

    public String getRetrieveListWhereClauseEqualities() {
        return null;
    }

    public String getRetrieveListLogicalConnectors() {
        return null;
    }

    public Collection getRetrieveListWhereClauseParameters() {
        return new ArrayList();
    }

    public String getRetrieveListOrderByClauseSql() {
        return null;
    }
}
