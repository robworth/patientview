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

package org.patientview.radar.model.sequenced;

import org.patientview.radar.model.enums.KidneyTransplantedNative;
import org.patientview.radar.model.enums.RemissionAchieved;

import java.util.Date;

public class Relapse extends SequencedModel {

    private Date dateOfRelapse;
    private KidneyTransplantedNative transplantedNative;
    private String viralTrigger, immunisationTrigger, otherTrigger;
    private String drug1, drug2, drug3;

    private RemissionAchieved remissionAchieved;
    private Date dateOfRemission;

    public Date getDateOfRelapse() {
        return dateOfRelapse;
    }

    public void setDateOfRelapse(Date dateOfRelapse) {
        this.dateOfRelapse = dateOfRelapse;
    }

    public KidneyTransplantedNative getTransplantedNative() {
        return transplantedNative;
    }

    public void setTransplantedNative(KidneyTransplantedNative transplantedNative) {
        this.transplantedNative = transplantedNative;
    }

    public String getViralTrigger() {
        return viralTrigger;
    }

    public void setViralTrigger(String viralTrigger) {
        this.viralTrigger = viralTrigger;
    }

    public String getImmunisationTrigger() {
        return immunisationTrigger;
    }

    public void setImmunisationTrigger(String immunisationTrigger) {
        this.immunisationTrigger = immunisationTrigger;
    }

    public String getOtherTrigger() {
        return otherTrigger;
    }

    public void setOtherTrigger(String otherTrigger) {
        this.otherTrigger = otherTrigger;
    }

    public String getDrug1() {
        return drug1;
    }

    public void setDrug1(String drug1) {
        this.drug1 = drug1;
    }

    public String getDrug2() {
        return drug2;
    }

    public void setDrug2(String drug2) {
        this.drug2 = drug2;
    }

    public String getDrug3() {
        return drug3;
    }

    public void setDrug3(String drug3) {
        this.drug3 = drug3;
    }

    public RemissionAchieved getRemissionAchieved() {
        return remissionAchieved;
    }

    public void setRemissionAchieved(RemissionAchieved remissionAchieved) {
        this.remissionAchieved = remissionAchieved;
    }

    public Date getDateOfRemission() {
        return dateOfRemission;
    }

    public void setDateOfRemission(Date dateOfRemission) {
        this.dateOfRemission = dateOfRemission;
    }
}
