package com.solidstategroup.radar.web.pages.login;


import com.solidstategroup.radar.model.exception.DecryptionException;
import com.solidstategroup.radar.model.exception.EmailAddressNotFoundException;

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
