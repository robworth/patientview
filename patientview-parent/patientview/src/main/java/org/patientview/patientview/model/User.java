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

package org.patientview.patientview.model;

import org.patientview.utils.LegacySpringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Date;

@Entity
public class User extends BaseModel {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private boolean emailverified;

    @Column(nullable = true)
    private boolean firstlogon;

    @Column(nullable = false)
    private boolean dummypatient;

    @Column(nullable = true)
    private Date lastlogon;

    @Column(nullable = true)
    private int failedlogons;

    @Column(nullable = true)
    private boolean accountlocked;

    @Column(nullable = true)
    private boolean isrecipient;

    @Column(nullable = true)
    private boolean isclinician;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    @Transient
    // get the user's role in the currently selected specialty
    public String getRole() {
        return LegacySpringUtils.getUserManager().getCurrentSpecialtyRole(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailverified() {
        return emailverified;
    }

    public void setEmailverified(boolean emailverified) {
        this.emailverified = emailverified;
    }

    public boolean isFirstlogon() {
        return firstlogon;
    }

    public void setFirstlogon(boolean firstlogon) {
        this.firstlogon = firstlogon;
    }

    public boolean isDummypatient() {
        return dummypatient;
    }

    public void setDummypatient(boolean dummypatient) {
        this.dummypatient = dummypatient;
    }

    public Date getLastlogon() {
        return lastlogon;
    }

    public void setLastlogon(Date lastlogon) {
        this.lastlogon = lastlogon;
    }

    public int getFailedlogons() {
        return failedlogons;
    }

    public void setFailedlogons(int failedlogons) {
        this.failedlogons = failedlogons;
    }

    public boolean isAccountlocked() {
        return accountlocked;
    }

    public void setAccountlocked(boolean accountlocked) {
        this.accountlocked = accountlocked;
    }

    public boolean isIsrecipient() {
        return isrecipient;
    }

    public void setIsrecipient(boolean isrecipient) {
        this.isrecipient = isrecipient;
    }

    public boolean isIsclinician() {
        return isclinician;
    }

    public void setIsclinician(boolean isclinician) {
        this.isclinician = isclinician;
    }
}
