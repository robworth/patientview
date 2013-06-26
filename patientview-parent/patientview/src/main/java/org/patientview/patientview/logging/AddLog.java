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

package org.patientview.patientview.logging;

import org.patientview.patientview.model.LogEntry;
import org.patientview.utils.LegacySpringUtils;

public final class AddLog {

    private AddLog() {
    }

    public static final String PASSWORD_RESET_FORGOTTEN = "password reset forgotten";
    public static final String PASSWORD_RESET = "password reset";
    public static final String PASSWORD_CHANGE = "password change";
    public static final String PASSWORD_LOCKED = "password locked";
    public static final String PASSWORD_UNLOCKED = "password unlocked";
    public static final String ACTOR_SYSTEM = "system";
    public static final String PATIENT_DATA = "patient data";
    public static final String PATIENT_DATA_FOLLOWUP = "patient data load";
    public static final String PATIENT_DATA_FAIL = "patient data fail";
    public static final String PATIENT_DATA_REMOVE = "patient data remove";
    public static final String PATIENT_DATA_CORRUPT = "patient data corrupt";
    public static final String LOGGED_ON = "logon";
    public static final String PATIENT_ADD = "patient add";
    public static final String PATIENT_DELETE = "patient delete";
    public static final String PATIENT_VIEW = "patient view";
    public static final String ADMIN_ADD = "admin add";
    public static final String UKT_DATA_REPLACE = "ukt data";
    public static final String PATIENT_COUNT = "patient count";
    public static final String EMAIL_VERIFY = "email verified";

    public static void addLog(String actor, String action, String user, String nhsno, String unitcode,
                              String extrainfo) {
        actor = (actor == null) ? "" : actor;
        action = (action == null) ? "" : action;
        user = (user == null) ? "" : user;
        nhsno = (nhsno == null) ? "" : nhsno;
        unitcode = (unitcode == null) ? "" : unitcode;
        extrainfo = (extrainfo == null) ? "" : extrainfo;

        LogEntry entry = new LogEntry(nhsno, user, action, actor, unitcode, extrainfo);
        try {
            LegacySpringUtils.getLogEntryManager().save(entry);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
