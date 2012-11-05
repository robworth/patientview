package com.worthsoln.service;

import com.worthsoln.patientview.model.Panel;
import com.worthsoln.patientview.model.TestResult;
import com.worthsoln.patientview.model.TestResultWithUnitShortname;
import com.worthsoln.patientview.model.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface TestResultManager {

    List<TestResultWithUnitShortname> getTestResultForPatient(User user, Panel panel);

    void save(TestResult testResult);

    List<TestResult> get(String nhsno, String unitcode);

    String getLatestWeightFromResults(User user);
}
