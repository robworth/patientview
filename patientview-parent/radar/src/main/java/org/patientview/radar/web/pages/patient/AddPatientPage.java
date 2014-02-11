/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.radar.web.pages.patient;

import org.apache.commons.collections.CollectionUtils;
import org.apache.wicket.Component;
import org.apache.wicket.Session;
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
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.patientview.model.Patient;
import org.patientview.model.Sex;
import org.patientview.model.enums.NhsNumberType;
import org.patientview.radar.dao.generic.DiseaseGroupDao;
import org.patientview.radar.model.filter.DemographicsFilter;
import org.patientview.radar.model.generic.AddPatientModel;
import org.patientview.radar.model.user.ProfessionalUser;
import org.patientview.radar.model.user.User;
import org.patientview.radar.service.DemographicsManager;
import org.patientview.radar.service.PatientManager;
import org.patientview.radar.service.UserManager;
import org.patientview.radar.web.RadarApplication;
import org.patientview.radar.web.RadarSecuredSession;
import org.patientview.radar.web.components.ComponentHelper;
import org.patientview.radar.web.components.RadarRequiredDropdownChoice;
import org.patientview.radar.web.components.RadarRequiredTextField;
import org.patientview.radar.web.pages.BasePage;
import org.patientview.radar.web.panels.CreatePatientPanel;
import org.patientview.radar.web.panels.SelectPatientPanel;
import org.patientview.util.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * generic add patient page
 * if you select srns or mpgn then redirects to phase1 patients page otherwise goes to generic patients page
 */
@AuthorizeInstantiation({User.ROLE_PROFESSIONAL, User.ROLE_SUPER_USER})
public class AddPatientPage extends BasePage {

    private static final String NO_PATIENTS_FOUND = "No existing patient records for this NHS number. "
                                                    + "Please press the \"create new patient\" button below";
    public static final String NHS_NUMBER_INVALID_MSG = "NHS or CHI number is not valid";

    @SpringBean
    private DiseaseGroupDao diseaseGroupDao;

    @SpringBean
    private DemographicsManager demographicsManager;

    @SpringBean
    private UserManager userManager;

    @SpringBean
    private PatientManager patientManager;


    public AddPatientPage() {

        ProfessionalUser user = (ProfessionalUser) RadarSecuredSession.get().getUser();

        // list of items to update in ajax submits
        final List<Component> componentsToUpdateList = new ArrayList<Component>();


        CompoundPropertyModel<AddPatientModel> addPatientModel =
                new CompoundPropertyModel<AddPatientModel>(new AddPatientModel());
        addPatientModel.getObject().setCentre(user.getCentre());

        final IModel<List<Patient>> patientListModel = new ListModel<Patient>();
        final SelectPatientPanel selectPatientPanel = new SelectPatientPanel("selectPatientPanel", patientListModel);
        selectPatientPanel.setVisible(false);

        final CreatePatientPanel createPatientPanel = new CreatePatientPanel("createPatientPanel", addPatientModel);
        createPatientPanel.setVisible(false);

        // create form
        Form<AddPatientModel> form = new Form<AddPatientModel>("form", addPatientModel) {

            final AddPatientModel model = getModelObject();


            @Override
            protected void onSubmit() {

                Session.get().cleanupFeedbackMessages();

                // just show the user one error at a time
                DemographicsFilter demographicsFilter = new DemographicsFilter();
                demographicsFilter.addSearchCriteria(DemographicsFilter.UserField.NHSNO.toString(),
                        model.getPatientId());

                List<String> radarUnits = userManager.getPatientRadarMappings(model.getPatientId());

                // check nhs number is valid
                if (!CommonUtils.isNhsNumberValidWhenUppercaseLettersAreAllowed(model.getPatientId())) {
                    selectPatientPanel.setVisible(false);
                    createPatientPanel.setVisible(false);
                    error(NHS_NUMBER_INVALID_MSG);

                } else if (CollectionUtils.isNotEmpty(radarUnits)) {
                    // check that this nhsno has a mapping in the radar system
                    selectPatientPanel.setVisible(false);
                    createPatientPanel.setVisible(false);
                    error("The patient is currently a member of the " + radarUnits.get(0) + " Registry");
                }

                if (!hasError()) {
                    patientListModel.setObject(patientManager.getPatientByNhsNumber(model.getPatientId()));

                    // If there is results show them otherwise hide them
                    if (CollectionUtils.isNotEmpty(patientListModel.getObject())) {
                        selectPatientPanel.setPatientModel(model);
                        selectPatientPanel.setVisible(true);
                    } else {
                        selectPatientPanel.setVisible(false);
                        info(NO_PATIENTS_FOUND);
                    }
                    createPatientPanel.setVisible(true);

                    setResponsePage(this.getPage());

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


        final WebMarkupContainer guidanceContainer = new WebMarkupContainer("guidanceContainer");
        guidanceContainer.setOutputMarkupPlaceholderTag(true);
        guidanceContainer.setVisible(true);

        guidanceContainer.add(
                new ExternalLink("consentFormsAndDiseaseGroupsCriteriaLink",
                        "http://rarerenal.org/radar-registry/criteria-and-consent/"));

        guidanceContainer.add(
                new ExternalLink("enrollingAPatientGuideLink", "http://rarerenal.org/radar-registry/" +
                        "radar-recruitment-guide/"));

        // add the components
        form.add(id, idType, diseaseGroup, submit, feedbackPanel, guidanceContainer);

        add(form, selectPatientPanel, createPatientPanel, pageNumber);
    }

    public void load() {

    }

}
