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

package org.patientview.radar.model.alport;

import org.patientview.radar.model.BaseModel;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Deafness extends BaseModel {

    public enum EvidenceOfDeafness {
        NO(1, "No"),
        YES_MINOR(2, "Yes, Minor"),
        YES_HEARING_AID_NEEDED(3, "Yes, Hearing aid needed");

        private int id;
        private String value;

        private EvidenceOfDeafness(int id, String value) {
            this.id = id;
            this.value = value;
        }

        public static EvidenceOfDeafness getEvidenceOfDeafness(int id) {
            for (EvidenceOfDeafness evidenceOfDeafness : EvidenceOfDeafness.values()) {
                if (evidenceOfDeafness.getId() == id) {
                    return evidenceOfDeafness;
                }
            }

            return EvidenceOfDeafness.NO;
        }

        public List<EvidenceOfDeafness> getAsList() {
            return Arrays.asList(EvidenceOfDeafness.values());
        }

        public int getId() {
            return id;
        }

        public String getValue() {
            return value;
        }
    }

    private Long radarNo;
    private EvidenceOfDeafness evidenceOfDeafness;
    private Date dateProblemFirstNoticed;
    private Date dateStartedUsingHearingAid;

    public Long getRadarNo() {
        return radarNo;
    }

    public void setRadarNo(Long radarNo) {
        this.radarNo = radarNo;
    }

    public EvidenceOfDeafness getEvidenceOfDeafness() {
        return evidenceOfDeafness;
    }

    public void setEvidenceOfDeafness(EvidenceOfDeafness evidenceOfDeafness) {
        this.evidenceOfDeafness = evidenceOfDeafness;
    }

    public Date getDateProblemFirstNoticed() {
        return dateProblemFirstNoticed;
    }

    public void setDateProblemFirstNoticed(Date dateProblemFirstNoticed) {
        this.dateProblemFirstNoticed = dateProblemFirstNoticed;
    }

    public Date getDateStartedUsingHearingAid() {
        return dateStartedUsingHearingAid;
    }

    public void setDateStartedUsingHearingAid(Date dateStartedUsingHearingAid) {
        this.dateStartedUsingHearingAid = dateStartedUsingHearingAid;
    }
}
