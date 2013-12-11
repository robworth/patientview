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
