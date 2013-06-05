package com.worthsoln.service.impl;

import com.worthsoln.patientview.logon.PatientLogon;
import com.worthsoln.patientview.logon.UnitAdmin;
import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.patientview.model.SpecialtyUserRole;
import com.worthsoln.patientview.model.radar.Demographics;
import com.worthsoln.patientview.model.PatientUser;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.unit.UnitUtils;
import com.worthsoln.patientview.user.UserUtils;
import com.worthsoln.repository.RadarDao;
import com.worthsoln.repository.SpecialtyUserRoleDao;
import com.worthsoln.repository.PatientUserDao;
import com.worthsoln.repository.UnitDao;
import com.worthsoln.repository.UserDao;
import com.worthsoln.repository.UserMappingDao;
import com.worthsoln.service.SecurityUserManager;
import com.worthsoln.service.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(value = "userManager")
public class UserManagerImpl implements UserManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserManagerImpl.class);

    @Inject
    private UserDao userDao;

    @Inject
    private UserMappingDao userMappingDao;

    @Inject
    private SecurityUserManager securityUserManager;

    @Inject
    private SpecialtyUserRoleDao specialtyUserRoleDao;

    @Inject
    private RadarDao radarDao;

    @Inject
    private PatientUserDao patientUserDao;

    @Inject
    private UnitDao unitDao;

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
    public User saveUserFromUnitAdmin(UnitAdmin unitAdmin, String unitcode) {

        // check for an existing user
        User user = get(unitAdmin.getUsername());

        final boolean isNewUser = user == null;

        if (isNewUser) {
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
        user.setUsername(unitAdmin.getUsername());
        user.setIsrecipient(unitAdmin.isIsrecipient());
        user.setIsclinician(unitAdmin.isIsclinician());

        save(user);

        addEditUserSpecialtyRole(user, unitAdmin.getRole());

        if (isNewUser) {

            UserMapping userMapping = new UserMapping(user.getUsername(), unitcode, "");
            save(userMapping);

            // create mappings in radar if they don't already exist
            if (!userExistsInRadar(user.getId())) {
                createProfessionalUserInRadar(user, unitcode);
            }
        }

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

    private void delete(User user, String unitcode) {

        List<UserMapping> userMappings = getUserMappingsExcludeUnitcode(user.getUsername(),
                UnitUtils.PATIENT_ENTERS_UNITCODE);

        if (userMappings.size() == 1) {

            // this is a user that exists in only one unit -> full delete

            specialtyUserRoleDao.delete(getCurrentSpecialtyUserRole(user));

            deleteUserMappings(user.getUsername(), unitcode); // deletes from usermapping table
            deleteUserMappings(user.getUsername() + "-GP", unitcode);
            deleteUserMappings(user.getUsername(), UnitUtils.PATIENT_ENTERS_UNITCODE);
            userDao.delete(user); // deletes from user table

            User gpUser = get(user.getUsername() + "-GP");
            if (gpUser != null) {
                userDao.delete(gpUser);
            }

            // patients get all their records deleted
            if ("patient".equals(getCurrentSpecialtyRole(user))) {
                UserUtils.removePatientFromSystem(user.getUsername(), unitcode);
            }

            // remove the user from radar as well
            radarDao.removeUserFromRadar(user.getId());

        } else {

            // this is a user that exists in multiple units -> just remove their unit access/mapping

            deleteUserMappings(user.getUsername(), unitcode);
            deleteUserMappings(user.getUsername() + "-GP", unitcode);
        }
    }

    @Override
    public void delete(String username, String unitcode) {

        User user = get(username);

        if (user != null) {
            try {
                delete(user, unitcode);
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
    public boolean patientExistsInRadar(String nhsno) {
        Demographics demographics = radarDao.getDemographicsByNhsNo(nhsno);

        if (demographics != null) {
            PatientUser patientUser = patientUserDao.getPatientUserByRadarNo(demographics.getRadarNo());

            return patientUser != null;
        }

        return false;
    }

    @Override
    public void incrementFailedLogins(String username) {
        User user = userDao.get(username);

        if (user != null) {
            user.setFailedlogons(user.getFailedlogons() + 1);
            userDao.save(user);
        }
    }

    @Override
     public int getFailedLogins(String username) {
         User user = userDao.get(username);

         if (user != null) {
             return user.getFailedlogons();
         }

         return 0;
     }

    @Override
     public void lockUserAccount(String username) {
        User user = userDao.get(username);

        if (user != null) {
            user.setAccountlocked(true);
            userDao.save(user);
        }
     }

    @Override
    public void resetFailedLoginsForUser(String username) {
        User user = userDao.get(username);

        if (user != null) {
            user.setFailedlogons(0);
            userDao.save(user);
        }
    }

    @Override
    public boolean userExistsInRadar(Long userId) {
        return radarDao.userExistsInRadar(userId);
    }

    @Override
    public void createProfessionalUserInRadar(User user, String unitcode) {
        // check to see if this user has an account already in radar and if they dont create one
        Unit unit = unitDao.get(unitcode, null);

        if (unit != null) {
            radarDao.createProfessionalUserInRadar(user, unit);
        } else {
            LOGGER.error("Could not create admin user in radar " + user.getId());
        }
    }

    @Override
    public void removeUserFromRadar(Long userId) {
        radarDao.removeUserFromRadar(userId);
    }

    @Override
    public List<User> getUsers(User user, Specialty specialty, String userType, Unit unit) {

        return userDao.get(user, specialty, userType, unit);
    }
}
