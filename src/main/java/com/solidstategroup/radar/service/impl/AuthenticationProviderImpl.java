package com.solidstategroup.radar.service.impl;

import com.solidstategroup.radar.dao.UserDao;
import com.solidstategroup.radar.model.user.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class AuthenticationProviderImpl implements AuthenticationProvider {

    private UserDao userDao;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Get the user by their email
        User user = userDao.getProfessionalUser(authentication.getName());
        if (user != null) {
            // Get the password hash
            try {
                /*String passwordHash = user.getPasswordHash((String) authentication.getCredentials());
                if (user.getPassword().equals(passwordHash)) {
                    // Authenticated
                    authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(),
                            Arrays.asList(new GrantedAuthorityImpl(User.ROLE_USER)));
                    return authentication;
                }*/
            } catch (Exception e) {
                throw new AuthenticationServiceException(e.getMessage());
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
