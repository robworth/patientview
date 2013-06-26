package org.patientview.radar.model.generic;

import java.io.Serializable;
import java.util.Date;

/**
 * table: testresult
 */
public class MedicalResult implements Serializable {
    private Long radarNo;
    private String nhsNo;
    private Double bloodUrea; // urea
    private Date bloodUreaDate; //
    private Double serumCreatanine; // creatinine
    private Date creatanineDate;
    private Double weight; // weight
    private Date weightDate;
    private Double height; // height
    private Date heightDate;
    private Integer bpSystolic; // BPsys
    private Integer bpDiastolic; // BPdia
    private Date bpDate;
    private YesNo antihypertensiveDrugs; //  antihypertensive
    private Date antihypertensiveDrugsDate; //  antihypertensive
    private Integer pcr;
    private Date pcrDate;
    private Integer acr;
    private Date acrDate;
    private DiseaseGroup diseaseGroup;

    boolean toBeUpdated = true; // to determine if medical results form is cleared and a new values will be entered
    boolean toBeValidated = true; // to find out if a submission from FE to BE needs to be saved to DB

    public enum YesNo {
        YES("1"),
        NO("0"),
        UNKNOWN("no");

        private String id;

        YesNo(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
        public static YesNo getById(String id) {
            for (YesNo yesNo : values()) {
                if (yesNo.getId().equals(id)) {
                    return yesNo;
                }
            }
            return null;
        }


        @Override
        public String toString() {
            if (this.equals(YES)) {
                return "Yes";
            } else if (this.equals(NO)) {
                return "No";
            } else {
                return "Unknown";
            }
        }
    }

    public Long getRadarNo() {
        return radarNo;
    }

    public void setRadarNo(Long radarNo) {
        this.radarNo = radarNo;
    }

    public String getNhsNo() {
        return nhsNo;
    }

    public void setNhsNo(String nhsNo) {
        this.nhsNo = nhsNo;
    }

    public Double getBloodUrea() {
        return bloodUrea;
    }

    public void setBloodUrea(Double bloodUrea) {
        this.bloodUrea = bloodUrea;
    }

    public Date getBloodUreaDate() {
        return bloodUreaDate;
    }

    public void setBloodUreaDate(Date bloodUreaDate) {
        this.bloodUreaDate = bloodUreaDate;
    }

    public Double getSerumCreatanine() {
        return serumCreatanine;
    }

    public void setSerumCreatanine(Double serumCreatanine) {
        this.serumCreatanine = serumCreatanine;
    }

    public Date getCreatanineDate() {
        return creatanineDate;
    }

    public void setCreatanineDate(Date creatanineDate) {
        this.creatanineDate = creatanineDate;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Date getWeightDate() {
        return weightDate;
    }

    public void setWeightDate(Date weightDate) {
        this.weightDate = weightDate;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Date getHeightDate() {
        return heightDate;
    }

    public void setHeightDate(Date heightDate) {
        this.heightDate = heightDate;
    }

    public Integer getBpSystolic() {
        return bpSystolic;
    }

    public void setBpSystolic(Integer bpSystolic) {
        this.bpSystolic = bpSystolic;
    }

    public Integer getBpDiastolic() {
        return bpDiastolic;
    }

    public void setBpDiastolic(Integer bpDiastolic) {
        this.bpDiastolic = bpDiastolic;
    }

    public Date getBpDate() {
        return bpDate;
    }

    public void setBpDate(Date bpDate) {
        this.bpDate = bpDate;
    }

    public YesNo getAntihypertensiveDrugs() {
        return antihypertensiveDrugs;
    }

    public void setAntihypertensiveDrugs(YesNo antihypertensiveDrugs) {
        this.antihypertensiveDrugs = antihypertensiveDrugs;
    }

    public Date getAntihypertensiveDrugsDate() {
        return antihypertensiveDrugsDate;
    }

    public void setAntihypertensiveDrugsDate(Date antihypertensiveDrugsDate) {
        this.antihypertensiveDrugsDate = antihypertensiveDrugsDate;
    }

    public Integer getPcr() {
        return pcr;
    }

    public void setPcr(Integer pcr) {
        this.pcr = pcr;
    }

    public Date getPcrDate() {
        return pcrDate;
    }

    public void setPcrDate(Date pcrDate) {
        this.pcrDate = pcrDate;
    }

    public Integer getAcr() {
        return acr;
    }

    public void setAcr(Integer acr) {
        this.acr = acr;
    }

    public Date getAcrDate() {
        return acrDate;
    }

    public void setAcrDate(Date acrDate) {
        this.acrDate = acrDate;
    }

    public DiseaseGroup getDiseaseGroup() {
        return diseaseGroup;
    }

    public void setDiseaseGroup(DiseaseGroup diseaseGroup) {
        this.diseaseGroup = diseaseGroup;
    }

    public boolean isToBeUpdated() {
        return toBeUpdated;
    }

    public void setToBeUpdated(boolean toBeUpdated) {
        this.toBeUpdated = toBeUpdated;
    }

    public boolean isToBeValidated() {
        return toBeValidated;
    }

    public void setToBeValidated(boolean toBeValidated) {
        this.toBeValidated = toBeValidated;
    }

    public void clearValues() {
        this.bloodUrea = null;
        this.bloodUreaDate = null;
        this.serumCreatanine = null;
        this.creatanineDate = null;
        this.weight = null;
        this.weightDate = null;
        this.height = null;
        this.heightDate = null;
        this.bpSystolic = null;
        this.bpDiastolic = null;
        this.bpDate = null;
        this.antihypertensiveDrugs = null;
        this.antihypertensiveDrugsDate = null;
        this.pcr = null;
        this.pcrDate = null;
        this.acr = null;
        this.acrDate = null;
    }

}
