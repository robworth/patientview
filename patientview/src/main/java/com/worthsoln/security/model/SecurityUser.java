package com.worthsoln.security.model;

import com.worthsoln.patientview.model.Tenancy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 *
 */
public class SecurityUser extends User {

    // The tenancy this user is associated with
    private Tenancy tenancy;

    public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public SecurityUser(String username, String password, boolean enabled, boolean accountNonExpired,
                        boolean credentialsNonExpired, boolean accountNonLocked,
                        Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public Tenancy getTenancy() {
        return tenancy;
    }

    public void setTenancy(Tenancy tenancy) {
        this.tenancy = tenancy;
    }
}
