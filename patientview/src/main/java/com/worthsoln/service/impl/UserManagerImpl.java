package com.worthsoln.service.impl;

import com.worthsoln.patientview.logon.UserMapping;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.UserDao;
import com.worthsoln.repository.UserMappingDao;
import com.worthsoln.service.SecurityUserManager;
import com.worthsoln.service.UserManager;

import javax.inject.Inject;
import java.util.List;

/**
 *
 */
public class UserManagerImpl implements UserManager {

    @Inject
    private UserDao userDao;

    @Inject
    private UserMappingDao userMappingDao;

    @Inject
    private SecurityUserManager securityUserManager;

    @Override
    public User getLoggedInUser() {
        return userDao.get(securityUserManager.getLoggedInUsername());
    }

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }

    @Override
    public User get(String username) {
        return userDao.get(username);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public List<UserMapping> getUserMappings(User user) {
        return userMappingDao.getAll(user);
    }

    @Override
    public List<UserMapping> getUserMappingsExcludeUnitcode(String username, String unitcode) {
        return userMappingDao.getAllExcludeUnitcode(username, unitcode);
    }

    @Override
    public List<UserMapping> getUserMappings(String username, String unitcode) {
        return userMappingDao.getAll(username, unitcode);
    }

    @Override
    public List<UserMapping> getUserMappingsForNhsNo(String nhsNo) {
        return userMappingDao.getAllForNhsNo(nhsNo);
    }

    @Override
    public String getUsersRealUnitcodeBestGuess(String username) {
        return userMappingDao.getUsersRealUnitcodeBestGuess(username);
    }

    @Override
    public String getUsersRealNhsNoBestGuess(String username) {
        return userMappingDao.getUsersRealNhsNoBestGuess(username);
    }
}
