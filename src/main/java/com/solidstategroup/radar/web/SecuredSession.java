package com.solidstategroup.radar.web;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecuredSession extends AuthenticatedWebSession {

    public SecuredSession(Request request) {
        super(request);
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
}
