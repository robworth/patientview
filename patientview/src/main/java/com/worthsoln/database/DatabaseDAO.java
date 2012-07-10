package com.worthsoln.database;

import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;

public class DatabaseDAO {

    private final ConnectionManager connectionManager;

    public DatabaseDAO(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public DatabaseDAO(String databaseName) {
        connectionManager = new DatabaseConnectionManager(databaseName);
    }

    public void insertItem(InsertableItem insertableItem) {
        doExecute(insertableItem.getInsertQuery());
    }

    public void updateItem(UpdateableItem updateableItem) {
        doExecute(updateableItem.getUpdateQuery());
    }

    public void deleteItem(DeletableItem deletableItem) {
        doExecute(deletableItem.getDeleteQuery());
    }

    public Object retrieveItem(RetrievableItem retItem) {
        return doExecuteQuery(retItem.getRetrieveQuery());
    }

    public List retrieveList(RetrievableList retList) {
        return (List) doExecuteQuery(retList.getRetrieveListQuery());
    }

    public void doExecute(DatabaseUpdateQuery query) {
        try {
            DataSource datasource = connectionManager.getDataSource();
            QueryRunner runner = new QueryRunner(datasource);
            runner.update(query.getSql(), query.getParameters());
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Object doExecuteQuery(DatabaseQuery query) {
        Object result = null;
        try {
            DataSource datasource = connectionManager.getDataSource();
            QueryRunner runner = new QueryRunner(datasource);
            result = runner.query(query.getSql(), query.getParameters(), query.getResultSetHandler());
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
