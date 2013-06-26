package org.patientview.radar.web.pages.login;


import org.patientview.radar.model.exception.DecryptionException;
import org.patientview.radar.model.exception.EmailAddressNotFoundException;

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
