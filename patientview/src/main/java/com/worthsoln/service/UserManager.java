package com.worthsoln.service;

import com.worthsoln.patientview.logon.PatientLogon;
import com.worthsoln.patientview.logon.UnitAdmin;
import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.patientview.model.SpecialtyUserRole;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.patientview.model.User;
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

    List<User> getUsers(User user, Specialty specialty, String userType);
}
