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

package org.patientview.radar.web.panels;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.patientview.radar.model.Hospitalisation;
import org.patientview.radar.service.DiagnosisManager;
import org.patientview.radar.service.HospitalisationManager;
import org.patientview.radar.service.PatientManager;
import org.patientview.radar.web.RadarApplication;
import org.patientview.radar.web.choiceRenderers.DateChoiceRenderer;
import org.patientview.radar.web.components.RadarComponentFactory;
import org.patientview.radar.web.components.RadarDateTextField;
import org.patientview.radar.web.components.RadarRequiredDateTextField;
import org.patientview.radar.web.models.RadarModelFactory;
import org.patientview.radar.web.pages.patient.srns.SrnsPatientPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class HospitalisationPanel extends Panel {

    @SpringBean
    private HospitalisationManager hospitalisationManager;
    @SpringBean
    private DiagnosisManager diagnosisManager;
    @SpringBean
    private PatientManager patientManager;

    public HospitalisationPanel(String id, final IModel<Long> radarNumberModel) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        // Add container for the form, it is not shown on first visit
        final MarkupContainer hospitalisationContainer = new WebMarkupContainer("hospitalisationContainer");
        hospitalisationContainer.setVisible(false);
        hospitalisationContainer.setOutputMarkupId(true);
        hospitalisationContainer.setOutputMarkupPlaceholderTag(true);
        add(hospitalisationContainer);

        // Set up models for the previous results switcher
        final IModel<Hospitalisation> hospitalisationModel = new Model<Hospitalisation>();

        IModel<List<Hospitalisation>> hospitalisations = new AbstractReadOnlyModel<List<Hospitalisation>>() {
            @Override
            public List<Hospitalisation> getObject() {
                if (radarNumberModel.getObject() == null) {
                    return Collections.emptyList();
                } else {
                    return hospitalisationManager.getHospitalisationsByRadarNumber(radarNumberModel.getObject());
                }
            }
        };

        // Previous results switcher
        final DropDownChoice<Hospitalisation> hospitilisationSwitcher =
                new DropDownChoice<Hospitalisation>("hospitilisationSwitcher", hospitalisationModel, hospitalisations,
                        new DateChoiceRenderer("dateOfAdmission", "id") {
                            @Override
                            protected Date getDate(Object object) {
                                return ((Hospitalisation) object).getDateOfAdmission();
                            }
                        });
        add(hospitilisationSwitcher);

        // Add ajax behaviour to update form
        hospitilisationSwitcher.add(new AjaxFormComponentUpdatingBehavior("onchange") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                hospitalisationContainer.setVisible(true);
                target.add(hospitalisationContainer);
            }
        });

        // Add ajax add new
        add(new AjaxLink("addNew") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                hospitalisationContainer.setVisible(true);
                hospitalisationModel.setObject(new Hospitalisation());
                hospitilisationSwitcher.clearInput();
                target.add(hospitalisationContainer, hospitilisationSwitcher);
            }
        });

        final List<Component> componentsToUpdate = new ArrayList<Component>();
        componentsToUpdate.add(hospitilisationSwitcher);

        // Set up the form
        Form<Hospitalisation> form = new Form<Hospitalisation>("form",
                new CompoundPropertyModel<Hospitalisation>(hospitalisationModel)) {
            @Override
            protected void onValidateModelObjects() {
                super.onValidateModelObjects();
                Hospitalisation hospitalisation = getModelObject();
                Date dateofAdmission = hospitalisation.getDateOfAdmission();
                Date dateofDischarge = hospitalisation.getDateOfDischarge();
                if (dateofAdmission != null && dateofDischarge != null
                        && dateofAdmission.compareTo(dateofDischarge) != -1) {
                    get("dateOfDischarge").error("Date has to be after admission date");
                }
            }

            @Override
            protected void onSubmit() {
                Hospitalisation hospitalisation = getModelObject();
                hospitalisation.setRadarNumber(radarNumberModel.getObject());
                hospitalisationManager.saveHospitilsation(hospitalisation);
            }
        };

        Label successLabel = RadarComponentFactory.getSuccessMessageLabel("successMessage", form, componentsToUpdate);
        Label successLabelDown = RadarComponentFactory.getSuccessMessageLabel("successMessageDown", form,
                componentsToUpdate);

        Label errorLabel = RadarComponentFactory.getErrorMessageLabel("errorMessage", form, componentsToUpdate);
        Label errorLabelDown = RadarComponentFactory.getErrorMessageLabel("errorMessageDown", form, componentsToUpdate);

        // General details
        TextField<Long> radarNumber = new TextField<Long>("radarNumber", radarNumberModel);
        radarNumber.setEnabled(false);
        form.add(radarNumber);

        form.add(new TextField("hospitalNumber", RadarModelFactory.getHospitalNumberModel(radarNumberModel,
                patientManager)));

        form.add(new TextField("diagnosis", new PropertyModel(RadarModelFactory.getDiagnosisCodeModel(radarNumberModel,
                diagnosisManager), "abbreviation")));

        form.add(new TextField("firstName", RadarModelFactory.getFirstNameModel(radarNumberModel,
                patientManager)));
        form.add(new TextField("surname", RadarModelFactory.getSurnameModel(radarNumberModel, patientManager)));
        form.add(new DateTextField("dob", RadarModelFactory.getDobModel(radarNumberModel, patientManager),
                RadarApplication.DATE_PATTERN));

        form.add(new RadarRequiredDateTextField("dateOfAdmission", form, componentsToUpdate));
        form.add(new RadarDateTextField("dateOfDischarge", form, componentsToUpdate));
        form.add(new TextArea("reason"));
        form.add(new TextArea("comments"));
        form.add(new HospitilisationAjaxSubmitLink("save") {
            @Override
            protected List<? extends Component> getComponentsToUpdate() {
                return componentsToUpdate;
            }
        });

        form.add(new HospitilisationAjaxSubmitLink("saveDown") {
            @Override
            protected List<? extends Component> getComponentsToUpdate() {
                return componentsToUpdate;
            }
        });
        hospitalisationContainer.add(form);
    }

    @Override
    public boolean isVisible() {
        return ((SrnsPatientPage) getPage()).getCurrentTab().equals(SrnsPatientPage.CurrentTab.HOSPITALISATION);
    }

    private abstract class HospitilisationAjaxSubmitLink extends AjaxSubmitLink {

        protected HospitilisationAjaxSubmitLink(String id) {
            super(id);
        }

        @Override
        public void onSubmit(AjaxRequestTarget target, Form<?> form) {
            target.add(getComponentsToUpdate().toArray(new Component[getComponentsToUpdate().size()]));
            target.appendJavaScript(RadarApplication.FORM_IS_DIRTY_FALSE_SCRIPT);
        }

        @Override
        protected void onError(AjaxRequestTarget target, Form<?> form) {
            target.add(getComponentsToUpdate().toArray(new Component[getComponentsToUpdate().size()]));
        }

        protected abstract List<? extends Component> getComponentsToUpdate();
    }
}
