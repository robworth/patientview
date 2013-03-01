package com.worthsoln.repository;

import com.worthsoln.patientview.model.Tenancy;
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

    void deleteUserMappings(String username, String unitcode, Tenancy tenancy);

    List<UserMapping> getAll(String username, Tenancy tenancy);

    List<UserMapping> getAllExcludeUnitcode(String username, String unitcode, Tenancy tenancy);

    List<UserMapping> getAll(String username, String unitcode, Tenancy tenancy);

    List<UserMapping> getAllForNhsNo(String nhsNo, Tenancy tenancy);

    String getUsersRealUnitcodeBestGuess(String username, Tenancy tenancy);

    String getUsersRealNhsNoBestGuess(String username, Tenancy tenancy);

    UserMapping getUserMappingPatientEntered(User user, Tenancy tenancy);

    List<UserMapping> getUsersSiblings(String username, String unitcode, Tenancy tenancy);

    List<UserMapping> getDuplicateUsers(String nhsno, String username, Tenancy tenancy);
}
