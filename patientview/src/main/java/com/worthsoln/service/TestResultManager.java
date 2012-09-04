package com.worthsoln.service;

import com.worthsoln.patientview.model.Panel;
import com.worthsoln.patientview.model.TestResult;
import com.worthsoln.patientview.model.TestResultWithUnitShortname;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface TestResultManager {

    List<TestResultWithUnitShortname> getTestResultForPatient(String username, Panel panel);

    void save(TestResult testResult);

    List<TestResult> get(String nhsno, String unitcode);
}
