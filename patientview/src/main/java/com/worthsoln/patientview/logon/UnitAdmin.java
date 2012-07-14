package com.worthsoln.patientview.logon;

public class UnitAdmin extends Logon implements Cloneable {

    public UnitAdmin() {
    }

    public UnitAdmin(String username, String password, String name, String email, boolean emailverified, String role,
                     boolean firstlogon) {
        setUsername(username);
        setPassword(password);
        setName(name);
        setEmail(email);
        setEmailverfied(emailverified);
        setRole(role);
        setFirstlogon(firstlogon);
        setScreenname("");
    }

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
