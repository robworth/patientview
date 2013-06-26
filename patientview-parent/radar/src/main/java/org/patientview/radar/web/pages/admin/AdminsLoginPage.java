package org.patientview.radar.web.pages.admin;

import org.patientview.radar.model.user.AdminUser;
import org.patientview.radar.service.UserManager;
import org.patientview.radar.web.RadarSecuredSession;
import org.patientview.radar.web.components.RadarRequiredPasswordTextField;
import org.patientview.radar.web.components.RadarRequiredTextField;
import org.patientview.radar.web.pages.BasePage;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.List;

public class AdminsLoginPage extends BasePage {

    @SpringBean
    private UserManager userManager;
    private static final String LOGIN_FAILED_MESSAGE = "Login failed";

    public AdminsLoginPage() {

        // Construct model for the form
        CompoundPropertyModel<AdminUser> model =
                new CompoundPropertyModel<AdminUser>(new AdminUser());
        // Model for the password
        final IModel<String> passwordModel = new Model<String>();

        final List<Component> componentsToUpdate = new ArrayList<Component>();

        // Construct the form and add the fields
        Form<AdminUser> form = new Form<AdminUser>("form", model) {
            @Override
            protected void onSubmit() {
                // Get the wicket authentication session and ask to sign the user in with Spring security
                RadarSecuredSession session = RadarSecuredSession.get();
                AdminUser user = getModelObject();
                boolean loginFailed = false;
                // do an extra check to that an admin user exists with the username
                AdminUser adminUser = userManager.getAdminUserWithUsername(user.getEmail());
                if (adminUser != null) {
                    if (session.signIn(user.getEmail(), passwordModel.getObject())) {
                        session.setUser(adminUser);
                        // If we haven't been diverted here from a page request (i.e. we clicked login),
                        // redirect to logged in page
                        if (!continueToOriginalDestination()) {
                            setResponsePage(AdminsPage.class);
                        }
                    } else {
                        loginFailed = true;
                    }

                } else {
                    loginFailed = true;
                }

                if (loginFailed) {
                    // Show that the login failed if we couldn't authenticate
                    error("Login failed");
                }
            }
        };

        // Construct a feedback panel for validation messages
        // Construct feedback panel
        final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback", new IFeedbackMessageFilter() {
            public boolean accept(FeedbackMessage feedbackMessage) {
                String message = feedbackMessage.getMessage().toString();
                return message.contains(LOGIN_FAILED_MESSAGE);
            }
        });

        feedbackPanel.setOutputMarkupId(true);
        feedbackPanel.setOutputMarkupPlaceholderTag(true);
        form.add(feedbackPanel);
        componentsToUpdate.add(feedbackPanel);

        form.add(new RadarRequiredTextField("email", form, componentsToUpdate));
        RadarRequiredPasswordTextField password = new RadarRequiredPasswordTextField("password", form,
                componentsToUpdate);
        password.setModel(passwordModel);
        form.add(password);

        form.add(new AjaxSubmitLink("submit") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                // Might need to clear any old messages
                target.add(componentsToUpdate.toArray(new Component[componentsToUpdate.size()]));
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                // Update feedback panel with any validation messages
                target.add(componentsToUpdate.toArray(new Component[componentsToUpdate.size()]));
            }
        });
        add(form);
    }
}
