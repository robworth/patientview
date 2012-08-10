package com.worthsoln.repository.impl;

import java.util.ArrayList;
import java.util.Collection;

import com.worthsoln.patientview.logon.LogonDao;
import com.worthsoln.patientview.logon.PatientLogonWithTreatment;
import com.worthsoln.patientview.model.Tenancy;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.worthsoln.database.DatabaseQuery;

class UnitPatientsAllWithTreatmentDao extends LogonDao {

    private String unitcode;
    private Tenancy tenancy;

    public UnitPatientsAllWithTreatmentDao(String unitcode, Tenancy tenancy) {
        this.unitcode = unitcode;
        this.tenancy = tenancy;
    }

    public Collection getRetrieveListWhereClauseParameters() {
        ArrayList params = new ArrayList();
        params.add(unitcode);
        params.add("patient");
        params.add("%-GP");
        params.add(tenancy.getId());
        return params;
    }

    public DatabaseQuery getRetrieveListQuery() {
        ArrayList parameters = new ArrayList();
        parameters.addAll(getRetrieveListWhereClauseParameters());
        String sql = "SELECT " +
                " user.username,  user.password, user.name, user.email, usermapping.nhsno, usermapping.unitcode, " +
                " user.firstlogon, patient.treatment " +
                " FROM user, tenancyuserrole, usermapping " +
                " LEFT JOIN patient ON usermapping.nhsno = patient.nhsno " +
                " WHERE usermapping.username = user.username " +
                " AND user.id = tenancyuserrole.user_id " +
                " AND usermapping.unitcode = ? " +
                " AND tenancyuserrole.role = ? " +
                " AND user.name NOT LIKE ? " +
                " AND tenancyuserrole.tenancy_id = ? " +
                " ORDER BY user.name ASC";
        ResultSetHandler rsHandler = new BeanListHandler(getTableMapper());
        return new DatabaseQuery(sql, parameters.toArray(), rsHandler);
    }

    public Class getTableMapper() {
        return PatientLogonWithTreatment.class;
    }
}
