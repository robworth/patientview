package com.solidstategroup.radar.test.dao;

import com.solidstategroup.radar.dao.UtilityDao;
import com.solidstategroup.radar.model.Centre;
import com.solidstategroup.radar.model.Consultant;
import com.solidstategroup.radar.model.Country;
import com.solidstategroup.radar.model.Ethnicity;
import com.solidstategroup.radar.model.Relative;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UtilityDaoTest extends BaseDaoTest {

    @Autowired
    private UtilityDao utilityDao;

    @Test
    public void testGetCentre() {
        Centre centre = utilityDao.getCentre(3L);
        assertNotNull("Centre is null", centre);
        assertEquals("Centre ID is wrong", new Long(3), centre.getId());
        assertEquals("Name is wrong", "Bristol Royal Hospital for Children", centre.getName());
        assertEquals("Abbreviation is wrong", "Bristol", centre.getAbbreviation());

        // Check country
        assertNotNull("Country is null", centre.getCountry());
        assertEquals("Country name is wrong", "GB and Ireland", centre.getCountry().getName());
    }

    @Test
    public void testGetCentres() {
        List<Centre> centres = utilityDao.getCentres();
        assertNotNull("Centres list is null", centres);
    }

    @Test
    public void testGetConsultants() {
        List<Consultant> consultants = utilityDao.getConsultants();
        assertNotNull("Consultants list is null", consultants);
    }

    @Test
    public void testGetConsultant() {
        Consultant consultant = utilityDao.getConsultant(4L);
        assertNotNull("Consultant is null");
        assertEquals("Surname is wrong", "ANBU", consultant.getSurname());
        assertEquals("Forename is wrong", "Dr A Theodore", consultant.getForename());
        assertNull("Centre is not null", consultant.getCentre());
    }

    @Test
    public void testGetConsultantWithCentre() {
        Consultant consultant = utilityDao.getConsultant(5L);
        assertNotNull("Consultant is null");
        assertEquals("Surname is wrong", "ARNEIL", consultant.getSurname());
        assertEquals("Forename is wrong", "Professor Gavin", consultant.getForename());
        assertNotNull("Centre is null", consultant.getCentre());
        assertEquals("Centre is wrong", "Glasgow", consultant.getCentre().getAbbreviation());
    }

    @Test
    public void testGetCountries() {
        List<Country> countries = utilityDao.getCountries();
        assertNotNull("Countries list is null", countries);
    }

    @Test
    public void testGetEthnicities() {
        List<Ethnicity> ethnicities = utilityDao.getEthnicities();
        assertNotNull("Ethnicities list is null", ethnicities);
    }

    @Test
    public void testGetRelatives() {
        List<Relative> relatives = utilityDao.getRelatives();
        assertNotNull("Relatives list is null", relatives);
    }

}
