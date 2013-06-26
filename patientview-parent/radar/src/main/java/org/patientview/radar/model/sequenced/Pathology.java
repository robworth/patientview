package org.patientview.radar.model.sequenced;

import org.patientview.radar.model.enums.KidneyTransplantedNative;

import java.util.Date;

public class Pathology extends SequencedModel {

    public enum Side {
        LEFT(1), RIGHT(2);
        private int id;

        Side(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    private Date biopsyDate;

    private KidneyTransplantedNative kidneyTransplantedNative;
    private Side side;

    private String sampleLabNumber;

    private String interstitalInflmatoryInfilitrate, arterialAbnormalities, immunohistologicalFindings,
            electronMicroscopicFindings;

    // Tubules
    private Double estimatedTubules, measuredTubules;
    private String tubulesOtherFeature;

    // Images
    private String imageUrl1, imageUrl2, imageUrl3, imageUrl4, imageUrl5;

    // Glomerull stuff
    private Integer totalNumber, numberSclerosed, numberSegmentallySclerosed, numberCellularCrescents,
            numberFibrousCrescents, numberEndocapillaryHypercelluarity, numberFibrinoidNecrosis;
    private String otherFeature;

    private String histologicalSummary;

    public Date getBiopsyDate() {
        return biopsyDate;
    }

    public void setBiopsyDate(Date biopsyDate) {
        this.biopsyDate = biopsyDate;
    }

    public KidneyTransplantedNative getKidneyTransplantedNative() {
        return kidneyTransplantedNative;
    }

    public void setKidneyTransplantedNative(KidneyTransplantedNative kidneyTransplantedNative) {
        this.kidneyTransplantedNative = kidneyTransplantedNative;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public String getSampleLabNumber() {
        return sampleLabNumber;
    }

    public void setSampleLabNumber(String sampleLabNumber) {
        this.sampleLabNumber = sampleLabNumber;
    }

    public String getInterstitalInflmatoryInfilitrate() {
        return interstitalInflmatoryInfilitrate;
    }

    public void setInterstitalInflmatoryInfilitrate(String interstitalInflmatoryInfilitrate) {
        this.interstitalInflmatoryInfilitrate = interstitalInflmatoryInfilitrate;
    }

    public String getArterialAbnormalities() {
        return arterialAbnormalities;
    }

    public void setArterialAbnormalities(String arterialAbnormalities) {
        this.arterialAbnormalities = arterialAbnormalities;
    }

    public String getImmunohistologicalFindings() {
        return immunohistologicalFindings;
    }

    public void setImmunohistologicalFindings(String immunohistologicalFindings) {
        this.immunohistologicalFindings = immunohistologicalFindings;
    }

    public String getElectronMicroscopicFindings() {
        return electronMicroscopicFindings;
    }

    public void setElectronMicroscopicFindings(String electronMicroscopicFindings) {
        this.electronMicroscopicFindings = electronMicroscopicFindings;
    }

    public Double getEstimatedTubules() {
        return estimatedTubules;
    }

    public void setEstimatedTubules(Double estimatedTubules) {
        this.estimatedTubules = estimatedTubules;
    }

    public Double getMeasuredTubules() {
        return measuredTubules;
    }

    public void setMeasuredTubules(Double measuredTubules) {
        this.measuredTubules = measuredTubules;
    }

    public String getTubulesOtherFeature() {
        return tubulesOtherFeature;
    }

    public void setTubulesOtherFeature(String tubulesOtherFeature) {
        this.tubulesOtherFeature = tubulesOtherFeature;
    }

    public String getImageUrl1() {
        return imageUrl1;
    }

    public void setImageUrl1(String imageUrl1) {
        this.imageUrl1 = imageUrl1;
    }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public void setImageUrl2(String imageUrl2) {
        this.imageUrl2 = imageUrl2;
    }

    public String getImageUrl3() {
        return imageUrl3;
    }

    public void setImageUrl3(String imageUrl3) {
        this.imageUrl3 = imageUrl3;
    }

    public String getImageUrl4() {
        return imageUrl4;
    }

    public void setImageUrl4(String imageUrl4) {
        this.imageUrl4 = imageUrl4;
    }

    public String getImageUrl5() {
        return imageUrl5;
    }

    public void setImageUrl5(String imageUrl5) {
        this.imageUrl5 = imageUrl5;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Integer getNumberSclerosed() {
        return numberSclerosed;
    }

    public void setNumberSclerosed(Integer numberSclerosed) {
        this.numberSclerosed = numberSclerosed;
    }

    public Integer getNumberSegmentallySclerosed() {
        return numberSegmentallySclerosed;
    }

    public void setNumberSegmentallySclerosed(Integer numberSegmentallySclerosed) {
        this.numberSegmentallySclerosed = numberSegmentallySclerosed;
    }

    public Integer getNumberCellularCrescents() {
        return numberCellularCrescents;
    }

    public void setNumberCellularCrescents(Integer numberCellularCrescents) {
        this.numberCellularCrescents = numberCellularCrescents;
    }

    public Integer getNumberFibrousCrescents() {
        return numberFibrousCrescents;
    }

    public void setNumberFibrousCrescents(Integer numberFibrousCrescents) {
        this.numberFibrousCrescents = numberFibrousCrescents;
    }

    public Integer getNumberEndocapillaryHypercelluarity() {
        return numberEndocapillaryHypercelluarity;
    }

    public void setNumberEndocapillaryHypercelluarity(Integer numberEndocapillaryHypercelluarity) {
        this.numberEndocapillaryHypercelluarity = numberEndocapillaryHypercelluarity;
    }

    public Integer getNumberFibrinoidNecrosis() {
        return numberFibrinoidNecrosis;
    }

    public void setNumberFibrinoidNecrosis(Integer numberFibrinoidNecrosis) {
        this.numberFibrinoidNecrosis = numberFibrinoidNecrosis;
    }

    public String getOtherFeature() {
        return otherFeature;
    }

    public void setOtherFeature(String otherFeature) {
        this.otherFeature = otherFeature;
    }

    public String getHistologicalSummary() {
        return histologicalSummary;
    }

    public void setHistologicalSummary(String histologicalSummary) {
        this.histologicalSummary = histologicalSummary;
    }
}
