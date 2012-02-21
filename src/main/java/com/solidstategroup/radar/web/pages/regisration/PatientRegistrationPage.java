package com.solidstategroup.radar.web.pages.regisration;

import com.solidstategroup.radar.model.exception.RegistrationException;
import com.solidstategroup.radar.model.exception.UserEmailAlreadyExists;
import com.solidstategroup.radar.model.user.PatientUser;
import com.solidstategroup.radar.service.UserManager;
import com.solidstategroup.radar.web.components.RadarRequiredDateTextField;
import com.solidstategroup.radar.web.components.RadarTextFieldWithValidation;
import com.solidstategroup.radar.web.pages.BasePage;
import com.solidstategroup.radar.web.pages.login.PatientsLoginPage;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.StringValidator;

import java.util.ArrayList;
import java.util.List;

public class PatientRegistrationPage extends BasePage {

    public static final String FIX_ERRORS_MESSAGE = "Please fix all errors";
    public static final String COULD_NOT_REGISTER_MESSAGE = "Could not register - please check the radar number " +
            "and date of birth are correct";
    @SpringBean
    private UserManager userManager;

    public PatientRegistrationPage() {

        // Construct our model and the form
        CompoundPropertyModel<PatientUser> model = new CompoundPropertyModel<PatientUser>(new PatientUser());

        final List<Component> componentsToUpdateList = new ArrayList<Component>();

        final Form<PatientUser> form = new Form<PatientUser>("form", model) {
            @Override
            protected void onSubmit() {
                try {
                    PatientUser patientUser = getModelObject();
                    // Register
                    userManager.registerPatient(patientUser);
                    get("submit").setVisible(false);
                } catch (RegistrationException e) {
                    error(COULD_NOT_REGISTER_MESSAGE);
                } catch (UserEmailAlreadyExists userEmailAlreadyExists) {
                    get("username").error("Email address already exists");
                }
            }
        };
        add(form);
        final WebMarkupContainer successContainer = new WebMarkupContainer("success") {
            {
                setOutputMarkupId(true);
                setOutputMarkupPlaceholderTag(true);
                form.add(this);
                componentsToUpdateList.add(this);
            }

            @Override
            public boolean isVisible() {
                return form.isSubmitted() && !form.hasError();
            }
        };
        form.add(successContainer);
        successContainer.add(new Label("successMessage", "Your registration has been successful and you password has " +
                "been emailed to you. You can now login to the site"));

        successContainer.add(new BookmarkablePageLink("patientLink", PatientsLoginPage.class));

        // Construct feedback panel
        final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback", new IFeedbackMessageFilter() {
            public boolean accept(FeedbackMessage feedbackMessage) {
                String message = feedbackMessage.getMessage().toString();
                return message.contains(COULD_NOT_REGISTER_MESSAGE) || message.contains(FIX_ERRORS_MESSAGE);
            }
        });
        feedbackPanel.setOutputMarkupPlaceholderTag(true);
        form.add(feedbackPanel);

        // Add fields
        form.add(new RadarTextFieldWithValidation("radarNumber", null, true, form, componentsToUpdateList));
        TextField username = new RadarTextFieldWithValidation("username", new StringValidator(){
            @Override
            protected void onValidate(IValidatable<String> stringIValidatable) {
                /*
                  this does not do anything, pass a dummy validator so u can use this component with
                 adds a component feedback and component feedback indicator
                 */
            }
        }, true, form, componentsToUpdateList);
        form.add(username);

        // Required date field
        DateTextField dateOfBirth = new RadarRequiredDateTextField("dateOfBirth", form, componentsToUpdateList);
        form.add(dateOfBirth);

        // Ajax submit link
        AjaxButton submitLink = new IndicatingAjaxButton("submit") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                target.add(feedbackPanel);
                target.add(componentsToUpdateList.toArray(new Component[componentsToUpdateList.size()]));
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                form.error(FIX_ERRORS_MESSAGE);
                target.add(feedbackPanel);
                target.add(componentsToUpdateList.toArray(new Component[componentsToUpdateList.size()]));
            }
        };
        componentsToUpdateList.add(submitLink);
        form.add(submitLink);

    }
}
