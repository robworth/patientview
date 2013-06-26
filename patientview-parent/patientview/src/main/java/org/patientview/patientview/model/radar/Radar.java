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

package org.patientview.patientview.model.radar;

public final class Radar {

    public static final String PROFESSIONAL_USER_TABLE_NAME = "tbl_users"; // rdr specific user
    public static final String PROFESSIONAL_USER_ID_FIELD_NAME = "uID";
    public static final String PROFESSIONAL_USER_SURNAME_FIELD_NAME = "uSurname";
    public static final String PROFESSIONAL_USER_FORENAME_FIELD_NAME = "uForename";
    public static final String PROFESSIONAL_USER_CENTRE_ID_FIELD_NAME = "uCentre";

    public static final String USER_MAPPING_TABLE_NAME = "rdr_user_mapping";
    public static final String USER_MAPPING_USER_ID_FIELD_NAME = "userId";
    public static final String USER_MAPPING_ROLE_FIELD_NAME = "role";
    public static final String USER_MAPPING_RADAR_USER_ID_FIELD_NAME = "radarUserId";

    private Radar() {
    }
}
