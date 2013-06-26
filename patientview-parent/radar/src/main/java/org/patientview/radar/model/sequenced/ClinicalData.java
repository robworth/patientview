package org.patientview.radar.model.sequenced;

import org.patientview.radar.model.Phenotype;

import java.util.Date;

public class ClinicalData extends SequencedModel {
    public static final String OPHTHALMOSCOPY_HELP_TEXT = "If abnormal describe result, " +
            "eg hypertensive, retinopathy or Drusen";

    public enum DiabetesType {
        TYPE_I(1, "Type I IDDM"),
        TYPE_II(2, "Type II NIDDM"),
        NO(99, "No");
        private int id;
        private String label;

        DiabetesType(int id, String label) {
            this.id = id;
            this.label = label;
        }

        public int getId() {
            return id;
        }

        public String getLabel() {
            return label;
        }
    }

    public enum CourseOfDisease {
        ACUTE(1), CHRONIC(2), UNKNOWN(9);
        private int id;

        CourseOfDisease(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    public enum CkdStage {
        ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), UNKNOWN(0);
        private int id;

        CkdStage(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    private Date clinicalPictureDate;

    private Double height, weight; // Height in cm Weight in Kg

    // Check it out http://en.wikipedia.org/wiki/Blood_pressure
    private Integer systolicBloodPressure, diastolicBloodPressure;

    private Phenotype phenotype1, phenotype2, phenotype3, phenotype4;

    private String comments;

    private CourseOfDisease courseOfDisease;
    private String significantDiagnosis1, significantDiagnosis2;

    private Boolean oedema, anaemia, hypovalaemia, fever, thrombosis, peritonitis, pulmonaryOedema, hypertension,
            urticaria;
    private DiabetesType diabetesType;
    private Boolean rash, possibleImmunisationTrigger, partialLipodystrophy, preceedingInfection, chronicInfection,
            ophthalmoscopy;

    private String rashDetail, preceedingInfectionDetail, chronicInfectionDetail, ophthalmoscopyDetail;

    // For follow up page
    private Boolean infectionNecessitatingHospitalisation, complicationThrombosis;
    private String infectionDetail, complicationThrombosisDetail;

    private CkdStage ckdStage;
    private Boolean listedForTransplant;

    public Double getMeanArterialPressure() {
        // Got this from wikipedia
        if (diastolicBloodPressure != null && systolicBloodPressure != null) {
            return (double) diastolicBloodPressure + (1D / 3D) * (systolicBloodPressure - diastolicBloodPressure);
        }
        return null;
    }

    public Date getClinicalPictureDate() {
        return clinicalPictureDate;
    }

    public void setClinicalPictureDate(Date clinicalPictureDate) {
        this.clinicalPictureDate = clinicalPictureDate;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getSystolicBloodPressure() {
        return systolicBloodPressure;
    }

    public void setSystolicBloodPressure(Integer systolicBloodPressure) {
        this.systolicBloodPressure = systolicBloodPressure;
    }

    public Integer getDiastolicBloodPressure() {
        return diastolicBloodPressure;
    }

    public void setDiastolicBloodPressure(Integer diastolicBloodPressure) {
        this.diastolicBloodPressure = diastolicBloodPressure;
    }

    public Phenotype getPhenotype1() {
        return phenotype1;
    }

    public void setPhenotype1(Phenotype phenotype1) {
        this.phenotype1 = phenotype1;
    }

    public Phenotype getPhenotype2() {
        return phenotype2;
    }

    public void setPhenotype2(Phenotype phenotype2) {
        this.phenotype2 = phenotype2;
    }

    public Phenotype getPhenotype3() {
        return phenotype3;
    }

    public void setPhenotype3(Phenotype phenotype3) {
        this.phenotype3 = phenotype3;
    }

    public Phenotype getPhenotype4() {
        return phenotype4;
    }

    public void setPhenotype4(Phenotype phenotype4) {
        this.phenotype4 = phenotype4;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public CourseOfDisease getCourseOfDisease() {
        return courseOfDisease;
    }

    public void setCourseOfDisease(CourseOfDisease courseOfDisease) {
        this.courseOfDisease = courseOfDisease;
    }

    public String getSignificantDiagnosis1() {
        return significantDiagnosis1;
    }

    public void setSignificantDiagnosis1(String significantDiagnosis1) {
        this.significantDiagnosis1 = significantDiagnosis1;
    }

    public String getSignificantDiagnosis2() {
        return significantDiagnosis2;
    }

    public void setSignificantDiagnosis2(String significantDiagnosis2) {
        this.significantDiagnosis2 = significantDiagnosis2;
    }

    public Boolean getOedema() {
        return oedema;
    }

    public void setOedema(Boolean oedema) {
        this.oedema = oedema;
    }

    public Boolean getAnaemia() {
        return anaemia;
    }

    public void setAnaemia(Boolean anaemia) {
        this.anaemia = anaemia;
    }

    public Boolean getHypovalaemia() {
        return hypovalaemia;
    }

    public void setHypovalaemia(Boolean hypovalaemia) {
        this.hypovalaemia = hypovalaemia;
    }

    public Boolean getFever() {
        return fever;
    }

    public void setFever(Boolean fever) {
        this.fever = fever;
    }

    public Boolean getThrombosis() {
        return thrombosis;
    }

    public void setThrombosis(Boolean thrombosis) {
        this.thrombosis = thrombosis;
    }

    public Boolean getPeritonitis() {
        return peritonitis;
    }

    public void setPeritonitis(Boolean peritonitis) {
        this.peritonitis = peritonitis;
    }

    public Boolean getPulmonaryOedema() {
        return pulmonaryOedema;
    }

    public void setPulmonaryOedema(Boolean pulmonaryOedema) {
        this.pulmonaryOedema = pulmonaryOedema;
    }

    public Boolean getHypertension() {
        return hypertension;
    }

    public void setHypertension(Boolean hypertension) {
        this.hypertension = hypertension;
    }

    public Boolean getUrticaria() {
        return urticaria;
    }

    public void setUrticaria(Boolean urticaria) {
        this.urticaria = urticaria;
    }

    public DiabetesType getDiabetesType() {
        return diabetesType;
    }

    public void setDiabetesType(DiabetesType diabetesType) {
        this.diabetesType = diabetesType;
    }

    public Boolean getRash() {
        return rash;
    }

    public void setRash(Boolean rash) {
        this.rash = rash;
    }

    public Boolean getPossibleImmunisationTrigger() {
        return possibleImmunisationTrigger;
    }

    public void setPossibleImmunisationTrigger(Boolean possibleImmunisationTrigger) {
        this.possibleImmunisationTrigger = possibleImmunisationTrigger;
    }

    public Boolean getPartialLipodystrophy() {
        return partialLipodystrophy;
    }

    public void setPartialLipodystrophy(Boolean partialLipodystrophy) {
        this.partialLipodystrophy = partialLipodystrophy;
    }

    public Boolean getPreceedingInfection() {
        return preceedingInfection;
    }

    public void setPreceedingInfection(Boolean preceedingInfection) {
        this.preceedingInfection = preceedingInfection;
    }

    public Boolean getChronicInfection() {
        return chronicInfection;
    }

    public void setChronicInfection(Boolean chronicInfection) {
        this.chronicInfection = chronicInfection;
    }

    public Boolean getOphthalmoscopy() {
        return ophthalmoscopy;
    }

    public void setOphthalmoscopy(Boolean ophthalmoscopy) {
        this.ophthalmoscopy = ophthalmoscopy;
    }

    public String getRashDetail() {
        return rashDetail;
    }

    public void setRashDetail(String rashDetail) {
        this.rashDetail = rashDetail;
    }

    public String getPreceedingInfectionDetail() {
        return preceedingInfectionDetail;
    }

    public void setPreceedingInfectionDetail(String preceedingInfectionDetail) {
        this.preceedingInfectionDetail = preceedingInfectionDetail;
    }

    public String getChronicInfectionDetail() {
        return chronicInfectionDetail;
    }

    public void setChronicInfectionDetail(String chronicInfectionDetail) {
        this.chronicInfectionDetail = chronicInfectionDetail;
    }

    public String getOphthalmoscopyDetail() {
        return ophthalmoscopyDetail;
    }

    public void setOphthalmoscopyDetail(String ophthalmoscopyDetail) {
        this.ophthalmoscopyDetail = ophthalmoscopyDetail;
    }

    public Boolean getInfectionNecessitatingHospitalisation() {
        return infectionNecessitatingHospitalisation;
    }

    public void setInfectionNecessitatingHospitalisation(Boolean infectionNecessitatingHospitalisation) {
        this.infectionNecessitatingHospitalisation = infectionNecessitatingHospitalisation;
    }

    public Boolean getComplicationThrombosis() {
        return complicationThrombosis;
    }

    public void setComplicationThrombosis(Boolean complicationThrombosis) {
        this.complicationThrombosis = complicationThrombosis;
    }

    public String getInfectionDetail() {
        return infectionDetail;
    }

    public void setInfectionDetail(String infectionDetail) {
        this.infectionDetail = infectionDetail;
    }

    public String getComplicationThrombosisDetail() {
        return complicationThrombosisDetail;
    }

    public void setComplicationThrombosisDetail(String complicationThrombosisDetail) {
        this.complicationThrombosisDetail = complicationThrombosisDetail;
    }

    public CkdStage getCkdStage() {
        return ckdStage;
    }

    public void setCkdStage(CkdStage ckdStage) {
        this.ckdStage = ckdStage;
    }

    public Boolean getListedForTransplant() {
        return listedForTransplant;
    }

    public void setListedForTransplant(Boolean listedForTransplant) {
        this.listedForTransplant = listedForTransplant;
    }
}
