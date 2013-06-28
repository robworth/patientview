package org.patientview.radar.web.pages.regisration;


import org.patientview.radar.model.exception.DaoException;
import org.patientview.radar.model.exception.DecryptionException;
import org.patientview.radar.model.user.ProfessionalUser;
import org.patientview.radar.service.UserManager;
import org.patientview.radar.web.components.RadarComponentFactory;
import org.patientview.radar.web.components.RadarRequiredTextField;
import org.patientview.radar.web.pages.BasePage;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;
import org.springframework.security.core.AuthenticationException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChangeRegistrationDetails extends BasePage {

    public static final String VERIFICATION_FAILED_MESSAGE = "Invalid username and password. Please enter a valid " +
            "username and password";
    public static final String PASSWORD_ID = "password";
    public static final String PASSWORD_MISMATCH_ERROR_MESSAGE = "Please make sure both passwords match";
    public static final String UNEXPECTED_ERROR = "An unexpected error occured";
    @SpringBean
    private UserManager userManager;

    public ChangeRegistrationDetails() {

        // ------------------------- authentication section -------------------------------- //

        // need a model for password as no setter method for password in User.class probably for security
        final IModel<String> passwordModel = new Model<String>();

        // components to update on ajax
        final List<Component> componentsToUpdate = new ArrayList<Component>();

        final WebMarkupContainer authenticationPanel = new WebMarkupContainer("authenticationPanel");
        authenticationPanel.setOutputMarkupId(true);
        authenticationPanel.setOutputMarkupPlaceholderTag(true);
        componentsToUpdate.add(authenticationPanel);
        add(authenticationPanel);

        // controls whether the second changed details panel is visible
        final IModel<Boolean> changeDetailsVisibilityModel = new Model<Boolean>(false);

        final CompoundPropertyModel<ProfessionalUser> userModel = new CompoundPropertyModel<ProfessionalUser>(
                new Model<ProfessionalUser>(
                new ProfessionalUser()));
        final Form<ProfessionalUser> authenticationForm = new Form<ProfessionalUser>("authenticationForm",
                userModel) {
            @Override
            protected void onSubmit() {
                ProfessionalUser user = getModelObject();
                try {
                    boolean authenticated = userManager.authenticateProfessionalUser(user.getUsername(),
                            passwordModel.getObject());

                    if (!authenticated) {
                        error(VERIFICATION_FAILED_MESSAGE);
                    }
                } catch (AuthenticationException e) {
                    error(VERIFICATION_FAILED_MESSAGE);
                }

                if (isSubmitted() && !hasError()) {
                    authenticationPanel.setVisible(false);
                    changeDetailsVisibilityModel.setObject(true);
                }
            }

        };

        authenticationForm.add(new RadarRequiredTextField("username", authenticationForm, componentsToUpdate));
        final PasswordTextField password = RadarComponentFactory.getRequiredPasswordTextField(PASSWORD_ID,
                authenticationForm, componentsToUpdate);
        password.setOutputMarkupId(true);
        password.setOutputMarkupPlaceholderTag(true);
        authenticationForm.add(password);
        password.setModel(passwordModel);
        componentsToUpdate.add(password);

        // components to update on ajax for the change details components
        final List<Component> changeDetailsComponentsToUpdate = new ArrayList<Component>();

        authenticationForm.add(new AjaxSubmitLink("authenticationSubmit") {
            {
                setOutputMarkupId(true);
                setOutputMarkupPlaceholderTag(true);
            }

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                target.add(componentsToUpdate.toArray(new Component[componentsToUpdate.size()]));
                target.add(changeDetailsComponentsToUpdate.toArray(new Component[
                        changeDetailsComponentsToUpdate.size()]));
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(componentsToUpdate.toArray(new Component[componentsToUpdate.size()]));
                password.clearInput();
            }

        });
        authenticationPanel.add(authenticationForm);

        // Construct feedback panel
        final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback", new IFeedbackMessageFilter() {
            public boolean accept(FeedbackMessage feedbackMessage) {
                String message = feedbackMessage.getMessage().toString();
                return message.equals(VERIFICATION_FAILED_MESSAGE);
            }
        });

        componentsToUpdate.add(feedbackPanel);
        feedbackPanel.setOutputMarkupId(true);
        feedbackPanel.setOutputMarkupPlaceholderTag(true);
        authenticationForm.add(feedbackPanel);

        // --------------------------------------- change details section ------------------------------ //
        final WebMarkupContainer changeDetailsPanel = new WebMarkupContainer("changeDetailsPanel") {
            @Override
            public boolean isVisible() {
                return changeDetailsVisibilityModel.getObject();
            }
        };
        changeDetailsPanel.setOutputMarkupId(true);
        changeDetailsPanel.setOutputMarkupPlaceholderTag(true);
        changeDetailsComponentsToUpdate.add(changeDetailsPanel);
        add(changeDetailsPanel);

        final Form<PasswordModel> changeDetailsForm = new Form<PasswordModel>("changeDetailsForm",
                new CompoundPropertyModel<PasswordModel>(new Model<PasswordModel>(new PasswordModel()))) {
            @Override
            protected void onSubmit() {
                PasswordModel passwordModel = getModelObject();
                if (!passwordModel.password1.equals(passwordModel.password2)) {
                    error(PASSWORD_MISMATCH_ERROR_MESSAGE);
                }
                if (!hasError()) {
                    try {
                        userManager.changeUserPassword(userModel.getObject().getUsername(), getModelObject().password1);
                    } catch (DecryptionException e) {
                        error(UNEXPECTED_ERROR);
                    } catch (DaoException e) {
                        error(UNEXPECTED_ERROR);
                    }
                }
            }

        };

        final PasswordTextField password1 = RadarComponentFactory.getRadarPasswordTextFieldWithValidation("password1",
                StringValidator.lengthBetween(6, 10), true, changeDetailsForm, changeDetailsComponentsToUpdate);
        changeDetailsForm.add(password1);

        final PasswordTextField password2 = RadarComponentFactory.getRequiredPasswordTextField("password2",
                changeDetailsForm,
                changeDetailsComponentsToUpdate);
        changeDetailsForm.add(password2);


        password1.setOutputMarkupId(true);
        password1.setOutputMarkupPlaceholderTag(true);

        password2.setOutputMarkupId(true);
        password2.setOutputMarkupPlaceholderTag(true);

        changeDetailsComponentsToUpdate.add(password1);
        changeDetailsComponentsToUpdate.add(password2);

        AjaxButton changeDetailsSubmit = new IndicatingAjaxButton("changeDetailsSubmit") {
            {
                setOutputMarkupId(true);
                setOutputMarkupPlaceholderTag(true);
            }

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                target.add(changeDetailsComponentsToUpdate.toArray(new Component[
                        changeDetailsComponentsToUpdate.size()]));
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(changeDetailsComponentsToUpdate.toArray(new Component[
                        changeDetailsComponentsToUpdate.size()]));
                password1.clearInput();
                password2.clearInput();
            }

            @Override
            public boolean isVisible() {
                return !(changeDetailsForm.isSubmitted() && !changeDetailsForm.hasError());
            }
        };
        changeDetailsForm.add(changeDetailsSubmit);
        componentsToUpdate.add(changeDetailsSubmit);
        changeDetailsPanel.add(changeDetailsForm);

        // Construct feedback panel
        final FeedbackPanel changeDetailsfeedbackPanel = new FeedbackPanel("feedback", new IFeedbackMessageFilter() {
            public boolean accept(FeedbackMessage feedbackMessage) {
                String message = feedbackMessage.getMessage().toString();
                return message.equals(PASSWORD_MISMATCH_ERROR_MESSAGE) || message.equals(UNEXPECTED_ERROR);
            }
        });

        changeDetailsForm.add(new Label("successMessage", "Your password has been changed") {
            {
                setOutputMarkupId(true);
                setOutputMarkupPlaceholderTag(true);
                componentsToUpdate.add(this);
            }

            @Override
            public boolean isVisible() {
                return changeDetailsForm.isSubmitted() && !changeDetailsForm.hasError();
            }
        });


        changeDetailsComponentsToUpdate.add(changeDetailsfeedbackPanel);
        changeDetailsfeedbackPanel.setOutputMarkupId(true);
        changeDetailsfeedbackPanel.setOutputMarkupPlaceholderTag(true);
        changeDetailsForm.add(changeDetailsfeedbackPanel);

    }

    private class PasswordModel implements Serializable {
        String password1;
        String password2;
    }
}
