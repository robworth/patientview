package com.worthsoln.service;

import com.worthsoln.patientview.model.Patient;

import java.util.List;

/**
 *
 */
public interface PatientManager {

    List<Patient> get(String unitCode);
}
