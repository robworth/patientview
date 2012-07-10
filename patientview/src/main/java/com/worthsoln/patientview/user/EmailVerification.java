package com.worthsoln.patientview.user;

import java.util.Calendar;

public class EmailVerification {

    private int id;
    private String username;
    private String email;
    private String verificationcode;
    private Calendar expirydatestamp;

    public EmailVerification() {
    }

    public EmailVerification(String username, String email, String verificationcode, Calendar expirydatestamp) {
        this.username = username;
        this.email = email;
        this.verificationcode = verificationcode;
        this.expirydatestamp = expirydatestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVerificationcode() {
        return verificationcode;
    }

    public void setVerificationcode(String verificationcode) {
        this.verificationcode = verificationcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Calendar getExpirydatestamp() {
        return expirydatestamp;
    }

    public void setExpirydatestamp(Calendar expirydatestamp) {
        this.expirydatestamp = expirydatestamp;
    }
}
