package com.worthsoln.service;

import com.worthsoln.patientview.model.Diagnosis;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface DiagnosisManager {

    void save(Diagnosis diagnosis);

    List<Diagnosis> getOtherDiagnoses(String nhsno, String unitcode);
}
