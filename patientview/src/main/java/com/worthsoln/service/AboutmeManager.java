package com.worthsoln.service;

import com.worthsoln.patientview.model.Aboutme;
import com.worthsoln.patientview.model.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface AboutmeManager {

    Aboutme getForPatient(User user);

    void save(Aboutme aboutme);
}
