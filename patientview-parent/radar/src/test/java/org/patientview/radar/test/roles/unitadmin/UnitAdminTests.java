package org.patientview.radar.test.roles.unitadmin;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.patientview.model.Patient;
import org.patientview.radar.dao.UtilityDao;
import org.patientview.radar.model.user.PatientUser;
import org.patientview.radar.service.DemographicsManager;
import org.patientview.radar.service.UserManager;
import org.patientview.radar.test.TestPvDbSchema;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Notes: Seen on frontend via ExistingPatientsListingPage,
 * the DemographicsDataProvider uses DemographicsManager.getDemographicsByRenalUnit.
 * <p/>
 * <p/>
 * Todo move the logic to get Centre from User out of ExistingPatientsListingPage so it can be covered by these tests
 * <p/>
 * todo don't apply the dataset.xml, and build the test context from scratch for each role
 */
@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class UnitAdminTests extends TestPvDbSchema {

    @Inject
    RoleHelper roleHelper;

    @Inject
    UtilityDao utilityDao;

    @Inject
    UserManager userManager;

    @Inject
    DemographicsManager demographicsManager;


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
     * Test: the creation of a patient from Radar and make sure the User table and the UserMappings table get
     * correctly updated. For this user there will be no record existing in the patient table.
     *
     * The correct mapping should be
     * 1) To the disease the patient has currently diagnosed with
     * 2) The unit that the user selected in the Renal Unit
     * 3) The user should be mapped to the Patient Unit Group
     *
     * @throws Exception
     */
    @Test
    public void testUserRegistrationWithMappings() throws Exception {

        // Create a patient that is in a disease group and a Renal Unit
        List<PatientUser> patientUsers = roleHelper.createPatientsInUnit(testRenalUnit,testDiseaseUnit, 1);


        // Assert
        List<String> unitCodes = userManager.getUnitCodes(patientUsers.get(0));
        Assert.assertTrue("There should be three units mapped to the user", unitCodes.size() == 3);
        Assert.assertTrue("The should be a " + testDiseaseUnit + " unit code mapped", containsUnitCode(unitCodes, testDiseaseUnit));
        Assert.assertTrue("The should be a " + testRenalUnit + " unit code mapped", containsUnitCode(unitCodes, testRenalUnit));
        Assert.assertTrue("The should be a PATIENT unit code mapped", containsUnitCode(unitCodes, "PATIENT"));


    }

    /**
     * Add a user mapping for Renal Unit A
     * Create patients into Renal Unit A
     * Show that the service call for get Patients as shown on the Radar patient list ExistingPatientsListingPage
     * will only return users with mappings into Unit A
     */
    @Test
    public void testUnitAdminCanSeePatientsInTheirRenalUnit() throws Exception {
        int patientCount = 12;

        // Create a Unit Admin the the Renal A Unit
        PatientUser userAdmin = roleHelper.createUnitAdmin(StringUtils.right(Long.toString(new Date().getTime()), 10), testRenalUnit);
        // Create the users that are mapped to the Renal A Unit
        roleHelper.createPatientsInUnit(testRenalUnit, testRenalUnit, patientCount);
        // Retrieve all the users that are mapped to the UnitAdmins Unit.
        List<Patient> patientInUnit = demographicsManager.getDemographicsByUnitAdmin(userAdmin);
        Assert.assertTrue("There should 10 patient returned", patientInUnit.size() == patientCount);

    }

    /**
     * Add a user mapping for Disease group Alports
     * Create patients into Disease group Alports
     * Show that the service call for get Patients as shown on the Radar patient list ExistingPatientsListingPage
     * will only return users with mappings into Disease group Alports
     */
    @Test
    public void testUnitAdminCanSeePatientsInTheirDiseaseGroup() throws Exception{
        int patientCount = 34;

        // Create patients that are in only in 1 disease group (no renal unit)
        roleHelper.createPatientsInUnit(testDiseaseUnit, testDiseaseUnit, patientCount);
        // Create a unit admin the belongs to that disease group
        PatientUser userAdmin = roleHelper.createUnitAdmin("675675", testDiseaseUnit);

        List<Patient> patientInUnit = demographicsManager.getDemographicsByUnitAdmin(userAdmin);

        Assert.assertTrue("There should be the correct number of people in the disease group", patientInUnit.size() == patientCount);
    }

    /**
     * Add a user mapping for Renal Unit A AND Disease group Alports
     * Create patients into Renal Unit A AND Disease group Alports
     * Show that the service call for get Patients as shown on the Radar patient list ExistingPatientsListingPage
     * will only return users with mappings into Renal Unit A AND Disease group Alports
     */
    @Test
    public void testUnitAdminCanSeePatientsAggregatingRenalUnitAndDiseaseGroup() throws Exception {
        int renalPatientCount = 32;
        int diseasePatientCount =  51;

        // Create patients that are in only in 1 disease group (no renal unit)
        roleHelper.createPatientsInUnit(testDiseaseUnit, testDiseaseUnit, diseasePatientCount);
        // Create patients that are in only in 1 renal unit (no disease group)
        roleHelper.createPatientsInUnit(testRenalUnit, testRenalUnit, renalPatientCount);

        // Create a unit admin the belongs to that disease group and Renal Unit
        PatientUser userAdmin = roleHelper.createUnitAdmin("675675", testDiseaseUnit, testRenalUnit);

        // Retrieve the patients that are mapped to the Renal Unit and Disease Group
        List<Patient> patientInUnit = demographicsManager.getDemographicsByUnitAdmin(userAdmin);

        Assert.assertTrue("There should be the correct number of people returned for the mappings",
                patientInUnit.size() == (renalPatientCount + diseasePatientCount) );
    }

    private boolean containsUnitCode(List<String> unitCodes, String unitCode) {

        for (String s : unitCodes) {
            if (s.equalsIgnoreCase(unitCode)) {
                return true;
            }
        }
        return false;
    }

}
