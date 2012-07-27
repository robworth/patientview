package com.worthsoln.test.repository;

import com.worthsoln.patientview.model.UktStatus;
import com.worthsoln.repository.UktStatusDao;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.*;

/**
 *
 */
public class UktStatusDaoTest extends BaseDaoTest {

    @Inject
    private UktStatusDao uktStatusDao;

    @Test
    public void testSaveGet() {

        UktStatus uktStatus = new UktStatus();
        uktStatus.setKidney("kidney");
        uktStatus.setNhsno("nhsno1");
        uktStatus.setPancreas("pancreas");

        uktStatusDao.save(uktStatus);

        assertTrue("Invalid id after save", uktStatus.getId() > 0);

        UktStatus check = uktStatusDao.get(uktStatus.getNhsno());

        assertEquals("Check has incorrect pancreas", "pancreas", check.getPancreas());
    }
}
