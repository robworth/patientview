package com.worthsoln.service;

import com.worthsoln.patientview.model.Patient;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface PatientManager {

    List<Patient> get(String unitCode);
}
