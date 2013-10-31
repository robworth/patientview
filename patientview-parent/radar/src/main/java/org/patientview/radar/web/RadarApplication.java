package org.patientview.radar.web;

import org.patientview.radar.web.pages.HomePage;
import org.patientview.radar.web.pages.ProfessionalsPage;
import org.patientview.radar.web.pages.RecruitmentPage;
import org.patientview.radar.web.pages.admin.AdminConsultantPage;
import org.patientview.radar.web.pages.admin.AdminConsultantsPage;
import org.patientview.radar.web.pages.admin.AdminIssuePage;
import org.patientview.radar.web.pages.admin.AdminIssuesPage;
import org.patientview.radar.web.pages.admin.AdminPatientAllPage;
import org.patientview.radar.web.pages.admin.AdminPatientPage;
import org.patientview.radar.web.pages.admin.AdminPatientsAllPage;
import org.patientview.radar.web.pages.admin.AdminPatientsPage;
import org.patientview.radar.web.pages.admin.AdminUserPage;
import org.patientview.radar.web.pages.admin.AdminUsersPage;
import org.patientview.radar.web.pages.admin.AdminsBasePage;
import org.patientview.radar.web.pages.admin.AdminsLoginPage;
import org.patientview.radar.web.pages.admin.AdminsPage;
import org.patientview.radar.web.pages.content.ConsentFormsPage;
import org.patientview.radar.web.pages.content.DiseaseIndexPage;
import org.patientview.radar.web.pages.content.ErrorPage;
import org.patientview.radar.web.pages.content.MpgnPage;
import org.patientview.radar.web.pages.content.SrnsPage;
import org.patientview.radar.web.pages.login.PatientForgottenPasswordPage;
import org.patientview.radar.web.pages.login.PatientsLoginPage;
import org.patientview.radar.web.pages.login.ProfessionalForgottenPasswordPage;
import org.patientview.radar.web.pages.login.ProfessionalsLoginPage;
import org.patientview.radar.web.pages.patient.AddPatientPage;
import org.patientview.radar.web.pages.patient.ExistingPatientsListingPage;
import org.patientview.radar.web.pages.patient.GenericPatientPage;
import org.patientview.radar.web.pages.patient.srns.SrnsPatientPage;
import org.patientview.radar.web.pages.patient.srns.SrnsPatientPageReadOnly;
import org.patientview.radar.web.pages.regisration.ChangeRegistrationDetails;
import org.patientview.radar.web.pages.regisration.ProfessionalRegistrationPage;
import org.apache.wicket.Component;
import org.apache.wicket.ConverterLocator;
import org.apache.wicket.IConverterLocator;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;
import org.apache.wicket.authorization.UnauthorizedInstantiationException;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.PageProvider;
import org.apache.wicket.request.handler.RenderPageRequestHandler;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.convert.converter.DoubleConverter;
import org.apache.wicket.util.convert.converter.IntegerConverter;
import org.apache.wicket.util.convert.converter.LongConverter;

import java.text.NumberFormat;
import java.util.Locale;

public class RadarApplication extends AuthenticatedWebApplication {
    // mainly used for display
    public static final String DATE_PATTERN = "dd-MMM-yyyy";
    // mainly used for form input
    public static final String DATE_PATTERN2 = "dd-MM-yyyy";

    // page numbers - declare them all in once place so can easily be changed
    public static final int DEMOGRAPHICS_PAGE_NO = 1;
    public static final int DIAGNOSIS_PAGE_NO = 2;
    public static final int CLINICAL_FIRST_VISIT_PAGE_NO = 3;
    public static final int LABORATORY_FIRST_VISIT_PAGE_NO = 4;
    public static final int TREATMENT_FIRST_VISIT_PAGE_NO = 5;
    public static final int CLINICAL_FOLLOW_UP_PAGE_NO = 6;
    public static final int LABORATORY_FOLLOW_UP_PAGE_NO = 7;
    public static final int TREATMENT_FOLLOW_UP_PAGE_NO = 8;
    public static final int RRT_THERAPY_PAGE_NO = 9;
    public static final int PATHOLOGY_PAGE_NO = 11;
    public static final int RELAPSE_PAGE_NO = 12;
    public static final int HOSPITALISATION_PAGE_NO = 16;

    // generic page numbers
    public static final int ADD_PATIENT_PAGE_N0 = 17;
    public static final int GENERIC_DEMOGRAPHICS_PAGE_NO = 18;
    public static final int MEDICAL_RESULTS_PAGE_NO = 19;
    public static final int GENETICE_PAGE_NO = 20;
    public static final int MEDICINE_PAGE_NO = 21;
    // some global settings
    public static final Double MIN_HEIGHT = 35.0;
    public static final Double MAX_HEIGHT = 220.0;
    public static final String ADMINS_BASE_URL = "admins";

    // some script
    public static final String FORM_IS_DIRTY_TRUE_SCRIPT = "editPatientPage.formIsDirty=true;";
    public static final String FORM_IS_DIRTY_FALSE_SCRIPT = "editPatientPage.formIsDirty=false;";

    private boolean ajaxDebug = true;

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
        getSecuritySettings().setUnauthorizedComponentInstantiationListener(
                new IUnauthorizedComponentInstantiationListener() {
                    public void onUnauthorizedInstantiation(final Component component) {
                        if (component instanceof Page) {
                            if (component instanceof AdminsBasePage) {
                                throw new RestartResponseAtInterceptPageException(AdminsLoginPage.class);
                            } else if (component.getClass() == SrnsPatientPageReadOnly.class) {
                                throw new RestartResponseAtInterceptPageException(PatientsLoginPage.class);
                            }

                            throw new RestartResponseAtInterceptPageException(ProfessionalsLoginPage.class);
                        } else {
                            throw new UnauthorizedInstantiationException(component.getClass());
                        }
                    }
                });

        getRequestCycleListeners().add(new AbstractRequestCycleListener() {
            @Override
            public IRequestHandler onException(RequestCycle cycle, Exception ex) {
                return new RenderPageRequestHandler(new PageProvider(new ErrorPage(ex)));
            }
        });

        // remove ajax debug
        getDebugSettings().setAjaxDebugModeEnabled(ajaxDebug);

        // Mount nice URLs for pages - patient pages

        // admins
        mountPage(ADMINS_BASE_URL, AdminsPage.class);
        mountPage("login/admins", AdminsLoginPage.class);
        mountPage(ADMINS_BASE_URL + "/consultants", AdminConsultantsPage.class);
        mountPage(ADMINS_BASE_URL + "/consultants/edit", AdminConsultantPage.class);
        mountPage(ADMINS_BASE_URL + "/issues", AdminIssuesPage.class);
        mountPage(ADMINS_BASE_URL + "/issues/edit", AdminIssuePage.class);
        mountPage(ADMINS_BASE_URL + "/patients-all", AdminPatientsAllPage.class);
        mountPage(ADMINS_BASE_URL + "/patients-all/edit", AdminPatientAllPage.class);
        mountPage(ADMINS_BASE_URL + "/patients-user", AdminPatientsPage.class);
        mountPage(ADMINS_BASE_URL + "/patients-user/edit", AdminPatientPage.class);
        mountPage(ADMINS_BASE_URL + "/users", AdminUsersPage.class);
        mountPage(ADMINS_BASE_URL + "/users/edit", AdminUserPage.class);

        // patient pages
        mountPage("patient/edit", SrnsPatientPage.class);
        mountPage("patient/view", SrnsPatientPageReadOnly.class);
        mountPage("patients", ExistingPatientsListingPage.class);
        mountPage("patient/new", AddPatientPage.class);
        mountPage("patient/edit/generic", GenericPatientPage.class);

        // professional pages
        mountPage("registration/professional", ProfessionalRegistrationPage.class);
        mountPage("professionals", ProfessionalsPage.class);
        mountPage("recruitment", RecruitmentPage.class);
        mountPage("change-details", ChangeRegistrationDetails.class);

        // login pages
        mountPage("login/patient", PatientsLoginPage.class);
        mountPage("login/professional", ProfessionalsLoginPage.class);

        // forget password pages
        mountPage("patient/recover", PatientForgottenPasswordPage.class);
        mountPage("professional/recover", ProfessionalForgottenPasswordPage.class);

        // Static content pages
        mountPage("diseaseindex", DiseaseIndexPage.class);
        mountPage("mpgn", MpgnPage.class);
        mountPage("srns", SrnsPage.class);
        mountPage("consentforms", ConsentFormsPage.class);

        mountPage("error", ErrorPage.class);
    }

    protected IConverterLocator newConverterLocator() {
        // change the number formatters do disable grouping e.g. the comma in 1,500

        ConverterLocator converterLocator = new ConverterLocator();
        converterLocator.set(Integer.class, new IntegerConverter() {
            @Override
            public NumberFormat getNumberFormat(Locale locale) {
                NumberFormat numberFormat = super.getNumberFormat(Locale.getDefault());
                numberFormat.setGroupingUsed(false);
                return numberFormat;
            }
        });

        converterLocator.set(Double.class, new DoubleConverter() {
            @Override
            public NumberFormat getNumberFormat(Locale locale) {
                NumberFormat numberFormat = super.getNumberFormat(Locale.getDefault());
                numberFormat.setGroupingUsed(false);
                return numberFormat;
            }
        });

        converterLocator.set(Long.class, new LongConverter() {
            @Override
            public NumberFormat getNumberFormat(Locale locale) {
                NumberFormat numberFormat = super.getNumberFormat(Locale.getDefault());
                numberFormat.setGroupingUsed(false);
                return numberFormat;
            }
        });

        return converterLocator;
    }

    public boolean isAjaxDebug() {
        return ajaxDebug;
    }

    public void setAjaxDebug(boolean ajaxDebug) {
        this.ajaxDebug = ajaxDebug;
    }
}
