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

import org.patientview.patientview.model.Specialty;
import org.patientview.patientview.model.SpecialtyUserRole;
import org.patientview.patientview.model.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  Access the spring security user details
 *
 *  This manager also contains unsecured UserManager methods.
 */
@Transactional(propagation = Propagation.REQUIRED)
public interface SecurityUserManager {

    String getLoggedInUsername();

    String getLoggedInEmailAddress();

    boolean isFirstLogon();

    boolean isEmailVerified();

    Specialty getLoggedInSpecialty();

    boolean isLoggedIn();

    boolean isLoggedInToSpecialty();

    boolean isSpecialtyPresent(String context);

    boolean isRolePresent(String... roles);

    void setLoggedInSpecialty(Long specialtyId) throws Exception;

    boolean hasAccessToSpecialty(User user, Specialty specialty);

    boolean userHasReadAccessToUnitUser(String username);

    User get(String username);

    List<SpecialtyUserRole> getSpecialtyUserRoles(User user);

    boolean userHasReadAccessToUnit(String unitCode);

    void incrementFailedLogins(String username);

    int getFailedLogins(String username);

    void lockUserAccount(String username);

    void resetFailedLoginsForUser(String username);
}
