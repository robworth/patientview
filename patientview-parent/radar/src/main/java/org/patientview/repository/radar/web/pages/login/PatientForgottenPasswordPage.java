package org.patientview.repository.radar.web.pages.login;


import org.patientview.model.radar.exception.DecryptionException;
import org.patientview.model.radar.exception.EmailAddressNotFoundException;

public class PatientForgottenPasswordPage extends ForgottenPasswordPage{

    @Override
    protected String getUserType() {
        return "Patients";
    }

    @Override
    public void sendPassword(String username) throws EmailAddressNotFoundException, DecryptionException {
        userManager.sendForgottenPasswordToPatient(username);
    }
}
