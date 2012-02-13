package com.solidstategroup.radar.web.pages;

import com.solidstategroup.radar.model.exception.RegistrationException;
import com.solidstategroup.radar.model.user.PatientUser;
import com.solidstategroup.radar.service.UserManager;
import com.solidstategroup.radar.web.components.RadarRequiredDateTextField;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.List;

public class PatientRegistrationPage extends BasePage {

    @SpringBean
    private UserManager userManager;

    public PatientRegistrationPage() {

        // Construct our model and the form
        CompoundPropertyModel<PatientUser> model = new CompoundPropertyModel<PatientUser>(new PatientUser());
        // Radar number is primitive so have separate model
        final Model<Long> radarNumberModel = new Model<Long>();

        Form<PatientUser> form = new Form<PatientUser>("form", model) {
            @Override
            protected void onSubmit() {
                try {
                    PatientUser patientUser = getModelObject();

                    // Set radar number on user model
                    patientUser.setRadarNumber(radarNumberModel.getObject());

                    // Register
                    userManager.registerPatient(patientUser);
                } catch (RegistrationException e) {
                    error("Could not register - please check the radar number and date of birth are correct");
                }
            }
        };
        add(form);

        // Construct feedback panel
        final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setOutputMarkupPlaceholderTag(true);
        form.add(feedbackPanel);

        // Add fields
        form.add(new RequiredTextField<Long>("radarNumber", radarNumberModel, Long.class));
        form.add(new RequiredTextField("username"));

        // Required date field
        final List<Component> componentsToUpdateList = new ArrayList<Component>();
        DateTextField dateOfBirth = new RadarRequiredDateTextField("dateOfBirth", form, componentsToUpdateList);
        form.add(dateOfBirth);

        // Ajax submit link
        form.add(new AjaxSubmitLink("submit") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                target.add(feedbackPanel);
                target.add(componentsToUpdateList.toArray(new Component[componentsToUpdateList.size()]));
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(feedbackPanel);
                target.add(componentsToUpdateList.toArray(new Component[componentsToUpdateList.size()]));
            }
        });
    }
}
