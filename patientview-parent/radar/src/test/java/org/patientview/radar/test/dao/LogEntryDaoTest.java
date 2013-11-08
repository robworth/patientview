package org.patientview.radar.test.dao;

import org.junit.Test;
import org.patientview.radar.dao.ClinicalDataDao;
import org.patientview.radar.dao.LogEntryDao;
import org.patientview.radar.model.LogEntry;
import org.patientview.radar.model.Phenotype;
import org.patientview.radar.model.sequenced.ClinicalData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class LogEntryDaoTest extends BaseDaoTest {

    @Autowired
    private LogEntryDao logEntryDao;

    @Test
    public void testSave() {

        LogEntry logEntry = new LogEntry();
        logEntry.setAction("TestAction");
        logEntry.setActor("TestActor");
        logEntry.setDate(new Date());
        logEntry.setExtrainfo("Test extra info");
        logEntry.setNhsno("123456789");
        logEntry.setUnitcode("testunit");
        logEntry.setUser("testuser");
        logEntry.setSpecialty(1);

        logEntryDao.save(logEntry);
        assertNotNull(logEntry.getId());

        LogEntry checkLogEntry = logEntryDao.get(logEntry.getId());
        assertEquals("LogEntry saving incorrect", "TestAction", checkLogEntry.getAction());

    }
}
