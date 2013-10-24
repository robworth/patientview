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

package org.patientview.test.repository;

import org.patientview.patientview.model.LogEntry;
import org.patientview.patientview.model.Specialty;
import org.patientview.repository.LogEntryDao;
import org.patientview.test.helpers.RepositoryHelpers;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LogEntryDaoTest extends BaseDaoTest {

    @Inject
    private LogEntryDao logEntryDao;

    @Inject
    private RepositoryHelpers repositoryHelpers;

    private Specialty specialty;

    @Before
    public void setupSystem() {
        specialty = repositoryHelpers.createSpecialty("Specialty1", "ten1", "A test specialty");
    }

    @Test
    public void testAddGetLogEntry() throws Exception {
        LogEntry logEntry = getTestObject();

        logEntryDao.save(logEntry);

        assertTrue("Invalid id for new log entry", logEntry.getId() > 0);

        LogEntry checkLogEntry = logEntryDao.get(logEntry.getId());

        assertNotNull(checkLogEntry);
        assertEquals("Nhs no not persisted", logEntry.getNhsno(), checkLogEntry.getNhsno());
        assertEquals("Action not persisted", logEntry.getAction(), checkLogEntry.getAction());
        assertEquals("Actor not persisted", logEntry.getActor(), checkLogEntry.getActor());
        assertEquals("Date not persisted", logEntry.getDate(), checkLogEntry.getDate());
        assertEquals("Extra info not persisted", logEntry.getExtrainfo(), checkLogEntry.getExtrainfo());
        assertEquals("Unit code not persisted", logEntry.getUnitcode(), checkLogEntry.getUnitcode());
        assertEquals("User not persisted", logEntry.getUser(), checkLogEntry.getUser());
    }

    @Test
    public void testGetLatestLogEntry() throws Exception {
        // Want an older date so we can filter to the latest one
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -5);

        LogEntry logEntry1 = getTestObject();
        logEntry1.setDate(calendar);
        logEntryDao.save(logEntry1);

        assertTrue("Invalid id for new log entry 1", logEntry1.getId() > 0);

        LogEntry logEntry2 = getTestObject();
        logEntryDao.save(logEntry2);

        assertTrue("Invalid id for new log entry 2", logEntry2.getId() > 0);

        // create a third with a different nhs no that is newer than than the 2nd
        LogEntry logEntry3 = getTestObject();
        logEntry3.setNhsno("123456780");
        logEntryDao.save(logEntry3);

        assertTrue("Invalid id for new log entry 3", logEntry3.getId() > 0);

        // entry 2 was the newest so would expect this back
        LogEntry checkLogEntry = logEntryDao.getLatestLogEntry(logEntry2.getNhsno(), "TestAction");
        assertNotNull(checkLogEntry);
        assertEquals("Incorrect log entry retrieved", checkLogEntry.getId(), logEntry2.getId());
    }

    @Test
    public void testGetLogEntriesBetweenDatesWithUsername() throws Exception {
        // create a date from 2 days ago - will use this on two of the log entries
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, -2);

        // create a date from 5 days ago - will use this on one of the log entries and hopefully it wont be pulled back
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DATE, -5);

        // start date to filter from
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DATE, -3);

        // end date to filter from will be today
        Calendar endDate = Calendar.getInstance();

        // first two dates will be from 2 days ago and should be retrieved
        LogEntry logEntry1 = getTestObject();
        logEntry1.setDate(calendar1);
        logEntryDao.save(logEntry1);

        assertTrue("Invalid id for log entry 1", logEntry1.getId() > 0);

        LogEntry logEntry2 = getTestObject();
        logEntry2.setDate(calendar1);
        logEntryDao.save(logEntry2);

        assertTrue("Invalid id for log entry 2", logEntry2.getId() > 0);

        // third entry will be 5 days old and should not be retrieved
        LogEntry logEntry3 = getTestObject();
        logEntry3.setDate(calendar2);
        logEntryDao.save(logEntry3);

        assertTrue("Invalid id for log entry 3", logEntry3.getId() > 0);

        // fourth log entry is to make sure only ones for the actor user are pulled back in the range
        LogEntry logEntry4 = getTestObject();
        logEntry4.setDate(calendar1);
        logEntry4.setActor("TestActor2");
        logEntryDao.save(logEntry4);

        assertTrue("Invalid id for log entry 4", logEntry4.getId() > 0);

        // So we now expect back entry 1 & 2, 3 is out of the date range and 4 is in the range but for another user
        List<LogEntry> checkLogEntries = logEntryDao.get(logEntry1.getActor(), startDate, endDate, specialty);

        assertNotNull(checkLogEntries);
        assertTrue("No log entries found", !checkLogEntries.isEmpty() && checkLogEntries.size() > 0);
        assertTrue("To many log entries found", checkLogEntries.size() == 2);

        // check the order is by id so 1 first
        assertEquals("LogEntry1 was not first", checkLogEntries.get(0).getId(), logEntry1.getId());
        assertEquals("LogEntry2 was not second", checkLogEntries.get(1).getId(), logEntry2.getId());
    }

    @Test
    public void testGetLogEntriesBetweenDatesWithNhsNoAndAction() throws Exception {
        // create a date from 2 days ago - will use this on two of the log entries
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, -2);

        // create a date from 5 days ago - will use this on one of the log entries and hopefully it wont be pulled back
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DATE, -5);

        // start date to filter from
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DATE, -3);

        // end date to filter from will be today
        Calendar endDate = Calendar.getInstance();

        // first two dates will be from 2 days ago and should be retrieved
        LogEntry logEntry1 = getTestObject();
        logEntry1.setDate(calendar1);
        logEntryDao.save(logEntry1);

        assertTrue("Invalid id for log entry 1", logEntry1.getId() > 0);

        LogEntry logEntry2 = getTestObject();
        logEntry2.setDate(calendar1);
        logEntryDao.save(logEntry2);

        assertTrue("Invalid id for log entry 2", logEntry2.getId() > 0);

        // third entry will be in the range but have a different action
        LogEntry logEntry3 = getTestObject();
        logEntry3.setDate(calendar1);
        logEntry3.setAction("anewaction");
        logEntryDao.save(logEntry3);

        assertTrue("Invalid id for log entry 3", logEntry3.getId() > 0);

        // fourth entry will be 5 days old and should not be retrieved
        LogEntry logEntry4 = getTestObject();
        logEntry4.setDate(calendar2);
        logEntryDao.save(logEntry4);

        assertTrue("Invalid id for log entry 4", logEntry4.getId() > 0);

        // fifth log entry is to make sure only ones for the nhsno are pulled back in the range
        LogEntry logEntry5 = getTestObject();
        logEntry5.setDate(calendar1);
        logEntry5.setNhsno("123456780");
        logEntryDao.save(logEntry5);

        assertTrue("Invalid id for log entry 5", logEntry5.getId() > 0);

        // So we now expect back entry 1 & 2, 3 has a different action, 4 is out of the date range and 5
        // is in the range but for another user
        List<LogEntry> checkLogEntries = logEntryDao.getWithNhsNo(logEntry1.getNhsno(), startDate, endDate,
                logEntry1.getAction(), specialty);

        assertNotNull(checkLogEntries);
        assertTrue("No log entries found", !checkLogEntries.isEmpty() && checkLogEntries.size() > 0);
        assertTrue("To many log entries found", checkLogEntries.size() == 2);

        // check the order is by id so 1 first
        assertEquals("LogEntry1 was not first", checkLogEntries.get(0).getId(), logEntry1.getId());
        assertEquals("LogEntry2 was not second", checkLogEntries.get(1).getId(), logEntry2.getId());
    }

    @Test
    public void testGetLogEntriesWithAllFilters() throws Exception {
        // create a date from 2 days ago - will use this on two of the log entries
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, -2);

        // create a date from 5 days ago - will use this on one of the log entries and hopefully it wont be pulled back
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DATE, -5);

        // start date to filter from
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DATE, -3);

        // end date to filter from will be today
        Calendar endDate = Calendar.getInstance();

        // first two dates will be from 2 days ago and should be retrieved
        LogEntry logEntry1 = getTestObject();
        logEntry1.setDate(calendar1);
        logEntryDao.save(logEntry1);

        assertTrue("Invalid id for log entry 1", logEntry1.getId() > 0);

        LogEntry logEntry2 = getTestObject();
        logEntry2.setAction("testaction2");
        logEntry2.setNhsno("123456780");
        logEntry2.setActor("testactor2");
        logEntry2.setUser("testuser2");
        logEntry2.setUnitcode("testunit2");
        logEntry2.setDate(calendar1);
        logEntryDao.save(logEntry2);

        assertTrue("Invalid id for log entry 2", logEntry2.getId() > 0);

        // So we now expect back entry 1 and not 2
        List<LogEntry> checkLogEntries = logEntryDao.getWithNhsNo(logEntry1.getNhsno(), logEntry1.getUser(),
                logEntry1.getActor(), logEntry1.getAction(), logEntry1.getUnitcode(), startDate, endDate, specialty);

        assertNotNull(checkLogEntries);
        assertTrue("No log entries found", !checkLogEntries.isEmpty() && checkLogEntries.size() > 0);
        assertTrue("To many log entries found", checkLogEntries.size() == 1);
    }

    @Test
    public void testGetLogEntriesWithUnitCode() throws Exception {
        // create a date from 2 days ago - will use this on two of the log entries
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, -2);

        // create a date from 5 days ago - will use this on one of the log entries and hopefully it wont be pulled back
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DATE, -5);

        // start date to filter from
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DATE, -3);

        // end date to filter from will be today
        Calendar endDate = Calendar.getInstance();

        // first two dates will be from 2 days ago and should be retrieved
        LogEntry logEntry1 = getTestObject();
        logEntry1.setDate(calendar1);
        logEntryDao.save(logEntry1);

        assertTrue("Invalid id for log entry 1", logEntry1.getId() > 0);

        LogEntry logEntry2 = getTestObject();
        logEntry2.setUnitcode("testunit2");
        logEntry2.setDate(calendar1);
        logEntryDao.save(logEntry2);

        assertTrue("Invalid id for log entry 2", logEntry2.getId() > 0);

        // So we now expect back entry 1 and not 2
        List<LogEntry> checkLogEntries = logEntryDao.getWithUnitCode(logEntry1.getUnitcode(), startDate, endDate,
                specialty);

        assertNotNull(checkLogEntries);
        assertTrue("No log entries found", !checkLogEntries.isEmpty() && checkLogEntries.size() > 0);
        assertTrue("To many log entries found", checkLogEntries.size() == 1);
    }

    @Test
    // As per testGetLatestLogEntry() but with a specialty set
    public void testLogAndSearchWithSpecialty() {
        // Want an older date so we can filter to the latest one
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -5);

        LogEntry logEntry1 = getTestObject();
        logEntry1.setSpecialty(specialty);
        logEntry1.setDate(calendar);
        logEntryDao.save(logEntry1);

        assertTrue("Invalid id for new log entry 1", logEntry1.getId() > 0);

        LogEntry logEntry2 = getTestObject();
        logEntry2.setSpecialty(specialty);
        logEntryDao.save(logEntry2);

        assertTrue("Invalid id for new log entry 2", logEntry2.getId() > 0);

        // create a third with a different nhs no that is newer than than the 2nd
        LogEntry logEntry3 = getTestObject();
        logEntry3.setSpecialty(specialty);
        logEntry3.setNhsno("123456780");
        logEntryDao.save(logEntry3);

        assertTrue("Invalid id for new log entry 3", logEntry3.getId() > 0);

        // entry 2 was the newest so would expect this back
        LogEntry checkLogEntry = logEntryDao.getLatestLogEntry(logEntry2.getNhsno(), "TestAction");
        assertNotNull(checkLogEntry);
        assertEquals("Incorrect log entry retrieved", checkLogEntry.getId(), logEntry2.getId());
        assertEquals("Specialty not correct", specialty, checkLogEntry.getSpecialty());
    }

    private LogEntry getTestObject() {
        LogEntry logEntry = new LogEntry();
        logEntry.setAction("TestAction");
        logEntry.setActor("TestActor");
        logEntry.setDate(Calendar.getInstance());
        logEntry.setExtrainfo("Test extra info");
        logEntry.setNhsno("123456789");
        logEntry.setUnitcode("testunit");
        logEntry.setUser("testuser");
        logEntry.setSpecialty(specialty);
        return logEntry;
    }
}
