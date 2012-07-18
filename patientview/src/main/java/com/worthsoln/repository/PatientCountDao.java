package com.worthsoln.repository;

import com.worthsoln.patientview.model.PatientCount;

import java.util.List;

/**
 *
 */
public interface PatientCountDao {

    List<PatientCount> get(String unitCode, String role);
}
