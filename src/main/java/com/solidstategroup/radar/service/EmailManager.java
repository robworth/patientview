package com.solidstategroup.radar.service;

import com.solidstategroup.radar.model.user.PatientUser;
import com.solidstategroup.radar.model.user.ProfessionalUser;

public interface EmailManager {

    boolean sendTestEmail();

    void sendPatientRegistrationEmail(PatientUser patientUser, String password);

    void sendAdminPatientRegistrationEmail(PatientUser patientUser);

    void sendAdminProfessionalRegistrationEmail(ProfessionalUser professionalUser);

    void sendForgottenPassword(PatientUser patientUser, String password);
}
