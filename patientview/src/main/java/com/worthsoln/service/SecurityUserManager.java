package com.worthsoln.service;

/**
 *  Access the spring security user details
 */
public interface SecurityUserManager {

    String getLoggedInUsername();

    boolean isLoggedIn();

    boolean isRolePresent(String... roles);
}
