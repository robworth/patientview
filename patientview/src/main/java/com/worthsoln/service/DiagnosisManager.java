package com.worthsoln.service;

import com.worthsoln.patientview.model.Diagnosis;

import java.util.List;

/**
 *
 */
public interface DiagnosisManager {

    void save(Diagnosis diagnosis);

    List<Diagnosis> getOtherDiagnoses(String nhsno, String unitcode);
}
