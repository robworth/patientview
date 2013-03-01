package com.worthsoln.patientview.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Calendar;

@Entity
public class EmailVerification extends BaseModel {

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String verificationcode;

    @Column(nullable = false)
    private Calendar expirydatestamp;

    public EmailVerification() {
    }

    public EmailVerification(String username, String email, String verificationcode, Calendar expirydatestamp) {
        this.username = username;
        this.email = email;
        this.verificationcode = verificationcode;
        this.expirydatestamp = expirydatestamp;
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
