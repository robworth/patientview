package com.solidstategroup.radar.test.dao;

import com.solidstategroup.radar.dao.PlasmapheresisDao;
import com.solidstategroup.radar.model.Plasmapheresis;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PlasmapheresisDaoTest extends BaseDaoTest {

    @Autowired
    private PlasmapheresisDao plasmapheresisDao;

    @Test
    public void testGetPlasmapheresis() {
        Plasmapheresis plasmapheresis = plasmapheresisDao.getPlasmapheresis(1L);
        assertNotNull("Plasmapheresis wasn't null", plasmapheresis);
        assertEquals("Wrong ID", new Long(1), plasmapheresis.getId());
        assertEquals("Wrong radar number", new Long(218), plasmapheresis.getRadarNumber());
        assertNotNull("Null exchange", plasmapheresis.getPlasmapheresisExchanges());
        assertEquals("Wrong exchange", "x4/wk", plasmapheresis.getPlasmapheresisExchanges().getName());
    }

    @Test
    public void testGetPlasmapheresisByRadarNumber() {
        List<Plasmapheresis> plasmapheresisList = plasmapheresisDao.getPlasmapheresisByRadarNumber(218L);
        assertNotNull("List was null", plasmapheresisList);
        assertEquals("Wrong size", 5, plasmapheresisList.size());

        // All the items should have right radar number
        for (Plasmapheresis plasmapheresis : plasmapheresisList) {
            assertEquals("Wrong radar number", new Long(218), plasmapheresis.getRadarNumber());
        }
    }

}
