package com.worthsoln.repository;

import com.worthsoln.patientview.model.Patient;
import com.worthsoln.patientview.model.Tenancy;
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

    // Note: generics not used as the result is half user, half patient
    List getUnitPatientsWithTreatmentDao(String unitcode, String nhsno, String name, boolean showgps, Tenancy tenancy);

    // Note: generics not used as the result is half user, half patient
    List getUnitPatientsAllWithTreatmentDao(String unitcode, Tenancy tenancy);
}
