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
import org.patientview.patientview.utils.TimestampUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Calendar;

@Entity
@Table(name = "dia_eyecheckup")
public class EyeCheckup extends BaseModel {

    @Column(nullable = false)
    private String nhsno;

    @Column(nullable = true)
    private Calendar lastRetinalDate;

    @Column(nullable = true)
    private String lastRetinalPlace;

    @Column(nullable = true)
    private String rightRGrade;

    @Column(nullable = true)
    private String rightMGrade;

    @Column(nullable = true)
    private String rightVA;

    @Column(nullable = true)
    private String leftRGrade;

    @Column(nullable = true)
    private String leftMGrade;

    @Column(nullable = true)
    private String leftVA;

    @Column(nullable = false)
    private String unitcode;

    public EyeCheckup() {
    }

    public Calendar getLastRetinalDate() {
        return lastRetinalDate;
    }

    public void setLastRetinalDate(Calendar lastRetinalDate) {
        this.lastRetinalDate = lastRetinalDate;
    }

    public void setLastRetinalDate(String lastRetinalDate) {
        this.lastRetinalDate = TimestampUtils.createTimestamp(lastRetinalDate);
    }

    public String getLastRetinalPlace() {
        return lastRetinalPlace;
    }

    public void setLastRetinalPlace(String lastRetinalPlace) {
        this.lastRetinalPlace = lastRetinalPlace;
    }

    public String getRightRGrade() {
        return rightRGrade;
    }

    public void setRightRGrade(String rightRGrade) {
        this.rightRGrade = rightRGrade;
    }

    public String getRightMGrade() {
        return rightMGrade;
    }

    public void setRightMGrade(String rightMGrade) {
        this.rightMGrade = rightMGrade;
    }

    public String getRightVA() {
        return rightVA;
    }

    public void setRightVA(String rightVA) {
        this.rightVA = rightVA;
    }

    public String getLeftRGrade() {
        return leftRGrade;
    }

    public void setLeftRGrade(String leftRGrade) {
        this.leftRGrade = leftRGrade;
    }

    public String getLeftMGrade() {
        return leftMGrade;
    }

    public void setLeftMGrade(String leftMGrade) {
        this.leftMGrade = leftMGrade;
    }

    public String getLeftVA() {
        return leftVA;
    }

    public void setLeftVA(String leftVA) {
        this.leftVA = leftVA;
    }

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public String getLastRetinalDateFormatted() {
        if (lastRetinalDate != null) {
            return TimestampUtils.DAY_FORMAT_SLASH.format(lastRetinalDate.getTime());
        }
        return "";
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }
}
