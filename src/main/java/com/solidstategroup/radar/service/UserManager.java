package com.solidstategroup.radar.service;

import com.solidstategroup.radar.model.exception.RegistrationException;
import com.solidstategroup.radar.model.user.PatientUser;

public interface UserManager {

    void registerPatient(PatientUser patientUser) throws RegistrationException;

}
