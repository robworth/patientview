package com.solidstategroup.radar.service;

import com.solidstategroup.radar.model.exception.ProfessionalUserEmailAlreadyExists;
import com.solidstategroup.radar.model.exception.RegistrationException;
import com.solidstategroup.radar.model.user.PatientUser;
import com.solidstategroup.radar.model.user.ProfessionalUser;
import com.solidstategroup.radar.model.filter.ProfessionalUserFilter;

import java.util.Date;
import java.util.List;

public interface UserManager {

    PatientUser getPatientUser(String email);

    PatientUser getPatientUser(String email, Date dateOfBirth);

    void savePatientUser(PatientUser patientUser);

    void registerPatient(PatientUser patientUser) throws RegistrationException;

    ProfessionalUser getProfessionalUser(Long id);

    ProfessionalUser getProfessionalUser(String email);

    void saveProfessionalUser(ProfessionalUser professionalUser) throws Exception;

    void registerProfessional(ProfessionalUser professionalUser) throws ProfessionalUserEmailAlreadyExists, RegistrationException;

    void sendForgottenPassword(String username);

    void deleteProfessionalUser(ProfessionalUser professionalUser) throws Exception;

    List<ProfessionalUser> getProfessionalUsers();

    List<ProfessionalUser> getProfessionalUsers(ProfessionalUserFilter filter);

    List<ProfessionalUser> getProfessionalUsers(ProfessionalUserFilter filter, int page, int numberPerPage);


}
