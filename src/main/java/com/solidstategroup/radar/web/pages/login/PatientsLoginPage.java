package com.solidstategroup.radar.web.pages.login;

import com.solidstategroup.radar.model.user.PatientUser;
import com.solidstategroup.radar.service.UserManager;
import com.solidstategroup.radar.web.RadarApplication;
import com.solidstategroup.radar.web.RadarSecuredSession;
import com.solidstategroup.radar.web.components.RadarRequiredDateTextField;
import com.solidstategroup.radar.web.pages.BasePage;
import com.solidstategroup.radar.web.pages.PatientPageReadOnly;
import com.solidstategroup.radar.web.pages.regisration.PatientRegistrationPage;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.List;

public class PatientsLoginPage extends BasePage {

    @SpringBean
    private UserManager userManager;

    public PatientsLoginPage() {
        // Patients log in form
        CompoundPropertyModel<PatientUser> model = new CompoundPropertyModel<PatientUser>(new PatientUser());
        final Model<String> passwordModel = new Model<String>();

        Form<PatientUser> form = new Form<PatientUser>("form", model) {
            @Override
            protected void onSubmit() {
                AuthenticatedWebSession session = RadarSecuredSession.get();
                PatientUser user = getModelObject();
                boolean loginFailed = false;
                if (userManager.getPatientUser(user.getUsername(), user.getDateOfBirth()) != null) {
                    if (session.signIn(user.getUsername(), passwordModel.getObject())) {
                        PatientUser patientUser = userManager.getPatientUser(user.getUsername());
                        // If we haven't been diverted here from a page request (i.e. we clicked login),
                        // redirect to logged in page
                        setResponsePage(PatientPageReadOnly.class, PatientPageReadOnly.getParameters(
                                patientUser.getRadarNumber()));

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
        add(form);

        // Feedback panel
        final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setOutputMarkupPlaceholderTag(true);
        form.add(feedbackPanel);

        // Add components to form
        form.add(new RequiredTextField("username"));
        form.add(new PasswordTextField("password", passwordModel));

        // Date of birth with picker
        final List<Component> componentsToUpdateList = new ArrayList<Component>();
        DateTextField dateOfBirth = new RadarRequiredDateTextField("dateOfBirth",
                form, componentsToUpdateList);
        form.add(dateOfBirth);

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

        // Add links for forgotten password and register
        add(new BookmarkablePageLink<PatientForgottenPasswordPage>("forgottenPasswordLink",
                PatientForgottenPasswordPage.class));
        add(new BookmarkablePageLink<PatientRegistrationPage>("registerLink", PatientRegistrationPage.class));
    }
}
