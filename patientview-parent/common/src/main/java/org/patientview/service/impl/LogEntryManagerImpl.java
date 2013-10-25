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

package org.patientview.service.impl;

import org.patientview.model.patientview.LogEntry;
import org.patientview.repository.LogEntryDao;
import org.patientview.service.LogEntryManager;
import org.patientview.service.SecurityUserManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Calendar;
import java.util.List;

/**
 *
 */
@Service(value = "logEntryManager")
public class LogEntryManagerImpl implements LogEntryManager {



    @Inject
    private LogEntryDao logEntryDao;

    @Inject
    private SecurityUserManager securityUserManager;

    private static final int MAX_TEXT_FIELD_SIZE = 65000;

    public LogEntry get(Long id) {
        return logEntryDao.get(id);
    }

    @Override
    public void save(LogEntry logEntry) {

        if (logEntry.getSpecialty() == null) {
            logEntry.setSpecialty(securityUserManager.getLoggedInSpecialty());
        }

        // manually truncate the data to fit into the TEXT column, don't want to run an alter table command on this
        // massive table.  If this is not acceptable set the column to be a LONGTEXT and possible increase
        // the max_allowed_packet size
        if (logEntry.getExtrainfo() != null && logEntry.getExtrainfo().length() > MAX_TEXT_FIELD_SIZE) {
            logEntry.setExtrainfo(logEntry.getExtrainfo().substring(0, MAX_TEXT_FIELD_SIZE));
        }

        logEntryDao.save(logEntry);
    }

    @Override
    public LogEntry getLatestLogEntry(String nhsno, String action) {
        return logEntryDao.getLatestLogEntry(nhsno, action);
    }

    @Override
    public List<LogEntry> get(String username, Calendar startDate, Calendar endDate) {
        LogEntry logEntry = new LogEntry();
        logEntry.setUser(username);
        logEntry.setSpecialty(securityUserManager.getLoggedInSpecialty());
        return logEntryDao.get(logEntry, startDate, endDate);
    }

    @Override
    public List<LogEntry> getWithNhsNo(String nhsno, Calendar startDate, Calendar endDate, String action) {
        LogEntry logEntry = new LogEntry();
        logEntry.setNhsno(nhsno);
        logEntry.setAction(action);
        logEntry.setSpecialty(securityUserManager.getLoggedInSpecialty());
        return logEntryDao.get(logEntry, startDate, endDate);
    }

    @Override
    public List<LogEntry> getWithNhsNo(String nhsno, String user, String actor, String action, String unitcode,
                                       Calendar startDate, Calendar endDate) {
        LogEntry logEntry = new LogEntry();
        logEntry.setNhsno(nhsno);
        logEntry.setUser(user);
        logEntry.setActor(actor);
        logEntry.setUnitcode(unitcode);
        logEntry.setSpecialty(securityUserManager.getLoggedInSpecialty());
        logEntry.setAction(action);
        return logEntryDao.get(logEntry, startDate, endDate);

    }

    @Override
    public List<LogEntry> getWithUnitCode(String unitcode, Calendar startdate, Calendar enddate) {
        LogEntry logEntry = new LogEntry();
        logEntry.setUnitcode(unitcode);
        logEntry.setSpecialty(securityUserManager.getLoggedInSpecialty());
        return logEntryDao.get(logEntry, startdate, enddate);
    }


    @Override
    public List<LogEntry> get(LogEntry logEntry, Calendar startDate, Calendar endDate) {
        return logEntryDao.get(logEntry, startDate, endDate);
    }

    public void addLog(String actor, String action, String user, String nhsno, String unitcode,
                       String extrainfo) {
        actor = (actor == null) ? "" : actor;
        action = (action == null) ? "" : action;
        user = (user == null) ? "" : user;
        nhsno = (nhsno == null) ? "" : nhsno;
        unitcode = (unitcode == null) ? "" : unitcode;
        extrainfo = (extrainfo == null) ? "" : extrainfo;

        LogEntry entry = new LogEntry(nhsno, user, action, actor, unitcode, extrainfo);

        this.save(entry);

    }
}
