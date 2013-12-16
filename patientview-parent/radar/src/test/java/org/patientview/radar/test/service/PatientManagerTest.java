package org.patientview.radar.test.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.patientview.model.Patient;
import org.patientview.radar.dao.UtilityDao;
import org.patientview.radar.service.PatientManager;
import org.patientview.radar.test.TestPvDbSchema;
import org.patientview.radar.test.roles.unitadmin.RoleHelper;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;

/**
 * User: james@solidstategroup.com
 * Date: 05/12/13
 * Time: 15:13
 */
@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class PatientManagerTest extends TestPvDbSchema {

    private final static String testDiseaseUnit = "Allports";
    private final static String testRenalUnit = "RENALA";

    @Inject
    RoleHelper roleHelper;

    @Inject
    UtilityDao utilityDao;

    @Inject
    PatientManager patientManager;

    @Before
    public void setup() {
        // Setup a Renal Unit and Disease Group
        try {
            utilityDao.createUnit(testRenalUnit);
            utilityDao.createUnit(testDiseaseUnit);
        } catch (Exception e) {

        }
    }

    /**
     * Create a patient and then requery it
     *
     */
    @Test
    public void testGetById() throws Exception {
        Patient patient = roleHelper.createPatient("564564564" , testRenalUnit, testDiseaseUnit);

        patientManager.save(patient);


        Patient returnPatient = patientManager.getById(patient.getId());

        Assert.assertNotNull("There must be a patient returned", returnPatient);
        Assert.assertTrue("The two patients must have the same id", patient.getId().equals(patient.getId()));

    }


}
