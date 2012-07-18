package com.worthsoln.repository;

import com.worthsoln.patientview.logon.UserMapping;
import com.worthsoln.patientview.model.User;

import java.util.List;

/**
 *
 */
public interface UserMappingDao {

    List<UserMapping> getAll(User user);

    List<UserMapping> getAllExcludeUnitcode(String username, String unitcode);

    List<UserMapping> getAll(String username, String unitcode);

    List<UserMapping> getAllForNhsNo(String nhsNo);

    String getUsersRealUnitcodeBestGuess(String username);

    String getUsersRealNhsNoBestGuess(String username);

    UserMapping getUserMappingPatientEntered(User user);
}
