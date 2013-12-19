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

public class IssueFilter extends BaseFilter {
    public enum Field {
        ID("bID"),
        TYPE("bType"),
        PAGE("bPage"),
        DATE_LOGGED("bDateLogged"),
        DATE_RESOLVED("bDateResolved"),
        DESC("bDesc"),
        COMMENTS("bComment"),
        PRIORITY("bPriority"),
        STATUS("bStatus"),
        UPDATED("bUpdated");

        private String databaseFieldName;

        private Field(String databaseFieldName) {
            this.databaseFieldName = databaseFieldName;
        }

        public static Field getField(String fieldName) {
            for (Field field : values()) {
                if (field.getDatabaseFieldName().equalsIgnoreCase(fieldName)) {
                    return field;
                }
            }

            return Field.ID;
        }

        public String getDatabaseFieldName() {
            return databaseFieldName;
        }
    }

    public IssueFilter() {
        super();
        sortField = Field.ID.getDatabaseFieldName();
    }
}
