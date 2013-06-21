package com.solidstategroup.radar.web.pages.login;


import com.solidstategroup.radar.model.exception.DecryptionException;
import com.solidstategroup.radar.model.exception.EmailAddressNotFoundException;

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
