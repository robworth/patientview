package com.worthsoln.service;

import com.worthsoln.patientview.logon.UserMapping;
import com.worthsoln.patientview.model.User;

import java.util.List;

/**
 *
 */
public interface UserManager {

    User getLoggedInUser();

    User get(Long id);

    User get(String username);

    void save(User user);

    void delete(User user);

    void delete(String username);

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
}
