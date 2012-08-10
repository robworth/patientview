package com.worthsoln.patientview.logon;

import com.worthsoln.database.StorableItem;

import java.util.ArrayList;

public class LogonDao extends StorableItem {

    private Logon logon;

    public LogonDao() {
    }

    public LogonDao(Logon logon) {
        this.logon = logon;
    }

    public String getIdColumnName() {
        return "username";
    }

    public String[] getColumnNames() {
        return new String[]{"password", "name", "email", "emailverified", "firstlogon", "dummypatient",
                "lastlogon", "failedlogons", "accountlocked", "screenname"};
    }

    public ArrayList getColumnParameters() {
        ArrayList params = new ArrayList();
        params.add(logon.getPassword());
        params.add(logon.getName());
        params.add(logon.getEmail());
        params.add(logon.isEmailverfied());
        params.add(logon.isFirstlogon());
        params.add(logon.isDummypatient());
        params.add(logon.getLastlogon());
        params.add(logon.getFailedlogons());
        params.add(logon.isAccountlocked());
        params.add(logon.getScreenname());
        return params;
    }

    public Object getIdParameter() {
        return logon.getUsername();
    }

    public Class getTableMapper() {
        return Logon.class;
    }

    public String getTableName() {
        return "user";
    }
}
