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

package org.patientview.service;

import org.patientview.patientview.controller.PagedResultsWrapper;
import org.patientview.patientview.model.TestResultWithUnitShortname;
import org.patientview.patientview.model.Panel;
import org.patientview.patientview.model.User;
import org.patientview.patientview.model.TestResult;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
public interface TestResultManager {

    List<TestResultWithUnitShortname> getTestResultForPatient(User user, Panel panel);

    void save(TestResult testResult);

    List<TestResult> get(String nhsno, String unitcode);

    PagedResultsWrapper<TestResult> get(Set<String> nhsnos, int page, int pagesize);

    String getLatestWeightFromResults(User user);

    void deleteTestResultsWithinTimeRange(String nhsno, String unitcode, String testcode, Date startDate, Date endDate);

    void deleteTestResults(String nhsno, String unitcode);
}
