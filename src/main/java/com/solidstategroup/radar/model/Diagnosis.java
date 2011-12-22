package com.solidstategroup.radar.model;

import java.util.Date;

public class Diagnosis {

    private DiagnosisCode diagnosisCode;
    private String text;
    private Date biopsyDate;
    private Date esrfDate;
    private int age;
    private boolean prepubertal;
    private int height;
    private ClinicalPresentation clinicalPresentationA, clinicalPresentationB;
    private Date onsetSymptomsDate;

    public DiagnosisCode getDiagnosisCode() {
        return diagnosisCode;
    }

    public void setDiagnosisCode(DiagnosisCode diagnosisCode) {
        this.diagnosisCode = diagnosisCode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getBiopsyDate() {
        return biopsyDate;
    }

    public void setBiopsyDate(Date biopsyDate) {
        this.biopsyDate = biopsyDate;
    }

    public Date getEsrfDate() {
        return esrfDate;
    }

    public void setEsrfDate(Date esrfDate) {
        this.esrfDate = esrfDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isPrepubertal() {
        return prepubertal;
    }

    public void setPrepubertal(boolean prepubertal) {
        this.prepubertal = prepubertal;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ClinicalPresentation getClinicalPresentationA() {
        return clinicalPresentationA;
    }

    public void setClinicalPresentationA(ClinicalPresentation clinicalPresentationA) {
        this.clinicalPresentationA = clinicalPresentationA;
    }

    public ClinicalPresentation getClinicalPresentationB() {
        return clinicalPresentationB;
    }

    public void setClinicalPresentationB(ClinicalPresentation clinicalPresentationB) {
        this.clinicalPresentationB = clinicalPresentationB;
    }

    public Date getOnsetSymptomsDate() {
        return onsetSymptomsDate;
    }

    public void setOnsetSymptomsDate(Date onsetSymptomsDate) {
        this.onsetSymptomsDate = onsetSymptomsDate;
    }
}
