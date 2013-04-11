package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.LogEntry;
import com.worthsoln.repository.LogEntryDao;
import com.worthsoln.service.LogEntryManager;
import com.worthsoln.service.SecurityUserManager;
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

    @Override
    public void save(LogEntry logEntry) {

        if (logEntry.getSpecialty() == null) {
            logEntry.setSpecialty(securityUserManager.getLoggedInSpecialty());
        }

        // manually truncate the data to fit into the TEXT column, don't want to run an alter table command on this
        // massive table.  If this is not acceptable set the column to be a LONGTEXT and possible increase
        // the max_allowed_packet size
        if (logEntry.getExtrainfo() != null && logEntry.getExtrainfo().length() > 65000) {
            logEntry.setExtrainfo(logEntry.getExtrainfo().substring(0, 65000));
        }

        logEntryDao.save(logEntry);
    }

    @Override
    public LogEntry getLatestLogEntry(String nhsno, String action) {
        return logEntryDao.getLatestLogEntry(nhsno, action, securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List<LogEntry> get(String username, Calendar startdate, Calendar enddate) {
        return logEntryDao.get(username, startdate, enddate, securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List<LogEntry> getWithNhsNo(String nhsno, Calendar startdate, Calendar enddate, String action) {
        return logEntryDao.getWithNhsNo(nhsno, startdate, enddate, action, securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List<LogEntry> getWithNhsNo(String nhsno, String user, String actor, String action, String unitcode,
                                       Calendar startdate, Calendar enddate) {
        return logEntryDao.getWithNhsNo(nhsno, user, actor, action, unitcode, startdate, enddate,
                securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List<LogEntry> getWithUnitCode(String unitcode, Calendar startdate, Calendar enddate) {
        return logEntryDao.getWithUnitCode(unitcode, startdate, enddate, securityUserManager.getLoggedInSpecialty());
    }
}