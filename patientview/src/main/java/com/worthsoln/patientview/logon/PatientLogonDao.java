package com.worthsoln.patientview.logon;

public class PatientLogonDao extends LogonDao {

    public PatientLogonDao(Logon logon) {
        super(logon);
    }

    public Class getTableMapper() {
        return PatientLogon.class;
    }
}
