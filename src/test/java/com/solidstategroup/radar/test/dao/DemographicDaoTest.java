package com.solidstategroup.radar.test.dao;

import com.solidstategroup.radar.dao.DemographicsDao;
import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.model.Sex;
import com.solidstategroup.radar.model.Status;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class DemographicDaoTest extends BaseDaoTest {

    @Autowired
    private DemographicsDao demographicDao;

    @Test
    public void testGetDemographic() {
        Demographics demographics = demographicDao.getDemographicsByRadarNumber(241L);

        // Check it's not null
        assertNotNull("Demographics was null", demographics);

        // Check radar number is correct
        assertEquals("Wrong radar number", new Long(241), demographics.getId());

        // We should definitely have a valid forename and surname
        assertTrue("Forename was empty", StringUtils.isNotBlank(demographics.getForename()));
        assertTrue("Surname was empty", StringUtils.isNotBlank(demographics.getSurname()));

        // Check address according to correct values in dataset
        assertEquals("Wrong address 1", "9 Lowther Road", demographics.getAddress1());
        assertEquals("Wrong address 2", "Yeovil", demographics.getAddress2());
        assertEquals("Wrong address 3", "Somerset", demographics.getAddress3());
        assertEquals("Wrong postcode", "BA21 5PF", demographics.getPostcode());

        // Check hospital number and NHS number
        assertEquals("Wrong NHS number", "4921148228", demographics.getNhsNumber());
        assertEquals("Wrong Hospital number", "1703489M", demographics.getHospitalNumber());

        // Centre should be set as 3, Bristol
        assertNotNull("Centre was null", demographics.getRenalUnit());
        assertEquals("Centre was not Bristol", demographics.getRenalUnit().getAbbreviation(), "Bristol");
    }

    @Test
    public void testGetSexes() throws Exception {
        List<Sex> sexes = demographicDao.getSexes();
        assertNotNull("Sexes was null", sexes);
        assertEquals("Wrong size", 3, sexes.size());
    }

    @Test
    public void testGetStatuses() throws Exception {
        List<Status> statuses = demographicDao.getStatuses();
        assertNotNull("Statuses was null", statuses);
        assertEquals("Wrong size", 6, statuses.size());
    }
}
