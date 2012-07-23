package com.worthsoln.repository;

import com.worthsoln.patientview.model.Aboutme;

/**
 *
 */
public interface AboutmeDao {

    Aboutme get(String nhsno);

    void save(Aboutme aboutme);
}
