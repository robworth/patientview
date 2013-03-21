package com.worthsoln.service;

import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.patientview.model.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *  Access the spring security user details
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface SecurityUserManager {

    String getLoggedInUsername();

    String getLoggedInEmailAddress();

    boolean isFirstLogon();

    boolean isEmailVerified();

    Specialty getLoggedInSpecialty();

    boolean isLoggedIn();

    boolean isLoggedInToSpecialty();

    boolean isSpecialtyPresent(String context);

    boolean isRolePresent(String... roles);

    void setLoggedInSpecialty(Long specialtyId) throws Exception;

    boolean hasAccessToSpecialty(User user, Specialty specialty);
}
