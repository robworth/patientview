package com.worthsoln.repository;

import com.worthsoln.patientview.model.Diagnosis;

import java.util.List;

/**
 *
 */
public interface DiagnosisDao {

    void save(Diagnosis diagnosis);

    List<Diagnosis> getOtherDiagnoses(String nhsno, String unitcode);
}
