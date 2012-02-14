package com.solidstategroup.radar.service;

import com.solidstategroup.radar.model.user.PatientUser;

public interface EmailManager {

    boolean sendTestEmail();

    void sendPatientRegistrationEmail(PatientUser patientUser, String password);

    void sendAdminPatientRegistrationEmail(PatientUser patientUser);

    void sendForgottenPassword(PatientUser patientUser, String password);
}
