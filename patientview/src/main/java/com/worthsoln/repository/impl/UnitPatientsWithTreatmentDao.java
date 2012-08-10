package com.worthsoln.repository.impl;

import com.worthsoln.database.DatabaseQuery;
import com.worthsoln.patientview.logon.LogonDao;
import com.worthsoln.patientview.logon.PatientLogonWithTreatment;
import com.worthsoln.patientview.model.Tenancy;
import com.worthsoln.patientview.unit.UnitUtils;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.ArrayList;
import java.util.Collection;

/**
 *  This has been moved in the repository packages from the old database stuff.
 *
 *  Now package private to enforce usage via the Spring bean
 *
 *  This has been modified to join to the tenancy
 */
class UnitPatientsWithTreatmentDao extends LogonDao {

    private String unitcode;
    private String nhsno;
    private String name;
    private boolean showgps;
    private Tenancy tenancy;

    public UnitPatientsWithTreatmentDao(String unitcode, String nhsno, String name, boolean showgps, Tenancy tenancy) {
        this.unitcode = unitcode;
        this.nhsno = nhsno;
        this.name = name;
        this.showgps = showgps;
        this.tenancy = tenancy;
    }

    public Collection getRetrieveListWhereClauseParameters() {
        ArrayList params = new ArrayList();
        params.add("patient");
        if (!"".equals(unitcode)) {
            params.add(unitcode);
        }
        params.add("%" + nhsno + "%");
        params.add("%" + name + "%");
        if (!showgps) {
            params.add("%-GP");
        }
        params.add(tenancy.getId());
        return params;
    }

    public DatabaseQuery getRetrieveListQuery() {
        ArrayList parameters = new ArrayList();
        parameters.addAll(getRetrieveListWhereClauseParameters());
        String sql = "SELECT "
                + "user.username,  user.password, user.name, user.email, usermapping.nhsno, usermapping.unitcode, "
                + "user.firstlogon, patient.treatment "
                + "FROM user, tenancyuserrole, usermapping "
                + "LEFT JOIN patient ON usermapping.nhsno = patient.nhsno AND usermapping.unitcode = patient.centreCode "
                + "WHERE tenancyuserrole.role = ? "
                + "AND user.username = usermapping.username "
                + "AND user.id = tenancyuserrole.user_id "
                + "AND usermapping.unitcode <> '" + UnitUtils.PATIENT_ENTERS_UNITCODE + "' ";

        if (!"".equals(unitcode)) {
            sql += "AND usermapping.unitcode = ? ";
        }
        sql += "AND usermapping.nhsno LIKE ? "
                + "AND user.name LIKE ? ";
        if (!showgps) {
            sql += "AND user.name NOT LIKE ? ";
        }
        sql += "AND tenancyuserrole.tenancy_id = ? ";

        sql += "ORDER BY user.name ASC ";
        ResultSetHandler rsHandler = new BeanListHandler(getTableMapper());
        return new DatabaseQuery(sql, parameters.toArray(), rsHandler);
    }

    public Class getTableMapper() {
        return PatientLogonWithTreatment.class;
    }
}
