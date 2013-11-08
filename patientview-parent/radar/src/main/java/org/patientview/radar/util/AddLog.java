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

package org.patientview.radar.util;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.patientview.radar.model.LogEntry;
import org.patientview.radar.service.LogEntryManager;

public final class AddLog {

    private AddLog() {
    }

    @SpringBean
    private static LogEntryManager logEntryManager;

    public static final String PASSWORD_RESET_FORGOTTEN = "RDR password reset forgotten";
    public static final String LOGGED_ON = "RDR logon";
    public static final String PATIENT_ADD = "RDR patient add";
    public static final String PATIENT_DELETE = "RDR patient delete";
    public static final String PATIENT_LOGON_VIEW = "RDR patient logon view";
    public static final String ADMIN_ADD = "RDR admin add";
    public static final String ADMIN_ISSUE_ADD = "RDR admin issue add";


    public static void addLog(String action, String user, String nhsno, String unitcode,
                              String extrainfo) {
        action = (action == null) ? "" : action;
        user = (user == null) ? "" : user;
        nhsno = (nhsno == null) ? "" : nhsno;
        unitcode = (unitcode == null) ? "" : unitcode;
        extrainfo = (extrainfo == null) ? "" : extrainfo;

        LogEntry entry = new LogEntry(nhsno, user, action, unitcode, extrainfo);

        logEntryManager.save(entry);

    }

    public static void setLogEntryManager(LogEntryManager logEntryManager) {
        AddLog.logEntryManager = logEntryManager;
    }
}
