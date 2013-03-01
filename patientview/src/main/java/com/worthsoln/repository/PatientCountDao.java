package com.worthsoln.repository;

import com.worthsoln.patientview.model.PatientCount;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface PatientCountDao {

    void save(PatientCount patientCount);

    List<PatientCount> get(String unitCode, String role);
}
