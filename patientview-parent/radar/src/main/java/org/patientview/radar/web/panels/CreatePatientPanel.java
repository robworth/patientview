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

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.patientview.model.Patient;
import org.patientview.radar.model.generic.AddPatientModel;
import org.patientview.radar.util.RadarUtility;

/**
 * User: james@solidstategroup.com
 * Date: 20/11/13
 * Time: 17:17
 */
public class CreatePatientPanel extends Panel {

    private CompoundPropertyModel<AddPatientModel> addPatientModel;

    public CreatePatientPanel(String id, CompoundPropertyModel<AddPatientModel> addPatientModel) {
        super(id);
        this.addPatientModel = addPatientModel;
        init();
    }


    private void init() {
        // Add the form that will create a patient from scratch
        add(createCreateForm());

    }

    private Form createCreateForm() {

        // This is the form that submits to the new create patient form

        AjaxSubmitLink create = new AjaxSubmitLink("create") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
            }

        };

        Form<?> createForm = new Form<Patient>("patientCreationForm") {
            @Override
            protected void onSubmit() {
                Patient patient = new Patient();
                patient.setDiseaseGroup(addPatientModel.getObject().getDiseaseGroup());
                patient.setNhsno(addPatientModel.getObject().getPatientId());
                patient.setNhsNumberType(addPatientModel.getObject().getNhsNumberType());
                setResponsePage(RadarUtility.getDiseasePage(patient, this.getPage().getPageParameters()));

            }
        };
        createForm.add(create);

        return createForm;
    }

}
