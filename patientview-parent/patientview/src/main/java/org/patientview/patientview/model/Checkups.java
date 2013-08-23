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
import java.util.Date;

@Entity
@Table(name = "dia_checkups")
public class Checkups extends BaseModel {

    @Column(nullable = false)
    private String nhsno;

    @Column(nullable = true)
    private Date lastRetinalDate;

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

    @Column(nullable = true)
    private Date footCheckDate;

    @Column(nullable = true)
    private String footCheckPlace;

    @Column(nullable = true)
    private String rightDpPulse;

    @Column(nullable = true)
    private String rightPtPulse;

    @Column(nullable = true)
    private String rightMonofilament;

    @Column(nullable = true)
    private String rightRiskScore;

    @Column(nullable = true)
    private String leftDpPulse;

    @Column(nullable = true)
    private String leftPtPulse;

    @Column(nullable = true)
    private String leftMonofilament;

    @Column(nullable = true)
    private String leftRiskScore;

    @Column(nullable = false)
    private String unitcode;

    public Checkups() {
    }

    public Date getLastRetinalDate() {
        return lastRetinalDate;
    }

    public void setLastRetinalDate(Date lastRetinalDate) {
        this.lastRetinalDate = lastRetinalDate;
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

    public Date getFootCheckDate() {
        return footCheckDate;
    }

    public void setFootCheckDate(Date footCheckDate) {
        this.footCheckDate = footCheckDate;
    }

    public String getFootCheckPlace() {
        return footCheckPlace;
    }

    public void setFootCheckPlace(String footCheckPlace) {
        this.footCheckPlace = footCheckPlace;
    }

    public String getRightDpPulse() {
        return rightDpPulse;
    }

    public void setRightDpPulse(String rightDpPulse) {
        this.rightDpPulse = rightDpPulse;
    }

    public String getRightPtPulse() {
        return rightPtPulse;
    }

    public void setRightPtPulse(String rightPtPulse) {
        this.rightPtPulse = rightPtPulse;
    }

    public String getRightMonofilament() {
        return rightMonofilament;
    }

    public void setRightMonofilament(String rightMonofilament) {
        this.rightMonofilament = rightMonofilament;
    }

    public String getRightRiskScore() {
        return rightRiskScore;
    }

    public void setRightRiskScore(String rightRiskScore) {
        this.rightRiskScore = rightRiskScore;
    }

    public String getLeftDpPulse() {
        return leftDpPulse;
    }

    public void setLeftDpPulse(String leftDpPulse) {
        this.leftDpPulse = leftDpPulse;
    }

    public String getLeftPtPulse() {
        return leftPtPulse;
    }

    public void setLeftPtPulse(String leftPtPulse) {
        this.leftPtPulse = leftPtPulse;
    }

    public String getLeftMonofilament() {
        return leftMonofilament;
    }

    public void setLeftMonofilament(String leftMonofilament) {
        this.leftMonofilament = leftMonofilament;
    }

    public String getLeftRiskScore() {
        return leftRiskScore;
    }

    public void setLeftRiskScore(String leftRiskScore) {
        this.leftRiskScore = leftRiskScore;
    }

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public String getLastRetinalDateFormatted() {
        if (lastRetinalDate != null) {
            return TimestampUtils.DAY_FORMAT_SLASH.format(lastRetinalDate);
        }
        return "";
    }

    public String getFootCheckDateFormatted() {
        if (footCheckDate != null) {
            return TimestampUtils.DAY_FORMAT_SLASH.format(footCheckDate);
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
