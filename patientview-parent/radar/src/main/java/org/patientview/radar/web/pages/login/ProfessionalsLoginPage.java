package org.patientview.radar.web.pages.login;

import org.patientview.radar.model.user.ProfessionalUser;
import org.patientview.radar.model.user.User;
import org.patientview.radar.service.UserManager;
import org.patientview.radar.web.RadarSecuredSession;
import org.patientview.radar.web.components.RadarRequiredPasswordTextField;
import org.patientview.radar.web.components.RadarRequiredTextField;
import org.patientview.radar.web.pages.BasePage;
import org.patientview.radar.web.pages.ProfessionalsPage;
import org.patientview.radar.web.pages.regisration.ChangeRegistrationDetails;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.List;

public class ProfessionalsLoginPage extends BasePage {

    @SpringBean
    private UserManager userManager;
    private static final String LOGIN_FAILED_MESSAGE = "Login failed";

    public ProfessionalsLoginPage() {

        // Construct model for the form
        CompoundPropertyModel<ProfessionalUser> model =
                new CompoundPropertyModel<ProfessionalUser>(new ProfessionalUser());
        // Model for the password
        final IModel<String> passwordModel = new Model<String>();

        // components to update on ajax
        final List<Component> componentsToUpdate = new ArrayList<Component>();

        // Construct the form and add the fields
        Form<ProfessionalUser> form = new Form<ProfessionalUser>("form", model) {
            @Override
            protected void onSubmit() {
                // Get the wicket authentication session and ask to sign the user in with Spring security
                RadarSecuredSession session = RadarSecuredSession.get();
                ProfessionalUser user = getModelObject();
                boolean loginFailed = false;

                User professionalUser = userManager.getProfessionalUserWithUsername(user.getEmail());
                // Warning: super admins login via this page too
                if (professionalUser == null) {
                    professionalUser = userManager.getSuperUserWithUsername(user.getEmail());
                }
                if (professionalUser != null) {
                    if (session.signIn(user.getEmail(), passwordModel.getObject())) {
                        session.setUser(professionalUser);
                        // If we haven't been diverted here from a page request (i.e. we clicked login),
                        // redirect to logged in page
                        if (!continueToOriginalDestination()) {
                            setResponsePage(ProfessionalsPage.class);
                        }
                    } else {

                        loginFailed = true;
                    }
                } else {

                    loginFailed = true;
                }
                if (loginFailed) {
                    // Show that the login failed if we couldn't authenticate
                    error(LOGIN_FAILED_MESSAGE);
                }
            }
        };

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
        RadarRequiredPasswordTextField password = new RadarRequiredPasswordTextField("password",
                form, componentsToUpdate);
        form.add(password);
        password.setModel(passwordModel);

        form.add(new IndicatingAjaxButton("submit") {
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
        add(new BookmarkablePageLink("forgotPasswordLink", ProfessionalForgottenPasswordPage.class));
        add(new BookmarkablePageLink("changeDetailsLink", ChangeRegistrationDetails.class));
    }
}
