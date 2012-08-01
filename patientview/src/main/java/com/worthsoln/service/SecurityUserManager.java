package com.worthsoln.service;

import com.worthsoln.patientview.model.Tenancy;
import com.worthsoln.patientview.model.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *  Access the spring security user details
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface SecurityUserManager {

    String getLoggedInUsername();

    Tenancy getLoggedInTenancy();

    boolean isLoggedIn();

    boolean isLoggedInToTenancy();

    boolean isTenancyPresent(String context);

    boolean isRolePresent(String... roles);

    void setLoggedInTenancy(Long tenancyId) throws Exception;

    boolean hasAccessToTenancy(User user, Tenancy tenancy);
}
