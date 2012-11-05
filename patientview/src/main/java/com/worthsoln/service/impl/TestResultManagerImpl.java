package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.Panel;
import com.worthsoln.patientview.model.TestResult;
import com.worthsoln.patientview.model.TestResultWithUnitShortname;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.patientview.unit.UnitUtils;
import com.worthsoln.repository.TestResultDao;
import com.worthsoln.service.TestResultManager;
import com.worthsoln.service.UnitManager;
import com.worthsoln.service.UserManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service(value = "testResultManager")
public class TestResultManagerImpl implements TestResultManager {

    @Inject
    private TestResultDao testResultDao;

    @Inject
    private UnitManager unitManager;

    @Inject
    private UserManager userManager;

    @Override
    public List<TestResultWithUnitShortname> getTestResultForPatient(User user, Panel panel) {
        return testResultDao.getTestResultForPatient(user.getUsername(), panel, unitManager.getUsersUnits(user));
    }

    @Override
    public void save(TestResult testResult) {
        testResultDao.save(testResult);
    }

    @Override
    public List<TestResult> get(String nhsno, String unitcode) {
        return testResultDao.get(nhsno, unitcode);
    }

    @Override
    public String getLatestWeightFromResults(User user) {
        List<UserMapping> userMappings = userManager.getUserMappingsExcludeUnitcode(user.getUsername(),
                UnitUtils.PATIENT_ENTERS_UNITCODE);

        if (userMappings != null && !userMappings.isEmpty()) {
            List<String> unitcodes = new ArrayList<String>();

            for (UserMapping userMapping : userMappings) {
                unitcodes.add(userMapping.getUnitcode());
            }

            return testResultDao.getLatestWeightFromResults(userMappings.get(0).getNhsno(), unitcodes);
        }

        return null;
    }
}
