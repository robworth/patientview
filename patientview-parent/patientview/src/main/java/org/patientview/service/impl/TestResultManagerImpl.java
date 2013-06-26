/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.service.impl;

import org.patientview.patientview.model.Panel;
import org.patientview.patientview.model.TestResult;
import org.patientview.patientview.model.TestResultWithUnitShortname;
import org.patientview.patientview.model.User;
import org.patientview.patientview.model.UserMapping;
import org.patientview.patientview.unit.UnitUtils;
import org.patientview.repository.TestResultDao;
import org.patientview.service.TestResultManager;
import org.patientview.service.UnitManager;
import org.patientview.service.UserManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
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

    @Override
    public void deleteTestResultsWithinTimeRange(String nhsno, String unitcode, String testcode, Date startDate,
                                                 Date endDate) {
        testResultDao.deleteTestResultsWithinTimeRange(nhsno, unitcode, testcode, startDate, endDate);
    }

    @Override
    public void deleteTestResults(String nhsno, String unitcode) {
        testResultDao.deleteTestResults(nhsno, unitcode);
    }
}
