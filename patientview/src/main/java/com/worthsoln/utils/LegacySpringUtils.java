package com.worthsoln.utils;

import com.worthsoln.service.SecurityUserManager;

import javax.inject.Inject;

/**
 *  Used to connect up the methods in the old model classes that do service layer operations to the newer service
 * managers. We can move stuff out of this when the service/DAO code gets moved out from the model classes.
 * When/if...
 */
public class LegacySpringUtils {

    private static SecurityUserManager securityUserManager;

    public static void setSecurityUserManager(SecurityUserManager securityUserManager) {
        LegacySpringUtils.securityUserManager = securityUserManager;
    }

    public static SecurityUserManager getSecurityUserManager() {
        return securityUserManager;
    }
}
