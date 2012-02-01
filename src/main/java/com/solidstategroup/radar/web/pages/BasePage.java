package com.solidstategroup.radar.web.pages;

import com.solidstategroup.radar.model.user.User;
import com.solidstategroup.radar.web.SecuredSession;
import com.solidstategroup.radar.web.behaviours.RadarStyleBehaviour;
import com.solidstategroup.radar.web.pages.content.ConsentFormsPage;
import com.solidstategroup.radar.web.pages.content.DiseaseIndexPage;
import com.solidstategroup.radar.web.pages.content.MpgnPage;
import com.solidstategroup.radar.web.pages.content.SrnsPage;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BasePage extends WebPage {

    public BasePage() {
        boolean userLoggedIn = (isProfessionalUserLoggedIn() || isPatientUserLoggedIn());

        // Attach styles
        add(new RadarStyleBehaviour());

        add(new Label("title", getTitle()));

        // Generic links
        add(new BookmarkablePageLink<HomePage>("homePageLink", HomePage.class));
        add(new BookmarkablePageLink<DiseaseIndexPage>("diseaseIndexPageLink", DiseaseIndexPage.class));

        AjaxLink logoutLink = new AjaxLink<HomePage>("logoutLink") {
            @Override
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                SecuredSession.get().invalidate();
                setResponsePage(HomePage.class);
            }
        };
        logoutLink.setVisible(userLoggedIn);
        add(logoutLink);

        // Enter new patient - only visible when a professional is logged in
        BookmarkablePageLink enterNewPatientPageLink = new BookmarkablePageLink<PatientPage>("enterNewPatientPageLink",
                PatientPage.class);
        enterNewPatientPageLink.setVisible(isProfessionalUserLoggedIn());
        add(enterNewPatientPageLink);

        // Container for existing patients links, only visible when a professional is logged in
        MarkupContainer existingPatientsContainer = new WebMarkupContainer("existingPatientsContainer");
        existingPatientsContainer.setVisible(isProfessionalUserLoggedIn());
        existingPatientsContainer.add(
                new BookmarkablePageLink<ExistingPatientsPage>("patientsListingPageLink", ExistingPatientsPage.class),
                new BookmarkablePageLink<RecruitmentPage>("recruitmentPageLink", RecruitmentPage.class)
        );
        add(existingPatientsContainer);

        // Container for clinicians links
        MarkupContainer cliniciansContainer = new WebMarkupContainer("cliniciansContainer");
        cliniciansContainer.setVisible(isProfessionalUserLoggedIn());
        cliniciansContainer.add(
                new BookmarkablePageLink<MpgnPage>("mpgnPageLink", MpgnPage.class),
                new BookmarkablePageLink<SrnsPage>("srnsPageLink", SrnsPage.class),
                new BookmarkablePageLink<ConsentFormsPage>("consentFormsPageLink", ConsentFormsPage.class)
        );
        add(cliniciansContainer);

        // only want to show if on patient login page
        BookmarkablePageLink patientRegistrationLink = new BookmarkablePageLink<PatientRegistrationPage>(
                "patientRegistrationPageLink", PatientRegistrationPage.class);
        patientRegistrationLink.setVisible(getPageClass() == PatientsLoginPage.class);
        add(patientRegistrationLink);

        // only want to show on professional login page or homepage
        BookmarkablePageLink professionalRegistrationPageLink = new BookmarkablePageLink<ProfessionalRegistrationPage>(
                "professionalRegistrationPageLink", ProfessionalRegistrationPage.class);
        professionalRegistrationPageLink.setVisible(getPageClass() == ProfessionalsLoginPage.class
                || getPageClass() == HomePage.class);
        add(professionalRegistrationPageLink);

        BookmarkablePageLink professionalsPageLink = new BookmarkablePageLink<ProfessionalsPage>(
                "professionalsPageLink", ProfessionalsPage.class);
        professionalsPageLink.setVisible(!userLoggedIn);
        add(professionalsPageLink);

        BookmarkablePageLink patientsPageLink = new BookmarkablePageLink<PatientsLoginPage>("patientsPageLink",
                PatientPage.class);
        patientsPageLink.setVisible(!userLoggedIn);
        add(patientsPageLink);
    }

    public String getTitle() {
        return "RADAR - National Renal Rare Disease Registry";
    }

    protected boolean isProfessionalUserLoggedIn() {
        return getUser() != null && getUser().getSecurityRole().equals(User.ROLE_PROFESSIONAL);
    }

    protected boolean isPatientUserLoggedIn() {
        return getUser() != null && getUser().getSecurityRole().equals(User.ROLE_PATIENT);
    }

    protected User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }
}
