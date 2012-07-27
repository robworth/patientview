package com.worthsoln.repository;

import com.worthsoln.patientview.model.Diagnosis;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface DiagnosisDao {

    Diagnosis get(Long id);

    List<Diagnosis> getOtherDiagnoses(String nhsno, String unitcode);

    void save(Diagnosis diagnosis);
}
