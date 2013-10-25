package org.patientview.repository.radar.web.pages.login;


import org.patientview.model.radar.exception.DecryptionException;
import org.patientview.model.radar.exception.EmailAddressNotFoundException;

public class ProfessionalForgottenPasswordPage extends ForgottenPasswordPage{

    @Override
    protected String getUserType() {
        return "Clinicians";
    }

    @Override
    public void sendPassword(String username) throws EmailAddressNotFoundException, DecryptionException {
        userManager.sendForgottenPasswordToProfessional(username);
    }
}
