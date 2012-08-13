package com.worthsoln.test.helpers;

import com.worthsoln.patientview.model.Tenancy;

/**
 *
 */
public interface SecurityHelpers {

    void loginAsUser(String username, Tenancy tenancy);

    void loginAsUser(String username);

    void logout();
}
