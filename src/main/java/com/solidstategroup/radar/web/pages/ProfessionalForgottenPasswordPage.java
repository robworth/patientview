package com.solidstategroup.radar.web.pages;


import com.solidstategroup.radar.model.exception.EmailAddressNotFoundException;

public class ProfessionalForgottenPasswordPage extends ForgottenPasswordPage{

    @Override
    protected String getUsesType() {
        return "Clinicians";
    }

    @Override
    public void sendPassword(String username) throws EmailAddressNotFoundException {
        userManager.sendForgottenPasswordToProfessional(username);
    }
}
