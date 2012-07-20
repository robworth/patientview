package com.worthsoln.service;

import com.worthsoln.patientview.model.LogEntry;

import java.util.Calendar;
import java.util.List;

/**
 *
 */
public interface LogEntryManager {

    void save(LogEntry logEntry);

    LogEntry getLatestLogEntry(String nhsno, String action);

    List<LogEntry> get(String username, Calendar startdate, Calendar enddate);

    // action is optional
    List<LogEntry> getWithNhsNo(String nhsno, Calendar startdate, Calendar enddate, String action);

    List<LogEntry> getWithNhsNo(String nhsno, String user, String actor, String action, String unitcode,
                                Calendar startdate, Calendar enddate);

    List<LogEntry> getWithUnitCode(String unitcode, Calendar startdate, Calendar enddate);
}
