package com.solidstategroup.radar.test.dao;

import com.solidstategroup.radar.dao.UtilityDao;
import com.solidstategroup.radar.model.Centre;
import com.solidstategroup.radar.model.Country;
import com.solidstategroup.radar.model.Ethnicity;
import com.solidstategroup.radar.model.Relative;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class UtilityDaoTest extends BaseDaoTest {

    @Autowired
    private UtilityDao utilityDao;

    @Test
    public void testGetCentres() {
        List<Centre> centres = utilityDao.getCentres();
        assertNotNull("Centres list is null", centres);
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
