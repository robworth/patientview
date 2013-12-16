package org.patientview.radar.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.patientview.model.Patient;
import org.patientview.radar.dao.PatientDao;
import org.patientview.radar.dao.UserDao;
import org.patientview.radar.dao.UtilityDao;
import org.patientview.radar.test.TestPvDbSchema;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;
import java.util.List;

/**
 * User: james@solidstategroup.com
 * Date: 06/11/13
 * Time: 10:15
 */
@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class PatientDaoTest extends TestPvDbSchema {

    @Inject
    private PatientDao patientDao;

    @Inject
    UtilityDao utilityDao;

    @Inject
    UserDao userDao;

    /**
     * Get to retrieve records (SQL syntax test bascially). TODO embellish with actual data!
     *
     */
    @Test
    public void testGetPatientsByNhsNo(){

        List<Patient> patientList = patientDao.getPatientsByNhsNumber("test");


    }

}
