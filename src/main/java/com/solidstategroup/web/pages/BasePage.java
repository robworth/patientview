package com.solidstategroup.web.pages;



import com.solidstategroup.web.pages.newPatient.DemographicsPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public abstract class BasePage extends WebPage {

    private static final String TITLE_WICKET_ID = "title";
    public static final String HOME_PAGE_WICKET_ID = "homePage";
    public static final String PRO_REGISTRATION_PAGE_WICKET_ID = "proRegistrationPage";
    public static final String PROFESSIONALS_PAGE_WICKET_ID = "professionalsPage";
    public static final String PATIENTS_PAGE_WICKET_ID = "patientsPage";
    public static final String DISEASE_INDEX_PAGE_WICKET_ID = "diseaseIndexPage";

    public BasePage() {
        add(new Label(TITLE_WICKET_ID, getTitle()));

        BookmarkablePageLink[] naviationPages = {
            new BookmarkablePageLink(HOME_PAGE_WICKET_ID, HomePage.class),
            new BookmarkablePageLink(PRO_REGISTRATION_PAGE_WICKET_ID, RegistrationProfessionalPage.class),
            new BookmarkablePageLink(PROFESSIONALS_PAGE_WICKET_ID, ProfessionalsPage.class),
            new BookmarkablePageLink(PATIENTS_PAGE_WICKET_ID, PatientsPage.class),
            new BookmarkablePageLink(DISEASE_INDEX_PAGE_WICKET_ID, DiseaseIndexPage.class),
            new BookmarkablePageLink("demographicsPage", DemographicsPage.class),
            new BookmarkablePageLink("patientsListingPage", PatientsListingPage.class),
            new BookmarkablePageLink("recruitmentPage", RecruitmentPage.class),
            new BookmarkablePageLink("mpgnPage", MpgnPage.class),
            new BookmarkablePageLink("srnsPage", SrnsPage.class)
        };

        add(naviationPages);
    }

    public String getTitle() {
        return "RADAR - National Renal Rare Disease Registry";
    }

}
