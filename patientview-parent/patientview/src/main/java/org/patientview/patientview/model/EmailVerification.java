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

import org.patientview.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Calendar;

@Entity
public class EmailVerification extends BaseModel {

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String verificationcode;

    @Column(nullable = false)
    private Calendar expirydatestamp;

    public EmailVerification() {
    }

    public EmailVerification(String username, String email, String verificationcode, Calendar expirydatestamp) {
        this.username = username;
        this.email = email;
        this.verificationcode = verificationcode;
        this.expirydatestamp = expirydatestamp;
    }

    public String getVerificationcode() {
        return verificationcode;
    }

    public void setVerificationcode(String verificationcode) {
        this.verificationcode = verificationcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Calendar getExpirydatestamp() {
        return expirydatestamp;
    }

    public void setExpirydatestamp(Calendar expirydatestamp) {
        this.expirydatestamp = expirydatestamp;
    }
}
