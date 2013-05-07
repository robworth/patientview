package com.worthsoln.patientview.model;

import com.worthsoln.ibd.Ibd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Date;

@Entity(name = "pv_patientjoin_request")
public class JoinRequest extends BaseModel {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Date dateOfBirth;

    @Column(nullable = true)
    private String nhsNo;

    @Column(nullable = false)
    private String unitcode;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Date dateOfRequest;

    @Transient
    private String antiSpamAnswer;

    public JoinRequest() {
    }

    public JoinRequest(String firstName, String lastName, Date dateOfBirth, String nhsNo, String unitcode,
                       String email, Date dateOfRequest) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.nhsNo = nhsNo;
        this.unitcode = unitcode;
        this.email = email;
        this.dateOfRequest = dateOfRequest;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNhsNo() {
        return nhsNo;
    }

    public void setNhsNo(String nhsNo) {
        this.nhsNo = nhsNo;
    }

    public Date getDateOfRequest() {
        return dateOfRequest;
    }

    public void setDateOfRequest(Date dateOfRequest) {
        this.dateOfRequest = dateOfRequest;
    }

    public String getAntiSpamAnswer() {
        return antiSpamAnswer;
    }

    public void setAntiSpamAnswer(String antiSpamAnswer) {
        this.antiSpamAnswer = antiSpamAnswer;
    }

    public String getDateOfBirthFormatted() {
        if (dateOfBirth != null) {
            return Ibd.DATE_FORMAT.format(dateOfBirth);
        }

        return "";
    }

    public String getDateOfRequestFormatted() {
        if (dateOfRequest != null) {
            return Ibd.DATE_FORMAT.format(dateOfRequest);
        }

        return "";
    }
}
