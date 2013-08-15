package org.patientview.radar.web.pages.admin;

import org.patientview.radar.model.Demographics;
import org.patientview.radar.model.user.PatientUser;
import org.patientview.radar.service.DemographicsManager;
import org.patientview.radar.service.UserManager;
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

public class AdminPatientPage extends AdminsBasePage {

    @SpringBean
    private DemographicsManager demographicsManager;
    @SpringBean
    private UserManager userManager;

    private static final String PARAM_ID = "ID";

    public AdminPatientPage(PageParameters parameters) {
        super();

        final Demographics demographics;
        final PatientUser patientUser;

        StringValue idValue = parameters.get(PARAM_ID);
        patientUser = userManager.getPatientUser(idValue.toLong());
        demographics = demographicsManager.getDemographicsByRadarNumber(patientUser.getRadarNumber());

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
        userForm.add(new Label("forename", demographics.getForename()));
        userForm.add(new Label("surname", demographics.getSurname()));
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
