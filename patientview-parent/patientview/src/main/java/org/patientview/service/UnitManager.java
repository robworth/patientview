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

import org.patientview.patientview.logon.UnitAdmin;
import org.patientview.model.Specialty;
import org.patientview.model.Unit;
import org.patientview.patientview.model.UnitStat;
import org.patientview.patientview.model.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
@Secured(value = { "ROLE_ANY_USER" })
public interface UnitManager {

    Unit get(Long id);

    /**
     * It is planned to have this method secured so that only users with mapping to this unit
     * can access the unit, using: @UnitSecured(value = "UNIT_ACCESS")
     *
     * It should be possible to turn this on now, but there may well still be a couple of bugs in there.
     * @param unitCode
     * @return
     */
    Unit get(String unitCode);

    /**
     * a new unitcode doesn't exist, so any user doesn't have SecurityConfig.UNIT_ACCESS permission to check this code
     * @param unitCode String
     * @return true if duplicate
     */
    boolean checkDuplicateUnitCode(String unitCode);

    void save(Unit unit);

    List<Unit> getAllDisregardingSpeciality(boolean sortByName);

    List<Unit> getAll(boolean sortByName);

    List<Unit> getAll(String[] sourceTypesToExclude, String[] sourceTypesToInclude);

    List<Unit> getAdminsUnits();

    List<Unit> getAdminsUnits(boolean isRadarGroup);

    List<Unit> getUnitsWithUser();

    List<Unit> getLoggedInUsersUnits();

    List<Unit> getUsersUnits(User user);

    List<Unit> getLoggedInUsersUnits(String[] notTheseUnitCodes, String[] plusTheseUnitCodes);

    List<String> getUsersUnitCodes(User user);

    List<UnitStat> getPatientCountsForUnit(String unitCode);

    List<UnitStat> getUnitStatsForUnit(String unitCode);

    List<UnitAdmin> getUnitUsers(String unitcode);

    @Secured(value =  "ROLE_RENAL_SUPERADMIN")
    List<UnitAdmin> getAllUnitUsers();

    List<User> getUnitPatientUsers(String unitcode, Specialty specialty);
}
