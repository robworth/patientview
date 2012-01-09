package com.solidstategroup.radar.web.pages;

import com.solidstategroup.radar.service.UserManager;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ForgottenPasswordPage extends BasePage {

    @SpringBean
    private UserManager userManager;

    public ForgottenPasswordPage() {

        // Construct form
        Form<String> form = new Form<String>("form", new Model<String>()) {
            @Override
            protected void onSubmit() {
                userManager.sendForgottenPassword(getModelObject());
            }
        };
        add(form);

        // Feedback
        final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        form.add(feedbackPanel);

        // Email - can use same model as the form
        form.add(new RequiredTextField<String>("email", form.getModel()));

        // Submit link
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
    }

}
