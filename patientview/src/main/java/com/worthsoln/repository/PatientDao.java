package com.worthsoln.repository;

import com.worthsoln.patientview.model.Patient;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface PatientDao {

    void save(Patient patient);

    List<Patient> get(String centreCode);
}
