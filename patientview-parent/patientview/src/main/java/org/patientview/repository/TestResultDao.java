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

package org.patientview.repository;

import org.patientview.patientview.model.TestResult;
import org.patientview.patientview.model.TestResultWithUnitShortname;
import org.patientview.patientview.model.Panel;
import org.patientview.patientview.model.Unit;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface TestResultDao {

    /**
     * Get the test results for the patient for the units they belong to.
     * The unit list is per Specialty.
     *
     * @param units not mandatory
     */
    List<TestResultWithUnitShortname> getTestResultForPatient(String username, Panel panel, List<Unit> units);

    void save(TestResult testResult);

    List<TestResult> get(String nhsno, String unitcode);

    String getLatestWeightFromResults(String nhsno, List<String> unitcodes);

    void deleteTestResultsWithinTimeRange(String nhsno, String unitcode, String testcode, Date startDate, Date endDate);

    void deleteTestResults(String nhsno, String unitcode);
}
