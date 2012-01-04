package com.solidstategroup.radar.web;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SecuredSession extends AuthenticatedWebSession {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecuredSession.class);

    public SecuredSession(Request request) {
        super(request);
    }

    @Override
    public boolean authenticate(String username, String password) {
        try {
            Authentication authentication = getAuthenticationManager()
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return authentication.isAuthenticated();
        } catch (AuthenticationException e) {
            LOGGER.warn("User '{}' failed to login. Reason: {}", username, e.getMessage());
        }
        return false;

    }

    @Override
    public Roles getRoles() {
        Roles roles = new Roles();
        if (isSignedIn()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                for (GrantedAuthority authority : authentication.getAuthorities()) {
                    roles.add(authority.getAuthority());
                }
            }
        }
        return roles;
    }

    private ProviderManager getAuthenticationManager() {
        return (ProviderManager) WebApplicationContextUtils.getWebApplicationContext(
                WebApplication.get().getServletContext()).getBean("authenticationManager");
    }

}
