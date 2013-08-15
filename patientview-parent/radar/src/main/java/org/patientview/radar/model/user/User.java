package org.patientview.radar.model.user;

import org.patientview.radar.model.BaseModel;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

/**
 *  Note: we user the BaseModel to hold the "radarUserId"
 */
public abstract class User extends BaseModel implements UserDetails {

    // Roles, avoid an enum to make it a bit easier with Spring security
    public static final String ROLE_PROFESSIONAL = "ROLE_PROFESSIONAL";
    public static final String ROLE_PATIENT = "ROLE_PATIENT";
    public static final String ROLE_SUPER_USER = "ROLE_SUPER_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    private Long userId;
    private String username, password, email;

    // todo this date should probably be in the PatientUser
    private Date dateRegistered = new Date(); // Construct this - DAO will overwrite with correct value
    private String name;

    public abstract String getSecurityRole();

    public boolean hasValidUserId() {
        return userId != null && userId > 0;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static String getPasswordHash(String password) throws Exception {
        return DigestUtils.sha256Hex(password);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
