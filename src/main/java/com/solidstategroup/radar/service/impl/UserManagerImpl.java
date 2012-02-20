package com.solidstategroup.radar.service.impl;

import com.solidstategroup.radar.dao.DemographicsDao;
import com.solidstategroup.radar.dao.UserDao;
import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.model.exception.DaoException;import com.solidstategroup.radar.model.exception.DecryptionException;
import com.solidstategroup.radar.model.exception.EmailAddressNotFoundException;
import com.solidstategroup.radar.model.exception.UserEmailAlreadyExists;
import com.solidstategroup.radar.model.filter.ProfessionalUserFilter;
import com.solidstategroup.radar.model.filter.PatientUserFilter;
import com.solidstategroup.radar.model.exception.RegistrationException;
import com.solidstategroup.radar.model.user.AdminUser;
import com.solidstategroup.radar.model.user.PatientUser;
import com.solidstategroup.radar.model.user.ProfessionalUser;
import com.solidstategroup.radar.model.user.User;
import com.solidstategroup.radar.service.EmailManager;
import com.solidstategroup.radar.service.UserManager;
import com.solidstategroup.radar.util.TripleDes;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Date;
import java.util.List;

public class UserManagerImpl implements UserManager, UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserManagerImpl.class);

    private EmailManager emailManager;
    private ProviderManager authenticationManager;

    private DemographicsDao demographicsDao;
    private UserDao userDao;

    public AdminUser getAdminUser(String email) {
        return userDao.getAdminUser(email);
    }

    public PatientUser getPatientUser(Long id) {
        return userDao.getPatientUser(id);
    }

    public PatientUser getPatientUser(String email) {
        return userDao.getPatientUser(email);
    }

    public PatientUser getPatientUser(String email, Date dateOfBirth) {
        PatientUser user = userDao.getPatientUser(email);
        if (user != null) {
            return user.getDateOfBirth().equals(dateOfBirth) ? user : null;
        }
        return null;
    }

    public List<PatientUser> getPatientUsers() {
        return getPatientUsers(new PatientUserFilter(), -1, -1);
    }

    public List<PatientUser> getPatientUsers(PatientUserFilter filter) {
        return getPatientUsers(filter, -1, -1);
    }

    public List<PatientUser> getPatientUsers(PatientUserFilter filter, int page, int numberPerPage) {
        return userDao.getPatientUsers(filter, page, numberPerPage);
    }

    public void savePatientUser(PatientUser patientUser) throws Exception {
        // if the password prop set then encrypt it
        if (patientUser.getPassword() != null && patientUser.getPassword().length() > 0) {
            patientUser.setPasswordHash(PatientUser.getPasswordHash(patientUser.getPassword()));
        }

        userDao.savePatientUser(patientUser);
    }

    public void deletePatientUser(PatientUser patientUser) throws Exception {
        userDao.deletePatientUser(patientUser);
    }

    public void registerPatient(PatientUser patientUser) throws RegistrationException, UserEmailAlreadyExists {
        // Check we have a valid radar number, email address and date of birth
        if (patientUser == null ||
                patientUser.getRadarNumber() <= 0L ||
                StringUtils.isBlank(patientUser.getUsername()) ||
                patientUser.getDateOfBirth() == null) {
            throw new IllegalArgumentException("Must supply a non null patient user " +
                    "with valid radar number, username and date of birth for registration");
        }

        PatientUser dupliatePatientUser = getPatientUser(patientUser.getUsername());
        if(dupliatePatientUser != null) {
            throw new UserEmailAlreadyExists("User email already exists");
        }

        // First we need to try and get a demographics user with the supplied radar number
        Demographics demographics = demographicsDao.getDemographicsByRadarNumber(patientUser.getRadarNumber());

        // If we get a demographic check the date of birth matches
        if (demographics != null && demographics.getDateOfBirth() != null
                && demographics.getDateOfBirth().equals(patientUser.getDateOfBirth())) {

            // Generate the password - 8 random characters
            String password = generateRandomPassword();
            try {
                patientUser.setPasswordHash(User.getPasswordHash(password));

                // Save the patient user to the patient user table
                userDao.savePatientUser(patientUser);

                // Send the registration email to the user
                emailManager.sendPatientRegistrationEmail(patientUser, password);

                // Send the registration email to the admin
                emailManager.sendPatientRegistrationAdminNotificationEmail(patientUser);

            } catch (Exception e) {
                // If we get an exception getting password hash then log and throw an exception
                LOGGER.error("Could not get password hash when registering user {}", patientUser.getUsername(), e);
                throw new RegistrationException("Could not register patient - exception generating password", e);
            }

        } else {
            // If there wasn't a demographic record for that radar number, or the date of birth didn't match
            throw new RegistrationException("Could not register patient - " +
                    "date of birth incorrect for given radar number");
        }
    }

    public void registerProfessional(ProfessionalUser professionalUser) throws UserEmailAlreadyExists,
            RegistrationException {
        User user = userDao.getProfessionalUser(professionalUser.getEmail());
        if (user != null) {
            throw new UserEmailAlreadyExists("Email address already exists");
        }

        try {
            saveProfessionalUser(professionalUser);
        } catch (Exception e) {
            LOGGER.error("Could not register professional", e);
            throw new RegistrationException("Could not register professional", e);
        }
        emailManager.sendProfessionalRegistrationAdminNotificationEmail(professionalUser);
    }

    public ProfessionalUser getProfessionalUser(Long id) {
        return userDao.getProfessionalUser(id);
    }

    public ProfessionalUser getProfessionalUser(String email) {
        return userDao.getProfessionalUser(email);
    }

    public void saveProfessionalUser(ProfessionalUser professionalUser) throws Exception {
        // if its a new user generate a password
        if (!professionalUser.hasValidId()) {
            String password = generateRandomPassword();
            professionalUser.setPasswordHash(ProfessionalUser.getPasswordHash(password));
            professionalUser.setUsernameHash(ProfessionalUser.getUsernameHash(professionalUser.getEmail()));
        }

        userDao.saveProfessionalUser(professionalUser);
    }

    public void deleteProfessionalUser(ProfessionalUser professionalUser) throws Exception {
        userDao.deleteProfessionalUser(professionalUser);
    }

    public List<ProfessionalUser> getProfessionalUsers() {
        return getProfessionalUsers(new ProfessionalUserFilter(), -1, -1);
    }

    public List<ProfessionalUser> getProfessionalUsers(ProfessionalUserFilter filter) {
        return getProfessionalUsers(filter, -1, -1);
    }

    public List<ProfessionalUser> getProfessionalUsers(ProfessionalUserFilter filter, int page, int numberPerPage) {
        return userDao.getProfessionalUsers(filter, page, numberPerPage);
    }

    public boolean authenticateProfessionalUser(String username, String password) throws AuthenticationException {
        ProfessionalUser professionalUser = userDao.getProfessionalUser(username);
        if (professionalUser != null) {
            try {
                Authentication authentication = authenticationManager.
                        authenticate(new UsernamePasswordAuthenticationToken(username, password));
                return authentication.isAuthenticated();
            } catch (AuthenticationException e) {
                LOGGER.warn("Authentication failed for user {} and password {}", username, e.getMessage());
                throw e;
            }
        }
        return false;
    }

    public void changeUserPassword(String username, String password) throws DecryptionException, DaoException {
        ProfessionalUser professionalUser = getProfessionalUser(username);
        try {
            professionalUser.setPasswordHash(User.getPasswordHash(password));

        } catch (Exception e) {
            LOGGER.error("could not get password hash for password", e);
            throw new DecryptionException("could not get password hash for password");
        }
        try {
            userDao.saveProfessionalUser(professionalUser);
        } catch (Exception e) {
            LOGGER.error("could not save professional user", e);
            throw new DaoException("Could not save professional user");
        }
    }

    public void sendForgottenPasswordToPatient(String username) throws EmailAddressNotFoundException,
            DecryptionException {
        // In theory this could just go in the email manager but we need to query for user first
        PatientUser patientUser = userDao.getPatientUser(username);
        if (patientUser != null) {
            try {
                String password = TripleDes.decrypt(patientUser.getPasswordHash());
                emailManager.sendForgottenPassword(patientUser, password);
            } catch (Exception e) {
                LOGGER.error("Could not decrypt password for forgotten password email for {}", username, e);
                throw new DecryptionException("Could not decrypt password for forgotten password email", e);
            }
        } else {
            LOGGER.error("Could not find user with email {}", username);
            throw new EmailAddressNotFoundException("Email Address not found");
        }
    }

    public void sendForgottenPasswordToProfessional(String username) throws EmailAddressNotFoundException,
            DecryptionException {
        // In theory this could just go in the email manager but we need to query for user first
        ProfessionalUser professionalUser = userDao.getProfessionalUser(username);
        if (professionalUser != null) {
            try {
                String password = TripleDes.decrypt(professionalUser.getPasswordHash());
                professionalUser.setUsername(TripleDes.decrypt(professionalUser.getUsernameHash()));
                emailManager.sendForgottenPassword(professionalUser, password);
            } catch (Exception e) {
                LOGGER.error("Could not decrypt");
                throw new DecryptionException("Could not decrypt", e);
            }
        } else {
            LOGGER.error("Could not find user with email {}", username);
            throw new EmailAddressNotFoundException("Email Address not found");
        }
    }

    private String generateRandomPassword() {
        // I love you Apache commons
        return RandomStringUtils.randomAlphanumeric(8);
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, DataAccessException {
        // Pull the user from the DAO
        User user = userDao.getProfessionalUser(email);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException("User not found with email address " + email);
    }

    public void setEmailManager(EmailManager emailManager) {
        this.emailManager = emailManager;
    }

    public void setDemographicsDao(DemographicsDao demographicsDao) {
        this.demographicsDao = demographicsDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setAuthenticationManager(ProviderManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}
