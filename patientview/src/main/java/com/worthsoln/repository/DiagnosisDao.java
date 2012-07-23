package com.worthsoln.repository;

import com.worthsoln.patientview.model.Diagnosis;

import java.util.List;

/**
 *
 */
public interface DiagnosisDao {

    Diagnosis get(Long id);

    List<Diagnosis> getOtherDiagnoses(String nhsno, String unitcode);

    void save(Diagnosis diagnosis);
}
