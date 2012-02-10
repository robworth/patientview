package com.solidstategroup.radar.web.pages.admin;

import com.solidstategroup.radar.model.user.AdminUser;
import com.solidstategroup.radar.web.RadarSecuredSession;
import com.solidstategroup.radar.web.pages.BasePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class AdminsLoginPage extends BasePage {
    public AdminsLoginPage() {

        // Construct model for the form
        CompoundPropertyModel<AdminUser> model =
                new CompoundPropertyModel<AdminUser>(new AdminUser());
        // Model for the password
        final IModel<String> passwordModel = new Model<String>();

        // Construct the form and add the fields
        Form<AdminUser> form = new Form<AdminUser>("form", model) {
            @Override
            protected void onSubmit() {
                // Get the wicket authentication session and ask to sign the user in with Spring security
                AuthenticatedWebSession session = RadarSecuredSession.get();
                AdminUser user = getModelObject();
                if (session.signIn(user.getEmail(), passwordModel.getObject())) {
                    // If we haven't been diverted here from a page request (i.e. we clicked login),
                    // redirect to logged in page
                    if (!continueToOriginalDestination()) {
                        setResponsePage(AdminsPage.class);
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
        form.add(new PasswordTextField("password", passwordModel));
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
