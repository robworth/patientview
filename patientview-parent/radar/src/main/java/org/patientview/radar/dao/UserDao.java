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

    List<String> getUnitCodes(User user);

    List<String> getAllUnitCodes();

    Long createLockedPVUser(String username, String password, String name, String email) throws Exception;

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
