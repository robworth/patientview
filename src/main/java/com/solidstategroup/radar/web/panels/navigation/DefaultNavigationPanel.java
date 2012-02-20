package com.solidstategroup.radar.web.panels.navigation;

import com.solidstategroup.radar.web.pages.login.PatientsLoginPage;
import com.solidstategroup.radar.web.pages.login.ProfessionalsLoginPage;
import com.solidstategroup.radar.web.pages.regisration.PatientRegistrationPage;
import com.solidstategroup.radar.web.pages.regisration.ProfessionalRegistrationPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.MarkupContainer;
import com.solidstategroup.radar.web.pages.HomePage;
import com.solidstategroup.radar.web.pages.PatientPage;
import com.solidstategroup.radar.web.pages.ExistingPatientsListingPage;
import com.solidstategroup.radar.web.pages.RecruitmentPage;
import com.solidstategroup.radar.web.pages.ProfessionalsPage;
import com.solidstategroup.radar.web.pages.PatientPageReadOnly;
import com.solidstategroup.radar.web.pages.content.DiseaseIndexPage;
import com.solidstategroup.radar.web.pages.content.MpgnPage;
import com.solidstategroup.radar.web.pages.content.SrnsPage;
import com.solidstategroup.radar.web.pages.content.ConsentFormsPage;
import com.solidstategroup.radar.web.RadarSecuredSession;
import com.solidstategroup.radar.model.user.User;

// TODO: this could be split into patient, professional and no one logged in
public class DefaultNavigationPanel extends BaseNavigationPanel {
    public DefaultNavigationPanel(Class<? extends org.apache.wicket.Page> pageClass) {
        boolean userLoggedIn = (isProfessionalOrSuperUserLoggedIn() || isPatientUserLoggedIn());

        // Generic links
        addHomePageLink();

        addLogoutLink(userLoggedIn);

        add(new BookmarkablePageLink<DiseaseIndexPage>("diseaseIndexPageLink", DiseaseIndexPage.class));

        // Enter new patient - only visible when a professional is logged in
        BookmarkablePageLink enterNewPatientPageLink = new BookmarkablePageLink<PatientPage>("enterNewPatientPageLink",
                PatientPage.class);
        enterNewPatientPageLink.setVisible(isProfessionalOrSuperUserLoggedIn());
        add(enterNewPatientPageLink);

        // Container for existing patients links, only visible when a professional is logged in
        MarkupContainer existingPatientsContainer = new WebMarkupContainer("existingPatientsContainer");
        existingPatientsContainer.setVisible(isProfessionalOrSuperUserLoggedIn());
        existingPatientsContainer.add(
                new BookmarkablePageLink<ExistingPatientsListingPage>("patientsListingPageLink",
                        ExistingPatientsListingPage.class),
                new BookmarkablePageLink<RecruitmentPage>("recruitmentPageLink", RecruitmentPage.class)
        );
        add(existingPatientsContainer);

        // Container for clinicians links
        MarkupContainer cliniciansContainer = new WebMarkupContainer("cliniciansContainer");
        cliniciansContainer.setVisible(isProfessionalOrSuperUserLoggedIn());
        cliniciansContainer.add(
                new BookmarkablePageLink<MpgnPage>("mpgnPageLink", MpgnPage.class),
                new BookmarkablePageLink<SrnsPage>("srnsPageLink", SrnsPage.class),
                new BookmarkablePageLink<ConsentFormsPage>("consentFormsPageLink", ConsentFormsPage.class)
        );
        add(cliniciansContainer);

        // only want to show if on patient login page
        BookmarkablePageLink patientRegistrationLink = new BookmarkablePageLink<PatientRegistrationPage>(
                "patientRegistrationPageLink", PatientRegistrationPage.class);
        patientRegistrationLink.setVisible(pageClass == PatientsLoginPage.class);
        add(patientRegistrationLink);

        // only want to show on professional login page or homepage
        BookmarkablePageLink professionalRegistrationPageLink = new BookmarkablePageLink<ProfessionalRegistrationPage>(
                "professionalRegistrationPageLink", ProfessionalRegistrationPage.class);
        professionalRegistrationPageLink.setVisible(pageClass == ProfessionalsLoginPage.class
                || pageClass == HomePage.class);
        add(professionalRegistrationPageLink);

        BookmarkablePageLink professionalsPageLink = new BookmarkablePageLink<ProfessionalsPage>(
                "professionalsPageLink", ProfessionalsPage.class);
        professionalsPageLink.setVisible(!userLoggedIn);
        add(professionalsPageLink);

        BookmarkablePageLink patientsPageLink = new BookmarkablePageLink<PatientPageReadOnly>("patientsPageLink",
                PatientPageReadOnly.class);
        patientsPageLink.setVisible(!userLoggedIn);
        add(patientsPageLink);
    }

    protected boolean isProfessionalOrSuperUserLoggedIn() {
        RadarSecuredSession session = RadarSecuredSession.get();
        return session.isSignedIn() ? session.getRoles().hasRole(User.ROLE_PROFESSIONAL) ||
                session.getRoles().hasRole(User.ROLE_SUPER_USER) : false;
    }

    protected boolean isPatientUserLoggedIn() {
        RadarSecuredSession session = RadarSecuredSession.get();
        return session.isSignedIn() ? session.getRoles().hasRole(User.ROLE_PATIENT) : false;
    }
}
