package org.patientview.radar.service;

import org.patientview.radar.model.Demographics;
import org.patientview.radar.model.exception.InvalidSecurityQuestionAnswer;
import org.patientview.radar.model.exception.UserEmailAlreadyExists;
import org.patientview.radar.model.exception.DaoException;
import org.patientview.radar.model.exception.DecryptionException;
import org.patientview.radar.model.exception.EmailAddressNotFoundException;
import org.patientview.radar.model.exception.RegistrationException;
import org.patientview.radar.model.filter.PatientUserFilter;
import org.patientview.radar.model.filter.ProfessionalUserFilter;
import org.patientview.radar.model.user.AdminUser;
import org.patientview.radar.model.user.PatientUser;
import org.patientview.radar.model.user.ProfessionalUser;
import org.patientview.radar.model.user.User;
import org.springframework.security.core.AuthenticationException;

import java.util.Date;
import java.util.List;

public interface UserManager {

    AdminUser getAdminUser(String email);

    AdminUser getAdminUserWithUsername(String username);

    PatientUser getPatientUser(Long id);

    PatientUser getPatientUser(String email);

    PatientUser getPatientUserWithUsername(String username);

    PatientUser getPatientUser(String email, Date dateOfBirth);

    PatientUser getPatientUserWithUsername(String username, Date dateOfBirth);

    List<PatientUser> getPatientUsers();

    List<PatientUser> getPatientUsers(PatientUserFilter filter);

    List<PatientUser> getPatientUsers(PatientUserFilter filter, int page, int numberPerPage);

    void savePatientUser(PatientUser patientUser) throws Exception;

    void deletePatientUser(PatientUser patientUser) throws Exception;

    void registerPatient(Demographics demographics) throws Exception;

    void registerProfessional(ProfessionalUser professionalUser) throws UserEmailAlreadyExists,
            RegistrationException, InvalidSecurityQuestionAnswer;

    ProfessionalUser getProfessionalUser(Long id);

    ProfessionalUser getProfessionalUser(String email);

    ProfessionalUser getProfessionalUserWithUsername(String username);

    User getSuperUserWithUsername(String username);

    void saveProfessionalUser(ProfessionalUser professionalUser) throws Exception;

    void sendForgottenPasswordToPatient(String username) throws EmailAddressNotFoundException, DecryptionException;

    void sendForgottenPasswordToProfessional(String username) throws EmailAddressNotFoundException, DecryptionException;

    void deleteProfessionalUser(ProfessionalUser professionalUser) throws Exception;

    List<ProfessionalUser> getProfessionalUsers();

    List<ProfessionalUser> getProfessionalUsers(ProfessionalUserFilter filter);

    List<ProfessionalUser> getProfessionalUsers(ProfessionalUserFilter filter, int page, int numberPerPage);

    /**
     * Authentication for login is done using spring security but does does not check the user type e.g. if its a
     * professional user. This was added as an extra layer to check if the username exists for professional user
     * before authenticating. Used in change ChangeRegistrationDetails.class for clinicians
     */
    boolean authenticateProfessionalUser(String username, String password) throws AuthenticationException;

    void changeUserPassword(String username, String password) throws DecryptionException, DaoException;

    boolean userExistsInPatientView(String nhsno);
}
