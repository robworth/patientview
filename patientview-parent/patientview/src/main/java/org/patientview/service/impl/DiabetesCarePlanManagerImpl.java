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

import org.patientview.patientview.model.DiabetesCarePlan;
import org.patientview.patientview.model.User;
import org.patientview.patientview.model.UserMapping;
import org.patientview.repository.DiabetesCarePlanDao;
import org.patientview.service.DiabetesCarePlanManager;
import org.patientview.service.UserManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(value = "diabetesCarePlanManager")
public class DiabetesCarePlanManagerImpl implements DiabetesCarePlanManager {

    @Inject
    private UserManager userManager;

    @Inject
    private DiabetesCarePlanDao carePlanDao;

    @Override
    public DiabetesCarePlan getCarePlan(User user) {
        String nhsNo = getNhsNumber(user);

        if (nhsNo != null) {
            return getCarePlan(nhsNo);
        }

        return null;
    }

    @Override
    public DiabetesCarePlan getCarePlan(String nhsno) {
        return carePlanDao.get(nhsno);
    }

    @Override
    public void saveCarePlan(DiabetesCarePlan carePlan) {
        carePlanDao.save(carePlan);
    }

    public String getNhsNumber(User user) {
        UserMapping userMapping = userManager.getUserMappingPatientEntered(user);

        if (userMapping != null) {
            return userMapping.getNhsno();
        }

        return null;
    }

}
