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

import org.patientview.ibd.Ibd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Date;

@Entity(name = "pv_patientjoin_request")
public class JoinRequest extends BaseModel {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Date dateOfBirth;

    @Column(nullable = true)
    private String nhsNo;

    @Column(nullable = false)
    private String unitcode;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Date dateOfRequest;

    @Transient
    private String antiSpamAnswer;

    public JoinRequest() {
    }

    public JoinRequest(String firstName, String lastName, Date dateOfBirth, String nhsNo, String unitcode,
                       String email, Date dateOfRequest) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.nhsNo = nhsNo;
        this.unitcode = unitcode;
        this.email = email;
        this.dateOfRequest = dateOfRequest;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNhsNo() {
        return nhsNo;
    }

    public void setNhsNo(String nhsNo) {
        this.nhsNo = nhsNo;
    }

    public Date getDateOfRequest() {
        return dateOfRequest;
    }

    public void setDateOfRequest(Date dateOfRequest) {
        this.dateOfRequest = dateOfRequest;
    }

    public String getAntiSpamAnswer() {
        return antiSpamAnswer;
    }

    public void setAntiSpamAnswer(String antiSpamAnswer) {
        this.antiSpamAnswer = antiSpamAnswer;
    }

    public String getDateOfBirthFormatted() {
        if (dateOfBirth != null) {
            return Ibd.DATE_FORMAT.format(dateOfBirth);
        }

        return "";
    }

    public String getDateOfRequestFormatted() {
        if (dateOfRequest != null) {
            return Ibd.DATE_FORMAT.format(dateOfRequest);
        }

        return "";
    }
}
