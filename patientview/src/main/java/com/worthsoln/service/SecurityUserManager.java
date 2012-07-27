package com.worthsoln.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *  Access the spring security user details
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface SecurityUserManager {

    String getLoggedInUsername();

    boolean isLoggedIn();

    boolean isRolePresent(String... roles);
}
