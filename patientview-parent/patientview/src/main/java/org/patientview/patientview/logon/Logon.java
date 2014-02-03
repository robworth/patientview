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

package org.patientview.patientview.logon;

import org.patientview.ibd.Ibd;
import org.patientview.model.Patient;
import org.patientview.service.UserManager;
import org.patientview.utils.LegacySpringUtils;

import java.util.Date;

public abstract class Logon {

    private String username;
    private String password;

    // This role attribute has gone a bit wonky now - to the getter and setter.
    // The role is now stored against the Specialty
    private String role;
    private String firstName;
    private String lastName;
    private String email;
    private boolean emailverified;
    private String nhsno;
    private String unitcode;
    private boolean firstlogon;
    private boolean dummypatient;
    private Date lastlogon;
    private int failedlogons;
    private boolean accountlocked;
    private boolean isrecipient;
    private boolean isclinician;
    private Date lastverificationdate;
    private int rrtModality;
    private Date lastdatadate;

    public Logon() {
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

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {

        if (role == null) {
            // attempt to work this out cos it's no longer in this db table
            UserManager userManager = LegacySpringUtils.getUserManager();
            role = userManager.getCurrentSpecialtyRole(userManager.get(this.getUsername()));
        }

        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = (unitcode != null) ? unitcode.toUpperCase() : unitcode;
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

    public String getDisplayRole() {
        return LogonUtils.displayRole(getRole());
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

    public String getLastlogonFormatted() {
        if (lastlogon != null) {
            return Ibd.DATE_FORMAT.format(lastlogon);
        }

        return "";
    }

    public String getLastverificationdateFormatted() {
        if (lastverificationdate != null) {
            return Ibd.DATE_FORMAT.format(lastverificationdate);
        }

        return "";
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

    public Date getLastverificationdate() {
        return lastverificationdate;
    }

    public void setLastverificationdate(Date lastverificationdate) {
        this.lastverificationdate = lastverificationdate;
    }

    public int getRrtModality() {
        return rrtModality;
    }

    public void setRrtModality(int rrtModality) {
        this.rrtModality = rrtModality;
    }

    public String getModality() {
        Patient.RRTModality[] modalities = Patient.RRTModality.values();
        for (Patient.RRTModality  modality : modalities) {
            if (modality.getId() == rrtModality) {
                 return modality.name();
            }
        }
        return "";
    }

    public Date getLastdatadate() {
        return lastdatadate;
    }

    public void setLastdatadate(Date lastdatadate) {
        this.lastdatadate = lastdatadate;
    }

    public String getLastdatadateFormatted() {
        if (lastdatadate != null) {
            return Ibd.DATE_FORMAT.format(lastdatadate);
        }

        return "";
    }
}
