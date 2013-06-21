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

package com.worthsoln.service;

import com.worthsoln.patientview.logon.PatientLogon;
import com.worthsoln.patientview.logon.UnitAdmin;
import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.patientview.model.SpecialtyUserRole;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.UserMapping;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)
public interface UserManager {

    User getLoggedInUser();

    User get(Long id);

    User get(String username);

    String getLoggedInUserRole();

    Specialty getCurrentSpecialty(User user);

    String getCurrentSpecialtyRole(User user);

    List<SpecialtyUserRole> getSpecialtyUserRoles(User user);

    void save(User user);

    User saveUserFromUnitAdmin(UnitAdmin unitAdmin, String unitcode);

    User saveUserFromPatient(PatientLogon patientLogon);

    void delete(String username, String unitcode);

    List<User> getAllUsers();

    void save(UserMapping userMapping);

    void deleteUserMappings(String username, String unitcode);

    List<UserMapping> getUserMappings(String username);

    List<UserMapping> getUserMappingsExcludeUnitcode(String username, String unitcode);

    List<UserMapping> getUserMappings(String username, String unitcode);

    List<UserMapping> getUserMappingsForNhsNo(String nhsNo);

    String getUsersRealUnitcodeBestGuess(String username);

    String getUsersRealNhsNoBestGuess(String username);

    UserMapping getUserMappingPatientEntered(User user);

    List<UserMapping> getUsersSiblings(String username, String unitcode);

    List<UserMapping> getDuplicateUsers(String nhsno, String username);

    boolean patientExistsInRadar(String nhsno);

    void incrementFailedLogins(String username);

    int getFailedLogins(String username);

    void lockUserAccount(String username);

    void resetFailedLoginsForUser(String username);

    boolean userExistsInRadar(Long userId);

    void createProfessionalUserInRadar(User user, String unitcode);

    void removeUserFromRadar(Long userId);

    List<User> getUsers(User user, Specialty specialty, String userType, Unit unit);
}
