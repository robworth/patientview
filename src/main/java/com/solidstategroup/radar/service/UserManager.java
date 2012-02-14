package com.solidstategroup.radar.service;

import com.solidstategroup.radar.model.exception.ProfessionalUserEmailAlreadyExists;
import com.solidstategroup.radar.model.exception.RegistrationException;
import com.solidstategroup.radar.model.user.PatientUser;
import com.solidstategroup.radar.model.user.ProfessionalUser;

import java.util.Date;

public interface UserManager {

    PatientUser getPatientUser(String email);

    PatientUser getPatientUser(String email, Date dateOfBirth);

    void savePatientUser(PatientUser patientUser);

    ProfessionalUser getProfessionalUser(String email);

    void registerPatient(PatientUser patientUser) throws RegistrationException;

    void registerProfessional(ProfessionalUser professionalUser) throws ProfessionalUserEmailAlreadyExists, RegistrationException;

    void sendForgottenPassword(String username);


}
