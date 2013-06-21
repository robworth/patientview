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

package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.SplashPage;
import com.worthsoln.patientview.model.SplashPageUserSeen;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.SplashPageDao;
import com.worthsoln.repository.SplashPageUserSeenDao;
import com.worthsoln.service.SecurityUserManager;
import com.worthsoln.service.SplashPageManager;
import com.worthsoln.service.UnitManager;
import com.worthsoln.service.UserManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 *
 */
@Service(value = "splashPageManager")
public class SplashPageManagerImpl implements SplashPageManager {

    @Inject
    private UnitManager unitManager;

    @Inject
    private UserManager userManager;

    @Inject
    private SecurityUserManager securityUserManager;

    @Inject
    private SplashPageDao splashPageDao;

    @Inject
    private SplashPageUserSeenDao splashPageUserSeenDao;


    @Override
    public SplashPage get(Long id) {
        return splashPageDao.get(id);
    }

    @Override
    public void save(SplashPage splashPage) {

        // apply Specialty to splash page if not set
        if (splashPage.getSpecialty() == null) {
            splashPage.setSpecialty(securityUserManager.getLoggedInSpecialty());
        }

        splashPageDao.save(splashPage);
    }

    @Override
    public void save(SplashPageUserSeen splashPageUserSeen) {
        splashPageUserSeenDao.save(splashPageUserSeen);
    }

    @Override
    public void delete(Long id) {
        SplashPage splashPage = get(id);
        if (splashPage != null) {
            splashPageDao.delete(splashPage);
        }
    }

    @Override
    public List<SplashPage> getAll() {

        List<String> unitCodes = null;

        if (userManager.getLoggedInUserRole().equals("unitadmin")) {
            unitCodes = unitManager.getUsersUnitCodes(userManager.getLoggedInUser());
        }

        return splashPageDao.getAll(unitCodes, securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List<SplashPage> getAllForPatient(User user) {

        List<String> unitCodes = unitManager.getUsersUnitCodes(user);
        unitCodes.add("ALL");

        return splashPageDao.getAll(unitCodes, securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List<SplashPageUserSeen> getSeenForPatient(User user) {
        return splashPageUserSeenDao.getSeenForPatient(user);
    }

    @Override
    public void removeSeenSplashPage(Long id) {
        splashPageUserSeenDao.delete(id);
    }
}
