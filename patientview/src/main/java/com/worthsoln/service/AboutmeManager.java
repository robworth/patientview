package com.worthsoln.service;

import com.worthsoln.patientview.model.Aboutme;
import com.worthsoln.patientview.model.User;

/**
 *
 */
public interface AboutmeManager {

    Aboutme getForPatient(User user);

    void save(Aboutme aboutme);
}
