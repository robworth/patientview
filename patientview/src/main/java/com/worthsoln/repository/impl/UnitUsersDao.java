package com.worthsoln.repository.impl;

import java.util.ArrayList;
import java.util.Collection;

import com.worthsoln.patientview.logon.LogonDao;
import com.worthsoln.patientview.logon.UnitAdmin;
import com.worthsoln.patientview.model.Tenancy;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.worthsoln.database.DatabaseQuery;

class UnitUsersDao extends LogonDao {

    private String unitcode;
    private Tenancy tenancy;

    public UnitUsersDao(String unitcode, Tenancy tenancy) {
        this.unitcode = unitcode;
        this.tenancy = tenancy;
    }

    public Collection getRetrieveListWhereClauseParameters() {
        ArrayList params = new ArrayList();

        params.add(tenancy.getId());
        params.add(unitcode);
        params.add("unitadmin");
        params.add("unitstaff");

        return params;
    }

    public DatabaseQuery getRetrieveListQuery() {
        ArrayList parameters = new ArrayList();

        parameters.addAll(getRetrieveListWhereClauseParameters());

        String sql = "SELECT user.* FROM user, usermapping, tenancyuserrole " +
                "WHERE user.username = usermapping.username " +
                "AND user.id = tenancyuserrole.user_id " +
                "AND tenancyuserrole.tenancy_id = ? " +
                "AND usermapping.unitcode = ? AND ( tenancyuserrole.role = ? OR tenancyuserrole.role = ? )";

        ResultSetHandler rsHandler = new BeanListHandler(getTableMapper());

        return new DatabaseQuery(sql, parameters.toArray(), rsHandler);
    }

    public Class getTableMapper() {
        return UnitAdmin.class;
    }
}
