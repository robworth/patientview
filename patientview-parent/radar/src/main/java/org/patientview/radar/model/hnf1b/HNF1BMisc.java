package org.patientview.radar.model.hnf1b;

import org.patientview.radar.model.BaseModel;
import org.patientview.radar.model.enums.YesNo;

public class HNF1BMisc extends BaseModel {

    private Long radarNo;
    private YesNo renalCysts;
    private YesNo singleKidney;
    private YesNo otherRenalMalformations;
    private String otherRenalMalformationsDetails;
    private YesNo diabetes;
    private int ageAtDiabetesDiagnosis;
    private YesNo gout;
    private int ageAtGoutDiagnosis;
    private YesNo genitalMalformation;
    private String genitalMalformationDetails;

    public Long getRadarNo() {
        return radarNo;
    }

    public void setRadarNo(Long radarNo) {
        this.radarNo = radarNo;
    }

    public YesNo getRenalCysts() {
        return renalCysts;
    }

    public void setRenalCysts(YesNo renalCysts) {
        this.renalCysts = renalCysts;
    }

    public YesNo getSingleKidney() {
        return singleKidney;
    }

    public void setSingleKidney(YesNo singleKidney) {
        this.singleKidney = singleKidney;
    }

    public YesNo getOtherRenalMalformations() {
        return otherRenalMalformations;
    }

    public void setOtherRenalMalformations(YesNo otherRenalMalformations) {
        this.otherRenalMalformations = otherRenalMalformations;
    }

    public YesNo getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(YesNo diabetes) {
        this.diabetes = diabetes;
    }

    public YesNo getGout() {
        return gout;
    }

    public void setGout(YesNo gout) {
        this.gout = gout;
    }

    public YesNo getGenitalMalformation() {
        return genitalMalformation;
    }

    public void setGenitalMalformation(YesNo genitalMalformation) {
        this.genitalMalformation = genitalMalformation;
    }

    public String getOtherRenalMalformationsDetails() {
        return otherRenalMalformationsDetails;
    }

    public void setOtherRenalMalformationsDetails(String otherRenalMalformationsDetails) {
        this.otherRenalMalformationsDetails = otherRenalMalformationsDetails;
    }

    public int getAgeAtDiabetesDiagnosis() {
        return ageAtDiabetesDiagnosis;
    }

    public String getAgeAtDiabetesDiagnosisAsString() {
        return Integer.toString(ageAtDiabetesDiagnosis);
    }

    public void setAgeAtDiabetesDiagnosisAsString(String ageAtDiabetesDiagnosis) {
        try {
            this.ageAtDiabetesDiagnosis = Integer.parseInt(ageAtDiabetesDiagnosis);
        } catch (Exception e) {
            // not sure obviously not a number
        }
    }

    public void setAgeAtDiabetesDiagnosis(int ageAtDiabetesDiagnosis) {
        this.ageAtDiabetesDiagnosis = ageAtDiabetesDiagnosis;
    }

    public int getAgeAtGoutDiagnosis() {
        return ageAtGoutDiagnosis;
    }

    public String getAgeAtGoutDiagnosisAsString() {
        return Integer.toString(ageAtGoutDiagnosis);
    }

    public void setAgeAtGoutDiagnosisAsString(String ageAtGoutDiagnosis) {
        try {
            this.ageAtGoutDiagnosis = Integer.parseInt(ageAtGoutDiagnosis);
        } catch (Exception e) {
            // not sure obviously not a number
        }
    }

    public void setAgeAtGoutDiagnosis(int ageAtGoutDiagnosis) {
        this.ageAtGoutDiagnosis = ageAtGoutDiagnosis;
    }

    public String getGenitalMalformationDetails() {
        return genitalMalformationDetails;
    }

    public void setGenitalMalformationDetails(String genitalMalformationDetails) {
        this.genitalMalformationDetails = genitalMalformationDetails;
    }


}
