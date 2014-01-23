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

package org.patientview.radar.web.panels.alport;

import org.patientview.model.Patient;
import org.patientview.model.generic.DiseaseGroup;
import org.patientview.radar.model.alport.Medicine;
import org.patientview.radar.service.alport.MedicineManager;
import org.patientview.radar.service.generic.DiseaseGroupManager;
import org.patientview.radar.web.RadarApplication;
import org.patientview.radar.web.behaviours.RadarBehaviourFactory;
import org.patientview.radar.web.components.RadarDateTextField;
import org.patientview.radar.web.panels.PatientDetailPanel;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MedicinePanel extends Panel {

    @SpringBean
    private MedicineManager medicineManager;

    @SpringBean
    private DiseaseGroupManager diseaseGroupManager;

    private DiseaseGroup diseaseGroup;
    private Patient patient;
    private IModel<Medicine> editMedicineIModel;

    private WebMarkupContainer medicineListContainer;
    private Form<Medicine> editMedicineForm;
    private WebMarkupContainer editMedicineContainer;

    public MedicinePanel(final String id, final Patient patient) {
        super(id);

        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        this.patient = patient;

        // all medicines added use the alport disease group at the mo
        diseaseGroup = new DiseaseGroup();
        diseaseGroup.setId(DiseaseGroup.ALPORT_DISEASEGROUP_ID);

        // add the patient detail bar to the tab
        PatientDetailPanel patientDetail = new PatientDetailPanel("patientDetail", patient, "Medications");
        patientDetail.setOutputMarkupId(true);
        add(patientDetail);

        // create a model that we can edit
        editMedicineIModel = new Model<Medicine>();

        // add the current list of medicines
        medicineListContainer = createMedicineList();
        add(medicineListContainer);

        // create edit area
        editMedicineContainer = createEditMedicineContainer();
        add(editMedicineContainer);

        // add form to create new medicines
        add(new MedicineForm("addMedicineForm", new CompoundPropertyModel<Medicine>(new Medicine())));
    }

    private WebMarkupContainer createMedicineList() {
        final WebMarkupContainer medicinesContainer = new WebMarkupContainer("medicinesContainer");
        medicinesContainer.setOutputMarkupId(true);
        medicinesContainer.setOutputMarkupPlaceholderTag(true);

        final IModel<List<Medicine>> medicinesModel = new AbstractReadOnlyModel<List<Medicine>>() {
            @Override
            public List<Medicine> getObject() {
                return medicineManager.getMedicines(patient, diseaseGroup);
            }
        };

        ListView<Medicine> medicines = new ListView<Medicine>("medicines", medicinesModel) {
            @Override
            protected void populateItem(ListItem<Medicine> item) {
                final Medicine medicine = item.getModelObject();

                item.add(new Label("name", medicine.getName()));
                item.add(new Label("dose", medicine.getDose()));
                item.add(DateLabel.forDatePattern("startDate", new PropertyModel<Date>(medicine, "startDate"),
                        RadarApplication.DATE_PATTERN));
                item.add(DateLabel.forDatePattern("endDate", new PropertyModel<Date>(medicine, "endDate"),
                        RadarApplication.DATE_PATTERN));

                AjaxLink ajaxLink = new AjaxLink("deleteLink") {
                    @Override
                    public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                        medicineManager.delete(medicine);
                        ajaxRequestTarget.add(medicinesContainer);
                    }
                };
                ajaxLink.add(RadarBehaviourFactory.getDeleteConfirmationBehaviour());
                item.add(ajaxLink);

                AjaxLink ajaxEditLink = new AjaxLink("editLink") {
                    @Override
                    public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                        editMedicineIModel.setObject(medicine);
                        ajaxRequestTarget.add(editMedicineForm);
                        ajaxRequestTarget.add(editMedicineContainer);
                    }
                };
                item.add(ajaxEditLink);
            }
        };

        medicinesContainer.add(medicines);

        return medicinesContainer;
    }

    private WebMarkupContainer createEditMedicineContainer() {
        final WebMarkupContainer editMedicinesContainer = new WebMarkupContainer("editMedicinesContainer") {
            @Override
            public boolean isVisible() {
                return editMedicineIModel.getObject() != null && editMedicineIModel.getObject().hasValidId();
            }
        };
        editMedicinesContainer.setOutputMarkupId(true);
        editMedicinesContainer.setOutputMarkupPlaceholderTag(true);

        editMedicineForm = new MedicineForm("editMedicineForm", new CompoundPropertyModel<Medicine>(
                editMedicineIModel));

        editMedicineForm.add(new AjaxLink("cancel") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                editMedicineIModel.setObject(null);
                target.add(editMedicineForm);
                target.add(editMedicinesContainer);
            }
        });

        editMedicinesContainer.add(editMedicineForm);

        return editMedicinesContainer;
    }

    private class MedicineForm extends Form<Medicine> {
        private MedicineForm(String id, IModel<Medicine> medicineIModel) {
            super(id, medicineIModel);

            // create the add new medicine form
            // general feedback for messages that are not to do with a certain component in the form
            final FeedbackPanel medicineFormFeedback = new FeedbackPanel("formFeedback");
            medicineFormFeedback.setOutputMarkupId(true);
            medicineFormFeedback.setOutputMarkupPlaceholderTag(true);

            setOutputMarkupId(true);
            setOutputMarkupPlaceholderTag(true);

            TextField name = new TextField<String>("name");
            name.setRequired(true);
            add(name);

            TextField dose = new TextField<String>("dose");
            add(dose);

            RadarDateTextField startDate = new RadarDateTextField("startDate", this,
                    new ArrayList<Component>(0));
            startDate.setRequired(true);
            add(startDate);

            RadarDateTextField endDate = new RadarDateTextField("endDate", this,
                    new ArrayList<Component>(0));
            endDate.setRequired(false);
            add(endDate);

            add(medicineFormFeedback);

            add(new AjaxSubmitLink("submit") {
                @Override
                protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                    // clear the form
                    form.clearInput();
                    target.add(form);
                    target.add(medicineFormFeedback);
                    target.add(medicineListContainer);
                    target.add(editMedicineContainer);
                    target.appendJavaScript(RadarApplication.FORM_IS_DIRTY_FALSE_SCRIPT);
                }

                @Override
                protected void onError(AjaxRequestTarget target, Form<?> form) {
                    target.add(form);
                    target.add(medicineFormFeedback);
                }
            });
        }

        @Override
        protected void onSubmit() {
            Medicine medicine = getModelObject();

            if (!hasError()) {
                medicine.setNhsNo(patient.getNhsno());
                medicine.setDiseaseGroup(diseaseGroup);
                medicineManager.save(medicine);
                getModel().setObject(new Medicine());
            }
        }
    }
}
