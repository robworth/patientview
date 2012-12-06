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
