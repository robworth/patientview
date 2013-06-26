package org.patientview.radar.test.dao;

import org.patientview.radar.dao.HospitalisationDao;
import org.patientview.radar.model.Hospitalisation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class HospitalisationDaoTest extends BaseDaoTest {

    @Autowired
    private HospitalisationDao hospitalisationDao;

    @Test
    public void testSaveHospitilisation() throws Exception {

        // save
        Hospitalisation hospitalisation = new Hospitalisation();
        hospitalisationDao.saveHospitilsation(hospitalisation);
        assertNotNull(hospitalisation.getId());

        // update
        Hospitalisation hospitalisationUpdate = new Hospitalisation();
        hospitalisationUpdate.setId(new Long(1));
        hospitalisationDao.saveHospitilsation(hospitalisationUpdate);
    }

    @Test
    public void testGetHospitalisation() {
        Hospitalisation hospitalisation = hospitalisationDao.getHospitalisation(3L);
        assertNotNull("Hospitalisation was null", hospitalisation);
        assertEquals("Wrong ID", new Long(3), hospitalisation.getId());
    }

    @Test
    public void testGetHospitalisationUnknown() {
        Hospitalisation hospitalisation = hospitalisationDao.getHospitalisation(3232L);
        assertNull("Hospitalisation null", hospitalisation);
    }

    @Test
    public void testGetHospitalisationByRadarNumber() {
        List<Hospitalisation> hospitalisations = hospitalisationDao.getHospitalisationsByRadarNumber(250L);
        assertNotNull("Hospitalisations list was null");
        assertEquals("Wrong size", 1, hospitalisations.size());
    }
}
