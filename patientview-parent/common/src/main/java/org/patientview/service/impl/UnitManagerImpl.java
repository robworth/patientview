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

import org.patientview.patientview.logon.UnitAdmin;
import org.patientview.model.patientview.PatientCount;
import org.patientview.model.patientview.Specialty;
import org.patientview.model.patientview.Unit;
import org.patientview.model.patientview.UnitStat;
import org.patientview.model.patientview.User;
import org.patientview.model.patientview.UserMapping;
import org.patientview.repository.radar.PatientCountDao;
import org.patientview.repository.radar.UnitDao;
import org.patientview.repository.radar.UnitStatDao;
import org.patientview.service.LogEntryManager;
import org.patientview.service.SecurityUserManager;
import org.patientview.service.UnitManager;
import org.patientview.service.UserManager;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service(value = "unitManager")
@Lazy
public class UnitManagerImpl implements UnitManager {

    @Inject
    private PatientCountDao patientCountDao;

    @Inject
    private SecurityUserManager securityUserManager;

    @Inject
    private UnitDao unitDao;

    @Inject
    private UnitStatDao unitStatDao;

    @Inject
    private UserManager userManager;


    @Override
    public Unit get(Long id) {
        return unitDao.get(id);
    }

    @Override
    public Unit get(String unitCode) {
        return unitDao.get(unitCode, securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public void save(Unit unit) {

        // set the Specialty against the unit if not already set
        if (unit.getSpecialty() == null) {
            unit.setSpecialty(securityUserManager.getLoggedInSpecialty());
        }

        unitDao.save(unit);
    }

    @Override
    public List<Unit> getAllDisregardingSpeciality(boolean sortByName) {
        return unitDao.getAll(true);
    }

    @Override
    public List<Unit> getAll(boolean sortByName) {
        return unitDao.getAll(true, securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List<Unit> getAll(String[] sourceTypesToExclude, String[] sourceTypesToInclude) {
        return unitDao.getAll(sourceTypesToExclude, sourceTypesToInclude);
    }

    @Override
    public List<Unit> getUnitsWithUser() {
        return unitDao.getUnitsWithUser(securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List<Unit> getAdminsUnits() {
        return unitDao.getAdminsUnits(securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List<Unit> getAdminsUnits(boolean isRadarGroup) {
        return unitDao.getAdminsUnits(securityUserManager.getLoggedInSpecialty(), isRadarGroup);
    }

    @Override
    public List<Unit> getLoggedInUsersUnits() {
        return getUsersUnits(userManager.getLoggedInUser());
    }

    @Override
    public List<Unit> getUsersUnits(User user) {
        List<String> usersUnitCodes = getUsersUnitCodes(user);
        return unitDao.get(usersUnitCodes, securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List<Unit> getLoggedInUsersUnits(String[] notTheseUnitCodes, String[] plusTheseUnitCodes) {
        User user = userManager.getLoggedInUser();

        if (userManager.getCurrentSpecialtyRole(user).equals("superadmin")) {
            return getAdminsUnits();
        }

        List<String> usersUnitCodes = getUsersUnitCodes(user);
        return unitDao.get(usersUnitCodes, notTheseUnitCodes, plusTheseUnitCodes,
                securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List<String> getUsersUnitCodes(User user) {
        List<String> unitCodes = new ArrayList<String>();

        if (user != null && !"superadmin".equals(userManager.getCurrentSpecialtyRole(user))) {

            List<UserMapping> userMappings = userManager.getUserMappings(user.getUsername());

            for (UserMapping userMapping : userMappings) {
                unitCodes.add(userMapping.getUnitcode());
            }
        }

        return unitCodes;
    }

    @Override
    public List<UnitStat> getPatientCountsForUnit(String unitCode) {

        List<PatientCount> patientCounts = patientCountDao.get(unitCode, "patient");

        List<UnitStat> patientCountStats = new ArrayList<UnitStat>();
        for (PatientCount patientCount : patientCounts) {
            String yearmonth = patientCount.getYearmonth();
            int count = patientCount.getCount();
            patientCountStats.add(new UnitStat(unitCode, yearmonth, LogEntryManager.PATIENT_COUNT, count));
        }

        return patientCountStats;
    }

    @Override
    public List<UnitStat> getUnitStatsForUnit(String unitCode) {
        return unitStatDao.get(unitCode);
    }

    @Override
    public List<UnitAdmin> getUnitUsers(String unitcode) {
        return unitDao.getUnitUsers(unitcode, securityUserManager.getLoggedInSpecialty());
    }

    public List<UnitAdmin> getAllUnitUsers(Boolean isRadarGroup) {
        return unitDao.getAllUnitUsers(isRadarGroup, securityUserManager.getLoggedInSpecialty());
    }

    public List<UnitAdmin> getAllUnitUsers() {
        return unitDao.getAllUnitUsers(null, securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List<User> getUnitPatientUsers(String unitcode, Specialty specialty) {
        return unitDao.getUnitPatientUsers(unitcode, securityUserManager.getLoggedInSpecialty());
    }
}
