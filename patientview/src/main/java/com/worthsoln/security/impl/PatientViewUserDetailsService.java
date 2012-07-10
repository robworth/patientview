package com.worthsoln.security.impl;

import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.patientview.logon.PatientLogon;
import com.worthsoln.patientview.logon.PatientLogonDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 *      Grab the user from the database
 */
public class PatientViewUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientViewUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        LOGGER.debug("Attempting to load user for name {}", s);

        User securityUser = null;

        DatabaseDAO dao = new DatabaseDAO("patientview");
        PatientLogon patientLogon = (PatientLogon) dao.retrieveItem(new PatientLogonDao(new PatientLogon(s)));

        if (patientLogon != null && patientLogon.getRole() != null) {

            // convert to spring security convention
            String role = "ROLE_" + patientLogon.getRole().toUpperCase();

            List<GrantedAuthority> authorities
                    = AuthorityUtils.createAuthorityList(role);

            // we inform spring security if the user has been locked out due to failed login attempts here
            securityUser = new User(
                    patientLogon.getUsername(),
                    patientLogon.getPassword(),
                    true, // enabled
                    true, // account not expired
                    true, // credentials not expired
                    !patientLogon.isAccountlocked(), // true - account not locked
                    authorities);

        } else {
            LOGGER.debug("Unable to find user to attempt login from database: {}", s);
        }

        return securityUser;
    }

//
}
