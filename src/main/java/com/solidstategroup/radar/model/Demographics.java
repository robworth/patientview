package com.solidstategroup.radar.model;


import org.joda.time.DateTime;
import org.joda.time.Years;

import java.util.Date;

public class Demographics extends BaseModel {

    // Radar number is ID

    private Date dateRegistered;
    private String surname;
    private String forename;
    private Date dateOfBirth;
    private Sex sex;
    private Ethnicity ethnicity;
    private String address1, address2, address3, address4, postcode;

    private String surnameAlias;
    private String previousPostcode;

    private String hospitalNumber;
    private String nhsNumber;
    private String renalRegistryNumber;
    private String ukTransplantNumber;
    private String chiNumber;

    private Status status;
    private Consultant consultant;
    private Centre renalUnit;

    private boolean consent;
    private Centre renalUnitAuthorised;

    public Integer getAge() {
        // Return the difference between now and the date of birth
        if (dateOfBirth != null) {
            return Years.yearsBetween(new DateTime(dateOfBirth), new DateTime(new Date())).getYears();
        }
        return null;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Ethnicity getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(Ethnicity ethnicity) {
        this.ethnicity = ethnicity;
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

    public String getSurnameAlias() {
        return surnameAlias;
    }

    public void setSurnameAlias(String surnameAlias) {
        this.surnameAlias = surnameAlias;
    }

    public String getPreviousPostcode() {
        return previousPostcode;
    }

    public void setPreviousPostcode(String previousPostcode) {
        this.previousPostcode = previousPostcode;
    }

    public String getHospitalNumber() {
        return hospitalNumber;
    }

    public void setHospitalNumber(String hospitalNumber) {
        this.hospitalNumber = hospitalNumber;
    }

    public String getNhsNumber() {
        return nhsNumber;
    }

    public void setNhsNumber(String nhsNumber) {
        this.nhsNumber = nhsNumber;
    }

    public String getRenalRegistryNumber() {
        return renalRegistryNumber;
    }

    public void setRenalRegistryNumber(String renalRegistryNumber) {
        this.renalRegistryNumber = renalRegistryNumber;
    }

    public String getUkTransplantNumber() {
        return ukTransplantNumber;
    }

    public void setUkTransplantNumber(String ukTransplantNumber) {
        this.ukTransplantNumber = ukTransplantNumber;
    }

    public String getChiNumber() {
        return chiNumber;
    }

    public void setChiNumber(String chiNumber) {
        this.chiNumber = chiNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Consultant getConsultant() {
        return consultant;
    }

    public void setConsultant(Consultant consultant) {
        this.consultant = consultant;
    }

    public Centre getRenalUnit() {
        return renalUnit;
    }

    public void setRenalUnit(Centre renalUnit) {
        this.renalUnit = renalUnit;
    }


    public Centre getRenalUnitAuthorised() {
        return renalUnitAuthorised;
    }

    public void setRenalUnitAuthorised(Centre renalUnitAuthorised) {
        this.renalUnitAuthorised = renalUnitAuthorised;
    }

    public boolean isConsent() {
        return consent;
    }

    public void setConsent(boolean consent) {
        this.consent = consent;
    }
}
