package org.patientview.radar.test.roles.unitadmin;

import org.junit.Before;
import org.junit.Test;

/**
 *  Notes: Seen on frontend via ExistingPatientsListingPage,
 *  the DemographicsDataProvider uses DemographicsManager.getDemographicsByRenalUnit.
 *
 *  Todo move the logic to get Centre from User out of ExistingPatientsListingPage so it can be covered by these tests
 *
 *  todo don't apply the dataset.xml, and build the test context from scratch for each role
 */
public class UnitAdminTests {

    /**
     * A unit admin is created by the superadmin in PV.
     *
     * Create a basic PV user structure - User - Role as unit admin etc
     */
    @Before
    public void setup() {

    }

    /**
     * Add a user mapping for Renal Unit A
     * Create patients into Renal Unit A
     * Show that the service call for get Patients as shown on the Radar patient list ExistingPatientsListingPage
     * will only return users with mappings into Unit A
     */
    @Test
    public void testUnitAdminCanSeePatientsInTheirRenalUnit() {

    }

    /**
     * Add a user mapping for Disease group Alports
     * Create patients into Disease group Alports
     * Show that the service call for get Patients as shown on the Radar patient list ExistingPatientsListingPage
     * will only return users with mappings into Disease group Alports
     */
    @Test
    public void testUnitAdminCanSeePatientsInTheirDiseaseGroup() {

    }

    /**
     * Add a user mapping for Renal Unit A AND Disease group Alports
     * Create patients into Renal Unit A AND Disease group Alports
     * Show that the service call for get Patients as shown on the Radar patient list ExistingPatientsListingPage
     * will only return users with mappings into Renal Unit A AND Disease group Alports
     */
    @Test
    public void testUnitAdminCanSeePatientsAggregatingRenalUnitAndDiseaseGroup() {

    }
}
