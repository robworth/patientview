package org.patientview.radar.test.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.patientview.radar.dao.PatientLinkDao;
import org.patientview.radar.dao.UtilityDao;
import org.patientview.radar.model.PatientLink;
import org.patientview.radar.test.TestPvDbSchema;
import org.patientview.radar.test.roles.unitadmin.RoleHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Tests for the link creation when linking a patient record to an existing patient view record created by the XML
 * import.
 *
 * User: james@solidstategroup.com
 * Date: 14/11/13
 * Time: 16:30
 */
@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class PatientLinkDaoTest extends TestPvDbSchema {

    @Inject
    @Named(value = "patientLinkDao")
    private PatientLinkDao patientLinkDao;

    @Inject
    private UtilityDao utilityDao;

    @Inject
    private ApplicationContext applicationContext;

    @Inject
    private RoleHelper roleHelper;

    final String testDiseaseUnit = "RENALA";
    final String testRenalUnit = "Allports";

    /**
     * A unit admin is created by the superadmin in PV.
     * <p/>
     * Create a basic PV user structure - User - Role as unit admin etc
     */
    @Before
    public void setup() {
        // Setup a Renal Unit and Disease Group
        utilityDao.createUnit(testRenalUnit);
        utilityDao.createUnit(testDiseaseUnit);
    }


    /**
     * Test that the DAO can create a link
     *
     */
    @Test
    public void testPatientLinkCreation() {
        PatientLink patientLink = new PatientLink();

        patientLink.setSourceNhsNO("3423");
        patientLink.setSourceUnit(testDiseaseUnit);

        patientLink.setDestinationNhsNo("56");
        patientLink.setDestinationUnit(testRenalUnit);

        patientLink = patientLinkDao.createLink(patientLink);

        Assert.assertTrue("The patient link as a valid id", patientLink.hasValidId());

    }


    private PatientLink createLink(String nshNo, String sourceUnitCode, String destinationUnitCode) {
        PatientLink patientLink = new PatientLink();

        patientLink.setSourceNhsNO(nshNo);
        patientLink.setSourceUnit(sourceUnitCode);

        patientLink.setDestinationNhsNo("56");
        patientLink.setDestinationUnit(destinationUnitCode);

        patientLink = patientLinkDao.createLink(patientLink);

        return patientLink;
    }


    @After
    public void tearDown() throws Exception {
        this.clearData();
    }
}
