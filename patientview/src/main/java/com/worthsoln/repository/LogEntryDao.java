package com.worthsoln.repository;

import com.worthsoln.patientview.model.LogEntry;
import com.worthsoln.patientview.model.Specialty;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Transactional(propagation = Propagation.MANDATORY)
public interface LogEntryDao {

    LogEntry get(Long id);

    void save(LogEntry logEntry);

    LogEntry getLatestLogEntry(String nhsno, String action, Specialty specialty);

    List<LogEntry> get(String username, Calendar startdate, Calendar enddate, Specialty specialty);

    // action is optional
    List<LogEntry> getWithNhsNo(String nhsno, Calendar startdate, Calendar enddate, String action, Specialty specialty);

    List<LogEntry> getWithNhsNo(String nhsno, String user, String actor, String action, String unitcode,
                                Calendar startdate, Calendar enddate, Specialty specialty);

    List<LogEntry> getWithUnitCode(String unitcode, Calendar startdate, Calendar enddate, Specialty specialty);
}
