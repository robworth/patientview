package com.solidstategroup.radar.web.pages;

import com.solidstategroup.radar.model.user.User;
import com.solidstategroup.radar.web.behaviours.RadarStyleBehaviour;
import com.solidstategroup.radar.web.pages.content.ConsentFormsPage;
import com.solidstategroup.radar.web.pages.content.DiseaseIndexPage;
import com.solidstategroup.radar.web.pages.content.MpgnPage;
import com.solidstategroup.radar.web.pages.content.SrnsPage;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BasePage extends WebPage {

    public BasePage() {
        // Attach styles
        add(new RadarStyleBehaviour());

        add(new Label("title", getTitle()));

        // Enter new patient - only visible when a professional is logged in
        BookmarkablePageLink enterNewPatientLink = new BookmarkablePageLink("enterNewPatientLink", PatientPage.class);
        enterNewPatientLink.setVisible(isProfessionalUserLoggedIn());

        // Container for existing patients links, only visible when a professional is logged in
        MarkupContainer existingPatientsContainer = new WebMarkupContainer("existingPatientsContainer");
        existingPatientsContainer.setVisible(isProfessionalUserLoggedIn());
        existingPatientsContainer.add(
                new BookmarkablePageLink("patientsListingPage", ExistingPatientsPage.class),
                new BookmarkablePageLink("recruitmentPage", RecruitmentPage.class)
        );
        add(existingPatientsContainer);

        BookmarkablePageLink[] navigationPages = {
                new BookmarkablePageLink("homePage", HomePage.class),
                new BookmarkablePageLink("proRegistrationPage", RegistrationProfessionalPage.class),
                new BookmarkablePageLink("professionalsPage", ProfessionalsPage.class),
                new BookmarkablePageLink("patientsPage", PatientsPage.class),
                new BookmarkablePageLink("diseaseIndexPage", DiseaseIndexPage.class),
                enterNewPatientLink,
                new BookmarkablePageLink("mpgnPage", MpgnPage.class),
                new BookmarkablePageLink("srnsPage", SrnsPage.class),
                new BookmarkablePageLink("consentFormsPage", ConsentFormsPage.class)
        };

        add(navigationPages);
    }

    public String getTitle() {
        return "RADAR - National Renal Rare Disease Registry";
    }

    protected boolean isProfessionalUserLoggedIn() {
        return getUser() != null && getUser().getSecurityRole().equals(User.ROLE_PROFESSIONAL);
    }

    protected User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }

}
