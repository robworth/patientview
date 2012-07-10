package com.worthsoln.patientview.logon;

import java.util.ArrayList;
import java.util.Collection;

public class UnitPatientsDao extends LogonDao {

    private String unitcode;

    public UnitPatientsDao(String unitcode) {
        this.unitcode = unitcode;
    }

    public Collection getRetrieveListWhereClauseParameters() {
        ArrayList params = new ArrayList();

        params.add(unitcode);
        params.add("patient");

        return params;
    }

    public String getRetrieveListWhereClauseSql() {
        return "unitcode,role";
    }

    public String getRetrieveListWhereClauseEqualities() {
        return "=,=";
    }

    public Class getTableMapper() {
        return PatientLogonWithTreatment.class;
    }
}
