package com.worthsoln.test.helpers.impl;

import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.patientview.model.SpecialtyUserRole;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.repository.SpecialtyDao;
import com.worthsoln.repository.SpecialtyUserRoleDao;
import com.worthsoln.repository.UserDao;
import com.worthsoln.repository.UserMappingDao;
import com.worthsoln.test.helpers.RepositoryHelpers;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

/**
 *
 */
@Repository(value = "repositoryHelpers")
public class RepositoryHelpersImpl implements RepositoryHelpers {

    @Inject
    private SpecialtyDao specialtyDao;

    @Inject
    private UserDao userDao;

    @Inject
    private UserMappingDao userMappingDao;

    @Inject
    private SpecialtyUserRoleDao specialtyUserRoleDao;

    @Override
    public User createUser(String username, String email, String password, String name, String screenName) {

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setUsername(username);
        user.setScreenname(screenName);
        userDao.save(user);

        return user;
    }

    @Override
    public User createUserWithMapping(String username, String email, String password, String name, String screenName,
                                      String unitcode, String nhsno, Specialty specialty) {

        User user = createUser(username, email, password, name, screenName);

        UserMapping userMapping = new UserMapping();
        userMapping.setSpecialty(specialty);
        userMapping.setNhsno(nhsno);
        userMapping.setUnitcode(unitcode);
        userMapping.setUsername(username);

        userMappingDao.save(userMapping);

        return user;
    }

    @Override
    public Specialty createSpecialty(String name, String context, String description) {

        Specialty specialty = new Specialty();
        specialty.setName(name);
        specialty.setContext(context);
        specialty.setDescription(description);

        specialtyDao.save(specialty);

        return specialty;
    }

    @Override
    public SpecialtyUserRole createSpecialtyUserRole(Specialty specialty, User user, String role) {

        SpecialtyUserRole specialtyUserRole1 = new SpecialtyUserRole();
        specialtyUserRole1.setRole(role);
        specialtyUserRole1.setSpecialty(specialty);
        specialtyUserRole1.setUser(user);
        specialtyUserRoleDao.save(specialtyUserRole1);

        return specialtyUserRole1;
    }
}
