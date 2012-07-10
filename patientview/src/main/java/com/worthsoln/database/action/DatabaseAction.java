package com.worthsoln.database.action;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.Action;
import com.worthsoln.database.DatabaseDAO;

public abstract class DatabaseAction extends Action {

    public DatabaseAction() {
    }

    public abstract String getIdentifier();

    public abstract String getDatabaseName();

    protected DatabaseDAO getDao(HttpServletRequest request) throws Exception {
        return new DatabaseDAO(getDatabaseName());
    }
}
