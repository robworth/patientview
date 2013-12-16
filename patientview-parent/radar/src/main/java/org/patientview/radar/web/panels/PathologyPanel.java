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
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.RangeValidator;
import org.patientview.radar.model.enums.KidneyTransplantedNative;
import org.patientview.radar.model.exception.InvalidModelException;
import org.patientview.radar.model.sequenced.Pathology;
import org.patientview.radar.service.DiagnosisManager;
import org.patientview.radar.service.PathologyManager;
import org.patientview.radar.service.PatientManager;
import org.patientview.radar.web.RadarApplication;
import org.patientview.radar.web.choiceRenderers.DateChoiceRenderer;
import org.patientview.radar.web.components.RadarComponentFactory;
import org.patientview.radar.web.components.RadarRequiredDateTextField;
import org.patientview.radar.web.components.RadarTextFieldWithValidation;
import org.patientview.radar.web.models.RadarModelFactory;
import org.patientview.radar.web.pages.patient.srns.SrnsPatientPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class PathologyPanel extends Panel {

    @SpringBean
    private PathologyManager pathologyManager;
    @SpringBean
    private DiagnosisManager diagnosisManager;
    @SpringBean
    private PatientManager patientManager;

    public PathologyPanel(String id, final IModel<Long> radarNumberModel) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        // Model for tha pathology container, the pathology ID
        final IModel<Pathology> pathologyModel = new Model<Pathology>();

        IModel pathologiesListModel = new AbstractReadOnlyModel<List>() {
            @Override
            public List getObject() {
                if (radarNumberModel.getObject() != null) {
                    List list = pathologyManager.getPathologyByRadarNumber(radarNumberModel.getObject());
                    return !list.isEmpty() ? list : Collections.emptyList();
                }
                return Collections.emptyList();
            }
        };

        // Container for form, so we can hide and first then show
        final MarkupContainer pathologyContainer = new WebMarkupContainer("pathologyContainer");
        pathologyContainer.setVisible(false);
        pathologyContainer.setOutputMarkupId(true);
        pathologyContainer.setOutputMarkupPlaceholderTag(true);
        add(pathologyContainer);

        // Switcheroo
        final DropDownChoice<Pathology> pathologySwitcher =
                new DropDownChoice<Pathology>("pathologySwitcher", pathologyModel, pathologiesListModel,
                        new DateChoiceRenderer("biopsyDate", "id") {
                            @Override
                            protected Date getDate(Object object) {
                                return ((Pathology) object).getBiopsyDate();
                            }
                        });
        pathologySwitcher.setOutputMarkupId(true);
        pathologySwitcher.setOutputMarkupPlaceholderTag(true);
        add(pathologySwitcher);

        // Add new
        add(new AjaxLink("addNew") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                pathologyContainer.setVisible(true);
                pathologyModel.setObject(new Pathology());
                pathologySwitcher.clearInput();
                target.add(pathologyContainer, pathologySwitcher);
            }
        });

        // Add Ajax behaviour to switch the container on change
        pathologySwitcher.add(new AjaxFormComponentUpdatingBehavior("onchange") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                pathologyContainer.setVisible(true);
                target.add(pathologyContainer);
            }
        });

        final List<Component> componentsToUpdate = new ArrayList<Component>();
        componentsToUpdate.add(pathologySwitcher);

        // Set up model
        Form<Pathology> form = new Form<Pathology>("form", new CompoundPropertyModel<Pathology>(pathologyModel)) {
            @Override
            protected void onSubmit() {
                Pathology pathology = getModelObject();
                pathology.setRadarNumber(radarNumberModel.getObject());
                try {
                    pathologyManager.savePathology(pathology);
                } catch (InvalidModelException e) {
                    for (String message : e.getErrors()) {
                        if (message.equals(PathologyManager.TOTAL_ERROR)) {
                            get("totalNumber").error(PathologyManager.TOTAL_ERROR);
                        }
                    }
                }
            }
        };
        pathologyContainer.add(form);

        RadarComponentFactory.getSuccessMessageLabel("successMessage", form, componentsToUpdate);
        RadarComponentFactory.getSuccessMessageLabel("successMessageDown", form, componentsToUpdate);

        RadarComponentFactory.getErrorMessageLabel("errorMessage", form, componentsToUpdate);
        RadarComponentFactory.getErrorMessageLabel("errorMessageDown", form, componentsToUpdate);

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

        // Add inputs
        form.add(new RadarRequiredDateTextField("biopsyDate", form, componentsToUpdate));

        RadioGroup<KidneyTransplantedNative> kideneyTransplant =
                new RadioGroup<KidneyTransplantedNative>("KidneyTransplantedNative");

        kideneyTransplant.add(new Radio<KidneyTransplantedNative>("native",
                new Model<KidneyTransplantedNative>(KidneyTransplantedNative.NATIVE)));
        kideneyTransplant.add(new Radio<KidneyTransplantedNative>("txKidney",
                new Model<KidneyTransplantedNative>(KidneyTransplantedNative.TRANSPLANTED)));

        form.add(kideneyTransplant);

        RadioGroup<Pathology.Side> side = new RadioGroup<Pathology.Side>("side");
        side.add(new Radio<Pathology.Side>("left", new Model<Pathology.Side>(Pathology.Side.LEFT)));
        side.add(new Radio<Pathology.Side>("right", new Model<Pathology.Side>(Pathology.Side.RIGHT)));
        form.add(side);

        form.add(new TextField("sampleLabNumber"));
        form.add(new TextArea("interstitalInflmatoryInfilitrate"));
        form.add(new TextArea("arterialAbnormalities"));
        form.add(new TextArea("immunohistologicalFindings"));
        form.add(new TextArea("electronMicroscopicFindings"));

        form.add(new RadarTextFieldWithValidation("estimatedTubules", new RangeValidator<Double>(0.0, 100.0),
                form, componentsToUpdate));
        form.add(new RadarTextFieldWithValidation("measuredTubules",
                new RangeValidator<Double>(0.0, 100.0), form, componentsToUpdate));
        form.add(new TextArea("tubulesOtherFeature"));

        form.add(new TextField("imageUrl1"));
        form.add(new TextField("imageUrl2"));
        form.add(new TextField("imageUrl3"));
        form.add(new TextField("imageUrl4"));
        form.add(new TextField("imageUrl5"));

        form.add(new RadarTextFieldWithValidation("totalNumber", new RangeValidator<Integer>(0, 150), form,
                componentsToUpdate));
        form.add(new RadarTextFieldWithValidation("numberSclerosed", new RangeValidator<Integer>(0, 150), form,
                componentsToUpdate));
        form.add(new RadarTextFieldWithValidation("numberSegmentallySclerosed", new RangeValidator<Integer>(0, 150),
                form, componentsToUpdate));
        form.add(new RadarTextFieldWithValidation("numberCellularCrescents", new RangeValidator<Integer>(0, 150), form,
                componentsToUpdate));
        form.add(new RadarTextFieldWithValidation("numberFibrousCrescents", new RangeValidator<Integer>(0, 150), form,
                componentsToUpdate));
        form.add(new RadarTextFieldWithValidation("numberEndocapillaryHypercelluarity",
                new RangeValidator<Integer>(0, 150), form, componentsToUpdate));
        form.add(new RadarTextFieldWithValidation("numberFibrinoidNecrosis", new RangeValidator<Integer>(0, 150), form,
                componentsToUpdate));

        form.add(new TextArea("otherFeature"));

        form.add(new TextArea("histologicalSummary"));

        form.add(new PathologySubmitLink("save", form) {
            @Override
            protected List<Component> getComponentsToUpdate() {
                return componentsToUpdate;
            }
        });

        form.add(new PathologySubmitLink("saveDown", form) {
            @Override
            protected List<Component> getComponentsToUpdate() {
                return componentsToUpdate;
            }
        });
    }

    @Override
    public boolean isVisible() {
        return ((SrnsPatientPage) getPage()).getCurrentTab().equals(SrnsPatientPage.CurrentTab.PATHOLOGY);
    }

    private abstract class PathologySubmitLink extends AjaxSubmitLink {

        protected PathologySubmitLink(String id, Form<?> form) {
            super(id, form);
        }

        @Override
        protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
            target.add(getComponentsToUpdate().toArray(new Component[getComponentsToUpdate().size()]));
            target.appendJavaScript(RadarApplication.FORM_IS_DIRTY_FALSE_SCRIPT);
        }

        @Override
        protected void onError(AjaxRequestTarget target, Form<?> form) {
            target.add(getComponentsToUpdate().toArray(new Component[getComponentsToUpdate().size()]));
            ComponentFeedbackPanel a = (ComponentFeedbackPanel) getParent().get("totalNumberFeedback");
            a.getFeedbackMessages();
        }

        protected abstract List<Component> getComponentsToUpdate();
    }
}
