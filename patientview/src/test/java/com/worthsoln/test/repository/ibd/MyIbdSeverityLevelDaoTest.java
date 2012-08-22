package com.worthsoln.test.repository.ibd;

import com.worthsoln.ibd.model.MyIbdSeverityLevel;
import com.worthsoln.ibd.model.enums.Severity;
import com.worthsoln.repository.ibd.MyIbdSeverityLevelDao;
import com.worthsoln.test.repository.BaseDaoTest;
import org.junit.Test;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class MyIbdSeverityLevelDaoTest extends BaseDaoTest {

    @Inject
    private MyIbdSeverityLevelDao myIbdSeverityLevelDao;

    @Test
    public void testAddGetMyIbdSeverityLevel() throws Exception {
        MyIbdSeverityLevel myIbdSeverityLevel = getTestObject();

        myIbdSeverityLevelDao.save(myIbdSeverityLevel);

        MyIbdSeverityLevel checkMyIbdSeverityLevel = myIbdSeverityLevelDao.get(myIbdSeverityLevel.getId());

        assertNotNull(checkMyIbdSeverityLevel);
        assertTrue("Invalid id for new MyIbdSeverityLevel", checkMyIbdSeverityLevel.getId() > 0);
        assertEquals("Nhs no not stored", checkMyIbdSeverityLevel.getNhsno(), myIbdSeverityLevel.getNhsno());
        assertEquals("Severity not stored", checkMyIbdSeverityLevel.getSeverity(), myIbdSeverityLevel.getSeverity());
        assertEquals("Level not stored", checkMyIbdSeverityLevel.getLevel(), myIbdSeverityLevel.getLevel());
    }

    @Test
    public void testAddGetUpdateNoDuplicateLevel() throws Exception {
        // first make a severity level and save
        MyIbdSeverityLevel myIbdSeverityLevel1 = getTestObject();
        myIbdSeverityLevelDao.save(myIbdSeverityLevel1);
        assertTrue("Id invalud", myIbdSeverityLevel1.getId() > 0);

        // then make another seveirty level of same type and when saved should have same id as the first
        MyIbdSeverityLevel myIbdSeverityLevel2 = getTestObject();
        myIbdSeverityLevelDao.save(myIbdSeverityLevel2);
        assertEquals("First object was not updated", myIbdSeverityLevel1.getId(), myIbdSeverityLevel2.getId());
    }

    @Test
    public void testAddGetMyIbdSeverityLevelWithNoValue() throws Exception {
        // because no value has been set it should not have stored in db so should not have an id after
        MyIbdSeverityLevel myIbdSeverityLevel = getTestObject();
        myIbdSeverityLevel.setLevel(-1);

        myIbdSeverityLevelDao.save(myIbdSeverityLevel);

        assertNull(myIbdSeverityLevel.getId());
    }

    @Test
    public void testAddGetMyIbdSeverityLevelUpdateWithNoValue() throws Exception {
        // first make a level and save
        MyIbdSeverityLevel myIbdSeverityLevel = getTestObject();
        myIbdSeverityLevelDao.save(myIbdSeverityLevel);
        assertTrue("Id invalud", myIbdSeverityLevel.getId() > 0);

        // next pull it back update the value and save again
        MyIbdSeverityLevel checkMyIbdSeverityLevel = myIbdSeverityLevelDao.get(myIbdSeverityLevel.getNhsno(),
                myIbdSeverityLevel.getSeverity());
        assertNotNull(checkMyIbdSeverityLevel);
        checkMyIbdSeverityLevel.setLevel(checkMyIbdSeverityLevel.getSeverity().getDefaultLevel());
        myIbdSeverityLevelDao.save(checkMyIbdSeverityLevel);

        // now try pull back one time it should be null
        checkMyIbdSeverityLevel = myIbdSeverityLevelDao.get(myIbdSeverityLevel.getNhsno(),
                myIbdSeverityLevel.getSeverity());

        assertTrue(!checkMyIbdSeverityLevel.hasValidId());
    }

    private MyIbdSeverityLevel getTestObject() {
        MyIbdSeverityLevel myIbdSeverityLevel = new MyIbdSeverityLevel();
        myIbdSeverityLevel.setNhsno("1234567890");
        myIbdSeverityLevel.setSeverity(Severity.SEVERE);
        myIbdSeverityLevel.setLevel(12);
        return myIbdSeverityLevel;
    }
}
