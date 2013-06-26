package org.patientview.radar.model;


import org.patientview.radar.model.enums.NhsNumberType;
import org.patientview.radar.model.generic.DiseaseGroup;
import org.patientview.radar.model.generic.GenericDiagnosis;
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
    private NhsNumberType nhsNumberType;
    private String renalRegistryNumber;
    private String ukTransplantNumber;

    private Status status;
    private Clinician clinician;
    private Centre renalUnit;

    private boolean consent;
    private Centre renalUnitAuthorised;

    // generic extra fields
    private DiseaseGroup diseaseGroup;
    private String emailAddress;
    private String phone1;
    private String phone2;
    private String mobile;
    private RRTModality rrtModality;
    private GenericDiagnosis genericDiagnosis;
    private Date dateOfGenericDiagnosis;
    private String otherClinicianAndContactInfo;
    private String comments;

    private String republicOfIrelandId;
    private String isleOfManId;
    private String channelIslandsId;
    private String indiaId;

    private boolean generic;

    public enum RRTModality {
        HD(1),
        PD(2),
        Tx(3),
        NONE(-1);

        private int id;

        RRTModality(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

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

    public NhsNumberType getNhsNumberType() {
        return nhsNumberType;
    }

    public void setNhsNumberType(NhsNumberType nhsNumberType) {
        this.nhsNumberType = nhsNumberType;
    }

    // helper for templates
    public String getChiNumber() {
        if (getNhsNumberType().equals(NhsNumberType.CHI_NUMBER)) {
            return nhsNumber;
        }

        return null;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public DiseaseGroup getDiseaseGroup() {
        return diseaseGroup;
    }

    public void setDiseaseGroup(DiseaseGroup diseaseGroup) {
        this.diseaseGroup = diseaseGroup;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRepublicOfIrelandId() {
        return republicOfIrelandId;
    }

    public void setRepublicOfIrelandId(String republicOfIrelandId) {
        this.republicOfIrelandId = republicOfIrelandId;
    }

    public String getIsleOfManId() {
        return isleOfManId;
    }

    public void setIsleOfManId(String isleOfManId) {
        this.isleOfManId = isleOfManId;
    }

    public String getChannelIslandsId() {
        return channelIslandsId;
    }

    public void setChannelIslandsId(String channelIslandsId) {
        this.channelIslandsId = channelIslandsId;
    }

    public String getIndiaId() {
        return indiaId;
    }

    public void setIndiaId(String indiaId) {
        this.indiaId = indiaId;
    }

    public RRTModality getRrtModality() {
        return rrtModality;
    }

    public void setRrtModality(RRTModality rrtModality) {
        this.rrtModality = rrtModality;
    }

    public GenericDiagnosis getGenericDiagnosis() {
        return genericDiagnosis;
    }

    public void setGenericDiagnosis(GenericDiagnosis genericDiagnosis) {
        this.genericDiagnosis = genericDiagnosis;
    }

    public Date getDateOfGenericDiagnosis() {
        return dateOfGenericDiagnosis;
    }

    public void setDateOfGenericDiagnosis(Date dateOfGenericDiagnosis) {
        this.dateOfGenericDiagnosis = dateOfGenericDiagnosis;
    }

    public String getOtherClinicianAndContactInfo() {
        return otherClinicianAndContactInfo;
    }

    public void setOtherClinicianAndContactInfo(String otherClinicianAndContactInfo) {
        this.otherClinicianAndContactInfo = otherClinicianAndContactInfo;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isGeneric() {
        return generic;
    }

    public void setGeneric(boolean generic) {
        this.generic = generic;
    }

    public Clinician getClinician() {
        return clinician;
    }

    public void setClinician(Clinician clinician) {
        this.clinician = clinician;
    }
}
