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
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;
import org.patientview.model.Patient;
import org.patientview.radar.model.user.PatientUser;
import org.patientview.radar.service.PatientManager;
import org.patientview.radar.service.UserManager;

public class AdminPatientPage extends AdminsBasePage {

    @SpringBean
    private PatientManager patientManager;

    @SpringBean
    private UserManager userManager;

    private static final String PARAM_ID = "ID";

    public AdminPatientPage(PageParameters parameters) {
        super();

        final Patient patient;
        final PatientUser patientUser;

        StringValue idValue = parameters.get(PARAM_ID);
        patientUser = userManager.getPatientUser(idValue.toLong());
        patient = patientManager.getPatientByRadarNumber(patientUser.getRadarNumber());

        CompoundPropertyModel<PatientUser> patientUserModel =
                new CompoundPropertyModel<PatientUser>(patientUser);

        final FeedbackPanel feedback = new FeedbackPanel("feedback");
        feedback.setOutputMarkupId(true);
        feedback.setOutputMarkupPlaceholderTag(true);
        add(feedback);

        final Form<PatientUser> userForm = new Form<PatientUser>("patientForm", patientUserModel) {
            protected void onSubmit() {
                try {
                    userManager.savePatientUser(getModelObject());
                } catch (Exception e) {
                    error("Could not save patient: " + e.toString());
                }
            }
        };
        add(userForm);

        userForm.add(new Label("radarNo", patientUser.getRadarNumber().toString()));
        userForm.add(new Label("forename", patient.getForename()));
        userForm.add(new Label("surname", patient.getSurname()));
        userForm.add(new RequiredTextField("username"));
        userForm.add(new Label("dob", patientUser.getDateOfBirth().toString()));
        userForm.add(new Label("dateRegistered", patientUser.getDateRegistered().toString()));

        userForm.add(new AjaxSubmitLink("updateTop") {
            protected void onSubmit(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                setResponsePage(AdminPatientsPage.class);
            }

            protected void onError(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                ajaxRequestTarget.add(feedback);
            }
        });

        userForm.add(new AjaxLink("cancelTop") {
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                setResponsePage(AdminPatientsPage.class);
            }
        });

        userForm.add(new AjaxSubmitLink("updateBottom") {
            protected void onSubmit(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                setResponsePage(AdminPatientsPage.class);
            }

            protected void onError(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                ajaxRequestTarget.add(feedback);
            }
        });

        userForm.add(new AjaxLink("cancelBottom") {
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                setResponsePage(AdminPatientsPage.class);
            }
        });
    }

    public static PageParameters getPageParameters(PatientUser patientUser) {
        return new PageParameters().set(PARAM_ID, patientUser.getId());
    }
}
