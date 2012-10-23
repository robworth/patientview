package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.Panel;
import com.worthsoln.patientview.model.TestResult;
import com.worthsoln.patientview.model.TestResultWithUnitShortname;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.TestResultDao;
import com.worthsoln.service.TestResultManager;
import com.worthsoln.service.UnitManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
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
}
