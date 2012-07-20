package com.worthsoln.repository;

import com.worthsoln.patientview.model.Patient;

import java.util.List;

/**
 *
 */
public interface PatientDao {

    List<Patient> get(String unitCode);
}
