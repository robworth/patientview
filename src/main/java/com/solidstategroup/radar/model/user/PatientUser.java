package com.solidstategroup.radar.model.user;

import com.solidstategroup.radar.model.Ethnicity;
import com.solidstategroup.radar.model.Sex;
import org.joda.time.DateTime;
import org.joda.time.Years;

import java.util.Date;

public class PatientUser extends User {

    private String nhsNumber;
    private Long radarNumber;
    private Date dateOfBirth;
    private String sex;
    private String telephone2;
    private String mobile;

    private String surnameAlias;
    private String hospitalNumber;

    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String postcode;
    private String previousPostcode;

    @Override
    public String getSecurityRole() {
        return User.ROLE_PATIENT;
    }

    public Integer getAge() {
        // Return the difference between now and the date of birth
        if (dateOfBirth != null) {
            return Years.yearsBetween(new DateTime(dateOfBirth), new DateTime(new Date())).getYears();
        }
        return null;
    }

    public String getNhsNumber() {
        return nhsNumber;
    }

    public void setNhsNumber(String nhsNumber) {
        this.nhsNumber = nhsNumber;
    }

    public Long getRadarNumber() {
        return radarNumber;
    }

    public void setRadarNumber(Long radarNumber) {
        this.radarNumber = radarNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSurnameAlias() {
        return surnameAlias;
    }

    public void setSurnameAlias(String surnameAlias) {
        this.surnameAlias = surnameAlias;
    }

    public String getHospitalNumber() {
        return hospitalNumber;
    }

    public void setHospitalNumber(String hospitalNumber) {
        this.hospitalNumber = hospitalNumber;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPreviousPostcode() {
        return previousPostcode;
    }

    public void setPreviousPostcode(String previousPostcode) {
        this.previousPostcode = previousPostcode;
    }
}
