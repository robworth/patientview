package com.solidstategroup.radar.service.impl;

import com.solidstategroup.radar.dao.DemographicsDao;
import com.solidstategroup.radar.dao.UserDao;
import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.model.exception.RegistrationException;
import com.solidstategroup.radar.model.user.PatientUser;
import com.solidstategroup.radar.model.user.User;
import com.solidstategroup.radar.service.EmailManager;
import com.solidstategroup.radar.service.UserManager;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserManagerImpl implements UserManager, UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserManagerImpl.class);

    private EmailManager emailManager;

    private DemographicsDao demographicsDao;
    private UserDao userDao;

    public void registerPatient(PatientUser patientUser) throws RegistrationException {
        // Check we have a valid radar number, email address and date of birth
        if (patientUser == null ||
                patientUser.getRadarNumber() <= 0L ||
                StringUtils.isBlank(patientUser.getUsername()) ||
                patientUser.getDateOfBirth() == null) {
            throw new IllegalArgumentException("Must supply a non null patient user " +
                    "with valid radar number, username and date of birth for registration");
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
                emailManager.sendAdminPatientRegistrationEmail(patientUser);

            } catch (Exception e) {
                // If we get an exception getting password hash then log and throw an exception
                LOGGER.error("Could not get password hash when registering user {}", patientUser.getUsername(), e);
                throw new RegistrationException("Could not register patient - exception generating password");
            }

        } else {
            // If there wasn't a demographic record for that radar number, or the date of birth didn't match
            throw new RegistrationException("Could not register patient - " +
                    "date of birth incorrect for given radar number");
        }
    }

    private String generateRandomPassword() {
        // I love you Apache commons
        return RandomStringUtils.random(8);
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

}
