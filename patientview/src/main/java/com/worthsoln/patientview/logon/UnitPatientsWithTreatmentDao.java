package com.worthsoln.patientview.logon;

import com.worthsoln.database.DatabaseQuery;
import com.worthsoln.patientview.unit.UnitUtils;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.ArrayList;
import java.util.Collection;

public class UnitPatientsWithTreatmentDao extends LogonDao {

    private String unitcode;
    private String nhsno;
    private String name;
    private boolean showgps;

    public UnitPatientsWithTreatmentDao(String unitcode, String nhsno, String name, boolean showgps) {
        this.unitcode = unitcode;
        this.nhsno = nhsno;
        this.name = name;
        this.showgps = showgps;
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
        return params;
    }

    public DatabaseQuery getRetrieveListQuery() {
        ArrayList parameters = new ArrayList();
        parameters.addAll(getRetrieveListWhereClauseParameters());
        String sql = "SELECT "
                + "user.username,  user.password, user.name, user.email, usermapping.nhsno, usermapping.unitcode, "
                + "user.firstlogon, patient.treatment "
                + "FROM user, usermapping "
                + "LEFT JOIN patient ON usermapping.nhsno = patient.nhsno AND usermapping.unitcode = patient.centreCode "
                + "WHERE user.role = ? "
                + "AND user.username = usermapping.username "
                + "AND usermapping.unitcode <> '" + UnitUtils.PATIENT_ENTERS_UNITCODE + "' ";

        if (!"".equals(unitcode)) {
            sql += "AND usermapping.unitcode = ? ";
        }
        sql += "AND usermapping.nhsno LIKE ? "
                + "AND user.name LIKE ? ";
        if (!showgps) {
            sql += "AND user.name NOT LIKE ? ";
        }
        sql += "ORDER BY user.name ASC ";
        ResultSetHandler rsHandler = new BeanListHandler(getTableMapper());
        return new DatabaseQuery(sql, parameters.toArray(), rsHandler);
    }

    public Class getTableMapper() {
        return PatientLogonWithTreatment.class;
    }
}
