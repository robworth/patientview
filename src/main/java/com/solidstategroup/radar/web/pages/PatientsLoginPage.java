package com.solidstategroup.radar.web.pages;

import com.solidstategroup.radar.model.user.PatientUser;
import com.solidstategroup.radar.web.SecuredSession;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

public class PatientsLoginPage extends BasePage {

    public PatientsLoginPage() {

        // Patients log in form
        CompoundPropertyModel<PatientUser> model = new CompoundPropertyModel<PatientUser>(new PatientUser());
        final Model<String> passwordModel = new Model<String>();

        Form<PatientUser> form = new Form<PatientUser>("form", model) {
            @Override
            protected void onSubmit() {
                // Todo: This needs to do checks on date of birth too

                // Get the wicket authentication session and ask to sign the user in with Spring security
                AuthenticatedWebSession session = SecuredSession.get();
                PatientUser user = getModelObject();
                if (session.signIn(user.getUsername(), passwordModel.getObject())) {
                    // If we haven't been diverted here from a page request (i.e. we clicked login),
                    // redirect to logged in page
                    if (!continueToOriginalDestination()) {
                        // Todo: Figure out where this should go
                        setResponsePage(ProfessionalsPage.class);
                    }
                } else {
                    // Show that the login failed if we couldn't authenticate
                    error("Login failed");
                }
            }
        };
        add(form);

        // Feedback panel
        final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setOutputMarkupPlaceholderTag(true);
        form.add(feedbackPanel);

        // Add components to form
        form.add(new RequiredTextField("username"));
        form.add(new PasswordTextField("password", passwordModel));

        // Date of birth with picker
        DateTextField dateOfBirth = DateTextField.forDatePattern("dateOfBirth", "dd-MM-yyyy");
        dateOfBirth.setRequired(true);
        dateOfBirth.add(new DatePicker());
        form.add(dateOfBirth);

        form.add(new AjaxSubmitLink("submit") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                target.add(feedbackPanel);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(feedbackPanel);
            }
        });

        // Add links for forgotten password and register
        add(new BookmarkablePageLink("forgottenPasswordLink", ForgottenPasswordPage.class));
        add(new BookmarkablePageLink("registerLink", PatientRegistrationPage.class));
    }
}
