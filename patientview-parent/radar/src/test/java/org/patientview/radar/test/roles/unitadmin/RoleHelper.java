package org.patientview.radar.test.roles.unitadmin;

import org.patientview.radar.dao.UserDao;
import org.patientview.radar.dao.UtilityDao;
import org.patientview.radar.model.user.PatientUser;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

/**
 * User: james@solidstategroup.com
 * Date: 04/11/13
 * Time: 10:16
 */
@Component
public class RoleHelper {

    @Inject
    private UserDao userDao;

    @Inject
    private UtilityDao utilityDao;


    public PatientUser createUnitAdmin(String nhsNo, String unitCode) throws Exception {
        // Creates a user in patient with a mapping!!
        PatientUser patientUser = createUserInPatientView(nhsNo, unitCode);
        userDao.createRoleInPatientView(patientUser.getUserId(), "unitadmin");
        return patientUser;
    }


    public void deleteUser(PatientUser patientUser) throws Exception {
        userDao.deletePatientUser(patientUser);
    }


    public PatientUser createUserInPatientView(String nhsNo, String unitCode) throws Exception {
        // Creates a user in patient with a mapping!!
        userDao.createRawUser(nhsNo, "TestUser", "Test User", nhsNo + "@aptientview.com", unitCode, nhsNo);
        return userDao.getExternallyCreatedPatientUser(nhsNo);

    }

    public void cleanTestData(List<PatientUser> patientUsers, String unitCode) {
        // -- Clean up test (No Transaction Manager)
        for (PatientUser patientUser : patientUsers) {
            utilityDao.deletePatientViewMapping(patientUser.getUsername());
            utilityDao.deletePatientViewUser(patientUser.getUsername());
        }

        utilityDao.deleteUnit(unitCode);
    }



}
