package com.solidstategroup.radar.web.pages;

import com.solidstategroup.radar.model.user.ProfessionalUser;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

public class ProfessionalsLoginPage extends BasePage {

    public ProfessionalsLoginPage() {

        // Construct model for the form
        CompoundPropertyModel<ProfessionalUser> model =
                new CompoundPropertyModel<ProfessionalUser>(new ProfessionalUser());

        // Construct the form and add the fields
        Form<ProfessionalUser> form = new Form<ProfessionalUser>("form", model) {
            @Override
            protected void onSubmit() {
                // Get the wicket authentication session and ask to sign the user in with Spring security
                AuthenticatedWebSession session = AuthenticatedWebSession.get();
                ProfessionalUser user = getModelObject();
                if (session.signIn(user.getEmail(), user.getPassword())) {
                    // If we haven't been diverted here from a page request (i.e. we clicked login),
                    // redirect to logged in page
                    if (!continueToOriginalDestination()) {
                        setResponsePage(ProfessionalsPage.class);
                    }
                } else {
                    // Show that the login failed if we couldn't authenticate
                    error("Login failed");
                }

            }
        };

        // Construct a feedback panel for validation messages
        final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setOutputMarkupId(true);
        feedbackPanel.setOutputMarkupPlaceholderTag(true);
        form.add(feedbackPanel);

        form.add(new RequiredTextField("email"));
        form.add(new PasswordTextField("password"));
        form.add(new AjaxSubmitLink("submit") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                // Might need to clear any old messages
                target.add(feedbackPanel);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                // Update feedback panel with any validation messages
                target.add(feedbackPanel);
            }
        });
        add(form);
    }
}
