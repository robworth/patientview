package com.worthsoln.repository.impl;

import java.util.ArrayList;
import java.util.Collection;

import com.worthsoln.patientview.logon.LogonDao;
import com.worthsoln.patientview.logon.UnitAdmin;
import com.worthsoln.patientview.model.Specialty;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.worthsoln.database.DatabaseQuery;

class UnitUsersDao extends LogonDao {

    private String unitcode;
    private Specialty specialty;

    public UnitUsersDao(String unitcode, Specialty specialty) {
        this.unitcode = unitcode;
        this.specialty = specialty;
    }

    public Collection getRetrieveListWhereClauseParameters() {
        ArrayList params = new ArrayList();

        params.add(specialty.getId());
        params.add(unitcode);
        params.add("unitadmin");
        params.add("unitstaff");

        return params;
    }

    public DatabaseQuery getRetrieveListQuery() {
        ArrayList parameters = new ArrayList();

        parameters.addAll(getRetrieveListWhereClauseParameters());

        String sql = "SELECT user.* FROM user, usermapping, specialtyuserrole " +
                "WHERE user.username = usermapping.username " +
                "AND user.id = specialtyuserrole.user_id " +
                "AND specialtyuserrole.specialty_id = ? " +
                "AND usermapping.unitcode = ? AND ( specialtyuserrole.role = ? OR specialtyuserrole.role = ? )";

        ResultSetHandler rsHandler = new BeanListHandler(getTableMapper());

        return new DatabaseQuery(sql, parameters.toArray(), rsHandler);
    }

    public Class getTableMapper() {
        return UnitAdmin.class;
    }
}
