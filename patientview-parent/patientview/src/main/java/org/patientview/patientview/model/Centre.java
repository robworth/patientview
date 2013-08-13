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

@Entity
public class Centre extends BaseModel {

    @Column(nullable = false)
    private String centreCode;
    @Column
    private String centreName;
    @Column
    private String centreAddress1;
    @Column
    private String centreAddress2;
    @Column
    private String centreAddress3;
    @Column
    private String centreAddress4;
    @Column
    private String centrePostCode;
    @Column
    private String centreTelephone;
    @Column
    private String centreEmail;

    public Centre() {
    }

    public Centre(String centreCode) {
        this.centreCode = centreCode;
    }

    public String getCentreAddress1() {
        return centreAddress1;
    }

    public void setCentreAddress1(String centreAddress1) {
        this.centreAddress1 = centreAddress1;
    }

    public String getCentreAddress2() {
        return centreAddress2;
    }

    public void setCentreAddress2(String centreAddress2) {
        this.centreAddress2 = centreAddress2;
    }

    public String getCentreAddress3() {
        return centreAddress3;
    }

    public void setCentreAddress3(String centreAddress3) {
        this.centreAddress3 = centreAddress3;
    }

    public String getCentreAddress4() {
        return centreAddress4;
    }

    public void setCentreAddress4(String centreAddress4) {
        this.centreAddress4 = centreAddress4;
    }

    public String getCentreCode() {
        return centreCode;
    }

    public void setCentreCode(String centreCode) {
        this.centreCode = (centreCode != null) ? centreCode.toUpperCase() : centreCode;
    }

    public String getCentreEmail() {
        return centreEmail;
    }

    public void setCentreEmail(String centreEmail) {
        this.centreEmail = centreEmail;
    }

    public String getCentreName() {
        return centreName;
    }

    public void setCentreName(String centreName) {
        this.centreName = centreName;
    }

    public String getCentrePostCode() {
        return centrePostCode;
    }

    public void setCentrePostCode(String centrePostCode) {
        this.centrePostCode = centrePostCode;
    }

    public String getCentreTelephone() {
        return centreTelephone;
    }

    public void setCentreTelephone(String centreTelephone) {
        this.centreTelephone = centreTelephone;
    }
}
