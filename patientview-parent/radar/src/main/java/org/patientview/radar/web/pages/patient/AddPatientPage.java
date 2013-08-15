package org.patientview.radar.web.pages.patient;

import org.patientview.radar.dao.generic.DiseaseGroupDao;
import org.patientview.radar.model.Sex;
import org.patientview.radar.model.enums.NhsNumberType;
import org.patientview.radar.model.filter.DemographicsFilter;
import org.patientview.radar.model.generic.AddPatientModel;
import org.patientview.radar.model.generic.DiseaseGroup;
import org.patientview.radar.model.user.ProfessionalUser;
import org.patientview.radar.model.user.User;
import org.patientview.radar.service.DemographicsManager;
import org.patientview.radar.service.UserManager;
import org.patientview.radar.service.UtilityManager;
import org.patientview.radar.web.RadarApplication;
import org.patientview.radar.web.RadarSecuredSession;
import org.patientview.radar.web.components.ComponentHelper;
import org.patientview.radar.web.components.RadarRequiredDropdownChoice;
import org.patientview.radar.web.components.RadarRequiredTextField;
import org.patientview.radar.web.pages.BasePage;
import org.patientview.radar.web.pages.patient.alport.AlportPatientPage;
import org.patientview.radar.web.pages.patient.hnf1b.HNF1BPatientPage;
import org.patientview.radar.web.pages.patient.srns.SrnsPatientPage;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.List;

/**
 * generic add patient page
 * if you select srns or mpgn then redirects to phase1 patients page otherwise goes to generic patients page
 */
@AuthorizeInstantiation({User.ROLE_PROFESSIONAL, User.ROLE_SUPER_USER})
public class AddPatientPage extends BasePage {
    public static final String NHS_NUMBER_INVALID_MSG = "NHS or CHI number is not valid";

    @SpringBean
    private DiseaseGroupDao diseaseGroupDao;

    @SpringBean
    private DemographicsManager demographicsManager;

    @SpringBean
    private UserManager userManager;

    @SpringBean
    private UtilityManager utilityManager;

    public AddPatientPage() {
        ProfessionalUser user = (ProfessionalUser) RadarSecuredSession.get().getUser();

        // list of items to update in ajax submits
        final List<Component> componentsToUpdateList = new ArrayList<Component>();
        final WebMarkupContainer pvMessageContainer = new WebMarkupContainer("pvMessageContainer");
        pvMessageContainer.setOutputMarkupPlaceholderTag(true);
        pvMessageContainer.setVisible(false);
        pvMessageContainer.add(
                new ExternalLink("patientViewLink",
                        utilityManager.getPatientViewSiteUrl(), utilityManager.getPatientViewSiteUrl()));

        CompoundPropertyModel<AddPatientModel> addPatientModel =
                new CompoundPropertyModel<AddPatientModel>(new AddPatientModel());
        addPatientModel.getObject().setCentre(user.getCentre());

        // create form
        Form<AddPatientModel> form = new Form<AddPatientModel>("form", addPatientModel) {
            @Override
            protected void onSubmit() {
                AddPatientModel model = getModelObject();

                // just show the user one error at a time

                DemographicsFilter demographicsFilter = new DemographicsFilter();
                demographicsFilter.addSearchCriteria(DemographicsFilter.UserField.NHS_NO.toString(),
                        model.getPatientId());

                // check nhs number is valid
                if (!demographicsManager.isNhsNumberValidWhenUppercaseLettersAreAllowed(model.getPatientId())) {
                    error(NHS_NUMBER_INVALID_MSG);

                } else if (demographicsManager.getDemographics(demographicsFilter).size() > 0) {
                    // check that this nhsno does not already exist in the radar system
                    error("A patient with this NHS or CHI number already exists");

                } else if (!userManager.userExistsInPatientView(model.getPatientId())) {
                    // If nhsno is not already in patient view inform user they need to add the patient using the
                    // patient view application.
                    pvMessageContainer.setVisible(true);
                    error("All patients must have a PatientView user. That NHS number is not currently in " +
                            "PatientView hence you will need to to go to PatientView to add it.");
                }

                // TODO: this is terrible as we need to check disease groups to know where to send it - well done abul
                // TODO: need to implement a patient base page with the constructors needed and then have an enum map
                // TODO: that maps disease ids to the page they need to go to so we dont need all these ifs
                if (!hasError()) {
                    if (model.getDiseaseGroup() != null) {
                        if (model.getDiseaseGroup().getId().equals(DiseaseGroup.SRNS_DISEASE_GROUP_ID) ||
                                model.getDiseaseGroup().getId().
                                        equals(DiseaseGroup.MPGN_DISEASEGROUP_ID)) {
                            setResponsePage(SrnsPatientPage.class, SrnsPatientPage.getParameters(model));
                        } else if (model.getDiseaseGroup().getId().equals(DiseaseGroup.ALPORT_DISEASEGROUP_ID)) {
                            setResponsePage(new AlportPatientPage(model));
                        } else if (model.getDiseaseGroup().getId().equals(DiseaseGroup.HNF1B_DISEASEGROUP_ID)) {
                            setResponsePage(new HNF1BPatientPage(model));
                        } else {
                            setResponsePage(new GenericPatientPage(model));
                        }
                    }
                }
            }
        };

        // create components
        RadarRequiredTextField id = new RadarRequiredTextField("patientId", form, componentsToUpdateList);

        RadarRequiredDropdownChoice idType =
                new RadarRequiredDropdownChoice("nhsNumberType", NhsNumberType.getNhsNumberTypesAsList(),
                        new ChoiceRenderer() {
                            @Override
                            public Object getDisplayValue(Object object) {
                                return ((NhsNumberType) object).getName();
                            }

                            @Override
                            public String getIdValue(Object object, int index) {
                                return ((NhsNumberType) object).getId() + "";
                            }
                        }, form, componentsToUpdateList);

        RadarRequiredDropdownChoice diseaseGroup =
                new RadarRequiredDropdownChoice("diseaseGroup", diseaseGroupDao.getAll(),
                        new ChoiceRenderer<Sex>("name", "id"), form, componentsToUpdateList);
        Label pageNumber = new Label("pageNumber", RadarApplication.ADD_PATIENT_PAGE_N0 + "");

        // submit link
        AjaxSubmitLink submit = new AjaxSubmitLink("submit") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                ComponentHelper.updateComponentsIfParentIsVisible(target, componentsToUpdateList);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                ComponentHelper.updateComponentsIfParentIsVisible(target, componentsToUpdateList);
            }
        };

        // feedback panel
        final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback", new IFeedbackMessageFilter() {
            public boolean accept(FeedbackMessage feedbackMessage) {
                String message = feedbackMessage.getMessage().toString();
                return message != null && message.length() > 0;
            }
        });

        feedbackPanel.setOutputMarkupPlaceholderTag(true);
        componentsToUpdateList.add(feedbackPanel);
        componentsToUpdateList.add(pvMessageContainer);

        final WebMarkupContainer guidanceContainer = new WebMarkupContainer("guidanceContainer");
        guidanceContainer.setOutputMarkupPlaceholderTag(true);
        guidanceContainer.setVisible(true);

        guidanceContainer.add(
                new ExternalLink("consentFormsAndDiseaseGroupsCriteriaLink",
                        "http://www.rarerenal.org/join/criteria-and-consent/"));

        guidanceContainer.add(
                new ExternalLink("enrollingAPatientGuideLink", "http://rarerenal.org/radar-registry/" +
                        "radar-registry-background-information/radar-recruitment-guide/"));

        // add the components
        form.add(id, idType, diseaseGroup, submit, feedbackPanel, pvMessageContainer, guidanceContainer);

        add(form, pageNumber);
    }
}
