package com.solidstategroup.radar.service.impl;

import com.solidstategroup.radar.model.user.PatientUser;
import com.solidstategroup.radar.service.EmailManager;

public class EmailManagerImpl implements EmailManager {

    public void sendPatientRegistrationEmail(PatientUser patientUser, String password) {
        // Todo: Load in patient-registration.vm and then run through Velocity render
    }

    public void sendAdminPatientRegistrationEmail(PatientUser patientUser) {
        // Todo: Same but for admin-patient-registration.vm
        // Todo: Sends to fiona.braddon@nhs.net but put this in radar.properties
    }

}
