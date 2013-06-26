package org.patientview.radar.test.dao;

import org.patientview.radar.dao.PlasmapheresisDao;
import org.patientview.radar.model.Plasmapheresis;
import org.patientview.radar.model.PlasmapheresisExchangeUnit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class PlasmapheresisDaoTest extends BaseDaoTest {

    @Autowired
    private PlasmapheresisDao plasmapheresisDao;

    @Test
    public void testSavePlasmapheresis() throws Exception {
        // save
        Plasmapheresis plasmapheresis = new Plasmapheresis();
        plasmapheresisDao.savePlasmapheresis(plasmapheresis);
        assertNotNull(plasmapheresis.getId());

        // update
        Plasmapheresis plasmapheresisUpdate = new Plasmapheresis();
        plasmapheresisUpdate.setId(new Long(1));
        plasmapheresisDao.savePlasmapheresis(plasmapheresisUpdate);
    }

    @Test
    public void testPlasmapheresisDelete() throws Exception {
        Plasmapheresis plasmapheresis =
                plasmapheresisDao.getPlasmapheresis(new Long(1));
        assertNotNull(plasmapheresis);

        plasmapheresisDao.deletePlasmaPheresis(plasmapheresis);
        plasmapheresis = plasmapheresisDao.getPlasmapheresis(new Long(1));
        assertNull(plasmapheresis);
    }

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

    @Test
    public void testGetPlasmapheresisExchangeUnits() {
        List<PlasmapheresisExchangeUnit> exchangeUnits = plasmapheresisDao.getPlasmapheresisExchangeUnits();
        assertNotNull("List null", exchangeUnits);
        assertEquals("List is wrong size", 8, exchangeUnits.size());
    }
}
