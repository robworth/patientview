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

import org.patientview.patientview.model.enums.DiagnosticType;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *      Generic holder for exams and procedure dates
 */
@Entity(name = "diagnostic")
public class Diagnostic extends BaseModel {

    @Column(nullable = false)
    private String nhsno;

    @Column(nullable = false)
    private String unitcode;

    @Column(nullable = false)
    private Calendar datestamp;

    @Transient
    private DiagnosticType diagnosticType;

    @Column(nullable = false)
    private String description;

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public Calendar getDatestamp() {
        return datestamp;
    }

    public void setDatestamp(Calendar datestamp) {
        this.datestamp = datestamp;
    }

    public DiagnosticType getDiagnosticType() {
        return diagnosticType;
    }

    public void setDiagnosticType(DiagnosticType diagnosticType) {
        this.diagnosticType = diagnosticType;
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "diagnostic_type_id", nullable = false)
    public int getDiagnosticTypeId() {
        if (diagnosticType != null) {
            return diagnosticType.getId();
        }

        return -1;
    }

    public void setDiagnosticTypeId(int diagnosticTypeId) {
        this.diagnosticType = DiagnosticType.getDiagnosticType(diagnosticTypeId);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Transient
    public String getFormattedDatestamp() {
        if (datestamp != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
            if ((datestamp.get(Calendar.HOUR_OF_DAY) == 0) && (datestamp.get(Calendar.MINUTE) == 0)) {
                return dateFormat.format(datestamp.getTime());
            } else {
                return dateTimeFormat.format(datestamp.getTime());
            }
        }

        return "";
    }
}
