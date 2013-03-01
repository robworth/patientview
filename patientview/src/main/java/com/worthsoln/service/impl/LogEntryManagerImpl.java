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

        if (logEntry.getTenancy() == null) {
            logEntry.setTenancy(securityUserManager.getLoggedInTenancy());
        }

        logEntryDao.save(logEntry);
    }

    @Override
    public LogEntry getLatestLogEntry(String nhsno, String action) {
        return logEntryDao.getLatestLogEntry(nhsno, action, securityUserManager.getLoggedInTenancy());
    }

    @Override
    public List<LogEntry> get(String username, Calendar startdate, Calendar enddate) {
        return logEntryDao.get(username, startdate, enddate, securityUserManager.getLoggedInTenancy());
    }

    @Override
    public List<LogEntry> getWithNhsNo(String nhsno, Calendar startdate, Calendar enddate, String action) {
        return logEntryDao.getWithNhsNo(nhsno, startdate, enddate, action, securityUserManager.getLoggedInTenancy());
    }

    @Override
    public List<LogEntry> getWithNhsNo(String nhsno, String user, String actor, String action, String unitcode,
                                       Calendar startdate, Calendar enddate) {
        return logEntryDao.getWithNhsNo(nhsno, user, actor, action, unitcode, startdate, enddate,
                securityUserManager.getLoggedInTenancy());
    }

    @Override
    public List<LogEntry> getWithUnitCode(String unitcode, Calendar startdate, Calendar enddate) {
        return logEntryDao.getWithUnitCode(unitcode, startdate, enddate, securityUserManager.getLoggedInTenancy());
    }
}
