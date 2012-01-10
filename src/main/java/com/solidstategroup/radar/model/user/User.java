package com.solidstategroup.radar.model.user;

import com.solidstategroup.radar.model.BaseModel;
import com.solidstategroup.radar.util.TripleDes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

public abstract class User extends BaseModel implements UserDetails {

    // Roles, avoid an enum to make it a bit easier with Spring security
    public static final String ROLE_PROFESSIONAL = "ROLE_PROFESSIONAL";
    public static final String ROLE_PATIENT = "ROLE_PATIENT";

    private String username;
    private Date dateRegistered = new Date(); // Construct this - DAO will overwrite with correct value
    private byte[] passwordHash;

    public abstract String getSecurityRole();

    public String getPassword() {
        return null;  // Todo: Implement
    }

    public static byte[] getPasswordHash(String password) throws Exception {
        return TripleDes.encrypt(password);
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return Arrays.<GrantedAuthority>asList(new GrantedAuthorityImpl(getSecurityRole()));
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
        return true;  // Todo: Implement
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public byte[] getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(byte[] passwordHash) {
        // Copy array
        this.passwordHash = Arrays.copyOf(passwordHash, passwordHash.length);
    }
}
