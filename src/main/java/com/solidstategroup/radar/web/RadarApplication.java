package com.solidstategroup.radar.web;

import com.solidstategroup.radar.web.pages.ExistingPatientsPage;
import com.solidstategroup.radar.web.pages.HomePage;
import com.solidstategroup.radar.web.pages.PatientPage;
import com.solidstategroup.radar.web.pages.PatientsLoginPage;
import com.solidstategroup.radar.web.pages.ProfessionalsLoginPage;
import com.solidstategroup.radar.web.pages.ProfessionalsPage;
import com.solidstategroup.radar.web.pages.RecruitmentPage;
import com.solidstategroup.radar.web.pages.RegistrationProfessionalPage;
import com.solidstategroup.radar.web.pages.content.ConsentFormsPage;
import com.solidstategroup.radar.web.pages.content.DiseaseIndexPage;
import com.solidstategroup.radar.web.pages.content.MpgnPage;
import com.solidstategroup.radar.web.pages.content.SrnsPage;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

public class RadarApplication extends AuthenticatedWebApplication {

    public static final String DATE_PATTERN = "dd-MMM-yyyy";

    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return SecuredSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        // Not sure if we can work out which one it needs to be, probably a redirect according to the referring page
        return ProfessionalsLoginPage.class;
    }

    @Override
    public void init() {
        super.init();

        // This allows our SpringBean annotations to work
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));

        // Mount nice URLs for pages - patient pages
        mountPage("patient", PatientPage.class);
        mountPage("patients", ExistingPatientsPage.class);
        mountPage("login", PatientsLoginPage.class);

        mountPage("registration", RegistrationProfessionalPage.class);
        mountPage("professionals", ProfessionalsPage.class);
        mountPage("recruitment", RecruitmentPage.class);

        // Static content pages
        mountPage("diseaseindex", DiseaseIndexPage.class);
        mountPage("mpgn", MpgnPage.class);
        mountPage("srns", SrnsPage.class);
        mountPage("consentforms", ConsentFormsPage.class);
    }
}
