package org.patientview.radar.web.pages.login;

import org.patientview.radar.model.exception.DecryptionException;
import org.patientview.radar.model.exception.EmailAddressNotFoundException;
import org.patientview.radar.service.UserManager;
import org.patientview.radar.web.components.RadarRequiredTextField;
import org.patientview.radar.web.pages.BasePage;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.List;

public abstract class ForgottenPasswordPage extends BasePage {

    public static final String EMAIL_ADDRESS_NOT_RECOGNISED_MESSAGE = "Email address not recognised";
    public static final String ERROR_MESSAGE = "An unexpected error occured";
    @SpringBean
    protected UserManager userManager;

    public ForgottenPasswordPage() {

        Label userType = new Label("userType", getUserType());
        add(userType);

        // components to update on ajax submit
        final List<Component> componentsToUpdate = new ArrayList<Component>();
        // Construct form
        final Form<String> form = new Form<String>("form", new Model<String>()) {
            @Override
            protected void onSubmit() {
                try {
                    sendPassword(getModelObject());
                } catch (EmailAddressNotFoundException e) {
                    error(EMAIL_ADDRESS_NOT_RECOGNISED_MESSAGE);
                } catch (DecryptionException e) {
                    error(ERROR_MESSAGE);
                }
            }
        };
        add(form);

        // Feedback
        final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback", new IFeedbackMessageFilter() {
            public boolean accept(FeedbackMessage feedbackMessage) {
                String message = feedbackMessage.getMessage().toString();
                return message.contains(ERROR_MESSAGE) || message.contains(EMAIL_ADDRESS_NOT_RECOGNISED_MESSAGE);
            }
        });

        // success message
        Label successMessage = new Label("successMessage", "Your password has been emailed to you") {
            {
                setOutputMarkupId(true);
                setOutputMarkupPlaceholderTag(true);
                form.add(this);
                componentsToUpdate.add(this);
            }

            @Override
            public boolean isVisible() {
                return form.isSubmitted() && !form.hasError();
            }
        };
        form.add(feedbackPanel);
        componentsToUpdate.add(feedbackPanel);
        feedbackPanel.setOutputMarkupId(true);
        feedbackPanel.setOutputMarkupPlaceholderTag(true);

        // Email - can use same model as the form
        RadarRequiredTextField email = new RadarRequiredTextField("email", form, componentsToUpdate);
        email.setModel(form.getModel());
        form.add(email);

        // Submit link
        form.add(new IndicatingAjaxButton("submit") {
            {
                componentsToUpdate.add(this);
            }
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                target.add(componentsToUpdate.toArray(new Component[componentsToUpdate.size()]));
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(componentsToUpdate.toArray(new Component[componentsToUpdate.size()]));
            }

            @Override
            public boolean isVisible() {
                if (form.isSubmitted() && !form.hasError()) {
                     return false;
                }
                return super.isVisible();
            }
        });

    }

    protected abstract String getUserType();

    public abstract void sendPassword(String username) throws EmailAddressNotFoundException, DecryptionException;

}
