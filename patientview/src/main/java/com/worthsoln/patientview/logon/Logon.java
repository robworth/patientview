package com.worthsoln.patientview.logon;

import com.worthsoln.service.UserManager;
import com.worthsoln.utils.LegacySpringUtils;

import java.util.Date;

public abstract class Logon {

    private String username;
    private String password;

    // This role attribute has gone a bit wonky now - to the getter and setter.
    // The role is now stored against the tenancy
    private String role;
    private String name;
    private String email;
    private boolean emailverfied;
    private String nhsno;
    private String unitcode;
    private boolean firstlogon;
    private boolean dummypatient;
    private Date lastlogon;
    private int failedlogons;
    private boolean accountlocked;
    private String screenname;

    public Logon() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {

        if (role == null) {
            // attempt to work this out cos it's no longer in this db table
            UserManager userManager = LegacySpringUtils.getUserManager();
            role = userManager.getCurrentTenancyRole(userManager.get(this.getUsername()));
        }

        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = (unitcode != null) ? unitcode.toUpperCase() : unitcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailverfied() {
        return emailverfied;
    }

    public void setEmailverfied(boolean emailverfied) {
        this.emailverfied = emailverfied;
    }

    public boolean isFirstlogon() {
        return firstlogon;
    }

    public void setFirstlogon(boolean firstlogon) {
        this.firstlogon = firstlogon;
    }

    public String getDisplayRole() {
        return LogonUtils.displayRole(getRole());
    }

    public boolean isDummypatient() {
        return dummypatient;
    }

    public void setDummypatient(boolean dummypatient) {
        this.dummypatient = dummypatient;
    }

    public Date getLastlogon() {
        return lastlogon;
    }

    public void setLastlogon(Date lastlogon) {
        this.lastlogon = lastlogon;
    }

    public int getFailedlogons() {
        return failedlogons;
    }

    public void setFailedlogons(int failedlogons) {
        this.failedlogons = failedlogons;
    }

    public boolean isAccountlocked() {
        return accountlocked;
    }

    public void setAccountlocked(boolean accountlocked) {
        this.accountlocked = accountlocked;
    }

    public String getScreenname() {
        return screenname;
    }

    public void setScreenname(String screenname) {
        this.screenname = screenname;
    }
}
