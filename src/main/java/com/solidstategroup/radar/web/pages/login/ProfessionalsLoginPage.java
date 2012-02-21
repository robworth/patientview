package com.solidstategroup.radar.web.pages.login;

import com.solidstategroup.radar.model.user.ProfessionalUser;
import com.solidstategroup.radar.service.UserManager;
import com.solidstategroup.radar.web.RadarSecuredSession;
import com.solidstategroup.radar.web.pages.BasePage;
import com.solidstategroup.radar.web.pages.ProfessionalsPage;
import com.solidstategroup.radar.web.pages.regisration.ChangeRegistrationDetails;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ProfessionalsLoginPage extends BasePage {

    @SpringBean
    private UserManager userManager;

    public ProfessionalsLoginPage() {

        // Construct model for the form
        CompoundPropertyModel<ProfessionalUser> model =
                new CompoundPropertyModel<ProfessionalUser>(new ProfessionalUser());
        // Model for the password
        final IModel<String> passwordModel = new Model<String>();

        // Construct the form and add the fields
        Form<ProfessionalUser> form = new Form<ProfessionalUser>("form", model) {
            @Override
            protected void onSubmit() {
                // Get the wicket authentication session and ask to sign the user in with Spring security
                AuthenticatedWebSession session = RadarSecuredSession.get();
                ProfessionalUser user = getModelObject();
                boolean loginFailed = false;
                ProfessionalUser professionalUser = userManager.getProfessionalUser(user.getEmail());
                if (professionalUser != null) {
                    if (session.signIn(user.getEmail(), passwordModel.getObject())) {
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
        form.add(new IndicatingAjaxButton("submit") {
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
        add(new BookmarkablePageLink("forgotPasswordLink", ProfessionalForgottenPasswordPage.class));
        add(new BookmarkablePageLink("changeDetailsLink", ChangeRegistrationDetails.class));
    }
}
