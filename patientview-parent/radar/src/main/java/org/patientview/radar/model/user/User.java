/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

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

    private boolean isClinician;

    // todo this date should probably be in the PatientUser
    private Date dateRegistered = new Date(); // Construct this - DAO will overwrite with correct value
    private String firstName;
    private String lastName;

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
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isClinician() {
        return isClinician;
    }

    public void setClinician(boolean clinician) {
        isClinician = clinician;
    }
}
