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

package org.patientview.ibd.model;

import org.patientview.ibd.model.enums.Diagnosis;
import org.patientview.ibd.model.enums.Severity;
import org.patientview.patientview.model.BaseModel;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ibd_myibd_severity_level")
public class MyIbdSeverityLevel extends BaseModel {

    @Column(nullable = false)
    private String nhsno;

    @Transient
    private Severity severity;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String treatment;

    @Column(nullable = true)
    private Integer level;

    public MyIbdSeverityLevel() {
    }

    public MyIbdSeverityLevel(String nhsno, Severity severity, Integer level, String treatment) {
        this.nhsno = nhsno;
        this.severity = severity;
        this.level = level;
        this.treatment = treatment;
    }

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "severity_id", nullable = false)
    public long getSeverityId() {
        if (severity != null) {
            return severity.getId();
        }

        return -1;
    }

    public void setSeverityId(Long id) {
        this.severity = Severity.getSeverity(id);
    }

    public Integer getLevel(Diagnosis diagnosis) {
        if (level == null || level <= 0) {
            return severity.getDefaultLevel(diagnosis);
        }

        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
}
