package org.patientview.radar.web.pages.login;


import org.patientview.radar.model.exception.DecryptionException;
import org.patientview.radar.model.exception.EmailAddressNotFoundException;

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
