package com.worthsoln.test.helpers;

import com.worthsoln.patientview.model.Specialty;

/**
 *
 */
public interface SecurityHelpers {

    void loginAsUser(String username, Specialty specialty);

    void loginAsUser(String username);

    void logout();
}
