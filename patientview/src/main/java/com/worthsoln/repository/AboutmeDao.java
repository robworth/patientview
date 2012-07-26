package com.worthsoln.repository;

import com.worthsoln.patientview.model.Aboutme;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface AboutmeDao {

    Aboutme get(String nhsno);

    void save(Aboutme aboutme);
}
