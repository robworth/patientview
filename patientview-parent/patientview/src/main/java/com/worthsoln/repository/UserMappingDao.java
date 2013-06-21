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

package com.worthsoln.repository;

import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.patientview.model.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface UserMappingDao {

    UserMapping get(Long id);

    void save(UserMapping userMapping);

    void deleteUserMappings(String username, String unitcode, Specialty specialty);

    List<UserMapping> getAll(String username, Specialty specialty);

    List<UserMapping> getAllExcludeUnitcode(String username, String unitcode, Specialty specialty);

    List<UserMapping> getAll(String username, String unitcode, Specialty specialty);

    List<UserMapping> getAllForNhsNo(String nhsNo, Specialty specialty);

    String getUsersRealUnitcodeBestGuess(String username, Specialty specialty);

    String getUsersRealNhsNoBestGuess(String username, Specialty specialty);

    UserMapping getUserMappingPatientEntered(User user, Specialty specialty);

    List<UserMapping> getUsersSiblings(String username, String unitcode, Specialty specialty);

    List<UserMapping> getDuplicateUsers(String nhsno, String username, Specialty specialty);
}