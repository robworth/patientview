package com.solidstategroup.radar.service.impl;

import com.solidstategroup.radar.dao.UserDao;
import com.solidstategroup.radar.model.user.User;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import java.util.Arrays;
import java.util.List;

public class AuthenticationProviderImpl implements AuthenticationProvider {

    private UserDao userDao;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Get the user by their email, try admin, then professional, then patient
        // Todo: Get Admin user
        User user = userDao.getProfessionalUser(authentication.getName());

        if (user == null) {
            user = userDao.getPatientUser(authentication.getName());
        }

        // If we've found a user...
        if (user != null) {
            // Get the password hash
            try {
                // Didn't want to store plain text password in memory, even tho probably safe
                // Instead password hash is set on user from DAO, then we compare the two hashes
                byte[] passwordHash = User.getPasswordHash((String) authentication.getCredentials());
                if (Arrays.equals(user.getPasswordHash(), passwordHash)) {
                    // Authenticated
                    List<GrantedAuthorityImpl> authorities =
                            Arrays.asList(new GrantedAuthorityImpl(user.getSecurityRole()));
                    authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), authorities);
                    return authentication;
                }
            } catch (Exception e) {
                LoggerFactory.getLogger(AuthenticationProviderImpl.class)
                        .error("Could not log user {} in", authentication.getName(), e);
                throw new AuthenticationServiceException("Could not log in user", e);
            }
        }

        return null;
    }

    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
