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

package org.patientview.service;

import org.patientview.patientview.model.LogEntry;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface LogEntryManager {

    //TODO move to enum
    String PASSWORD_RESET_FORGOTTEN = "password reset forgotten";
    String PASSWORD_RESET = "password reset";
    String PASSWORD_CHANGE = "password change";
    String PASSWORD_LOCKED = "password locked";
    String PASSWORD_UNLOCKED = "password unlocked";
    String EMAIL_CHANGED = "email changed";
    String ACTOR_SYSTEM = "system";
    String PATIENT_DATA = "patient data";
    String PATIENT_DATA_FOLLOWUP = "patient data load";
    String PATIENT_DATA_FAIL = "patient data fail";
    String PATIENT_DATA_REMOVE = "patient data remove";
    String PATIENT_DATA_CORRUPT = "patient data corrupt";
    String LOGGED_ON = "logon";
    String PATIENT_ADD = "patient add";
    String PATIENT_DELETE = "patient delete";
    String PATIENT_VIEW = "patient view";
    String ADMIN_ADD = "admin add";
    String UKT_DATA_REPLACE = "ukt data";
    String PATIENT_COUNT = "patient count";
    String EMAIL_VERIFY = "email verified";

    void save(LogEntry logEntry);

    LogEntry getLatestLogEntry(String nhsno, String action);

    List<LogEntry> get(String username, Calendar startdate, Calendar enddate);

    // action is optional
    List<LogEntry> getWithNhsNo(String nhsno, Calendar startdate, Calendar enddate, String action);

    List<LogEntry> getWithNhsNo(String nhsno, String user, String actor, String action, String unitcode,
                                Calendar startdate, Calendar enddate);

    List<LogEntry> getWithUnitCode(String unitcode, Calendar startdate, Calendar enddate);

    void addLog(String actor, String action, String user, String nhsno, String unitcode,
                String extrainfo);
}
