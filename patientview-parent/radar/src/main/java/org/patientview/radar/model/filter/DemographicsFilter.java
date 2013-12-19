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

package org.patientview.radar.model.filter;

public class DemographicsFilter extends BaseFilter {
    public enum UserField {
        RADAR_NO("patient.radarNo"),
        SURNAME("patient.surname"),
        FORENAME("patient.forename"),
        REGISTRATION_DATE("patient.dateReg"),
        NHSNO("patient.nhsno"),
        ADDRESS("patient.address1,patient.address2," +
                "patient.address3,patient.address4,patient.POSTCODE"),
        DIAGNOSIS("tbl_DiagCode.dcAbbr"),
        CONSULTANT_FORNAME("tbl_Consultants.cFNAME"),
        CONSULTANT_SURNAME("tbl_Consultants.cSNAME"),
        CENTRE("unit.shortname");

        private String databaseFieldName;

        private UserField(String databaseFieldName) {
            this.databaseFieldName = databaseFieldName;
        }

        public static UserField getUserField(String fieldName) {
            for (UserField userField : values()) {
                if (userField.getDatabaseFieldName().equalsIgnoreCase(fieldName)) {
                    return userField;
                }
            }

            return UserField.SURNAME;
        }

        public String getDatabaseFieldName() {
            return databaseFieldName;
        }
    }

    public DemographicsFilter() {
        super();
        sortField = UserField.RADAR_NO.getDatabaseFieldName();
    }
}
