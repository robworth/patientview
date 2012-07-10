package com.worthsoln.database;

import java.util.Hashtable;
import java.util.ResourceBundle;

public class DatabaseProperties {

    private static Hashtable dbPropertiesInstances;
    private static ResourceBundle dbProperties;

    public static synchronized DatabaseProperties getInstance(String databaseName) {
        DatabaseProperties dbProperties = null;
        if (dbPropertiesInstances == null) {
            dbPropertiesInstances = new Hashtable();
            dbProperties = new DatabaseProperties(databaseName);
            dbPropertiesInstances.put(databaseName, dbProperties);
        } else {
            dbProperties = (DatabaseProperties) dbPropertiesInstances.get(databaseName);
        }
        return dbProperties;
    }

    private String databaseName;

    private DatabaseProperties(String databaseName) {
        dbProperties = ResourceBundle.getBundle("database");
        setDatabaseName(databaseName);
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDriver() {
        return dbProperties.getString(databaseName + ".driver");
    }

    public String getUrl() {
        return dbProperties.getString(databaseName + ".url");
    }

    public String getUsername() {
        return dbProperties.getString(databaseName + ".username");
    }

    public String getPassword() {
        return dbProperties.getString(databaseName + ".password");
    }
}
