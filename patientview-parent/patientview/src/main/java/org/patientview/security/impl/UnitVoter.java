/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */
package org.patientview.security.impl;

import org.patientview.security.SecurityConfig;
import org.patientview.security.UnitSecured;
import org.patientview.security.model.SecurityUser;
import org.patientview.service.SecurityUserManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import javax.inject.Inject;

/**
 *  Implement finer grained authorisation per unit
 */
@Transactional(propagation = Propagation.REQUIRED)
public class UnitVoter implements AccessDecisionVoter {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnitVoter.class);

    @Inject
    private SecurityUserManager securityUserManager;

    @Override
    public boolean supports(ConfigAttribute configAttribute) {

        // not sure how this should really work - use the voter for everything
        return true;
    }

    @Override
    /**
     *  authentication: encapsulates the principal and their granted authorities
     *
     *  o: the actual secure object invocation,
     *
     *  e.g. for method based security: ReflectiveMethodInvocation:
     *  com.worthsoln.service.get(String unitCode);
     *  target is of class [com.worthsoln.service.impl.UnitManagerImpl]
     *
     *  collection: A list of ConfigAttribute, typically, SecurityConfig, which hold the attributes supplied by the
     *  @Secured("ROLE_USER") annotation
     */
    public int vote(Authentication authentication, Object o, Collection collection) {

        int result = ACCESS_DENIED;    // default deny access

        if (o instanceof MethodInvocation) {
            // securing a method
            MethodInvocation methodInvocation = (MethodInvocation) o;
            result = voteOnMethodInvocation(authentication, methodInvocation);

        } else if (o instanceof FilterInvocation) {
            // without securing a url
            result = ACCESS_GRANTED;
        }

        return result;
    }

    private int voteOnMethodInvocation(Authentication authentication, MethodInvocation methodInvocation) {

        // Check for the @UnitSecured annotation that provides custom method security
        int result = ACCESS_DENIED;
        if (authentication.getPrincipal() instanceof SecurityUser) {
            SecurityUser user = (SecurityUser) authentication.getPrincipal();

            UnitSecured securedAnnotation = methodInvocation.getMethod().getAnnotation(UnitSecured.class);

            if (securedAnnotation != null && securedAnnotation.value() != null) {
                if (securedAnnotation.value().equals(SecurityConfig.UNIT_USER_READ_AUTH)) {
                    result = voteOnUserReadMethodInvocation(methodInvocation, user);
                } else if (securedAnnotation.value().equals(SecurityConfig.UNIT_FEEDBACK_READ_AUTH)) {
                    result = voteOnFeedbackReadMethodInvocation(methodInvocation, user);
                } else if (securedAnnotation.value().equals(SecurityConfig.UNIT_READ_AUTH)) {
                    result = voteOnUnitReadMethodInvocation(methodInvocation, user);
                }

            } else {
                // There is no UnitSecured annotation - access granted
                result = ACCESS_GRANTED;
            }
        } else {
            result = ACCESS_GRANTED;
        }

        if (result == ACCESS_DENIED) {
            // report authorisation fail
            LOGGER.info("Vote failed on method: {}, result: {}", methodInvocation.getMethod(), result);
        } else {
            // show add debug
            LOGGER.debug("Vote on method: {}, result: {}", methodInvocation.getMethod(), result);
        }

        return result;
    }

    /**
     * Authenticate the login user request the user that belongs to valid unit.
     *
     * @param methodInvocation
     * @param securityUser
     * @return 1: valid request, -1: invalid request
     */
    private int voteOnUserReadMethodInvocation(MethodInvocation methodInvocation, SecurityUser securityUser) {

        String username = getUserName(methodInvocation);
        if (securityUserManager.userHasReadAccessToUnitUser(username)) {
            return ACCESS_GRANTED;
        }

        return ACCESS_DENIED;
    }

    /**
     * Authenticate the login user request the feedback that belongs to valid unit.
     *
     * @param methodInvocation
     * @param securityUser
     * @return 1: valid request, -1: invalid request
     */
    private int voteOnFeedbackReadMethodInvocation(MethodInvocation methodInvocation, SecurityUser securityUser) {

        Object username = getUnitCode(methodInvocation);
        if (securityUserManager.userHasReadAccessToUnitFeedback(username.toString())) {
            return ACCESS_GRANTED;
        }

        return ACCESS_DENIED;
    }

    /**
     * Authenticate the login user request unit code that belongs to valid unit.
     *
     * @param methodInvocation
     * @param securityUser
     * @return
     */
    private int voteOnUnitReadMethodInvocation(MethodInvocation methodInvocation, SecurityUser securityUser) {

        Object username = getUnitCode(methodInvocation);
        if (securityUserManager.userHasReadAccessToUnitFeedback(username.toString())) {
            return ACCESS_GRANTED;
        }

        return ACCESS_DENIED;
    }

    /**
     * Get the username associated with the method invocation
     *
     * @param methodInvocation method
     * @return username
     */
    private String getUserName(MethodInvocation methodInvocation) {
        String result = null;

        try {
            Object[] arguments = methodInvocation.getArguments();
            if (arguments.length > 0) {
                Object arg0 = arguments[0];
                if (arg0 instanceof String) {
                    result = String.valueOf(arg0);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error parsing username id from methodInvocation: " + e.getMessage());
        }

        return result;
    }

    /**
     * Get the unit code associated with the method invocation
     *
     * @param methodInvocation method
     * @return unitcode
     */
    private String getUnitCode(MethodInvocation methodInvocation) {
        String result = null;

        try {
            Object[] arguments = methodInvocation.getArguments();
            if (arguments.length > 0) {
                Object arg0 = arguments[0];
                if (arg0 instanceof String) {
                    result = String.valueOf(arg0);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error parsing unitcode from methodInvocation: " + e.getMessage());
        }

        return result;
    }

    @Override
    public boolean supports(Class aClass) {
        // not sure how this should really work - use the voter for everything
        return true;
    }

    public void setSecurityUserManager(SecurityUserManager securityUserManager) {
        this.securityUserManager = securityUserManager;
    }
}
