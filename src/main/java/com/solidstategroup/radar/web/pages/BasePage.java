package com.solidstategroup.radar.web.pages;

import com.solidstategroup.radar.web.pages.content.ConsentFormsPage;
import com.solidstategroup.radar.web.pages.content.DiseaseIndexPage;
import com.solidstategroup.radar.web.pages.content.MpgnPage;
import com.solidstategroup.radar.web.pages.content.SrnsPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public abstract class BasePage extends WebPage {

    public BasePage() {
        add(new Label("title", getTitle()));

        BookmarkablePageLink[] navigationPages = {
                new BookmarkablePageLink("homePage", HomePage.class),
                new BookmarkablePageLink("proRegistrationPage", RegistrationProfessionalPage.class),
                new BookmarkablePageLink("professionalsPage", ProfessionalsPage.class),
                new BookmarkablePageLink("patientsPage", PatientsPage.class),
                new BookmarkablePageLink("diseaseIndexPage", DiseaseIndexPage.class),
                new BookmarkablePageLink("demographicsPage", PatientPage.class),
                new BookmarkablePageLink("patientsListingPage", ExistingPatientsPage.class),
                new BookmarkablePageLink("recruitmentPage", RecruitmentPage.class),
                new BookmarkablePageLink("mpgnPage", MpgnPage.class),
                new BookmarkablePageLink("srnsPage", SrnsPage.class),
                new BookmarkablePageLink("consentFormsPage", ConsentFormsPage.class)
        };

        add(navigationPages);
    }

    public String getTitle() {
        return "RADAR - National Renal Rare Disease Registry";
    }

}
