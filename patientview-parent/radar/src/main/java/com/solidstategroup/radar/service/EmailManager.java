package com.solidstategroup.radar.service;

import com.solidstategroup.radar.model.user.PatientUser;
import com.solidstategroup.radar.model.user.ProfessionalUser;

import java.util.Map;

public interface EmailManager {

    void sendPatientRegistrationEmail(PatientUser patientUser, String password);

    void sendPatientRegistrationReminderEmail(PatientUser patientUser) throws Exception;

    void sendPatientRegistrationAdminNotificationEmail(PatientUser patientUser);

    void sendProfessionalRegistrationAdminNotificationEmail(ProfessionalUser professionalUser);

    void sendForgottenPassword(PatientUser patientUser, String password);

    void sendForgottenPassword(ProfessionalUser professionalUser, String password) throws Exception;

    // to allow for testing, this method is in the interface
    String renderTemplate(Map<String, Object> map, String template);

    // to allow for testing, this method is in the interface
    void sendEmail(String from, String[] to, String[] bbc, String subject, String body);

}
