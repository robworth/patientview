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
import org.patientview.patientview.model.ResultHeading;
import org.patientview.repository.ResultHeadingDao;
import org.patientview.service.ResultHeadingManager;
import org.patientview.service.SecurityUserManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 *
 */
@Service(value = "resultHeadingManager")
public class ResultHeadingManagerImpl implements ResultHeadingManager {

    @Inject
    private ResultHeadingDao resultHeadingDao;

    @Inject
    private SecurityUserManager securityUserManager;

    @Override
    public ResultHeading get(String headingcode) {
        return resultHeadingDao.get(headingcode, securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List<ResultHeading> getAll() {
        return resultHeadingDao.getAll(securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List<ResultHeading> get(int panel) {
        return resultHeadingDao.get(panel, securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public void save(ResultHeading resultHeading) {

        // set the Specialty against the heading if not already set
        if (resultHeading.getSpecialty() == null) {
            resultHeading.setSpecialty(securityUserManager.getLoggedInSpecialty());
        }

        resultHeadingDao.save(resultHeading);
    }

    @Override
    public void delete(String headingCode) {
        resultHeadingDao.delete(headingCode, securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List<Panel> getPanels() {
        return resultHeadingDao.getPanels(securityUserManager.getLoggedInSpecialty());
    }
}
