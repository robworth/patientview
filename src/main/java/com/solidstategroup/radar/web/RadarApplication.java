package com.solidstategroup.radar.web;

import com.solidstategroup.radar.web.pages.ExistingPatientsPage;
import com.solidstategroup.radar.web.pages.HomePage;
import com.solidstategroup.radar.web.pages.PatientPage;
import com.solidstategroup.radar.web.pages.PatientsPage;
import com.solidstategroup.radar.web.pages.ProfessionalsPage;
import com.solidstategroup.radar.web.pages.RecruitmentPage;
import com.solidstategroup.radar.web.pages.RegistrationProfessionalPage;
import com.solidstategroup.radar.web.pages.content.ConsentFormsPage;
import com.solidstategroup.radar.web.pages.content.DiseaseIndexPage;
import com.solidstategroup.radar.web.pages.content.MpgnPage;
import com.solidstategroup.radar.web.pages.content.SrnsPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

public class RadarApplication extends WebApplication {

    public static final String DATE_PATTERN = "dd-MMM-yyyy";

    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    @Override
    public void init() {
        super.init();

        // Mount nice URLs for pages - patient pages
        mountPage("patient", PatientPage.class);
        mountPage("patients", ExistingPatientsPage.class);
        mountPage("login", PatientsPage.class);

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
