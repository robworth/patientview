package com.worthsoln.service.impl;

import com.worthsoln.patientview.logon.PatientLogon;
import com.worthsoln.patientview.logon.UnitAdmin;
import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.patientview.model.SpecialtyUserRole;
import com.worthsoln.patientview.model.Demographics;
import com.worthsoln.patientview.model.PatientUser;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.SpecialtyUserRoleDao;
import com.worthsoln.repository.DemographicsDao;
import com.worthsoln.repository.PatientUserDao;
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
    private SpecialtyUserRoleDao specialtyUserRoleDao;

    @Inject
    private DemographicsDao demographicsDao;

    @Inject
    private PatientUserDao patientUserDao;

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

        // get role from spring user for this logged in specialty
        return getCurrentSpecialtyRole(getLoggedInUser());
    }

    @Override
    public Specialty getCurrentSpecialty(User user) {

        SpecialtyUserRole specialtyUserRole = getCurrentSpecialtyUserRole(user);

        if (specialtyUserRole != null) {
            return specialtyUserRole.getSpecialty();
        } else {
            return null;
        }
    }

    @Override
    public String getCurrentSpecialtyRole(User user) {

        SpecialtyUserRole specialtyUserRole = getCurrentSpecialtyUserRole(user);

        if (specialtyUserRole != null) {
            return specialtyUserRole.getRole();
        } else {
            return null;
        }
    }

    private SpecialtyUserRole getCurrentSpecialtyUserRole(User user) {

        // get role from spring user for this logged in Specialty
        if (user != null) {
            List<SpecialtyUserRole> specialtyUserRoles = getSpecialtyUserRoles(user);
            Specialty loggedInSpecialty = securityUserManager.getLoggedInSpecialty();

            if (loggedInSpecialty != null) {
                for (SpecialtyUserRole specialtyUserRole : specialtyUserRoles) {

                    if (specialtyUserRole.getSpecialty().equals(loggedInSpecialty)) {
                        return specialtyUserRole;
                    }
                }
            }
        }

        return null;
    }

    @Override
    public List<SpecialtyUserRole> getSpecialtyUserRoles(User user) {
        return specialtyUserRoleDao.get(user);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User saveUserFromUnitAdmin(UnitAdmin unitAdmin) {

        // check for an existing user
        User user = get(unitAdmin.getUsername());

        if (user == null) {
            // create a user to save based on the unitAdmin
            user = new User();
        }
        user.setAccountlocked(unitAdmin.isAccountlocked());
        user.setDummypatient(unitAdmin.isDummypatient());
        user.setEmail(unitAdmin.getEmail());
        user.setEmailverified(unitAdmin.isEmailverfied());
        user.setFailedlogons(unitAdmin.getFailedlogons());
        user.setFirstlogon(unitAdmin.isFirstlogon());
        user.setLastlogon(unitAdmin.getLastlogon());
        user.setName(unitAdmin.getName());
        user.setPassword(unitAdmin.getPassword());
        user.setScreenname(unitAdmin.getScreenname());
        user.setUsername(unitAdmin.getUsername());

        save(user);

        addEditUserSpecialtyRole(user, unitAdmin.getRole());

        return user;
    }



    @Override
    public User saveUserFromPatient(PatientLogon patientLogon) {

        // check for an existing user
        User user = get(patientLogon.getUsername());

        if (user == null) {
            // create a user to save based on the unitAdmin
            user = new User();
        }
        user.setAccountlocked(patientLogon.isAccountlocked());
        user.setDummypatient(patientLogon.isDummypatient());
        user.setEmail(patientLogon.getEmail());
        user.setEmailverified(patientLogon.isEmailverfied());
        user.setFailedlogons(patientLogon.getFailedlogons());
        user.setFirstlogon(patientLogon.isFirstlogon());
        user.setLastlogon(patientLogon.getLastlogon());
        user.setName(patientLogon.getName());
        user.setPassword(patientLogon.getPassword());
        user.setScreenname(patientLogon.getScreenname());
        user.setUsername(patientLogon.getUsername());

        save(user);

        addEditUserSpecialtyRole(user, patientLogon.getRole());

        return user;
    }

    // handle the permissions for the Specialty
    private void addEditUserSpecialtyRole(User user, String role) {

        SpecialtyUserRole specialtyUserRole = getCurrentSpecialtyUserRole(user);

        if (specialtyUserRole == null) {
            // associate new user with the current Specialty and role
            specialtyUserRole = new SpecialtyUserRole();
        }

        // this is always updating the specialtyUserRole - shouldn't be an issue
        specialtyUserRole.setUser(user);
        specialtyUserRole.setRole(role);
        specialtyUserRole.setSpecialty(securityUserManager.getLoggedInSpecialty());
        specialtyUserRoleDao.save(specialtyUserRole);
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

        if (userMapping.getSpecialty() == null) {
            userMapping.setSpecialty(securityUserManager.getLoggedInSpecialty());
        }

        userMappingDao.save(userMapping);
    }

    @Override
    public void deleteUserMappings(String username, String unitcode) {
        userMappingDao.deleteUserMappings(username, unitcode, securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List<UserMapping> getUserMappings(String username) {
        return userMappingDao.getAll(username, securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List<UserMapping> getUserMappingsExcludeUnitcode(String username, String unitcode) {
        return userMappingDao.getAllExcludeUnitcode(username, unitcode, securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List<UserMapping> getUserMappings(String username, String unitcode) {
        return userMappingDao.getAll(username, unitcode, securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List<UserMapping> getUserMappingsForNhsNo(String nhsNo) {
        return userMappingDao.getAllForNhsNo(nhsNo, securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public String getUsersRealUnitcodeBestGuess(String username) {
        return userMappingDao.getUsersRealUnitcodeBestGuess(username, securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public String getUsersRealNhsNoBestGuess(String username) {
        return userMappingDao.getUsersRealNhsNoBestGuess(username, securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public UserMapping getUserMappingPatientEntered(User user) {
        return userMappingDao.getUserMappingPatientEntered(user, securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List<UserMapping> getUsersSiblings(String username, String unitcode) {
        return userMappingDao.getUsersSiblings(username, unitcode, securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List<UserMapping> getDuplicateUsers(String nhsno, String username) {
        return userMappingDao.getDuplicateUsers(nhsno, username, securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List<UnitAdmin> getUnitUsers(String unitcode) {
        return userDao.getUnitUsers(unitcode, securityUserManager.getLoggedInSpecialty());
        return userDao.getUnitUsers(unitcode, securityUserManager.getLoggedInTenancy());
    }

    @Override
    public boolean existsInRadar(String nhsno) {
        Demographics demographics = demographicsDao.getDemographicsByNhsNo(nhsno);

        if (demographics != null) {
            PatientUser patientUser = patientUserDao.getPatientUserByRadarNo(demographics.getRadarNo());

            return patientUser != null;
        }

        return false;
    }
}
