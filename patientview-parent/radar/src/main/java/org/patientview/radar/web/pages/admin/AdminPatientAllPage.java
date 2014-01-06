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

package org.patientview.radar.web.pages.admin;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;
import org.patientview.model.Patient;
import org.patientview.radar.model.Diagnosis;
import org.patientview.radar.model.DiagnosisCode;
import org.patientview.radar.service.DiagnosisManager;
import org.patientview.radar.service.PatientManager;

import java.util.Collections;
import java.util.List;

public class AdminPatientAllPage extends AdminsBasePage {

    @SpringBean
    private PatientManager patientManager;
    @SpringBean
    private DiagnosisManager diagnosisManager;

    private static final String PARAM_ID = "ID";

    public AdminPatientAllPage(PageParameters parameters) {
        super();

        final Patient patient;

        StringValue idValue = parameters.get(PARAM_ID);
        patient = patientManager.getPatientByRadarNumber(idValue.toLongObject());

        Diagnosis diagnosis = diagnosisManager.getDiagnosisByRadarNumber(patient.getId());

        CompoundPropertyModel<Diagnosis> diagnosisModel =
                new CompoundPropertyModel<Diagnosis>(diagnosis);

        final FeedbackPanel feedback = new FeedbackPanel("feedback");
        feedback.setOutputMarkupId(true);
        feedback.setOutputMarkupPlaceholderTag(true);
        add(feedback);

        final Form<Diagnosis> userForm = new Form<Diagnosis>("patientForm", diagnosisModel) {
            protected void onSubmit() {
                try {
                    diagnosisManager.saveDiagnosis(getModelObject());
                } catch (Exception e) {
                    error("Could not save diagnosis: " + e.toString());
                }
            }
        };
        add(userForm);

        userForm.add(new Label("radarNo", patient.getId().toString()));
        userForm.add(new Label("forename", patient.getForename()));
        userForm.add(new Label("surname", patient.getSurname()));

        // get centres and sort by name
        List<DiagnosisCode> diagnosisCodes = diagnosisManager.getDiagnosisCodes();
        Collections.sort(diagnosisCodes, DiagnosisCode.getComparator());

        DropDownChoice<DiagnosisCode> diagnosisCode = new DropDownChoice<DiagnosisCode>("diagnosisCode", diagnosisCodes,
                new ChoiceRenderer<DiagnosisCode>("abbreviation", "id"));
        diagnosisCode.setRequired(true);
        userForm.add(diagnosisCode);

        userForm.add(new AjaxSubmitLink("updateTop") {
            protected void onSubmit(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                setResponsePage(AdminPatientsAllPage.class);
            }

            protected void onError(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                ajaxRequestTarget.add(feedback);
            }
        });

        userForm.add(new AjaxLink("cancelTop") {
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                setResponsePage(AdminPatientsAllPage.class);
            }
        });

        userForm.add(new AjaxSubmitLink("updateBottom") {
            protected void onSubmit(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                setResponsePage(AdminPatientsAllPage.class);
            }

            protected void onError(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                ajaxRequestTarget.add(feedback);
            }
        });

        userForm.add(new AjaxLink("cancelBottom") {
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                setResponsePage(AdminPatientsAllPage.class);
            }
        });
    }

    public static PageParameters getPageParameters(Patient patient) {
        return new PageParameters().set(PARAM_ID, patient.getId());
    }
}
