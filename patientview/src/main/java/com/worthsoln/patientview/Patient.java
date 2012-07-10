package com.worthsoln.patientview;

public class Patient {

    private String nhsno;
    private String surname;
    private String forename;
    private String dateofbirth;
    private String sex;
    private String address1;
    private String address2;
    private String address3;
    private String postcode;
    private String telephone1;
    private String telephone2;
    private String mobile;
    private String centreCode;
    private String diagnosis;
    private String treatment;
    private String transplantstatus;
    private String hospitalnumber;
    private String gpname;
    private String gpaddress1;
    private String gpaddress2;
    private String gpaddress3;
    private String gppostcode;
    private String gptelephone;

    public Patient() {
    }

    public Patient(String nhsno, String centreCode) {
        this.nhsno = nhsno;
        this.centreCode = centreCode;
    }

    public Patient(String nhsno, String surname, String forename, String dateofbirth, String sex, String address1,
                   String address2, String address3, String postcode, String telephone1, String telephone2,
                   String mobile, String centreCode, String diagnosis, String treatment, String transplantstatus,
                   String hospitalnumber, String gpname, String gpaddress1, String gpaddress2, String gpaddress3,
                   String gppostcode, String gptelephone) {
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        setCentreCode(centreCode);
        this.dateofbirth = dateofbirth;
        this.forename = forename;
        this.nhsno = nhsno;
        this.postcode = postcode;
        this.sex = sex;
        this.surname = surname;
        this.telephone1 = telephone1;
        this.telephone2 = telephone2;
        this.mobile = mobile;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.transplantstatus = transplantstatus;
        this.hospitalnumber = hospitalnumber;
        this.gpname = gpname;
        this.gpaddress1 = gpaddress1;
        this.gpaddress2 = gpaddress2;
        this.gpaddress3 = gpaddress3;
        this.gppostcode = gppostcode;
        this.gptelephone = gptelephone;
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

    public String getCentreCode() {
        return centreCode;
    }

    public void setCentreCode(String centreCode) {
        this.centreCode = (centreCode != null) ? centreCode.toUpperCase() : centreCode;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
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

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getHospitalnumber() {
        return hospitalnumber;
    }

    public void setHospitalnumber(String hospitalnumber) {
        this.hospitalnumber = hospitalnumber;
    }

    public String getTransplantstatus() {
        return transplantstatus;
    }

    public void setTransplantstatus(String transplantstatus) {
        this.transplantstatus = transplantstatus;
    }

    public String getGpaddress1() {
        return gpaddress1;
    }

    public void setGpaddress1(String gpaddress1) {
        this.gpaddress1 = gpaddress1;
    }

    public String getGpaddress2() {
        return gpaddress2;
    }

    public void setGpaddress2(String gpaddress2) {
        this.gpaddress2 = gpaddress2;
    }

    public String getGpaddress3() {
        return gpaddress3;
    }

    public void setGpaddress3(String gpaddress3) {
        this.gpaddress3 = gpaddress3;
    }

    public String getGpname() {
        return gpname;
    }

    public void setGpname(String gpname) {
        this.gpname = gpname;
    }

    public String getGppostcode() {
        return gppostcode;
    }

    public void setGppostcode(String gppostcode) {
        this.gppostcode = gppostcode;
    }

    public String getGptelephone() {
        return gptelephone;
    }

    public void setGptelephone(String gptelephone) {
        this.gptelephone = gptelephone;
    }
}
