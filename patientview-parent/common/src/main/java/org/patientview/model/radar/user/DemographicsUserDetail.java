package org.patientview.model.radar.user;

import java.util.Date;

public class DemographicsUserDetail {

    private Date lastverificationdate;
    private String email;
    private Date lastlogon;
    private boolean accountlocked;
    private Date lastdatadate;

    public Date getLastverificationdate() {
        return lastverificationdate;
    }

    public void setLastverificationdate(Date lastverificationdate) {
        this.lastverificationdate = lastverificationdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastlogon() {
        return lastlogon;
    }

    public void setLastlogon(Date lastlogon) {
        this.lastlogon = lastlogon;
    }

    public boolean isAccountlocked() {
        return accountlocked;
    }

    public void setAccountlocked(boolean accountlocked) {
        this.accountlocked = accountlocked;
    }

    public Date getLastdatadate() {
        return lastdatadate;
    }

    public void setLastdatadate(Date lastdatadate) {
        this.lastdatadate = lastdatadate;
    }
}
