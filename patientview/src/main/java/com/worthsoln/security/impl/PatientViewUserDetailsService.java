package com.worthsoln.security.impl;

import com.worthsoln.patientview.model.TenancyUserRole;
import com.worthsoln.security.model.SecurityUser;
import com.worthsoln.service.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 *      Grab the user from the database
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class PatientViewUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientViewUserDetailsService.class);

    @Inject
    private UserManager userManager;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        LOGGER.debug("Attempting to load user for name {}", s);
        SecurityUser securityUser = null;
        com.worthsoln.patientview.model.User user = userManager.get(s);

        if (user != null) {

            List<TenancyUserRole> tenancyUserRoles = userManager.getTenancyUserRoles(user);

            if (tenancyUserRoles != null && tenancyUserRoles.size() > 0) {

                List<String> roles = new ArrayList<String>();

                for (TenancyUserRole tenancyUserRole : tenancyUserRoles) {

                    // convert to spring security convention
                    String role = "ROLE_" + (tenancyUserRole.getTenancy().getContext() + "_"
                            + tenancyUserRole.getRole()).toUpperCase();
                    roles.add(role);
                }

                // add in a place order role so that we can bounce users to the login page when no tenancy is set
                roles.add("ROLE_ANY_USER");

                List<GrantedAuthority> authorities
                        = AuthorityUtils.createAuthorityList(roles.toArray(new String[roles.size()]));

                // we inform spring security if the user has been locked out due to failed login attempts here
                securityUser = new SecurityUser(
                        user.getUsername(),
                        user.getPassword(),
                        true, // enabled
                        true, // account not expired
                        true, // credentials not expired
                        !user.isAccountlocked(), // true - account not locked
                        authorities);

            } else {
                LOGGER.debug("Unable to find roles for user to attempt login from database: {}", s);
            }
        } else {
            LOGGER.debug("Unable to find user to attempt login from database: {}", s);
        }

        return securityUser;
    }
}
