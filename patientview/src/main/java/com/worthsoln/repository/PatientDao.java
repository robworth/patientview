package com.worthsoln.repository;

import com.worthsoln.patientview.model.Patient;

import java.util.List;

/**
 *
 */
public interface PatientDao {

    void save(Patient patient);

    List<Patient> get(String centreCode);
}
