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

package org.patientview.radar.dao;

import org.patientview.radar.exception.UserCreationException;
import org.patientview.radar.exception.UserMappingException;
import org.patientview.radar.exception.UserRoleException;
import org.patientview.radar.model.filter.PatientUserFilter;
import org.patientview.radar.model.filter.ProfessionalUserFilter;
import org.patientview.radar.model.user.AdminUser;
import org.patientview.radar.model.user.PatientUser;
import org.patientview.radar.model.user.ProfessionalUser;
import org.patientview.radar.model.user.User;
import org.patientview.radar.model.user.UserMapping;

import java.util.List;

public interface UserDao {

    public <T extends User> T getUser(String email);

    AdminUser getAdminUser(String email);

    AdminUser getAdminUserWithUsername(String username);

    AdminUser getAdminUser(Long id);

    List<AdminUser> getAdminUsers();

    void saveAdminUser(AdminUser adminUser) throws Exception;

    PatientUser getPatientUser(Long id);

    PatientUser getPatientUser(String email);

    PatientUser getPatientUserWithUsername(String username);

    PatientUser getPatientUserByRadarNo(Long radarNo);

    User createUser(User user);

    Long createLockedPVUser(String username, String password, String firstName, String lastName, String email)
            throws Exception;

    PatientUser getPatientViewUser(String nhsno);

    List<PatientUser> getPatientUsers(PatientUserFilter filter, int page, int numberPerPage);

    void savePatientUser(PatientUser patientUser) throws UserCreationException;

    void deletePatientUser(PatientUser patientUser) throws Exception;

    ProfessionalUser getProfessionalUser(Long id);

    ProfessionalUser getProfessionalUser(String email);

    ProfessionalUser getProfessionalUserWithUsername(String username);

    User getSuperUserWithUsername(String username);

    void saveProfessionalUser(ProfessionalUser professionalUser) throws Exception;

    void deleteProfessionalUser(ProfessionalUser professionalUser) throws Exception;

    List<ProfessionalUser> getProfessionalUsers(ProfessionalUserFilter filter, int page, int numberPerPage);

    boolean userExistsInPatientView(String nhsno);

    List<String> getPatientRadarMappings(String nhsNo);

    void createUserMappingAndRoleInPatientView(Long userId, String username, String nhsno, String unitcode,
                                               String rpvRole) throws Exception;

    void createUserMappingInPatientView(String username, String nhsno, String unitcode) throws Exception;

    void deleteUserMappingInPatientView(String username) throws Exception;

    void createRoleInPatientView(Long userId, String rpvRole) throws UserRoleException;

    void deleteRoleInPatientView(Long userId) throws Exception;

    void saveUserMapping(User user) throws UserMappingException;

    void deleteUserMapping(User user) throws Exception;

    boolean userExistsInPatientView(String nhsno, String unitcode);

    boolean usernameExistsInPatientView(String username);

    public void deleteUser(User user) throws Exception;

    UserMapping getUserMapping(Long userId, Long radarUserId, String role);

    ProfessionalUser getProfessionalUserByUsername(String username);
}
