package com.solidstategroup.radar.test.dao;

import com.solidstategroup.radar.dao.TransplantDao;
import com.solidstategroup.radar.model.Transplant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TransplantDaoTest extends BaseDaoTest {

    @Autowired
    private TransplantDao transplantDao;

    @Test
    public void testGetTransplant() {
        Transplant transplant = transplantDao.getTransplant(4L);
        assertNotNull("Transplant was null", transplant);
        assertEquals("Wrong ID on transplant", new Long(4), transplant.getId());
    }

    @Test
    public void testGetTransplantUnknown() {
        Transplant transplant = transplantDao.getTransplant(2324L);
        assertNull("Transplant was not null", transplant);
    }

    @Test
    public void testGetTransplantByRadarNumber() {
        List<Transplant> transplants = transplantDao.getTransplantsByRadarNumber(219L);
        assertNotNull("Transplants list was null", transplants);
        assertEquals("Wrong size", 24, transplants.size());
    }
}
