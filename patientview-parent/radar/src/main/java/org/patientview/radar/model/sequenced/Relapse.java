package org.patientview.radar.model.sequenced;

import org.patientview.radar.model.enums.KidneyTransplantedNative;
import org.patientview.radar.model.enums.RemissionAchieved;

import java.util.Date;

public class Relapse extends SequencedModel {

    private Date dateOfRelapse;
    private KidneyTransplantedNative transplantedNative;
    private String viralTrigger, immunisationTrigger, otherTrigger;
    private String drug1, drug2, drug3;

    private RemissionAchieved remissionAchieved;
    private Date dateOfRemission;

    public Date getDateOfRelapse() {
        return dateOfRelapse;
    }

    public void setDateOfRelapse(Date dateOfRelapse) {
        this.dateOfRelapse = dateOfRelapse;
    }

    public KidneyTransplantedNative getTransplantedNative() {
        return transplantedNative;
    }

    public void setTransplantedNative(KidneyTransplantedNative transplantedNative) {
        this.transplantedNative = transplantedNative;
    }

    public String getViralTrigger() {
        return viralTrigger;
    }

    public void setViralTrigger(String viralTrigger) {
        this.viralTrigger = viralTrigger;
    }

    public String getImmunisationTrigger() {
        return immunisationTrigger;
    }

    public void setImmunisationTrigger(String immunisationTrigger) {
        this.immunisationTrigger = immunisationTrigger;
    }

    public String getOtherTrigger() {
        return otherTrigger;
    }

    public void setOtherTrigger(String otherTrigger) {
        this.otherTrigger = otherTrigger;
    }

    public String getDrug1() {
        return drug1;
    }

    public void setDrug1(String drug1) {
        this.drug1 = drug1;
    }

    public String getDrug2() {
        return drug2;
    }

    public void setDrug2(String drug2) {
        this.drug2 = drug2;
    }

    public String getDrug3() {
        return drug3;
    }

    public void setDrug3(String drug3) {
        this.drug3 = drug3;
    }

    public RemissionAchieved getRemissionAchieved() {
        return remissionAchieved;
    }

    public void setRemissionAchieved(RemissionAchieved remissionAchieved) {
        this.remissionAchieved = remissionAchieved;
    }

    public Date getDateOfRemission() {
        return dateOfRemission;
    }

    public void setDateOfRemission(Date dateOfRemission) {
        this.dateOfRemission = dateOfRemission;
    }
}
