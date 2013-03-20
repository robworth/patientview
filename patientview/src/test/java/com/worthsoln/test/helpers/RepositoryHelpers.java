package com.worthsoln.test.helpers;

import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.patientview.model.SpecialtyUserRole;
import com.worthsoln.patientview.model.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface RepositoryHelpers {

    User createUser(String username, String email, String password, String name, String screenName);

    User createUserWithMapping(String username, String email, String password, String name, String screenName,
                               String unitcode, String nhsno, Specialty specialty);

    Specialty createSpecialty(String name, String context, String description);

    SpecialtyUserRole createSpecialtyUserRole(Specialty specialty, User user, String role);

}
