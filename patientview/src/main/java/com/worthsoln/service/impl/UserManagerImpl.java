package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.Tenancy;
import com.worthsoln.patientview.model.TenancyUserRole;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.TenancyUserRoleDao;
import com.worthsoln.repository.UserDao;
import com.worthsoln.repository.UserMappingDao;
import com.worthsoln.service.SecurityUserManager;
import com.worthsoln.service.UserManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 *
 */
@Service(value = "userManager")
public class UserManagerImpl implements UserManager {

    @Inject
    private UserDao userDao;

    @Inject
    private UserMappingDao userMappingDao;

    @Inject
    private SecurityUserManager securityUserManager;

    @Inject
    private TenancyUserRoleDao tenancyUserRoleDao;

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
    public String getLoggedInUserRole() {

        // todo get role from spring user for this logged in tenancy - should be in securityUserManager

        return "patient";  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getCurrentTenancyRole(User user) {

        // todo get role for this user for logged in tenancy - should be in securityUserManager

        return "patient";  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<TenancyUserRole> getTenancyUserRoles(User user) {
        return tenancyUserRoleDao.get(user);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public void delete(String username) {

        User user = get(username);

        if (user != null) {
            try {
                delete(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public void save(UserMapping userMapping) {
        userMappingDao.save(userMapping);
    }

    @Override
    public void deleteUserMappings(String username, String unitcode) {
        userMappingDao.deleteUserMappings(username, unitcode);
    }

    @Override
    public List<UserMapping> getUserMappings(String username) {
        return userMappingDao.getAll(username);
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

    @Override
    public UserMapping getUserMappingPatientEntered(User user) {
        return userMappingDao.getUserMappingPatientEntered(user);
    }

    @Override
    public List<UserMapping> getUsersSiblings(String username, String unitcode) {
        return userMappingDao.getUsersSiblings(username, unitcode);
    }

    @Override
    public List<UserMapping> getDuplicateUsers(String nhsno, String username) {
        return userMappingDao.getDuplicateUsers(nhsno, username);
    }
}
