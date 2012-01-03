package com.solidstategroup.radar.model.user;

import com.solidstategroup.radar.model.BaseModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class User extends BaseModel implements UserDetails {

    // Roles, avoid an enum to make it a bit easier with Spring security
    public static final String ROLE_PROFESSIONAL = "ROLE_PROFESSIONAL";
    public static final String ROLE_PATIENT = "ROLE_PATIENT";

    private String username, password;
    private Date dateRegistered;

    public Collection<GrantedAuthority> getAuthorities() {
        return null;  // Todo: Implement
    }

    public boolean isAccountNonExpired() {
        return false;  // Todo: Implement
    }

    public boolean isAccountNonLocked() {
        return false;  // Todo: Implement
    }

    public boolean isCredentialsNonExpired() {
        return false;  // Todo: Implement
    }

    public boolean isEnabled() {
        return false;  // Todo: Implement
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }
}
