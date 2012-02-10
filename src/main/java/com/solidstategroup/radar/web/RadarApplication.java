package com.solidstategroup.radar.web;

import com.solidstategroup.radar.web.pages.ExistingPatientsListingPage;
import com.solidstategroup.radar.web.pages.PatientPageReadOnly;
import com.solidstategroup.radar.web.pages.PatientRegistrationPage;
import com.solidstategroup.radar.web.pages.admin.AdminsBasePage;
import com.solidstategroup.radar.web.pages.admin.AdminsLoginPage;
import com.solidstategroup.radar.web.pages.admin.AdminsPage;
import com.solidstategroup.radar.web.pages.HomePage;
import com.solidstategroup.radar.web.pages.PatientPage;
import com.solidstategroup.radar.web.pages.PatientsLoginPage;
import com.solidstategroup.radar.web.pages.ProfessionalsLoginPage;
import com.solidstategroup.radar.web.pages.ProfessionalsPage;
import com.solidstategroup.radar.web.pages.RecruitmentPage;
import com.solidstategroup.radar.web.pages.ProfessionalRegistrationPage;
import com.solidstategroup.radar.web.pages.content.ConsentFormsPage;
import com.solidstategroup.radar.web.pages.content.DiseaseIndexPage;
import com.solidstategroup.radar.web.pages.content.MpgnPage;
import com.solidstategroup.radar.web.pages.content.SrnsPage;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;
import org.apache.wicket.authorization.UnauthorizedInstantiationException;
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
        return RadarSecuredSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        // dont actually need to do anything here as there is a unauthorised listener set on the application
        // which checks what page was requested and decides which login to send to
        return null;
    }

    @Override
    public void init() {
        super.init();

        // This allows our SpringBean annotations to work
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));

        // set a security listener for checks on pages and what logins they should go to
        // TODO: may have an issue with this as professional and patients both use the .PatientPage
        getSecuritySettings().setUnauthorizedComponentInstantiationListener(
                new IUnauthorizedComponentInstantiationListener() {
                    public void onUnauthorizedInstantiation(final Component component) {
                        if (component instanceof Page) {
                            if (component instanceof AdminsBasePage) {
                                throw new RestartResponseAtInterceptPageException(AdminsLoginPage.class);
                            } else if (component.getClass() == PatientPageReadOnly.class) {
                                throw new RestartResponseAtInterceptPageException(PatientsLoginPage.class);
                            }

                            throw new RestartResponseAtInterceptPageException(ProfessionalsLoginPage.class);
                        } else {
                            throw new UnauthorizedInstantiationException(component.getClass());
                        }
                    }
                });

        mountPage("admins", AdminsPage.class);

        // Mount nice URLs for pages - patient pages
        mountPage("patient/edit", PatientPage.class);
        mountPage("patient/view", PatientPageReadOnly.class);
        mountPage("patients", ExistingPatientsListingPage.class);
        mountPage("registration/patient", PatientRegistrationPage.class);

        mountPage("login/patient", PatientsLoginPage.class);
        mountPage("login/professional", ProfessionalsLoginPage.class);

        mountPage("registration/professional", ProfessionalRegistrationPage.class);
        mountPage("professionals", ProfessionalsPage.class);
        mountPage("recruitment", RecruitmentPage.class);

        // Static content pages
        mountPage("diseaseindex", DiseaseIndexPage.class);
        mountPage("mpgn", MpgnPage.class);
        mountPage("srns", SrnsPage.class);
        mountPage("consentforms", ConsentFormsPage.class);
    }
}
