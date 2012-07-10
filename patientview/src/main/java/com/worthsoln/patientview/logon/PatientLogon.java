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
                        boolean accountlocked, String screenname, String splashpage) {
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
        setSplashpage(splashpage);
    }

    //TODO: delete this constructor after multiple unit feature implemented
/*
    public PatientLogon(String username, String password, String name, String email, boolean emailverified, String nhsno, String unitcode,
                        boolean firstlogon, boolean dummypatient, Date lastlogon, int failedlogons,
                        boolean accountlocked, String screenname, String splashpage) {
        setUsername(username);
        setPassword(password);
        setName(name);
        setEmail(email);
        setEmailverfied(emailverified);
        setNhsno(nhsno);
        setUnitcode(unitcode);
        setRole("patient");
        setFirstlogon(firstlogon);
        setDummypatient(dummypatient);
        setLastlogon(lastlogon);
        setFailedlogons(failedlogons);
        setAccountlocked(accountlocked);
        setScreenname(screenname);
        setSplashpage(splashpage);
    }
*/

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
