package com.worthsoln.patientview.logon;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.worthsoln.database.DatabaseQuery;

public class UnitUsersDao extends LogonDao {

    private String unitcode;

    public UnitUsersDao(String unitcode) {
        this.unitcode = unitcode;
    }

    public Collection getRetrieveListWhereClauseParameters() {
        ArrayList params = new ArrayList();

        params.add(unitcode);
        params.add("unitadmin");
        params.add("unitstaff");

        return params;
    }

    public DatabaseQuery getRetrieveListQuery() {
        ArrayList parameters = new ArrayList();

        parameters.addAll(getRetrieveListWhereClauseParameters());

        String sql = "SELECT user.* FROM user, usermapping WHERE user.username = usermapping.username " +
                "AND usermapping.unitcode = ? AND ( user.role = ? OR user.role = ? )";

        ResultSetHandler rsHandler = new BeanListHandler(getTableMapper());

        return new DatabaseQuery(sql, parameters.toArray(), rsHandler);
    }

    public Class getTableMapper() {
        return UnitAdmin.class;
    }
}
