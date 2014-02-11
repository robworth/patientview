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

package org.patientview.radar.model.hnf1b;

import org.patientview.radar.model.BaseModel;
import org.patientview.radar.model.enums.YesNo;

import java.util.Date;

public class HNF1BMisc extends BaseModel {

    private Long radarNo;
    private YesNo renalCysts;
    private YesNo singleKidney;
    private YesNo otherRenalMalformations;
    private String otherRenalMalformationsDetails;
    private YesNo diabetes;
    private Date dateAtDiabetesDiagnosis;
    private YesNo gout;
    private Date dateAtGoutDiagnosis;
    private YesNo genitalMalformation;
    private String genitalMalformationDetails;

    public Long getRadarNo() {
        return radarNo;
    }

    public void setRadarNo(Long radarNo) {
        this.radarNo = radarNo;
    }

    public YesNo getRenalCysts() {
        return renalCysts;
    }

    public void setRenalCysts(YesNo renalCysts) {
        this.renalCysts = renalCysts;
    }

    public YesNo getSingleKidney() {
        return singleKidney;
    }

    public void setSingleKidney(YesNo singleKidney) {
        this.singleKidney = singleKidney;
    }

    public YesNo getOtherRenalMalformations() {
        return otherRenalMalformations;
    }

    public void setOtherRenalMalformations(YesNo otherRenalMalformations) {
        this.otherRenalMalformations = otherRenalMalformations;
    }

    public YesNo getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(YesNo diabetes) {
        this.diabetes = diabetes;
    }

    public YesNo getGout() {
        return gout;
    }

    public void setGout(YesNo gout) {
        this.gout = gout;
    }

    public YesNo getGenitalMalformation() {
        return genitalMalformation;
    }

    public void setGenitalMalformation(YesNo genitalMalformation) {
        this.genitalMalformation = genitalMalformation;
    }

    public String getOtherRenalMalformationsDetails() {
        return otherRenalMalformationsDetails;
    }

    public void setOtherRenalMalformationsDetails(String otherRenalMalformationsDetails) {
        this.otherRenalMalformationsDetails = otherRenalMalformationsDetails;
    }

    public Date getDateAtDiabetesDiagnosis() {
        return dateAtDiabetesDiagnosis;
    }

    public void setDateAtDiabetesDiagnosis(Date dateAtDiabetesDiagnosis) {
        this.dateAtDiabetesDiagnosis = dateAtDiabetesDiagnosis;
    }

    public Date getDateAtGoutDiagnosis() {
        return dateAtGoutDiagnosis;
    }

    public void setDateAtGoutDiagnosis(Date dateAtGoutDiagnosis) {
        this.dateAtGoutDiagnosis = dateAtGoutDiagnosis;
    }

    public String getGenitalMalformationDetails() {
        return genitalMalformationDetails;
    }

    public void setGenitalMalformationDetails(String genitalMalformationDetails) {
        this.genitalMalformationDetails = genitalMalformationDetails;
    }


}
