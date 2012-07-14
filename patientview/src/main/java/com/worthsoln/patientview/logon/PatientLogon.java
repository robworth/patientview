package com.worthsoln.patientview.logon;

import java.util.Date;

public class PatientLogon extends Logon implements Cloneable {

    public PatientLogon() {
    }

    public PatientLogon(String username) {
        setUsername(username);
    }

    public PatientLogon(String username, String password, String name, String email, boolean emailverified,
                        boolean firstlogon, boolean dummypatient, Date lastlogon, int failedlogons,
                        boolean accountlocked, String screenname) {
        setUsername(username);
        setPassword(password);
        setName(name);
        setEmail(email);
        setEmailverfied(emailverified);
        setRole("patient");
        setFirstlogon(firstlogon);
        setDummypatient(dummypatient);
        setLastlogon(lastlogon);
        setFailedlogons(failedlogons);
        setAccountlocked(accountlocked);
        setScreenname(screenname);
    }

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
