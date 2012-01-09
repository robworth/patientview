package com.solidstategroup.radar.test.dao;

import com.solidstategroup.radar.dao.HospitalisationDao;
import com.solidstategroup.radar.model.Hospitalisation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HospitalisationDaoTest extends BaseDaoTest {

    @Autowired
    private HospitalisationDao hospitalisationDao;

    @Test
    public void testGetHospitalisation() {
        Hospitalisation hospitalisation = hospitalisationDao.getHospitalisation(3L);
        assertNotNull("Hospitalisation was null", hospitalisation);
    }

    @Test
    public void testGetHospitalisationByRadarNumber() {
        List<Hospitalisation> hospitalisations = hospitalisationDao.getHospitalisationsByRadarNumber(250L);
        assertNotNull("Hospitalisations list was null");
        assertEquals("Wrong size", 1, hospitalisations.size());
    }
}
