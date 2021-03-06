/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.model;

import org.joda.time.DateTime;
import org.joda.time.Years;
import org.patientview.model.enums.NhsNumberType;
import org.patientview.model.generic.DiseaseGroup;
import org.patientview.model.generic.GenericDiagnosis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Patient extends BaseModel {

    @Transient
    private static final SimpleDateFormat UK_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    @Column(nullable = false)
    private String nhsno;
    @Column
    private String surname;
    @Column
    private String forename;
    @Column
    private Date dateofbirth;
    @Column
    private String sex;
    @Column
    private String address1;
    @Column
    private String address2;
    @Column
    private String address3;
    @Column
    private String address4;
    @Column
    private String postcode;
    @Column
    private String telephone1;
    @Column
    private String telephone2;
    @Column
    private String mobile;
    @Column(nullable = false)
    private String unitcode;
    @Column
    private String diagnosis;
    @Column
    private Date diagnosisDate;
    @Column
    private String treatment;
    @Column
    private String transplantstatus;
    @Column
    private String hospitalnumber;
    @Column
    private String gpname;
    @Column
    private String gpaddress1;
    @Column
    private String gpaddress2;
    @Column
    private String gpaddress3;
    @Column
    private String gppostcode;
    @Column
    private String gptelephone;
    @Column
    private String gpemail;
    @Column
    private Date bmdexam;
    @Column
    private String bloodgroup;

    @Column(nullable = true, columnDefinition = "TEXT")
    // Note: this is used at the moment for IBD only.  The patient details view shows a separate "Other Conditions"
    // which is pulling in through DiagnosisManager.getOtherDiagnoses()
    private String otherConditions;

    private static final Logger LOGGER = LoggerFactory.getLogger(Patient.class);

    @Column
    private Long radarNo;

    @Column
    private String rrNo;

    @Column
    private Date dateReg;

    @Column
    private String nhsNoType;

    @Column
    private String uktNo;

    @Column
    private String surnameAlias;

    @Column(nullable = true)
    private Integer age;

    @Column
    private String ethnicGp;

    @Column
    private String postcodeOld;

    @Column
    private Boolean consent;

    @Column
    private Date dateBapnReg;

    @Column
    private String consNeph;

    @Column
    private Long status;

    @Column
    private String emailAddress;

    @Column(nullable = true)
    private Integer rrtModality;

    @Column
    private String otherClinicianAndContactInfo;

    @Column
    private String comments;

    @Column
    private String republicOfIrelandId;

    @Column
    private String isleOfManId;

    @Column
    private String channelIslandsId;

    @Column
    private String indiaId;

    @Column
    private Boolean generic;

    @Column
    private String genericDiagnosis;

    @Column
    private Date dateOfGenericDiagnosis;

    @Column(nullable = true)
    private Long radarConsentConfirmedByUserId;

    @Column
    private Date mostRecentTestResultDateRangeStopDate;

    @Column
    private String sourceType;

    @Column(nullable = true)
    private Long patientLinkId;

    @Transient
    private String patientLinkUnitCode;

    @Transient
    private Clinician clinician;

    @Transient
    private Centre renalUnit;

    @Transient
    private GenericDiagnosis genericDiagnosisModel;

    @Transient
    private Sex sexModel;

    @Transient
    private Centre renalUnitAuthorised;

    @Transient
    private Ethnicity ethnicity;

    @Transient
    private NhsNumberType nhsNumberType;

    @Transient
    private DiseaseGroup diseaseGroup;

    @Transient
    private RRTModality rrtModalityEunm;

    @Transient
    private Status statusModel;

    @Transient
    private Boolean diagnosisDateSelect;

    @Transient
    private boolean linked;

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

    public Patient() {
    }

    public Patient(String nhsno, String unitCode) {
        this.nhsno = nhsno;
        this.unitcode = unitCode;
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

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitCode) {
        this.unitcode = (unitCode != null) ? unitCode.toUpperCase() : unitCode;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public String getFormatedDateOfBirth() {

        if (dateofbirth != null) {
            return UK_DATE_FORMAT.format(dateofbirth);
        } else {
            return null;
        }

    }

    public String getDateOfBirthStr() {
        return UK_DATE_FORMAT.format(dateofbirth);
    }

    public void setDateofbirth(Date dateofbirth) {
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

    public Date getDiagnosisDate() {
        return diagnosisDate;
    }

    public void setDiagnosisDate(Date diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
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

    public String getGpemail() {
        return gpemail;
    }

    public void setGpemail(String gpemail) {
        this.gpemail = gpemail;
    }

    public String getOtherConditions() {
        return otherConditions;
    }

    public void setOtherConditions(String otherConditions) {
        this.otherConditions = otherConditions;
    }

    public Date getBmdexam() {
        return bmdexam;
    }

    public void setBmdexam(Date bmdexam) {
        this.bmdexam = bmdexam;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public Integer getAge() {
        // Return the difference between now and the date of birth
        if (dateofbirth != null) {
            return Years.yearsBetween(new DateTime(dateofbirth), new DateTime(new Date())).getYears();
        }
        return null;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean isConsent() {
        return consent;
    }

    public void setConsent(Boolean consent) {
        this.consent = consent;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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

    public Boolean isGeneric() {
        return generic;
    }

    public void setGeneric(Boolean generic) {
        this.generic = generic;
    }

    public String getGenericDiagnosis() {
        return genericDiagnosis;
    }

    public void setGenericDiagnosis(String genericDiagnosis) {
        this.genericDiagnosis = genericDiagnosis;
    }

    public Date getDateOfGenericDiagnosis() {
        return dateOfGenericDiagnosis;
    }

    public void setDateOfGenericDiagnosis(Date dateOfGenericDiagnosis) {
        this.dateOfGenericDiagnosis = dateOfGenericDiagnosis;
    }

    public Clinician getClinician() {
        return clinician;
    }

    public void setClinician(Clinician clinician) {
        this.clinician = clinician;
    }

    public Centre getRenalUnit() {
        return renalUnit;
    }

    public void setRenalUnit(Centre renalUnit) {
        this.renalUnit = renalUnit;
    }

    public GenericDiagnosis getGenericDiagnosisModel() {
        return genericDiagnosisModel;
    }

    public void setGenericDiagnosisModel(GenericDiagnosis genericDiagnosisModel) {
        this.genericDiagnosisModel = genericDiagnosisModel;
    }

    public Sex getSexModel() {
        return sexModel;
    }

    public void setSexModel(Sex sexModel) {
        this.sexModel = sexModel;
    }

    public Centre getRenalUnitAuthorised() {
        return renalUnitAuthorised;
    }

    public void setRenalUnitAuthorised(Centre renalUnitAuthorised) {
        this.renalUnitAuthorised = renalUnitAuthorised;
    }

    public Ethnicity getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(Ethnicity ethnicity) {
        this.ethnicity = ethnicity;
    }

    public Long getRadarNo() {
        return radarNo;
    }

    public void setRadarNo(Long radarNo) {
        this.radarNo = radarNo;
    }

    public String getRrNo() {
        return rrNo;
    }

    public void setRrNo(String rrNo) {
        this.rrNo = rrNo;
    }

    public Date getDateReg() {
        return dateReg;
    }

    public void setDateReg(Date dateReg) {
        this.dateReg = dateReg;
    }

    public String getNhsNoType() {
        return nhsNoType;
    }

    public void setNhsNoType(String nhsNoType) {
        this.nhsNoType = nhsNoType;
    }

    public String getUktNo() {
        return uktNo;
    }

    public void setUktNo(String uktNo) {
        this.uktNo = uktNo;
    }

    public String getSurnameAlias() {
        return surnameAlias;
    }

    public void setSurnameAlias(String snameAlias) {
        this.surnameAlias = snameAlias;
    }

    public String getEthnicGp() {
        return ethnicGp;
    }

    public void setEthnicGp(String ethnicGp) {
        this.ethnicGp = ethnicGp;
    }

    public String getPostcodeOld() {
        return postcodeOld;
    }

    public void setPostcodeOld(String postcodeOld) {
        this.postcodeOld = postcodeOld;
    }

    public Date getDateBapnReg() {
        return dateBapnReg;
    }

    public void setDateBapnReg(Date dateBapnReg) {
        this.dateBapnReg = dateBapnReg;
    }

    public String getConsNeph() {
        return consNeph;
    }

    public void setConsNeph(String consNeph) {
        this.consNeph = consNeph;
    }

    public NhsNumberType getNhsNumberType() {
        return nhsNumberType;
    }

    public void setNhsNumberType(NhsNumberType nhsNumberType) {
        this.nhsNumberType = nhsNumberType;
    }

    public DiseaseGroup getDiseaseGroup() {
        return diseaseGroup;
    }

    public void setDiseaseGroup(DiseaseGroup diseaseGroup) {
        this.diseaseGroup = diseaseGroup;
    }

    public Status getStatusModel() {
        return statusModel;
    }

    public void setStatusModel(Status statusModel) {
        this.statusModel = statusModel;
    }

    public Integer getRrtModality() {
        return rrtModality;
    }

    public void setRrtModality(Integer rrtModality) {
        this.rrtModality = rrtModality;
    }

    public RRTModality getRrtModalityEunm() {
        return rrtModalityEunm;
    }

    public void setRrtModalityEunm(RRTModality rrtModalityEunm) {
        this.rrtModalityEunm = rrtModalityEunm;
    }

    public Date getDob() {
        return getDateofbirth();
    }

    public void setDob(Date dob) {
        setDateofbirth(dob);
    }

    public Boolean getDiagnosisDateSelect() {
        return diagnosisDateSelect;
    }

    public void setDiagnosisDateSelect(Boolean diagnosisDateSelect) {
        this.diagnosisDateSelect = diagnosisDateSelect;
    }

    public Long getRadarConsentConfirmedByUserId() {
        return radarConsentConfirmedByUserId;
    }

    public void setRadarConsentConfirmedByUserId(Long radarConsentConfirmedByUserId) {
        this.radarConsentConfirmedByUserId = radarConsentConfirmedByUserId;
    }

    public Date getMostRecentTestResultDateRangeStopDate() {
        return mostRecentTestResultDateRangeStopDate;
    }

    public void setMostRecentTestResultDateRangeStopDate(Date mostRecentTestResultDateRangeStopDate) {
        this.mostRecentTestResultDateRangeStopDate = mostRecentTestResultDateRangeStopDate;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public Long getPatientLinkId() {
        return patientLinkId;
    }

    public void setPatientLinkId(Long patientLinkId) {
        this.patientLinkId = patientLinkId;
    }

    public boolean isLinked() {
        return patientLinkId != null && patientLinkId > 0;
    }

    public String getPatientLinkUnitCode() {
        return patientLinkUnitCode;
    }

    public void setPatientLinkUnitCode(String patientLinkUnitCode) {
        this.patientLinkUnitCode = patientLinkUnitCode;
    }

}



