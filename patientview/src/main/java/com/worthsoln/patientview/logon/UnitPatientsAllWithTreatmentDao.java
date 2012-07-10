package com.worthsoln.patientview.logon;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.worthsoln.database.DatabaseQuery;

public class UnitPatientsAllWithTreatmentDao extends LogonDao {

    private String unitcode;

    public UnitPatientsAllWithTreatmentDao(String unitcode) {
        this.unitcode = unitcode;
    }

    public Collection getRetrieveListWhereClauseParameters() {
        ArrayList params = new ArrayList();
        params.add(unitcode);
        params.add("patient");
        params.add("%-GP");
        return params;
    }

    public DatabaseQuery getRetrieveListQuery() {
        ArrayList parameters = new ArrayList();
        parameters.addAll(getRetrieveListWhereClauseParameters());
        String sql = "SELECT " +
                " user.username,  user.password, user.name, user.email, usermapping.nhsno, usermapping.unitcode, " +
                " user.firstlogon, patient.treatment " +
                " FROM user, usermapping " +
                " LEFT JOIN patient ON usermapping.nhsno = patient.nhsno " +
                " WHERE usermapping.username = user.username " +
                " AND usermapping.unitcode = ? " +
                " AND user.role = ? " +
                " AND user.name NOT LIKE ? " +
                " ORDER BY user.name ASC";
        ResultSetHandler rsHandler = new BeanListHandler(getTableMapper());
        return new DatabaseQuery(sql, parameters.toArray(), rsHandler);
    }

    public Class getTableMapper() {
        return PatientLogonWithTreatment.class;
    }
}
